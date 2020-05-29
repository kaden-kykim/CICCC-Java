package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessons.ASimpleComputer;

interface CommonComputerFunctions {
    public boolean autoPowerOn = false;
    public boolean isOn = false;

    public boolean powerOn();

    public boolean powerOff();
}

interface CommonComputerDevices<T> {
    public ComputerHardDisk<String> disks = new ComputerHardDisk<String>(null, 0);
    public ComputerKeyboard<Character[]> keyboard = new ComputerKeyboard<Character[]>(null, 0);
    public ComputerMonitor<Character[]> monitor = new ComputerMonitor<Character[]>(null, 0);
    public ComputerMouse<Integer[]> mouse = new ComputerMouse<Integer[]>(null, 0);
}


public abstract class AbstractComputer<T> extends MVCPattern
        implements CommonComputerFunctions,
        CommonComputerDevices<T> {

    public boolean isOn = false;
    public boolean autoPowerOn = false;

    public AbstractComputer() {
        if (autoPowerOn) {
            powerOn();
        }

    }

    @Override
    public void finalize() {
        if (isOn == true) {
            powerOff();
        }
    }

}
