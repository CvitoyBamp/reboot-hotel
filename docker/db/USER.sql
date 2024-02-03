CREATE TABLE PUBLIC.USER (
    ID SERIAL PRIMARY KEY, -- первичный ключ
    ROLE_ID INTEGER NOT NULL, -- внешний ключ к таблице ролей
    NAME VARCHAR(128) NOT NULL, -- имя гостя
    BIRTHDAY DATE NOT NULL, -- дата рождения гостя
    PASSWORD TEXT NOT NULL, -- пароль аккаунта
    EMAIL VARCHAR(320) NOT NULL UNIQUE, -- имеил
    PHONE VARCHAR(11) NOT NULL UNIQUE, -- телефон
    ROW_INS_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- время, когда запись появилась
    ROW_UPD_TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- будет меняться при апдейтах за счет триггеров
    CONSTRAINT FK_ROLES
        FOREIGN KEY (ROLE_ID)
            REFERENCES PUBLIC.ROLES(ID)
);

insert into public.user values ('1', '1','admin','03.23.1980','123456','admin@admin.ru','89001234567');
insert into public.user values ('2', '2','Василий','03.14.2000','123456','vasya@ya.ru','89001234569');
insert into public.user values ('3', '2','Мария','05.10.1993','123456','masha@google.com','89011234569');
insert into public.user values ('4', '2','Виктор','03.04.1976','123456','vitya@yahoo.com','89011274569');
insert into public.user values ('5', '2','Игорь','09.18.1987','123456','шпщк@нф.com','89011974269');


drop table public.user cascade ;