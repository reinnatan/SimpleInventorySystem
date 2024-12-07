package testing.java.fx.testingjavafx;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import testing.java.fx.testingjavafx.attribute.panel.AttributePanel;
import testing.java.fx.testingjavafx.attributevalue.panel.AttributeValuePanel;
import testing.java.fx.testingjavafx.brand.panel.BrandPanel;
import testing.java.fx.testingjavafx.category.panel.CategoryPanel;
import testing.java.fx.testingjavafx.store.panel.StorePanel;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloApplication extends Application implements EventHandler, AttributePanel.AttributeValueInterface {

    protected Map<String, VBox> mapButton = new HashMap<String, VBox>();
    protected List<String> listButton = Arrays.asList("Category", "Brand", "Attribute", "Store");
    protected StackPane mainContainer;

    @Override
    public void start(Stage stage) throws IOException {
        // Left Menu
        VBox leftMenu = new VBox(10); // Vertical layout with spacing
        leftMenu.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 10;"); // Light gray background

        CategoryPanel categoryPanel = new CategoryPanel();
        BrandPanel brandPanel = new BrandPanel();
        StorePanel storePanel = new StorePanel();
        AttributePanel attributePanel = new AttributePanel(this);

        Button categoryButton = new Button("Category");
        categoryButton.setPrefWidth(100);
        Button brandButton = new Button("Brand");
        brandButton.setPrefWidth(100);
        Button attributeButton = new Button("Attribute");
        attributeButton.setPrefWidth(100);
        Button storeButton = new Button("Store");
        storeButton.setPrefWidth(100);

        leftMenu.getChildren().addAll(
                categoryButton,
                brandButton,
                attributeButton,
                storeButton
        );

        // Layout
        BorderPane layout = new BorderPane();
        mainContainer = new StackPane();
        layout.setLeft(leftMenu);
        layout.setCenter(mainContainer);

        categoryButton.setOnAction(e -> {
            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(categoryPanel);
            categoryPanel.setupTableView();
        });

        brandButton.setOnAction(e -> {
            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(brandPanel);
            brandPanel.setupTableView();
        });

        storeButton.setOnAction(e -> {
            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(storePanel);
            storePanel.setupDataToko();
        });

        attributeButton.setOnAction(e -> {
            mainContainer.getChildren().clear();
            mainContainer.getChildren().add(attributePanel);
            attributePanel.setupTableAttribute();
        });

        // Scene
        Scene scene = new Scene(layout, 1200, 800);
        stage.setTitle("JavaFX Left and Right Menu Example");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void handle(Event event) {
        System.out.println(event.getSource());
    }

    @Override
    public void changeToAttributeValuePanel(AttributeValuePanel attributeValuePanel) {
        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(attributeValuePanel);
    }

    @Override
    public void changeToAttributePanel(AttributePanel attributePanel) {
        mainContainer.getChildren().clear();
        mainContainer.getChildren().add(attributePanel);
    }
}
