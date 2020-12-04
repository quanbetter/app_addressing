-- app meta
create table app_meta
(
    id         bigint auto_increment comment 'id'
        primary key,
    app_name   varchar(255)                       not null comment '提供接口的服务id',
    app_desc   varchar(255)                       not null comment 'app信息简述',
    app_secret varchar(255)                       not null comment '签名',
    updated_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    created_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    constraint appId__index
        unique (app_name)
)
    comment 'app元数据表';

create index index_app_id
    on app_meta (id);



--  app-instance app对应的实例表
create table app_instance
(
    id         bigint auto_increment comment 'id'
        primary key,
    app_id     bigint                             not null comment '提供接口的服务id',
    address    varchar(255)                       not null comment 'app的地址',
    port       int      default 80                not null comment 'app的port',
    updated_at datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    created_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    constraint fk_app_instance_app_meta
        foreign key (app_id) references app_meta (id)
)
    comment 'app实例表';


-- 接口信息表
create table interface_instance
(
    id             bigint auto_increment comment 'id'
        primary key,
    app_id         bigint                             not null comment '提供接口的服务名',
    interface_name varchar(255)                       not null comment '接口名',
    path           varchar(255)                       not null comment '接口对应的路径',
    priority       bigint   default 9999              not null comment '优先级',
    updated_at     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    created_at     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    constraint fk_interface_instance_app_meta
        foreign key (app_id) references app_meta (id)
)
    comment '接口实例表';

create index appId__index
    on interface_instance (app_id);






-- 插入数据例子
insert into interface_instance (interface_name, address, port, app_id, is_default, priority)
values ('login','127.0.0.1',9001,'ci-better-login',0,2);


-- 查询所有数据例子
 select i.*, concat(a.app_key, "&amp;", i.interface_name) as key_name from app_id_meta as a
join interface_instance as i on a.app_id = i.app_id;

select ai.*,am.app_secret from app_instance as ai join app_id_meta as am on am.app_id = ai.app_id  where ai.is_default;
-- 删除数据
delete from interface_instance where app_id = 'ci-better-login' and interface_name = 'login' and address ='127.0.0.1';

-- 更新数据

-- 主键和外键的字符编码需要一致，引用类型需要一致，被引用的列需要建立索引
-- 外键用于表与表之间的约束
-- 建立外键：
alter table interface_instance
	add constraint interface_instance___fk
		foreign key (app_id) references app_id_meta (app_id);
-- MySQL 外键约束（FOREIGN KEY）是表的一个特殊字段，经常与主键约束一起使用。对于两个具有关联关系的表而言，
-- 相关联字段中主键所在的表就是主表（父表），外键所在的表就是从表（子表）。