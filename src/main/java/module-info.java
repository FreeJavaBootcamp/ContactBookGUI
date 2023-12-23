module com.fjb.contactbook {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.fjb.contactbook to javafx.fxml;
    exports com.fjb.contactbook;
    exports com.fjb.contactbook.controller;
    opens com.fjb.contactbook.controller to javafx.fxml;
}