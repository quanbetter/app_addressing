-- interface_instance
create table interface_instance
(
    id             bigint auto_increment comment 'id'
        primary key,
    interface_name varchar(255)                       not null comment '接口名',
    address        varchar(128)                       not null comment '接口所在实例的ip',
    port           int                                not null comment '实例端口',
    app_id         varchar(255)                       not null comment '提供接口的服务id',
    is_default      bit                                not null comment '是否是默认提供者',
    updated_at     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    created_at     datetime default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment '接口实例表';

create index index_app_id_interface_name
    on interface_instance (interface_name, app_id);

-- appId-appKey
create table app_id_meta
(
    id         bigint auto_increment comment 'id'
        primary key,
    app_id     varchar(255)                       not null comment '提供接口的服务id',
    app_key    bigint                             not null comment 'app_id对应的唯一app_key,用于外部可见',
    app_msg    varchar(255)                       not null comment 'app信息简述',
    updated_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    created_at datetime default CURRENT_TIMESTAMP not null comment '创建时间'
)
    comment 'appId和appKey对应关系表';

create index index_app_id_key
    on app_id_meta (app_key);

insert into interface_instance (interface_name, address, port, app_id, is_default, priority)
values ('login','127.0.0.1',9001,'ci-better-login',0,2);