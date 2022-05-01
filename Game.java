
public class Game{

    GameArena gameFrame;
    Rectangle gameBoard;
    public Game(){
        makeGame();

    
    }
    public void makeGame(){
        gameFrame = new GameArena(1000,700);


    }
}