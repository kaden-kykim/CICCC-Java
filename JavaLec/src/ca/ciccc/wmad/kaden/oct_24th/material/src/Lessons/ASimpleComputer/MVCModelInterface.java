package ca.ciccc.wmad.kaden.oct_24th.material.src.Lessons.ASimpleComputer;

class ModelWriter<T> extends GenericWriter<T> {

}

class ModelReader<T> extends GenericReader<T> {

}

public interface MVCModelInterface extends MVCBaseInterface {
    //public ModelReader reader = new ModelReader();
    //public ModelWriter writer = new ModelWriter();
    public void UpdateModel();
}
