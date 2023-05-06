package Nerdle.View.MainScreen;

import Nerdle.Model.*;
import Nerdle.View.AboutScreen.*;
import Nerdle.View.FalseGuessScreen.FalseGuessScreenPresenter;
import Nerdle.View.FalseGuessScreen.FalseGuessScreenView;
import Nerdle.View.RulesScreen.*;
import Nerdle.View.SettingsScreen.*;
import Nerdle.View.UISettings;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;

public class MainScreenPresenter {

    private Nerdle model;
    private MainScreenView view;
    private UISettings uiSettings;
    private MainScreenTransition mainScreenTransition;
    int column=0;
    int row=0;

    public MainScreenPresenter(Nerdle model, MainScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        column =0;
        row =0;
        initOverzicht();

        updateView();
        EventHandlers();
    }

    private void updateView() {

        System.out.println(model.getCurrentGuess().getCombinationString());

        updateOverzicht();
        updateGuessesView();
     }

    private void EventHandlers() {

        addOverzichtEventHandlers();


        view.getSettingsItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingsView settingsView = new SettingsView(uiSettings);
                SettingsPresenter presenter = new SettingsPresenter(new MVPModel(), settingsView, uiSettings);
                Stage settingsStage = new Stage();
                settingsStage.setTitle("Settings");
                settingsStage.initOwner(view.getScene().getWindow());
                settingsStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(settingsView);
                settingsStage.setScene(scene);
                settingsStage.setTitle(uiSettings.getApplicationName() + " - Settings");
                settingsStage.setX(view.getScene().getWindow().getX() + uiSettings.getResX() / 10);
                settingsStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 10);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        settingsStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                settingsView.getScene().getWindow().setHeight(7 * uiSettings.getResY() / 10);
                settingsView.getScene().getWindow().setWidth(7 * uiSettings.getResX() / 10);
                if (uiSettings.styleSheetAvailable()) {
                    settingsStage.getScene().getStylesheets().removeAll();
                    try {
                        settingsStage.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                settingsStage.showAndWait();
                if (uiSettings.styleSheetAvailable()) {
                    view.getScene().getStylesheets().removeAll();
                    try {
                        view.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
            }
        });
        view.getLoadItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Data File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Textfiles", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
                File selectedFile = fileChooser.showOpenDialog(view.getScene().getWindow());
                if ((selectedFile != null) ){ //^ (Files.isReadable(Paths.get(selectedFile.toURI())))) {
                    try {
                        List<String> input = Files.readAllLines(Paths.get(selectedFile.toURI()));
                        // begin implementeren ingelezen gegevens doorgeven aan model
                        for (int i = 0; i < input.size(); i++) {
                            String inputline = input.get(i);
                            String[] elementen = inputline.split(" ");
                        }
                        // einde implementeren ingelezen gegevens doorgeven aan model
                    } catch (IOException e) {
                        //
                    }
                } else {
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("Problem with the selected input file:");
                    errorWindow.setContentText("File is not readable");
                    errorWindow.showAndWait();
                }
            }
        });
        view.getSaveItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Data File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Textfiles", "*.txt"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));
                File selectedFile = fileChooser.showSaveDialog(view.getScene().getWindow());
                if ((selectedFile != null) ^ (Files.isWritable(Paths.get(selectedFile.toURI())))) {
                    try {
                        Files.deleteIfExists(Paths.get(selectedFile.toURI()));
                    } catch (IOException e) {
                        //
                    }
                    try (Formatter output = new Formatter(selectedFile)) {
                        // Begin implementeren wegschrijven model-gegevens
                        output.format("%s%n", "Here comes the data!");
                        output.format("%s%n", "First record");
                        output.format("%s%n", "...");
                        output.format("%s%n", "Last record");
                        // Einde implementeren wegschrijven model-gegevens
                    } catch (IOException e) {
                        //
                    }
                } else {
                    Alert errorWindow = new Alert(Alert.AlertType.ERROR);
                    errorWindow.setHeaderText("Problem with the selected output file:");
                    errorWindow.setContentText("File is not writable");
                    errorWindow.showAndWait();
                }
            }
        });
        view.getExitItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleCloseEvent(event);
            }
        });
        view.getAboutItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AboutScreenView aboutScreenView = new AboutScreenView(uiSettings);
                AboutScreenPresenter aboutScreenPresenter = new AboutScreenPresenter(new MVPModel(), aboutScreenView, uiSettings);
                Stage aboutScreenStage = new Stage();
                aboutScreenStage.initOwner(view.getScene().getWindow());
                aboutScreenStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(aboutScreenView);
                aboutScreenStage.setScene(scene);
                aboutScreenStage.setTitle(uiSettings.getApplicationName() + " - About");
                aboutScreenStage.setX(view.getScene().getWindow().getX() + uiSettings.getResX() / 10);
                aboutScreenStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 10);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        aboutScreenStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                aboutScreenView.getScene().getWindow().setHeight(uiSettings.getResY() / 2);
                aboutScreenView.getScene().getWindow().setWidth(uiSettings.getResX() / 2);
                if (uiSettings.styleSheetAvailable()) {
                    aboutScreenView.getScene().getStylesheets().removeAll();
                    try {
                        aboutScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    } catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                aboutScreenStage.showAndWait();
            }});
        view.getRulesItem().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RulesScreenView rulesScreenView = new RulesScreenView(uiSettings);
                RulesScreenPresenter rulesScreenPresenter = new RulesScreenPresenter(new MVPModel(), rulesScreenView, uiSettings);
                Stage rulesScreenStage = new Stage();
                rulesScreenStage.initOwner(view.getScene().getWindow());
                rulesScreenStage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = new Scene(rulesScreenView);
                rulesScreenStage.setScene(scene);
                rulesScreenStage.setTitle(uiSettings.getApplicationName()+ " - Info");
                rulesScreenStage.setX(view.getScene().getWindow().getX() + uiSettings.getResX() / 10);
                rulesScreenStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 10);
                if (Files.exists(uiSettings.getApplicationIconPath())) {
                    try {
                        rulesScreenStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                    }
                    catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                } else { // do nothing, if ApplicationIconImage is not available, program can continue
                }
                rulesScreenView.getScene().getWindow().setHeight(uiSettings.getResY()/2);
                rulesScreenView.getScene().getWindow().setWidth(uiSettings.getResX()/2);
                if (uiSettings.styleSheetAvailable()){
                    rulesScreenView.getScene().getStylesheets().removeAll();
                    try {
                        rulesScreenView.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                    }
                    catch (MalformedURLException ex) {
                        // do nothing, if toURL-conversion fails, program can continue
                    }
                }
                rulesScreenStage.showAndWait();
            }});
    }


    private void addOverzichtEventHandlers(){
        ImageView[][] guessesArray=view.guessesView.getGuessesArray();
        for (int i = 0; i < model.getOverzicht().getPossibilities().length; i++){
            EquationCharacter eqc = model.getOverzicht().getPossibility(i);

            CharacterTile currentTile = view.getOverzichtView().get(i);

            currentTile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    model.addToCurrentGuess(eqc);

                    /**
                    StringBuilder builder = new StringBuilder("images/buttons/lightgray");
                    switch (eqc.getOperation()){
                        case NUMBER: builder.append(eqc.getNumber()); break;
                        case EQUALS: builder.append("_equals"); break;
                        case PLUS: builder.append("_plus"); break;
                        case MINUS: builder.append("_minus"); break;
                        case DIVIDE: builder.append("_divide"); break;
                        case MULTIPLY: builder.append("_multiply"); break;
                    }
                    guessesArray[column][row].setImage(new Image(builder+".png"));


                    column++;
                     **/

                    updateView();
                }
            });
        }

        view.getOverzichtView().getEnterButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(!model.enterCurrentGuess()){
                    FalseGuessScreenView falseGuessScreenView = new FalseGuessScreenView();
                    FalseGuessScreenPresenter falseGuessScreenPresenter = new FalseGuessScreenPresenter(falseGuessScreenView, uiSettings);
                    Stage falseguesStage = new Stage();
                    falseguesStage.setTitle("False guess");
                    falseguesStage.initOwner(view.getScene().getWindow());
                    falseguesStage.initModality(Modality.APPLICATION_MODAL);
                    Scene scene = new Scene(falseGuessScreenView);
                    falseguesStage.setScene(scene);
                    falseguesStage.setTitle(uiSettings.getApplicationName() + " - False guess");
                    falseguesStage.setX(view.getScene().getWindow().getX() + uiSettings.getResX() / 10);
                    falseguesStage.setY(view.getScene().getWindow().getY() + uiSettings.getResY() / 10);
                    if (Files.exists(uiSettings.getApplicationIconPath())) {
                        try {
                            falseguesStage.getIcons().add(new Image(uiSettings.getApplicationIconPath().toUri().toURL().toString()));
                        } catch (MalformedURLException ex) {
                            // do nothing, if toURL-conversion fails, program can continue
                        }
                    } else { // do nothing, if ApplicationIconImage is not available, program can continue
                    }
                    falseGuessScreenView.getScene().getWindow().setHeight(uiSettings.getResY() / 5);
                    falseGuessScreenView.getScene().getWindow().setWidth(uiSettings.getResX() / 5);
                    if (uiSettings.styleSheetAvailable()) {
                        falseguesStage.getScene().getStylesheets().removeAll();
                        try {
                            falseguesStage.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                        } catch (MalformedURLException ex) {
                            // do nothing, if toURL-conversion fails, program can continue
                        }
                    }
                    falseguesStage.showAndWait();
                    if (uiSettings.styleSheetAvailable()) {
                        view.getScene().getStylesheets().removeAll();
                        try {
                            view.getScene().getStylesheets().add(uiSettings.getStyleSheetPath().toUri().toURL().toString());
                        } catch (MalformedURLException ex) {
                            // do nothing, if toURL-conversion fails, program can continue
                        }
                    }
                    //return;
                }

                /*
                for (column = 0; column < model.getCurrentGuess().getCombinationLength(); column++) {
                    String guessCharacter = String.valueOf(model.getCurrentGuess().getCombinationString().charAt(column));
                    String answerCharacter = String.valueOf(model.getAnswer().getCombinationString().charAt(column));
                    String answer = model.getAnswer().getCombinationString();
                    if (guessCharacter.equals(answerCharacter)) {

                        StringBuilder builder = new StringBuilder("images/results/green");
                        switch (guessCharacter) {
                            case "0": builder.append("0");break;
                            case "1": builder.append("1");break;
                            case "2": builder.append("2");break;
                            case "3": builder.append("3");break;
                            case "4": builder.append("4");break;
                            case "5": builder.append("5");break;
                            case "6": builder.append("6");break;
                            case "7": builder.append("7");break;
                            case "8": builder.append("8");break;
                            case "9": builder.append("9");break;
                            case "=": builder.append("_equals");break;
                            case "+": builder.append("_plus");break;
                            case "-": builder.append("_minus");break;
                            case "/": builder.append("_divide");break;
                            case "x": builder.append("_multiply");break;
                        }
                        builder.append(".png");
                        String imagepath=builder.toString();
                        guessesArray[column][row].setImage(new Image(imagepath));
                    }
                    else if (!answer.contains(guessCharacter)) {

                        StringBuilder builder = new StringBuilder("images/results/black");
                        switch (guessCharacter) {
                            case "0": builder.append("0");break;
                            case "1": builder.append("1");break;
                            case "2": builder.append("2");break;
                            case "3": builder.append("3");break;
                            case "4": builder.append("4");break;
                            case "5": builder.append("5");break;
                            case "6": builder.append("6");break;
                            case "7": builder.append("7");break;
                            case "8": builder.append("8");break;
                            case "9": builder.append("9");break;
                            case "=": builder.append("_equals");break;
                            case "+": builder.append("_plus");break;
                            case "-": builder.append("_minus");break;
                            case "/": builder.append("_divide");break;
                            case "x": builder.append("_multiply");break;
                        }
                        builder.append(".png");
                        String imagepath=builder.toString();
                        guessesArray[column][row].setImage(new Image(imagepath));
                    }

                    else if (answer.contains(guessCharacter)) {

                        StringBuilder builder = new StringBuilder("images/results/purple");
                        switch (guessCharacter) {
                            case "0": builder.append("0");break;
                            case "1": builder.append("1");break;
                            case "2": builder.append("2");break;
                            case "3": builder.append("3");break;
                            case "4": builder.append("4");break;
                            case "5": builder.append("5");break;
                            case "6": builder.append("6");break;
                            case "7": builder.append("7");break;
                            case "8": builder.append("8");break;
                            case "9": builder.append("9");break;
                            case "=": builder.append("_equals");break;
                            case "+": builder.append("_plus");break;
                            case "-": builder.append("_minus");break;
                            case "/": builder.append("_divide");break;
                            case "x": builder.append("_multiply");break;
                        }
                        builder.append(".png");
                        String imagepath=builder.toString();
                        guessesArray[column][row].setImage(new Image(imagepath));
                    }
                }

                 */
                //model.enterCurrentGuess();
                updateView();
                row++;
                column=0;
                if(model.isFoundIt()){
                    mainScreenTransition=new MainScreenTransition(view);
                    mainScreenTransition.play();
                } else if (model.isOver()) {

                    //placeholder

                }
            }
        });

        view.getOverzichtView().getBackSpaceButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                model.deleteFromCurrentGuess();
                //--column;
                //guessesArray[column][row].setImage(new Image("images/darkgrey.png"));
                updateView();
            }
        });

    }

    public void windowsHandler() {
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) { handleCloseEvent(event); }});
    }

    private void handleCloseEvent(Event event){
        final Alert stopWindow = new Alert(Alert.AlertType.CONFIRMATION);
        stopWindow.setHeaderText("You're closing the game.");
        stopWindow.setContentText("Are you sure? Unsaved data may be lost.");
        stopWindow.setTitle("WARNING!");
        stopWindow.getButtonTypes().clear();
        ButtonType noButton = new ButtonType("No");
        ButtonType yesButton = new ButtonType("Yes");
        stopWindow.getButtonTypes().addAll(yesButton, noButton);
        stopWindow.showAndWait();
        if (stopWindow.getResult() == null || stopWindow.getResult().equals(noButton)) {
            event.consume();
        } else {
            view.getScene().getWindow().hide();
        }
    }


    private void initOverzicht(){
        for(EquationCharacter eqc: model.getOverzicht().getPossibilities()){
            if(eqc.getOperation() == EquationCharacter.Operation.NUMBER){
                view.getOverzichtView().addNumber(eqc);
            }
            else {
                view.getOverzichtView().addOperation(eqc);
            }
        }
    }

    private void updateOverzicht(){



        for (int i = 0; i < model.getOverzicht().getPossibilities().length; i++) {
            EquationCharacter eqc = model.getOverzicht().getPossibility(i);

            CharacterTile currentTile = view.getOverzichtView().get(i);

            currentTile.changeCharacter(eqc);
        }

    }

    private void updateGuessesView(){
        List<Combination> allGuesses = model.getGuesses();
        Combination currentguess = model.getCurrentGuess();

        GuessesView gv = view.getGuessesView();

        //update all done guesses
        // for future updates: performance
        for (int i =0; i < allGuesses.size(); i++){
            EquationCharacter[] guessCharacters = allGuesses.get(i).getCharacters();
            for (int j = 0; j < guessCharacters.length; j++){
                gv.getElement(i,j).changeCharacter(guessCharacters[j]);
            }
        }

        int i = allGuesses.size();
        EquationCharacter[] curChars = currentguess.getCharacters();
        for (int j=0; j < curChars.length; j++){
            gv.getElement(i,j).changeCharacter(curChars[j]);
            //System.out.println(j + curChars[j].getOperation().toString());
        }

    }
}
