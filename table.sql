create table course (
    id bigint not null,
    course_name varchar(255) not null,
    created_date timestamp,
    is_deleted boolean,
    last_updated_date timestamp,
    primary key (id)
);
create table employee (
    employee_type varchar(31) not null,
    id bigint not null,
    name varchar(255) not null,
    primary key (id)
);
create table full_time_employee (
    salary decimal(19, 2),
    id bigint not null,
    primary key (id),
    foreign key (id) references employee(id)
);
create table part_time_employee (
    hourly_wage decimal(19, 2),
    id bigint not null,
    primary key (id),
    foreign key (id) references employee(id)
);
create table passport (
    id bigint not null,
    passport_id varchar(255) not null unique,
    primary key (id)
);
create table person (
    id integer not null,
    birth_date timestamp,
    location varchar(255),
    name varchar(255),
    primary key (id)
);
create table review (
    id bigint not null,
    description varchar(255),
    rating varchar(255) not null,
    course_id bigint not null,
    primary key (id),
    foreign key (course_id) references course(id)
);

create table student (
    id bigint not null,
    address_line varchar(255),
    city varchar(255),
    name varchar(255) not null,
    passport_id bigint,
    primary key (id),
    foreign key (passport_id) references passport(id)
);
create table student_course (
    student_id bigint not null,
    course_id bigint not null,
    foreign key (student_id) references student(id),
    foreign key (course_id) references course(id)
);
