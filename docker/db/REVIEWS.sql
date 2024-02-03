CREATE TABLE PUBLIC.REVIEWS (
    ID SERIAL PRIMARY KEY, -- номер отзыва (первичный ключ)
    USER_ID INTEGER NOT NULL, -- внешний ключ из таблицы юзер
    COMMENT TEXT NOT NULL, -- комментарий клиента
    RATING SMALLINT CHECK (RATING BETWEEN 1 AND 5), -- оценка с констрейнтом от 1 до 5
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- будет меняться при апдейтах за счет триггеров
    CONSTRAINT FK_REVIEWS_USER
        FOREIGN KEY (USER_ID)
            REFERENCES PUBLIC.USER(ID)
);

insert into public.reviews values ('1', '2', 'Очень понравился номер', '5');
insert into public.reviews values ('2', '2', 'Супер-отель, 4 за неприветливость персонала', '4');
insert into public.reviews values ('3', '3', 'Место нормальное, ресторан неочень', '3');
insert into public.reviews values ('4', '3', 'Не поменяли полотенце', '2');
insert into public.reviews values ('5', '4', 'Ужасное место, нет мидий', '1');
insert into public.reviews values ('6', '5', 'Больше не приеду: Манжерок лучше.', '4');


drop table PUBLIC.REVIEWS cascade;