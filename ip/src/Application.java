import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Application {
    Input input;
    Output output;

    public Application(Input input, Output output) {
        this.input = input;
        this.output = output;
    }
    Database database = new Database();

    public void newS(){
        database.clearNewInventory();
        Staff staff1 = new Staff("John", "manager", 20);
        Store store = new Store(staff1);
        output.printStore(store);
        Animal animal = new Animal("Husky",0,0,0);
        ArrayList<Animal> animals = new ArrayList<>();

        Scanner console = new Scanner(System.in);
        int in;

        do {
            printMenu();
            in = getUserInput(console, 1, 8);
            switch (in) {
                case 1:
                    ArrayList<Animal> newAnimal = input.getRandomAnimal(1);
                    animals.addAll(newAnimal);
                    store.setAnimalList(animals);
                    for(Animal animal1:newAnimal){
                        database.saveAnimal(animal1,"new_animals");}
                    break;
                case 2:
                    int randomIndex = input.nextInt(0, store.getAnimalList().length - 1);
                    output.printMessageNl("Removing animal with index " + (randomIndex + 1));
                    for (int i = 0; i < store.animalList.size();i++){
                        if(i == randomIndex) {
                            output.printMessageNl(store.animalList.get(i).toString());
                            database.removeFromDB(store.animalList.get(i), "new_animals");
                        }
                    }
                    store.removeAnimalIdx(randomIndex);
                    output.printMessageNl("");
                    break;
                case 3:
                    output.printStore(store);
                    break;
                case 4:
                    Animal.totalWeight(animals);
                    break;
                case 5:
                    store.countAnimals(animals);
                    break;
                case 6:
                    Map<String, Integer> animalMap = Animal.groupByBreed(animals);
                    for(Map.Entry<String, Integer> entry : animalMap.entrySet()){
                        output.printMessageNl(entry.getKey() + " : " + entry.getValue());
                    }
                    break;
                case 7:
                    Animal a = animal.createAnimal();
                    animals.add(a);
                    database.saveAnimal(a,"new_animals");
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        } while (in != 0);
    }


    public void oldS() {
        output.printMessageNl("Provide table name: ");
        Scanner scanner = new Scanner(System.in);
        String table = scanner.nextLine();
        ArrayList<Animal> animals = database.storeDB();
        Staff staff1 = new Staff("John", "manager", 20);
        Store store = new Store(staff1);
        store.setAnimalList(animals);
        Animal animal = new Animal("Husky",0,0,0);

        Scanner console = new Scanner(System.in);
        int in;

        do {
            printMenu();
            in = getUserInput(console, 1, 8);
            switch (in) {
                case 1:
                    ArrayList<Animal> newAnimal = input.getRandomAnimal(1);
                    animals.addAll(newAnimal);
                    store.setAnimalList(animals);
                    for(Animal animal1:newAnimal){
                        database.saveAnimal(animal1,table);
                    }
                    break;
                case 2:
                    int randomIndex = input.nextInt(0, store.getAnimalList().length - 1);
                    output.printMessageNl("Removing animal with index " + (randomIndex + 1));
                    for (int i = 0; i < store.animalList.size();i++){
                        if(i == randomIndex) {
                            output.printMessageNl(store.animalList.get(i).toString());
                            database.removeFromDB(store.animalList.get(i), table);
                        }
                    }
                    store.removeAnimalIdx(randomIndex);
                    output.printMessageNl("");
                    break;
                case 3:
                    output.printStore(store);
                    break;
                case 4:
                    Animal.totalWeight(animals);
                    break;
                case 5:
                    store.countAnimals(animals);
                    break;
                case 6:
                    Map<String, Integer> animalMap = Animal.groupByBreed(animals);
                    for(Map.Entry<String, Integer> entry : animalMap.entrySet()){
                        output.printMessageNl(entry.getKey() + " : " + entry.getValue());
                    }
                    break;
                case 7:
                    Animal a = animal.createAnimal();
                    animals.add(a);
                    database.saveAnimal(a,table);
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        } while (in != 0);
    }

    public void run(String[] args) {
        if(args.length > 0){
            if( args[0].equals("customer")){
                customerMenu();
            }
            else if(args[0].equals("admin")){
                adminMenu(args);
            }
        }
        else {
            output.printMessageNl("\n             PetShop Management System:");
            output.printMessageNl("1. Customer");
            output.printMessageNl("2. Admin");

            Scanner console = new Scanner(System.in);
            int userInput = getUserInput(console, 1, 2);

            switch (userInput) {
                case 1:
                    customerMenu();
                    break;
                case 2:
                    adminMenu(args);
                    break;
                default:
                    System.exit(1);
            }
        }
    }

    public void adminMenu(String[] args){
        Scanner console = new Scanner(System.in);
        int userInput;
        if(args.length > 1){
            if( args[1].equals("old")){
                oldS();
            }
            else if(args[1].equals("new")){
                newS();
            }
        }
        else do{
            output.printMessageNl("\n             Admin Management System:");
            output.printMessageNl("1. Create new inverntory");
            output.printMessageNl("2. Load old inventory");

            userInput = getUserInput(console, 1, 2);

            switch (userInput) {
                case 1:
                    newS();
                    break;
                case 2:
                    oldS();
                    break;
                default:
                    System.exit(1);
            }
        } while(userInput != 0);
    }

    public void customerMenu() {
        Scanner console = new Scanner(System.in);
        int userInput;
        do {
            output.printMessageNl("\n             Customer Menu:");
            output.printMessageNl("1. View inventory");
            output.printMessageNl("2. Buy animal");
            output.printMessageNl("3. Return animal");
            output.printMessageNl("4. Exit");
            userInput = getUserInput(console, 1, 4);
            switch (userInput) {
                case 1:
                    database.printDB();
                    break;
                case 2:
                    database.buyAnimal();
                    break;
                case 3:
                    database.returnAnimal();
                    break;
                case 4:
                    System.exit(1);
            }
        }while(userInput != 0);
    }

    private class newSThread extends Thread {
        @Override
        public void run() {
            newS();
        }
    }
    private class oldSThread extends Thread {
        @Override
        public void run() {
            oldS();
        }
    }
    public static void main(String[] args) {
        Application application = new Application(new Input(), new Output());
        if (args.length > 0) {
            if (args[0].equals("customer")) {
                application.customerMenu();
            } else if (args[0].equals("admin")) {
                application.adminMenu(args);
            }
        } else {
            Scanner console = new Scanner(System.in);
            int userInput = application.getUserInput(console, 1, 2);

            switch (userInput) {
                case 1:
                    new Thread(() -> application.customerMenu()).start();
                    break;
                case 2:
                    new Thread(() -> application.adminMenu(args)).start();
                    break;
                default:
                    System.exit(1);
            }
        }
    }

    private void printMenu() {
        output.printMessageNl("       Menu");
        output.printMessageNl("1. Random animal");
        output.printMessageNl("2. Remove animal");
        output.printMessageNl("3. Print store");
        output.printMessageNl("4. Compute total weight of animals");
        output.printMessageNl("5. Count animals");
        output.printMessageNl("6. Group animals by breed");
        output.printMessageNl("7. Add animal");
        output.printMessageNl("8. Exit");
    }

    private int getUserInput(Scanner scanner, int min, int max) {
        int userInput;
        do {
            output.printMessage("Enter your choice (" + min + "-" + max + "): ");
            while (!scanner.hasNextInt()) {
                output.printMessageNl("Invalid input. Please enter a number between " + min + " and " + max + ".");
                scanner.next();
            }
            userInput = scanner.nextInt();
            if (userInput < min || userInput > max) {
                output.printMessageNl("Invalid input. Please enter a number between " + min + " and " + max + ".");
            }
        } while (userInput < min || userInput > max);

        return userInput;
    }
}
