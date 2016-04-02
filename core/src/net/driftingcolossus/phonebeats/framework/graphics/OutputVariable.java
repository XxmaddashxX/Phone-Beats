package net.driftingcolossus.phonebeats.framework.graphics;

public class OutputVariable<E> {

	private E value;
    private String insert;

    public OutputVariable(String insert, E value) {
        this.value = value;
        this.insert = insert;
    }

    public E getValue() {
        return this.value;
    }

    public String getInsert() {
        return this.insert;
    }
	
}
