import java.lang.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Label output;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView fullscreen;

    double num1;
    double num2;
    String operator = "";
    String equation = "";

    @FXML
    void onNumberClicked (MouseEvent event) {
        String chosen = ((Label) event.getSource()).getText();
        int value = Integer.parseInt(chosen);
        equation += String.valueOf(value);
        output.setText(equation);
    }

    @FXML
    void onSymbolClicked (MouseEvent event) {
        String symbol = ((Label) event.getSource()).getText();
        equation += symbol;
        if (symbol.equals("=")) {
            num2 = Double.parseDouble(output.getText().substring((output.getText().lastIndexOf(operator) + 2)));
            switch (operator) {
                case "+" -> output.setText(num1 + num2 + "");
                case "-" -> output.setText(num1 - num2 + "");
                case "*" -> output.setText(num1 * num2 + "");
                case "/" -> output.setText(num1 / num2 + "");
            }
            operator = ".";
        }
        else if (symbol.equals("-") && equation.charAt(0) == '-' && equation.length() == 1) {
            output.setText(symbol);
        }
        else {
            num1 = Double.parseDouble(output.getText());
            equation = num1 + " " + symbol + " ";
            output.setText(equation);
            switch (symbol) {
                case "+" -> operator = "+";
                case "-" -> operator = "-";
                case "*" -> operator = "*";
                case "/" -> operator = "/";
            }
        }
    }

    @FXML
    void onClearClicked (MouseEvent event) {
        output.setText("");
        operator = "";
        equation = "";
    }

    @FXML
    void onExitClicked (MouseEvent event) {
        javafx.application.Platform.exit();
    }

    @FXML
    void onMinClicked (MouseEvent event) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void onFullClicked (MouseEvent event) {
        Stage stage = (Stage) fullscreen.getScene().getWindow();
        stage.setFullScreen(true);
    }
}
