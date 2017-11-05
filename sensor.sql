CREATE DATABASE IF NOT EXISTS sensor DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

use sensor;

--drop table if exists sensor_state;

CREATE TABLE IF NOT EXISTS sensor_state(
	id BIGINT(11)  AUTO_INCREMENT  PRIMARY KEY COMMENT '唯一id',
	magnetic_steel VARCHAR(5) COMMENT '磁钢',
	train VARCHAR(5) COMMENT '火车',
	brush VARCHAR(5) COMMENT '雨刷',
	brush_reset VARCHAR(5) COMMENT  '雨刷复位',
	raindrop VARCHAR(5) COMMENT '雨滴',
	temperature  DOUBLE(5,2) COMMENT '温度',
	temperature_state VARCHAR(5) COMMENT '温度状态',
	light DOUBLE(5,2) COMMENT  '光照',
	light_state VARCHAR(5) COMMENT '光照状态',
	update_time TIMESTAMP  COMMENT '更新时间' DEFAULT CURRENT_TIMESTAMP
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '传感器数据表';


desc sensor_state;


INSERT INTO sensor_state(magnetic_steel,train,brush,brush_reset,raindrop,temperature,light)
		VALUES ('001','000','000','001','000',37.33,37.22);
INSERT INTO sensor_state(magnetic_steel,train,brush,brush_reset,raindrop,temperature,light)
		VALUES ('000','000','001','001','001',-15.00,19);
INSERT INTO sensor_state(magnetic_steel,train,brush,brush_reset,raindrop,temperature,light)
		VALUES ('001','000','000','001','000',60.00,60.00);
INSERT INTO sensor_state(magnetic_steel,train,brush,brush_reset,raindrop,temperature,light)
		VALUES ('000','000','000','000','000',-30.00,67.22);
INSERT INTO sensor_state(magnetic_steel,train,brush,brush_reset,raindrop,temperature,light)
		VALUES ('001','000','000','001','000',22.33,77.22);
INSERT INTO sensor_state(magnetic_steel,train,brush,brush_reset,raindrop,temperature,light)
		VALUES ('001','000','000','001','000',37.33,37.22);
INSERT INTO sensor_state(magnetic_steel,train,brush,brush_reset,raindrop,temperature,light)
		VALUES ('001','000','000','001','000',37.33,37.22);
INSERT INTO sensor_state(magnetic_steel,train,brush,brush_reset,raindrop,temperature,light)
		VALUES ('001','000','000','001','000',37.33,37.22);
		
		
--drop table if exists sensor_constant;

CREATE TABLE IF NOT EXISTS sensor_constant(
	id BIGINT(11)  AUTO_INCREMENT  PRIMARY KEY COMMENT '唯一id',
	constant_type VARCHAR(5) COMMENT '常量类型',
	constant_low DOUBLE(5,2) COMMENT '常量低阈值',
	constant_hight DOUBLE(5,2) COMMENT '常量高阈值'
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT '传感器常量表';


INSERT INTO sensor_constant(constant_type,constant_low,constant_hight)
		VALUES ('l',20.00,50.00);
INSERT INTO sensor_constant(constant_type,constant_low,constant_hight)
		VALUES ('t',-10.00,50.00);