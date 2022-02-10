CREATE TABLE products
(
    product_id          SERIAL PRIMARY KEY,
    product_name        varchar  NOT NULL,
);

CREATE TABLE articleEntities
(
    speaker_id    SERIAL PRIMARY KEY,
    first_name    varchar(30)   NOT NULL,
    last_name     varchar(30)   NOT NULL,
    title         varchar(40)   NOT NULL,
    company       varchar(50)   NOT NULL,
    speaker_bio   varchar(2000) NOT NULL,
    speaker_photo BYTEA   NULL
);

CREATE TABLE session_speakers
(
    session_id integer NOT NULL REFERENCES sessions (session_id),
    speaker_id integer NOT NULL REFERENCES speakers (speaker_id)
);

CREATE TABLE workshops
(
    workshop_id   SERIAL PRIMARY KEY,
    workshop_name varchar(60)   NOT NULL,
    description   varchar(1024) NOT NULL,
    requirements  varchar(1024) NOT NULL,
    room          varchar(30)   NOT NULL,
    capacity      integer       NOT NULL
);

CREATE TABLE workshop_speakers
(
    workshop_id integer NOT NULL REFERENCES workshops (workshop_id),
    speaker_id  integer NOT NULL REFERENCES speakers (speaker_id)
);

CREATE TABLE workshop_registrations
(
    workshop_id        integer NOT NULL REFERENCES workshops (workshop_id),
    attendee_ticket_id integer NOT NULL REFERENCES attendee_tickets (attendee_ticket_id)
);