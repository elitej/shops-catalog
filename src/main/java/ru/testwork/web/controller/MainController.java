package ru.testwork.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.testwork.model.Shop;
import ru.testwork.service.ShopService;

@Controller
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    private ShopService shopService;

    @Autowired
    public MainController(ShopService shopService) {
        this.shopService = shopService;
    }

    // TODO: 14.04.2017 write home.jsp
    @GetMapping("/")
    public String showHomePage() {
        return "redirect:shops/1";
    }

    @GetMapping("shops/{pageNumber}")
    public String showShopsForPage(@PathVariable int pageNumber, Model model) {
        logger.debug("showShopsForPage is executed, pageNumber = \"{}\"", pageNumber);

        Page<Shop> page = shopService.findForPage(pageNumber);
        int current = page.getNumber() + 1;

        model.addAttribute("shops", page.getContent());
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPages", page.getTotalPages());

        return "main";
    }

}
