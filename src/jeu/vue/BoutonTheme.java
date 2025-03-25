package jeu.vue;

import java.util.prefs.Preferences;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;

public class BoutonTheme extends ToggleButton {
    private static final Preferences prefs = Preferences.userNodeForPackage(BoutonTheme.class);
    private static final String MODE = "theme";

    /**
     * Le constructeur.
     * @param scene la scène.
     */
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

    /**
     * Applique le thème sombre ou clair à la scène en fonction de l'état du bouton.
     * @param scene la scène.
     * @param sombre l'état du bouton (activé ou non).
     */
    private void appliquerTheme(Scene scene, boolean sombre) {
        scene.getStylesheets().clear();
        if (sombre) {
            scene.getStylesheets().add(getClass().getResource("/jeu/controlleur/clair.css").toExternalForm());
        } else {
            scene.getStylesheets().add(getClass().getResource("/jeu/controlleur/test sombre.css").toExternalForm());
        }
    }
}
