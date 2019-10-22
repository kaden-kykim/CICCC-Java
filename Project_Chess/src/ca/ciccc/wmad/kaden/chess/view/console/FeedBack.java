package ca.ciccc.wmad.kaden.chess.view.console;

public enum FeedBack {

    FB_WELCOME_MESSAGE("Welcome to Chess Game! Please enter the menu number!"),
    FB_INCORRECT_ANSWER("Please input correctly"),
    FB_NOT_GAMING("There is no playing game"),
    FB_WARNING_PROGRESS_LOST("Progress of current game will be lost. Are you Okay? (y/N)"),
    FB_GAME_NOT_UNDO("Can NOT undo! This is your first turn."),
    FB_GAME_SELECT_PIECE("Please select your piece"),
    FB_GAME_WAIT_COMPUTER("Please wait for Computer..."),
    FB_NONE("");

    private final String feedback;

    FeedBack(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return feedback;
    }
}
