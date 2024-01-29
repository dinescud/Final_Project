import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import java.util.ArrayList;


public class JavaFXApp extends Application {

    private static StackPane stackPane;
    Database database = new Database();
    private TextField nameField;
    private Scene WelcomeScene;
    private Scene registerScene;
    private Scene loginScene;
    private Scene adminScene;
    private Scene userScene;
    private Scene inventoryScene;
    private Scene addScene;

    private VBox WelcomeBox(){
        Label label = new Label("Pet Shop Management System");

        // Create buttons
        Button bLog = new Button("Log in");
        Button bSign = new Button("Sign up");

        // Create an HBox to hold the buttons
        HBox hbox = new HBox(10); // spacing between nodes
        hbox.setAlignment(Pos.CENTER); // center alignment
        hbox.getChildren().addAll(bLog, bSign);

        // Create a VBox to hold the label and HBox with buttons
        VBox vbox = new VBox(10); // spacing between nodes
        vbox.setAlignment(Pos.CENTER); // center alignment
        vbox.getChildren().addAll(label, hbox);

        bLog.setOnAction( e -> {switchScene(loginScene);});
        bSign.setOnAction( e -> {switchScene(registerScene);});

        return vbox;
    }
    public GridPane createRegisterGrid() {
        GridPane registerGrid = new GridPane();
        registerGrid.setHgap(10);
        registerGrid.setVgap(10);
        registerGrid.setPadding(new Insets(20, 20, 20, 20));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        registerGrid.add(nameLabel, 0, 0);
        registerGrid.add(nameField, 1, 0);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        registerGrid.add(emailLabel, 0, 1);
        registerGrid.add(emailField, 1, 1);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        registerGrid.add(passwordLabel, 0, 2);
        registerGrid.add(passwordField, 1, 2);


        Button registerButton = new Button("Register");
        registerGrid.add(registerButton, 1, 3);

        Label errorLabel = new Label();
        registerGrid.add(errorLabel, 0, 5, 2, 1);

        registerButton.setOnAction(e -> {
            String name = nameField.getText(); // Retrieve text from nameField
            if (!name.isEmpty()) { // Check if name is not empty
                VBox userBox = createUserBox(name); // Pass name to createUserBox
                userScene.setRoot(userBox); // Set the root of userScene
                switchScene(userScene); // Switch to userScene
            } else {
                errorLabel.setText("Please enter a name."); // Show error if name is empty
            }
            String email = emailField.getText();
            String password = passwordField.getText();

            if (!database.isValidEmail(email)) {
                errorLabel.setText("Invalid email. Please make sure that the email contains '@' and '.com'.");
            }
            else {
                if (database.isValidRegister(name, email, password)) {
                    database.saveUserCredentials(name, email, password);
                    errorLabel.setText("Registration successful!");
                    clearFields(nameField, emailField, passwordField);
                    switchScene(userScene);
                } else {
                    errorLabel.setText("Account already exists.");
                }
            }
        });

        Button switchToLoginButton = new Button("Switch to Login");
        switchToLoginButton.setOnAction(e -> switchScene(loginScene));

        registerGrid.add(switchToLoginButton, 1, 4);

        return registerGrid;
    }
    private GridPane createLoginGrid() {
        GridPane loginGrid = new GridPane();
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setPadding(new Insets(20, 20, 20, 20));

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        loginGrid.add(nameLabel, 0, 0);
        loginGrid.add(nameField, 1, 0);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        loginGrid.add(emailLabel, 0, 1);
        loginGrid.add(emailField, 1, 1);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        loginGrid.add(passwordLabel, 0, 2);
        loginGrid.add(passwordField, 1, 2);

        Button loginButton = new Button("Login");
        loginGrid.add(loginButton, 1, 3);

        Label errorLabel = new Label();
        loginGrid.add(errorLabel, 0, 4, 2, 1);

        loginButton.setOnAction(e -> {
            String name = nameField.getText(); // Retrieve text from nameField
            String email = emailField.getText();
            String password = passwordField.getText();
            if (!name.isEmpty() && database.checkUserCredentials(email,password).equals("no")) { // Check if name is not empty
                VBox userBox = createUserBox(name); // Pass name to createUserBox
                userScene.setRoot(userBox); // Set the root of userScene
                switchScene(userScene); // Switch to userScene
            } else {
                errorLabel.setText("Please enter a name."); // Show error if name is empty
            }

            if (!database.isValidEmail(email)) {
                errorLabel.setText("Invalid email. Please make sure that the email contains '@' and '.com'.");
            }
            else {
                if (database.isValidLogin(email, password)) {
                    if (database.checkUserCredentials(email, password).equals("admin"))
                        switchScene(adminScene);
                    else switchScene(userScene);
                } else {
                    errorLabel.setText("Account doesnt exist.");
                }
            }
        });

        Button switchToRegisterButton = new Button("Switch to Register");
        switchToRegisterButton.setOnAction(e -> switchScene(registerScene));

        loginGrid.add(switchToRegisterButton, 1, 5);

        return loginGrid;
    }

