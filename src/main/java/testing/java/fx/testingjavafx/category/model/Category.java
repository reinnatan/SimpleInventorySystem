package testing.java.fx.testingjavafx.category.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category
{
    private StringProperty categoryName;
    private StringProperty status;

    public Category(String categoryName, String status) {
        this.categoryName = new SimpleStringProperty(categoryName);
        this.status = new SimpleStringProperty(status);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public StringProperty categoryNameProperty() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}