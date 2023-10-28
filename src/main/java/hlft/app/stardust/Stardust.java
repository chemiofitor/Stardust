package hlft.app.stardust;

import atlantafx.base.theme.NordDark;
import hlft.app.stardust.widget.WindowTop;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Stardust extends Application {
    private final StardustProperties properties = new StardustProperties(this);

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = anchor(primaryStage);
        //更换UI主题
        Application.setUserAgentStylesheet(new NordDark().getUserAgentStylesheet());

        VBox top = top(anchorPane, primaryStage);

        body(anchorPane, primaryStage);

        Scene scene = new Scene(anchorPane, properties.initialWidth, properties.initialHeight);

        //设置标题
        primaryStage.setTitle("星尘|Stardust");
        //设置窗口大小限制
        primaryStage.setMinHeight(properties.minHeight);
        primaryStage.setMinWidth(properties.minWidth);
        primaryStage.setMaxHeight(properties.maxHeight);
        primaryStage.setMaxWidth(properties.maxWidth);

        //透明化原窗口组件
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        //设置窗口图标
        primaryStage.getIcons().add(new Image("assets/image/icon.png"));

        //显示窗口
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void body(AnchorPane anchorPane, Stage primaryStage) {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);

        for (int i = 0; i < 48; i++) {
            AnchorPane paneX = new AnchorPane();
            Button button = new Button(String.valueOf(i + 1));
            paneX.getChildren().add(button);
            AnchorPane.setLeftAnchor(button, 0d);
            AnchorPane.setRightAnchor(button, 0d);
            AnchorPane.setBottomAnchor(button, 0d);
            pane.add(paneX, i % 6, i / 6);
        }

        anchorPane.getChildren().add(pane);
        AnchorPane.setLeftAnchor(pane, 5d);
        AnchorPane.setRightAnchor(pane, 5d);
        AnchorPane.setBottomAnchor(pane, 10d);
    }

    private AnchorPane anchor(Stage stage) {
        AnchorPane anchorPane = new AnchorPane();

        anchorPane.setOnMouseMoved((MouseEvent event) -> {
            event.consume();
            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = stage.getWidth();
            double height = stage.getHeight();
            Cursor cursorType = Cursor.DEFAULT;

            properties.isRight = properties.isBottomRight = properties.isBottom = false;
            if (y >= height - properties.resizeWidth) {
                if (x >= width - properties.resizeWidth) {// 右下角调整窗口状态
                    properties.isBottomRight = true;
                    cursorType = Cursor.SE_RESIZE;
                } else {// 下边界调整窗口状态
                    properties.isBottom = true;
                    cursorType = Cursor.S_RESIZE;
                }
            } else if (x >= width - properties.resizeWidth) {// 右边界调整窗口状态
                properties.isRight = true;
                cursorType = Cursor.E_RESIZE;
            }
            // 最后改变鼠标光标
            anchorPane.setCursor(cursorType);
        });

        anchorPane.setOnMouseDragged((MouseEvent event) -> {
            double x = event.getSceneX();
            double y = event.getSceneY();
            // 保存窗口改变后的x、y坐标和宽度、高度，用于预判是否会小于最小宽度、最小高度
            double nextX = stage.getX();
            double nextY = stage.getY();
            double nextWidth = stage.getWidth();
            double nextHeight = stage.getHeight();
            if (properties.isRight || properties.isBottomRight) {// 所有右边调整窗口状态
                nextWidth = x;
            }
            if (properties.isBottomRight || properties.isBottom) {// 所有下边调整窗口状态
                nextHeight = y;
            }
            if (nextWidth <= properties.minWidth) {// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                nextWidth = properties.minWidth;
            }
            if (nextHeight <= properties.minHeight) {// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                nextHeight = properties.minHeight;
            }
            if (nextWidth >= properties.maxWidth) {// 如果窗口改变后的宽度小于最小宽度，则宽度调整到最小宽度
                nextWidth = properties.maxWidth;
            }
            if (nextHeight >= properties.maxHeight) {// 如果窗口改变后的高度小于最小高度，则高度调整到最小高度
                nextHeight = properties.maxHeight;
            }
            // 最后统一改变窗口的x、y坐标和宽度、高度，可以防止刷新频繁出现的屏闪情况
            stage.setX(nextX);
            stage.setY(nextY);
            stage.setWidth(nextWidth);
            stage.setHeight(nextHeight);
            properties.presentWidth = (int) nextWidth;
            properties.presentHeight = (int) nextHeight;
        });

        return anchorPane;
    }

    private VBox top(AnchorPane anchorPane, Stage primaryStage) {
        WindowTop top = new WindowTop(primaryStage);
        top.setPrefHeight(30);

        //窗口拖动
        top.setOnMousePressed(event -> {
            properties.xOffset = event.getSceneX();
            properties.yOffset = event.getSceneY();
        });

        //窗口拖动
        top.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - properties.xOffset);
            primaryStage.setY(event.getScreenY() - properties.yOffset);
        });

        anchorPane.getChildren().add(top);
        AnchorPane.setLeftAnchor(top, 0d);
        AnchorPane.setRightAnchor(top, 0d);
        AnchorPane.setTopAnchor(top, 0d);
        return top;
    }

    @Override
    public void stop() {

    }

    @Override
    public void init() {

    }

    public static void main(String[] args) {
        launch();
    }
}