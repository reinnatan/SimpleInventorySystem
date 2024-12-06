package testing.java.fx.testingjavafx.attributevalue.panel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import testing.java.fx.testingjavafx.attribute.model.AttributeDB;
import testing.java.fx.testingjavafx.attribute.panel.AttributePanel;
import testing.java.fx.testingjavafx.attribute.viewmodel.AttributeVM;
import testing.java.fx.testingjavafx.attributevalue.model.AttributeValueDB;
import testing.java.fx.testingjavafx.dbutil.DBUtil;

import java.util.ArrayList;

public class AttributeValuePanel extends VBox {
    protected ObservableList<AttributeVM> data;
    protected TableView<AttributeVM> tableView;

    public AttributeValuePanel(AttributeDB attributeDB, AttributePanel attributePanel , AttributePanel.AttributeValueInterface attributeValueInterface){
        this.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10;");
        HBox panelButton = new HBox();

        Button addAttributeValue = new Button("Add Value Attribute");
        Button backAttributePanel = new Button("<-");
        panelButton.getChildren().addAll(backAttributePanel, addAttributeValue);

        tableView = new TableView<AttributeVM>();
        TableColumn<AttributeVM, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().attributeNameProperty());
        nameColumn.setPrefWidth(500);

        TableColumn<AttributeVM, String> ageColumn = new TableColumn<>("Status");
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        ageColumn.setPrefWidth(500);
        tableView.getColumns().addAll(nameColumn, ageColumn);

        data = FXCollections.observableArrayList();
        tableView.setItems(data);
        tableView.refresh();
        getChildren().add(panelButton);
        getChildren().add(tableView);

        addAttributeValue.setOnAction(e -> {
            Dialog alert = new Dialog();
            alert.setTitle("Add Brand");
            VBox boxContent = new VBox();
            boxContent.getChildren().add(new Label("Attribute Value Name"));
            TextField attributeValueName = new TextField();
            boxContent.getChildren().add(attributeValueName);
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
                    AttributeValueDB attributeValueDB = new AttributeValueDB(attributeValueName.getText(), comboBoxStatus.getValue().toString());
                    attributeDB.getListAttributes().add(attributeValueDB);
                    attributeValueDB.setAttributeDB(attributeDB);
                    em.merge(attributeDB);
                    em.merge(attributeValueDB);
                    em.getTransaction().commit();

                    data.add(new AttributeVM(0L, attributeValueName.getText(), comboBoxStatus.getValue().toString()));
                    tableView.setItems(data);
                    tableView.refresh();
                    System.out.println("You clicked Yes!");

                } else if (response == buttonTypeCancel) {
                    System.out.println("You clicked Cancel!");
                }
            });
        });

        backAttributePanel.setOnAction(value -> {
            attributeValueInterface.changeToAttributePanel(attributePanel);
        });


        //setup attribute value in tableview
        setupAttributeValue(attributeDB);
    }

    public void setupAttributeValue(AttributeDB attributeDB){
        EntityManager em = DBUtil.getEntityManager();
        Query query = em.createQuery("SELECT s FROM AttributeValueDB s WHERE s.attributeDB.id = :attribute", AttributeValueDB.class);
        query.setParameter("attribute", attributeDB.getId());
        query.getResultList().forEach(attributeValueDB -> {
            data.add(new AttributeVM(((AttributeValueDB)attributeValueDB).getId() ,((AttributeValueDB)attributeValueDB).getAttributValue(), ((AttributeValueDB)attributeValueDB).getStatus() ));
            tableView.setItems(data);
            tableView.refresh();
        });
    }
}
