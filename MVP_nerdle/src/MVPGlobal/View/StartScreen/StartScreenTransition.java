package MVPGlobal.View.StartScreen;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;

public class StartScreenTransition extends Transition {

    private final StartScreenView view;
    private int maxDuration;

    public StartScreenTransition(StartScreenView view, int maxDuration) {
        this.view = view;
        this.maxDuration = maxDuration;
        this.setCycleDuration(Duration.seconds(maxDuration));
        this.setCycleCount(1);
        this.setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double frac) {
        this.view.getTimeDisplay().setText(String.format("Loading: %.1f", frac * 100));
        this.view.getTimeProgress().setProgress(frac);
    }
}
