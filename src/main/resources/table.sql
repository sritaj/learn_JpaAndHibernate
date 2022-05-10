-- TABLES for Showcasing Relationships and JPA-HIBERNATE Examples --
create table course (
    id bigint auto_increment not null,
    course_name varchar(255) not null,
    created_date timestamp,
    is_deleted boolean,
    last_updated_date timestamp,
    primary key (id)
);
create table passport (
    id bigint auto_increment not null,
    passport_id varchar(255) not null unique,
    primary key (id)
);
create table review (
    id bigint auto_increment not null,
    description varchar(255),
    rating varchar(255) not null,
    course_id bigint not null,
    primary key (id),
    foreign key (course_id) references course(id)
);
create table student (
    id bigint auto_increment not null,
    address_line varchar(255),
    city varchar(255),
    name varchar(255) not null,
    passport_id bigint,
    primary key (id),
    foreign key (passport_id) references passport(id)
);
create table student_course (
    student_id bigint auto_increment not null,
    course_id bigint not null,
    foreign key (student_id) references student(id),
    foreign key (course_id) references course(id)
);
-- TABLE for Soft Delete Example --
create table person (
    id integer auto_increment not null,
    birth_date timestamp,
    location varchar(255),
    name varchar(255),
    primary key (id)
);
-- TABLE for Spring JPA Data Example --
create table product(
    id int PRIMARY KEY auto_increment not null,
    name varchar(20),
    description varchar(100),
    price decimal(8, 3)
);
-- TABLE for JPQL and Native Queries Example --
create table author(
    id int PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(20),
    last_name varchar(20),
    books_published int
);
-- TABLE for Custom ID Generation Example --
create table book(
    id bigint PRIMARY KEY,
    title varchar (200),
    author varchar (200),
    publisher varchar (200)
);
create table id_gen(
    gen_name varchar(60) PRIMARY KEY,
    gen_val int(20)
);
-- TABLE for Inheritance Mapping : SINGLE TABLE Example --
create table payment(
    id int PRIMARY KEY,
    payment_mode varchar(2),
    amount decimal(8, 3),
    card_number varchar(20),
    check_number varchar(20)
);
-- TABLES for Inheritance Mapping : TABLE PER CLASS Example --
create table bike(
    id int PRIMARY KEY,
    amount decimal(8, 3),
    bike varchar(20)
);
create table car(
    id int PRIMARY KEY,
    amount decimal(8, 3),
    car varchar(20)
);
-- TABLES for Inheritance Mapping : JOINED Example --
create table employee (
    employee_type varchar(31) not null,
    id bigint auto_increment not null,
    name varchar(255) not null,
    primary key (id)
);
create table full_time_employee (
    salary decimal(19, 2),
    id bigint auto_increment not null,
    primary key (id),
    foreign key (id) references employee(id) ON DELETE CASCADE
);
create table part_time_employee (
    hourly_wage decimal(19, 2),
    id bigint auto_increment not null,
    primary key (id),
    foreign key (id) references employee(id) ON DELETE CASCADE
);
-- TABLE for Component Mapping Example --
create table customer(
    id int not null primary key,
    name varchar(20),
    streetaddress varchar(30),
    city varchar(20),
    state varchar(20),
    zipcode varchar(20),
    country varchar(20)
);
-- TABLE for File Saving and Retrieval Example --
create TABLE image(
    id BIGINT NOT NULL,
    name varchar(100) NOT NULL,
    data BLOB NOT NULL,
    PRIMARY KEY(id)
);
-- TABLE for Stored Procedure Example --
create table actionfigures(
    id int PRIMARY KEY auto_increment not null,
    name varchar(20) not null,
    description varchar(100),
    price decimal(8, 3)
);
-- TABLES for One to Many Mapping Example --
create table client (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar (20) not null
);
create table phone_number(
    id int PRIMARY KEY AUTO_INCREMENT,
    client_id int,
    number varchar(20) not null,
    type varchar(20),
    FOREIGN KEY (client_id) REFERENCES client(id)
);
-- TABLES for Many to Many Mapping Example --
create table programmer(
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(20) not null,
    salary int
);
create table project(
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(20) not null
);
create table (
    programmer_id int,
    project_id int,
    FOREIGN KEY (programmer_id) REFERENCES programmer(id),
    FOREIGN KEY (project_id) REFERENCES project(id)
);
-- TABLES for One to One Mapping Example --
create table driver(
    id int PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(20) not null,
    last_name varchar(20),
    age int
);
create table license(
    id int PRIMARY KEY AUTO_INCREMENT,
    type varchar(20) not null,
    valid_from date,
    valid_to date,
    person_id int,
    FOREIGN KEY (driver_id) REFERENCES driver(id)
);
-- TABLE for Transaction Management Example --
create table bank_account (
    accno int PRIMARY KEY AUTO_INCREMENT,
    lastname varchar(25),
    firstname varchar(25) not null,
    bal int
);
-- TABLE for Composite Key ID Example --
CREATE TABLE staff (
    id int NOT NULL,
    email varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id, email)
);
-- TABLE for Composite Key ID Embedded Approach Example --
CREATE TABLE pilot (
    id int NOT NULL,
    email varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id, email)
);
-- TABLES for Mini Project Example --
create table patient(
    id int PRIMARY KEY AUTO_INCREMENT,
    first_name varchar (20) not null,
    last_name varchar(30),
    phone varchar(30),
    provider_name varchar(30),
    copay decimal(10, 5)
);
create table doctor(
    id int PRIMARY KEY AUTO_INCREMENT,
    first_name varchar (20) not null,
    last_name varchar(30),
    speciality varchar(30)
);
create table patients_doctors(
    patient_id int,
    doctor_id int,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE
);
create table appointment(
    id int PRIMARY KEY AUTO_INCREMENT,
    patient_id int,
    doctor_id int,
    appointment_time datetime,
    started TINYINT(1),
    ended TINYINT(1),
    reason varchar(200),
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE
);
-- TABLES for REST APIs Example --
create table user_account(
    id int AUTO_INCREMENT PRIMARY KEY,
    user_name varchar(20) not null,
    password varchar(20),
    age int,
    email varchar(40),
    gender varchar(10),
    phone_number varchar(10),
    city varchar(20),
    country varchar(20)
);
create table interest(
    id int AUTO_INCREMENT PRIMARY KEY,
    likes varchar(100),
    dislikes varchar(100),
    hobbies varchar(100),
    profile_url varchar(60),
    about varchar(200),
    user_id int,
    FOREIGN KEY (user_id) REFERENCES user_account(id) on delete cascade
);
-- TABLES for 2nd Level Caching using EhCache Example --
create table foo(
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(20) not null
);
create table bar(
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(20) not null
);