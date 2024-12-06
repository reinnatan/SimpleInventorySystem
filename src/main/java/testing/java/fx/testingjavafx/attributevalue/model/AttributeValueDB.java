package testing.java.fx.testingjavafx.attributevalue.model;

import jakarta.persistence.*;
import testing.java.fx.testingjavafx.attribute.model.AttributeDB;

@Entity
@Table(name = "attribute_value")
public class AttributeValueDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String attributValue;
    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private AttributeDB attributeDB;

    public AttributeValueDB(){}

    public AttributeValueDB(String attributValue, String status) {
        this.attributValue = attributValue;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AttributeDB getAttributeDB() {
        return attributeDB;
    }

    public void setAttributeDB(AttributeDB attributeDB) {
        this.attributeDB = attributeDB;
    }
}
