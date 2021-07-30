package user;

public class User {
	
	private String name;//学号
	private String password;
	private String collection;//姓名
	private String type;
	private String phone;
	
	public String getName() {
		return name;
	}
	public User() {
		super();
	}
	public User( String password, String collection,  String phone) {
		super();
		
		this.password = password;
		this.collection = collection;
		this.phone = phone;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	
}
