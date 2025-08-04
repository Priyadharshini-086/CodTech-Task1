import java.io.*;
import java.util.Scanner;

public class FileHandlingUtility {

    private static String FILE_NAME = "sample.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nFILE HANDLING UTILITY");
            System.out.println("1. Write to file");
            System.out.println("2. Read from file");
            System.out.println("3. Modify file content");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            switch (choice) {
                case 1:
                    writeToFile(scanner);
                    break;
                case 2:
                    readFromFile();
                    break;
                case 3:
                    modifyFile(scanner);
                    break;
                case 4:
                    System.out.println("Exiting... Done.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select again.");
            }

        } while (choice != 4);

        scanner.close();
    }
    public static void writeToFile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            System.out.println("Enter content to write to file:");
            String content = scanner.nextLine();
            writer.write(content);
            System.out.println("Successfully written to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("An error occurred during writing: " + e.getMessage());
        }
    }
    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("Content of " + FILE_NAME + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("An error occurred during reading: " + e.getMessage());
        }
    }
    public static void modifyFile(Scanner scanner) {
        try {
            // Read original content
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.err.println("File does not exist: " + FILE_NAME);
                return;
            }

            Scanner fileScanner = new Scanner(file);
            StringBuilder content = new StringBuilder();

            while (fileScanner.hasNextLine()) {
                content.append(fileScanner.nextLine()).append("\n");
            }
            fileScanner.close();
            System.out.print("Enter word to be replaced: ");
            String oldWord = scanner.nextLine();
            System.out.print("Enter new word: ");
            String newWord = scanner.nextLine();
            String updatedContent = content.toString().replace(oldWord, newWord);
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(updatedContent);
            writer.close();

            System.out.println("File modified successfully.");

        } catch (IOException e) {
            System.err.println("An error occurred during modification: " + e.getMessage());
        }
    }
}

