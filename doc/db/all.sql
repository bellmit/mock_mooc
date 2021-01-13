-- course
drop table if exists `course`;
create table course
(
    id         char(8)     not null default '',
    name       varchar(50) not null,
    summary    varchar(2000),
    time       int                  default 0 comment 'duration|second',
    price      decimal(8, 2)        default 0.00,
    image      varchar(100) comment 'cover',
    level      char(1) comment 'enum|[CourseLevelEnum]:ONE("1","elementary"),TWO("2","intermediate"),THREE("3","advanced")',
    charge     char(1) comment 'enum|[CourseChargeEnum]:CHARGE("C","chargeable"),FREE("F","free")',
    status     char(1) comment 'enum|[CourseStatusEnum]:PUBLISH("P","published"),DRAFT("D","draft")',
    enroll     integer              default 0 comment 'number of enrolled people',
    sort       int,
    created_at datetime(3),
    updated_at datetime(3),
    primary key (id)
) engine = innodb
  default charset = utf8mb4;
insert into course(id, name, summary, time, price, image, level, charge, status, enroll, sort, created_at, updated_at)
values ('00000001', 'course01', 'this is a test course', 7200, 19.9, '', 1, 'C', 'D', 100, 0, now(), now());

-- course content file
drop table if exists `courseContentFile`;
create table `courseContentFile` (
                                       `id` char(8) not null default '' comment 'id',
                                       `course_id` char(8) not null comment 'course id',
                                       `url` varchar(100) comment 'full path',
                                       `name` varchar(100),
                                       `size` int comment 'byte',
                                       primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='file in course content';

-- chapter
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
values ('000000', '00000001', 'chapter00');
insert into `chapter` (`id`, `course_id`, `name`)
values ('00000001', '00000001', 'chapter01');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000002', '000002', 'chapter02');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000003', '000003', 'chapter03');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000004', '000004', 'chapter04');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000005', '000005', 'chapter05');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000006', '000006', 'chapter06');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000007', '000007', 'chapter07');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000008', '000008', 'chapter08');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000009', '000009', 'chapter09');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000010', '000010', 'chapter10');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000011', '000011', 'chapter11');

-- section
drop table if exists `section`;
create table `section`
(
    `id`         char(8)     not null default '',
    `title`      varchar(50) not null,
    `course_id`  char(8) comment 'course.id',
    `chapter_id` char(8) comment 'chapter.id',
    `video`      varchar(200),
    `time`       int comment 'video duration|second',
    `charge`     char(1) comment 'CHARGE("C","chargeable"),FREE("F","free")',
    `sort`       int,
    `created_at` datetime(3),
    `updated_at` datetime(3),
    primary key (`id`)
) engine = InnoDB
  default charset = utf8mb4;
insert into `section` (id, title, course_id, chapter_id, video, time, charge, sort, created_at, updated_at)
values ('00000001', 'section01', '00000001', '00000001', '', 500, 'F', 1, now(), now());

-- category
drop table if exists `category`;
create table `category` (
                            `id` char(8) not null default '' comment 'id',
                            `parent` char(8) not null default '' comment 'parent id',
                            `name` varchar(50) not null,
                            `sort` int,
                            primary key (`id`)
) engine=innodb default charset=utf8mb4;

