CREATE VIEW REL_USER_WINESHOP_EXTENDED
(
    USER_ID,
    NAME,
    SURNAME,
    EMAIL,
    PASSWORD,
    TYPE,
    WINESHOP_ID,
    WINESHOP_NAME
)
AS
    SELECT
        us.ID,
        us.NAME,
        us.SURNAME,
        us.EMAIL,
        us.PASSWORD,
        us.TYPE,
        ws.ID, ws.NAME
    FROM `REL_USER_WINESHOP
` 
JOIN USER AS us ON USER_ID = us.ID 
JOIN WINESHOP ws ON WINESHOP_ID = ws.ID 