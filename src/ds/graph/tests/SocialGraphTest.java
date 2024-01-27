package ds.graph.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ds.graph.EdgeDoesNotExist;
import ds.graph.Person;
import ds.graph.SocialGraph;
import ds.graph.PersonAlreadyExists;
import ds.graph.PersonDoesNotExist;


class SocialGraphTest {

	SocialGraph sg = new SocialGraph();
	
	@BeforeEach
	void setUp() throws Exception {
	
		// Create Persons to insert into the social graph. 
		Person a1 = new Person("Alice", 20, .2f);
		Person a2 = new Person("Abigail", 30, .43f);
		Person a3 = new Person("Anna", 40, .13f);
		Person a4 = new Person("Andy", 50, .79f);
		Person a5 = new Person("Aaron", 60, .98f);
		
		sg.addVertex(a1);
		sg.addVertex(a2);
		sg.addVertex(a3);
		sg.addVertex(a4);
		sg.addVertex(a5);
		
		sg.addEdge(a1, a2);
		sg.addEdge(a1, a3);
		sg.addEdge(a1, a4);
		
		sg.addEdge(a2, a4);
		sg.addEdge(a2, a5);
	
		
		Person b1 = new Person("Bethany", 35, .78f);
		Person b2 = new Person("Bernice", 55, .86f);
		Person b3 = new Person("Blake", 85, .27f);
		Person b4 = new Person("Bryony", 95, .82f);
		Person b5 = new Person("Bertha", 5, .01f);
		
		sg.addVertex(b1);
		sg.addVertex(b2);
		sg.addVertex(b3);
		sg.addVertex(b4);
		sg.addVertex(b5);
		
		sg.addEdge(b1, b2);
		sg.addEdge(b2, b3);
		sg.addEdge(b3, b4);
		sg.addEdge(b4, b5);
		
		sg.addEdge(b1, a2);
		sg.addEdge(b5, a3);
		

		Person c1 = new Person("Calissa", 36, .78f);
		Person c2 = new Person("Cassy", 37, .78f);
		Person c3 = new Person("Chanai", 42, .78f);
		Person c4 = new Person("Colleen", 45, .78f);
		Person c5 = new Person("Caylee", 21, .78f);
		Person c6 = new Person("Charvik", 16, .78f);
		Person c7 = new Person("Calab", 63, .78f);
		Person c8 = new Person("Cornell", 92, .78f);
		Person c9 = new Person("Chadwick", 27, .78f);
		Person c10 = new Person("Charlie", 23, .78f);
		
		sg.addVertex(c1);
		sg.addVertex(c2);
		sg.addVertex(c3);
		sg.addVertex(c4);
		sg.addVertex(c5);
		sg.addVertex(c6);
		sg.addVertex(c7);
		sg.addVertex(c8);
		sg.addVertex(c9);
		sg.addVertex(c10);
		
		sg.addEdge(a1, c1);
		sg.addEdge(c2, c3);
		sg.addEdge(c4, c5);
		sg.addEdge(c6, c1);
		
		sg.addEdge(b1, c3);
		sg.addEdge(b4, c4);
		
		sg.addEdge(a5, c7);
		sg.addEdge(c8, c9);
		sg.addEdge(b3, c10);
		sg.addEdge(c10, c8);
		
		sg.addEdge(c8, c1);
	}
	
	@Test
	void testAddVertex() throws PersonAlreadyExists {
		Person newPerson = new Person("NewPerson", 25, 0.5f);
		sg.addVertex(newPerson);
		assertNotNull(sg.getVertex("NewPerson"), "New person should be added in the graph.");
	}
	
	@Test
	void testAddDuplicateVertexThrowsException() {
		Person duplicatePerson = new Person("Alice", 20, 0.2f);
		assertThrows(PersonAlreadyExists.class, () -> {
			sg.addVertex(duplicatePerson);
			// Attempt to add the same person repeatedly
			sg.addVertex(duplicatePerson);
		}, "Adding a duplicate person should throw PersonAlreadyExists exception.");
	}
	
	@Test
	void testAddEdge() throws PersonDoesNotExist, PersonAlreadyExists {
        // set Person who are not added in setUp
		Person a = new Person("UniquePersonA", 30, 0.5f);
        Person b = new Person("UniquePersonB", 35, 0.6f);
		sg.addVertex(a);
		sg.addVertex(b);
		sg.addEdge(a, b);
		
		assertTrue(a.getContacts().contains(b) && b.getContacts().contains(a), "Edge should be added between UniquePersonA and UniquePersonB.");
		
	}
	
	@Test
	void testRemoveEdge() throws PersonDoesNotExist, EdgeDoesNotExist, PersonAlreadyExists {
		// set Person who are not added in setUp
		Person a = new Person("UniquePersonA", 30, 0.5f);
        Person b = new Person("UniquePersonB", 35, 0.6f);
		sg.addVertex(a);
		sg.addVertex(b);
		sg.addEdge(a, b);
		sg.removeEdge(a, b);
		
		assertFalse(a.getContacts().contains(b) && b.getContacts().contains(a), "Edge between UniquePersonA and UniquePersonB should be removed.");
	
	}
	
	@Test
	void testSearchBFS() throws PersonDoesNotExist {
		// Choose Alice as a  start and Aaron as a target person for BFS
		Person start = sg.getVertex("Alice");
		Person target = sg.getVertex("Aaron");	
		
		ArrayList<Person> path = sg.searchBFS(start, target);
        assertNotNull(path, "Path should not be null.");
        assertTrue(path.contains(target), "Path should contain the target person.");
	}
	
	@Test
	void testSearchDFS() throws PersonDoesNotExist {
		// Choose Alice as a  start and Aaron as a target person for BFS
		Person start = sg.getVertex("Alice");
		Person target = sg.getVertex("Aaron");	
		
		ArrayList<Person> path = sg.searchDFS(start, target);
        assertNotNull(path, "Path should not be null.");
        assertTrue(path.contains(target), "Path should contain the target person.");
    }	
}
