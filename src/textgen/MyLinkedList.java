package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<>(null);
		this.tail = new LLNode<>(null);
		head.next = tail;
		tail.prev = head;
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if (element == null)
			throw new NullPointerException("The element to add can't be null. It must be a valid element");
		LLNode newNode = new LLNode(element);

		if (this.size == 0){
			head = newNode;
			tail = newNode;
			size++;
			//System.out.println("Data added at the front =="+head.next.data);
		}
		else
		{
			LLNode<E> current = head;
			while (current.next !=null){
				current = current.next;
			}
			current.next = newNode;
			newNode.prev = current;
			//System.out.println("Data added At the back =="+current.next.data);
			size++;
		}
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index == -1 || index >= size)
			throw new IndexOutOfBoundsException("Invalid Index");
		LLNode<E> current = this.head;
		E result = null;
		int counter = 0;
		while (current!=null){

			if (counter == index)
			{
				result = current.data;
				break;
			}
			current = current.next;
			counter++;
		}
		//System.out.println("Data at index "+index+" found!::"+result);
		return result;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param 'The' index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null)
			throw new NullPointerException("The element to add cannot be null");

		//System.out.println("Index to add .."+index);
		if (index == -1)
			throw new IndexOutOfBoundsException("Wrong Index: It cannot be -1 ");

		if (size == 0){
			this.add(element);
		}
		else
		{
			if (index >= size)
				throw new IndexOutOfBoundsException("IndexOutOf Bounds. The index cannot be greater than or equal to size");

			LLNode<E> newNode = new LLNode(element);
			LLNode<E> currentNode = this.head;
			int counter = 0;

			while (currentNode!=null){
				if (counter == index){
					currentNode.prev.next = newNode;
					newNode.next = currentNode;
					newNode.prev = currentNode.prev;
					currentNode.prev = newNode;
					size++;
				}
				//System.out.println("The new size after adding =="+size);
				counter++;
				currentNode = currentNode.next;
			}
		}

	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index == -1 || index >= size)
			throw new IndexOutOfBoundsException("Invalid Index");
		LLNode<E> current = this.head;
		E removed = null;
		int counter = 0;
		while (current!=null){

			if (counter == index)
			{
				if (counter > 0){
					current.prev.next = current.next;
					if (current.next != null){ // Have you reached the last node whose next is null ?
						current.next.prev = current.prev;
					}

				}
				else if(counter == 0){
					this.head = current.next;
				}
				current.next = null;
				current.prev = null;
				removed = current.data;
				this.size--;
				break;
			}
			current = current.next;
			counter++;
		}
		//System.out.println("Data removed at index "+index+" is ::"+removed);
		return removed;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (element == null)
			throw new NullPointerException("The element to set cannot be null");
		if (index >= size)
			throw new IndexOutOfBoundsException("IndexOutOf Bounds. The index cannot be greater than or equal to size");

		if (index == -1)
			throw new IndexOutOfBoundsException("Wrong Index: It cannot be -1 ");

		LLNode<E> newNode = new LLNode(element);
		LLNode<E> currentNode = this.head;
		int counter = 0;
		E changed = null;
		while (currentNode!=null){
			if (counter == index){

				changed = currentNode.data;
				currentNode.data = element;
			}
			counter++;
			currentNode = currentNode.next;
		}
		return changed;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
