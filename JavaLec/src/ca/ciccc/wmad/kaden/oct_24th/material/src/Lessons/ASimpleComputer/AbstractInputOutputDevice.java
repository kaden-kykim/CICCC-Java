package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessons.ASimpleComputer;

public abstract class AbstractInputOutputDevice<T> extends AbstractDevice<T> {


    public AbstractInputOutputDevice(T initialModel, int availableMemory) {
        super(initialModel, availableMemory);
        // TODO Auto-generated constructor stub
    }

    public BaseInputDevice<T> input;
    public BaseOutputDevice<T> output;


}
