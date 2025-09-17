public class TextManager {
    private final String text;
    private int position;
    public TextManager(String input) {
        this.text = input;
        this.position = 0;
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
        return text.charAt(position++);
    }
}
