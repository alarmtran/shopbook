import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Scanner;

public class SystemBook {
    private static HashMap<String, String> listAccount = new HashMap<>();
    private static ArrayList<User> listUser = readDataAccount();

    public static void loginMenu(Scanner input) {
        readDataAccount();
        while (true) {
            java.lang.System.out.println("Bạn muốn : \n1 : Tạo tài khoản \n2 : Đăng nhập \n3 : Thoát chương trình ");
            int selection = Integer.parseInt(input.nextLine());
            switch (selection) {
                case 1:
                    System.out.println("----------TẠO TÀI KHOẢN-------------- : ");
                    System.out.println("Mời bạn nhập vào tên tài khoản");
                    String username = input.nextLine();
                    while (listAccount.containsKey(username)) {
                        System.out.println("Tài khoản đã tồn tại , mời bạn nhập lại");
                        username = input.nextLine();
                    }
                    System.out.println("Mời bạn nhập vào mật khẩu");
                    String password = input.nextLine();
                    listAccount.put(username, password);
                    User user = new User(username, password);
                    listUser.add(user);
                    System.out.println("Tạo tài khoản thành công !!!");
                    break;
                case 2:
                    System.out.println("-----------ĐĂNG NHẬP--------------");
                    System.out.println("Tài khoản : ");
                    String name = input.nextLine();
                    if (listAccount.containsKey(name)) {
                        System.out.println("Mật khẩu : ");
                        String pass = input.nextLine();
                        if (listAccount.get(name).equals(pass)) {
                            System.out.println("Đăng nhập thành công");
                            for (int i = 0; i < listUser.size(); i++) {
                                if (listUser.get(i).getUsername().equals(name)) {
                                    doAction(input, listUser.get(i));
                                    break;
                                }
                            }
                        } else {
                            System.out.println("Mật khẩu không chính xác");
                        }
                    } else {
                        System.out.println("Tài khoản không chính xác hoặc không tồn tại");
                    }
                    break;
                case 3:
                    writeDataAccount(listUser);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Không hợp lệ vui lòng nhập lại");
                    selection = Integer.parseInt(input.nextLine());
            }
        }
    }

