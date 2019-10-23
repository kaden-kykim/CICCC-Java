package ca.ciccc.wmad.kaden.pattern.mvc;

import ca.ciccc.wmad.kaden.pattern.mvc.base.BaseView;

public class CLIView implements BaseView {

    @Override
    public void print(String content) {
        System.out.println(content);
    }

}
