public class Main {
    public static void main(String[] args) {
        Output outputDevice = new Output();
        Input inputDevice = new Input();
        Application app = new Application(inputDevice,outputDevice);
        app.run(args);
    }
}
