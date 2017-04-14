package ru.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.testwork.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
