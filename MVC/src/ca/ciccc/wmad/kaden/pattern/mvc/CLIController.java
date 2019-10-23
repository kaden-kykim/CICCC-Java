package ca.ciccc.wmad.kaden.pattern.mvc;

import ca.ciccc.wmad.kaden.pattern.mvc.base.BaseController;
import ca.ciccc.wmad.kaden.pattern.mvc.base.BaseModel;
import ca.ciccc.wmad.kaden.pattern.mvc.base.BaseView;

public class CLIController implements BaseController {

    private CLIView view;
    private CLIModel model;

    @Override
    public void setView(BaseView baseView) {
        this.view = (CLIView) baseView;
    }

    @Override
    public void setModel(BaseModel baseModel) {
        this.model = (CLIModel) baseModel;
    }

    public void storeArguments(String[] args) {
        model.setArguments(args);
    }

    public void printAllArguments() {
        view.print("Hello, now Print all arguments that you inputted");
        int length = model.getNumOfArguments();
        for (int i = 0; i < length; ++i) {
            String arg = model.getArgument(i);
            view.print(arg);
        }
    }

}
