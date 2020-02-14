create table `saga_logger` (
    id bigint primary key ,
    version int(11) DEFAULT NULL ,
    create_at datetime NOT NULL,
    update_at datetime NOT NULL,
    transaction_id varchar(50) not null ,
    class_name varchar(30) not null ,
    data_json JSON ,
    operation varchar(2),

    INDEX `UK_TRANSACTION_ID_CLASS_NAME` (`transaction_id`, `class_name`)
);