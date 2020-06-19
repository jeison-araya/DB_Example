 # Comandos para Microsoft SQL Server Management Studio 
> El nombre de la Base de Datos para este ejemplo es '2020_IF-3100_TALLER'.
>
> 
 #  CRUD
> En informática, CRUD es el acrónimo de "Crear, Leer, Actualizar y Borrar", que se usa para referirse a las funciones básicas en bases de datos o la capa de persistencia en un > software.
>
>
 # Base de Datos 
>
> ## Crear Base de Datos.
> ``` sql
>       CREATE DATABASE 2020_IF-3100_TALLER
> ```
> ## Seleccionar Base de Datos.
> ``` sql
>       USE 2020_IF-3100_B00000
> ```
>    
> ## Eliminar Base de Datos
> ``` sql
>       DROP DATABASE 2020_IF-3100_B00000
> ```
>
 # TABLAS
> 
>
> ## Leer tabla
> ``` sql
>       -- Leer columna de una tabla.
>       SELECT name FROM studentsU
>    
>       -- Leer varias columnas de una tabla.
>       SELECT name, institutionalId FROM studentsU
>    
>       -- Leer columna con un encabezado temporal.
>       SELECT name AS 'Nombre del estudiante' FROM studentsU
>    
>       -- Leer registros de forma ordenada. [Ordenar por institutionalId ascendentemente]
>       SELECT * FROM studentsU ORDER BY institutionalId ASC
>
>       -- Leer registros de forma ordenada. [Ordenar por institutionalId descendentemente]
>       SELECT * FROM studentsU ORDER BY institutionalId ASC
>
>       -- Leer todas las columas de la tabla "courses".
>       SELECT * FROM studentsU WHERE institutionalId LIKE '%B91'
>
>       -- Leer todas las columas de la tabla "courses".
>       SELECT * FROM courses
>
>       -- Leer todas las columas de la tabla "studentsU".
>       SELECT * FROM studentsU
> 
>       -- Leer varias tablas 
>       SELECT s.name AS Student, c.name Course 
>           FROM studentsU s, courses c
>           WHERE s.course_id = c.course_id
>           AND c.name = 'Algoritmos y Estructuras de Datos'
> ```
> 
> ## Actualizar tabla
> ``` sql
>       ALTER TABLE studentsU ALTER COLUMN institutionalId VARCHAR(20)
>       ALTER TABLE studentsU ALTER COLUMN institutionalId VARCHAR(06)
> ```
>
> ## Eliminar tabla
> ``` sql
>       -- Eliminar la tabla "courses".
>       DROP TABLE courses
>        
>       -- Eliminar la tabla "studentsU".
>       DROP TABLE studentsU
> ```
>  
 # REGISTRO
> 
> ## Insertar registro
> ``` sql
>       -- Insertar un registro a la tabla "studentsU" en la columna específica "name".
>       INSERT INTO studentsU (name) VALUES ('Juan Rafael Mora')
>
>       -- Insertar varios registros en la tabla "studentsU".
>       INSERT INTO studentsU (institutionalId, name, phone, course_id) VALUES
>           ('B90127', 'Juana Pereira' ,'8888-8888', 'IF3001'),
>           ('B92995', 'Daniel Salas', '8888-8888', 'IF3100'),
>           ('B89111', 'Juan Santamaría', '8888-8888', 'IF3100'),
>           ('B90456', 'William Walker' ,'8888-8888', 'IF3100'),
>           ('B90844', 'Juan Pereira' ,'8888-8888', 'IF1400'),
>           ('B89988', 'Francisca Carrasco', '8888-8888', 'IF3001'),
>           ('B89141', 'Franklin Chang', '8888-8888', 'IF3001'),
>           ('B55643', 'Keylor Navas', '8888-8888', 'IF3001'),
>           ('B13345', 'Chunche Montero', '8888-8888', 'IF3001'),
>           ('B56424', 'El Moradito', '8888-8888', 'IF3100'),
>           ('B89167', 'La Cegua', '8888-8888', 'IF3100')
>
>       -- Insertar varios registros en la tabla "courses".
>       INSERT INTO courses (course_id, name) VALUES 
>           ('IF3100', 'Introducción a los Sistemas de la Información'),
>           ('IF3001', 'Algoritmos y Estructuras de Datos'),
>           ('IF3000', 'Programación II'),
>           ('MA0321', 'Cálculo Diferencial e Integral')
> ```
>
> ## Actualizar registro
> ``` sql
>       -- Actualizar el "nombre" de un registo mediante el "institutionalID" en la tabla "studentsU".
>       UPDATE studentsU SET name = 'Juana Pereira' WHERE institutionalId = 'B90127'
>
>       -- Actualizar el varios registros en la tabla "studentsU".
>       UPDATE studentsU SET name = 'Juana Pereira Remix', phone = '1111-1111' WHERE institutionalId = 'B90127'
> ```
>
> ## Eliminar registro
> ``` sql
>       -- Eliminar un registro
>       DELETE FROM studentsU WHERE institutionalId = 'B90127'
> ```
>    
## Nota
> El 'WHERE' es muy importante, ya que si no se especifica, elimina todos los registros en la tabla.
