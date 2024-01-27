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
        assertEquals(25, person.getAge(), "Age should be 25.");
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
}
