CREATE TABLE financial_item (
    id BINARY(16) NOT NULL PRIMARY KEY,
    category_id BINARY(16) NOT NULL,
    name VARCHAR(70) NOT NULL,
    regex_name VARCHAR(70) NOT NULL,
    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES category(id)
);