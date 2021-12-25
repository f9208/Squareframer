module base {
    requires javafx.graphics;
    requires javafx.controls;
    requires java.base;
    requires java.desktop;
    requires javafx.fxml;

    exports base.utils;
    opens base.fx;
}