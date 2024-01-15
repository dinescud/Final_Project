import java.io.OutputStream;
public class Output {
    OutputStream consoleOutputStream;

    Output() {
        this.consoleOutputStream = System.out;
    }

    private String lastPrintedMessage;

    public void printMessage(Object message) {
        System.out.print(message);
        lastPrintedMessage = (String) message;
    }

    public void printMessageNl(Object message) {
        System.out.println(message);
    }

    public void printStore(Store store){
        printMessageNl(store.staff1.name + "'s Store" + "\n-job: " + store.staff1.job + "\n-age: " + store.staff1.age);
        if(store.getAnimalList().length == 0) printMessageNl("\n Store is empty");
        int i = 1;
        for(Animal animal : store.getAnimalList()){
            printMessageNl(i + ". " + animal.toString());
            i++;
        }
    }
}