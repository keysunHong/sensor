package com.keysun.sensor.service;

import com.keysun.sensor.po.SensorConstant;
import com.keysun.sensor.po.SensorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by h on 2017/10/10.
 *
 * @desc 等待map的监听类
 */
@Configuration
public class SensorStateListener extends Thread {
    public static Logger logger = LoggerFactory.getLogger(SensorStateListener.class);

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run() {
        while (true) {
            try {
                String sql1 = "select * from sensor_constant where constant_type='l'";
                RowMapper<SensorConstant> rm = BeanPropertyRowMapper.newInstance(SensorConstant.class);
                SensorConstant light = jdbcTemplate.queryForObject(sql1, new Object[]{}, rm);
                String sql2 = "select * from sensor_constant where constant_type='t'";
                SensorConstant temperature = jdbcTemplate.queryForObject(sql2, new Object[]{}, rm);
                String sql3 = "select * from sensor_state where temperature_state is null or light_state is null";
                List<SensorState> sensorStates = jdbcTemplate.query(sql3, new RowMapper<SensorState>() {
                    @Override
                    public SensorState mapRow(ResultSet resultSet, int i) throws SQLException {
                        SensorState sensorState = new SensorState();
                        sensorState.setId(resultSet.getInt("id"));
                        sensorState.setMagnetic_steel(resultSet.getString("magnetic_steel"));
                        sensorState.setTrain(resultSet.getString("train"));
                        sensorState.setBrush(resultSet.getString("brush"));
                        sensorState.setBrush_reset(resultSet.getString("brush_reset"));
                        sensorState.setRaindrop(resultSet.getString("raindrop"));
                        sensorState.setLight(resultSet.getDouble("light"));
                        sensorState.setLight_state(resultSet.getString("brush")==null ? "":resultSet.getString("brush"));
                        sensorState.setTemperature(resultSet.getDouble("light"));
                        sensorState.setTemperature_state(resultSet.getString("brush")==null ? "":resultSet.getString("brush"));
                        sensorState.setUpdate_time(resultSet.getTimestamp("update_time"));
                        return sensorState;
                    }
                });
                for(SensorState sensorState : sensorStates){
                    logger.info(sensorState.toString());
                    double lightValue = sensorState.getLight();
                    double temperatureValue = sensorState.getTemperature();
                    if(lightValue>=light.getConstant_low() && lightValue<=light.getConstant_hight()){
                        sensorState.setLight_state("000");
                    }else if(lightValue>light.getConstant_hight()){
                        sensorState.setLight_state("001");
                    }else{
                        sensorState.setLight_state("-001");
                    }

                    if(temperatureValue>=temperature.getConstant_low() && temperatureValue<=temperature.getConstant_hight()){
                        sensorState.setTemperature_state("000");
                    }else if(temperatureValue>temperature.getConstant_hight()){
                        sensorState.setTemperature_state("001");
                    }else{
                        sensorState.setTemperature_state("-001");
                    }

                    String sql4 = "update sensor_state set light_state = ?,temperature_state = ? where id = ?";
                    int update = jdbcTemplate.update(sql4, sensorState.getLight_state(), sensorState.getTemperature_state(), sensorState.getId());
                    StringBuffer sb = new StringBuffer();
                    sb.append("id=");
                    sb.append(sensorState.getId());
                    sb.append(" :the state is '");
                    sb.append(update);
                    sb.append("'");
                    logger.info(sb.toString());
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @PostConstruct
    public void startListening() {
        try {
            logger.info("starting");
            this.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
