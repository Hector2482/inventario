package com.demo.inventario.controllers;

import com.demo.inventario.entities.InventarioEntity;
import com.demo.inventario.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/apirest/v1/products")
public class InventarioController {

    private final InventarioService inventarioService;

    @Autowired
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // Método para obtener todos los productos
    @GetMapping
    public List<InventarioEntity> getAllProducts() {
        return inventarioService.getAllProducts();
    }

    // Método para crear un nuevo producto
    @PostMapping
    public ResponseEntity<String> addNewProduct(@RequestBody InventarioEntity product) {
        UUID newProductId = inventarioService.createProduct(product);
        return ResponseEntity.created(URI.create("/apirest/v1/products/" + newProductId))
                .body("Nuevo producto creado con ID: " + newProductId);
    }

    // Método para actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<String> modifyProduct(@PathVariable UUID id, @RequestBody InventarioEntity product) {
        Optional<InventarioEntity> updatedProduct = inventarioService.updateProduct(id, product);
        if (updatedProduct.isPresent()) {
            return ResponseEntity.ok("Producto actualizado");
        }
        return ResponseEntity.notFound().build();
    }

    // Método para eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        if (inventarioService.deleteProductById(id)) {
            return ResponseEntity.ok("Producto Eliminado");
        }
        return ResponseEntity.notFound().build();
    }
}

