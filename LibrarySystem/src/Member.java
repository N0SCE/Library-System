import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Member {

    private static int idMember = 1;
    private int differenceDate;
    private int extendNumber = 0;
    private String type;
    private String fee;
    private static ArrayList<String> members = new ArrayList<>();
    private static ArrayList<String> borrowedBooks = new ArrayList<>();
    private static ArrayList<String> readBooksInLibrary = new ArrayList<>();
    private int borrowedBooksNumber;
    private LocalDate borrowedBookDate;
    private LocalDate dateDeadline;
    private LocalDate returnDate;
    private LocalDate readInLibraryDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void addMember(String type){
        this.type = type;
        members.add(type);
        if(type.equals("A")){
            System.out.println("Created new member: Acamedic [id: " + Integer.toString(idMember) + "]");
        }else if(type.equals("S")){
            System.out.println("Created new member: Student [id: " + Integer.toString(idMember) + "]");
        }
        idMember++;
    }

    public ArrayList<String> getMembers(){
        return members;
    }

    public void borrowBook(int idBook, int idMember, String date){
        // check conditions

        if(members.get(idMember-1).equals("A")){
            if(borrowedBooksNumber>= 4){
                System.out.println("You have exceeded the borrowing limit!");
                return;
            }


        } else if (members.get(idMember-1).equals("S")) {
            if(borrowedBooksNumber>= 3){
                System.out.println("You have exceeded the borrowing limit!");
                return;
            }

            Book book = new Book();
            if (book.getBooks().get(idBook-1).equals("H")){
                System.out.println("You can't borrow this book!");
                return;
            }


        }
        
        borrowedBookDate = LocalDate.parse(date); 

        borrowedBooks.add(Integer.toString(idBook) + "," +Integer.toString(idMember));
        System.out.println("The book ["+ Integer.toString(idBook) + "] was borrowed by member [" + Integer.toString(idMember) + "] at " + date);

    }

    public void returnBook(int idBook, int idMember, String date){
        if(members.get(idMember-1).equals("A")){
            dateDeadline = borrowedBookDate.plusDays(14);
            returnDate = LocalDate.parse(date);
            differenceDate = (returnDate.getDayOfYear() + (returnDate.getYear() * 365)) - (dateDeadline.getDayOfYear() + (dateDeadline.getYear() * 365));
            if(differenceDate <= 0){
                fee = "0";
            }
            if(differenceDate > 0){
                fee = Integer.toString(differenceDate);
                System.out.println("The book ["+ Integer.toString(idBook) + "] was returned by member [" + Integer.toString(idMember) + "] at " + date + " Fee: " + fee);
                System.out.println("You must pay penalty! Fee: " + Integer.toString(differenceDate));
                
            }

            borrowedBooks.remove(Integer.toString(idBook)+"," +Integer.toString(idMember));
            borrowedBooksNumber--;
            return;
        }

        if(members.get(idMember-1).equals("S")){
            dateDeadline = borrowedBookDate.plusDays(7);
            returnDate = LocalDate.parse(date);
            differenceDate = (returnDate.getDayOfYear() + (returnDate.getYear() * 365)) - (dateDeadline.getDayOfYear() + (dateDeadline.getYear() * 365));
            if(differenceDate <= 0){
                fee = "0";
            }
            if(differenceDate > 0){
                fee = Integer.toString(differenceDate);
                System.out.println("The book ["+ Integer.toString(idBook) + "] was returned by member [" + Integer.toString(idMember) + "] at " + date + " Fee: " + fee);
                System.out.println("You must pay penalty! Fee: " + Integer.toString(differenceDate));
            }

            borrowedBooks.remove(Integer.toString(idBook)+"," +Integer.toString(idMember));
            borrowedBooksNumber--;
        }


    }


    public void extendDeadline(int idBook, int idMember, String date){
        // fix , do smth with ids
        if(extendNumber >= 1){
            System.out.println("You cannot extend deadline!");
            return;
        }
        borrowedBookDate = LocalDate.parse(date);
        extendNumber++;
    }

    public void readInLibrary(int idBook, int idMember, String date){
        Book book = new Book();
        if(members.get(idMember-1).equals("A")){
            if (borrowedBooks.contains(book.getBooks().get(idBook-1))){ 
                System.out.println("You can not read this book!"); 
                return;
            }

            readBooksInLibrary.add(Integer.toString(idBook)+ "," + Integer.toString(idMember));
            readInLibraryDate = LocalDate.parse(date);
            System.out.println("The book ["+ Integer.toString(idBook) + "] was read in library by member [" + Integer.toString(idMember) + "] at " + date);
        }

        if(members.get(idMember-1).equals("S")){
            if (book.getBooks().get(idBook-1).equals("H")){
                System.out.println("Students can not read handwritten books!");
                return;
            }

            if (borrowedBooks.contains(book.getBooks().get(idBook-1))){
                System.out.println("You can not read this book!"); 
                return;
            }

            readBooksInLibrary.add(Integer.toString(idBook)+ "," + Integer.toString(idMember));
            readInLibraryDate = LocalDate.parse(date);
            System.out.println("The book ["+ Integer.toString(idBook) + "] was read in library by member [" + Integer.toString(idMember) + "] at " + date);
        }
    }


    public void getHistory(){
        // Print the history
        System.out.println("History of library:\n");
        memberCalculator("S");
        memberCalculator("A");
        bookCalculator("P");
        bookCalculator("H");
        borrowedBookCalculator();
        readInLibraryCalculator();
    }

    public void memberCalculator(String type){
        int number = 0;
        int idForPrint = 0;
        for(String member: members){
            if(member.equals(type)){
                number++;
            }
        }

        if(type.equals("S")){
            System.out.println("Number of students: " + Integer.toString(number));
            for(String member: members){
                idForPrint++;
                if(member.equals(type)){
                    System.out.println("Student [id: " + Integer.toString(idForPrint) + "]");
                }
            }
        }else if(type.equals("A")){
            System.out.println("Number of acamedics: " + Integer.toString(number));
            for(String member: members){
                idForPrint++;
                if(member.equals(type)){
                    System.out.println("Acamedic [id: " + Integer.toString(idForPrint) + "]");
                }
            }
        }
        System.out.println();
        
    }

    public void bookCalculator(String type){
        int number = 0;
        int idForPrint = 0;
        Book book = new Book();
        ArrayList<String> books = new ArrayList<>();
        books = book.getBooks();
        for(String b: books){
            if(b.equals(type)){
                number++;
            }
        }

        if(type.equals("P")){
            System.out.println("Number of printed books: " + Integer.toString(number));
            for(String b: books){
                idForPrint++;
                if(b.equals(type)){
                    System.out.println("Printed [id: " + Integer.toString(idForPrint) + "]");
                }
            }
        }else if(type.equals("H")){
            System.out.println("Number of handwritten books: " + Integer.toString(number));
            for(String b: books){
                idForPrint++;
                if(b.equals(type)){
                    System.out.println("Handwritten [id: " + Integer.toString(idForPrint) + "]");
                }
            }
        }
        System.out.println();
    }

    public void borrowedBookCalculator(){
        System.out.println("Number of borrowed books: " + Integer.toString(borrowedBooks.size()));
        for(String borrowed: borrowedBooks){
            System.out.println("The book [" + borrowed.split(",")[0] + "] was borrowed by member " + "[" + borrowed.split(",")[1] + "] at " + borrowedBookDate);
            
        }

        System.out.println();
    }

    public void readInLibraryCalculator(){
        System.out.println("Number of books read in library: " + Integer.toString(readBooksInLibrary.size()));
        for(String b: readBooksInLibrary){
            System.out.println("The book [" + b.split(",")[0] + "] was read in library by member " + "[" + b.split(",")[1] + "] at " + readInLibraryDate);
            
        }
    }
}
