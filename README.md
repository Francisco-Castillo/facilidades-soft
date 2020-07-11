# Facsoft
[![Captura-de-pantalla-de-2020-07-11-20-10-40.png](https://i.postimg.cc/T2jyZPkB/Captura-de-pantalla-de-2020-07-11-20-10-40.png)](https://postimg.cc/Bt6QFJfC)
Aplicación Jakarta EE con Maven y Primefaces.
## Pre-requisitos :pushpin:

* Instalar [AdoptOpenJDK 8](https://adoptopenjdk.net/)
* Instalar [Git](https://git-scm.com/)
* Instalar [Maven](https://maven.apache.org/)
* Instalar [Payara Server 5.x](https://www.payara.fish/products/downloads/#community)
* Instalar [MariaDB](https://mariadb.com/)
* Instalar [Conector J 2.6](https://downloads.mariadb.org/connector-java/) - Este conector es un archivo con extension .jar, que una vez descargado se debe copiar dentro del directorio (/payara/glassfish/domains/domain1/lib) de payara server.

## Clonar proyecto

Abrir una terminal/consola y clonar el repositorio

```
git clone https://github.com/Francisco-Castillo/facilidades-soft/
```
## Crear base de datos

Una vez clonado el proyecto, se debe crear la base de datos facsoftdb:

```
CREATE DATABASE facsoftdb;
```

## Editar archivo [`flyway.properties`](src/main/resources/flyway.properties)

```
jdbc.url=jdbc:mariadb://localhost:3306/facsoftdb
jdbc.user=
jdbc.password=
```
## Editar archivo [`config.properties`](src/main/resources/config.properties)

```
uploads=
noimage=
directorio.reportes=
directorio.imagenes=
imagen.logofacsoft=
ruta.plantilla=
direccion.remitente=
asunto=
```
## Editar archivo [`jasper.properties`](src/main/resources/jasper.properties)
```
directorio.origen=
directorio.destino=
```

## Ejecutar migraciones :scroll:

Ejecute una terminal y ubiquese en la raiz del proyecto. Allí ejecute los siguientes comandos:

Para consultar las migraciones disponibles:
```
mvn initialize flyway:info
```
Para migrar:

```
mvn initialize flyway:migrate
```

Si hubo algun error durante la migración, ejecute el siguiente comando:

```
mvn initialize flyway:clean
```
## Levantar Payara Server :rocket:

```
cd directorio/instalacion/payaraServer/bin/
```
En linux

```
./asadmin start-domain
```
## Crear pool de conexiones y recurso JDBC
Para que la aplicación pueda funcionar es necesario crear un datasource, más conocido como pool de conexiones. Para ello, colocar en el navegador web la siguiente dirección: (http://localhost:4848) que mostrará el panel de administración de Payara.

* **Pool de conexiones**
  * 1 - Nuevo...
  * 2 - Nombre de Pool: *facsoftPool*
  * 3 - Tipo de recurso : *java.sql.Driver*
  * 4 - Click en siguiente
  * 5 - Driver Classname:  *org.mariadb.jdbc.Driver*
  * 6 - Agregar las siguientes propiedades adicionales: 
    * 6.1 - serverName : *localhost*
    * 6.2 - databaseName : *facsoftdb*
    * 6.3 - URL : *jdbc:mariadb://localhost:3306/facsoftdb*
    * 6.4 - user: *nombreDeUsuario*
    * 6.5 - password: *password*
    
Una vez finalizada la creación del pool de conexiones, se debe realizar un "ping" para comprobar que el proceso se haya realizado de manera exitosa.

Para ello haga clic en el botón con la leyenda "Ping" que se encuentra dentro de la pestaña general 
    
* **Recurso JDBC**
  * 1 - Nuevo...
  * 2 - Nombre JNDI :     *jdbc/facsoftdb*
  * 3 - Nombre Pool : *facsoftPool*
  * 4 - Click en aceptar
  
## Compilar e instalar dependencias :wrench:

Abrir una terminal y ubicarse en la raiz del proyecto. Alli ingresar el siguiente comando.

```
mvn clean install
```
se creará un directorio target con el archivo *facilidades-soft.war*
  
## Desplegar *.war*
Abrir una terminal y ubicarse en el directorio bin de payara.
```
cd directorio/payara/bin/
./asadmin deploy /directorio/proyecto/target/facilidades-soft.war
```
## Probar aplicación :earth_americas:
Abrir un navegador web e ingresar la siguiente dirección: http://localhost:8080/facilidades-soft/

## Autor :black_nib:
- Francisco Castillo - cefrancastillo@gmail.com

