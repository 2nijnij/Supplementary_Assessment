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
	
	// Method to remove a contact
	public void removeContact(Person person) {
		contacts.remove(person);
	}
	
	/**
	 * Calculate the infectiveness of the person
	 * Will return the calculated infectiveness value
	 * 
	 */
	public float getInfectiveness() {
		return (age / 100f) - (socialHygine * (age / 100f));
	}
	
	// Overridden equals method
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(name,  person.name);
	}
	
	
}
