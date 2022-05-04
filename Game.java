import static java.util.concurrent.TimeUnit.*;
public class Game{

    GameArena gameFrame;
    Board gameBoard;
    Striker player1, player2;
    Goal p1G, p2G;
    Magnet[] ballMagnets;



    public Game(){
        gameFrame = new GameArena(1000,700);
        makeBoard();
        makeStrikers();
        makeGoals();
        makeMagnets();
    
        int x = 1;
        while (true){
            try{
            Thread.sleep(100);
            tests(x);
            if( gameFrame.upPressed() == true){
                System.out.println("UP");
                player1.moveUp();
                player1.addCompToGameArena(gameFrame);
               gameFrame.run();
            }
            }
            catch(InterruptedException e)
            {
                // this part is executed when an exception (in this example InterruptedException) occurs
            }
            x++;
        }
    }
    private void tests(int x){
        System.out.println(x);
    }
    private void makeBoard(){
        gameBoard = new Board();
        gameBoard.addCompToGameArena(gameFrame);
    }

    private void makeStrikers(){
        player1 = new Striker(gameBoard.getWidth() / 4 + gameBoard.getXY(), gameBoard.getHeight() / 2 + gameBoard.getXY(), 4);
        player2 = new Striker((gameBoard.getWidth() / 4) + (gameBoard.getWidth() / 2) + gameBoard.getXY(), (gameBoard.getHeight() / 2) + gameBoard.getXY() , 4);
        player1.addCompToGameArena(gameFrame);
        player2.addCompToGameArena(gameFrame);
        
    }
    private void makeGoals(){
        p1G = new Goal(40 + gameBoard.getXY(), gameBoard.getHalfWayY(),5);
        p2G = new Goal(-40 + gameBoard.getEndOfBoardX(), gameBoard.getHalfWayY(), 5);
        p1G.addCompToGameArena(gameFrame);
        p2G.addCompToGameArena(gameFrame);
    }
    private void makeMagnets(){
            



        int YPosCounter = 0;
        ballMagnets = new Magnet[3];
        for (int x = 0; x < 3; x++){
            ballMagnets[x] = new Magnet(gameBoard.getHalfWayX(), gameBoard.getHalfWayY() + YPosCounter, 6);
            ballMagnets[x].addCompToGameArena(gameFrame);
            switch (x){case 0: YPosCounter = -70; case 1: YPosCounter *= -1;}
            System.out.println("YPosCounter: " + YPosCounter);
        }
    }
    
}