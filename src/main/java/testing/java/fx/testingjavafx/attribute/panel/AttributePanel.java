package testing.java.fx.testingjavafx.attribute.panel;

import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import testing.java.fx.testingjavafx.attribute.model.AttributeDB;
import testing.java.fx.testingjavafx.attribute.viewmodel.AttributeVM;
import testing.java.fx.testingjavafx.attributevalue.panel.AttributeValuePanel;
import testing.java.fx.testingjavafx.dbutil.DBUtil;
import testing.java.fx.testingjavafx.store.model.StoreDB;
import testing.java.fx.testingjavafx.store.viewmodel.StoreVM;

public class AttributePanel extends VBox {

    public interface AttributeValueInterface {
        void changeToAttributeValuePanel(AttributeValuePanel attributeValuePanel);
        void changeToAttributePanel(AttributePanel attributePanel);
    }

    private ObservableList<AttributeVM> data;
    private TableView<AttributeVM> tableView;
    private AttributeValueInterface attributeValueInterface;

    public AttributePanel(AttributeValueInterface attributeValueInterface) {
        this.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10;");
        Button addCategory = new Button("Add Attribute");
        this.attributeValueInterface  =  attributeValueInterface;

        tableView = new TableView<AttributeVM>();
        TableColumn<AttributeVM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().attributeNameProperty());
        nameColumn.setPrefWidth(150);

        TableColumn<AttributeVM, String> ageColumn = new TableColumn<>("Status");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ageColumn.setPrefWidth(150);

        TableColumn<AttributeVM, String> actionColumn = new TableColumn<>("Action");
        actionColumn.setPrefWidth(150);
        actionColumn.setCellFactory(col -> new TableCell() {
            private final Button button = new Button("+");
            {
                button.setOnAction(event -> {
                    AttributeValuePanel attributeValuePanel = new AttributeValuePanel(AttributePanel.this,attributeValueInterface);
                    attributeValueInterface.changeToAttributeValuePanel(attributeValuePanel);
                });
            }

            @Override
            public void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : button);
            }
        });


        tableView.getColumns().addAll(nameColumn, ageColumn, actionColumn);

        data = FXCollections.observableArrayList();
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
                    AttributeDB attributeDB = new AttributeDB(categoryName.getText(), comboBoxStatus.getValue().toString());
                    em.persist(attributeDB);
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

        setupAttribute();
    }

    public void setupAttribute(){
        EntityManager em = DBUtil.getEntityManager();
        em.createQuery("SELECT s FROM AttributeDB s", AttributeDB.class).getResultList().forEach(attributeDB -> {
            data.add(new AttributeVM(attributeDB.getAttributeName(), attributeDB.getStatus()));
            tableView.setItems(data);
            tableView.refresh();
        });
    }
}
