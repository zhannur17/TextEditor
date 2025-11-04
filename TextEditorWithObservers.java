import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TextEditorWithObservers {
    private List<Observer> observers = new ArrayList<>();
    private String text;
    private TextFormatStrategy currentFormatStrategy;


    private int wordCount;
    private int charCount;
    private int sentenceCount;
    private LocalDateTime lastEdited;

    public TextEditorWithObservers(String initialText) {
        this.text = initialText;
        this.currentFormatStrategy = new BoldFormatStrategy();
        updateStatistics();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void editText(String newText) {
        this.text = newText;
        updateStatistics();
        notifyObservers("Text edited: \"" + newText + "\"");
    }


    public void setTextFormatStrategy(TextFormatStrategy formatStrategy) {
        this.currentFormatStrategy = formatStrategy;
        notifyObservers("Text format changed to: " + formatStrategy.getClass().getSimpleName());
    }

    public String getFormattedText() {
        return currentFormatStrategy.format(text);
    }

    public void saveDocument() {
        notifyObservers("Document saved at " + getFormattedTime());
    }

    private void updateStatistics() {
        this.wordCount = countWords(text);
        this.charCount = text.length();
        this.sentenceCount = countSentences(text);
        this.lastEdited = LocalDateTime.now();
    }

    private int countWords(String text) {
        if (text == null || text.trim().isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }

    private int countSentences(String text) {
        if (text == null || text.trim().isEmpty()) return 0;
        return text.split("[.!?]+").length;
    }

    private String getFormattedTime() {
        return lastEdited.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void printStatistics() {
        System.out.println("\n--- Document Statistics ---");
        System.out.println("Words: " + wordCount);
        System.out.println("Characters: " + charCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Last edited: " + getFormattedTime());
    }
}
