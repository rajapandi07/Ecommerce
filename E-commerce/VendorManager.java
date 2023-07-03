import java.sql.ResultSet;
import java.sql.SQLException;
public class VendorManager{
	
	VendorDatabase vendorDatabase=new VendorDatabase();
	Vendor vendor=null;
	ResultSet rs=null;
	
	public boolean accountExists(long phoneNo){
		try{
		if((vendorDatabase.getVendor(phoneNo)).next()){
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
		if((vendorDatabase.getVendor(phoneNo,password)).next()){
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
		rs=vendorDatabase.getVendor(phoneNo,password);
		if(rs.next()){
			id=rs.getInt(1);
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public void updateWallet(int productId,double price,boolean condition){
		if(condition){
			vendorDatabase.updateWallet(productId,price,"+");
		}
		else{
			vendorDatabase.updateWallet(productId,price,"-");
		}
	}
}
