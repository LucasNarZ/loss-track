CREATE TABLE products (
    id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    barcode VARCHAR(255),
    category VARCHAR(255),
    CONSTRAINT pk_products PRIMARY KEY (id)
);
