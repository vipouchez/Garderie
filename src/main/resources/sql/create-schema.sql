create table entity(
    id  int(6)  AUTO_INCREMENT primary key,
    first_name varchar(30),
    last_name varchar(30),
    father_name varchar(30),
    address_id int(6),
    birthday date,
    imageUrl varchar(256),
);

create table student(
    id  int(6)  AUTO_INCREMENT primary key,
    mother_name varchar(30),
    grand_father_name varchar(30),
    father_cin varchar(30) ,
    father_phone_number varchar(30),
    entity_id int(6),
    foreign key(entity_id) references student(id)
);
