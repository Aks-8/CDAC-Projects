package crudoparation.model;

public class employee {
   
	private int id;
	private String name;
	private String designation;
	private String emailId;
	private String phone;
	
	public employee(int id, String name, String designation, String emailId, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.emailId = emailId;
		this.phone = phone;
	}
	
	public employee(String name, String designation, String emailId, String phone) {
		super();
		this.name = name;
		this.designation = designation;
		this.emailId = emailId;
		this.phone = phone;
	}

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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhone(){
		return phone;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	
}
