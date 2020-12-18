drop table if exists `test`;
create table `test` (
 `id` char(8) not null default '' comment 'id',
 `name` varchar(50) comment 'name',
 primary key (`id`)
) engine=innodb default charset=utf8mb4;

insert into `test` (`id`, `name`) values(1, 'tester');
insert into `test` (`id`, `name`) values(2, 'tester2');