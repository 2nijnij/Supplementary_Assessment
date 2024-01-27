package ds.graph;

import java.util.ArrayList;
import java.util.Objects;

public class Person {
	private float socialHygine;
	private int age;
	private String name;
	private ArrayList<Person> contacts;
	
// Constructor to initialize a Person object
	public Person(String name, int age, float socialHygine) {
		this.name = name;
		this.age = age;
		this.socialHygine = socialHygine;
		this.contacts = new ArrayList<>()
;	}
	
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
	
	// Method to add a contact
	public void addContact(Person person) {
		if (person != null && !contacts.contains(person)) {
			contacts.add(person);
		}
	}
	
	
}
