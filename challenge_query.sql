--SCRIPT PARA CREAR LA TABLA
CREATE TABLE productos (
    id serial PRIMARY KEY,
    nombre character varying(100),
    descripcion character varying,
    precio numeric(10,2),
    cantidad integer,
    bloqueado boolean default false
);

-- PROCEDIMIENTO PARA INSERTAR
CREATE OR REPLACE PROCEDURE sp_insert_producto(
    p_nombre character varying,
    p_descripcion character varying,
    p_precio double precision,
    p_cantidad integer
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO productos(nombre, descripcion, precio, cantidad)
    VALUES (p_nombre, p_descripcion, p_precio, p_cantidad);
END;
$$;

-- PROCEDIMIENTO PARA ACTUALIZAR
CREATE OR REPLACE PROCEDURE sp_update_producto(
    p_id integer,
    p_nombre character varying,
    p_descripcion character varying,
    p_precio double precision, 
    p_cantidad integer
)
LANGUAGE plpgsql
AS $$
BEGIN
    IF (SELECT bloqueado FROM productos WHERE id = p_id) THEN
        RAISE EXCEPTION 'Producto bloqueado';
    ELSE
        UPDATE productos
        SET nombre = p_nombre,
            descripcion = p_descripcion,
            precio = p_precio,
            cantidad = p_cantidad
        WHERE id = p_id;
    END IF;
END;
$$;
