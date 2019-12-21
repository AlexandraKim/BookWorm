alter table user add column phone varchar(15);
alter table user add column email varchar(128);

update user set phone = '12356777' where firstname = 'Ryan';
update user set email = 'ryan@gmail.com' where firstname = 'Ryan';

alter table book add column Quantity int(11);

update book set Quantity = 2;
