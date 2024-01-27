package ds.graph;

import java.util.ArrayList;
import java.util.Objects;

public class Person {
	private float socialHygine;
	private int age;
	private String name;
	private ArrayList<Person> contacts;
	
	
	public Person(String name, int age, float socialHygine) {
		
	}
	
	// Accessor and Mutator Methods
	
	public float getSocialHygine() {
		return socialHygine;
	}
	
	public void setSocialHygine(float socialHygine) {
		this.socialHygine = socialHygine;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge (int age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Person> getContacts() {
		return contacts;
	}
	
	
	
	
}
