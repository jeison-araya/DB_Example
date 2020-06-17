# Conexión entre Java y Bases de datos en MySQL
## Requerimientos
- Instalar el conector de MySQL para Java.

# Instalar conector de Java. 
Se puede descargar e instalar la librería o agregar la dependencia si se usa un proyecto con Maven.
## Mediante .jar
- Descarga
    1. Ingresar a https://dev.mysql.com/downloads/connector/j/
    2. Selecionar plataforma independiente.
    3. Descargar .zip
- Instalación
    1. Crear un directorio en un lugar seguro, ya que no se debe eliminar.
    2. Copiar el .jar que contiente el archivo .zip previamente descargado.
## Mediante depedencia Maven.
- Buscar la versión    
    1. Ingresar a https://mvnrepository.com/artifact/mysql/mysql-connector-java
    2. Copiar la dependecia en el .pom del proyecto

        ```xml
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.20</version>
        </dependency>
        ```



