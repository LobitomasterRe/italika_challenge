package com.italika.challenge.domain.port;

import com.italika.challenge.domain.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {
    void insertar(Producto producto);
    void actualizar(Producto producto);
    Optional<Producto> obtenerPorId(Integer id);
    List<Producto> listarTodos();
    void eliminar(Integer id);
    void bloquear(Integer id);
}