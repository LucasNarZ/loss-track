CREATE TABLE tenants (
    id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    plan VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT pk_tenants PRIMARY KEY (id),
    CONSTRAINT uk_tenants_cnpj UNIQUE (cnpj)
);
