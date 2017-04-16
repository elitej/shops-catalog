package ru.testwork.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.testwork.model.Shop;
import ru.testwork.service.ShopService;

@Controller
@RequestMapping("/shops")
public class ShopsCatalogController {

    private static Logger logger = LoggerFactory.getLogger(ShopsCatalogController.class);

    private ShopService shopService;

    @Autowired
    public ShopsCatalogController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/{pageNumber}")
    public String showShopsForPage(@PathVariable int pageNumber, Model model) {
        logger.debug("showShopsForPage is executed, pageNumber = \"{}\"", pageNumber);

        Page<Shop> page = shopService.findForPage(pageNumber);
        int current = page.getNumber() + 1;

        model.addAttribute("shops", page.getContent());
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPages", page.getTotalPages());

        return "shops";
    }
}
