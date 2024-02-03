CREATE TABLE PUBLIC.ROOM_TYPE (
    ID SERIAL PRIMARY KEY, -- первичный ключ шаблона
    ROOM_NAME VARCHAR(64) NOT NULL, -- название номера (Single Room, Family Room и пр.)
    DESCRIPTION TEXT NOT NULL, -- описание номера
    MAX_ADULTS SMALLINT NOT NULL,
    MAX_CHILDREN SMALLINT NOT NULL,
    ADDS JSONB, -- доп. удобства в номере {"tv": true, "wifi": true, "fridge": true, "conditioner": true}
    PHOTO_SRC TEXT NOT NULL, -- ссылка на фото комнаты
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP -- будет меняться при апдейтах за счет триггеров
);

-- drop table PUBLIC.ROOM_TYPE cascade;

insert into public.room_type values ('1', 'Одноместный номер', 'Одноместный номер для одного с максимальным комфортом','1','0','{}','/images/img_1.jpg');
insert into public.room_type values ('2', 'Семейный номер', 'Семейный номер для родителей с детьми','3','2','{}','/images/img_2.jpg');
insert into public.room_type values ('3', 'Президентский номер', 'Супер лакшери','3','2','{}','/images/img_3.jpg');

delete from room_type cascade;