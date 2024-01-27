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
	    assertEquals("Ashley", person.getName(), "Name should be Alice.");
	}
}
