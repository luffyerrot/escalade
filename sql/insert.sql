  
insert into person.roles value(1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

insert into person.users value(1, 'user@gmail.com', '$2a$10$rBH/auEBl4jC2PfdFaodJunzYlyBVsMWc56Q5VtJ9Id7v9/BJXsjq', 'user'),
					   (2, 'admin@gmail.com', '$2a$10$arBPccdH6MQYseCLDuxJ7u2BPFdxZSd.kKpjZQO7UA.y34PEoSzjC', 'admin');

insert into person.user_role value(1, 1), (2, 1), (2, 2);

insert into person.topos value(1, '2020-10-20 06:12:52', 'description', 'name', false, 'place', true, false, false, 1);
insert into person.topos value(2, '2020-10-20 06:13:52', 'description1', 'name1', false, 'place1', false, false, false, 1);
insert into person.topos value(3, '2020-10-20 06:14:52', 'description2', 'name2', true, 'place2', true, false, false, 1);
insert into person.topos value(4, '2020-10-20 06:15:52', 'description3', 'name3', true, 'place3', false, false, false, 1);
insert into person.topos value(5, '2020-10-20 06:16:52', 'description4', 'name4', false, 'place4', true, false, false, 1);
insert into person.topos value(6, '2020-10-20 11:25:08', 'descriptionadmin', 'nameadmin', true, 'placeadmin', true, false, false, 2);
insert into person.topos value(7, '2020-10-20 11:40:01', 'descriptionadmin1', 'nameadmin1', false, 'placeadmin1', true, false, false, 2);
insert into person.topos value(8, '2020-10-20 11:40:01', 'description', 'name', true, 'place', true, false, false, 2);
insert into person.sectors value(1, 12000, 'name', 'type', 1, 1);

insert into person.ways value(1, 'description 1', 6, 2000, 1, 1),
					  (2, 'description 2', 8, 1500, 1, 1),
					  (3, 'description 3', 3, 8500, 1, 1);

insert into person.comments value(1, 'comment', '2020-02-08 08:36:21', 1, 1);