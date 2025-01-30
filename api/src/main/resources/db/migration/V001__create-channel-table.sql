create table channel (
    id int auto_increment primary key,
    name varchar(255),
    username varchar(255) unique,
    description text null,
    password text,
    subscribers_amount int,
    views_amount int,
    videos_amount int,
    created_at datetime,
    is_active tinyint
);