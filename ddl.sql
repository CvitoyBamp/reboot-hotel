-- public.photo_store определение

-- Drop table

-- DROP TABLE public.photo_store;

CREATE TABLE public.photo_store (
                                    id int8 NOT NULL,
                                    row_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                    upd_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                    photo varchar(255) NOT NULL,
                                    photo_name varchar(255) NOT NULL,
);


-- public.roles определение

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
                              id bigserial NOT NULL,
                              row_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              upd_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                              role_name varchar(64) NULL,
);


-- public.room_type определение

-- Drop table

-- DROP TABLE public.room_type;

CREATE TABLE public.room_type (
                                  max_adults int2 NOT NULL,
                                  max_children int2 NOT NULL,
                                  id bigserial NOT NULL,
                                  row_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  upd_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  description varchar(255) NOT NULL,
                                  photo_src varchar(255) NULL,
                                  room_name varchar(255) NOT NULL,
);


-- public.room определение

-- Drop table

-- DROP TABLE public.room;

CREATE TABLE public.room (
                             is_locked bool NOT NULL,
                             price_per_day numeric(38, 2) NOT NULL,
                             id int8 NOT NULL,
                             room_type_id int8 NULL,
                             row_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             upd_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                             CONSTRAINT fkd468eq7j1cbue8mk20qfrj5et FOREIGN KEY (room_type_id) REFERENCES public.room_type(id)
);


-- public."user" определение

-- Drop table

-- DROP TABLE public."user";

CREATE TABLE public."user" (
                               birthday date NOT NULL,
                               id bigserial NOT NULL,
                               roles_id int8 NULL,
                               row_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               upd_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                               "name" varchar(128) NOT NULL,
                               email varchar(320) NOT NULL,
                               "password" varchar(255) NOT NULL,
                               phone varchar(255) NOT NULL,
                               CONSTRAINT fkqwdb2ejj8pvnovexfq172hej4 FOREIGN KEY (roles_id) REFERENCES public.roles(id)
);


-- public.booking определение

-- Drop table

-- DROP TABLE public.booking;

CREATE TABLE public.booking (
                                end_date date NOT NULL,
                                start_date date NOT NULL,
                                hotel_user_id int8 NULL,
                                id bigserial NOT NULL,
                                room_id int8 NULL,
                                row_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                upd_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                note varchar(255) NOT NULL,
                                CONSTRAINT fkq83pan5xy2a6rn0qsl9bckqai FOREIGN KEY (room_id) REFERENCES public.room(id),
                                CONSTRAINT fkrweep9f6rkkm07knty2ouwqmi FOREIGN KEY (hotel_user_id) REFERENCES public."user"(id)
);


-- public.reviews определение

-- Drop table

-- DROP TABLE public.reviews;

CREATE TABLE public.reviews (
                                rating int2 NULL,
                                hotel_user_id int8 NULL,
                                id bigserial NOT NULL,
                                row_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                upd_insert_timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                "comment" varchar(255) NOT NULL,
                                CONSTRAINT fkbsvj4rn82lx5aumlbp8ysujhk FOREIGN KEY (hotel_user_id) REFERENCES public."user"(id)
);