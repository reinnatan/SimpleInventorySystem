package testing.java.fx.testingjavafx.category.panel;

import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import testing.java.fx.testingjavafx.category.model.Category;
import testing.java.fx.testingjavafx.category.viewmodel.CategoryVM;
import testing.java.fx.testingjavafx.dbutil.DBUtil;

public class CategoryPanel extends VBox{


    protected ObservableList<CategoryVM> data;
    protected TableView<CategoryVM> tableView;

    public CategoryPanel() {
        this.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10;");
        Button addCategory = new Button("Add Category");

        tableView = new TableView<CategoryVM>();
        TableColumn<CategoryVM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        nameColumn.setPrefWidth(500);

        TableColumn<CategoryVM, String> ageColumn = new TableColumn<>("Status");
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
            alert.setTitle("Add Category");
            VBox boxContent = new VBox();
            boxContent.getChildren().add(new Label("Category Name"));
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
                    Category category = new Category(categoryName.getText(), comboBoxStatus.getValue().toString());
                    em.getTransaction().begin();
                    em.persist(category);
                    em.getTransaction().commit();

                    data.add(new CategoryVM(categoryName.getText(), comboBoxStatus.getValue().toString()));
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
        em.createQuery("SELECT c FROM Category c", Category.class).getResultList().forEach(category -> {
            data.add(new CategoryVM(category.getCategoryName(), category.getStatus()));
            System.out.println(category.getCategoryName());
        });
        tableView.setItems(data);
        tableView.refresh();
    }

}
