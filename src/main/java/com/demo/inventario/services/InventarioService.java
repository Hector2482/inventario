package com.demo.inventario.services;

import com.demo.inventario.entities.InventarioEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InventarioService {

    private List<InventarioEntity> productos;

    public InventarioService() {
        // Se crea una lista para almacenar los productos
        productos = new ArrayList<>();
        productos.add(new InventarioEntity(UUID.randomUUID(), "Horno microondas", "Electrodomésticos", 450000, 20));
        productos.add(new InventarioEntity(UUID.randomUUID(), "Aspiradora de mano", "Electrodomésticos", 200000, 15));
        productos.add(new InventarioEntity(UUID.randomUUID(), "Plancha a vapor", "Electrodomésticos", 300000, 25));
        productos.add(new InventarioEntity(UUID.randomUUID(), "Auriculares inalámbricos", "Electrónica", 150000, 20));
        productos.add(new InventarioEntity(UUID.randomUUID(), "Smartwatch", "Electrónica", 300000, 10));
        productos.add(new InventarioEntity(UUID.randomUUID(), "Raqueta de tenis", "Deportes", 120000, 10));
        productos.add(new InventarioEntity(UUID.randomUUID(), "Bicicleta de montaña", "Deportes", 900000, 5));
    }

    // Retorna todos los productos
    public List<InventarioEntity> getAllProducts() {
        return productos;
    }

    // Retorna el producto por su ID
    public InventarioEntity getProductById(UUID id) {
        return productos.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }

    // Crea un producto con un ID
    public UUID createProduct(InventarioEntity product) {
        product.setId(UUID.randomUUID());
        productos.add(product);
        return product.getId();
    }

    // Actualiza los productos
    public Optional<InventarioEntity> updateProduct(UUID id, InventarioEntity updateProduct) {
        Optional<InventarioEntity> existingProduct = productos.stream().filter(product -> product.getId().equals(id)).findFirst();
        if (existingProduct.isPresent()) {
            InventarioEntity product = existingProduct.get();
            product.setName(updateProduct.getName());
            product.setCategory(updateProduct.getCategory());
            product.setPrice(updateProduct.getPrice());
            product.setStock(updateProduct.getStock());
            return Optional.of(product);
        }
        return Optional.empty();
    }

    // Elimina los productos
    public boolean deleteProductById(UUID id) {
        return productos.removeIf(product -> product.getId().equals(id));
    }
}
