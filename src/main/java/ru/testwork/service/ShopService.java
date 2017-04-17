package ru.testwork.service;

import org.springframework.data.domain.Page;
import ru.testwork.model.Shop;

import java.util.List;

public interface ShopService {

    Shop findById(long id, boolean fetchEagerly);

    Page<Shop> findByName(String name, int pageNumber);

    Page<Shop> findForPage(int pageNumber);

}
