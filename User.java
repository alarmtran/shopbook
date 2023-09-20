package homework.hw1.Lam_Project;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;

    private BookManager panel;

    private ArrayList<Book> listMyBook = new ArrayList<>();
    private int wallet;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.panel = new BookManager();
        this.wallet = 0;
    }

    public BookManager getPanel() {
        return panel;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        if (this instanceof Admin) {
            this.username = username;
        } else {
            System.out.println("Bạn không đủ thẩm quyền");
        }
    }

    private String getPassword(){
        return this.password;
    }
    protected String getPassword(User user) {
        if (this instanceof Admin) {
            return password;
        } else {
            System.out.println("Bạn không đủ thẩm quyền");
        }
        return null;
    }

    protected void setPassword(String password) {
        if (this instanceof Admin) {
            this.password = password;
        } else {
            System.out.println("Bạn không đủ thẩm quyền");
        }
    }

    public void buyBook(int index) {
        Book test = panel.getAllBook().get(index);
        if (wallet < test.getCost()) {
            System.out.println("=============O===========");
            System.out.println("Bạn không đủ tiền");
            System.out.println("=============O===========");
        } else {
            System.out.println("=============O===========");
            System.out.println("Mua sách thành công , bạn hãy kiểm tra tủ sách của bạn");
            System.out.println("=============O===========");
            wallet -= panel.getAllBook().get(index).getCost();
            listMyBook.add(panel.getAllBook().get(index));
        }
    }

    public int getWallet() {
        return wallet;
    }

    private void change(int wallet) {
        this.wallet = wallet;
    }
    private void setListMyBook(ArrayList<Book> list){
        this.listMyBook = list;
    }

    public void setWallet(User user, int wallet) {
        if (this instanceof Admin) {
            user.change(wallet);
        } else {
            System.out.println("Bạn không đủ thẩm quyền");
        }
    }

    public void setMyListBook(User user, ArrayList<Book> listMyBook){
        if(this instanceof Admin){
            user.setListMyBook(listMyBook);
        }else{
            System.out.println("Bạn không đủ thẩm quyền");
        }
    }

    public ArrayList<Book> getListMyBook(){
        return this.listMyBook;
    }

    public void printListMyBook(){
        if (listMyBook.size() == 0) {
            System.out.println("Bạn chưa có cuốn sách nào");
        } else {
            int index = 0;
            for (Book book : listMyBook) {
                System.out.println(++index + " " + book);
            }
        }
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
    @Override
    public String toString() {
        return String.format("Username : %s , wallet : %d , Role : User", username, wallet);
    }


}
