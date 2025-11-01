import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TextEditorWithObservers editor = new TextEditorWithObservers("Initial text");

        EditorUIObserver uiObserver = new EditorUIObserver();
        AutoSaveObserver autoSaveObserver = new AutoSaveObserver();
        LoggerObserver loggerObserver = new LoggerObserver();

        editor.registerObserver(uiObserver);
        editor.registerObserver(autoSaveObserver);
        editor.registerObserver(loggerObserver);

        while (true) {
            System.out.println("\n--- Text Editor ---");
            System.out.println("1. Edit text");
            System.out.println("2. Change format (bold, italic, underline, bolditalic, boldunderline)");
            System.out.println("3. Save document");
            System.out.println("4. Remove observer");
            System.out.println("5. Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter new text: ");
                    String newText = scanner.nextLine();
                    editor.editText(newText);
                    break;
                case "2":
                    System.out.print("Choose format: ");
                    String format = scanner.nextLine();
                    try {
                        editor.setTextFormatStrategy(FormatFactory.createFormat(format));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid format.");
                    }
                    break;
                case "3":
                    editor.saveDocument();
                    break;
                case "4":
                    System.out.println("Which observer to remove? (ui/autosave/logger)");
                    String obs = scanner.nextLine().toLowerCase();
                    switch (obs) {
                        case "ui": editor.removeObserver(uiObserver); break;
                        case "autosave": editor.removeObserver(autoSaveObserver); break;
                        case "logger": editor.removeObserver(loggerObserver); break;
                        default: System.out.println("Unknown observer."); break;
                    }
                    break;
                case "5":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }

            System.out.println("Formatted text: " + editor.getFormattedText());
        }
    }
}
