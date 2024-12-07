package testing.java.fx.testingjavafx.store.panel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import testing.java.fx.testingjavafx.dbutil.DBUtil;
import testing.java.fx.testingjavafx.store.model.StoreDB;
import testing.java.fx.testingjavafx.store.viewmodel.StoreVM;

import java.util.List;

public class StorePanel extends VBox {

    private ObservableList<StoreVM> data;
    private TableView<StoreVM> tableView;

    public StorePanel() {
        this.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10;");
        Button addCategory = new Button("Store Category");

        tableView = new TableView<StoreVM>();
        TableColumn<StoreVM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().storeNameProperty());
        nameColumn.setPrefWidth(500);

        TableColumn<StoreVM, String> ageColumn = new TableColumn<>("Status");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ageColumn.setPrefWidth(500);
        tableView.getColumns().addAll(nameColumn, ageColumn);

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
        tableView.refresh();
        getChildren().add(addCategory);
        getChildren().add(tableView);
        setupDataToko();

        addCategory.setOnAction(e -> {
            Dialog alert = new Dialog();
            alert.setTitle("Store Category");
            VBox boxContent = new VBox();
            boxContent.getChildren().add(new Label("Store Name"));
            TextField categoryName = new TextField();
            boxContent.getChildren().add(categoryName);
            boxContent.getChildren().add(new Label("Status"));
            ComboBox comboBoxStatus = new ComboBox();
            comboBoxStatus.getItems().addAll(
                    "Active",
                    "Inactive"
            );

            comboBoxStatus.setValue("Active");
            boxContent.getChildren().add(comboBoxStatus);
            alert.getDialogPane().setContent(boxContent);
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeCancel = new ButtonType("Cancel");

            alert.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeCancel);
            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypeYes) {
                    StoreDB storeDB = new StoreDB(categoryName.getText(), comboBoxStatus.getValue().toString());
                    EntityManager em = DBUtil.getEntityManager();
                    em.getTransaction().begin();
                    em.persist(storeDB);
                    em.getTransaction().commit();

                    data.add(new StoreVM(categoryName.getText(), comboBoxStatus.getValue().toString()));
                    tableView.setItems(data);
                    tableView.refresh();
                    System.out.println("You clicked Yes!");
                } else if (response == buttonTypeCancel) {
                    System.out.println("You clicked Cancel!");
                }
            });

            setupDataToko();
        });
    }

    public void setupDataToko(){
        data.clear();
        EntityManager em = DBUtil.getEntityManager();
        em.createQuery("SELECT s FROM StoreDB s", StoreDB.class).getResultList().forEach(storeDB -> {
            data.add(new StoreVM(storeDB.getStoreName(), storeDB.getStatus()));
        });

        tableView.setItems(data);
        tableView.refresh();
    }
}
