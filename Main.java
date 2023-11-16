import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        
        int indice = 0;

        do {
            System.out.println(" User Schema - My first connection from system to database");
            System.out.println("1 : Create user");
            System.out.println("2 : Update user");
            System.out.println("3 : Delete user");
            System.out.println("4 : View user(s)");
            System.out.println("5 : Login");
            System.out.println("6 : View email(s)");
            new User(0, null, null, null);

            indice = scan.nextInt();
            switch (indice) {

                case 1:
                    System.out.println("Type your name: ");
                    String name = scan.next();
                    System.out.println("Type your email: ");
                    String email = scan.next();
                    System.out.println("Type a password: ");
                    String password = scan.next();
                    User newUser = new User(0, name, email, password);
                    newUser.createUser();
                    break;

                case 2:
                    System.out.println("Type the ID that your want update: ");
                    int id = scan.nextInt();
                    System.out.println("Update the name: ");
                    name = scan.next();
                    System.out.println("Update the email: ");
                    email = scan.next();
                    System.out.println("Update the password: ");
                    password = scan.next();
                    User updateUser = new User(id, name, email, password);
                    updateUser.updateUser();
                    break;

                case 3:
                    System.out.println("Type the Id to delete from userSchema: ");
                    id = scan.nextInt();
                    User deleteUserById = new User(id, null, null, null);
                    deleteUserById.deleteUser();
                    break;

                case 4:
                    User.ViewUsers();
                    break;

                case 5:
                    System.out.println("Type your email: ");
                    email = scan.next();
                    System.out.println("Type your password: ");
                    password = scan.next();
                    User loginUser = new User(0, null, email, password);
                    loginUser.login();
                    break;

                case 6:
                    User.viewEmails();
                    break;

                default:
                    break;
            }
        } while (indice != 0);
        scan.close();
    }
}
