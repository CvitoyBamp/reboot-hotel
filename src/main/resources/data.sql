--Автогенератор инсертов смотри в HotelApplication.java
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('1','slider-1.jpg','/images/slider-1.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('2','slider-2.jpg','/images/slider-2.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('3','slider-3.jpg','/images/slider-3.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('4','slider-4.jpg','/images/slider-4.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('5','slider-5.jpg','/images/slider-5.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('6','slider-6.jpg','/images/slider-6.jpg');
insert into PUBLIC.PHOTO_STORE(id, photo_name, photo) values ('7','slider-7.jpg','/images/slider-7.jpg');

-- Роли
insert into PUBLIC.ROLES(id, role_name) values ('1', 'ADMIN');
insert into PUBLIC.ROLES(id, role_name) values ('2', 'USER');

-- Типовые номера
insert into public.room_type(id, room_name, description, max_adults, max_children, photo_src) values ('1', 'Одноместный номер', 'Одноместный номер для одного с максимальным комфортом','1','0','/images/img_1.jpg');
insert into public.room_type(id, room_name, description, max_adults, max_children, photo_src) values ('2', 'Семейный номер', 'Семейный номер для родителей с детьми','3','2','/images/img_2.jpg');
insert into public.room_type(id, room_name, description, max_adults, max_children, photo_src) values ('3', 'Президентский номер', 'Супер лакшери','3','2','/images/img_3.jpg');

-- Комнаты
insert into PUBLIC.ROOM(id, room_type_id, price_per_day, is_locked) values ('1', '1', '90', 'false');
insert into PUBLIC.ROOM(id, room_type_id, price_per_day, is_locked) values ('2', '2', '120', 'false');
insert into PUBLIC.ROOM(id, room_type_id, price_per_day, is_locked) values ('3', '3', '250', 'false');
insert into PUBLIC.ROOM(id, room_type_id, price_per_day, is_locked) values ('4', '1', '95', 'false');
insert into PUBLIC.ROOM(id, room_type_id, price_per_day, is_locked) values ('5', '1', '100', 'false');

-- Пользователи
insert into public.user(id, roles_id, name, birthday, password, email, phone) values ('1', '2','Василий','03.04.2000','123$2a$10$ZI3IFWvl02PrSyCZ3orysesMZKGA2pD.FGjzRHQYrBlrTJYk914V2456','vasya@ya.ru','89001234569');
insert into public.user(id, roles_id, name, birthday, password, email, phone) values ('2', '2','Мария','05.10.1993','$2a$10$ZI3IFWvl02PrSyCZ3orysesMZKGA2pD.FGjzRHQYrBlrTJYk914V2','masha@google.com','89011234569');
insert into public.user(id, roles_id, name, birthday, password, email, phone) values ('3', '2','Виктор','03.04.1976','$2a$10$ZI3IFWvl02PrSyCZ3orysesMZKGA2pD.FGjzRHQYrBlrTJYk914V2','vitya@yahoo.com','89011274569');
insert into public.user(id, roles_id, name, birthday, password, email, phone) values ('4', '2','Игорь','09.08.1987','$2a$10$ZI3IFWvl02PrSyCZ3orysesMZKGA2pD.FGjzRHQYrBlrTJYk914V2','шпщк@нф.com','89011974269');
insert into public.user(id, roles_id, name, birthday, password, email, phone) values ('5', '1','admin','09.09.1987','$2a$10$ZI3IFWvl02PrSyCZ3orysesMZKGA2pD.FGjzRHQYrBlrTJYk914V2','admin@admin.ru','89091586789');

-- Отзывы
insert into public.reviews(id, hotel_user_id, comment, rating) values ('1', '2', 'Очень понравился номер', '5');
insert into public.reviews(id, hotel_user_id, comment, rating) values ('2', '2', 'Супер-отель, 4 за неприветливость персонала', '4');
insert into public.reviews(id, hotel_user_id, comment, rating) values ('3', '3', 'Место нормальное, ресторан неочень', '3');
insert into public.reviews(id, hotel_user_id, comment, rating) values ('4', '3', 'Не поменяли полотенце', '2');
insert into public.reviews(id, hotel_user_id, comment, rating) values ('5', '4', 'Ужасное место, нет мидий', '1');
insert into public.reviews(id, hotel_user_id, comment, rating) values ('6', '4', 'Больше не приеду: Манжерок лучше.', '4');

-- Обновляем сиквенсы после инсертов
DO
'
    declare
        _r record;
        _i bigint;
        _m bigint;
    begin
        for _r in (
            SELECT relname,nspname,d.refobjid::regclass, a.attname, refobjid
            FROM   pg_depend    d
                       JOIN   pg_attribute a ON a.attrelid = d.refobjid AND a.attnum = d.refobjsubid
                       JOIN pg_class r on r.oid = objid
                       JOIN pg_namespace n on n.oid = relnamespace
            WHERE  d.refobjsubid > 0 and  relkind = ''S''
        ) loop
                execute format(''select last_value from %I.%I'',_r.nspname,_r.relname) into _i;
                execute format(''select max(%I) from %s'',_r.attname,_r.refobjid) into _m;
                if coalesce(_m,0) > _i then
                    raise info ''%'',concat(''changed: '',_r.nspname,''.'',_r.relname,'' from:'',_i,'' to:'',_m);
                    execute format(''alter sequence %I.%I restart with %s'',_r.nspname,_r.relname,_m+1);
                end if;
            end loop;
    end;
' LANGUAGE PLPGSQL;