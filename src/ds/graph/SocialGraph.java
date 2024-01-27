package ds.graph;

import java.util.ArrayList;
import java.util.*;

public class SocialGraph {
	private ArrayList<Person> vertices;
	
	//Constructor to initialize the SocialGraph
	public SocialGraph() {
		vertices = new ArrayList<>();
	}
	
	/**
	 * Add the given person to the graph. The person needs to be added to the list of vertices.
	 * 
	 * @param p The person to add
	 * @throws PersonAlreadyExists If the person is already present in the graph, 
	 *  		this method should throw a PersonAlreadyPresent exception. 
	 */
	public void addVertex(Person p) throws PersonAlreadyExists {
		if (vertices.contains(p)) {
			throw new PersonAlreadyExists ("Person already exists in the graph.");
		}
		vertices.add(p);
	}
	
	
	/**
	 * Remove the given Person from the graph. Any edges to this person should also be removed. 
	 * 
	 * @throws PersonDoesNotExist If the given person is not found inside the graph. 
	 * @param p The person to remove
	 */
	public void removeVertex(Person p) throws PersonDoesNotExist {
		if (!vertices.contains(p)) {
			throw new PersonDoesNotExist("The person does not exist in the graph.");
		}
		
		vertices.remove(p);
		
		// Remove this person from the contacts of all other people in the graph
		for (Person person : vertices) {
			person.removeContact(p);
		}
		
	}
	
	/**
	 * Add an edge between the two people (vertices) in the graph. The graph is undirected, so 
	 * both People will need to list the other, in their list of contacts. 
	 * 
	 * If the edge already exists, this method should return true. 
	 *
	 * @param a The first person
	 * @param b The second person
	 * @return True if the edge already exists, otherwise False
	 * @throws PersonDoesNotExist	If the person is not found within the graph. 
	 */
	public boolean addEdge(Person a, Person b) throws PersonDoesNotExist {
		if (!vertices.contains(a) || !vertices.contains(b)) {
			throw new PersonDoesNotExist("One or both person are not in the graph.");
		}
		
		boolean alreadyExists = a.getContacts().contains(b) && b.getContacts().contains(a);
		
		if (!alreadyExists) {
			a.addContact(b);
			b.addContact(a);
		}
		return alreadyExists;
	}
	
	/**
	 * Remove the edge between the given People from the graph. 
	 * If no edge existed between these people, this method should throw an EdgeDoesNotExist exception. 
	 * 
	 * @throws EdgeDoesNotExist
	 * @param a The first person
	 * @param b The second person
	 */
	public void removeEdge(Person a, Person b) throws EdgeDoesNotExist {
		if (a == null || b == null || !vertices.contains(a) || !vertices.contains(b)) {
			throw new EdgeDoesNotExist ("One or both persons are not in the graph.");
		}
		
		if (!a.getContacts().contains(b) || !b.getContacts().contains(a)) {
			throw new EdgeDoesNotExist("No edge exists between the specified person.");
		}
		
		a.removeContact(b);
		b.removeContact(a);
		
	}
	
    /**
     * Retrieves a vertex (Person) from the graph based on the name.
     * 
     * @param name The name of the person to retrieve.
     * @return The Person with the given name, or null if not found.
     */
	public Person getVertex(String name) {
		for (Person p : vertices) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		// Person not found
		return null;
	}
	
	/**
	 * Implement a breadth-first search, from Person start to target. 
	 * This method should consider the graph unweighted: the order that the Persons are stored inside the contacts list will
	 * determine the order that the BFS operates. 
	 * 
	 * @throws PersonDoesNotExist if either start or target are not in the graph. 
	 * @param start The starting person
	 * @param target The target person
	 * @return A list of nodes that must be traversed to get to target, from start. 
	 */
	public ArrayList<Person> searchBFS(Person start, Person target) throws PersonDoesNotExist {
        if (!vertices.contains(start) || !vertices.contains(target)) {
            throw new PersonDoesNotExist("Either start or target person does not exist in the graph.");
        }

        Queue<Person> queue = new LinkedList<>();
        Map<Person, Person> predecessors = new HashMap<>();
        queue.add(start);
        predecessors.put(start, null);

        while (!queue.isEmpty()) {
            Person current = queue.poll();

            if (current.equals(target)) {
                return reconstructPath(predecessors, target);
            }

            for (Person contact : current.getContacts()) {
                if (!predecessors.containsKey(contact)) {
                    queue.add(contact);
                    predecessors.put(contact, current);
                }
            }
        }
        
        // Return an empty path, if target is not found
        return new ArrayList<>();
	}
	
