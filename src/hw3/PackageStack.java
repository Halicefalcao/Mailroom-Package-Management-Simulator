//Halice Sachin Falcao
//SBU ID-115437214
//R-01

package hw3;

import java.util.Stack;

/**
 * A class representing a stack of packages.
 */

public class PackageStack {
    final int CAPACITY = 7;// The maximum capacity of the stack.
    private Stack<Package> stk;// The stack to hold the packages.

    /**
     * Constructs a new PackageStack object.
     */
    public PackageStack() {
        stk = new Stack<>();
    }

    /**
     * Pushes a package onto the stack.
     *
     * @param x the package to push onto the stack
     * @throws FullStackException if the stack is full
     */
    public void push(Package x) throws FullStackException {
        if (stk.size() == CAPACITY) {
            throw new FullStackException("Stack is full.");
        }
        stk.push(x);
    }

    /**
     * Pops a package from the stack.
     *
     * @return the package popped from the stack
     * @throws EmptyStackException if the stack is empty
     */
    public Package pop() throws EmptyStackException {
        if (stk.isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        return stk.pop();

    }

    /**
     * Peeks at the top package in the stack without removing it.
     *
     * @return the top package in the stack
     * @throws EmptyStackException if the stack is empty
     */
    public Package peek() throws EmptyStackException {
        if (stk.isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        return stk.peek();
    }

    /**
     * Checks if the stack is full.
     *
     * @return true if the stack is full, otherwise false
     */
    public boolean isFull() {
        return stk.size() == CAPACITY;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, otherwise false
     */
    public boolean isEmpty() {
        return stk.isEmpty();
    }

    /**
     * Returns a string representation of the stack.
     *
     * @return a string representation of the stack
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Package pkg : stk) {
            sb.append("[").append(pkg.getRecipient()).append(" ").append(pkg.getArrivalDate()).append("]");
        }
        if (sb.length() == 0) {
            sb.append("empty.");

        }
        return sb.toString();
    }

}