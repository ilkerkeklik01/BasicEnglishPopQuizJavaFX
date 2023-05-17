import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Pop Quiz");

        Pane pane = new Pane();

        primaryStage.setScene(new Page1(primaryStage));
        primaryStage.setResizable(false);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
