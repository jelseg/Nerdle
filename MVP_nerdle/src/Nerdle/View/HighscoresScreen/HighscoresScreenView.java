package Nerdle.View.HighscoresScreen;

import Nerdle.Model.Difficulty;
import Nerdle.View.UISettings;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HighscoresScreenView extends BorderPane {

    private TableView table;
    private UISettings uiSettings;

    private ComboBox<Difficulty> difficultyComboBox;
    private ComboBox<String> scoreTypeCombobox;

    public HighscoresScreenView(){
        initialiseNodes();
        layoutNodes();
    }

    public HighscoresScreenView(UISettings uiSettings){
        this.uiSettings = uiSettings;

        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes(){

        table = new TableView();
        table.setEditable(false);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        difficultyComboBox = new ComboBox<>();
        difficultyComboBox.getItems().addAll(Difficulty.values());
        difficultyComboBox.getSelectionModel().select(Difficulty.NORMAL);

        scoreTypeCombobox = new ComboBox<>();
        scoreTypeCombobox.getItems().addAll("Average number of attempts","Score");
        scoreTypeCombobox.getSelectionModel().select(0);

    }

    private void layoutNodes(){
        HBox hBox = new HBox(scoreTypeCombobox,difficultyComboBox);
        hBox.setMaxWidth(Double.MAX_VALUE);
        setTop(hBox);
        setCenter(table);
    }

    TableView getTable(){
        return table;
    }

    ComboBox<Difficulty> getDifficultyComboBox(){return difficultyComboBox;}
    ComboBox<String> getScoreTypeCombobox(){return scoreTypeCombobox;}

}
