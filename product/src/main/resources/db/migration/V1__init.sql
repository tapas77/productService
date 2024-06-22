create table category (id bigint not null auto_increment, created_at datetime(6),
                       is_deleted bit not null, updated_at datetime(6), name varchar(255),
                       primary key (id)) engine=InnoDB;

create table product (id bigint not null auto_increment,
                      created_at datetime(6), is_deleted bit not null,
                      updated_at datetime(6), description varchar(255),
                      image varchar(255), name varchar(255), price integer not null,
                      category_id bigint, primary key (id)) engine=InnoDB;
alter table product add constraint FK1mtsbur82frn64de7balymq9s foreign key (category_id) references category (id);