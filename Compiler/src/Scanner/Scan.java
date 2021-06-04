package Scanner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
public class Scan {
    public static final String[] KEYWORD={"Pattern","DerivedFrom",
            "TrueFor—Else","Ity","Sity","Cwq","CwqSequence","Ifity","Sifity",
            "Valueless","Logical","BreakFromThis","Whatever","Respondwith",
            "Srap","Scan—Conditionof","Require"};
    public static final String SYMBOL=". @ $ - + * / && || ~ = ==  < >  != <= >= = -> [ ] { } , ' /- -/ -- # ^ \"" ;
    public static final String ALPHABET="a b c d e f g h i j k l m n o p q r s t u v w x y z _ —" +
            "Q W E R T Y U I O P A S D F G H J K L Z X C V B N M";
    public static final String NUMBER=" 0 1 2 3 4 5 6 7 8 9";
    public static boolean isAlaphabet(String token){return ALPHABET.contains(token);}
    public static boolean isNumber(String token){return NUMBER.contains(token);}
    public static boolean isKeyword(String token){
       for(int i=0;i<KEYWORD.length;i++){
           if (KEYWORD[i].equals(token))
                   return true;
       }
       return false;
    }
    public static boolean isSymbol(String token){
        return SYMBOL.contains(token);
    }
    static String identiferRegularExperssion = "[_a-zA-Z][_a-zA-Z|0-9]*";
    public static String getSymbolName(String symbol){ 
       
        if (Pattern.matches(identiferRegularExperssion, symbol)&&  !isKeyword(symbol)) {
                     return "identifire";}
        
        switch (symbol){
            case "@":return "Start symbol";
            case "$":return "End symbol";
            case "+":return "Arithmetic operation";
            case "-":return "Arithmetic operation";
            case "*":return "Arithmetic operation";
            case "/":return "Arithmetic operation";
            case"&&": return "logic operator";
            case"||": return "logic operator";
            case"~": return "logic operator";
            case "==":return "Relational operator";
            case "<=":return "Relational operator";
            case ">=":return "Relational operator";
            case "!=": return "Relational operator";
            case "<": return "Relational operator";
            case ">": return "Relational operator";
            case"=": return "Assignment operator";
            case "->": return "Access OPerator";
            case "[": return "Braces";
            case "]": return "Braces";
            case "{": return "Braces";
            case "}": return "Braces";
            case "\"": return "Quotation Mark";
            case "'": return "Quotation Mark";
            case ",": return "Quotation Mark";
            case "/-": return "Comment";
            case "-/": return "Comment";
            case "--": return "Comment";
            case "#": return "Token Delimiter";
            case "^": return "Line Delimiter";
            case ".": return "Line Delimiter";
            case "Pattern": return "Class";
            case "DerivedFrom": return "Inheritance";
            case "TrueFor—Else": return "Condition";
            case "Ity": return "Integer";
            case "Sity": return "SInteger";
            case "Cwq": return "Character";
            case "CwqSequence": return "String";
            case "Ifity": return "Float";
            case "Sifity": return "SFloat";
            case "Valueless": return "Void";
            case "Logical": return "Boolean";
            case "BreakFromThis": return "Break";
            case "Whatever": return "Loop";
            case "Respo\"ndwith": return "Return";
            case "Srap": return "Struct";
            case "Scan—Conditionof": return "Switch";
            case "Require": return "Inclusion";
            
   
            default: return "Invalid";
        }
    }
    public static String getNumName(String num){
        String identiferRegularExperssio = "^[0-9]*$";
         if (Pattern.matches(identiferRegularExperssio, num)) {
                     return "Constant";
         }else
             return "invalid";
        
    }

}
