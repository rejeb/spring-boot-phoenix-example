CREATE TABLE IF NOT EXISTS us_population (update_ts TIMESTAMP NOT NULL,state CHAR(2) NOT NULL,city VARCHAR NOT NULL,population INTEGER CONSTRAINT us_population_pk PRIMARY KEY (update_ts,state, city));

CREATE TABLE IF NOT EXISTS us_population_event (event_ts TIMESTAMP NOT NULL,event_type VARCHAR NOT NULL,state CHAR(2),city VARCHAR CONSTRAINT event_pk PRIMARY KEY (event_ts, event_type));
