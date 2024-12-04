package testing.java.fx.testingjavafx.brand.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BrandVM
{
    private StringProperty brandName;
    private StringProperty status;

    public BrandVM(String brandName, String status) {
        this.brandName = new SimpleStringProperty(brandName);
        this.status = new SimpleStringProperty(status);
    }

    public String getBrandName() {
        return brandName.get();
    }

    public StringProperty brandNameProperty() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName.set(brandName);
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
