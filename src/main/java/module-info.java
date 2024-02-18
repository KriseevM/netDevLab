module ru.kriseev.netdevlab {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens ru.kriseev.netdevlab to javafx.fxml;
    exports ru.kriseev.netdevlab;
}