package Scanner;

public class Literal {
   int lineNo;
    String lexeme;
    String returnToken;
    String lexemeNoInLine;
    String matchability;
    public Literal(int lineNo, String lexeme, String returnToken, String lexemeNoInLine, String matchability){
        this.lineNo=lineNo;
        this.lexeme=lexeme;
        this.returnToken=returnToken;
        this.lexemeNoInLine=lexemeNoInLine;
        this.matchability=matchability;
    }
    public int getLineNo(){return lineNo;}
    public String getName(){
        return returnToken;
    }
    public String getValue(){
         return lexeme;
    }
    public String getLexemeNoInLine() {
        return lexemeNoInLine;
    }

    public String getMatchability() {
        return matchability;
    }
}
