public class ShipperView{
	Input input=new Input();
	ProductDatabase productDatabase=new ProductDatabase();
	ProductManager productManager=new ProductManager();
	DateValidation dateValidation=new DateValidation();
	public void logIn(){
		boolean flag=true;
		long phoneNo=0l;
			phoneNo=input.getPhoneNo("Enter phoneNo : ");
			input.getString("");
			String password=input.getPassword("Enter password : ");
			if(productManager.accountExists(phoneNo)){
				if(productManager.accountExists(phoneNo,password)){
					delivery();
				}
				else{
					System.out.println("Incorrect Password..");
				}
			}
			else
			   	System.out.println("Account doesn't Exists");
	}
	
	public void delivery(){
		boolean check=true;
		while(check){
		System.out.println("+===================+\n"
				 + "|   1.delivery      |\n"
				  +"|   2.log out       |\n"
				  +"+===================+");
		int dec=input.getInt("Enter : ");
		if(dec==1){
			int orderId=input.getInt("Enter order id to deliver : ");
			if(productDatabase.orderedIdExists(orderId)){
				String date=dateValidation.getCurrentDate();
				productDatabase.deliverProduct(orderId,date);
				if((productDatabase.paymentMethod(orderId)).equals("cashondelivery")){
					productDatabase.updatePayment(orderId,"cashondelivery",true,date);
				}
			}
			else{
				System.out.println("order id does not exists");
			}
		}
		else if(dec==2){
			check=false;
		}
		else{
			System.out.println("Enter valid input");
		}
	}
	}
}
