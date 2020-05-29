package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessons.ASimpleComputer;

import java.util.ArrayList;

public abstract class AbstractMemory<T> {

    public ArrayList<T> model = new ArrayList<T>();
    protected int availableMemory = 0;

    public AbstractMemory(T initialModel, int availableMemory) {
        this.model.add(initialModel);
        this.availableMemory = availableMemory;
        // TODO Auto-generated constructor stub
    }

}
