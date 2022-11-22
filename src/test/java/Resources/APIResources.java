package Resources;

public enum APIResources {
	LoginAPI("/auth"),
	CreateBookingAPI("/booking"),
	UpdateBookingAPI("/booking");
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}

}
