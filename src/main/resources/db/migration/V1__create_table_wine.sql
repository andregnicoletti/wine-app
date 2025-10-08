-- Migration inicial:

-- Tabela paises
CREATE TABLE IF NOT EXISTS paises (
    id SERIAL PRIMARY KEY,
    nome  TEXT NOT NULL UNIQUE
);

-- regiões (podem ter sub-regiões)
CREATE TABLE IF NOT EXISTS regioes (
    id SERIAL PRIMARY KEY,
    pais_id INT NOT NULL REFERENCES paises(id),
    nome  TEXT NOT NULL,
    regiao_pai_id INT REFERENCES regioes(id),
    UNIQUE (pais_id, nome)
);

-- vinícolas / produtores
CREATE TABLE IF NOT EXISTS vinicolas (
    id SERIAL PRIMARY KEY,
    regiao_id INT REFERENCES regioes(id),
    nome TEXT NOT NULL,
    site TEXT,
    UNIQUE (nome, regiao_id)
);

-- uvas
CREATE TABLE IF NOT EXISTS uvas
(
    id   SERIAL PRIMARY KEY,
    nome TEXT NOT NULL UNIQUE
);

-- vinhos
CREATE TABLE IF NOT EXISTS vinhos
(
    id   SERIAL PRIMARY KEY,
    vinicola_id INT NOT NULL REFERENCES vinicolas(id),
    nome TEXT NOT NULL,
    safra INT,
    regiao_id INT REFERENCES regioes(id),
    cor TEXT,
    estilo TEXT,
    docura TEXT,
    teor_alcoolico NUMERIC(4,1),
    volume_ml INT DEFAULT 750,
    codigo_barras TEXT,
    observacoes TEXT
);

-- relação de vinho x uvas (para blends)
CREATE TABLE IF NOT EXISTS vinhos_uvas
(
    vinho_id   INT NOT NULL REFERENCES vinhos (id) ON DELETE CASCADE,
    uva_id     INT NOT NULL REFERENCES uvas (id),
    percentual NUMERIC(5, 2),
    PRIMARY KEY (vinho_id, uva_id),
    CHECK ( percentual IS NULL OR (percentual >= 0 AND percentual <= 100))
);

-- usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    perfil VARCHAR(20) NOT NULL CHECK (perfil IN ('parcial','completo'))
);

CREATE INDEX IF NOT EXISTS idx_vinhos_nome ON vinhos(nome);
CREATE INDEX IF NOT EXISTS idx_vinhos_safra ON vinhos(safra);
CREATE INDEX IF NOT EXISTS idx_vinicolas_nome ON vinicolas(nome);
CREATE INDEX IF NOT EXISTS idx_regioes_nome ON regioes(nome);
