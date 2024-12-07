package testing.java.fx.testingjavafx.order.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String categoryName;
    @Column(nullable = false)
    private String status;

    public Order() {}
    public Order(String categoryName, String status) {
        this.categoryName = categoryName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
