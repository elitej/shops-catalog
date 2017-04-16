package ru.testwork.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @ManyToMany(mappedBy = "products")
    private List<Shop> shops;

    public Product() {
    }

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(Long id, String name, List<Shop> shops) {
        this.id = id;
        this.name = name;
        this.shops = shops;
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

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public List<Shop> getShops() {
        return shops;
    }

}
