public class TextManager {
    private final String text;
    private int position;
    private int lineNumber;

    public TextManager(String input) {
        this.text = input;
        this.position = 0;
        this.lineNumber = 1;
    }

    public boolean isAtEnd() {
        return position >= text.length();
    }

    public char PeekCharacter() {
        return text.charAt(position);
    }

    public char PeekCharacter(int dist) {
        return text.charAt(dist);
    }

    public char GetCharacter() {
        char c = text.charAt(position++);
        if (c == '\n') {
            lineNumber++;
        }
        return c;
    }

    public int getPosition() {
        return position;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
