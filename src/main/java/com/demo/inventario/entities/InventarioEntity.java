package com.demo.inventario.entities;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Se establece la entidad del producto
public class InventarioEntity {
    private UUID id;
    private String name;
    private String category;
    private double price;
    private int stock;
}