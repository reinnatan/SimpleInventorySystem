package testing.java.fx.testingjavafx.product.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String productsName;
    @Column(nullable = false)
    private String imageName;
    @Column(nullable = false)
    private String skuName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private float price;
    @Column(nullable = false)
    private float qty;

    public Products() {}

    public Products(Long id, String productsName, String imageName, String skuName, String description, float price, float qty) {
        this.id = id;
        this.productsName = productsName;
        this.imageName = imageName;
        this.skuName = skuName;
        this.description = description;
        this.price = price;
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
