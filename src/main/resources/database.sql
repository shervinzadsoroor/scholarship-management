create table scholarship
(
    id           int auto_increment
        primary key,
    status       varchar(24) not null,
    name         varchar(24) not null,
    family       varchar(24) not null,
    nationalCode varchar(10) not null,
    lastUni      varchar(24) not null,
    lastDegree   varchar(24) not null,
    lastField    varchar(24) not null,
    lastScore    float       not null,
    applyUni     varchar(24) not null,
    applyDegree  varchar(24) not null,
    applyField   varchar(24) not null,
    applyDate    varchar(24) not null
)
    engine = InnoDB;

create table user
(
    id       int auto_increment
        primary key,
    username varchar(24) not null,
    password varchar(24) not null,
    role     varchar(10) not null,
    constraint role_UNIQUE
        unique (role),
    constraint user_username_uindex
        unique (username)
)
    engine = InnoDB;

create table log
(
    idlog          int auto_increment
        primary key,
    log            varchar(100) not null,
    scholarship_id int          null,
    user_role      varchar(10)  null,
    date           varchar(45)  null,
    constraint fk_user_role
        foreign key (user_role) references user (role)
)
    engine = InnoDB;

create index fk_scholarship_id_idx
    on log (scholarship_id);

create index fk_user_role_idx
    on log (user_role);

