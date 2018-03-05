

public class PojoContactDetails {
	//Declaring variables for storing the details
		 String name,email,address;
		 long mobileNumber;
		 //Constructor 
		 PojoContactDetails(String name,String email,String address,long mobileNumber)
			{
			this.name=name;
			this.email=email;
			this.address=address;
			this.mobileNumber=mobileNumber;   
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public long getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(long mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		 
	

}
