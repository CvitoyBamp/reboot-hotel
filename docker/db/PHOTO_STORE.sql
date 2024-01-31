CREATE TABLE PUBLIC.PHOTO_STORE (
    ID SERIAL PRIMARY KEY, -- первичный ключ шаблона
    PHOTO_NAME TEXT NOT NULL UNIQUE, -- имя фото
    PHOTO BYTEA NOT NULL UNIQUE, -- фото в бинарном формате
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- будет меняться при апдейтах за счет триггеров
);

--Автогенератор инсертов смотри в HotelApplication.java
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('0','slider-3.jpg','[B@376b4233');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('1','img_1.jpg','[B@b3d7190');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('2','slider-2.jpg','[B@6dde5c8c');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('3','img_2.jpg','[B@68ceda24');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('4','img_3.jpg','[B@587c290d');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('5','slider-1.jpg','[B@1da51a35');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('6','slider-5.jpg','[B@768b970c');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('7','slider-4.jpg','[B@134593bf');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('8','slider-6.jpg','[B@7dc222ae');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('9','img_4.jpg','[B@b9afc07');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('10','img_5.jpg','[B@15bfd87');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('11','slider-7.jpg','[B@44f75083');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('12','round.png','[B@1ad282e0');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('13','food-1.jpg','[B@2473b9ce');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('14','person_4.jpg','[B@71d15f18');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('15','person_3.jpg','[B@2bea5ab4');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('16','image_5.jpg','[B@f4168b8');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('17','person_2.jpg','[B@130f889');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('18','image_7.jpg','[B@40f08448');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('19','image_6.jpg','[B@7181ae3f');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('20','person_1.jpg','[B@1442d7b5');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('21','hero_1.jpg','[B@6cc7b4de');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('22','hero_3.jpg','[B@45afc369');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('23','hero_2.jpg','[B@17776a8');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('24','about_feature_image.png','[B@2bbaf4f0');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('25','hero_4.jpg','[B@33cb5951');

drop table public.photo_store cascade;