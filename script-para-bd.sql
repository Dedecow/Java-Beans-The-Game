CREATE DATABASE javabeans_game;

USE javabeans_game;

CREATE TABLE IF NOT EXISTS ingredientes (
    id_ingrediente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS menu_itens (
    id_item INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS receitas (
    fk_item INT NOT NULL,
    fk_ingrediente INT NOT NULL,
    PRIMARY KEY (fk_item, fk_ingrediente),
    FOREIGN KEY (fk_item) REFERENCES menu_itens(id_item),
    FOREIGN KEY (fk_ingrediente) REFERENCES ingredientes(id_ingrediente)
);

CREATE TABLE IF NOT EXISTS clientes_npc (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS frases (
    id_frase INT PRIMARY KEY AUTO_INCREMENT,
    texto_frase VARCHAR(255) NOT NULL,
    tipo_cliente VARCHAR(50) NOT NULL /* APRESSADO, CALMO, EXIGENTE, INDECISO */
);

INSERT INTO ingredientes (nome) VALUES
('Café'), ('Água'), ('Leite'), ('Leite vegetal'), 
('Açúcar'), ('Cacau'), ('Matcha'), ('Chantilly'), ('Canela'),
('Gelo'), ('Adoçante');

INSERT INTO menu_itens (nome) VALUES 
('Café Preto'), ('Café Latte'), ('Cappuccino'), 
('Matcha Latte'), ('Chocolate Quente'), ('Café com Canela');

INSERT INTO receitas (fk_item, fk_ingrediente) VALUES 
(1, 1), (1, 2), /* Café Preto (Café, Água) */
(2, 1), (2, 2), (2, 3), /* Café Latte (Café, Água, Leite) */
(3, 1), (3, 2), (3, 3), (3, 5), (3, 6), /* Cappuccino (Café, Água, Leite, Açúcar, Cacau) */
(4, 7), (4, 2), (4, 3), /* Matcha Latte (Matcha, Água, Leite) */
(5, 6), (5, 3), (5, 5), (5, 8), /* Chocolate Quente (Cacau, Leite, Açúcar, Chantilly) */
(6, 1), (6, 2), (6, 9); /* Café com Canela (Café, Água, Canela) */

INSERT INTO clientes_npc (nome) VALUES 
('Andre'), ('Leonan'), ('Debora'), ('Luisa'), ('Matheus'), ('Lucas');

INSERT INTO frases (tipo_cliente, texto_frase) VALUES
('APRESSADO', 'Vai demorar muito? Preciso rápido!'),
('CALMO', 'Tudo bem, posso esperar.'),
('EXIGENTE', 'Espero o melhor serviço possível.'),
('INDECISO', 'Não sei o que escolher...');