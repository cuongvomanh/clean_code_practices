import args.Args;
import args.ArgsException;

public class Main {
    public static void main(String[] args){
        try {
            Args arg = new Args("l,p#,d*", args);
            boolean logging = arg.getBoolean('l');
            int port = arg.getInt('p');
            String directory = arg.getString('d');
            excuteApplication(logging, port, directory);
        } catch(ArgsException e){
            System.out.println(String.format("Argument error: %s\n", e.errorMessage()));
        }
    }

    private static void excuteApplication(boolean logging, int port, String directory) {
        if (logging){
            System.out.println(String.format("Start App on port %d, in path %s.", port, directory));
        }
    }
}
