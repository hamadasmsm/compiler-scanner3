package Scanner;

public class Token {
    int lineNo;
    String lexeme;
    String returnToken;
    int lexemeNoInLine;
    String matchability;
    public Token(int lineNo, String lexeme, String returnToken, int lexemeNoInLine, String matchability){
        this.lineNo=lineNo;
        this.lexeme=lexeme;
        this.returnToken=returnToken;
        this.lexemeNoInLine=lexemeNoInLine;
        this.matchability=matchability;
    }


    public int getLineNo(){return lineNo;}
    public String getLexeme(){
        return lexeme;
    }
    public String getReturnToken(){
        return returnToken;
    }
    public void updateReturnToken(String lexeme){
        this.returnToken+=returnToken;
    }

    public int getLexemeNoInLine() {
        return lexemeNoInLine;
    }

    public String getMatchability() {
        return matchability;
    }
}
