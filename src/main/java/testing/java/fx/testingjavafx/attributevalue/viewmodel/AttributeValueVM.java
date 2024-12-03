package testing.java.fx.testingjavafx.attributevalue.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AttributeValueVM
{
    private StringProperty attributeValueName;
    private StringProperty status;

    public AttributeValueVM(String attributeName, String status) {
        this.attributeValueName = new SimpleStringProperty(attributeName);
        this.status = new SimpleStringProperty(status);
    }

    public StringProperty attributeValueNameProperty() {
        return attributeValueName;
    }
    public StringProperty statusProperty() {
        return status;
    }
}
