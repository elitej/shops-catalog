package ru.testwork.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.testwork.exception.ShopNotFoundException;
import ru.testwork.model.Shop;
import ru.testwork.service.ShopService;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private static Logger logger = LoggerFactory.getLogger(ShopController.class);

    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/{shopId}")
    public String showShopById(@PathVariable long shopId, Model model) {
        logger.debug("showShopById is executed, shopId = \"{}\"", shopId);

        Shop shop = shopService.findById(shopId, true);
        model.addAttribute("shop", shop);
        model.addAttribute("products", shop.getProducts());
        return "shop";
    }

    @ExceptionHandler(ShopNotFoundException.class)
    public String handleShopNotFoundException(ShopNotFoundException e, Model model) {
        logger.warn("shop with id = \"{}\" not found", e.getId());

        model.addAttribute("message", e.getMessage());
        return "error/404";
    }

}
