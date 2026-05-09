-- Povoando Clientes e Endereços base
INSERT INTO Enderecos (logradouro, bairro, numero, cep, cidade, estado, created_at, updated_at) 
VALUES ('Av. Principal', 'Centro', 100, '01000-000', 'São Paulo', 'SP', GETDATE(), GETDATE());

INSERT INTO Clientes (nome, cpf, telefone, email, created_at, updated_at) 
VALUES ('Usuário de Teste', '111.222.333-44', '11988887777', 'teste@provedor.com', GETDATE(), GETDATE());

-- Povoando 20 Produtos de Tecnologia
INSERT INTO Produtos (sku, nome, unidade_medida, categoria, ativo, created_at, updated_at) VALUES 
('TECH001', 'Smartphone Samsung Galaxy S24 Ultra', 'UN', 'Celulares', 1, GETDATE(), GETDATE()),
('TECH002', 'iPhone 15 Pro Max 256GB', 'UN', 'Celulares', 1, GETDATE(), GETDATE()),
('TECH003', 'Notebook Dell XPS 13', 'UN', 'Computadores', 1, GETDATE(), GETDATE()),
('TECH004', 'MacBook Air M3 13"', 'UN', 'Computadores', 1, GETDATE(), GETDATE()),
('TECH005', 'Monitor Gamer LG UltraGear 27"', 'UN', 'Periféricos', 1, GETDATE(), GETDATE()),
('TECH006', 'Teclado Mecânico Keychron K2', 'UN', 'Periféricos', 1, GETDATE(), GETDATE()),
('TECH007', 'Mouse Logitech MX Master 3S', 'UN', 'Periféricos', 1, GETDATE(), GETDATE()),
('TECH008', 'Headset Sony WH-1000XM5', 'UN', 'Áudio', 1, GETDATE(), GETDATE()),
('TECH009', 'Placa de Vídeo RTX 4070 Super', 'UN', 'Hardware', 1, GETDATE(), GETDATE()),
('TECH010', 'Processador Intel Core i7-14700K', 'UN', 'Hardware', 1, GETDATE(), GETDATE()),
('TECH011', 'SSD NVMe Samsung 990 Pro 1TB', 'UN', 'Hardware', 1, GETDATE(), GETDATE()),
('TECH012', 'Memória RAM Corsair Vengeance 32GB DDR5', 'UN', 'Hardware', 1, GETDATE(), GETDATE()),
('TECH013', 'iPad Air M2', 'UN', 'Tablets', 1, GETDATE(), GETDATE()),
('TECH014', 'Kindle Paperwhite 16GB', 'UN', 'E-readers', 1, GETDATE(), GETDATE()),
('TECH015', 'PlayStation 5 Slim', 'UN', 'Consoles', 1, GETDATE(), GETDATE()),
('TECH016', 'Nintendo Switch OLED', 'UN', 'Consoles', 1, GETDATE(), GETDATE()),
('TECH017', 'Roteador Wi-Fi 6 TP-Link Archer', 'UN', 'Redes', 1, GETDATE(), GETDATE()),
('TECH018', 'Câmera Mirrorless Canon EOS R10', 'UN', 'Fotografia', 1, GETDATE(), GETDATE()),
('TECH019', 'Smartwatch Apple Watch Series 9', 'UN', 'Wearables', 1, GETDATE(), GETDATE()),
('TECH020', 'Drone DJI Mini 4 Pro', 'UN', 'Drones', 1, GETDATE(), GETDATE());

-- Vinculando Preços Iniciais (Exemplo para os 2 primeiros)
INSERT INTO Precos (produto_id, data_vigencia, valor, promocao, created_at, updated_at) 
VALUES (1, GETDATE(), 7500.00, 0, GETDATE(), GETDATE());
INSERT INTO Precos (produto_id, data_vigencia, valor, promocao, created_at, updated_at) 
VALUES (2, GETDATE(), 8900.00, 0, GETDATE(), GETDATE());