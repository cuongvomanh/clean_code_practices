package args;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        Args arg = null;
        try {
            arg = new Args("l,p#,d*", args);
            boolean logging = arg.getBoolean('l');
            executeApplication(logging, 0, "");
        } catch (ArgsException e) {
            e.printStackTrace();
        }
    }

    private static void executeApplication(boolean logging, int port, String directory) {
        if (logging){
            System.out.println(String.format("Start App on port %d, in path %s.", port, directory));
        }
    }
}
