package fundamentals;

import java.util.EmptyStackException;

/**
 * Author: Pierre Schaus
 *
 * You have to implement the interface using
 * - a simple linkedList as internal structure
 * - a growing array as internal structure
 */
public interface Stack<E> {

    /**
     * Looks at the object at the top of this stack
     * without removing it from the stack
     */
    public boolean empty();

    /**
     * Returns the first element of the stack, without removing it from the stack
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E peek() throws EmptyStackException;

    /**
     * Remove the first element of the stack and returns it
     *
     * @throws EmptyStackException if the stack is empty
     */
    public E pop() throws EmptyStackException;

    /**
     * Adds an element to the stack
     *
     * @param item the item to add
     */
    public void push(E item);

}

/**
 * Implement the Stack interface above using a simple linked list.
 * You should have at least one constructor withtout argument.
 * You are not allowed to use classes from java.util
 * @param <E>
 */
class LinkedStack<E> implements Stack<E> {

    private Node<E> top;        // the node on the top of the stack
    private int size;        // size of the stack

    // helper linked list class
    private class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public LinkedStack(){
        this.top = new Node<>(null,null); //dummy node
        this.size = 1;
    }

    @Override
    public boolean empty() {
        // TODO Implement empty method
         return this.size == 1;
    }

    @Override
    public E peek() throws EmptyStackException {
        // TODO Implement peek method
         if (this.empty()){
             throw new EmptyStackException();
         } else {
             E toReturn = this.top.item;
             return toReturn;
         }
    }

    @Override
    public E pop() throws EmptyStackException {
        // TODO Implement pop method
         if (this.empty()){
             throw new EmptyStackException();
        } else {
             E toReturn = this.top.item;
             this.top = this.top.next;
             this.size--;
             return toReturn;
         }
    }

    @Override
    public void push(E item) {
        // TODO Implement push method
        Node<E> toAdd = new Node<>(item,this.top);
        this.top = toAdd;
        this.size++;
    }
}


/**
 * Implement the Stack interface above using an array as internal representation
 * The capacity of the array should double when the number of elements exceed its length.
 * You should have at least one constructor withtout argument.
 * You are not allowed to use classes from java.util
 * @param <E>
 */
class ArrayStack<E> implements Stack<E> {

    private E[] array;        // array storing the elements on the stack
    private int size;        // size of the stack

    public ArrayStack() {
        array = (E[]) new Object[10];
        this.size = 0;
    }

    @Override
    public boolean empty() {
        // TODO Implement empty method

        return this.size == 0;
    }

    @Override
    public E peek() throws EmptyStackException {
        if (this.empty()){
            throw new EmptyStackException();
        } else {
            E toReturn = this.array[0];
            return toReturn;
        }
    }

    @Override
    public E pop() throws EmptyStackException { //à vérifier
        // TODO Implement pop method
         if (this.empty()){
             throw new EmptyStackException();
         } else {
             E toReturn = this.array[0];
             this.array[0] = null;

             for (int i = 1; i < array.length ; i++) {
                 this.array[i-1] = this.array[i];
             }
             this.array[array.length-1] = null;
             this.size--;
             return toReturn;
         }
    }

    @Override
    public void push(E item) {
        // TODO Implement push method
    }
}

