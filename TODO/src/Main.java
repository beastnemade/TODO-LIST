import App.User;
import java.sql.*;

public class Main {
    public static void main(String[] args)  throws Exception {
            Connection con=getConnect();
            User user=new User(con);
            user.start();   
        
    }
    public static Connection getConnect() throws Exception{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LJ", "root", "");
            if(con!=null){
                System.out.println("Connected Successfull");
            }
            else{
                System.out.println("Connection failed");
                System.exit(0);
            }
            return con;
        }
}   