package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.EmptyListError;
import me.jjfoley.adt.errors.RanOutOfSpaceError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * A Singly-Linked List is a list that has only knowledge of its very first
 * element. Elements after that are chained, ending with a null node.
 * 
 * @author jfoley
 *
 * @param <T> - the type of the item stored in this list.
 */
public class SinglyLinkedList<T> extends ListADT<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	public T removeFront() {
		checkNotEmpty();
		T firstValue = this.start.value;
		this.start = this.start.next;
		return firstValue;
	}

	@Override
	public T removeBack() {	
		checkNotEmpty();
		
		//if only 1 item in list
		if(size() == 1) {
			return removeFront(); 
		} 
		else { 	  			
			Node<T> beforeLast = null;
  			for (Node<T> current = this.start; current.next != null; current = current.next) {
    			beforeLast = current;
  			}
 			T lastValue = beforeLast.next.value;
 			beforeLast.next = null;
			return lastValue;
			}
		}	

	@Override
	public T removeIndex(int index) {
		checkNotEmpty(); 
		//if only 1 item in list
		if(index==0) {
			return removeFront(); 
		} 
		else { 
			int at = 0; 
			for(Node<T> n = this.start; n != null; n = n.next) { 
				if(at == index-1) { 
				//need to access variable before 
					T removed = n.next.value; n.next = n.next.next; 
					return removed; 
				} at++; 
			} 
			throw new BadIndexError(index); 
		}
	}

	@Override
	public void addFront(T item) {
		this.start = new Node<T>(item, start);
	}

	@Override
	public void addBack(T item) {
		// deal with empty list
		if (this.start == null) {
			addFront(item);
			return;
		}
		// find last node
		Node<T> back = null;
		for (Node<T> here = this.start; here != null; here = here.next) {
			back = here;
		}
		assert(back.next == null);
		back.next = new Node<T>(item, null);
	}

	@Override
	public void addIndex(int index, T item) {
		checkNotEmpty();
		
		if (index < 0 || index > size()) { // if not valid/ran out of space
			throw new BadIndexError(index);
		} 
		if (this.start == null) { // if empty list
			addFront(item); // new node at start with value "item"
			System.out.println("front");
			return;	
		} else {
			
	

//			int at = 0;
//			for (Node<T> current = this.start; current != null; current = current.next) {
//				if (at++ == index) {
//					current = new Node<T>(item, current.next);
//					
//					for (int i = size()-1; i <= index; i--) {
//						current.setIndex(i+1, getIndex(i));
//					} 
//					setIndex(index, item);
//					
////					int space = size();
////					// loop backwards; slide items to the right at new given index
////					for (int i = space-1; i >= index; i--) {
////						setIndex(i+1, getIndex(i));
////					} 	
////					setIndex(index, item); // add item at given index/value	
////					space++;
//				}
//			}
//			throw new BadIndexError(index); 
		} 	
		
	}
			
//			// find last node
//			Node<T> back = null;
//			for (Node<T> here = this.start; here != null; here = here.next) {
//				back = here;
//			}
//			assert(back.next == null);
//			back.next = new Node<T>(item, null);
//			
//			// push everything at and to right of given index to the right
//			//int where = 0;
//			int space = size();
//			
//			// loop over SLL
//			for (Node<T> current = this.start; current != null; current = current.next) {
//				for(int i = index; i > index; i++) {	
//					// move everything at index to the right
//					current.setIndex(i+1, getIndex(i));
//					setIndex(index, item);
//					space++;
//				
//				// loop backwards; slide items to the right at new given index
//				for (int i = space-1; i >= index; i--) {
//					// stop when hit 
//					
//					here.setIndex(i+1, getIndex(i));
//					
//					setIndex(index, item); // add item at given index/value	
//					space++;
//			
//					if (where++ == index) {
//						here = new Node<T>(item, here.next);
//						return;
						
	
//			int at = 0;
//			for (Node<T> n = this.start; n != null; n = n.next) {
//				
//				Node<T> newNode = null; // make new node at index
//				
//				int space = size();
//				// loop backwards; slide items to the right at new given index
//				for (int i = space-1; i >= index; i--) {
//					setIndex(i+1, getIndex(i));
//				} 	
//				setIndex(index, item); // add item at given index/value	
//				space++;
//				
//				if (at++ == index) {
//					return;
//				}
//			}
//			
	
	@Override
	public T getFront() {
		checkNotEmpty();
		return this.getIndex(0);
	}

	@Override
	public T getBack() {		
		checkNotEmpty();
		return this.getIndex(this.size()-1);
	}

	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
	}

	@Override
	public void setIndex(int index, T value) {
		checkNotEmpty();
		if (index < 0 || index > size()-1) {
			throw new BadIndexError(index);
		} else {
			int num = 0;
			for(Node<T> n = this.start; n != null; n = n.next) {
				if(num == index) {
					n.value = value;					
				} num++;
			}
		}   
	}				

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	static class Node<T> {
		public int index;
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with a friend.
		 * @param value - the value to put in it.
		 * @param next - the friend of this node.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
		
		public void setIndex(int i, T index2) {
			// TODO Auto-generated method stub
			
		}

		/**
		 * Alternate constructor; create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.next = null;
		}
	}
}
