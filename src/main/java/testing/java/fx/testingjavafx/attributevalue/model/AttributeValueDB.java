package testing.java.fx.testingjavafx.attributevalue.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attribute_value")
public class AttributeValueDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String attributValue;

    public AttributeValueDB() {}

    public AttributeValueDB(Long id, String attributValue) {
        this.id = id;
        this.attributValue = attributValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributValue() {
        return attributValue;
    }

    public void setAttributValue(String attributValue) {
        this.attributValue = attributValue;
    }
}
