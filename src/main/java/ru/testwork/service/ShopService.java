package ru.testwork.service;

import org.springframework.data.domain.Page;
import ru.testwork.model.Shop;

import java.util.List;

public interface ShopService {

    Shop findById(long id, boolean fetchEagerly);

    List<Shop> findByName(String name);

    Page<Shop> findForPage(int pageNumber);

}
