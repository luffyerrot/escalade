insert into roles value(1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

insert into users value(1, 'user@gmail.com', '$2a$10$gvV1jvNRrqMLLCDb/NwKIeVngUzM6uAWM3bo6pnSbWiX5RbYuQrOW', 'jeanjean'),
					   (2, 'admin@gmail.com', '$2a$10$arBPccdH6MQYseCLDuxJ7u2BPFdxZSd.kKpjZQO7UA.y34PEoSzjC', 'admin');

insert into user_role value(1, 1), (2, 1), (2, 2);

insert into topos value(1, '2010-12-25', 'plaine entre deux mont', 'plaine des monts grisés', 'alpes', false, 1);
insert into sectors value(1, 12000, 'zone1', 'montagneux', 1, 1);

insert into ways value(1, 'terrain accidenté longant le mont du ciel bleu', 6, 2000, 1, 1),
					  (2, 'piste remontant le pic de l éffroi', 8, 1500, 1, 1),
					  (3, 'chemin longant le lac des monts', 3, 8500, 1, 1);

insert into comments value(1, 'site bien fourni très agréable !', '2020-02-01 21:05:02', 1, 1);