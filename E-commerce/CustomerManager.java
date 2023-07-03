import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class CustomerManager{

	
	CustomerDatabase customerDatabase=new CustomerDatabase();
	Customer customer=null;
	ResultSet rs=null;
	List list=null;
	public boolean accountExists(long phoneNo){
		try{
		if((customerDatabase.getCustomer(phoneNo)).next()){
			return true;
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean accountExists(long phoneNo,String password){
		try{
		if((customerDatabase.getCustomer(phoneNo,password)).next()){
			return true;
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public int getId(long phoneNo,String password){
		int id=0;
		try{
		rs=customerDatabase.getCustomer(phoneNo,password);
		if(rs.next()){
			id=rs.getInt(1);
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public void updateWallet(int customerId,double price,boolean condition){
		if(condition){
			customerDatabase.updateWallet(customerId,price,"+");
		}
		else{
			customerDatabase.updateWallet(customerId,price,"-");
		}
	}
}
