import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CustomerDatabase{
	
	Connection connection=(DatabaseConnection.getInstance()).getConnection();
	
	Statement st=null;
        PreparedStatement ps=null;
	ResultSet rs=null;
	
	public int addCustomer(Customer customer){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into customer(name,email,password,address,phone_no)values('"+customer.getName()+"','"+customer.getEmail()+"','"+customer.getPassword()+"','"+customer.getAddress()+"',"+customer.getPhoneNo()+")returning id;");
		rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("id");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public ResultSet getCustomer(long phoneNo){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select * from customer where phone_no="+phoneNo+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getCustomer(long phoneNo,String password){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select * from customer where phone_no="+phoneNo+" and password='"+password+"';");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}

	public boolean hasMoney(int customerId,double amount){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select * from customer where id="+customerId+";");
				rs.next();
				if((rs.getDouble(7))>=amount)
				return true;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateWallet(int customerId,double price,String operand){
		try{
			ps=connection.prepareStatement("update customer set wallet=wallet"+operand+""+price+"where id="+customerId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void changeAddress(int customerId,String address){
		try{
			ps=connection.prepareStatement("update customer set address='"+address+"' where id="+customerId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void changePhoneNo(int customerId,long phoneNo){
		try{
			ps=connection.prepareStatement("update customer set phone_no="+phoneNo+" where id="+customerId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
