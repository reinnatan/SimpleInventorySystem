package testing.java.fx.testingjavafx.attribute.model;

import jakarta.persistence.*;
import testing.java.fx.testingjavafx.attributevalue.model.AttributeValueDB;

import java.util.List;

@Entity
@Table(name = "attribute")
public class AttributeDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String attributeName;
    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "attributeDB", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttributeValueDB> listAttributes;

    public AttributeDB() {}

    public AttributeDB(String attributeName, String status) {
        this.attributeName = attributeName;
        this.status = status;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AttributeValueDB> getListAttributes() {
        return listAttributes;
    }

    public void setListAttributes(List<AttributeValueDB> listAttributes) {
        this.listAttributes = listAttributes;
    }
}
