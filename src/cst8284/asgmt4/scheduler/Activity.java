
package cst8284.asgmt4.scheduler;

import java.io.*;


public class Activity implements Serializable {
	/**
	 * A String object to store the value of descriptionOfWork
	 */
	private String descriptionOfWork;
	/**
	 * A String object to store the value of category
	 */
	private String category;

	/**
	 * 2 arg constructor that is used to set category and description field using
	 * setDescription() and setCategory().
	 * 
	 * @param description The string used to set the descriptionOfWork field of
	 *                    Activity object using setDescription().
	 * @param category    The string used to set the category field of Activity
	 *                    object using setCategory().
	 */
	public Activity(String description, String category) {
		setCategory(category);
		setDescription(description);
	}

	/**
	 * Used to return the description of the activity instance using the field
	 * descriptionofWork.
	 * 
	 * @return value of String descriptionOfWork field of the activity instance
	 */
	public String getDescription() {
		return descriptionOfWork;
	}

	/**
	 * sets the descriptionofWork field in an Activity instance.
	 * 
	 * @param s used to input the value in the descriptionOfWork field of an
	 *          activity instance.
	 */
	public void setDescription(String s) {
		descriptionOfWork = s;
	}

	/**
	 * Used to return the category of the activity instance using the field
	 * category.
	 * 
	 * @return value of String category field of the category instance
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * sets the category field in an Activity instance.
	 * 
	 * @param s used to input the value in the category field of an activity
	 *          instance.
	 */
	public void setCategory(String s) {
		category = s;
	}

	/**
	 * Used whenever an activity object is printed out or when a toString() is
	 * called for actvivty object. Prints out a string with both category and
	 * description concatenated together
	 * 
	 * @return A String with category and description concatenated together to be
	 *         able to use whenever we try to print a activity instance.
	 */
	public String toString() {
		return getCategory() + "\n" + getDescription();
	}
}
