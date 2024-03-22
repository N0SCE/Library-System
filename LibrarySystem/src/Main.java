import java.io.File;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        String inputFileName = args[0];
        String outputFileName = args[1];
       
        try{
            System.setOut(new PrintStream(new File(outputFileName)));
        } catch (Exception e){
            System.out.println("ERROR: Outfile is not created!");
        }
        CommandController commandController = new CommandController();
        commandController.Commands(inputFileName);
    }
}