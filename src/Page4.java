import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Page4 extends EachPage implements Gradeable {

    private static int correctSelections = 0;
    private static Button generateResultsButton = new Button("Generate Results");
    public Page4(Stage stage) {
        super(getPage(), stage);
        generateResultsButton.setOnAction(e->{
            printResults();
        });
    }

    private static GridPane getPage() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        try {
            Image image = new Image("backgrounds/background2.jpg");
            gridPane.setBackground(new Background(new BackgroundImage(image,null,null,null,null)));
        }catch (Exception e){
            e.printStackTrace();
        }

        Question[] questions = {
                new Question("A RIVER is bigger than a STREAM.", true),
                new Question("There are one thousand years in a CENTURY.", false),
                new Question("FOUNDED is the past tense of FOUND.", true),
                new Question("ANSWER can be used as a noun and a verb.", true),
                new Question("SCARLET is a brilliant red colour.", true),
                new Question("USED TO DOING and USED TO DO mean the same thing.", false),
                new Question("You can use IMPROVE as a noun and as a verb.", false),
                new Question("DOZEN is equivalent to 20.", false),
                new Question("The past tense of FIND is FOUND.", true),
                new Question("EQUIVALENT TO is (more or less) the same as EQUAL TO.", true),
                new Question("IRON is a type of metal.", true),
                new Question("PERPENDICULAR lines intersect at a 45-degree angle.", false),
                new Question("SYNONYM and ANTONYM are opposites.", false),
                new Question("EXCEL is a software for word processing.", false),
                new Question("The SUN revolves around the EARTH.", false),
                new Question("OXFORD is a city in France.", false),
                new Question("LEONARDO DA VINCI painted the Mona Lisa.", true),
                new Question("SPAIN is located in South America.", false),
                new Question("SYDNEY is the capital of Australia.", false),
                new Question("The MOON has its own light.", false),new Question("SYNONYM and ANTONYM are opposite in meaning.", true),
                new Question("DESSERT is a sweet course served at the end of a meal.", true),
                new Question("The word 'RELIABLE' is an adjective.", true),
                new Question("PERCENTAGE is a fraction expressed as a part of 100.", true),
                new Question("INFINITY is the largest number that exists.", false),
                new Question("SUBTRACT is the opposite operation of multiplication.", false),
                new Question("The word 'INFER' means to state directly and with certainty.", false),
                new Question("SYMMETRY is a balance or similarity in shape, size, or position.", true),
                new Question("The abbreviation 'USA' stands for United Soviet Alliance.", false),
                new Question("SYNTAX is the arrangement of words and phrases to create well-formed sentences.", true),
                new Question("HUMIDITY refers to the amount of water vapor in the air.", true),
                new Question("CONSEQUENCE means the cause or origin of something.", false),
                new Question("ACCELERATION is the rate at which an object changes its velocity.", true),
                new Question("A HERBIVORE is an animal that eats only meat.", false),
                new Question("The word 'FACETIOUS' means treating serious issues with inappropriate humor.", true),
                new Question("The word 'PUNCTUAL' means arriving or done at the expected or planned time.", true),
                new Question("CAPITAL letters are also known as uppercase letters.", true),
                new Question("The EQUATOR is an imaginary line that divides the Earth into Northern and Southern Hemispheres.", true),
                new Question("The word 'EXPONENT' refers to a symbol that indicates the operation of multiplication.", false),
                new Question("ANALOGY is a comparison between two things for the purpose of explanation or clarification.", true)
        };

        int column = 0;
        int row = 0;

        int half = questions.length/2;

        for (int i = 0; i < questions.length; i++) {
            Label questionLabel = new Label(questions[i].getQuestionText());
            questionLabel.setFont(Font.font("Arial", FontWeight.BOLD,12));
            ComboBox<String> selectionComboBox = new ComboBox<>(FXCollections.observableArrayList("True", "False"));

            gridPane.add(questionLabel, column, row);
            gridPane.add(selectionComboBox, column + 1, row);

            row++;

            if (row % half == 0) {
                column += 2;
                row = 0;
            }

        }

        Button checkButton = new Button("Check Answers");
        checkButton.setOnAction(event -> {
            calculateCorrectSelections(questions, gridPane);
            System.out.println("Correct selections: " + correctSelections);
        });

        gridPane.add(checkButton, 0, half+1);

        Button exitButton = new Button("Exit");

        exitButton.setOnAction(e->{
            System.exit(0);
        });
        gridPane.add(generateResultsButton,1,half+1);
        gridPane.add(exitButton,2,half+1);


        return gridPane;
    }

    private static void calculateCorrectSelections(Question[] questions, GridPane gridPane) {
        correctSelections = 0;
        int half = questions.length/2;
        for (int i = 0; i < questions.length; i++) {
            int column = i / half * 2;
            int row = i % half;


            ComboBox<String> selectionComboBox = (ComboBox<String>) gridPane.getChildren().get(i*2+1);
            String selectedOption = selectionComboBox.getSelectionModel().getSelectedItem();

            if (selectedOption != null && selectedOption.equalsIgnoreCase(questions[i].isCorrectAnswer() ? "True" : "False")) {
                correctSelections++;
            }
        }
        Stage resultStage = new Stage();
        resultStage.initOwner(primaryStage);
        resultStage.initStyle(StageStyle.UTILITY);
        resultStage.initModality(Modality.APPLICATION_MODAL);

        String str = "Correct selections of 40 : " + correctSelections;
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

        String str = "A RIVER is bigger than a STREAM., true\n"+
                "There are one thousand years in a CENTURY., false\n"+
                "FOUNDED is the past tense of FOUND., true\n"+
                "ANSWER can be used as a noun and a verb., true\n"+
                "SCARLET is a brilliant red colour., true\n"+
                "USED TO DOING and USED TO DO mean the same thing., false\n"+
                "You can use IMPROVE as a noun and as a verb., false\n"+
                "DOZEN is equivalent to 20., false\n"+
                "The past tense of FIND is FOUND., true\n"+
                "EQUIVALENT TO is (more or less) the same as EQUAL TO., true\n"+
                "IRON is a type of metal., true\n"+
                "PERPENDICULAR lines intersect at a 45-degree angle., false\n"+
                "SYNONYM and ANTONYM are opposites., false\n" +
                "EXCEL is a software for word processing., false\n"+
                "The SUN revolves around the EARTH., false\n"+
                "OXFORD is a city in France., false\n"+
                "LEONARDO DA VINCI painted the Mona Lisa., true\n"+
                "SPAIN is located in South America., false\n"+
                "SYDNEY is the capital of Australia., false\n"+
                "The MOON has its own light., false"+
                "SYNONYM and ANTONYM are opposite in meaning., true\n"+
                "DESSERT is a sweet course served at the end of a meal., true\n"+
                "The word 'RELIABLE' is an adjective., true\n"+
                "PERCENTAGE is a fraction expressed as a part of 100., true\n"+
                "INFINITY is the largest number that exists., false\n"+
                "SUBTRACT is the opposite operation of multiplication., false\n"+
                "The word 'INFER' means to state directly and with certainty., false\n"+
                "SYMMETRY is a balance or similarity in shape, size, or position., true\n"+
                "The abbreviation 'USA' stands for United Soviet Alliance., false\n"+
                "SYNTAX is the arrangement of words and phrases to create well-formed sentences., true\n"+
                "HUMIDITY refers to the amount of water vapor in the air., true\n"+
                "CONSEQUENCE means the cause or origin of something., false\n"+
                "ACCELERATION is the rate at which an object changes its velocity., true\n"+
                "A HERBIVORE is an animal that eats only meat., false\n"+
                "The word 'FACETIOUS' means treating serious issues with inappropriate humor., true\n"+
                "The word 'PUNCTUAL' means arriving or done at the expected or planned time., true\n"+
                "CAPITAL letters are also known as uppercase letters., true\n"+
                "The EQUATOR is an imaginary line that divides the Earth into Northern and Southern Hemispheres., true\n"+
                "The word 'EXPONENT' refers to a symbol that indicates the operation of multiplication., false\n"+
                "ANALOGY is a comparison between two things for the purpose of explanation or clarification., true";

        Label label = new Label(str);

        Scene scene = new Scene(label);
        resultStage.setScene(scene);
        resultStage.setTitle("Results");
        resultStage.setResizable(false);
        resultStage.showAndWait();
    }
}

