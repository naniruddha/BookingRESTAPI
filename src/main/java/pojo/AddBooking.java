package pojo;

public class AddBooking {
	private String firstName;
	private String lastName;
	private Number totalPrice;
	private Boolean depositPaid;
	private String additionalNeeds;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Number getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Number totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Boolean getDepositPaid() {
		return depositPaid;
	}
	public void setDepositPaid(Boolean depositPaid) {
		this.depositPaid = depositPaid;
	}
	public String getAdditionalNeeds() {
		return additionalNeeds;
	}
	public void setAdditionalNeeds(String additionalNeeds) {
		this.additionalNeeds = additionalNeeds;
	}
	
}
