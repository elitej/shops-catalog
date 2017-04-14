package ru.testwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testwork.exception.ProductNotFoundException;
import ru.testwork.model.Product;
import ru.testwork.repository.ProductRepository;
import ru.testwork.service.ProductService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private static final int PAGE_SIZE = 10;

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(long id) {
        return checkForNullItem(productRepository.findOne(id), id);
    }

    @Override
    public List<Product> findForPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "name");
        return productRepository.findAll(request).getContent();
    }

    private Product checkForNullItem(Product product, long id) {
        if (product == null)
            throw new ProductNotFoundException(id);
        return product;
    }

}
