> # Comandos para MySQL 
> El nombre de la base de datos para este ejemplo es '2020_IF-3100_B00000'.

> # Base de Datos [BD] CRUD
> ---
> ## ¿Qué es CRUD?
> En informática, CRUD es el acrónimo de "Crear, Leer, Actualizar y Borrar", que se usa para referirse a las funciones básicas en bases de datos o la capa de persistencia en un > software.
>---
> ## Crear bases de datos.
>       # Crear bases de datos.
>       CREATE DATABASE 2020_IF-3100_B00000;
>
> ## Seleccionar BD.
>       # Seleccionar la base de datos a trabajar
>       USE 2020_IF-3100_B00000
>    
> ## Eliminar BD
>       # Eliminar DB
>       DROP DATABASE 2020_IF-3100_B00000

> # Tablas
> ---
> ## Crear tabla
>        CREATE TABLE courses (
>               course_id VARCHAR(06) PRIMARY KEY, 
>               name VARCHAR(70)
>               )
>                
>        CREATE TABLE students (
>		        id int PRIMARY KEY NOT NULL IDENTITY, 
>		        institutionalId VARCHAR(06), name VARCHAR(30), 
>		        phone VARCHAR(20), 
>		        course_id VARCHAR(06) REFERENCES courses(course_id)
>	            )
> ---   
> ## Mostrar tabla
>
>       # Mostrar una columna de una tabla.
>       SELECT name FROM students
>    
>       # Mostrar varias columnas de una tabla.
>       SELECT name, institutionalId FROM students
>    
>       # Mostrar columna con un encabezado temporal.
>       SELECT name AS 'Nombre del estudiante' FROM students
>    
>       # Mostrar registros de forma ordenada. [Ordenar por identificador institucional ascendentemente]
>       SELECT * FROM students ORDER BY institutionalId ASC
>
>       # Mostrar registros de forma ordenada. [Ordenar por identificador institucional descendentemente]
>       SELECT * FROM students ORDER BY institutionalId ASC
>
>       # Mostrar todas las columas de la tabla "courses".
>       SELECT * FROM students WHERE institutionalId LIKE '%B91'
>
>       # Mostrar todas las columas de la tabla "courses".
>       SELECT * FROM courses
>
>       # Mostrar todas las columas de la tabla "students".
>       SELECT * FROM students
> 
>       # Mostrar varias tablas 
>       SELECT s.name AS Student, c.name Course 
>           FROM students s, courses c
>           WHERE s.course_id = c.course_id
>           AND c.name = 'Algoritmos y Estructuras de Datos'
> ---    
> ## Eliminar tabla
>       # Eliminar la tabla "courses".
>       DROP TABLE courses
>        
>       # Eliminar la tabla "students".
>       DROP TABLE students
>    
> # Registros
> ---
> ## Agregar registro
>       # Agregar un registro a la tabla "students" en la columna específica "name".
>       INSERT INTO students (name) VALUES ('Juan Rafael Mora')
>
>       # Agregar varios registros en la tabla "students".
>       INSERT INTO students (institutionalId, name, phone, course_id) VALUES
>           ('B90127', 'Juana Pereira' ,'8888-8888', 'IF3001'),
>           ('B89111', 'Juan Santamaría', '8888-8888', 'IF3100'),
>           ('B90456', 'William Walker' ,'8888-8888', 'IF3100'),
>           ('B90888', 'Juan Pereira' ,'8888-8888', 'IF1400'),
>           ('B89988', 'Francisca Carrasco', '8888-8888', 'IF3001'),
>           ('B89141', 'Franklin Chang', '8888-8888', 'IF3001')
>
>       # Agregar varios registros en la tabla "courses".
>       INSERT INTO courses (course_id, name) VALUES 
>           ('IF3001', 'Algoritmos y Estructuras de Datos'),
>           ('IF3000', 'Programación II'),
>           ('MA0321', 'Cálculo Diferencial e Integral')
>
> ---
> ## Modificar registro
>       # Modificar el "nombre" de un registo mediante el "institutionalID" en la tabla "students".
>       UPDATE students SET name = 'Juana Pereira' WHERE institutionalId = 'B90127'
>
>       # Modificar el varios registros en la tabla "students".
>       UPDATE students SET name = 'Juana Pereira Remix', phone = '1111-1111' WHERE institutionalId = 'B90127'
> ---
> ## Eliminar registro
>       # Eliminar un registro
>       DELETE FROM students WHERE institutionalId = 'B90127'
>    
> #### Nota
> El 'WHERE' es muy importante, ya que si no se especifica, elimina todos los registros en la tabla.
