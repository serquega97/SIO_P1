package BaseClasses;

public class UserRestaurant {
	
	private String user_id;
	private String restaurant_id;
	private float grade;
	
	public UserRestaurant(String user_id, String restaurant_id, float grade) {
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
		this.grade = grade;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

}
