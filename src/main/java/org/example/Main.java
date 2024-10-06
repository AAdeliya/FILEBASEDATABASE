package org.example;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        File inputFile = new File("src/main/java/org/example/filehandling.txt");    // Source file path
        File outputFile = new File("destination.txt"); // Destination file path

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            int character;
            while ((character = reader.read()) != -1) {
                writer.write(character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
