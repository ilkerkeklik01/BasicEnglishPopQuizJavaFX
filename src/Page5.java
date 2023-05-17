import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Page5 extends EachPage implements Gradeable{


    public Page5(Stage stage) {
        super(getPage(),stage);
    }

    private static Pane getPage() {


        return new Pane();

    }


    @Override
    public void printResults() {

    }

}