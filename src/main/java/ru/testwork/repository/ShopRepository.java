package ru.testwork.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.testwork.model.Shop;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByName(String name, Pageable pageable);
}
