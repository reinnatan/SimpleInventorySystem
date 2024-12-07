package testing.java.fx.testingjavafx.brand.panel;

import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import testing.java.fx.testingjavafx.brand.model.Brand;
import testing.java.fx.testingjavafx.brand.viewmodel.BrandVM;
import testing.java.fx.testingjavafx.dbutil.DBUtil;

public class BrandPanel extends VBox {
    protected ObservableList<BrandVM> data;
    TableView<BrandVM> tableView;

    public BrandPanel() {
        this.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10;");
        Button addCategory = new Button("Add Brand");

        tableView = new TableView<BrandVM>();
        TableColumn<BrandVM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().brandNameProperty());
        nameColumn.setPrefWidth(500);

        TableColumn<BrandVM, String> ageColumn = new TableColumn<>("Status");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ageColumn.setPrefWidth(500);
        tableView.getColumns().addAll(nameColumn, ageColumn);

        data = FXCollections.observableArrayList();
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
                    Brand brand = new Brand(categoryName.getText(), comboBoxStatus.getValue().toString());
                    EntityManager em = DBUtil.getEntityManager();
                    em.getTransaction().begin();
                    em.persist(brand);
                    em.getTransaction().commit();

                    data.add(new BrandVM(categoryName.getText(), comboBoxStatus.getValue().toString()));
                    tableView.setItems(data);
                    tableView.refresh();
                    System.out.println("You clicked Yes!");
                } else if (response == buttonTypeCancel) {
                    System.out.println("You clicked Cancel!");
                }
            });
            setupTableView();
        });

    }

    public void setupTableView(){
       data.clear();
       EntityManager em = DBUtil.getEntityManager();
       em.createQuery("SELECT b FROM Brand b", Brand.class).getResultList().forEach(brand -> {
              data.add(new BrandVM(brand.getBrandName(), brand.getStatus()));
       });
       tableView.setItems(data);
       tableView.refresh();
    }
}
