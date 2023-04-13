package Nerdle.View.NewUserScreen;

import Nerdle.Model.MVPModel;
import Nerdle.View.UISettings;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;

public class NewUserScreenView extends BorderPane {
    private Label label;
    private TextField username;
    private Button btnUsername;

  public NewUserScreenView(){
      initialiseNodes();
      layoutNodes();
  }

  private void initialiseNodes(){
        this.label=new Label("Fill in username:");
        this.username=new TextField();
        this.btnUsername=new Button("Start new game");
  }
  private void layoutNodes(){
        BorderPane.setMargin(this.label,new Insets(10));
        BorderPane.setMargin(this.username,new Insets(10));
        this.setLeft(label);
        this.setRight(username);

        BorderPane bpButton=new BorderPane();
        bpButton.setCenter(btnUsername);
        this.setBottom(bpButton);

        this.setPadding(new Insets(15));
  }


  //getters
    public Label getLabel() {
        return label;
    }

    public TextField getUsername() {
        return username;
    }

    public Button getBtnUsername() {
        return btnUsername;
    }
}
