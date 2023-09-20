import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {

    private String title;
    private String author;
    private ArrayList<String> content = new ArrayList<>();

    private int cost;
    private String type;

    public Book(String title, String author, String type, int cost) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void getContent() {
        for (String line : content) {
            System.out.println(line);
        }
    }

    public void setContent(String filename) {
        Scanner input = null;
        ArrayList<String> newContent = new ArrayList<>();
        try {
            input = new Scanner(new File(filename));
            while (input.hasNext()) {
                newContent.add(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.content = newContent;
    }

    @Override
    public String toString() {
        return String.format("Tên sách : %s | Tác giả : %s | Thể loại : %s | Giá bìa : %d", title, author, type, cost);
    }
}
