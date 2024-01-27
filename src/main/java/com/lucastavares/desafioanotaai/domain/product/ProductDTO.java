package com.lucastavares.desafioanotaai.domain.product;

public record ProductDTO(String id, String title, String description, String ownerId, Integer price, String categoryId) {
}
