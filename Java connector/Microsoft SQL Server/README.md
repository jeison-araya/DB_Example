> # Conexión entre Java y Bases de datos en Microsoft SQL Server
> ## Requerimientos
> - Tener una base de datos.
> - Instalar el conector de Microsoft SQL Server para Java.
---
> # Instalar conector de Java. 
> Se puede descargar e instalar la librería o agregar la dependencia si se usa un proyecto con Maven.

> ## Instalar depedencia Maven. 
> 1. Ingresar a https://mvnrepository.com/artifact/mysql/mysql-connector-java
> 2. Copiar la dependecia en el .pom del proyecto
>
>        ```xml
>        <dependency>
>            <groupId>mysql</groupId>
>            <artifactId>mysql-connector-java</artifactId>
>            <version>8.0.20</version>
>        </dependency>
>        ```

> # Código en Java.
> - Crear un nuevo proyecto o seleccionar uno exisitente.
> 