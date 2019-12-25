create table if not exists `users`
(
  `userId`             char(64) unique not null,
  `name`               varchar(255) unique not null,
  primary key (`userId`)
);

insert into users (userId, name) values ("1f91c006-97cd-4df5-9048-67ddd634af22", "Hoge");
insert into users (userId, name) values ("c685326c-4dd2-4be6-935c-17aa548f9192", "Fuga");
insert into users (userId, name) values ("92022a86-790a-40af-a141-9998665fc1ed", "Piyo");
