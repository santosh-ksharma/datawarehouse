CREATE TABLE products
(
    product_id  SERIAL PRIMARY KEY,
    product_name   varchar   NOT NULL,
    UNIQUE(product_name)
);

CREATE TABLE articles
(
    id  SERIAL  PRIMARY KEY,
    art_id  integer NOT NULL,
    amount_of   integer   NOT NULL,
    product_id integer  NOT NULL REFERENCES products(product_id)
);

CREATE TABLE inventories
(
    art_id  integer PRIMARY KEY,
    art_name   varchar   NOT NULL,
    stock varchar   NOT NULL
);