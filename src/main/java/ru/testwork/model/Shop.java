package ru.testwork.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shop_id")
    private Long id;

    @Column(name = "shop_name", nullable = false)
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "shop_city")),
            @AttributeOverride(name = "street", column = @Column(name = "shop_street"))
    })
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shops_products",
            joinColumns = @JoinColumn(name = "shop_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Shop() {
    }

    public Shop(Long id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }
}

