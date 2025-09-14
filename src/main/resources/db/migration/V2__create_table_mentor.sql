CREATE TABLE mentor (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    avatar_path VARCHAR(200),
    expertise VARCHAR(200),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);