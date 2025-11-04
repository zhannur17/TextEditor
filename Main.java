import java.io.FileWriter;
import java.io.IOException;
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
            System.out.println("3. Save document to output.html");
            System.out.println("4. Remove observer");
            System.out.println("5. Show text statistics");
            System.out.println("6. Exit");
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
                    String formatted = editor.getFormattedText();
                    saveToHTML(formatted);
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
                    editor.printStatistics();
                    break;
                case "6":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }

            System.out.println("Formatted text: " + editor.getFormattedText());
        }
    }

    private static void saveToHTML(String formattedText) {
        String htmlContent = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>Formatted Text</title>
        </head>
        <body style="font-family: Arial; font-size: 18px;">
            <p>%s</p>
        </body>
        </html>
        """.formatted(formattedText);

        try (FileWriter writer = new FileWriter("output.html")) {
            writer.write(htmlContent);
            System.out.println("Saved successfully: output.html");
        } catch (IOException e) {
            System.out.println("Error saving HTML: " + e.getMessage());
        }
    }
}
