CREATE TABLE participants(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    identity VARCHAR(11) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    clinic_id UUID NOT NULL,
    CONSTRAINT fk_clinic_id FOREIGN KEY (clinic_id) REFERENCES clinics(id)
);
