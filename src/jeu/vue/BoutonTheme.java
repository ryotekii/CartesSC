package jeu.vue;

import java.util.prefs.Preferences;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;

public class BoutonTheme extends ToggleButton {
    private static final Preferences prefs = Preferences.userNodeForPackage(BoutonTheme.class);
    private static final String MODE = "thème";

    public BoutonTheme(Scene scene) {
        super("Thème");
        boolean sombre = prefs.getBoolean(MODE, false);
        setSelected(sombre);
        appliquerTheme(scene, sombre);

        setOnAction(e -> {
            boolean nouveauMode = isSelected();
            prefs.putBoolean(MODE, nouveauMode);
            appliquerTheme(scene, nouveauMode);
        });
    }

    private void appliquerTheme(Scene scene, boolean sombre) {
        scene.getStylesheets().clear();
        if (sombre) {
            scene.getStylesheets().add(getClass().getResource("../controlleur/clair.css").toExternalForm());
        } else {
            scene.getStylesheets().add(getClass().getResource("../controlleur/test sombre.css").toExternalForm());
        }
    }
}
