public class SyntaxErrorException extends Exception {
    private int LineNumber, CharacterPosition;

    public SyntaxErrorException(String message, int LineNumber, int CharacterPosition) {
        super(message);
        this.LineNumber = LineNumber;
        this.CharacterPosition = CharacterPosition;
    }

    @Override
    public String toString() {
        return "Error at line " + LineNumber + " at character " + CharacterPosition + " : " + super.toString();
    }
}
