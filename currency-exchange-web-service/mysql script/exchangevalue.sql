drop database if exists `webservices`;

create database if not exists `webservices`;

use webservices;

drop table if exists `exchange_value`;

create table if not exists `exchange_value`

(
`id` bigint(15) not null auto_increment,

`currency_from` varchar(45) default null,

`currency_to` varchar(45) default null,

`conversion_multiple` float default null,

`port` int(10) default null,

primary key(`id`)

)engine=InnoDB auto_increment=3 default charset=latin1;

lock tables `exchange_value` write;

insert into `exchange_value` values

(1,'INR','USD',0.015,0),
(2,'USD','INR',68.14,0),
(3,'YEN','INR',0.62,0),
(4,'INR','YEN',1.61,0);

unlock tables;