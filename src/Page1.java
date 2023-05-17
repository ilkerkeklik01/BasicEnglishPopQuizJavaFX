import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;
import java.util.Map;

public class Page1 extends EachPage implements Gradeable{

    private static Button generateResultsButton = new Button("Generate Results");
    private static Button generateMyGrade = new Button("Generate My Grade");

    private static VBox staticVbox;
    private static Map<String,String> map;

    public Page1(Stage stage) {
        super(getPage(),stage);
        generateResultsButton.setOnAction(e-> printResults());
        generateMyGrade.setOnAction(e-> generateMyGradeClicked());
        pageNumber =1 ;
    }

    private void generateMyGradeClicked() {
        int numberOfQ = map.size();
        int trueOnes = 0;
        VBox vBox = staticVbox;
        for(int i = 0; i<vBox.getChildren().size();i++){
            //çiftse soru Türkçesi
            if(i%2==0){
                TextField textField = null;
                if(vBox.getChildren().get(i+1) instanceof TextField){
                    textField = (TextField) vBox.getChildren().get(i+1);
                }
                String strKey = ((Label)vBox.getChildren().get(i)).getText();
                String strValue = textField.getText();
                if(map.get(strKey).equals(strValue)){
                    trueOnes++;
                }
            }

        }

        Stage initialStage = (Stage) getRoot().getScene().getWindow();

        Stage resultStage = new Stage();
        resultStage.initOwner(initialStage);
        resultStage.initStyle(StageStyle.UTILITY);
        resultStage.initModality(Modality.APPLICATION_MODAL);

        Label label = new Label("You have "+trueOnes+" correct answer(s) of "+numberOfQ+" questions.");
        Scene scene = new Scene(label);
        resultStage.setScene(scene);
        resultStage.setTitle("Grade");
        resultStage.setResizable(false);
        resultStage.showAndWait();

    }


    private static Pane getPage() {
        fillTheMap();
        VBox root = new VBox(10);

        try {
            Image image = new Image("backgrounds/background2.jpg");
            root.setBackground(new Background(new BackgroundImage(image,null,null,null,null)));
        }catch (Exception e){
            e.printStackTrace();
        }


        root.setPadding(new Insets(10));
        Label label = new Label("Fill in the blanks by translating the following Turkish sentences into English.");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        root.getChildren().add(label);
        VBox vBox = getQuestions();
        root.getChildren().add(vBox);
        staticVbox =vBox;


        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setSpacing(10);

        hBox.getChildren().addAll(generateResultsButton,generateMyGrade);

        Button nextButton = new Button("Next Page");
        nextButton.setOnAction(e->{
            primaryStage.setScene(new Page2(primaryStage));
        });
        hBox.getChildren().add(nextButton);

        root.getChildren().add(hBox);



        return root;

    }
    private static VBox getQuestions(){
        VBox vBox = new VBox();

        for(String each : map.keySet()){
            Label ques = new Label(each);
            vBox.getChildren().add(ques);
            vBox.getChildren().add(new TextField());
        }
        return vBox;
    }


    private static void fillTheMap(){
        map = new HashMap<>();
        map.put("Bunu nasıl yapabilirim?","How can I do this?");
        map.put("Ali su içer.","Ali drinks water.");
        map.put("Ayşe eve geldi","Ayşe came home.");
        map.put("Semih ile takılmayı seviyorum.","I like to hang out with Semih.");
        map.put("Bir kalemin var mı?","Do you have a pencil?");
        map.put("Yüzmeyle ilgileniyorum.","I am interested in swimming.");
        map.put("Annem İngilizce öğreniyor.","My mother is learning English.");
        map.put("Yarın okula gitmeyeceğim.","I will not go to the school tomorrow.");
        map.put("Kaç tane arkadaşın var?","How many friends do you have?");
        map.put("Kaç yaşındasın?","How old are you?");
    }

    @Override
    public void printResults() {
        Stage initialStage = (Stage) getRoot().getScene().getWindow();

        Stage resultStage = new Stage();
        resultStage.initOwner(initialStage);
        resultStage.initStyle(StageStyle.UTILITY);
        resultStage.initModality(Modality.APPLICATION_MODAL);

        VBox vbox = new VBox();

        for(String keys: map.keySet()){
            vbox.getChildren().add(new Label(keys+" --> "+map.get(keys)));
        }
        Scene scene = new Scene(vbox);
        resultStage.setScene(scene);
        resultStage.setTitle("Results");
        resultStage.setResizable(false);
        resultStage.showAndWait();

    }

}
