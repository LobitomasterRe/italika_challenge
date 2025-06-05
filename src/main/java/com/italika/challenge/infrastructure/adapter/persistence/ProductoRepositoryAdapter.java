package com.italika.challenge.infrastructure.adapter.persistence;

import com.italika.challenge.domain.model.Producto;
import com.italika.challenge.domain.port.ProductoRepositoryPort;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final JdbcTemplate jdbcTemplate;

    public ProductoRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertar(Producto producto) {
        try {
            jdbcTemplate.update(SqlProducto.SP_INSERTAR,
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getCantidad()
            );
        } catch (DataAccessException e) {
            throw new IllegalStateException("Error al insertar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public void actualizar(Producto producto) {
        try {
            jdbcTemplate.update(SqlProducto.SP_ACTUALIZAR,
                    producto.getId(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecio(),
                    producto.getCantidad()
            );
        } catch (DataAccessException e) {
            throw new IllegalStateException("Error al actualizar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Producto> obtenerPorId(Integer id) {
        try {
            List<Producto> resultados = jdbcTemplate.query(SqlProducto.SP_OBTENER_POR_ID,
                    new Object[]{id}, new ProductoRowMapper());
            return resultados.stream().findFirst();
        } catch (DataAccessException e) {
            throw new IllegalStateException("Error al obtener producto por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Producto> listarTodos() {
        try {
            return jdbcTemplate.query(SqlProducto.SP_LISTAR_TODOS, new ProductoRowMapper());
        } catch (DataAccessException e) {
            throw new IllegalStateException("Error al listar productos: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            jdbcTemplate.update(SqlProducto.SP_ELIMINAR, id);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Error al eliminar producto: " + e.getMessage(), e);
        }
    }

    @Override
    public void bloquear(Integer id) {
        try {
            jdbcTemplate.update(SqlProducto.SP_BLOQUEAR, id);
        } catch (DataAccessException e) {
            throw new IllegalStateException("Error al bloquear producto: " + e.getMessage(), e);
        }
    }

    static class ProductoRowMapper implements RowMapper<Producto> {
        @Override
        public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidad"),
                    rs.getBoolean("bloqueado")
            );
        }
    }
}