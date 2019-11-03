package ca.ciccc.wmad.kaden.pong.contract;

public interface PongContract {

    int CALC_BOARD_WIDTH = 2000, CALC_BOARD_HEIGHT = 1000;
    int CALC_INIT_GAP_POS_X = 300, CALC_INIT_GAP_POS_Y = 150;
    double CALC_BALL_DIAMETER = 40.0;
    double CALC_PADDLE_WIDTH = 20.0, CALC_PADDLE_HEIGHT = 150.0, CALC_PADDLE_MAX_POS_Y = CALC_BOARD_HEIGHT - CALC_PADDLE_HEIGHT;
    double CALC_PLAYER_PADDLE_POS_X = 1900.0, CALC_COM_PADDLE_POS_X = 80.0, CALC_PADDLE_POS_Y = 425.0;

    interface View {

        void setBallPosition(double x, double y);

        void setPaddlePosition(boolean isPlayer, double y);

        void addScore(boolean isPlayer);

        void initScore();

        void setDifficultyText(String difficulty);

    }

    interface Presenter {

        void resetGame();

        void startGame();

        void checkUserPaddlePosition(double diffHeightInCalc);

        void toggleDifficulty();

    }
}
