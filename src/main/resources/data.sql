CREATE TABLE PERSON(
     id integer not null,
     name varchar(255) not null,
     location varchar(255) not null,
     birth_date timestamp,
     primary key(id)
);

Insert into PERSON(id, name, location, birth_date)
values(10001, 'sritaj', 'odisha', sysdate());

Insert into PERSON(id, name, location, birth_date)
values(10002, 'anusha', 'karnataka', sysdate());



