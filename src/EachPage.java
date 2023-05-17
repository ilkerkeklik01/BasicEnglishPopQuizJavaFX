import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class EachPage extends Scene {
    int pageNumber;

    static Stage primaryStage;

    public EachPage(Parent root,Stage stage) {
        super(root,1200,900);
        primaryStage = stage;

    }

}