insert into `category` (id, parent, name, sort) values ('00000100', '00000000', 'frontend', 100);
insert into `category` (id, parent, name, sort) values ('00000101', '00000100', 'html/css', 101);
insert into `category` (id, parent, name, sort) values ('00000102', '00000100', 'javascript', 102);
insert into `category` (id, parent, name, sort) values ('00000103', '00000100', 'vue.js', 103);
insert into `category` (id, parent, name, sort) values ('00000104', '00000100', 'react.js', 104);
insert into `category` (id, parent, name, sort) values ('00000105', '00000100', 'angular', 105);
insert into `category` (id, parent, name, sort) values ('00000106', '00000100', 'node.js', 106);
insert into `category` (id, parent, name, sort) values ('00000107', '00000100', 'jquery', 107);
insert into `category` (id, parent, name, sort) values ('00000108', '00000100', 'applet', 108);
insert into `category` (id, parent, name, sort) values ('00000200', '00000000', 'backend', 200);
insert into `category` (id, parent, name, sort) values ('00000201', '00000200', 'java', 201);
insert into `category` (id, parent, name, sort) values ('00000202', '00000200', 'springboot', 202);
insert into `category` (id, parent, name, sort) values ('00000203', '00000200', 'spring cloud', 203);
insert into `category` (id, parent, name, sort) values ('00000204', '00000200', 'ssm', 204);
insert into `category` (id, parent, name, sort) values ('00000205', '00000200', 'python', 205);
insert into `category` (id, parent, name, sort) values ('00000206', '00000200', 'crawler', 206);
insert into `category` (id, parent, name, sort) values ('00000300', '00000000', 'mobile development', 300);
insert into `category` (id, parent, name, sort) values ('00000301', '00000300', 'android', 301);
insert into `category` (id, parent, name, sort) values ('00000302', '00000300', 'ios', 302);
insert into `category` (id, parent, name, sort) values ('00000303', '00000300', 'react native', 303);
insert into `category` (id, parent, name, sort) values ('00000304', '00000300', 'ionic', 304);
insert into `category` (id, parent, name, sort) values ('00000400', '00000000', 'advanced technologies', 400);
insert into `category` (id, parent, name, sort) values ('00000401', '00000400', 'microservices', 401);
insert into `category` (id, parent, name, sort) values ('00000402', '00000400', 'block chain', 402);
insert into `category` (id, parent, name, sort) values ('00000403', '00000400', 'machine learning', 403);
insert into `category` (id, parent, name, sort) values ('00000404', '00000400', 'deep learning', 404);
insert into `category` (id, parent, name, sort) values ('00000405', '00000400', 'data mining', 405);
insert into `category` (id, parent, name, sort) values ('00000500', '00000000', 'cloud computing', 500);
insert into `category` (id, parent, name, sort) values ('00000501', '00000500', 'big data', 501);
insert into `category` (id, parent, name, sort) values ('00000502', '00000500', 'hadoop', 502);
insert into `category` (id, parent, name, sort) values ('00000503', '00000500', 'spark', 503);
insert into `category` (id, parent, name, sort) values ('00000504', '00000500', 'hbase', 504);
insert into `category` (id, parent, name, sort) values ('00000505', '00000500', 'Ali cloud', 505);
insert into `category` (id, parent, name, sort) values ('00000506', '00000500', 'docker', 506);
insert into `category` (id, parent, name, sort) values ('00000507', '00000500', 'kubernetes', 507);
insert into `category` (id, parent, name, sort) values ('00000600', '00000000', 'maintenance', 600);
insert into `category` (id, parent, name, sort) values ('00000604', '00000600', 'linux', 604);
insert into `category` (id, parent, name, sort) values ('00000605', '00000600', 'testing', 605);;
insert into `category` (id, parent, name, sort) values ('00000700', '00000000', 'database', 700);
insert into `category` (id, parent, name, sort) values ('00000701', '00000700', 'mysql', 701);
insert into `category` (id, parent, name, sort) values ('00000702', '00000700', 'redis', 702);
insert into `category` (id, parent, name, sort) values ('00000703', '00000700', 'mongodb', 703);

-- course category
drop table if exists `courseCategory`;
create table `courseCategory` (
                                   `id` char(8) not null default '' comment 'id',
                                   `course_id` char(8) comment 'course.id',
                                   `category_id` char(8) comment 'course.id',
                                   primary key (`id`)
) engine=innodb default charset=utf8mb4;

select*from courseCategory;

-- course content (1-1 relationship with course, separate from course because content
-- will be very long)
drop table if exists `courseContent`;
create table `courseContent` (
                                  `id` char(8) not null default '' comment 'course id',
                                  `content` mediumtext not null,
                                  primary key (`id`)
) engine=innodb default charset=utf8mb4;

-- teacher
drop table if exists `teacher`;
create table `teacher` (
                           `id` char(8) not null default '' comment 'id',
                           `name` varchar(50) not null,
                           `image` varchar(100),
                           `intro` varchar(500),
                           primary key (`id`)
) engine=innodb default charset=utf8mb4;

# add techer_id column to course table
alter table `course` add column (`teacher_id` char(8) comment 'teacher.id');

select * from teacher;

-- file
drop table if exists `file`;
create table `file` (
                        `id` char(8) not null default '',
                        `path` varchar(100) not null comment 'relative path',
                        `name` varchar(100) comment 'file name',
                        `suffix` varchar(10),
                        `size` int comment 'Byte',
                        `use` char(1) comment 'enum|[FileUseEnum]:COURSE("C", "teacher"), TEACHER("T", "course")',
                        `created_at` datetime(3),
                        `updated_at` datetime(3),
                        primary key (`id`),
                        unique key `path_unique` (`path`)
) engine=innodb default charset=utf8mb4;

alter table `file` add column (`shard_index` int comment 'uploaded shards');
alter table `file` add column (`shard_size` int comment 'Byte');
alter table `file` add column (`shard_total` int comment 'total number of shards');
alter table `file` add column (`key` varchar(32));
alter table `file` add unique key key_unique (`key`);

-- test
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

show full columns from section;