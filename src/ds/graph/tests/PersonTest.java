package ds.graph.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ds.graph.Person;

public class PersonTest {
	private Person person;
	
	@BeforeEach
	void setUp() {
		person = new Person("Yvette", 24, 0.5f);
	}
	
	@Test
	void testGetName() {
	    assertEquals("Yvette", person.getName(), "Name should be Yvette.");
	}
	
    @Test
    void testSetName() {
        person.setName("Jisoo");
        assertEquals("Jisoo", person.getName(), "Name should be updated to Jisoo.");
    }
    
    @Test
    void testGetAge() {
        assertEquals(24, person.getAge(), "Age should be 24.");
    }
    
    @Test
    void testSetAge() {
        person.setAge(35);
        assertEquals(35, person.getAge(), "Age should be updated to 35.");
    }
    
    @Test
    void testGetSocialHygine() {
        assertEquals(0.5f, person.getSocialHygine(), 0.01, "Social hygiene should be 0.5.");
    }
    
    @Test
    // test if the getters and setters for name, age, and socialHygiene work correctly
    void testSetSocialHygine() {
        person.setSocialHygine(0.9f);
        assertEquals(0.9f, person.getSocialHygine(), 0.01, "Social hygiene should be updated to 0.9.");
    }
    
    @Test
    // verify that contacts can be correctly added from a contact list
    void testAddContact() {
        Person newContact = new Person("Joohoney", 30, 0.7f);
        person.addContact(newContact);
        assertTrue(person.getContacts().contains(newContact), "Joohoney should be added as a contact.");
    }
    
    @Test
 // verify that contacts can be correctly removed from a contact list
    void testRemoveContact() {
        Person contact = new Person("Joohoney", 30, 0.7f);
        person.addContact(contact);
        person.removeContact(contact);
        assertFalse(person.getContacts().contains(contact), "Joohoney should be removed from contacts.");
    }
    
    @Test
    // check the correctness of the calculation
    void testInfectivenessCalculation() {
        float expectedInfectiveness = (24 / 100f) - (0.5f * (24 / 100f));
        assertEquals(expectedInfectiveness, person.getInfectiveness(), 0.12, 
                     "Infectiveness should be calculated correctly.");
    }
    
    @Test
    void testEquals() {
        // Create persons with identical attributes
        Person person1 = new Person("Alice", 25, 0.75f);
        Person person2 = new Person("Alice", 25, 0.75f);

        // Test that equals method considers them equal
        assertEquals(person1, person2);
    }
    
    @Test
    void testHashCode() {
        // Create persons with identical attributes
        Person person1 = new Person("Alice", 25, 0.75f);
        Person person2 = new Person("Alice", 25, 0.75f);

        // Test that hashCode method generates the same hash for equal objects
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    
}
