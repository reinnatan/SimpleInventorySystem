package testing.java.fx.testingjavafx.brand.panel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import testing.java.fx.testingjavafx.brand.model.Brand;

public class BrandPanel extends VBox {
    public BrandPanel() {
        this.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10;");
        Button addCategory = new Button("Add Brand");

        TableView<Brand> tableView = new TableView<Brand>();
        TableColumn<Brand, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().brandNameProperty());
        nameColumn.setPrefWidth(500);

        TableColumn<Brand, String> ageColumn = new TableColumn<>("Status");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ageColumn.setPrefWidth(500);
        tableView.getColumns().addAll(nameColumn, ageColumn);

        ObservableList<Brand> data = FXCollections.observableArrayList();
        tableView.setItems(data);
        tableView.refresh();
        getChildren().add(addCategory);
        getChildren().add(tableView);

        addCategory.setOnAction(e -> {
            Dialog alert = new Dialog();
            alert.setTitle("Add Brand");
            VBox boxContent = new VBox();
            boxContent.getChildren().add(new Label("Brand Name"));
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
                    data.add(new Brand(categoryName.getText(), comboBoxStatus.getValue().toString()));
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
