package ru.testwork.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.testwork.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Page<Shop> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
