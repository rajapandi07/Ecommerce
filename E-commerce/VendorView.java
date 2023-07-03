import java.util.List;
public class VendorView implements AuthenticationService{

	
	Input input=new Input();
	VendorManager vendorManager=new VendorManager();
	VendorDatabase vendorDatabase=new VendorDatabase();
	ProductDatabase productDatabase=new ProductDatabase();
	ProductManager productManager=new ProductManager();
	List list=null;
	public void vendorPage(){
		boolean check=true;
		while(check){
			System.out.print("+=======================+\n"
					+"|     Authentication    |\n"
					+"+=======================+\n"
					+"|    1.Log In           |\n"
					+"|    2.Sign-Up          |\n"
					+"|    3.Exit             |\n"
					+"+=======================+\n");
			int dec=input.getInt("Enter : ");
			switch(dec){
				case 1:
					logIn();
					break;
				case 2:
					signUp();
					break;
				case 3:
					check=false;
					break;
				default:
					System.out.println("Enter valid Input...");
			}
		}
	}
	
	public void signUp(){
		boolean flag=true;
		long phoneNo=0l;
		while(flag){
			phoneNo=input.getPhoneNo("Enter phoneNo : ");
			if(vendorManager.accountExists(phoneNo)){
				System.out.println("Already registered with this no");
			}
			else
			    flag=false;
		}
		input.getString("");
		String password=input.getPassword("Enter password : ");
		String name=input.getName("Enter name : ");
		String email=input.getString("Enter e-mail : ");
		String address=input.getString("Enter address : ");
		int id=vendorDatabase.addVendor(new Vendor(name,email,password,address,phoneNo));
		menu(id);
	}
	
	public void logIn(){
		boolean flag=true;
		long phoneNo=0l;
			phoneNo=input.getPhoneNo("Enter phoneNo : ");
			input.getString("");
			String password=input.getPassword("Enter password : ");
			if(vendorManager.accountExists(phoneNo)){
				if(vendorManager.accountExists(phoneNo,password)){
					menu(vendorManager.getId(phoneNo,password));
				}
				else{
					System.out.println("Incorrect Password..");
				}
			}
			else
			   	System.out.println("Account doesn't Exists");
		
	}
	
	public void menu(int vendorId){
		boolean check=true;
		while(check){
			System.out.print("+=====================================+\n"
			          	+"|             VENDOR MENU             |\n"
			          	+"+=====================================+\n"
			          	+"|        1.Add Stock                  |\n"
			          	+"|        2.Add New Product            |\n"
			          	+"|        3.Product Status             |\n"
			          	+"|        4.Update product price       |\n"
			          	+"|        5.Customer Reviews           |\n"
			          	+"|        6.Remove Stock               |\n"
			          	+"|        7.Exit                       |\n"
			          	+"+=====================================+\n");
			int dec=input.getInt("Enter : ");
			switch(dec){
				case 1:
					addStock(vendorId);
					break;
				case 2:
					addProduct(vendorId);
					break;
				case 3:
					list=productManager.getStocks(vendorId);
					if(!(list.isEmpty())){
						for(Object al:list){
							System.out.println(al);
						}
					}
					else{
						System.out.println("Your Stock is empty");
					}
					break;
				case 4:
					updatePrice(vendorId);
					break;
				case 5:
					customerReviews(vendorId);
					break;
				case 6:
					clearStock(vendorId);
					break;
				case 7:
					check=false;
					break;
				default:
					System.out.println("Enter Valid Input");
			}
		}
	}
	
	public void addProduct(int vendorId){
		input.getString("");
		int category=productManager.getCategoryId(input.getString("Enter category name : "));
		int product=productManager.getProductId(input.getString("Enter product name : "),category);
		int brand=productManager.getBrandId(input.getString("Enter Brand name : "));
		String model=input.getString("Enter model name : ");
		String description=input.getString("Enter product description : ");
		int price=input.getInt("Enter product price : ");
		int quantity=input.getInt("Enter product quantity : ");
		int productDetailId=productDatabase.addProduct(model,brand,description,category,price,product);
		productDatabase.addStock(productDetailId,vendorId,quantity,quantity);
		System.out.println("Product Added successfully..");
	}
	
	public void addStock(int vendorId){
		for(Object list:productManager.getStockDetails(vendorId)){
			System.out.println(list);
		}
		int stockId=input.getInt("Enter stock Id : ");
		if(productManager.stockExists(stockId,vendorId)){
			int stock=input.getInt("Enter Stock quantity : ");
			productDatabase.updateStock(stockId,vendorId,stock);
		}
		else{
			System.out.println("The given id not found in the above list");
		}
		
	}
	
	public void clearStock(int vendorId){
		list=productManager.getStocks(vendorId);
		int stockId=input.getInt("Enter stock Id : ");
		if(productManager.stockIdExists(stockId,list)){
			productDatabase.clearStock(stockId);
			System.out.println("The given product's stock cleared successfully");
		}
	}
	
	public void updatePrice(int vendorId){
		list=productManager.getVendorsProduct(vendorId);
		int productId=input.getInt("Enter product Id : ");
		if(productManager.productIdExists(productId,list)){
			int newPrice=input.getInt("Enter a new price of the product : ");
			productDatabase.updatePrice(productId,newPrice);
			System.out.println("Product price updated successfully.");
		}
		else{
			System.out.println("Given product id doesn't exists");
		}
	}
	
	public void customerReviews(int vendorId){
		list=productManager.getVendorsProduct(vendorId);
		int productId=input.getInt("Enter product Id : ");
		if(productManager.productIdExists(productId,list)){
			Product product=productManager.getProduct(productId,list);
			System.out.println(product);
			list=productManager.getRatings(productId);
			if(!(list.isEmpty())){
			System.out.println(list);
			}
			else{
			System.out.println("Given products has no ratings...");
			}
		}
		else{
			System.out.println("Given product id doesn't exists");
		}
	}
}