    private VBox createAdminBox() {
        VBox adminBox = new VBox();
        adminBox.setSpacing(10);
        adminBox.setPadding(new Insets(20, 20, 20, 20));

        Label welcomeLabel = new Label("Admin Management System");
        Label welcomeLabel1 = new Label("Hello, admin!");

        Label empty = new Label();
        Button option1 = new Button("Add new animal");
        Button option2 = new Button("Add a random animal ");
        Button option3 = new Button("Print the inventory");
        Button option4 = new Button("Sell an animal");
        Button option5 = new Button("Total number of animals in store");
        Button option6 = new Button("Show animals grouped by breed");
        Button option7 = new Button("Exit application");

        welcomeLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option1.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option2.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option3.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option4.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option5.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option6.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option7.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");


        adminBox.getChildren().addAll(welcomeLabel,welcomeLabel1,empty,option1,option2,option3,option4,option5,option6,option7);

        option1.setOnAction( e -> switchScene(addScene));
//        option2.setOnAction( e -> switchScene(addAlbumToDBScene));
        option3.setOnAction( e -> switchScene(inventoryScene));
//        option4.setOnAction( e -> switchScene(printAlbumsFromDBScene));
//        option5.setOnAction( e -> switchScene(printAlbumsFromDBScene));
//        option6.setOnAction( e -> switchScene(printAlbumsFromDBScene));
        option7.setOnAction( e -> System.exit(1));

        return adminBox;
    }
    private VBox createUserBox(String name) {

        VBox userBox = new VBox();
        userBox.setSpacing(10);
        userBox.setPadding(new Insets(20, 20, 20, 20));

        String text = "Welcome, " + name + "!";
        Label welcomeLabel = new Label(text);
        Label empty = new Label();
        Button option1 = new Button("See inventory");
        Button option2 = new Button("Buy animal");
        Button option3 = new Button("Return animal");
        Button option4 = new Button("Exit application");

        welcomeLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option1.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option2.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option3.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        option4.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        userBox.getChildren().addAll(welcomeLabel,empty,option1,option2,option3,option4);

        option1.setOnAction( e -> switchScene(inventoryScene));
//        option2.setOnAction( e -> switchScene(deletePlaylistScene));
//        option3.setOnAction( e -> switchScene(addSongToPlaylistScene));
        option4.setOnAction( e -> System.exit(1));

        return userBox;
    }

    private VBox printInventoryBox(Store store) {

        VBox inventoryBox = new VBox();
        inventoryBox.setSpacing(10);
        inventoryBox.setPadding(new Insets(20, 20, 20, 20));

        Label storeLabel = new Label(store.staff1.name + "'s Store");
        inventoryBox.getChildren().add(storeLabel);
        Label staffLabel = new Label("-job: " + store.staff1.job + "\n-age: " + store.staff1.age);
        inventoryBox.getChildren().add(staffLabel);

        ArrayList<Animal> songsFromDB = database.printDB();
        int i = 1;
        for(Animal a : songsFromDB){
            Label songData = new Label(i++ + ". " + a.AnimalBreed + ":\n- price " + a.price + "\n- age " + a.age + "\n- weight " + a.weight + "\n");
            inventoryBox.getChildren().add(songData);
        }
        return inventoryBox;
    }
    private ScrollPane userInventory(Store store){
        VBox userInvBox = printInventoryBox(store);
        Button switchToMenu = new Button("Back to menu");
        switchToMenu.setOnAction(e -> switchScene(userScene));
        userInvBox.getChildren().add(switchToMenu);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(userInvBox);
        return scrollPane;
    }
    private ScrollPane adminInventory(Store store){
        VBox adminInvBox = printInventoryBox(store);
        Button switchToMenu = new Button("Back to menu");
        switchToMenu.setOnAction(e -> switchScene(adminScene));
        adminInvBox.getChildren().add(switchToMenu);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(adminInvBox);
        return scrollPane;
    }

