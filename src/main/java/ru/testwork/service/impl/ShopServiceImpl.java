package ru.testwork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testwork.exception.ShopNotFoundException;
import ru.testwork.model.Product;
import ru.testwork.model.Shop;
import ru.testwork.repository.ShopRepository;
import ru.testwork.service.ShopService;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private static final int PAGE_SIZE = 10;

    private ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Transactional
    @Override
    public Shop findById(long id, boolean fetchEagerly) {
        Shop shop = shopRepository.findOne(id);
        checkForNullShop(shop, id);
        if (fetchEagerly) {
            shop.getProducts().size();
        }
        return shop;
    }

    @Override
    public List<Shop> findByName(String name) {
        PageRequest request = new PageRequest(0, PAGE_SIZE, Sort.Direction.ASC, "id");
        return shopRepository.findByName(name, request);
    }

    @Override
    public Page<Shop> findForPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id");
        return shopRepository.findAll(request);
    }

    private Shop checkForNullShop(Shop shop, long id) {
        if (shop == null)
            throw new ShopNotFoundException(id);
        return shop;
    }
}
