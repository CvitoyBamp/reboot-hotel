CREATE TABLE PUBLIC.ROOM (
    ID SERIAL PRIMARY KEY, -- первичный ключ
    NAME VARCHAR(64) NOT NULL, -- наименование номера
    PRICE NUMERIC NOT NULL, -- цена за день
    PHOTOS TEXT[] NOT NULL, -- массив из ссылок на фотографии
    ADDS JSONB -- доп. приколы в номере {"tv": true, "wifi": true, "fridge": true, "conditioner": true}
);

--1	Luxury	16000.00	{https://avatars.mds.yandex.net/get-altay/10767865/2a0000018a642d65148c57bdbcb4f918c143/XXXL,https://avatars.mds.yandex.net/get-altay/9849468/2a0000018a642d09f208937739cf5db57402/XXXL}	{"tv": true, "wifi": true, "fridge": true, "conditioner": true}
--2	Norm	8000.00	{https://avatars.mds.yandex.net/get-altay/8075227/2a000001847ad529702695836ac699a11730/XXXL,https://avatars.mds.yandex.net/get-altay/10156117/2a0000018a7e3124ab0c1f64ff2341285abc/XXXL}	{"tv": true, "wifi": true, "fridge": false, "conditioner": true}

drop table room cascade;