# Comandos para MySQL 
El nombre de la base de datos para este ejemplo es 'tienda'.
## Base de Datos [BD]

### Crear BD
    # Crear bases de datos.
    create database tienda;

### Seleccionar BD.
    # Seleccionar la base de datos a trabajar
    use tienda;
    
### Eliminar BD
    # Eliminar DB
     database tienda;
     
## Tablas

### Agregar tabla 
    # Creando una tabla para la BD seleccionada.
    create table producto(
        IdProducto int auto_increment primary key,
        nombre varchar(50) not null,
        precio decimal(10, 2) not null,
        fechaVenta date default '0000-00-00'
    );
    
## Mostrar una tabla
    # Mostrar  tabla
    show columns from producto;
    
## Eliminar tabla
    # Eliminar tabla
    drop table producto;
    
# Registros

## Agregar registro
    # Agregar un registro a la tabla producto.
    insert into producto values(null, "Zapatos", 12500, null);
    
    # Agregar un registro a la tabla producto en columnas específicas.
    insert into producto (nombre, precio) values ("Camisas", 10000);
    
    # Agregar varios registros en la tabala producto a columnas específicas. 
    insert into producto (nombre, precio) values 
    ("Pantalones", 16500), ("Trajes", 25000), ("Accesorios", 5000);
    
    
## Modificar registro
    # Modificar el precio de un registo en la tabla producto.
    update producto set precio = 11000 where idProducto = 2;
    # Modificar valores de varias columnas de un registro.
    update producto set nombre = "Zapatos de vestir", precio = 15000 where idProducto = 1;

## Eliminar registro
    # Eliminar un registro
    delete from producto where idProducto =  5;
    
#### Nota
El 'where' es muy importante, ya que si no se específica, elimina todos los registros en la tabla.
 
## Mostrar registros
    # Mostrar todos los registros de la tabla producto.
    select * from producto;
    
    # Mostrar una columna de una tabla.
    select nombre from producto;
    
    # Mostrar varias columnas de una tabla.
    select nombre, precio from producto;
    
    # Mostrar columna con un encabezado temporal.
    select precio as 'Precio del producto' from producto;
    
    # Mostrar registros específicos agregando un filtro.
    select * from producto where precio > 15000;
    
    # Mostrar registros con precio mínimo y máximo.
    select min(precio), max(precio) from producto; 
    
    # Mostrar registros de forma ordenada. [Ordenar por nombre]
    select * from producto order by nombre asc;