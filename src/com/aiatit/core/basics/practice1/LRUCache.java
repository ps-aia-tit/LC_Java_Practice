package com.aiatit.core.basics.practice1;

import java.util.HashMap;
import java.util.Map;

/***
 * 
 * The most efficient way to implement an LRU (Least Recently Used) Cache with
 * O(1) time complexity for both get and put operations is by combining a
 * HashMap and a Doubly Linked List. The HashMap provides O(1) average time
 * complexity for finding a node based on its key. The Doubly Linked List
 * maintains the usage order, with the most recently used (MRU) item at the head
 * and the least recently used (LRU) item at the tail, allowing for O(1)
 * insertion and removal of nodes once found.
 * 
 */
public class LRUCache {

	// Node for the Doubly Linked List
	class Node {
		int key;
		int value;
		Node prev;
		Node next;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private final Map<Integer, Node> cache = new HashMap<>();
	private final int capacity;
	private Node head; // Head of the list (most recently used)
	private Node tail; // Tail of the list (least recently used)

	public LRUCache(int capacity) {
		this.capacity = capacity;
		// Initialize dummy head and tail nodes to simplify edge cases
		head = new Node(0, 0);
		tail = new Node(0, 0);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Get the value of the key if the key exists in the cache, otherwise return -1.
	 */
	public int get(int key) {
		if (cache.containsKey(key)) {
			Node node = cache.get(key);
			// Move the accessed node to the front (most recently used)
			moveToHead(node);
			return node.value;
		}
		return -1;
	}

	/**
	 * Update the value of the key if the key exists. Otherwise, add the key-value
	 * pair to the cache. If the number of keys exceeds the capacity, evict the
	 * least recently used key.
	 */
	public void put(int key, int value) {
		if (cache.containsKey(key)) {
			// Update the value and move the node to the head
			Node node = cache.get(key);
			node.value = value;
			moveToHead(node);
		} else {
			// Add a new node
			Node newNode = new Node(key, value);
			cache.put(key, newNode);
			addNode(newNode);

			// Check if capacity is reached
			if (cache.size() > capacity) {
				// Remove the least recently used item (the tail's previous node)
				Node tailNode = tail.prev;
				removeNode(tailNode);
				cache.remove(tailNode.key);
			}
		}
	}

	// --- Doubly Linked List Helper Methods ---

	/**
	 * Adds a new node right after the head (most recently used position).
	 */
	private void addNode(Node node) {
		node.prev = head;
		node.next = head.next;
		head.next.prev = node;
		head.next = node;
	}

	/**
	 * Removes a specific node from the linked list.
	 */
	private void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}

	/**
	 * Removes a node and adds it back to the head (marks it as recently used).
	 */
	private void moveToHead(Node node) {
		removeNode(node);
		addNode(node);
	}

	// Example Usage in a main method
	public static void main(String[] args) {
		LRUCache lruCache = new LRUCache(2); // Capacity of 2
		lruCache.put(1, 1); // cache is {1=1}
		lruCache.put(2, 2); // cache is {1=1, 2=2}
		System.out.println(lruCache.get(1)); // return 1. MRU: 1
		lruCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		System.out.println(lruCache.get(2)); // returns -1 (not found)
		lruCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {3=3, 4=4}
		System.out.println(lruCache.get(1)); // return -1 (not found)
		System.out.println(lruCache.get(3)); // return 3. MRU: 3
		System.out.println(lruCache.get(4)); // return 4. MRU: 4
	}
}
