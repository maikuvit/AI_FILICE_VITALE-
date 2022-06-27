module com.example.ai_filicevitale {
    requires javafx.controls;
    requires javafx.fxml;
	requires org.antlr.antlr4.runtime;


    opens com.example.ai_filicevitale to javafx.fxml;
    exports com.example.ai_filicevitale;
    exports com.example.ai_filicevitale.Controller;
    exports com.example.ai_filicevitale.Model;
    opens com.example.ai_filicevitale.Controller to javafx.fxml;
}