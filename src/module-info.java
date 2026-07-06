module test.module {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.prefs;
    requires java.sql;

    opens jeu.controlleur to javafx.fxml;
    opens jeu.vue to javafx.fxml;
    opens jeu.modele to javafx.fxml;
    exports jeu.controlleur;
    exports jeu.modele;
    exports jeu.vue;
}
