public class CommandController extends Member{

    private String[] lines;

    public void Commands(String inputFileName){
        lines = FileReader.ReadFile(inputFileName);
        decideCommand(lines);
    }

    public void decideCommand(String[] lines){

        for(int i = 0; i < lines.length; i++){

            boolean isEmpty = false;
            if(lines[i].isEmpty()){
                isEmpty = true;
            }

            if(!isEmpty){
                Book book = new Book();
                Member member = new Member();
                if(lines[i].startsWith("addBook")){
                    book.addBook(lines[i].split("\t")[1]);
                } else if(lines[i].startsWith("addMember")){
                    addMember(lines[i].split("\t")[1]);
                }else if(lines[i].startsWith("borrowBook")){
                    borrowBook(Integer.valueOf(lines[i].split("\t")[1]), Integer.valueOf(lines[i].split("\t")[2]), lines[i].split("\t")[3]);
                }else if(lines[i].startsWith("returnBook")){
                    //System.out.println(lines[i].split("\t")[3]);
                    returnBook(Integer.valueOf(lines[i].split("\t")[1]), Integer.valueOf(lines[i].split("\t")[2]), lines[i].split("\t")[3]);
                }else if(lines[i].startsWith("extendBook")){
                    extendDeadline(Integer.valueOf(lines[i].split("\t")[1]), Integer.valueOf(lines[i].split("\t")[2]), lines[i].split("\t")[3]);
                }else if(lines[i].startsWith("readInLibrary")){
                    readInLibrary(Integer.valueOf(lines[i].split("\t")[1]), Integer.valueOf(lines[i].split("\t")[2]), lines[i].split("\t")[3]);
                }else if(lines[i].startsWith("getTheHistory")){
                    getHistory();
                }
            }
        }

    }

}
