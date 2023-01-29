
CREATE TABLE order_detail (
    id uuid primary key,
    first_name varchar(255),
    last_name varchar(255),
    phone_number varchar(255),
    email_address varchar(255),
    moving_from_address varchar(255),
    moving_to_address varchar(255),
    type_of_service varchar(255)
);

CREATE TABLE consultant_order (
    id uuid primary key,
    consultant_id uuid,
    consultant_name varchar(255),
    order_detail_id uuid references order_detail(id),
    order_register date,
    appointment_date date
);


