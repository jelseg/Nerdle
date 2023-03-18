package Nerdle.View;

import javafx.stage.Screen;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UISettings {

    private int resX;
    private int resY;
    private int insetsMargin;
    public static final char FILE_SEPARATOR = System.getProperties().getProperty("file.separator").charAt(0);
    private String ApplicationName;
    private String homeDir;
    private String defaultCss = "themes02.css";
    private Path styleSheetPath = Paths.get("resources"+FILE_SEPARATOR+"stylesheets"+FILE_SEPARATOR+defaultCss);
    private Path AboutImagePath = Paths.get("resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"Nerdle_logo_backdrop.png");
    private Path applicationIconPath = Paths.get("resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"Nerdle_logo_backdrop.png");
    private Path startScreenImagePath = Paths.get("resources"+FILE_SEPARATOR+"images"+FILE_SEPARATOR+"Nerdle_logo_backdrop.png");
    private Path rulesTextPath = Paths.get("resources"+FILE_SEPARATOR+"other"+FILE_SEPARATOR+"rules.txt");

    public UISettings() {
        this.resX= (int) Screen.getPrimary().getVisualBounds().getWidth();
        this.resY = (int) Screen.getPrimary().getVisualBounds().getHeight();
        this.insetsMargin = this.getLowestRes()/100;
        this.homeDir = System.getProperties().getProperty("user.dir");
        this.ApplicationName = "Nerdle";
    };

    public int getResX () {return this.resX;}

    public int getResY () {return this.resY;}

    public int getInsetsMargin () {return this.insetsMargin;}

    public int getLowestRes () {return (resX>resY?resX:resY);}

    public boolean styleSheetAvailable (){return Files.exists(styleSheetPath);}

    public Path getStyleSheetPath () {return this.styleSheetPath;}

        public void setStyleSheetPath (Path styleSheetPath) {this.styleSheetPath = styleSheetPath;}

    public String getHomeDir () {return this.homeDir;}

    public Path getApplicationIconPath () {return this.applicationIconPath;}

    public Path getStartScreenImagePath () {return this.startScreenImagePath;}

    public Path getAboutImagePath () {return this.AboutImagePath;}

    public Path getRulesTextPath() {return this.rulesTextPath;}

    public String getApplicationName () {return this.ApplicationName;}

}
