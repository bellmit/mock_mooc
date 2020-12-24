drop table if exists `chapter`;
create table `chapter`
(
    `id`        char(8) not null,
    `course_id` char(8),
    `name`      varchar(50),
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4;

insert into `chapter` (`id`, `course_id`, `name`)
values ('000000', '000000', 'chapter1');
insert into `chapter` (`id`, `course_id`, `name`)
values ('111111', '111111', 'chapter2
');

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