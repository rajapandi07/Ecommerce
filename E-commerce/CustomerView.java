import java.util.List;
public class CustomerView implements AuthenticationService{

	
	CustomerDatabase customerDatabase=new CustomerDatabase();
	CustomerManager customerManager=new CustomerManager();
	Input input=new Input();
	ProductDatabase productDatabase=new ProductDatabase();
	ProductManager productManager=new ProductManager();
	DateValidation dateValidation=new DateValidation();
	VendorManager vendorManager=new VendorManager();
	Order order=null;
	List list=null;
	public void customerPage(){
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
			if(customerManager.accountExists(phoneNo)){
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
		int id=customerDatabase.addCustomer(new Customer(name,email,password,address,phoneNo));
		menu(id);
	}
	
	public void logIn(){
		boolean flag=true;
		long phoneNo=0l;
			phoneNo=input.getPhoneNo("Enter phoneNo : ");
			input.getString("");
			String password=input.getPassword("Enter password : ");
			if(customerManager.accountExists(phoneNo)){
				if(customerManager.accountExists(phoneNo,password)){
					menu(customerManager.getId(phoneNo,password));
				}
				else{
					System.out.println("Incorrect Password..");
				}
			}
			else
			   	System.out.println("Account doesn't Exists");
		
	}
	
	public void menu(int customerId){
		boolean check=true;
		while(check){
			System.out.print("+========================================+\n"
					+"|            CUSTOMER MENU               |\n"
					+"+========================================+\n"
					+"|      1.Search Product                  |\n"
					+"|      2.Cancel Order                    |\n"
					+"|      3.Change Address                  |\n"
					+"|      4.Change Phone no                 |\n"
					+"|      5.History                         |\n"
					+"|      6.Rating                          |\n"
					+"|      7.Add Money                       |\n"
					+"|      8.View cart                       |\n"
					+"|      9.Exit                            |\n"
					+"+========================================+\n");
			int dec=input.getInt("Enter : ");
			switch(dec){
				case 1:
					searchProduct(customerId);
					break;
				case 2:
					cancelOrder(customerId);
					break;
				case 3:
					input.getString("");
					String address=input.getString("Enter new Address : ");
					customerDatabase.changeAddress(customerId,address);
					System.out.println("Address successfully Updated..");
					break;
				case 4:
					long phoneNo=input.getPhoneNo("Enter new PhoneNo : ");
					customerDatabase.changePhoneNo(customerId,phoneNo);
					System.out.println("Phone number successfully Updated..");
					break;
				case 5:
					productManager.getHistory(customerId);
					break;
				case 6:
					giveRating(customerId);
					break;
				case 7:
					double money=input.getDouble("Enter Amount to be added : ");
					customerManager.updateWallet(customerId,money,true);
					System.out.println("Successfully added to your wallet..");
					break;
				case 8:
					viewCart(customerId);
					break;
				case 9:
					check=false;
					break;
				default:
					System.out.println("Enter Valid Input");
			}
		}
	}
	
	public void searchProduct(int customerId){
		input.getString("");
		String product=input.getString("Enter product Name : ");
		list=productManager.getProducts(product);
		if(!(list.isEmpty())){
			for(Object al:list){
			System.out.println(al);
			}
			System.out.println("select product id else press 0 to back");
			int productId=input.getInt("Enter : ");
			if(productId !=0){
				if(productManager.productIdExists(productId,list)){
					int quantity=input.getInt("Enter quantity : ");
					System.out.println("Press 1 to buy \nPress 2 Add to cart\n Press Any to back");
					int dec=input.getInt("Enter the option : ");
					if(dec==1){
						buyProduct(productId,customerId,quantity);
					}
					else if(dec==2){
						productDatabase.addToCart(productId,customerId,quantity);
					}
				}
				else{
					System.out.println("productId doesn't exists");
				}
			}
		}
		else{
			System.out.println("Given product is not available");
		}
	}
	
	public void buyProduct(int productId,int customerId,int quantity){
		if(productDatabase.isAvailable(productId,quantity)){
			String date=dateValidation.getCurrentDate();
			double price=quantity*(productManager.getPrice(list,productId));
			System.out.println("Select Payment Option \nPress 1 for cash on delivery\nPress 2 for pay from Wallet\n Else press Any to back");
			int dec=input.getInt("Enter : ");
			if(dec==1){
				int orderId=productDatabase.order(productId,customerId,date,quantity,price);
				productDatabase.updatePayment(orderId,"cashondelivery",false);
				productManager.updateStock(productId,quantity,false);
			}
			else if(dec==2){
				if(customerDatabase.hasMoney(customerId,price)){
					int orderId=productDatabase.order(productId,customerId,date,quantity,price);
					productDatabase.updatePayment(orderId,"Wallet",true,date);
					productManager.updateStock(productId,quantity,false);
					customerManager.updateWallet(customerId,price,false);
					vendorManager.updateWallet(productId,price,true);
					
				}
				else{
					System.out.println("Insufficient Balance on your Wallet");
				}
			}
		}
		else{
			System.out.println("!! Out Of Stock !!");
		}
	}
	
	public void giveRating(int customerId){
		list=productManager.getProducts(customerId);
		if(!(list.isEmpty())){
			for(Object al:list){
				System.out.println(al);
			}
			int productId=input.getInt("Enter productId to give Rating : ");
			if(productManager.productIdExists(productId,list)){
				int rating=input.getRating("Enter Rating : ");
				input.getString("");
				String review=input.getString("Enter review of the product : ");
				productDatabase.giveRating(productId,customerId,rating,review);
				productDatabase.updateRating(productId,rating);
				System.out.println("Thank you for Rating..");
			}
			else{
				System.out.println("Product id does not exists \nYou didn't order this product....");
			}
		}
		else{
			System.out.println("You didn't order anything...");
		}
	}
	
	public void cancelOrder(int customerId){
		list=productManager.orderedProducts(customerId);
		if(!(list.isEmpty())){
			for(Object al:list){
			System.out.println(al);
			}
			int orderId=input.getInt("Enter product id to cancel the order : ");
			if(productManager.orderIdExists(orderId,list)){
				order=productManager.getOrder(orderId,list);
				if((productDatabase.paymentMethod(orderId)).equals("wallet")){
					productDatabase.cancelOrder(orderId);
					productManager.updateStock(order.getProductid(),order.getQuantity(),true);
					customerManager.updateWallet(order.getCustomerid(),order.getPrice(),true);
					vendorManager.updateWallet(order.getProductid(),order.getPrice(),false);
				}
				else{
					productDatabase.cancelOrder(orderId);
					productManager.updateStock(order.getProductid(),order.getQuantity(),true);
				}
			}
			else{
				System.out.println("Product id does not exists \nYou didn't order this product....");
			}
		}
		else{
			System.out.println("You didn't order anything...");
		}
	}
	
	public void viewCart(int customerId){
		boolean check=true;
		while(check){
			list=productManager.getCartProducts(customerId);
			System.out.println(list);
			System.out.println("1.Buy product\n2.Remove Product\n3.Exit");
			int dec=input.getInt("Enter : ");
			if(dec==1){
				int cartId=productManager.isAvailable(list);
				if(cartId==-1){
					buyProducts(customerId,list);
				}
				else{
					System.out.println("cart id "+cartId+" is not available");
				}
			}
			else if(dec==2){
				int cartId=input.getInt("Enter cart id to remove : ");
				if(productManager.cartIdExists(cartId,list)){
					productDatabase.removeProductInCart(cartId);
					System.out.println("product Removed Successfully in your cart");
				}
				else{
					System.out.println("Cart Id doesn't Exists");
				}
			}
			else{
				check=false;
			}
		}
	}
	
	public void buyProducts(int customerId,List list){
		String date=dateValidation.getCurrentDate();
			double price=productManager.getPrice(list);
			System.out.println("Select Payment Option \nPress 1 for cash on delivery\nPress 2 for pay from Wallet\n Else press Any to back");
			int dec=input.getInt("Enter : ");
			if(dec==1){
				for(Object al:list){
				Cart product=(Cart)al;
				int orderId=productDatabase.order(product.getproductId(),customerId,date,product.getQuantity(),(product.getQuantity()+product.getPrice()));
				productDatabase.updatePayment(orderId,"cashondelivery",false);
				productManager.updateStock(product.getproductId(),product.getQuantity(),false);
				}
			}
			else if(dec==2){
				if(customerDatabase.hasMoney(customerId,price)){
				for(Object al:list){
				Cart product=(Cart)al;
				int orderId=productDatabase.order(product.getproductId(),customerId,date,product.getQuantity(),(product.getQuantity()+product.getPrice()));
					productDatabase.updatePayment(orderId,"Wallet",true,date);
					productManager.updateStock(product.getproductId(),product.getQuantity(),false);
					customerManager.updateWallet(customerId,product.getPrice(),false);
					vendorManager.updateWallet(product.getproductId(),product.getPrice(),true);
					}
					
				}
				else{
					System.out.println("Insufficient Balance on your Wallet");
				}
			}
	}
}
