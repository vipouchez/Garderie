create table address (
    id  int(6)  AUTO_INCREMENT primary key,
    postal_code int(6),
    road_number varchar(30),
    road_name varchar(30),
    city varchar(30)
);






create table groups(
    name varchar(30) primary key
);

create table student(
    id  int(6)  AUTO_INCREMENT primary key,
    first_name varchar(30),
    last_name varchar(30),
    father_name varchar(30),
    birthday date,
    image_url varchar(256),
    mother_name varchar(30),
    grand_father_name varchar(30),
    father_cin varchar(30) ,
    father_phone_number varchar(30),
    address_id int(6),
    group_id varchar(30),
    foreign key(group_id) references groups(name),
    foreign key(address_id) references address(id)
);

create table employee(
    id  int(6)  AUTO_INCREMENT primary key,
    first_name varchar(30),
    last_name varchar(30),
    father_name varchar(30),
    birthday date,
    image_url varchar(256),
    cin_number varchar(30) ,
    phone_number varchar(30),
    address_id int(6),
    foreign key(address_id) references address(id)
);



create table activity(
    id  int(6)  AUTO_INCREMENT primary key,
    label varchar(30),
    employee_id int(6),
    group_id int(6),
    foreign key(group_id) references groups(name)
  );
