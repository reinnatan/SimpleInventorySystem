package testing.java.fx.testingjavafx.attribute.panel;

import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import testing.java.fx.testingjavafx.attribute.viewmodel.AttributeVM;
import testing.java.fx.testingjavafx.dbutil.DBUtil;

public class AttributePanel extends VBox {
    public AttributePanel() {
        this.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10;");
        Button addCategory = new Button("Add Attribute");

        TableView<AttributeVM> tableView = new TableView<AttributeVM>();
        TableColumn<AttributeVM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().attributeNameProperty());
        nameColumn.setPrefWidth(500);

        TableColumn<AttributeVM, String> ageColumn = new TableColumn<>("Status");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ageColumn.setPrefWidth(500);
        tableView.getColumns().addAll(nameColumn, ageColumn);

        ObservableList<AttributeVM> data = FXCollections.observableArrayList();
        tableView.setItems(data);
        tableView.refresh();
        getChildren().add(addCategory);
        getChildren().add(tableView);

        addCategory.setOnAction(e -> {
            Dialog alert = new Dialog();
            alert.setTitle("Add Brand");
            VBox boxContent = new VBox();
            boxContent.getChildren().add(new Label("Attribute Name"));
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
                    EntityManager em = DBUtil.getEntityManager();
                    em.getTransaction().begin();
                    AttributeVM attributeVM = new AttributeVM(categoryName.getText(), comboBoxStatus.getValue().toString());
                    em.persist(attributeVM);
                    em.getTransaction().commit();

                    data.add(new AttributeVM(categoryName.getText(), comboBoxStatus.getValue().toString()));
                    tableView.setItems(data);
                    tableView.refresh();
                    System.out.println("You clicked Yes!");
                } else if (response == buttonTypeCancel) {
                    System.out.println("You clicked Cancel!");
                }
            });


        });
    }
}
