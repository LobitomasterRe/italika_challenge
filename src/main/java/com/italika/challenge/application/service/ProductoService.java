package com.italika.challenge.application.service;

import com.italika.challenge.domain.model.Producto;
import com.italika.challenge.domain.port.ProductoRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepositoryPort repository;

    public ProductoService(ProductoRepositoryPort repository) {
        this.repository = repository;
    }

    public void crear(Producto producto) {
        repository.insertar(producto);
    }

    public void actualizar(Producto producto) {
        validarExistencia(producto.getId());
        repository.actualizar(producto);
    }

    public Producto obtener(Integer id) {
        return validarExistencia(id);
    }

    public List<Producto> listar() {
        return repository.listarTodos();
    }

    public void eliminar(Integer id) {
        validarExistencia(id);
        repository.eliminar(id);
    }

    public void bloquear(Integer id) {
        validarExistencia(id);
        repository.bloquear(id);
    }

    private Producto validarExistencia(Integer id) {
        return repository.obtenerPorId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto con ID " + id + " no encontrado"));
    }
}
