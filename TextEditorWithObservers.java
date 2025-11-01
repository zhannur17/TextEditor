import java.util.ArrayList;
import java.util.List;

class TextEditorWithObservers {
    private List<Observer> observers = new ArrayList<>();
    private String text;
    private TextFormatStrategy currentFormatStrategy;

    public TextEditorWithObservers(String initialText) {
        this.text = initialText;
        this.currentFormatStrategy = new BoldFormatStrategy();
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
        notifyObservers("Document saved.");
    }
}