    private GridPane addAnimalBox(){
        GridPane animalToDBGrid = new GridPane();
        animalToDBGrid.setHgap(10);
        animalToDBGrid.setVgap(10);
        animalToDBGrid.setPadding(new Insets(20, 20, 20, 20));

        Label breedLabel = new Label("Animal Breed:");
        TextField breedField = new TextField();
        animalToDBGrid.add(breedLabel, 0, 0);
        animalToDBGrid.add(breedField, 1, 0);

        Label priceLabel = new Label("Animal price:");
        TextField priceField = new TextField();
        animalToDBGrid.add(priceLabel, 0, 1);
        animalToDBGrid.add(priceField, 1, 1);

        Label ageLabel = new Label("Animal age:");
        TextField ageField = new TextField();
        animalToDBGrid.add(ageLabel, 0, 2);
        animalToDBGrid.add(ageField, 1, 2);

        Label weightLabel = new Label("Animal Weight:");
        TextField weightField = new TextField();
        animalToDBGrid.add(weightLabel, 0, 3);
        animalToDBGrid.add(weightField, 1, 3);

        Button addButton = new Button("Add");
        animalToDBGrid.add(addButton, 1, 4);

        Label errorLabel = new Label();
        animalToDBGrid.add(errorLabel, 0, 6, 2, 1);

        addButton.setOnAction(e -> {
            try {
                String breed = breedField.getText();
                int price = Integer.parseInt(priceField.getText());
                int age = Integer.parseInt(ageField.getText());
                int weight = Integer.parseInt(weightField.getText());

                errorLabel.setText("MUIE");

                if (database.isValidAnimal(breed, age, price, weight).equals("valid")) {
                    if (database.AnimalAlreadyInDB(breed, price, age, weight)) {
                        errorLabel.setText("Animal already in the database.");
                    } else {
                        //database.addAnimalToDB();
                        errorLabel.setText("Animal added successfully!");
                    }
                } else errorLabel.setText(database.isValidAnimal(breed, age, price, weight));
            } catch (NumberFormatException exception){
                errorLabel.setText("Invalid input. Please enter valid numbers.");
            }
        });

        Button switchToMenu = new Button("Back to menu");
        switchToMenu.setOnAction(e -> switchScene(adminScene));

        animalToDBGrid.add(switchToMenu, 1, 5);

        return animalToDBGrid;
    }

    private void switchScene(Scene switchToScene) {
        stackPane.getChildren().clear();
        stackPane.getChildren().add(switchToScene.getRoot());
    }
    private void clearFields(TextField nameField, TextField emailField, PasswordField passwordField) {
        nameField.clear();
        emailField.clear();
        passwordField.clear();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("My Pet Shop");

        Staff staff = new Staff("John", "manager", 20); // Example staff member
        Store store = new Store(staff);
        TextArea textArea = new TextArea();
        stackPane = new StackPane();
        WelcomeScene = new Scene(WelcomeBox(), 800, 400);
        registerScene = new Scene(createRegisterGrid(), 800, 400);
        loginScene = new Scene(createLoginGrid(), 800, 400);
        userScene = new Scene(new VBox(),800 , 400);
        adminScene = new Scene(createAdminBox(), 800, 400);
        inventoryScene = new Scene(userInventory(store), 800, 400);
        inventoryScene = new Scene(adminInventory(store), 800, 400);
        addScene = new Scene(addAnimalBox(), 800, 400);

        GridPane registerGrid = createRegisterGrid();
        nameField = (TextField) registerGrid.getChildren().get(1); // Assuming nameField is at index 1

        stackPane.getChildren().add(WelcomeScene.getRoot());
        primaryStage.setScene(new Scene(stackPane, 800, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
