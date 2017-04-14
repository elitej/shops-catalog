package ru.testwork.service;


import ru.testwork.model.Product;

import java.util.List;

public interface ProductService {

    Product findById(long id);

    List<Product> findForPage(int pageNumber);
}
