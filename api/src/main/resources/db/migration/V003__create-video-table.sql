create table video (
    id int auto_increment primary key,
    title varchar(255),
    content longtext,
    channel_id int,
    description longtext,
    like_amount int,
    dislike_amount int,
    view_amount int,
    comment_amount int,
    uploaded_at datetime,
    is_public tinyint,
    is_deleted tinyint,
    deleted_at datetime,
    foreign key (channel_id) references channel(id) on delete cascade
);
