package testing.java.fx.testingjavafx.attribute.viewmodel;

import javafx.beans.property.*;

public class AttributeVM
{
    private StringProperty attributeName;
    private StringProperty status;
    private LongProperty id;

    public AttributeVM(Long id, String attributeName, String status) {
        this.attributeName = new SimpleStringProperty(attributeName);
        this.status = new SimpleStringProperty(status);
        this.id = new SimpleLongProperty(id);
    }

    public StringProperty attributeNameProperty() {
        return attributeName;
    }
    public StringProperty statusProperty() {
        return status;
    }
    public LongProperty idProperty() {
        return id;
    }
}
