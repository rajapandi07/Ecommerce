import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class VendorDatabase{

	Connection connection=(DatabaseConnection.getInstance()).getConnection();
	
	Statement st=null;
        PreparedStatement ps=null;
	ResultSet rs=null;
	
	public int addVendor(Vendor vendor){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into vendor(name,email,password,address,phone_no)values('"+vendor.getName()+"','"+vendor.getEmail()+"','"+vendor.getPassword()+"','"+vendor.getAddress()+"',"+vendor.getPhoneNo()+")returning id;");
		rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("id");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public ResultSet getVendor(long phoneNo){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select * from vendor where phone_no="+phoneNo+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getVendor(long phoneNo,String password){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select * from vendor where phone_no="+phoneNo+" and password='"+password+"';");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateWallet(int vendorId,double price,String operand){
		try{
			ps=connection.prepareStatement("update vendor set wallet=wallet"+operand+""+price+" where id=(select vendorid from stock where productid="+vendorId+");");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
