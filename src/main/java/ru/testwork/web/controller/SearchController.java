package ru.testwork.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.testwork.model.Shop;
import ru.testwork.service.ShopService;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private static Logger logger = LoggerFactory.getLogger(SearchController.class);

    private ShopService shopService;

    @Autowired
    public SearchController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public String showShopByName(@RequestParam("shop_name") String shopName,
                                 @RequestParam("num") int pageNumber, Model model) {
        logger.debug("showShopByName is executed, shopName = \"{}\"", shopName);

        Page<Shop> page = shopService.findByName(shopName, pageNumber);
        int current = page.getNumber() + 1;

        model.addAttribute("shops", page.getContent());
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPages", page.getTotalPages());
        return "search";
    }
}
