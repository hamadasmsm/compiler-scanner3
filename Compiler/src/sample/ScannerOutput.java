package sample;

import Scanner.Literal;
import Scanner.NonToken;
import Scanner.Scan;
import Scanner.Token;
import com.sun.javafx.scene.control.TreeTableViewBackingList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ScannerOutput implements Initializable {
    //Token Table
    @FXML
    TableView<Token> tokenTable;
    @FXML
    TableColumn<Token,Integer> tLineNoColumn;
    @FXML
    TableColumn<Token,String> tLexemeColumn;
    @FXML
    TableColumn<Token,String> tTokenColumn;
    @FXML
    TableColumn<Token,Integer> tLexemenoLine;
    @FXML
    TableColumn<Token,String> tMatchability;
    @FXML
    Label lblTotalError;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialize Token Table
        tLineNoColumn.setCellValueFactory(
                new PropertyValueFactory<Token, Integer>("LineNo"));
        tLexemeColumn.setCellValueFactory(
                new PropertyValueFactory<Token, String>("Lexeme"));
        tTokenColumn.setCellValueFactory(
                new PropertyValueFactory<Token, String>("ReturnToken"));
        tLexemenoLine.setCellValueFactory(
                new PropertyValueFactory<Token, Integer>("LexemeNoInLine"));
        tMatchability.setCellValueFactory(
                new PropertyValueFactory<Token, String>("Matchability"));
        tokenTable.getItems().setAll(Controller.listToken);
        lblTotalError.setText("Total Error are "+Controller.TotalError.size());
    }
}
