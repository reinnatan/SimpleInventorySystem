package testing.java.fx.testingjavafx.product.panel;

import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.fxmisc.richtext.InlineCssTextArea;
import testing.java.fx.testingjavafx.dbutil.DBUtil;
import testing.java.fx.testingjavafx.product.model.Products;
import testing.java.fx.testingjavafx.product.viewmodel.ProductsVM;

public class ProductsPanel extends VBox{

    protected ObservableList<ProductsVM> data;
    protected TableView<ProductsVM> tableView;

    public ProductsPanel() {
        this.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10;");
        Button addCategory = new Button("Add Products");

        tableView = new TableView<ProductsVM>();
        TableColumn<ProductsVM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        nameColumn.setPrefWidth(500);

        TableColumn<ProductsVM, String> ageColumn = new TableColumn<>("Status");
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
            alert.setTitle("Add Products");
            alert.setWidth(400);
            alert.setHeight(400);
            VBox boxContent = new VBox();
            boxContent.getChildren().add(new Label("Image Products"));
            Button fileChooser = new Button("Image Products");
            boxContent.getChildren().add(fileChooser);
            boxContent.getChildren().add(new Label("Products Name"));
            TextField categoryName = new TextField();
            boxContent.getChildren().add(categoryName);
            boxContent.getChildren().add(new Label("SKU"));
            TextField sku = new TextField();
            boxContent.getChildren().add(sku);
            boxContent.getChildren().add(new Label("Price"));
            TextField price = new TextField();
            boxContent.getChildren().add(price);
            boxContent.getChildren().add(new Label("Quantity"));
            TextField quantity = new TextField();
            boxContent.getChildren().add(quantity);
            boxContent.getChildren().add(new Label("Description"));
            InlineCssTextArea description = new InlineCssTextArea();
            boxContent.getChildren().add(description);
            alert.getDialogPane().setContent(boxContent);
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeCancel = new ButtonType("Cancel");

            //setup button to open file chooser
            /* close this function for a while because it's not working to handle main popup
            fileChooser.setOnAction(r -> {
                addCategory.setDisable(true);
                FileChooser fileChooser1 = new FileChooser();
                fileChooser1.setTitle("Open Resource File");
                fileChooser1.showOpenDialog(ProductsPanel.this.getScene().getWindow());
            });
            */

            alert.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeCancel);
            alert.showAndWait().ifPresent(response -> {

                if (response == buttonTypeYes) {
                    EntityManager em = DBUtil.getEntityManager();
                    //Products products = new Products(categoryName.getText(), comboBoxStatus.getValue().toString());
                    em.getTransaction().begin();
                    ///em.persist(products);
                    em.getTransaction().commit();

                    //data.add(new ProductsVM(categoryName.getText(), comboBoxStatus.getValue().toString()));
                    tableView.setItems(data);
                    tableView.refresh();
                    System.out.println("You clicked Yes!");

                    setupTableView();


                } else if (response == buttonTypeCancel) {
                    System.out.println("You clicked Cancel!");
                }
            });



        });


    }

    public void setupTableView(){
        data.clear();
        EntityManager em = DBUtil.getEntityManager();
        em.createQuery("SELECT p FROM Products c", Products.class).getResultList().forEach(products -> {
            //data.add(new CategoryVM(products.getProductsName(), products.getStatus()));
            System.out.println(products.getProductsName());
        });
        tableView.setItems(data);
        tableView.refresh();
    }

}
