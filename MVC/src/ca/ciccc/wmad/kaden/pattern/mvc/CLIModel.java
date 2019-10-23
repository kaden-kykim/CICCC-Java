package ca.ciccc.wmad.kaden.pattern.mvc;

import ca.ciccc.wmad.kaden.pattern.mvc.base.BaseModel;

public class CLIModel implements BaseModel {

    private String[] arguments;

    @Override
    public void setArguments(String[] args) {
        this.arguments = args;
    }

    public int getNumOfArguments() {
        return arguments.length;
    }

    public String getArgument(int index) {
        return arguments[index];
    }

}
