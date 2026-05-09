-- 1. Endereços
CREATE TABLE Enderecos (
    id INT IDENTITY(1,1) PRIMARY KEY,
    logradouro VARCHAR(255),
    bairro VARCHAR(100),
    numero INT,
    complemento VARCHAR(100),
    cep VARCHAR(10),
    cidade VARCHAR(100),
    estado CHAR(2),
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2 DEFAULT GETDATE()
);

-- 2. Clientes
CREATE TABLE Clientes (
    id INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(150),
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2 DEFAULT GETDATE()
);

-- 3. Produtos
CREATE TABLE Produtos (
    id INT IDENTITY(1,1) PRIMARY KEY,
    sku VARCHAR(50) UNIQUE,
    nome VARCHAR(255),
    descricao VARCHAR(510),
    unidade_medida VARCHAR(20),
    categoria VARCHAR(100),
    ativo BIT DEFAULT 1,
    foto_url VARCHAR(MAX),
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2 DEFAULT GETDATE()
);

-- 4. Pedidos
CREATE TABLE Pedidos (
    id INT IDENTITY(1,1) PRIMARY KEY,
    data DATETIME2 DEFAULT GETDATE(),
    numero_pedido INT,
    total DECIMAL(18, 2),
    cliente_id INT,
    endereco_id INT,
    situacao varchar(50),
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2 DEFAULT GETDATE(),
    CONSTRAINT FK_Pedido_Cliente FOREIGN KEY (cliente_id) REFERENCES Clientes(id),
    CONSTRAINT FK_Pedido_Endereco FOREIGN KEY (endereco_id) REFERENCES Enderecos(id)
);

-- 5. Itens do Pedido
CREATE TABLE Pedido_Itens (
    id INT IDENTITY(1,1) PRIMARY KEY,
    pedido_id INT,
    produto_id INT,
    quantidade INT,
    preco_unitario DECIMAL(18, 2),
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2 DEFAULT GETDATE(),
    CONSTRAINT FK_Item_Pedido FOREIGN KEY (pedido_id) REFERENCES Pedidos(id),
    CONSTRAINT FK_Item_Produto FOREIGN KEY (produto_id) REFERENCES Produtos(id)
);

-- 6. Histórico de Preços
CREATE TABLE Precos (
    id INT IDENTITY(1,1) PRIMARY KEY,
    produto_id INT,
    data_vigencia DATETIME2,
    valor DECIMAL(18, 2),
    promocao BIT DEFAULT 0,
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2 DEFAULT GETDATE(),
    CONSTRAINT FK_Preco_Produto FOREIGN KEY (produto_id) REFERENCES Produtos(id)
);

-- 7. Movimentação de Estoque
CREATE TABLE Movimentacao_Estoque (
    id INT IDENTITY(1,1) PRIMARY KEY,
    produto_id INT,
    quantidade DECIMAL(18, 2) NOT NULL,
    data_movimentacao DATETIME2 DEFAULT GETDATE(),
    created_at DATETIME2 DEFAULT GETDATE(),
    updated_at DATETIME2 DEFAULT GETDATE(),
    CONSTRAINT FK_Estoque_Produto FOREIGN KEY (produto_id) REFERENCES Produtos(id)
);