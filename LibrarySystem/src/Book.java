import java.util.ArrayList;
import java.util.Arrays;

public class Book {
    private static int idBook = 1;
    private String type;
    private static ArrayList<String> books = new ArrayList<>();


    public void addBook(String type){
        this.type = type;
        books.add(type);
       // System.out.println(idBook);
        if(type.equals("H")){
            System.out.println("Created new book: Handwritten [id: " + Integer.toString(idBook) + "]");
        }else if(type.equals("P")){
            System.out.println("Created new book: Printed [id: " + Integer.toString(idBook) + "]");
        }
        idBook++;
    }

    public   ArrayList<String> getBooks() {
        return books;
    }
}
