module ec.edu.espol.ProyectoJuegoIIP {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.desktop;

    opens ec.edu.espol.ProyectoJuegoIIP to javafx.fxml;
    exports ec.edu.espol.ProyectoJuegoIIP;
}
