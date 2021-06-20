package Args;

public class Main {
    public static void main(String[] args) {
        try {
            Args arg = new Args("l,p#,d*", args);
            boolean logging = arg.parseBoolean('l');
            int port = arg.parseInt('p');
            String directory = arg.parseString('d');
            executeApplication(logging, port, directory);
        } catch (ArgsException e) {
            System.out.printf("Argument error: %s\n", e.errorMessage());
        }
    }

    private static void executeApplication(boolean logging, int port, String directory) {
        if (logging){
            System.out.println(String.format("Start App on port %d, in path %s.", port, directory));
        }
    }
}
