-- Migration inicial: cria a tabela 'wine'
CREATE TABLE IF NOT EXISTS wine (
    id SERIAL PRIMARY KEY,
    name  VARCHAR(120) NOT NULL,
    grape VARCHAR(80)  NOT NULL,
    year  INTEGER      NOT NULL CHECK (year >= 1850 AND year <= EXTRACT(YEAR FROM CURRENT_DATE) + 1),
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_wine_grape ON wine (grape);
