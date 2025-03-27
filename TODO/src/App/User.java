package App;

import App.Queue;
import java.sql.*;
import java.util.*;
import java.util.regex.*;

public class User {
    private String username;
    private String password;
    String name;
    String email;
    Connection con;
    Queue task_onGoing;
    Queue task_history;
    public static Map<String, Task> tasks = new LinkedHashMap<>();
    Scanner sc=new Scanner(System.in);
    public User(Connection con) throws Exception{
        this.con=con;
        System.out.println("Welcome to TODO List");
        System.out.println("1.Login\n2.Sign Up");
            try{
                System.out.print("Enter your Choice: ");
                switch (sc.nextInt()) {
                    case 1:
                        this.Login();
                        break;
                    case 2:
                        this.SignUp();
                        break;
                    default:
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid Input");
            }
    }

    public void start(){
        TodoList todoList=new TodoList(con, tasks,username);
        todoList.start();
    }

    public void SignUp() throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter your Name: ");
        name=sc.nextLine();
        while(true){
            System.out.print("Enter your Email: ");
            email=sc.nextLine();
            if(isValidEmail(email)){
                PreparedStatement pst=con.prepareStatement("select email from users where email=?");
                pst.setString(1, email);
                ResultSet rs=pst.executeQuery();
                if(rs.next()){
                    System.out.println("Email already Exist");
                    continue;
                }
                else
                    break;
            }
            else{
                System.out.println("Enter Valid Email");
                continue;
            }
        }
        while(true){
            System.out.print("Enter your Username: ");
            username=sc.nextLine();
            PreparedStatement pst = con.prepareStatement("select username from users where username=?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                System.out.println("Username already exists");
                continue;
            }
            else{
              break;
            }
        }
        sc.nextLine();
        while(true){
            System.out.print("Enter Password: ");
            password=sc.nextLine();
            System.out.print("Enter Password Again: ");
            String copypassword=sc.nextLine();
            if(password.equals(copypassword)){
                break;
            }
            else{
                System.out.println("Enter same password");
                continue;
            }
        }
        PreparedStatement pst=con.prepareStatement("insert into users(username,password,name,email) values(?,?,?,?)");
        pst.setString(1, username);
        pst.setString(2, password);
        pst.setString(3, name);
        pst.setString(4, email);
        int r=pst.executeUpdate();
        if(r!=0){
            System.out.println("SuccessFull inserted into database");
        }
        else{
            System.out.println("Not Enter into Database");
        }
    }
    
    public void Login() throws Exception{
        Scanner sc=new Scanner(System.in);
        String uname;
        while(true){

            System.out.print("Enter your Username: ");
            username=sc.next();
            System.out.println(username);
            PreparedStatement pst = con.prepareStatement("select username from users where username=?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                break;
            }
            else{
                System.out.println("Username Does not Exist");
                continue;
            }
        }
        sc.nextLine();
        while(true){
            System.out.print("Enter your Password: ");
            String pass=sc.nextLine();
            PreparedStatement pst = con.prepareStatement("select * from users where username=? and password=?");
            pst.setString(1, username);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                break;
            }
            else{
                System.out.println("Wrong PassWord");
                continue;
            }
        }
    }

    // Regular expression for validating an email
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    
    // Compile the regex pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Method to validate an email address
    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}


