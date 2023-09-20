import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookManager {
    private static ArrayList<String> category = new ArrayList<>();
    private ArrayList<Book> listMyBook = new ArrayList<>();
    private ArrayList<Book> allBook = new ArrayList<>();

    public BookManager() {
        Scanner input = null;
        try {
            input = new Scanner(new File("update.txt"));
            while (input.hasNext()) {
                String title = input.nextLine();
                String author = input.nextLine();
                String type = input.nextLine();
                int cost = Integer.parseInt(input.nextLine());
                Book book = new Book(title, author, type, cost);
                String filename = input.nextLine();
                book.setContent(filename);
                allBook.add(book);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.category.add("Trinh thám");
        this.category.add("Tình cảm");
        this.category.add("Nấu ăn");
        this.category.add("Lịch sử");
        this.category.add("Lập trình");
        this.category.add("Kinh dị");
        this.category.add("Thể thao");
        this.category.add("Thơ ca");
    }

    public void printCategory() {
        for (int i = 0; i < category.size(); i++) {
            if (i == category.size() - 1) {
                System.out.print(category.get(i));
            } else {
                System.out.print(category.get(i) + "|");
            }
        }
        System.out.println();

    }

    public void printStoreBookByFilter(ArrayList<Book> list) {
        for (Book book : list) {
            System.out.println(book);
        }
    }

    public void printAllBook() {
        System.out.println("Toàn bộ sách hiện có của cửa hàng : ");
        int index = 0;
        for (Book book : allBook) {
            System.out.println(++index + "." + book);
        }
        System.out.println();
    }

    public ArrayList<Book> sortBook(String category) {
        ArrayList<Book> filterList = new ArrayList<>();
        for (Book book : allBook) {
            if (book.getType().equals(category)) {
                filterList.add(book);
            }
        }
        return filterList;
    }

    public ArrayList<Book> getAllBook() {
        return allBook;
    }

    public ArrayList<Book> buyBook(int index) {
        listMyBook.add(allBook.get(index));
        return listMyBook;
    }

    public void readBook(int index) {
        if (index < 0 && index >= listMyBook.size()) {
            System.out.println("Danh sách của bạn không đủ sách để có thể đọc =))");
        } else {
            System.out.println("================0===============");
            listMyBook.get(index).getContent();
            System.out.println("================0===============");
        }

    }

    public ArrayList<Book> getListMyBook() {
        return listMyBook;
    }

    public void setListMyBook(ArrayList<Book> list){
        this.listMyBook = list;
    }
    public void printMyListBook() {
        if (listMyBook.size() == 0) {
            System.out.println("Bạn chưa có cuốn sách nào");
        } else {
            int index = 0;
            for (Book book : listMyBook) {
                System.out.println(++index + " " + book);
            }
        }
    }

    private void sort() {
        for (int i = 0; i < allBook.size() - 1; i++) {
            for (int j = i + 1; j < allBook.size(); j++) {
                if (allBook.get(j).getTitle().compareTo(allBook.get(i).getTitle()) < 0) {
                    Book temp = allBook.get(i);
                    allBook.set(i, allBook.get(j));
                    allBook.set(j, temp);
                }
            }
        }
    }

    public void printBill() {
        int sumCost = 0;
        int index = 0;
        for (Book book : listMyBook) {
            System.out.println(++index + " " + book);
            sumCost += book.getCost();
        }
        System.out.println("Tổng thanh toán " + sumCost);
    }

    public BookManager updateBook() {
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập số lượng sách muốn nhập vào cửa hàng");
        int amount = Integer.parseInt(input.nextLine());
        for (int i = 0; i < amount; i++) {
            System.out.println("Nhập tên cuốn sách : ");
            String nameBook = input.nextLine();
            System.out.println("Nhập tên tác giả : ");
            String nameAuthor = input.nextLine();
            System.out.println("Nhập thể loại sách : ");
            System.out.println("Hiện tại có những thể loại sách sau : ");
            printCategory();
            String type = input.nextLine();
            System.out.println("Nhập giá bìa của cuốn sách");
            int cost = Integer.parseInt(input.nextLine());
            Book book = new Book(nameBook, nameAuthor, type, cost);
            System.out.println("Nhập file .txt để truyền vào nội dung cuốn sách");
            String filename = input.nextLine();
            book.setContent(filename);
            allBook.add(book);
        }
        sort();
        return this;
    }
}
