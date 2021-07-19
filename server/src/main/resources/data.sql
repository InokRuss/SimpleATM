drop table if exists client_account_list cascade;
drop table if exists client_list cascade;
drop table if exists card_list;

create table client_list (
  id        int auto_increment primary key,
  firstName varchar(255),
  lastName  varchar(255));

create table client_account_list (
  id         int auto_increment primary key,
  client_id  int not null,
  accountNum varchar(25),
  isoCode    varchar(3),
  balance    int default 0,
  foreign key (client_id) references client_list(id));

create table card_list (
  id         int auto_increment  primary key,
  account_id int not null,
  pinCode    varchar(3),
  cardNum    varchar(16),
  expireDate date,
  cvcCode    int,  
  foreign key (account_id) references client_account_list(id));

insert into client_list (firstName, lastName) values
  ('Client', 'One'),
  ('Client', 'Two'),
  ('Client', 'Three');

insert into client_account_list (accountNum, isoCode, balance, client_id) values
  ('40762810000000000010', 'RUR', 1000, 1),
  ('40762810000000000020', 'RUR', 2000, 2),
  ('40762810000000000030', 'RUR', 3000, 3),
  ('40762840000000000040', 'USD', 10,   3);

insert into card_list (pinCode, cardNum, expireDate, cvcCode, account_id) values
  ('1111', '40762810111111111111', '2023-01-01', 111, 1),
  ('2222', '40762810222222222222', '2023-04-02', 222, 2),
  ('3333', '40762810333333333333', '2023-03-03', 333, 3),
  ('4444', '40762840444444444444', '2023-04-04', 444, 4);