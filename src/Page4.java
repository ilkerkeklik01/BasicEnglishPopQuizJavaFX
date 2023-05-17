import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Page4 extends EachPage implements Gradeable{


    public Page4(Stage stage) {
        super(getPage(),stage);
    }

    private static Pane getPage() {


        return new Pane();

    }


    @Override
    public void printResults() {

    }

}