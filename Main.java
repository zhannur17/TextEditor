import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TextEditorWithObservers textEditor = new TextEditorWithObservers("Initial text");

        textEditor.registerObserver(new EditorObserver("UI"));
        textEditor.registerObserver(new EditorObserver("AutoSave"));
        textEditor.registerObserver(new EditorObserver("Logger"));

        System.out.println("=== Simple Text Editor ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Edit text");
            System.out.println("2. Choose format (bold, italic, underline, bolditalic, boldunderline)");
            System.out.println("3. Save document (HTML)");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter new text: ");
                    String newText = scanner.nextLine();
                    textEditor.editText(newText);
                    break;

                case 2:
                    System.out.print("Choose format: ");
                    String formatChoice = scanner.nextLine();
                    try {
                        TextFormatStrategy strategy = FormatFactory.createFormat(formatChoice);
                        textEditor.setTextFormatStrategy(strategy);
                    } catch (Exception e) {
                        System.out.println("Invalid format type.");
                    }
                    break;

                case 3:
                    textEditor.saveDocument();
                    saveToHTML(textEditor.getFormattedText());
                    System.out.println("Saved! Check output.html to view result.");
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }

            System.out.println("Formatted Text Preview (HTML tags shown):");
            System.out.println(textEditor.getFormattedText());
        }
    }

    private static void saveToHTML(String formattedText) {
        String htmlContent = """
        <!DOCTYPE html>
        <html lang="en">
        <head><meta charset="UTF-8"><title>Formatted Text</title></head>
        <body style="font-family: Arial; font-size: 18px;">
            <p>%s</p>
        </body></html>
        """.formatted(formattedText);
        try (FileWriter writer = new FileWriter("output.html")) {
            writer.write(htmlContent);
            System.out.println("Saved successfully: output.html");
        } catch (IOException e) {
            System.out.println("Error saving HTML: " + e.getMessage());
        }
    }
}