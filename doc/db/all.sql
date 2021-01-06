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
values ('000000', '000000', 'chapter00');
insert into `chapter` (`id`, `course_id`, `name`)
values ('000001', '000001', 'chapter01');
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
values ('00000001', 'section01', '00000001', '00000000', '', 500, 'F', 1, now(), now());

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

show full columns from section;