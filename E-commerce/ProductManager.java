import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
public class ProductManager{

	ProductDatabase productDatabase=new ProductDatabase();
	ResultSet rs=null;
	List list=null;
	public int getCategoryId(String category){
		if((categoryExists(category))!=-1){
		try{
			rs=productDatabase.getCategoryId(category);
			rs.next();
		return rs.getInt(1);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		}
		return productDatabase.createCategory(category);
	}
	
	public int categoryExists(String category){
		int id=-1;
		try{
		rs=productDatabase.getCategoryId(category);
		if(rs.next()){
			id=rs.getInt(1);
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public int getProductId(String product,int category){
		if((productExists(product))!=-1){
		try{
			rs=productDatabase.getProductId(product);
			rs.next();
		return rs.getInt(1);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		}
		return productDatabase.createProduct(product,category);
	}
	
	public int productExists(String product){
		int id=-1;
		try{
		rs=productDatabase.getProductId(product);
		if(rs.next()){
			id=rs.getInt(1);
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public int getBrandId(String brand){
		if((brandExists(brand))!=-1){
		try{
			rs=productDatabase.getBrandId(brand);
			rs.next();
		return rs.getInt(1);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		}
		return productDatabase.createBrand(brand);
	}
	
	public int brandExists(String brand){
		int id=-1;
		try{
		rs=productDatabase.getBrandId(brand);
		if(rs.next()){
			id=rs.getInt(1);
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return id;
	}
	
	public List getStockDetails(int vendorId){
		list=new ArrayList();
		try{
		rs=productDatabase.getStockDetails(vendorId);
		while(rs.next()){
			list.add(new Stock(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean stockExists(int stockid,int vendorId){
		try{
		rs=productDatabase.getStockDetails(vendorId);
		while(rs.next()){
			if(rs.getInt(1)==stockid){
			return true;
		}
		}	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public List getProducts(String product){
		list=new ArrayList();
		try{
		rs=productDatabase.getProducts(product);
		while(rs.next()){
			list.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
		
	}
	
	public boolean productIdExists(int productId,List list){
		for(Object al:list){
			Product product=(Product)al;
			if(product.getId()==productId){
				return true;
			}
		}
		return false;
	}
	
	public boolean orderIdExists(int orderId,List list){
		for(Object al:list){
			Order order=(Order)al;
			if(order.getId()==orderId){
				return true;
			}
		}
		return false;
	}
	
	public int getPrice(List list,int productId){
		for(Object al:list){
			Product product=(Product)al;
			if(product.getId()==productId){
				return product.getPrice();
			}
		}
		return 0;
	}
	
	public List getProducts(int customerId){
		list=new ArrayList();
		try{
		rs=productDatabase.getProducts(customerId);
		while(rs.next()){
			list.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public Product getProduct(int productId,List list){
		for(Object al:list){
			Product product=(Product)al;
			if(product.getId()==productId){
				return product;
			}
		}
		return null;
	}
	
	public List getVendorsProduct(int vendorId){
		list=new ArrayList();
		try{
		rs=productDatabase.getVendorsProduct(vendorId);
		while(rs.next()){
			list.add(new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5)));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public void updateStock(int productId,int quantity,boolean condition){
		if(condition){
			productDatabase.updateStock(productId,quantity,"+");
		}
		else{
			productDatabase.updateStock(productId,quantity,"-");
		}
	}
	
	public List orderedProducts(int customerId){
		list=new ArrayList();
		try{
		rs=productDatabase.getOrders(customerId);
		while(rs.next()){
			list.add(new Order(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getInt(7),rs.getInt(8)));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public Order getOrder(int orderId,List list){
		for(Object al:list){
			Order order=(Order)al;
			if(order.getId()==orderId){
				return order;
			}
		}
		return null;
	}
	
	public List getHistory(int customerId){
		list=new ArrayList();
		try{
		rs=productDatabase.orderHistory(customerId);
		while(rs.next()){
			list.add(new OrderDetails(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List getStocks(int vendorId){
		list=new ArrayList();
		try{
		rs=productDatabase.getStocks(vendorId);
		while(rs.next()){
			list.add(new Stock(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean stockIdExists(int stockId,List list){
		for(Object al:list){
			Stock stock=(Stock)al;
			if(stock.getId()==stockId){
				return true;
			}
		}
		return false;
	}
	
	public List getRatings(int productId){
		list=new ArrayList();
		try{
		rs=productDatabase.getRatings(productId);
		while(rs.next()){
			list.add(new Rating(rs.getString(1),rs.getInt(2),rs.getString(3)));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List getCartProducts(int customerId){
		list=new ArrayList();
		try{
		rs=productDatabase.getCartProducts(customerId);
		while(rs.next()){
			list.add(new Cart(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7)));
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean cartIdExists(int cartId,List list){
		for(Object al:list){
			Cart product=(Cart)al;
			if(product.getId()==cartId){
				return true;
			}
		}
		return false;
	}
	
	public int isAvailable(List list){
		for(Object al:list){
			Cart product=(Cart)al;
			if(!(productDatabase.isAvailable(product.getproductId(),product.getQuantity()))){
				return product.getId();
			}
		}
		return -1;
	}
	
	public int getPrice(List list){
		int price=0;
		for(Object al:list){
			Cart product=(Cart)al;
			price+=(product.getPrice()*product.getQuantity());
		}
		return price;
	}
	
	public boolean accountExists(long phoneNo){
		try{
		if((productDatabase.getShipper(phoneNo)).next()){
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
		if((productDatabase.getShipper(phoneNo,password)).next()){
			return true;
		}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
