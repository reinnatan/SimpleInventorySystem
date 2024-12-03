package testing.java.fx.testingjavafx.store.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StoreVM
{
    private StringProperty storeName;
    private StringProperty status;

    public StoreVM(String storeName, String status) {
        this.storeName = new SimpleStringProperty(storeName);
        this.status = new SimpleStringProperty(status);
    }

    public String getStoreName() {
        return storeName.get();
    }

    public StringProperty storeNameProperty() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName.set(storeName);
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
