package sample;
import Scanner.Scan;
import Scanner.NonToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.*;
import Scanner.Token;
import Scanner.Literal;

public class Controller implements Initializable {

    static ObservableList<NonToken> listNonToken=FXCollections.observableArrayList();
    static ObservableList<Token> listToken=FXCollections.observableArrayList();
    static ObservableList<Literal> listLiteral=FXCollections.observableArrayList();
    static ObservableList<Token> TotalError=FXCollections.observableArrayList();
    TextArea textArea=new TextArea();
    static String codeWithoutNonTokens="";
    StringBuffer temp;
    private File f;
    boolean isFileOpen=false;
    @FXML
    Button btnNew;
    @FXML
    Button btnOpen;
    @FXML
    AnchorPane myPane;
    @FXML
    Button btnScan;
    @FXML
    Button btnComment;
    

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setPrefSize(888,540);

        
    }
    void removeNonToken(String tokenName,String tStart,String tEnd){
        while(temp.indexOf(tStart)!= -1)
        {
            int start=temp.indexOf(tStart);
            String tempEnd=temp.substring(start);
            String comment=temp.substring(start,start+tempEnd.indexOf(tEnd)+2);
          if(tokenName.equals("single comment")){
            temp.delete(start,start+tempEnd.indexOf(tEnd));
          }
            else {
              int linesCount = comment.length()-comment.replace("\n","").length();
              String lines="";
              for(int i=1;i<=linesCount;i++)
              {
                  lines+="\n";
              }
              temp.delete(start,start+tempEnd.indexOf(tEnd)+2);
              temp.insert(start,lines);
          }
        }
    }
    @FXML
    void clickScan() {
        try {
            
            boolean isString = false;
            listToken.clear();
            listLiteral.clear();
            listNonToken.clear();
            TotalError.clear();
            temp = new StringBuffer(textArea.getText());
            removeNonToken("single comment", "--", "\n");
            removeNonToken("multi comment", "/-", "-/");
            codeWithoutNonTokens = temp.toString();
            StringTokenizer lineTokenizer = new StringTokenizer(codeWithoutNonTokens, "\n",true);
            
            int lineNo=1;
            int lineSpace=1;
 
            
            while (lineTokenizer.hasMoreTokens()) {
                String nextToken=lineTokenizer.nextToken();
              
                if(nextToken.equals("\n")) {
                    lineNo++;
                    lineSpace = 1;
                    continue;
                };
                 
                StringTokenizer spaceTokenizer = new StringTokenizer(nextToken, " ");
               
                while (spaceTokenizer.hasMoreTokens()) {
                    String token = spaceTokenizer.nextToken();
                  
                        for (int i = 0; i < token.length(); i++) {
                            String character = Character.toString(token.charAt(i));
                            //is it a spcial symbol??
                            if (Scan.isSymbol(character) && !isString) {

                                if (((i + 1) < token.length())) {
                                    String character2 = Character.toString(token.charAt(i + 1));
                                    if (character.equals(character2)) {
                                        listToken.add(new Token(lineNo, character + character2, Scan.getSymbolName(character + character2), lineSpace, "matched"));
                                        lineSpace++;
                                        i++;
                                        continue;
                                    } else {
                                        listToken.add(new Token(lineNo, character, Scan.getSymbolName(character), lineSpace, "matched"));
                                        lineSpace++;
                                        continue;
                                    }
                                } else if ((((i + 1) < token.length()) && (character.equals("!") || character.equals("<") || character.equals(">")))) {
                                    String character2 = Character.toString(token.charAt(i + 1));
                                    if (character2.equals("=")) {
                                        listToken.add(new Token(lineNo, character + character2, Scan.getSymbolName(character + character2), lineSpace, "matched"));
                                        i++;
                                        lineSpace++;
                                        continue;
                                    } else {
                                        listToken.add(new Token(lineNo, character, Scan.getSymbolName(character), lineSpace, "matched"));
                                        lineSpace++;
                                        continue;
                                    }

                                } else {
                                    listToken.add(new Token(lineNo, character, Scan.getSymbolName(character), lineSpace, "matched"));
                                    lineSpace++;
                                    continue;
                                }
                            }
                            // is it a identifier??
                            String tempToken = "";
                            if (Scan.isAlaphabet(character) && !isString) {

                                do {
                                    tempToken += character;
                                    i++;
                                    if (i < token.length())
                                        character = Character.toString(token.charAt(i));
                                    else break;
                                } while (Scan.isAlaphabet(character) || Scan.isNumber(character));
                                --i;
                                if (Scan.isKeyword(tempToken)){
                                    listToken.add(new Token(lineNo, tempToken, Scan.getSymbolName(tempToken), lineSpace, "matched"));
                                
                                }else 
                                    listToken.add(new Token(lineNo, tempToken, Scan.getSymbolName(tempToken), lineSpace, "matched"));
                                lineSpace++;
                                continue;
                            }
                            
                            // is it a mumber??
                            String tempnum = "";
                            if (Scan.isNumber(character) && !isString) {

                                do {
                                    tempnum += character;
                                    i++;
                                    if (i < token.length())
                                        character = Character.toString(token.charAt(i));
                                    else break;
                                } while (Scan.isAlaphabet(character) || Scan.isNumber(character));
                                --i;
                                if (Scan.isKeyword(tempnum)){
                                    listToken.add(new Token(lineNo, tempnum, Scan.getNumName(tempnum), lineSpace, "matched"));
                                
                                }else if(Scan.getNumName(tempnum).equals("invalid")){
                                        
                                        listToken.add(new Token(lineNo, tempnum, Scan.getNumName(tempnum), lineSpace, "unmatched"));
                                        TotalError.add(new Token(lineNo, token.substring(token.indexOf(character)), "invlaid", lineSpace, "unmatched"));
                                }
                                        else
                                    listToken.add(new Token(lineNo, tempnum, Scan.getNumName(tempnum), lineSpace, "matched"));
                                lineSpace++;
                                continue;
                            }
                            
                           
                         
                            listToken.add(new Token(lineNo, token.substring(token.indexOf(character)), "invlaid", lineSpace, "unmatched"));
                            lineSpace++;
                            TotalError.add(new Token(lineNo, token.substring(token.indexOf(character)), "invlaid", lineSpace, "unmatched"));
                            break;
                            
                        }
                    
                }
            }
                
            scannerOutputPage();


        }
        catch (Exception ex)
       {
            JOptionPane.showMessageDialog(null,ex.toString());
       }
    }
    void scannerOutputPage() throws Exception
    {
        Parent root ;
        Stage primaryStage=new Stage();
        root = FXMLLoader.load(getClass().getResource("ScannerOutput.fxml"));
        primaryStage.setTitle("Scanner Result");
        Scene scene=new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("styling.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.show();

    }

    @FXML
    void clickOpen()
    {

        try {
            FileChooser chooser = new FileChooser();
            f= chooser.showOpenDialog(null);
            if(!(f.getName()).contains(".java")) throw new Exception();
            TempFile file = new TempFile();
            if (file.input(f)) {
                myPane.getChildren().clear();
                myPane.getChildren().add(textArea);
                textArea.setText(file.getFiletext());
                textArea.setVisible(false);
                btnScan.setDisable(false);
                  isFileOpen=true;
                btnNew.setDisable(true);
                
             
            } else {
                throw new Exception();
            }
        }
        catch(Exception e)
        {
           
        }
    }
    
    @FXML
    void writeCode()
    {

        try {
            myPane.getChildren().clear();
                myPane.getChildren().add(textArea);
                String x= "--write code here";
                textArea.setText(x);
                textArea.setVisible(true);
                btnScan.setDisable(false);
                btnNew.setDisable(true);
                btnComment.setDisable(false);
                
        }
        catch(Exception e)
        {
            
        }
    }
    
    @FXML
    void CommentCode()
    {
        String aa = textArea.getSelectedText();
        

        if (aa.startsWith("--")){
            if (aa.contains("\n")){
            String[] sb = aa.split("\n");
                for(String w:sb){  
                    String aaaa = w.replaceFirst("--", "");
            textArea.replaceSelection(aaaa + "\n");
                    }  
            } else {
            String aaaa = aa.replaceFirst("--", "");
            textArea.replaceSelection(aaaa);
            }
            

        }else {
            if (aa.contains("\n")){
            String[] sb = aa.split("\n");
                for(String w:sb){  
                    String aaaaa = w.replace(w, "--" + w + "\n");
                    textArea.replaceSelection(aaaaa); 
                    }  
            } else {
            String aaaaa = aa.replace(aa, "--" + aa);
                    textArea.replaceSelection(aaaaa); 
            }
                
            
 
        }
    }
}