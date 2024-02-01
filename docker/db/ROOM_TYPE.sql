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

drop table PUBLIC.ROOM_TYPE cascade;

insert into public.room_type values ('1', 'Одноместный номер', 'Одноместный номер для одного с максимальным комфортом','1','0','{}','https://downloader.disk.yandex.ru/preview/4ef8d952a4c3eabd38fb6dc3786e33d4fef436eb38b4a7507f8f2f44d8e949c1/65bc1fa8/DTeLflnP1spMOR8j50RWhVoX5dfRh-R-l8R9UbRWKa6qttV4YEhUw1Ue_57tlh74G0xuhXxc1vlFqmZQgwC0iQ%3D%3D?uid=0&filename=img_1.jpg&disposition=inline&hash=&limit=0&content_type=image%2Fjpeg&owner_uid=0&tknv=v2&size=2048x2048');
insert into public.room_type values ('2', 'Семейный номер', 'Семейный номер для родителей с детьми','3','2','{}','https://downloader.disk.yandex.ru/preview/26d2ba5625cccf241e8eaab7b0ee649d759d50674b8fd6b7d9e2a692fe25c1f6/65bc2502/YE4saQoKoKawIzHrt_IjNZxzatjyUCoTHjA2oV6zTv_eDnZv2SKGEDHkbfGq9LbEXEGgjdNZNobOb8BXj5COCQ%3D%3D?uid=0&filename=img_2.jpg&disposition=inline&hash=&limit=0&content_type=image%2Fjpeg&owner_uid=0&tknv=v2&size=2048x2048');
insert into public.room_type values ('3', 'Президентский номер', 'Супер лакшери','3','2','{}','https://downloader.disk.yandex.ru/preview/dbe4a2ad7a99ac73f7d6be582955d3853cecde35abbc51fa46a75480226926b5/65bc2525/7hOyl1oLs6GpL6lMQqY7NrXAKCd6jxFtvdtH6R_0nKB4JmzrhqG2UQ2P7TOkKhihzeco2uSq7C1l9DTbS7IXjg%3D%3D?uid=0&filename=img_3.jpg&disposition=inline&hash=&limit=0&content_type=image%2Fjpeg&owner_uid=0&tknv=v2&size=2048x2048');