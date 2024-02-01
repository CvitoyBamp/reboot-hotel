CREATE TABLE PUBLIC.ROOM (
    ID SERIAL PRIMARY KEY, -- первичный ключ
    ROOM_TYPE_ID INTEGER NOT NULL, -- внешний ключ для шаблона комнаты
    PRICE_PER_DAY NUMERIC NOT NULL, -- цена за день
    IS_LOCKED BOOLEAN NOT NULL, -- номер занят?
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- будет меняться при апдейтах за счет триггеров
    CONSTRAINT FK_ROOM_TYPE_ID
        FOREIGN KEY (ROOM_TYPE_ID)
            REFERENCES PUBLIC.ROOM_TYPE(ID)
);

drop table room cascade;

insert into PUBLIC.ROOM values ('1', '1', '90', 'false');
insert into PUBLIC.ROOM values ('2', '2', '120', 'false');
insert into PUBLIC.ROOM values ('3', '3', '250', 'false');