package com.keysun.sensor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keysun.sensor.po.SensorState;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by hong on 2017/11/5.
 */
@Controller
public class SensorController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(path="/getSensorState", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getSensorState(){
        String sql = "select * from sensor_state where temperature_state is not null and light_state is not null order by update_time desc limit 1";
        List<SensorState> sensorStates = jdbcTemplate.query(sql, new RowMapper<SensorState>() {
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
        JSONArray sensorArray = JSONArray.parseArray(JSON.toJSONString(sensorStates));
        JSONObject result = new JSONObject();
        result.put("CODE","200");
        result.put("DATA",sensorArray);
        return result;
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
