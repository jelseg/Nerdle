package Nerdle.View.HighscoresScreen;

import Nerdle.View.UISettings;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class HighscoresScreenView extends BorderPane {

    private TableView table;
    private UISettings uiSettings;

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

    }

    private void layoutNodes(){

        setCenter(table);
    }

    TableView getTable(){
        return table;
    }

}
