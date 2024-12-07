CREATE TABLE IF NOT EXISTS board (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    writer VARCHAR(50) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    modified_date TIMESTAMP NOT NULL,
    view_count INTEGER DEFAULT 0
);
