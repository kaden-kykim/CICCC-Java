package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessons.ASimpleComputer;


public abstract class MVCPattern implements MVCModelInterface, MVCViewInterface, MVCControllerInterface, MVCObjectInterface {

    public MVCModelInterface model;
    public MVCViewInterface view;
    public MVCControllerInterface controller;


    public void Update() {
        UpdateModel();
        UpdateView();
        UpdateController();
    }

}
