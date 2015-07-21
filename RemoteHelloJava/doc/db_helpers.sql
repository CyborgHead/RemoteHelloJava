SELECT id, stat_date, wind_speed
FROM weatherstats;

SELECT MAX(id) AS id
FROM weatherstats;

INSERT INTO weatherstats (stat_date, wind_speed)
VALUES ('2000-01-01', 15.0);

DELETE FROM weatherstats WHERE id = 2;