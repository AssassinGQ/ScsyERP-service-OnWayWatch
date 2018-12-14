drop table if exists t_truck_log;

create table t_truck_log (
  id            BIGINT(20)  NOT NULL AUTO_INCREMENT   COMMENT 'ID',
  corporation   BIGINT(20)  NOT NULL                    COMMENT '所属承运方ID',
  #   create_time DATETIME   NOT NULL DEFAULT now()  COMMENT '创建时间',
  #   update_time DATETIME   NOT NULL DEFAULT now()  COMMENT '最后修改时间',
  create_time   DATETIME    NOT NULL DEFAULT now()        COMMENT '创建时间',
  update_time   DATETIME    NOT NULL DEFAULT now()        COMMENT '最后修改时间',
  delete_time   DATETIME                                    COMMENT '删除时间',
  is_deleted    BOOLEAN     NOT NULL DEFAULT false        COMMENT '数据是否已经删除',
  out_storage_form    BIGINT(20)  NOT NULL                 COMMENT '对应出库单',
  truck               BIGINT(20)  NOT NULL                 COMMENT '对应车辆',
  time                DATETIME    NOT NULL DEFAULT now()  COMMENT '日志时间',
  distance            DOUBLE(5,2) NOT NULL                 COMMENT '行驶里程数（公里）',
  gpsx                DOUBLE(6,2) NOT NULL                 COMMENT '经度',
  gpsy                DOUBLE(6,2) NOT NULL                 COMMENT '纬度',
  speed               DOUBLE(5,2) NOT NULL                 COMMENT '时速',
  fuel_vol            DOUBLE(5,2) NOT NULL                 COMMENT '剩余油量',
  left_tire_pressure  DOUBLE(5,2) NOT NULL                 COMMENT '左轮胎压',
  right_tire_pressure DOUBLE(5,2) NOT NULL                 COMMENT '右轮胎压',
  left_tire_temp      DOUBLE(5,2) NOT NULL                 COMMENT '左轮胎温',
  right_tire_temp     DOUBLE(5,2) NOT NULL                 COMMENT '右轮胎温',
  posture             TEXT                                  COMMENT '姿态',
  has_warn            BOOLEAN     NOT NULL DEFAULT false COMMENT '是否有异常',
  warn                BIGINT(20)                            COMMENT '对应异常',
  PRIMARY KEY (id)
);

alter table t_truck_log comment '行车日志信息表';

## 权限的初始化数据
insert into t_truck_log(corporation, out_storage_form, truck, time, distance, gpsx, gpsy, speed, fuel_vol, left_tire_pressure, right_tire_pressure, left_tire_temp, right_tire_temp, has_warn)
        values (1, 1, 1, now(), 100, 1.1, 2.2, 3.3, 101, 102, 103, 104, 105, false);