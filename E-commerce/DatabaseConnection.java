import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class DatabaseConnection{

        private static DatabaseConnection dbManager=null;
	Connection connection=null;
	String url="jdbc:postgresql://localhost:5432/e_commerce";
                String userName="postgres";
                String password="1234";
 
	private DatabaseConnection(){
	}

	public static DatabaseConnection getInstance(){
		if(dbManager==null){
		dbManager=new DatabaseConnection();
		}
		return dbManager;
	}

	public Connection getConnection(){
		if(connection==null){
			try{
				connection=DriverManager.getConnection(url,userName,password);
				System.out.println("Connected Successfully...");
		}
		catch(SQLException e){
		    e.printStackTrace();
		}
		}
		return connection;
	}
}
