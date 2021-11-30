module game.willhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens game.willhero to javafx.fxml;
    exports game.willhero;
}