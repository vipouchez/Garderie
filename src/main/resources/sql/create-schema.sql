
create table student(
    id  int(6)  AUTO_INCREMENT primary key,
    first_name varchar(30),
    last_name varchar(30),
    father_name varchar(30),
    #address_id int(6), -- TODO
    birthday date,
    image_url varchar(256),
    mother_name varchar(30),
    grand_father_name varchar(30),
    father_cin varchar(30) ,
    father_phone_number varchar(30)
);


create table groups(
    name varchar(30)
);
