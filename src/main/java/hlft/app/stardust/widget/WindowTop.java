package hlft.app.stardust.widget;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowTop extends VBox {
    public WindowTop(Stage stage) {
        AnchorPane root = new AnchorPane();
        root.setId("root");

        WindowButton close = new WindowButton("close", event -> stage.close());
        WindowButton min = new WindowButton("min", event -> stage.setIconified(true));
        ImageView title = new ImageView(new Image("assets/image/title.png"));
        ImageView icon = new ImageView(new Image("assets/image/icon.png"));
        title.setFitHeight(40);
        title.setFitWidth(150);
        icon.setFitWidth(40);
        icon.setFitHeight(40);

        close.setId("button");
        min.setId("button");

        root.getChildren().add(close);
        AnchorPane.setRightAnchor(close, 0d);
        AnchorPane.setTopAnchor(close, 0d);

        root.getChildren().add(min);
        AnchorPane.setRightAnchor(min, 45d);
        AnchorPane.setTopAnchor(min, 0d);

        root.getChildren().add(title);
        AnchorPane.setLeftAnchor(title, 0d);
        AnchorPane.setTopAnchor(title, 0d);

        root.getChildren().add(icon);
        AnchorPane.setLeftAnchor(icon, 150d);
        AnchorPane.setTopAnchor(icon, 0d);

        root.getStylesheets().add("assets/css/top.css");
        this.getChildren().add(root);
    }
}
