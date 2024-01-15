import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    Output output = new Output();
    Scanner scanner = new Scanner(System.in);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/jbdc-petshop";
    private static final String username = "root";
    private static final String password = "tefejojigi69";

    public void addAnimalToDB() {
        Scanner scanner = new Scanner(System.in);

        output.printMessageNl("\n Choose the type of animal:");
        output.printMessageNl("1. Dog");
        output.printMessageNl("2. Fish");

        int animalType = getUserInput(scanner, "\n Enter your choice (1 or 2): ", 1, 2);

        String animalBreed;

        if (animalType == 1) {
            output.printMessage("\n Enter dog breed (Rottweiller, Golden, Bischon, Husky, Samoyed, Yorkshire, Labrador): ");
            while (true) {
                animalBreed = scanner.nextLine().trim();
                output.printMessageNl(animalBreed);
                try {
                    // Validate breed against the enums for dogs
                    if (isValidDogBreed(animalBreed)) {
                        break;
                    } else {
                        output.printMessage("\n Invalid breed. Please enter a valid dog breed: ");
                    }
                } catch (IllegalArgumentException e) {
                    output.printMessage("\n Invalid breed. Please enter a valid dog breed: ");
                }
            }
        } else {
            output.printMessage("\n Enter fish breed (ClownFish, Koi, GoldenFish, BubbleEye, Ryukin):");
            while (true) {
                animalBreed = scanner.nextLine().trim();
                try {
                    // Validate breed against the enums for fish
                    if (isValidFishBreed(animalBreed)) {
                        break;
                    } else {
                        output.printMessage("\n Invalid breed. Please enter a valid fish breed: ");
                    }
                } catch (IllegalArgumentException e) {
                    output.printMessage("\n Invalid breed. Please enter a valid fish breed: ");
                }
            }
        }
        int price = getUserInput(scanner, "Enter price (must be greater than 100): ", 101);
        int age = getUserInput(scanner, "Enter age (must be between 0 and 20): ", 0, 20);
        int weight = getUserInput(scanner, "Enter weight: ");
        String type = null;
        if (animalType == 1) type = "Dog";
        else type = "Fish";
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, username, password);
            String sqlQuery = "INSERT INTO animals (animalbreed, animalprice, animalweight, animalage, animaltype) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, animalBreed);
            preparedStatement.setInt(2, price);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, weight);
            preparedStatement.setString(5, type);
            preparedStatement.executeUpdate();
            output.printMessageNl("Animal stored in the database");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Animal newAnimal = new Animal(animalBreed, price, age, weight);

        output.printMessageNl("New animal added: " + newAnimal);
    }

    public int getUserInput(Scanner scanner, String prompt, int... validRange) {
        int userInput;
        do {
            output.printMessage(prompt);
            while (!scanner.hasNextInt()) {
                output.printMessageNl("Invalid input. Please enter a valid number.");
                scanner.next(); // consume the invalid input
            }
            userInput = scanner.nextInt();
            if (validRange.length == 0) {
                break; // No range validation needed
            }
            if (validRange.length == 1 && userInput >= validRange[0]) {
                break; // Validate against minimum value
            }
            if (validRange.length == 2 && userInput >= validRange[0] && userInput <= validRange[1]) {
                break; // Validate against range
            }
            output.printMessageNl("Invalid input. Please enter a valid number.");
        } while (true);

        scanner.nextLine();
        return userInput;
    }

    public boolean isValidDogBreed(String breed) {
        try {
            Dog.Breed.valueOf(breed);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isValidFishBreed(String breed) {
        try {
            Fish.Breed.valueOf(breed);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void printDB() {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, username, password);
            String sqlQuery = "select * from animals order by animaltype, animalbreed";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            output.printMessageNl("\n            Available animals:");
            int i = 1;
            while (resultSet.next()) {
                String animalType = resultSet.getString("animaltype");
                String animalBreed = resultSet.getString("animalbreed");
                int animalPrice = resultSet.getInt("animalprice");
                int animalAge = resultSet.getInt("animalage");
                int animalWeight = resultSet.getInt("animalweight");
                output.printMessageNl(i++ + ". " + animalBreed + ":\n- price " + animalPrice + "\n- age " + animalAge + "\n- weight " + animalWeight + "\n- " + animalType + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyAnimal() {
        printDB();
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, username, password);
            String sqlQueryCount = "select count(*) from animals";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryCount);
            ResultSet resultSet = preparedStatement.executeQuery();
            int animalCount = 0;
            if (resultSet.next()) {
                animalCount = resultSet.getInt(1);
            }
            int userInput = getUserInput(scanner, "\n Enter your choice: ", 1, animalCount);
            String sqlQueryBuy = "DELETE FROM animals WHERE (animaltype, animalbreed) IN (SELECT * FROM (SELECT animaltype, animalbreed FROM animals ORDER BY animaltype, animalbreed LIMIT 1 OFFSET " + (userInput - 1) + ") AS subquery)";
            PreparedStatement preparedStatementBuy = connection.prepareStatement(sqlQueryBuy);
            preparedStatementBuy.executeUpdate();
            output.printMessageNl("Purchase complete!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnAnimal() {
        output.printMessageNl("\n      Enter the animal's details");
        addAnimalToDB();
    }

    public void clearNewInventory() {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, username, password);
            String sqlQueryCount = "delete from new_animals";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryCount);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Animal> storeDB() {
        ArrayList<Animal> animalList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, username, password);
            String sqlQuery = "select * from animals";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String animalType = resultSet.getString("animaltype");
                String animalBreed = resultSet.getString("animalbreed");
                int animalPrice = resultSet.getInt("animalprice");
                int animalAge = resultSet.getInt("animalage");
                int animalWeight = resultSet.getInt("animalweight");
                Animal animal = new Animal(animalBreed, animalPrice, animalAge, animalWeight);
                animalList.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animalList;
    }

    public void saveAnimal(Animal animal,String table) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, username, password);
            String sqlQuery = "INSERT INTO " + table + " (animalbreed, animalprice, animalweight, animalage, animaltype) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, animal.AnimalBreed);
            preparedStatement.setInt(2, animal.price);
            preparedStatement.setInt(3, animal.weight);
            preparedStatement.setInt(4, animal.age);
            String type = null;
            if (isValidDogBreed(animal.AnimalBreed)) {
                type = "Dog";
            }
            if (isValidFishBreed(animal.AnimalBreed)) {
                type = "Fish";
            }
            preparedStatement.setString(5, type);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeFromDB(Animal animal, String table){
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, username, password);
            String sqlQuery = "DELETE FROM " + table + " WHERE animalbreed = '" + animal.AnimalBreed + "' and animalprice = " + animal.price + " and animalweight = " + animal.weight + " and animalage = " + animal.age;
            PreparedStatement preparedStatementBuy = connection.prepareStatement(sqlQuery);
            preparedStatementBuy.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}