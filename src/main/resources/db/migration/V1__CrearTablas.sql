/**
 * Author:  fcastillo
 * Created: 27/12/2019
 */

CREATE TABLE Personas(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  apellido VARCHAR(45) NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  ndocumento VARCHAR(15) NOT NULL,
  fnacimiento DATE,
  sexo INT NOT NULL DEFAULT 0,
  email VARCHAR(50),
  telefono VARCHAR(20),
  imagen VARCHAR(50)
);

CREATE TABLE Clientes(
  id INT NOT NULL PRIMARY KEY,
  codigo VARCHAR(15),
  falta DATETIME,
  estado INT NOT NULL
);

CREATE TABLE Usuarios(
  id INT NOT NULL PRIMARY KEY,
  username VARCHAR(15) NOT NULL,
  password VARCHAR(255) NOT NULL,
  estado INT NOT NULL,
  falta DATE NOT NULL,
  fultimologin DATETIME,
  idtipousuario INT NOT NULL
);

CREATE TABLE Perfiles(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  descripcion VARCHAR(20)
);

CREATE TABLE Prestamos(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  dineroprestado DECIMAL(7,2) NOT NULL,
  fsolicitud DATE NOT NULL,
  cantidadcuotas INT NOT NULL,
  tna DECIMAL(7,2),
  gastosadmin DECIMAL(3,2),
  segvida DECIMAL(3,2),
  iva DECIMAL(3,2),
  observaciones VARCHAR(400), 
  estado INT NOT NULL,
  idcliente INT NOT NULL,
  idoperador INT NOT NULL
);

CREATE TABLE Cuotas(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nro INT NOT NULL,
  valor DECIMAL(7,2) NOT NULL,
  fvencimiento DATE NOT NULL,
  estado INT NOT NULL,
  idprestamo INT NOT NULL
);

CREATE TABLE Pagos(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  fechapago DATE NOT NULL,
  idprestamo INT NOT NULL
);

CREATE TABLE DetallePagos(
  idpago INT NOT NULL,
  idcuota INT NOT NULL,
  recargo DECIMAL (7,2),
  montoabonado DECIMAL(7,2)
);



