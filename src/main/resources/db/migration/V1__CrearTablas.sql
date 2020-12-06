/**
 * Author:  fcastillo
 * Created: 27/12/2019
 */

CREATE TABLE personas(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  apellido VARCHAR(45) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  numero_documento VARCHAR(15) NOT NULL,
  fecha_nacimiento DATE,
  sexo INT NOT NULL DEFAULT 0,
  email VARCHAR(50),
  telefono VARCHAR(20),
  imagen VARCHAR(50)
);

CREATE TABLE clientes(
  id INT NOT NULL PRIMARY KEY,
  codigo VARCHAR(15),
  fecha_alta DATETIME,
  estado INT NOT NULL
);

CREATE TABLE usuarios(
  id INT NOT NULL PRIMARY KEY,
  username VARCHAR(15) NOT NULL,
  password VARCHAR(255) NOT NULL,
  estado INT NOT NULL,
  fecha_alta DATE NOT NULL,
  fecha_ultimo_login DATETIME,
  id_tipo_usuario INT NOT NULL
);

CREATE TABLE perfiles(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  descripcion VARCHAR(20)
);

CREATE TABLE prestamos(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  dinero_prestado DECIMAL(7,2) NOT NULL,
  fecha_solicitud DATE NOT NULL,
  cantidad_cuotas INT NOT NULL,
  tna DECIMAL(7,2),
  gastos_admin DECIMAL(3,2),
  seguro_vida DECIMAL(3,2),
  iva DECIMAL(3,2),
  observaciones VARCHAR(400), 
  estado INT NOT NULL,
  id_cliente INT NOT NULL,
  id_operador INT NOT NULL
);

CREATE TABLE cuotas(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nro INT NOT NULL,
  valor DECIMAL(7,2) NOT NULL,
  fecha_vencimiento DATE NOT NULL,
  estado INT NOT NULL,
  id_prestamo INT NOT NULL
);

CREATE TABLE pagos(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  fecha DATE NOT NULL,
  id_prestamo INT NOT NULL
);

CREATE TABLE detalle_pagos(
  id_pago INT NOT NULL,
  id_cuota INT NOT NULL,
  recargo DECIMAL (7,2),
  monto_abonado DECIMAL(7,2)
);



