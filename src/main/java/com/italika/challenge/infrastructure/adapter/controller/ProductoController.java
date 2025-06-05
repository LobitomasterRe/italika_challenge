package com.italika.challenge.infrastructure.adapter.controller;

import com.italika.challenge.application.service.ProductoService;
import com.italika.challenge.domain.model.Producto;
import com.italika.challenge.infrastructure.dto.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con la gestión de productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public ResponseEntity<ApiResponse> crear(@RequestBody Producto producto) {
        productoService.crear(producto);
        ApiResponse response = new ApiResponse(201, "Producto registrado correctamente");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> actualizar(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setId(id);
        productoService.actualizar(producto);
        ApiResponse response = new ApiResponse(200, "Producto actualizado correctamente");
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Bloquear o desbloquear un producto")
    @PutMapping("/bloquear/{id}")
    public ResponseEntity<Void> bloquear(@PathVariable Integer id) {
        productoService.bloquear(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Obtener un producto por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(productoService.obtener(id));
    }

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listar());
    }

    @Operation(summary = "Eliminar un producto por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
