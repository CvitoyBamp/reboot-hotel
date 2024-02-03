CREATE TABLE PUBLIC.PHOTO_STORE (
    ID SERIAL PRIMARY KEY, -- первичный ключ шаблона
    PHOTO_NAME VARCHAR(64) NOT NULL UNIQUE, -- имя фото
    PHOTO TEXT NOT NULL UNIQUE, -- фото в бинарном формате
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- будет меняться при апдейтах за счет триггеров
);

--Автогенератор инсертов смотри в HotelApplication.java
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('0','slider-1.jpg','/images/slider-1.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('2','slider-2.jpg','/images/slider-2.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('5','slider-3.jpg','/images/slider-3.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('6','slider-4.jpg','/images/slider-4.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('7','slider-5.jpg','/images/slider-5.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('8','slider-6.jpg','/images/slider-6.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('11','slider-7.jpg','/images/slider-7.jpg');

drop table public.photo_store cascade;