    public static void doAction(Scanner input, User user) {
        BookManager test = user.getPanel();
        while (true) {
            if (user instanceof Admin) {
                System.out.println("Menu của admin : ");
                System.out.println(" 1 : Check lịch sử đơn nạp \n 2 : Cộng tiền vào tài khoản \n 3 : Đăng xuất");
                int optionAdmin = Integer.parseInt(input.nextLine());
                switch (optionAdmin) {
                    case 1:
                        Scanner reader = null;
                        try {
                            reader = new Scanner(new File("naptien.txt"));
                            int count = 0;
                            while (reader.hasNext()) {
                                System.out.println("Đơn thứ " + ++count);
                                System.out.println("Tài khoản : " + reader.nextLine());
                                System.out.println("Giá nạp : " + reader.nextLine());
                            }
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 2:
                        System.out.println("Tên tài khoản bạn muốn cộng tiền");
                        String username = input.nextLine();
                        while (listAccount.containsKey(username) != true) {
                            System.out.println("Tài khoản không tồn tại , vui lòng nhập lại");
                            username = input.nextLine();
                        }
                        System.out.println("Số tiền bạn muốn cộng vào tài khoản : ");
                        int inputValue = Integer.parseInt(input.nextLine());
                        for (int i = 0; i < listUser.size(); i++) {
                            if (listUser.get(i).getUsername().equals(username)) {
                                user.setWallet(listUser.get(i), inputValue);
                            }
                        }
                        System.out.println("Thành công");
                        break;
                    case 3:
                        loginMenu(input);
                        break;
                    default:
                        System.out.println("Không hợp lệ , nhập lại");
                        optionAdmin = Integer.parseInt(input.nextLine());
                        break;
                }
            } else {
                System.out.println("Menu lựa chọn của cửa hàng sách : ");
                System.out.println(" 1 : Xem toàn bộ sách trong cửa hàng \n 2 : Lọc sách theo chủ đề \n 3 : Số sách mà bạn đang có \n 4 : Mua sách \n 5 : In hóa đơn \n 6 : Thoát \n 7 : Xem thông tin tài khoản của bạn \n 8 : Nạp tiền \n 9 : Đăng xuất");
                System.out.println("________________________________________________________________________________________________________________________________________________________________");
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        test.printAllBook();
                        System.out.println("________________________________________");
                        break;
                    case 2:
                        System.out.println("Dưới đây là tất cả những chủ đề sách bạn có thể lựa chọn : ");
                        test.printCategory();
                        System.out.println("Bạn muốn lựa chọn chủ đề nào");
                        String category = input.nextLine();
                        System.out.println("=============O============");
                        System.out.println("Dưới đây là những sách bạn có thể lựa chọn từ chủ đề bạn chọn : ");
                        switch (category) {
                            case "Trinh thám":
                                test.printStoreBookByFilter(test.sortBook("Trinh thám"));
                                break;
                            case "Tình cảm":
                                test.printStoreBookByFilter(test.sortBook("Tình cảm"));
                                break;
                            case "Nấu ăn":
                                test.printStoreBookByFilter(test.sortBook("Nấu ăn"));
                                break;
                            case "Lịch sử":
                                test.printStoreBookByFilter(test.sortBook("Lịch sử"));
                                break;
                            case "Lập trình":
                                test.printStoreBookByFilter(test.sortBook("Lập trình"));
                                break;
                            case "Kinh dị":
                                test.printStoreBookByFilter(test.sortBook("Kinh dị"));
                                break;
                            case "Thể thao":
                                test.printStoreBookByFilter(test.sortBook("Thể thao"));
                                break;
                            case "Thơ ca":
                                test.printStoreBookByFilter(test.sortBook("Thơ ca"));
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ , bạn hãy nhập lại ");
                                category = input.nextLine();
                                break;
                        }
                        System.out.println("________________________________________");
                        break;
                    case 3:
                        System.out.println("==================O=================");
                        System.out.println("Dưới đây là số sách bạn hiện có : ");
                        user.printListMyBook();
                        System.out.println("Bạn có muốn đọc quyển sách nào không \n 1 : Có \n 0 : Không");
                        int readChoice = Integer.parseInt(input.nextLine());
                        if (readChoice == 1) {
                            if (user.getListMyBook().size() < 1) {
                                System.out.println("Bạn không có bất kì cuốn sách nào để đọc");
                            } else {
                                System.out.println("Bạn muốn đọc quyển sách có thứ tự nào");
                                int indexBook = Integer.parseInt(input.nextLine());
                                user.readBook(indexBook - 1);
                            }
                        } else if (readChoice != 0 && readChoice != 1) {
                            System.out.println("Lựa chọn không hợp lệ");
                            readChoice = Integer.parseInt(input.nextLine());
                        }
                        break;
                    case 4:
                        System.out.println("===========O============");
                        test.printAllBook();
                        System.out.println("Dựa vào số thứ tự sách trên bạn muốn mua cuốn sách nào");
                        int index = Integer.parseInt(input.nextLine());
                        user.buyBook(index - 1);
                        break;
                    case 5:
                        System.out.println("Dưới đây là hóa đơn của bạn ");
                        test.printBill();
                        break;
                    case 6:
                        writeDataAccount(listUser);
                        System.out.println("Cảm ơn bạn đã sử dụng chương trình");
                        System.exit(0);
                        break;
                    case 7:
                        System.out.println("===================0=====================");
                        System.out.println(user);
                        System.out.println("===================0=====================");
                        break;
                    case 8:
                        System.out.println("Bạn muốn nạp bao nhiêu tiền");
                        int value = Integer.parseInt(input.nextLine());
                        System.out.println("8677778377777 MB Bank Tran Thanh Lam với nội dung : tên tài khoản ");
                        FileWriter writer = null;
                        try {
                            writer = new FileWriter("naptien.txt", true);
                            writer.append(user.getUsername() + "\n" + value + "\n");
                            writer.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 9:
                        loginMenu(input);
                    default:
                        System.out.println("Lựa chọn không hợp lệ");
                        choice = Integer.parseInt(input.nextLine());
                }
            }
        }
    }

    public static ArrayList<User> readDataAccount() {
        ArrayList<User> listUser = new ArrayList<>();
        Scanner reader = null;
        User admin = new Admin("admin", "1");
        try {
            reader = new Scanner(new File("account.txt"));
            while (reader.hasNext()) {
                String username = reader.nextLine();
                String password = reader.nextLine();
                int wallet = Integer.parseInt(reader.nextLine());
                User account = new User(username, password);
                if (username.equals("admin")) {
                    account = new Admin(username, password);
                    listAccount.put(username, password);
                } else {
                    account = new User(username, password);
                    listAccount.put(username, password);
                }
                admin.setWallet(account, wallet);
                ArrayList<Book> myBook = new ArrayList<>();
                String haveBook = reader.nextLine();
                if (haveBook == null) {
                    admin.setMyListBook(account, myBook);
                } else if (haveBook.equals("yes")) {
                    ArrayList<Book> allBook = admin.getPanel().getAllBook();
                    ArrayList<String> allTitle = new ArrayList<>();
                    for (int i = 0; i < allBook.size(); i++) {
                        allTitle.add(allBook.get(i).getTitle());
                    }
                    String[] title = reader.nextLine().split("-");
                    for (String description : title) {
                        switch (description) {
                            case "Đồng Chí":
                                myBook.add(allBook.get(0));
                                break;
                            case "Từ chối hiểu":
                                myBook.add(allBook.get(1));
                                break;
                            case "Xe đạp":
                                myBook.add(allBook.get(2));
                                break;
                            case "Bếp Lửa":
                                myBook.add(allBook.get(3));
                                break;
                            case "Người miền núi chất":
                                myBook.add(allBook.get(4));
                                break;
                        }
                    }
                    admin.setMyListBook(account, myBook);
                }
                listUser.add(account);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listUser;

    }

    public static void writeDataAccount(ArrayList<User> listUser) {
        FileWriter writer = null;
        Formatter format = null;
        User admin = new Admin("admin", "1");
        try {
            format = new Formatter("account.txt");
            format.format("admin\n1\n999999\nnull");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            format.close();
        }

        try {
            writer = new FileWriter("account.txt");
            for (int i = 0; i < listUser.size(); i++) {
                writer.append(listUser.get(i).getUsername() + "\n" + listAccount.get(listUser.get(i).getUsername()) + "\n" + listUser.get(i).getWallet() + "\n");
                ArrayList<Book> listMyBook = listUser.get(i).getListMyBook();
                if (listMyBook.size() == 0) {
                    writer.append("null\n");
                } else {
                    writer.append("yes\n");
                    for (int j = 0; j < listMyBook.size(); j++) {
                        if (j == listMyBook.size() - 1) {
                            writer.append(listMyBook.get(j).getTitle());
                        } else {
                            writer.append(listMyBook.get(j).getTitle() + "-");
                        }
                    }
                    writer.append("\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
