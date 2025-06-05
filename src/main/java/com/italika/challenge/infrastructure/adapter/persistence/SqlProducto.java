package com.italika.challenge.infrastructure.adapter.persistence;

public class SqlProducto {
    public static final String SP_INSERTAR = "CALL sp_insert_producto(?, ?, ?, ?)";
    public static final String SP_ACTUALIZAR = "CALL sp_update_producto(?, ?, ?, ?, ?)";
    public static final String SP_OBTENER_POR_ID = "SELECT * FROM productos WHERE id = ?";
    public static final String SP_LISTAR_TODOS = "SELECT * FROM productos";
    public static final String SP_ELIMINAR = "DELETE FROM productos WHERE id = ?";
    public static final String SP_BLOQUEAR = "UPDATE productos SET bloqueado = NOT bloqueado WHERE id = ?";
}