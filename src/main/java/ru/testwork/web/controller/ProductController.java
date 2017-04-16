package ru.testwork.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.testwork.exception.ProductNotFoundException;
import ru.testwork.model.Product;
import ru.testwork.model.Shop;
import ru.testwork.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public String showProductById(@PathVariable long productId, Model model) {
        logger.debug("showProductById is executed, productId = \"{}\"", productId);

        Product product = productService.findById(productId, true);
        model.addAttribute("product", product);
        model.addAttribute("shops", product.getShops());
        return "product";
    }



    @ExceptionHandler(ProductNotFoundException.class)
    public String handleItemNotFoundException(ProductNotFoundException e, Model model) {
        logger.warn("product with id = \"{}\" not found", e.getId());

        model.addAttribute("message", e.getMessage());
        return "error/404";
    }
}
