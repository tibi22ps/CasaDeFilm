module com.example.casafilme {

    requires java.sql;
    requires java.desktop;
    requires com.google.gson;
    requires poi.ooxml;
    requires org.jfree.jfreechart;


    opens com.example.casafilme to javafx.fxml;
    opens com.example.casafilme.model to com.google.gson;
    exports com.example.casafilme;
    exports com.example.casafilme.model.repository;
    exports com.example.casafilme.model;

}