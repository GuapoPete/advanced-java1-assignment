package userValidator;

import java.io.*;

public class UserDataValidator {
    public static void main(String[] args) {
        try {
            // Instantiate readers and writers
            BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"));
            BufferedWriter validWriter = new BufferedWriter(new FileWriter("valid_data.txt"));
            BufferedWriter errorWriter = new BufferedWriter(new FileWriter("error_data.txt"));

            // Run a while loop to iterate through each line of the file
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    // Split the line by comma and check for missing data
                    String[] userData = line.split(",");
                    if (userData.length != 3) {
                        throw new Exception("Missing Data");
                    }

                    // Extract data and trim whitespace
                    String name = userData[0].trim();
                    String email = userData[1].trim();
                    int age = Integer.parseInt(userData[2].trim());

                    // Check for invalid age
                    if (age <= 0) {
                        throw new Exception("Invalid Age");
                    }

                    // Write to appropriate file
                    validWriter.write(line);
                    validWriter.newLine();
                } catch (Exception e) {
                    // Write faulty line to error file
                    errorWriter.write(line + " - " + e.getMessage());
                    errorWriter.newLine();
                }
            }

            // Close readers and writers
            reader.close();
            validWriter.close();
            errorWriter.close();

        } catch (IOException e) {
            // Print error if file operation fails
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
