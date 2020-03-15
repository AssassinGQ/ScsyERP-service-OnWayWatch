drop table if exists t_warn;

create table t_warn (
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
  truck_log           BIGINT(20)  NOT NULL                 COMMENT '对应行车日志',
  time                DATETIME    NOT NULL DEFAULT now()  COMMENT '日志时间',
  gpsx                DOUBLE(6,2) NOT NULL                 COMMENT '经度',
  gpsy                DOUBLE(6,2) NOT NULL                 COMMENT '纬度',
  warn_type           VARCHAR(15) NOT NULL                 COMMENT '异常类型',
  status              VARCHAR(20) NOT NULL                 COMMENT '异常状态',
  warn_value          TEXT                                  COMMENT '异常参数',
  pictures            TEXT                                  COMMENT '异常图片',
  PRIMARY KEY (id)
);

alter table t_warn comment '异常信息表';

## 权限的初始化数据
insert into t_warn(corporation, out_storage_form, truck, truck_log, gpsx, gpsy, warn_type, status)
        values (1, 1, 1, 1, 1.1, 2.2, "abnormal_stop", "created");