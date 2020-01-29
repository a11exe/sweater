delete from user_role;
delete from usr;

insert into usr(id, active, password, username) values
  (1, true, '$2a$08$QrkocL9ddFPuCfS5J4Dww.fprefzn.wkaduz/Q1/XiC0f1JjrJ.ta', 'dru'),
  (2, true, '$2a$08$QrkocL9ddFPuCfS5J4Dww.fprefzn.wkaduz/Q1/XiC0f1JjrJ.ta', 'mike');

insert into user_role(user_id, roles) values
  (1, 'USER'), (1, 'ADMIN'),
  (2, 'USER');