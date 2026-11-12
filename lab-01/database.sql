Лабораторная работа №1
База данных магазина косметики

create table brands (
    id integer generated always as identity primary key,
    name varchar(100) not null unique,
    country varchar(100),
    description text
);

create table products (
    id integer generated always as identity primary key,
    name varchar(150) not null,
    category varchar(100) not null,
    price numeric(10, 2) not null check (price >= 0),
    quantity integer not null default 0 check (quantity >= 0),
    brand_id integer not null,
    constraint fk_products_brand foreign key (brand_id)
        references brands(id)
);

insert into brands (name, country, description)
values
    ('CeraVe', 'США', 'Косметика для ухода за кожей'),
    ('Maybelline', 'США', 'Декоративная косметика'),
    ('The Ordinary', 'Канада', 'Средства для ухода за кожей'),
    ('L''Oreal Paris', 'Франция', 'Косметика и средства для волос');

insert into products (name, category, price, quantity, brand_id)
values
    ('Увлажняющий крем', 'Уход за лицом', 1250.00, 25, (select id from brands where name = 'CeraVe')),
    ('Очищающий гель', 'Уход за лицом', 980.00, 18, (select id from brands where name = 'CeraVe')),
    ('Тушь для ресниц', 'Макияж', 890.00, 40, (select id from brands where name = 'Maybelline')),
    ('Тональный крем', 'Макияж', 1100.00, 15, (select id from brands where name = 'Maybelline')),
    ('Сыворотка с ниацинамидом', 'Уход за лицом', 1490.00, 20, (select id from brands where name = 'The Ordinary')),
    ('Гиалуроновая сыворотка', 'Уход за лицом', 1590.00, 12, (select id from brands where name = 'The Ordinary')),
    ('Шампунь', 'Уход за волосами', 650.00, 30, (select id from brands where name = 'L''Oreal Paris'));
