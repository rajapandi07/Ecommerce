public class Customer {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private long phoneNo;
    private long wallet;
    
    public Customer(int id, String name, String email, String password, String address, long phoneNo, long wallet) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNo = phoneNo;
        this.wallet = wallet;
    }
    
    public Customer(String name, String email, String password, String address, long phoneNo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNo = phoneNo;
    }
    
    // Getters and Setters
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public long getPhoneNo() {
        return phoneNo;
    }
    
    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public long getWallet() {
        return wallet;
    }
    
    public void setWallet(long wallet) {
        this.wallet = wallet;
    }
    
    @Override
    public String toString() {
    return "Customer{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", address='" + address + '\'' +
            ", phoneNo=" + phoneNo +
            ", wallet=" + wallet +
            '}';
}

}


