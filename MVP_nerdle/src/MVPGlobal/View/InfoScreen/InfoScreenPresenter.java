package MVPGlobal.View.InfoScreen;

import MVPGlobal.Model.MVPModel;
import MVPGlobal.View.UISettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.*;


public class InfoScreenPresenter {

    private MVPModel model;
    private InfoScreenView view;
    private UISettings uiSettings;

    public InfoScreenPresenter(MVPModel model, InfoScreenView view, UISettings uiSettings) {
        this.model = model;
        this.view = view;
        this.uiSettings = uiSettings;
        view.getInfoText().setText(ReadInfoFromFile());
        view.getBtnOk().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                view.getScene().getWindow().hide();
            }
        });
    }

    private String ReadInfoFromFile() {
        String infoTextInFile ="";
        try (BufferedReader reader = new BufferedReader(new FileReader(uiSettings.getInfoTextPath().toString()));){
            String line = "";
            String testString;
            while ((line = reader.readLine())!= null){
                infoTextInFile += line + "\n";
            }
        } catch (Exception ex) {
            // do nothing, if info.txt file can not be read or is incomplete, or ... a standard text will be return
        }
        return (infoTextInFile.compareTo("")==0)?"No info available":infoTextInFile;
    }
}
