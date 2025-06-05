package com.italika.challenge.domain.exception;

public class ProductoNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductoNotFoundException(Integer id) {
        super("Producto con ID " + id + " no encontrado");
    }
}
