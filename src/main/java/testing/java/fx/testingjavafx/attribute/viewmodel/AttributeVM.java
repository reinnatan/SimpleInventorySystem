package testing.java.fx.testingjavafx.attribute.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AttributeVM
{
    private StringProperty attributeName;
    private StringProperty status;

    public AttributeVM(String attributeName, String status) {
        this.attributeName = new SimpleStringProperty(attributeName);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty attributeNameProperty() {
        return attributeName;
    }
    public StringProperty statusProperty() {
        return status;
    }
}
