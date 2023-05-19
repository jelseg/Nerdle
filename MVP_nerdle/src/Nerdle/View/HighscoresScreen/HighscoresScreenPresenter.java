package Nerdle.View.HighscoresScreen;

import Nerdle.Model.Difficulty;
import Nerdle.Model.HighScoreList;
import Nerdle.Model.User;
import Nerdle.View.UISettings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.Map;

public class HighscoresScreenPresenter {

    private HighScoreList model;
    private HighscoresScreenView view;
    private UISettings uiSettings;

    //to simplify putting columns on (in)visible
    private Map<Difficulty,TableColumn<User,Double>> avgColumns;
    private Map<Difficulty,TableColumn<User,Integer>> scoreColumns;
    private TableColumn<User,Double> averageColumn;
    private TableColumn<User,Integer> scoreColum;

    public HighscoresScreenPresenter(HighscoresScreenView view, UISettings uiSettings){
        model = new HighScoreList();
        this.view = view;
        System.out.println(model);
        this.uiSettings = uiSettings;
        addEventHandlers();
        update();

    }

    private void addEventHandlers(){

        //update columns when other difficulty is chosen
        view.getDifficultyComboBox().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateColumns();
            }
        });

        view.getScoreTypeCombobox().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                updateColumns();
            }
        });
    }

    private void update(){
        setTableData();
        updateColumns();
    }

    private void updateColumns(){
        Difficulty currentDiff = view.getDifficultyComboBox().getValue();
        String scoreType = view.getScoreTypeCombobox().getValue();

        //hide all columns
        for (Difficulty difficulty: Difficulty.values()){
            avgColumns.get(difficulty).setVisible(false);
            scoreColumns.get(difficulty).setVisible(false);
        }

        if (scoreType.equalsIgnoreCase("Average number of attempts")){
            scoreColum.setVisible(false);
            averageColumn.setVisible(true);
            avgColumns.get(currentDiff).setVisible(true);
        } else {
            scoreColum.setVisible(true);
            averageColumn.setVisible(false);
            scoreColumns.get(currentDiff).setVisible(true);
        }

    }

    private void setTableData(){
        TableView table = view.getTable();
        DecimalFormat df2 = new DecimalFormat(".##");

        //user name column
        TableColumn<User,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(
                u -> new SimpleStringProperty(u.getValue().getName())
        );
        nameColumn.setPrefWidth(0.5);

        //tot average column
        averageColumn = new TableColumn<>("avg #att");
        averageColumn.setCellValueFactory(
                u -> new SimpleDoubleProperty(u.getValue().getAvgAttempts()).asObject()
        );
        averageColumn.setPrefWidth(0.5);
        //set formatting of the double value:
        averageColumn.setCellFactory(c -> new TableCell<>(){
            @Override
            protected void updateItem(Double number, boolean empty){
                super.updateItem(number, empty);
                if (number == null || empty) {
                    setText(null);
                } else if (number==Double.MAX_VALUE) {
                    setText("");
                } else {
                    setText(String.format("%.2f", number.doubleValue()));
                }
            }
        });

        //tot scoreColumn
        scoreColum = new TableColumn<>("Total Score");
        scoreColum.setCellValueFactory(
                u -> new SimpleIntegerProperty(u.getValue().getScore()).asObject()
        );

        table.getColumns().addAll(nameColumn,averageColumn,scoreColum);

        avgColumns = new EnumMap<Difficulty, TableColumn<User, Double>>(Difficulty.class);
        scoreColumns = new EnumMap<Difficulty, TableColumn<User, Integer>>(Difficulty.class);
        // Add columns for all difficulties:
        for (Difficulty difficulty:Difficulty.values()){

            //create column for average
            TableColumn<User,Double> currentCol = new TableColumn<>("Avg #att " + difficulty.toString());
            currentCol.setCellValueFactory(
                    u -> new SimpleDoubleProperty(u.getValue().getAvgAttempts(difficulty)).asObject()
            );
            currentCol.setPrefWidth(0.5);
            //set formatting of the double value:
            currentCol.setCellFactory(c -> new TableCell<>(){
                @Override
                protected void updateItem(Double number, boolean empty){
                    super.updateItem(number, empty);
                    if (number == null || empty) {
                        setText(null);
                    } else if (number==Double.MAX_VALUE) {
                        setText("");
                    } else {
                        setText(String.format("%.2f", number.doubleValue()));
                    }
                }
            });

            //add column to table
            table.getColumns().add(currentCol);
            //add to map for later use
            avgColumns.put(difficulty,currentCol);


            //score col
            TableColumn<User,Integer> cScoreCol = new TableColumn<>("Score " + difficulty);
            cScoreCol.setCellValueFactory(
                    u -> new SimpleIntegerProperty(u.getValue().getScore(difficulty)).asObject()
            );

            table.getColumns().add(cScoreCol);
            scoreColumns.put(difficulty,cScoreCol);

        }

        //add data from model
        table.getItems().addAll(model.getByAverageAttempts());
    }
}
