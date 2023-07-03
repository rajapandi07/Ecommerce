import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProductDatabase{


 	Connection connection=(DatabaseConnection.getInstance()).getConnection();
	
	Statement st=null;
        PreparedStatement ps=null;
	ResultSet rs=null;
	
	public ResultSet getCategoryId(String category){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select*from category where name='"+category+"'");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public int createCategory(String category){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into category(name)values('"+category+"')returning id;");
				rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("id");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public ResultSet getProductId(String product){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select*from product where name='"+product+"'");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public int createProduct(String product,int category){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into product(name,categoryid)values('"+product+"',"+category+")returning id;");
				rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("id");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public ResultSet getBrandId(String brand){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select*from brand where name='"+brand+"';");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public int createBrand(String brand){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into brand(name)values('"+brand+"')returning id;");
				rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("id");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public int addProduct(String model,int brand,String description,int category,int price,int product){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into productdetails(model,productid,brandid,description,categoryid,price)values('"+model+"',"+product+","+brand+",'"+description+"',"+category+","+price+")returning id;");
				rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("id");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void addStock(int productDetailId,int vendorId,int stock,int available){
		try{
			ps=connection.prepareStatement("insert into stock(productid,vendorid,stock,available)values("+productDetailId+","+vendorId+","+stock+","+available+");");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getStockDetails(int vendorid){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select s.id,b.name,d.model,d.price,s.stock,s.available from productdetails d join brand b on d.brandid=b.id join stock s on s.productid=d.id where s.vendorid="+vendorid+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public void updateStock(int stockid,int vendorid,int stock){
		try{
			ps=connection.prepareStatement("update stock set stock=stock+"+stock+",available=available+"+stock+"where id="+stockid+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getProducts(String product){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select d.id,b.name,d.model,d.price,d.rating from productdetails d join brand b on b.id=d.brandid join product p on p.id=d.productid  where p.name='"+product+"';");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public void addToCart(int productId,int customerId,int quantity){
		try{
			ps=connection.prepareStatement("insert into cart(productid,customerid,quantity)values("+productId+","+customerId+","+quantity+");");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public boolean isAvailable(int productId,int quantity){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select available from stock where productid="+productId+";");
				rs.next();
				if(rs.getInt(1)>quantity){
					return true;
				}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return false;
	}
	
	public void updateStock(int productId,int quantity,String operand){
		try{
			ps=connection.prepareStatement("update stock set available=available"+operand+""+quantity+"where productid="+productId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public int order(int productId,int customerId,String date,int quantity,double price){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into \"order\"(productid,customerid,orderdate,quantity,amount)values("+productId+","+customerId+",'"+date+"',"+quantity+","+price+")returning id;");
				rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("id");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void updatePayment(int orderId,String paymentType,boolean status,String date){
		try{
			ps=connection.prepareStatement("insert into payment(orderid,payment_type,status,delivery_date)values("+orderId+",'"+paymentType+"','"+status+"','"+date+"');");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updatePayment(int orderId,String paymentType,boolean status){
		try{
			ps=connection.prepareStatement("insert into payment(orderid,payment_type,status)values("+orderId+",'"+paymentType+"','"+status+"');");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getProducts(int customerId){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select d.id,b.name,d.model,d.price,d.rating from productdetails d join brand b on b.id=d.brandid join product p on p.id=d.productid join \"order\" o on o.productid=d.id where o.customerid="+customerId+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getVendorsProduct(int vendorId){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select d.id,b.name,d.model,d.price,d.rating from productdetails d join brand b on b.id=d.brandid join stock s on s.productid=d.id where s.vendorid="+vendorId+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getOrders(int customerId){
	try{
			st=connection.createStatement();
				rs=st.executeQuery("select o.id,(select name from brand where id=d.brandid),d.model,o.amount,o.quantity,o.orderdate,o.productid,o.customerid from \"order\" o join productdetails d on d.id=o.productid where o.customerid="+customerId+" and o.status='ordered';");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet orderHistory(int customerId){
	try{
			st=connection.createStatement();
				rs=st.executeQuery("select o.id,(select name from brand where id=d.brandid),d.model,o.amount,o.quantity,o.orderdate,o.status from \"order\" o join productdetails d on d.id=o.productid where o.customerid="+customerId+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public void giveRating(int productId,int customerId,int rating,String review){
		try{
			ps=connection.prepareStatement("insert into rating(productid,customerid,rating,review)values("+productId+","+customerId+","+rating+",'"+review+"');");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateRating(int productId,int rating){
		try{
			ps=connection.prepareStatement("update productdetails set rating=rating+"+rating+"/2 where id="+productId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void cancelOrder(int orderId){
		try{
			ps=connection.prepareStatement("update \"order\" set status='canceled' where id="+orderId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public String paymentMethod(int orderId){
		String payment="";
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select payment_type from payment where orderid="+orderId+";");
				rs.next();
				payment=rs.getString(1);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return payment;
	}
	
	public ResultSet getStocks(int vendorid){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select d.id,b.name,d.model,d.price,s.stock,s.available from productdetails d join brand b on b.id=d.brandid join stock s on s.productid=d.id where s.vendorid="+vendorid+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public void clearStock(int stockId){
		try{
			ps=connection.prepareStatement("update stock set available=0 where id="+stockId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updatePrice(int productId,int newPrice){
		try{
			ps=connection.prepareStatement("update productdetails set price="+newPrice+" where id="+productId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getRatings(int productId){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select c.name,r.rating,r.review from rating r join customer c on c.id=r.customerid where r.productid="+productId+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getCartProducts(int customerId){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select a.id,b.name,d.model,d.price,a.quantity,d.rating,a.productid from productdetails d join cart a on a.productid=d.id join brand b on b.id=d.brandid where a.customerid="+customerId+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public void removeProductInCart(int cartId){
		try{
			ps=connection.prepareStatement("delete from cart where id="+cartId+";");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getShipper(long phoneNo){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select * from shipper where phone_no="+phoneNo+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getShipper(long phoneNo,String password){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select * from shipper where phone_no="+phoneNo+" and password='"+password+"';");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean orderedIdExists(int orderId){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select * from \"order\" where id="+orderId+";");
				if(rs.next()){
					return true;
				}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public void deliverProduct(int orderId,String date){
		try{
			ps=connection.prepareStatement("update \"order\" set status='delivered',delivery_date='"+date+"' where id="+orderId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
