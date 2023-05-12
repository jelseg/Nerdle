package Nerdle.View.HighscoresScreen;

import Nerdle.Model.HighScoreList;
import Nerdle.Model.User;
import Nerdle.View.UISettings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.text.DecimalFormat;

public class HighscoresScreenPresenter {

    private HighScoreList model;
    private HighscoresScreenView view;
    private UISettings uiSettings;

    public HighscoresScreenPresenter(HighscoresScreenView view, UISettings uiSettings){
        model = new HighScoreList();
        this.view = view;
        System.out.println(model);
        this.uiSettings = uiSettings;
        addEventHandlers();
        update();

    }

    private void addEventHandlers(){

    }

    private void update(){
        setTableData();
    }

    private void setTableData(){
        TableView table = view.getTable();
        DecimalFormat df2 = new DecimalFormat(".##");

        TableColumn<User,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(
                u -> new SimpleStringProperty(u.getValue().getName())
        );
        nameColumn.setPrefWidth(0.5);

        TableColumn<User,Double> averageColumn = new TableColumn<>("Average #attempts");
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

        table.getColumns().addAll(nameColumn,averageColumn);

        table.getItems().addAll(model.getByAverageAttempts());
    }
}
