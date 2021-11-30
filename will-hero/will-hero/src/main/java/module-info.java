module game.willhero {
    requires javafx.controls;
    requires javafx.fxml;


    opens game.willhero to javafx.fxml;
    exports game.willhero;
}