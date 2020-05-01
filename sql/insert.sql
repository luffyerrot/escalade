insert into person.roles value(1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

insert into person.users value(1, 'user@gmail.com', '$2a$10$rBH/auEBl4jC2PfdFaodJunzYlyBVsMWc56Q5VtJ9Id7v9/BJXsjq', 'user'),
					   (2, 'admin@gmail.com', '$2a$10$arBPccdH6MQYseCLDuxJ7u2BPFdxZSd.kKpjZQO7UA.y34PEoSzjC', 'admin');

insert into person.user_role value(1, 1), (2, 1), (2, 2);

insert into person.topos value(1, '2020-10-20 06:12:52', 'description', 'name', false, 'place', false, false, false, 1);
insert into person.sectors value(1, 12000, 'name', 'type', 1, 1);

insert into person.ways value(1, 'description 1', 6, 2000, 1, 1),
					  (2, 'description 2', 8, 1500, 1, 1),
					  (3, 'description 3', 3, 8500, 1, 1);

insert into person.comments value(1, 'comment', '2020-02-08 08:36:21', 1, 1);