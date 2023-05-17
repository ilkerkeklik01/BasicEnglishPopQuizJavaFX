import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Page3 extends EachPage implements Gradeable{

    private static int correctSelections = 0;
    private static Button generateResultsButton = new Button("Generate Results");
    public Page3(Stage stage) {
        super(getPage(),stage);
        generateResultsButton.setOnAction(e->{
            printResults();
        });
    }

    private static ScrollPane getPage() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));


        VBox questionBox ;
        String[] o1 = {"Which is","Who's","When's","Where's"};
        questionBox=createQuestion(1," ---- your car? It's in the garage.",o1);
        gridPane.add(questionBox, 0, 0);

        String[] o2 = {"Whose","What's","Who's","Who"};
        questionBox=createQuestion(2,"---- bag is this? It's mine.",o2);
        gridPane.add(questionBox, 0, 1);

        String[] o3 = {"They are","There are","There is","It is"};
        questionBox=createQuestion(3,"---- only three chairs in my room.",o3);
        gridPane.add(questionBox, 0, 2);

        String[] o4 = {"got","have got","has got","is got"};
        questionBox=createQuestion(4,"She ---- a house in the town center.",o4);
        gridPane.add(questionBox, 0, 3);

        String[] o5 = {"How much","How old","How are","How many"};
        questionBox=createQuestion(5,"---- brothers have you got? Just one.",o5);
        gridPane.add(questionBox, 0, 4);

        String[] o6 = {"No, there isn't","Yes, there is any","Yes, they is","No, there aren't"};
        questionBox=createQuestion(6,"Is there any food left? ----.",o6);
        gridPane.add(questionBox, 1, 0);

        String[] o7= {"they","them","it","some"};
        questionBox=createQuestion(7,"My favorite painters are Manet and Renoir but \n" +
                "John doesn't like ---- at all.",o7);
        gridPane.add(questionBox, 1, 1);

        String[] o8 = {"many","a lot","much","the many"};
        questionBox=createQuestion(8,"There aren't ---- people here today.",o8);
        gridPane.add(questionBox, 1, 2);

        String[] o9 = {"some children","any children","a children","one children"};
        questionBox=createQuestion(9,"We haven't got ----.",o9);
        gridPane.add(questionBox, 1, 3);

        String[] o10 = {"don't speak","not","don't","speak not"};
        questionBox=createQuestion(10,"Do you speak English? No, I ----.",o10);
        gridPane.add(questionBox, 1, 4);


        Button checkButton = new Button("Check Answers");
        checkButton.setOnAction(event -> {
            calculateCorrectSelections(gridPane);

        });
        gridPane.add(checkButton, 0, 10);


        Button nextButton = new Button("Next Page");

        nextButton.setOnAction(e->{
            primaryStage.setScene(new Page4(primaryStage));
        });
        gridPane.add(generateResultsButton,0,11);
        gridPane.add(nextButton,0,12);

        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(gridPane);
        scrollPane.setFitToWidth(true);

        return scrollPane;

    }





    private static VBox createQuestion(int questionNumber,String question,String[] options) {
        VBox questionBox = new VBox();
        questionBox.setSpacing(5);

        String str = "Question " + questionNumber + ": "+question;
        Label questionLabel = new Label(str);
        questionLabel.setFont(Font.font("Arial" ,24));
        questionBox.getChildren().add(questionLabel);

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton option1 = new RadioButton("A. "+options[0]);
        option1.setFont(Font.font(18));
        option1.setToggleGroup(toggleGroup);

        RadioButton option2 = new RadioButton("B. "+options[1]);
        option2.setFont(Font.font(18));
        option2.setToggleGroup(toggleGroup);

        RadioButton option3 = new RadioButton("C. "+options[2]);
        option3.setFont(Font.font(18));
        option3.setToggleGroup(toggleGroup);

        RadioButton option4 = new RadioButton("D. "+options[3]);
        option4.setFont(Font.font(18));
        option4.setToggleGroup(toggleGroup);

        questionBox.getChildren().addAll(option1, option2, option3, option4);

        return questionBox;
    }




    private static void calculateCorrectSelections(GridPane gridPane) {
        correctSelections = 0;

        for (int i = 0; i < 10; i++) {
            VBox questionBox = (VBox) gridPane.getChildren().get(i);
            ToggleGroup toggleGroup = ((RadioButton) questionBox.getChildren().get(1)).getToggleGroup();
            RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();


            if (selectedRadioButton != null ) {
                switch (i+1){
                    case 1:
                    case 5:
                        if(selectedRadioButton.getText().startsWith("D.")){
                        correctSelections++;
                    }
                        break;
                    case 2:
                    case 6:
                    case 8:
                        if(selectedRadioButton.getText().startsWith("A.")){
                            correctSelections++;
                        }
                        break;
                    case 3:
                    case 7:
                    case 9:
                        if(selectedRadioButton.getText().startsWith("B.")){
                            correctSelections++;
                        }
                        break;
                    case 4:
                    case 10:
                        if(selectedRadioButton.getText().startsWith("C.")){
                            correctSelections++;
                        }
                        break;
                }
            }
        }
        Stage resultStage = new Stage();
        resultStage.initOwner(primaryStage);
        resultStage.initStyle(StageStyle.UTILITY);
        resultStage.initModality(Modality.APPLICATION_MODAL);

        String str = "Correct selections of 10 : " + correctSelections;
        Label label = new Label(str);
        label.setFont(Font.font(18));

        Scene scene = new Scene(label);
        resultStage.setScene(scene);
        resultStage.setTitle("Grade");
        resultStage.setResizable(false);
        resultStage.showAndWait();


    }

    @Override
    public void printResults() {
        Stage resultStage = new Stage();
        resultStage.initOwner(primaryStage);
        resultStage.initStyle(StageStyle.UTILITY);
        resultStage.initModality(Modality.APPLICATION_MODAL);

        String str = "1. Where's\n" +
                "2. Whose\n" +
                "3. There are\n" +
                "4. has got\n" +
                "5. How many\n" +
                "6. No, there isn't\n" +
                "7. them\n" +
                "8. many\n" +
                "9. any children\n" +
                "10. don't";
        Label label = new Label(str);

        Scene scene = new Scene(label);
        resultStage.setScene(scene);
        resultStage.setTitle("Results");
        resultStage.setResizable(false);
        resultStage.showAndWait();

    }

}