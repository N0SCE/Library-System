import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public static String[] ReadFile(String filePath){
        try{
            int i = 0;
            int lenght = Files.readAllLines(Paths.get(filePath)).size();
            String[] results = new String[lenght];
            for (String line : Files.readAllLines(Paths.get(filePath))){
                results[i++] = line;
            }

            return results;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
