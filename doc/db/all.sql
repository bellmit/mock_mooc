drop table if exists `chapter`;
create table `chapter`
(
    `id`        char(8) not null,
    `course_id` char(8),
    `name`      varchar(50),
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;

insert into `chapter` (`id`, `course_id`, `name`) values ('000000', '000000', 'chapter00');
insert into `chapter` (`id`, `course_id`, `name`) values ('000001', '000001', 'chapter01');
insert into `chapter` (`id`, `course_id`, `name`) values ('000002', '000002', 'chapter02');
insert into `chapter` (`id`, `course_id`, `name`) values ('000003', '000003', 'chapter03');
insert into `chapter` (`id`, `course_id`, `name`) values ('000004', '000004', 'chapter04');
insert into `chapter` (`id`, `course_id`, `name`) values ('000005', '000005', 'chapter05');
insert into `chapter` (`id`, `course_id`, `name`) values ('000006', '000006', 'chapter06');
insert into `chapter` (`id`, `course_id`, `name`) values ('000007', '000007', 'chapter07');
insert into `chapter` (`id`, `course_id`, `name`) values ('000008', '000008', 'chapter08');
insert into `chapter` (`id`, `course_id`, `name`) values ('000009', '000009', 'chapter09');
insert into `chapter` (`id`, `course_id`, `name`) values ('000010', '000010', 'chapter10');
insert into `chapter` (`id`, `course_id`, `name`) values ('000011', '000011', 'chapter11');

#test
drop table if exists `test`;
create table `test`
(
    `id`   char(8) not null default '' comment 'id',
    `name` varchar(50) comment 'name',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;

insert into `test` (`id`, `name`)
values (1, 'tester');
insert into `test` (`id`, `name`)
values (2, 'tester2');