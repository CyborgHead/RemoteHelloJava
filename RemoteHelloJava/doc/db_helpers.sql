SELECT id, stat_date, wind_speed
FROM weatherstats;

SELECT MAX(id) AS id
FROM weatherstats;

INSERT INTO weatherstats (stat_date, wind_speed)
VALUES ('2000-01-01', 15.0);

DELETE FROM weatherstats WHERE id = 2;


-----------------------------

CREATE DATABASE codeclinic
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;

COMMENT ON DATABASE codeclinic
  IS 'Testing JDBC as well as solving the Code-Clinic Problems.';

  
CREATE TABLE weatherstats
(
  stat_date date NOT NULL, -- Record Date.
  wind_speed real, -- Given Wind Speed.
  id serial NOT NULL, -- A unique auto-incremental identifier for records.
  CONSTRAINT weatherstats_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE weatherstats
  OWNER TO postgres;
COMMENT ON TABLE weatherstats
  IS 'Stores weather stats imported from text files or via web-services.';
COMMENT ON COLUMN weatherstats.stat_date IS 'Record Date.';
COMMENT ON COLUMN weatherstats.wind_speed IS 'Given Wind Speed.';
COMMENT ON COLUMN weatherstats.id IS 'A unique auto-incremental identifier for records.';

