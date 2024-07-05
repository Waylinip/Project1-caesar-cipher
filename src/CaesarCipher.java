import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaesarCipher {

    private static final List<Character> ALPHABET = createAlphabet();

    public static void main(String[] args) {

        CaesarCipher cipher = new CaesarCipher();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите режим работы(encrypt/decrypt): ");
        String mode = scanner.nextLine().toLowerCase();


        System.out.println("Введите ключ сдвига (число): ");
        int key = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        System.out.println("Введите название входного файла: ");
        String inputFileName = scanner.nextLine();

        System.out.println("Введите название выходного файла: ");
        String outputFileName = scanner.nextLine();



        if (mode.equals("encrypt")) {
            System.out.println("Введите текст для записи в файл: ");
            String textToWrite = scanner.nextLine().toLowerCase();
            writeToFile(inputFileName, textToWrite);
        }
        String fileContent = readFromFile(inputFileName);

        if (mode.equals("encrypt")) {
            cipher.encrypt(inputFileName, outputFileName, key);
            System.out.println("Шифрование завершено.");
        } else if (mode.equals("decrypt")) {
            cipher.decrypt(inputFileName, outputFileName, key);
            System.out.println("Расшифровка завершена.");
        }
    }
    public static void writeToFile(String filePath, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String readFromFile(String filePath) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
           while((line = reader.readLine()) != null){
               text.append(line);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
    private String shiftText(String text, int key) {
       StringBuilder result = new StringBuilder();
        for (char c: text.toCharArray()) {
            result.append(shiftCharacter(c,key));
        }
        return result.toString();
    }
  private static char shiftCharacter(char symbol , int key ) {
      ALPHABET.indexOf(symbol);
      if ( ALPHABET.indexOf(symbol) != -1) {
         return ALPHABET.get((ALPHABET.indexOf(symbol)+key)% ALPHABET.size());
      } else {
          return symbol;
      }
  }

    public void encrypt(String inputFile, String outputFile, int key ) {
String text = readFromFile(inputFile);
String encrypted = shiftText(text,key);
writeToFile(outputFile,encrypted);


        }

    public void decrypt(String inputFile, String outputFile, int key) {
        String inputText = readFromFile(inputFile);
        String decryptedText = shiftText(inputText, -key);
        writeToFile(outputFile, decryptedText);
    }
    private static List<Character> createAlphabet() {
        List<Character> alphabet = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            alphabet.add(c);
        }
        return alphabet;
    }

}
