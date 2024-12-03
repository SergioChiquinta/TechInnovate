/* Permisos Usuario techinnovate */
GRANT ALL PRIVILEGES ON techinnovate.* TO 'techinnovate'@'localhost' IDENTIFIED BY 'techinnovate';
FLUSH PRIVILEGES;

/* TABLAS DEL PROYECTO */
CREATE TABLE producto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(200) NOT NULL,
    descripcion VARCHAR(500),
    imagen_url VARCHAR(500),
    precio DECIMAL(10, 2) NOT NULL,
    distribuidor_id INT NOT NULL,
    categoria VARCHAR(200),
    detalle TEXT,
    marca VARCHAR(100),
    color VARCHAR(50),
    garantia INT,
    FOREIGN KEY (distribuidor_id) REFERENCES distribuidor(id)
);

CREATE TABLE distribuidor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(200) NOT NULL,
    sitio_web VARCHAR(500)
);

/* INFORMACIÓN TABLA producto */
INSERT INTO producto 
(nombre, descripcion, imagen_url, precio, distribuidor_id, categoria, detalle, marca, color, garantia) VALUES
('SOPORTE AUDÍFONO GAMING', 'XTECH YUREI XTH690', 'https://content.emarket.pe/common/collections/products/84/55/84552802-2161-4995-8896-4613b0666c84.jpg', 139.90, 1, 'Audífonos', 'Soporte iluminado con RGB con puertos USB 2.0', 'Xtech Yurei', 'Negro', 1),
('TECLADO GAMING ESPAÑOL', 'XTECH CHEVALIER XTK505S', 'https://content.emarket.pe/common/collections/products/dd/5b/dd5b93b8-1f27-4227-869f-0af15db8d87f.jpg', 89.90, 2, 'Teclados', 'Teclado de membrana con iluminación led multicolor', 'Xtech Chevalier', 'Negro', 1),
('AUDÍFONO GAMING', 'HYPERX CLOUD ALPHA S 7.1 SURROUND SOUND', 'https://content.emarket.pe/common/collections/products/c9/4c/c94c16bd-e307-423c-8cf6-00311ac62763.jpg', 399.90, 3, 'Audífonos', 'Altavoces de cámara doble de sonido con cancelación de ruido y microfono', 'Hyperx Cloud', 'Negro', 2),
('MOUSEPAD GAMING', 'LOGITECH G240 CLOTH MEDIO (M)', 'https://content.emarket.pe/common/collections/products/4f/9d/4f9d3aa5-e5d2-4728-a4d6-14817dfa3282.jpg', 79.90, 4, 'Mousepads', 'Alfombrilla de mouse de 280 x 340 mm', 'Logitech', 'Negro', 6),
('MOUSE GAMING INALÁMBRICO', 'HYPERX PULSEFIRE DART', 'https://content.emarket.pe/common/collections/products/72/91/729130a2-0dbf-462e-88d9-7b742d61878a.jpg', 429.90, 1, 'Mouse', 'Mouse inalámbrico con conexión 2.4 GHZ y hasta 50 horas de batería con una sola carga', 'HyperX', 'Negro', 1),
('NINTENDO SWITCH PLAYSTAND', 'MODELO ZELDA BY HORI', 'https://content.emarket.pe/common/collections/products/e8/4b/e84b839b-8943-4a3e-9428-6e2447743ce0.jpg', 79.90, 5, 'Consolas', 'Soporte para Nintendo Switch', 'Nintendo', 'Negro', 1),
('MOUSE GAMING', 'LOGITECH G300S 2500 DPI', 'https://content.emarket.pe/common/collections/products/ad/b9/adb93add-ef2d-4901-adea-9ae82b805276.jpg', 135.90, 6, 'Mouse', 'Mouse 9 botones programable, durabilidad con 10 M de clics', 'Logitech', 'Negro', 6),
('TECLADO GAMING', 'LOGITECH G PRO - USB CARBON', 'https://content.emarket.pe/common/collections/products/d3/6f/d36f2ac1-1818-4ce5-9d09-c13efdef44f5.jpg', 399.90, 7, 'Teclados', 'Teclado de switch azul con tecnologia LIGHTSYNC', 'Logitech', 'Negro', 6),
('MOUSEPAD', 'HYPERX FURY S PRO SPEED EDITION LARGO (L)', 'https://content.emarket.pe/common/collections/products/f2/4a/f24aac93-cb0b-489c-9d9f-a1bdfe9f465c.jpg', 89.90, 7, 'Mousepads', 'Mousepad de un tamaño de 450 x 400 mm, tiene una tela densa y suave al tacto', 'Hyperx', 'Rojo y negro', 2);

/* INFORMACIÓN TABLA DISTRIBUIDOR */
INSERT INTO distribuidor
(id, nombre, sitio_web) VALUES
(1, '2K', 'https://2k.com'),
(2, 'Focus Home Interactive', 'https://www.focus-home.com'),
(3, 'Thekla', null),
(4, 'Number One', null),
(5, '505 Games', 'https://505games.com'),
(6, 'Unknown Worlds Entertaiment', 'https://unknownworlds.com'),
(7, 'Campo Santo', 'https://camposanto.com');

/* ACTUALIZAR DISTRIBUIDOR_ID EN PRODUCTO */
UPDATE producto SET distribuidor_id = 1 WHERE id IN (1, 5);
UPDATE producto SET distribuidor_id = 2 WHERE id IN (2);
UPDATE producto SET distribuidor_id = 3 WHERE id IN (3);
UPDATE producto SET distribuidor_id = 4 WHERE id IN (4);
UPDATE producto SET distribuidor_id = 5 WHERE id IN (6);
UPDATE producto SET distribuidor_id = 6 WHERE id IN (7);
UPDATE producto SET distribuidor_id = 7 WHERE id IN (8, 9);

/* AHORA DISTRIBUIDOR_ID NO DEBE SER NULO */
ALTER TABLE producto
MODIFY distribuidor_id INT NOT NULL;

/* AGREGAR STOCK A PRODUCTO*/
ALTER TABLE producto
ADD stock INT DEFAULT 50;

/* Actualizar stock de todos los productos a 50 */
UPDATE producto
SET stock = 50;

/* VER LAS TABLAS */
SELECT * FROM producto;
SELECT * FROM distribuidor;
