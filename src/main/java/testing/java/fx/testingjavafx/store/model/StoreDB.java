package testing.java.fx.testingjavafx.store.model;
import jakarta.persistence.*;

@Entity
@Table(name = "store")
public class StoreDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String storeName;
    @Column(nullable = false)
    private String status;

    public StoreDB() {}

    public StoreDB(String storeName, String status) {
        this.storeName = storeName;
        this.status = status;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
