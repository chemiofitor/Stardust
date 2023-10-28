package hlft.app.stardust.widget;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WindowButton extends Button {
    public WindowButton(String id, EventHandler<ActionEvent> value) {
        if (value != null)
            this.setOnAction(value);
        ImageView close = new ImageView(new Image("assets/image/window/" + id + ".png"));
        close.setFitHeight(20);
        close.setFitWidth(20);

        this.setGraphic(close);
    }
}
