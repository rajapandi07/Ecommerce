public class Ecommerce{

	DateValidation date=new DateValidation();
	Input input=new Input();
	VendorView vendor=new VendorView();
	ShipperView shipper=new ShipperView();
	CustomerView customer=new CustomerView();
	public static void main(String[]args){
		
		Ecommerce ecom=new Ecommerce();
		ecom.menu();
	}
	
	public void menu(){
		boolean check=true;
		while(check){
			 System.out.print("+===================================+\n"
					+"|            MAIN MENU              |\n"
					+"|===================================|\n"
					+"|      1.Customer Page              |\n"
					+"|      2.Vendor Page                |\n"
					+"|      3.Shipper Page               |\n"
					+"|      4.Exit                       |\n"
					+"+===================================+\n");
			int dec=input.getInt("Enter : ");
			switch(dec){
				case 1:
					customer.customerPage();
					break;
				case 2:
					vendor.vendorPage();
					break;
				case 3:
					shipper.logIn();
					break;
				case 4:
					check=false;
				default:
					System.out.println("Enter Valid Input..");
			}
		}
	}
}
