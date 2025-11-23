DROP DATABASE IF EXISTS concesionario_db;
CREATE DATABASE concesionario_db;
USE concesionario_db;

CREATE TABLE tipos_motor (
    id_tipo_motor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

INSERT INTO tipos_motor (nombre) VALUES ('Gasolina'),('Electrico'),('Hibrido');

CREATE TABLE tipos_identificacion (
    id_tipo_identificacion INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(10) UNIQUE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(100),
    activo BOOLEAN DEFAULT TRUE
);

INSERT INTO tipos_identificacion (codigo, nombre, descripcion) VALUES
('CC', 'Cedula de Ciudadania', 'Documento nacional'),
('CE', 'Cedula de Extranjeria', 'Extranjeros residentes'),
('TI', 'Tarjeta de Identidad', 'Menores de edad'),
('NIT', 'NIT', 'Numero de Identificacion Tributaria'),
('Pasaporte', 'Pasaporte', 'Documento de viaje internacional');

CREATE TABLE formas_pago (
    codigo_forma VARCHAR(10) PRIMARY KEY,
    nombre_forma VARCHAR(100) NOT NULL,
    descripcion TEXT
);

INSERT INTO formas_pago (codigo_forma, nombre_forma, descripcion) VALUES
('0', 'Contado', 'Pago en efectivo o contado'),
('1', 'Credito Directo', 'Credito otorgado directamente por el concesionario'),
('2', 'Credito Bancario', 'Financiamiento a traves de entidad bancaria');

CREATE TABLE clientes (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    tipo_identificacion VARCHAR(50) NOT NULL,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    edad INT NOT NULL,
    email VARCHAR(100),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE vendedores (
    id_vendedor INT PRIMARY KEY AUTO_INCREMENT,
    identificacion VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    profesion VARCHAR(100),
    fecha_contratacion DATE NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE automoviles (
    id_auto INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    color VARCHAR(50) NOT NULL,
    id_tipo_motor INT NOT NULL,
    precio_base DECIMAL(12,2) NOT NULL,
    impuesto_venta DECIMAL(12,2) NOT NULL DEFAULT 0,
    iva DECIMAL(12,2) NOT NULL DEFAULT 0,
    precio_total DECIMAL(12,2) NOT NULL DEFAULT 0,
    estado ENUM('Disponible', 'Vendido', 'Reservado') DEFAULT 'Disponible',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_tipo_motor) REFERENCES tipos_motor(id_tipo_motor)
);

DELIMITER $$
CREATE TRIGGER calcular_precios_auto
BEFORE INSERT ON automoviles
FOR EACH ROW
BEGIN
    SET NEW.impuesto_venta = NEW.precio_base * 0.15;
    SET NEW.iva = NEW.precio_base * 0.19;
    SET NEW.precio_total = NEW.precio_base + NEW.impuesto_venta + NEW.iva;
END$$
DELIMITER ;

CREATE TABLE ventas (
    id_venta INT PRIMARY KEY AUTO_INCREMENT,
    numero_factura VARCHAR(50) UNIQUE NOT NULL,
    fecha_venta DATE NOT NULL,
    id_cliente INT NOT NULL,
    id_vendedor INT NOT NULL,
    id_auto INT NOT NULL,
    codigo_forma_pago VARCHAR(10) NOT NULL,
    precio_base DECIMAL(12,2) NOT NULL,
    impuesto_venta DECIMAL(12,2) NOT NULL,
    iva DECIMAL(12,2) NOT NULL,
    total_pagar DECIMAL(12,2) NOT NULL,
    estado ENUM('Activa', 'Anulada') DEFAULT 'Activa',
    fecha_anulacion DATE NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),
    FOREIGN KEY (id_vendedor) REFERENCES vendedores(id_vendedor),
    FOREIGN KEY (id_auto) REFERENCES automoviles(id_auto),
    FOREIGN KEY (codigo_forma_pago) REFERENCES formas_pago(codigo_forma)
);

CREATE INDEX idx_auto_estado ON automoviles(estado);
CREATE INDEX idx_venta_fecha ON ventas(fecha_venta);
CREATE INDEX idx_cliente_identificacion ON clientes(identificacion);
CREATE INDEX idx_vendedor_identificacion ON vendedores(identificacion);

SELECT 'Base de datos creada exitosamente' AS mensaje;