	// Create method to reconstruct the path from start to target
	private ArrayList<Person> reconstructPath(Map<Person, Person> predecessors, Person target) {
		ArrayList<Person> path = new ArrayList<>();
		for (Person at = target; at != null; at = predecessors.get(at)) {
			path.add(at);
		}
		Collections.reverse(path);
		return path;
	}
	
	/**
	 * Implement a breadth-first search, from Person start to target.
	 * The weights associated with each edge should determine the order that the BFS operates. 
	 * Higher weights are preferred over lower weights. The weight is found by calling getInfectiveness() on the Person. 
	 * 
	 * @throws PersonDoesNotExist if either start or target are not in the graph. 
	 * @param start The starting person
	 * @param target The target person
	 * @return A list of nodes that must be traversed to get to target, from start. 
	 */
	public ArrayList<Person> searchWeightedBFS(Person start, Person target) throws PersonDoesNotExist {
        if (!vertices.contains(start) || !vertices.contains(target)) {
        	throw new PersonDoesNotExist("Either start or target person does not exist in the graph.");
        }

        PriorityQueue<Person> queue = new PriorityQueue<>(Comparator.comparing(Person::getInfectiveness).reversed());
        Map<Person, Person> predecessors = new HashMap<>();
        queue.add(start);
        predecessors.put(start, null);

        while (!queue.isEmpty()) {
            Person current = queue.poll();

            if (current.equals(target)) {
                return reconstructPath(predecessors, target);
            }

            for (Person contact : current.getContacts()) {
                if (!predecessors.containsKey(contact)) {
                    queue.add(contact);
                    predecessors.put(contact, current);
                }
            }
        }
        // Return an empty path if target is not found
        return new ArrayList<>(); 
    }
		
	/**
	 * Implement a depth-first search, from Person start to target.  
	 * This method should consider the graph unweighted: the order that the Persons are stored inside the contacts list will
	 * determine the order that the DFS operates. 
 	 * @throws PersonDoesNotExist if either start or target are not in the graph. 	
	 * @param start The starting person
	 * @param target The target person
	 * @return A list of nodes that must be traversed to get to target, from start. 
	 */
	public ArrayList<Person> searchDFS(Person start, Person target) throws PersonDoesNotExist {
		if (!vertices.contains(start) || !vertices.contains(target)) {
            throw new PersonDoesNotExist("Either start or target person does not exist in the graph.");
        }

        Set<Person> visited = new HashSet<>();
        Map<Person, Person> predecessors = new HashMap<>();
        dfsHelper(start, target, visited, predecessors);

        if (!predecessors.containsKey(target)) {
        	// Return an empty path if target is not found
        	return new ArrayList<>(); 
        }

		return reconstructPath(predecessors, target);
	}
	
    private void dfsHelper(Person current, Person target, Set<Person> visited, Map<Person, Person> predecessors) {
        visited.add(current);

        for (Person contact : current.getContacts()) {
            if (!visited.contains(contact)) {
                predecessors.put(contact, current);
                dfsHelper(contact, target, visited, predecessors);
            }
        }
    }
	
	/**
	 * Implement a depth-first search, from Person start to target.  
	 * The weights associated with each edge should determine the order that the DFS operates. 
	 * Higher weights are preferred over lower weights. The weight is found by calling getInfectiveness() on the Person. 
	 * 
 	 * @throws PersonDoesNotExist if either start or target are not in the graph. 	
	 * @param start The starting person
	 * @param target THe target person
	 * @return A list of nodes that must be traversed to get to target, from start. 
	 */
	public ArrayList<Person> searchWeightedDFS(Person start, Person target) throws PersonDoesNotExist{
        if (!vertices.contains(start) || !vertices.contains(target)) {
            throw new PersonDoesNotExist("Either start or target person does not exist in the graph.");
        }

        Set<Person> visited = new HashSet<>();
        Map<Person, Person> predecessors = new HashMap<>();
        weightedDfsHelper(start, target, visited, predecessors);

        if (!predecessors.containsKey(target)) {
            return new ArrayList<>(); // Return an empty path if target is not found
        }

        return reconstructPath(predecessors, target);
    }
	
	/**
	 * This method should return an int value showing the total number of contacts-of-contacts of the start person. 
	 * This is the equivalent to doing a BFS around the start person, and 
	 * counting the vertices 2 steps away from the start node.  
	 * 
	 * @throws PersonDoesNotExist if either start or target are not in the graph. 	
	 * @param start
	 * @return
	 */
	public int countContacts(Person start) {
		return 0;
	}
}
