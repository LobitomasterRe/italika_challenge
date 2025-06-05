package com.italika.challenge.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa un producto del sistema")
public class Producto {

    @Schema(description = "Identificador único del producto", example = "1")
    private Integer id;

    @Schema(description = "Nombre del producto", example = "Teclado mecánico RGB")
    private String nombre;

    @Schema(description = "Descripción del producto", example = "Teclado con retroiluminación y switches azules")
    private String descripcion;

    @Schema(description = "Precio del producto", example = "159.99")
    private Double precio;

    @Schema(description = "Cantidad en stock", example = "25")
    private Integer cantidad;

    @Schema(description = "Estado de bloqueo del producto", example = "false")
    private Boolean bloqueado;

    public Producto() {
    }

    public Producto(Integer id, String nombre, String descripcion, Double precio, Integer cantidad, Boolean bloqueado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.bloqueado = bloqueado;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}
