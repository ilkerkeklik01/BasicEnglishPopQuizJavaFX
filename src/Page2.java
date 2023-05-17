import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Page2 extends EachPage implements Gradeable{

    private static Button myGradebutton = new Button("Generate My Grade");

    private static Button generateResultsButton = new Button("Generate Results");

    public Page2(Stage stage) {
        super(getPage(),stage);
        generateResultsButton.setOnAction(e->{
            printResults();
        });

    }


    private static Pane getPage() {
        BorderPane root = new BorderPane();
        try {
            Image image = new Image("backgrounds/background2.jpg");
            root.setBackground(new Background(new BackgroundImage(image,null,null,null,null)));
        }catch (Exception e){
            e.printStackTrace();
        }
        Label label = new Label();
        String str = "Opera refers to a dramatic art form, originating in Europe, in which the emotional content is \n" +
                "conveyed to the audience as much through music, both vocal and instrumental, as it is through the \n" +
                "lyrics. By contrast, in musical theater an actor's dramatic performance is primary, and the music \n" +
                "plays a lesser role. The drama in opera is presented using the primary elements of theater such as \n" +
                "scenery, costumes, and acting. However, the words of the opera, or libretto, are sung rather \n" +
                "than spoken. The singers are accompanied by a musical ensemble ranging from a small instrumental \n" +
                "ensemble to a full symphonic orchestra.";
        label.setText(str);
        label.setFont(Font.font("Arial",24));
        Label info = new Label();
        info.setFont(Font.font("Arial", FontWeight.BOLD,24));
        info.setText("Choose the correct options according to the text below.");
        VBox vBox = new VBox(info,label);
        vBox.setSpacing(10);

        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setHalignment(HPos.CENTER);
        separator.setPadding(new Insets(30));
        vBox.getChildren().add(separator);
        root.setTop(vBox);

        HBox hBox = new HBox();

        VBox vBox1 = new VBox();
        Label q1 = new Label("1. It is pointed out in the reading that opera ----.");
        vBox1.setSpacing(5);
        vBox1.getChildren().add(q1);
        vBox1.getChildren().add(createRadioButtonGroup("has developed under the influence of musical theater",
                "is a drama sung with the accompaniment of an orchestra",
                "is not a high-budget production",
                "is often performed in Europe",
                "is the most complex of all the performing arts"));

        VBox vBox2 = new VBox();
        Label q2 = new Label("2. We can understand from the reading that ----.");
        vBox2.setSpacing(5);
        vBox2.getChildren().add(q2);
        vBox2.getChildren().add(createRadioButtonGroup("people are captivated more by opera than musical theater",
                "drama in opera is more important than the music",
                "orchestras in operas can vary considerably in size",
                " musical theater relies above all on music",
                " there is argument over whether the music is \nimportant or the words in opera"));

        VBox vBox3 = new VBox();
        Label q3 = new Label("3. It is stated in the reading that ----.");
        vBox3.setSpacing(5);
        vBox3.getChildren().add(q3);
        vBox3.getChildren().add(createRadioButtonGroup("acting and costumes are secondary to music in musical theater",
                "many people find musical theater more captivating than opera",
                "music in musical theater is not as important as it is in opera",
                "an opera requires a huge orchestra as well as a large choir",
                "opera doesn't have any properties in common with musical theater"));


        root.setPadding(new Insets(30));
        hBox.getChildren().addAll(vBox1,vBox2,vBox3);

        root.setCenter(hBox);

        Button nextButton = new Button("Next Page");


        HBox buttonsHBox = new HBox(generateResultsButton,myGradebutton,nextButton);



        ToggleGroup toggleGroup1 = ((RadioButton)((VBox)vBox1.getChildren().get(1)).getChildren().get(0)).getToggleGroup();
        ToggleGroup toggleGroup2 = ((RadioButton)((VBox)vBox2.getChildren().get(1)).getChildren().get(0)).getToggleGroup();
        ToggleGroup toggleGroup3 = ((RadioButton)((VBox)vBox3.getChildren().get(1)).getChildren().get(0)).getToggleGroup();
        String[] user1 = {""};
        String[] user2={""};
        String[] user3={""};

        toggleGroup1.selectedToggleProperty().addListener((o, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                String selectedText = selectedRadioButton.getText();
                user1[0] = selectedText;
            }
        });
        toggleGroup2.selectedToggleProperty().addListener((o, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                String selectedText = selectedRadioButton.getText();
                user2[0] = selectedText;
            }
        });
        toggleGroup3.selectedToggleProperty().addListener((o, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                String selectedText = selectedRadioButton.getText();
                user3[0] = selectedText;
            }
        });
        myGradebutton.setOnAction(e-> {

            myGradebuttonClicked(user1[0],user2[0],user3[0]);
        });



        buttonsHBox.setSpacing(10);
        root.setBottom(buttonsHBox);

        nextButton.setOnAction(e->{
            primaryStage.setScene(new Page3(primaryStage));
        });



        return root;
    }

    private static void myGradebuttonClicked(String user1,String user2,String user3) {


        Stage resultStage = new Stage();
        resultStage.initOwner(primaryStage);
        resultStage.initStyle(StageStyle.UTILITY);
        resultStage.initModality(Modality.APPLICATION_MODAL);

        int corrects = 0;
        System.out.println("user1"+user1);

        if(user1.equals("is a drama sung with the accompaniment of an orchestra")) corrects++;
        if(user2.equals("orchestras in operas can vary considerably in size")) corrects++;
        if(user3.equals("music in musical theater is not as important as it is in opera")) corrects++;


        Label label = new Label(corrects+" of your answer(s) are correct!");

        Scene scene = new Scene(label);
        resultStage.setScene(scene);
        resultStage.setTitle("Grade");
        resultStage.setResizable(false);
        resultStage.showAndWait();

    }

    private static VBox createRadioButtonGroup(String o1,String o2,String o3,String o4,String o5) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton radioButton1 = new RadioButton(o1);
        radioButton1.setToggleGroup(toggleGroup);

        RadioButton radioButton2 = new RadioButton(o2);
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton(o3);
        radioButton3.setToggleGroup(toggleGroup);

        RadioButton radioButton4 = new RadioButton(o4);
        radioButton4.setToggleGroup(toggleGroup);

        RadioButton radioButton5 = new RadioButton(o5);
        radioButton5.setToggleGroup(toggleGroup);

        vbox.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4, radioButton5);

        return vbox;
    }

    @Override
    public void printResults() {
        Stage initialStage = (Stage) getRoot().getScene().getWindow();

        Stage resultStage = new Stage();
        resultStage.initOwner(initialStage);
        resultStage.initStyle(StageStyle.UTILITY);
        resultStage.initModality(Modality.APPLICATION_MODAL);

        VBox vBox = new VBox();
        Label label1 = new Label("Question 1 --> is a drama sung with the accompaniment of an orchestra");
        Label label2 = new Label("Question 2 --> orchestras in operas can vary considerably in size");
        Label label3 = new Label("Question 3 --> music in musical theater is not as important as it is in opera");
        vBox.getChildren().addAll(label1,label2,label3);

        Scene scene = new Scene(vBox);
        resultStage.setScene(scene);
        resultStage.setTitle("Results");
        resultStage.setResizable(false);
        resultStage.showAndWait();

    }



}