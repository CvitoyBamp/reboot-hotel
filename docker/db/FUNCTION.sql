-- функция, которая будет обновлять поле ROW_UPD_TIMESTAMP
CREATE OR REPLACE FUNCTION ROW_UPDATE()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.ROW_UPD_TIMESTAMP = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- триггер, которая будет вызывать функцию ROW_UPDATE при апдейте той или иной строки.
CREATE TRIGGER ROW_UPDATE_PHOTO_STORE
    BEFORE UPDATE ON PUBLIC.PHOTO_STORE
    FOR EACH ROW
    EXECUTE FUNCTION ROW_UPDATE();

CREATE TRIGGER ROW_UPDATE_ROOM_TYPE
    BEFORE UPDATE ON PUBLIC.ROOM_TYPE
    FOR EACH ROW
EXECUTE FUNCTION ROW_UPDATE();

CREATE TRIGGER ROW_UPDATE_BOOKING
    BEFORE UPDATE ON PUBLIC.BOOKING
    FOR EACH ROW
EXECUTE FUNCTION ROW_UPDATE();

CREATE TRIGGER ROW_UPDATE_USER
    BEFORE UPDATE ON PUBLIC.USER
    FOR EACH ROW
EXECUTE FUNCTION ROW_UPDATE();

CREATE TRIGGER ROW_UPDATE_ROOM
    BEFORE UPDATE ON PUBLIC.ROOM
    FOR EACH ROW
EXECUTE FUNCTION ROW_UPDATE();

CREATE TRIGGER ROW_UPDATE_REVIEWS
    BEFORE UPDATE ON PUBLIC.REVIEWS
    FOR EACH ROW
EXECUTE FUNCTION ROW_UPDATE();

