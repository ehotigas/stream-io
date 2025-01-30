create table channel_subscriber (
    id int auto_increment primary key,
    subscriber_id int,
    target_channel_id int,
    subscribed_at datetime,
    foreign key (subscriber_id) references channel(id) on delete cascade,
    foreign key (target_channel_id) references channel(id) on delete cascade
);