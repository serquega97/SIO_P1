package BaseClasses;

public class UserRestaurant {
	
	private int user_id;
	private int restaurant_id;
	private float grade;
	
	public UserRestaurant(int user_id, int restaurant_id, float grade) {
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
		this.grade = grade;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

}
