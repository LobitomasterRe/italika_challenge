package com.italika.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.italika.challenge.domain.model.Producto;
import com.italika.challenge.domain.model.Usuario;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProductoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpEntity<String> entity;

    @BeforeEach
    void setUp() {
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword("admin123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Usuario> loginRequest = new HttpEntity<>(usuario, headers);

        ResponseEntity<String> loginResponse = restTemplate.postForEntity(
                "/api/auth/login",
                loginRequest,
                String.class
        );

        String jwt = loginResponse.getBody();

        entity = new HttpEntity<>(null, createHeaders(jwt));
    }


    private HttpHeaders createHeaders(String jwt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwt);
        return headers;
    }

    @Test
    void listarProductos() {
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/productos", HttpMethod.GET, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void crearProducto() {
        Producto producto = new Producto(null, "Test", "Producto Test", 99.99, 10, false);
        HttpEntity<Producto> request = new HttpEntity<>(producto, entity.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(
                "/api/productos", HttpMethod.POST, request, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void obtenerProductoPorId() {
        int id = 7;
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/productos/" + id, HttpMethod.GET, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void actualizarProducto() {
        Producto producto = new Producto(7, "Mouse Actualizado", "Mouse óptico", 89.99, 5, false);
        HttpEntity<Producto> request = new HttpEntity<>(producto, entity.getHeaders());

        ResponseEntity<String> response = restTemplate.exchange(
                "/api/productos/7", HttpMethod.PUT, request, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void bloquearProducto() {
        int id = 7;
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/productos/bloquear/" + id, HttpMethod.PUT, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void eliminarProducto() {
        int id = 5;
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/productos/" + id, HttpMethod.DELETE, entity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
