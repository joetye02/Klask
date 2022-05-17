import static java.util.concurrent.TimeUnit.*;
public class Game{

    private GameArena gameFrame;
    private Board gameBoard;
    private Striker player1, player2;
    private Goal p1G, p2G;
    private Magnet[] ballMagnets;
    private Collision hit;

    private Ball gameBall; 
    private int gameBallXSpeed = 0;
    private int gameBallYSpeed = 0;


    public Game(){
        gameFrame = new GameArena(1000,700);
        makeBoard();
        makeStrikers();
        makeGoals();
        makeMagnets();
        makeGameBall();
    
        
        while (true){
            try{
            Thread.sleep(10);
                player1.checkMovement(gameFrame);
                player2.checkMovement(gameFrame);

                player1.checkWithinBoard(gameBoard.getXY(), gameBoard.getEndOfBoardX(), gameBoard.getEndOfBoardY(), gameFrame);
                player2.checkWithinBoard(gameBoard.getXY(), gameBoard.getEndOfBoardX(), gameBoard.getEndOfBoardY(), gameFrame);
                
                if (gameBall.collides(player1.getBase()) == true){
                    hit = new Collision(player1.getBase().getXPosition(), gameBall.getXPosition(), player1.getBase().getYPosition(), gameBall.getYPosition(),
                                        player1.getXSpeed(), this.gameBallXSpeed, player1.getYSpeed(), this.gameBallYSpeed);
                    
                }else if (gameBall.collides(player2.getBase()) == true){
                    hit = new Collision();
                }
                    
            }
            
            catch(InterruptedException e)
            {
                System.out.println("hi");
                // this part is executed when an exception (in this example InterruptedException) occurs
            }
            
        }
    }
  
    private void makeBoard(){
        gameBoard = new Board();
        gameBoard.addCompToGameArena(gameFrame);
    }

    private void makeStrikers(){
        player1 = new Striker(gameBoard.getWidth() / 4 + gameBoard.getXY(), gameBoard.getHeight() / 2 + gameBoard.getXY(), 5, 1);
        player2 = new Striker((gameBoard.getWidth() / 4) + (gameBoard.getWidth() / 2) + gameBoard.getXY(), (gameBoard.getHeight() / 2) + gameBoard.getXY() , 5, 2);
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
            ballMagnets[x] = new Magnet(gameBoard.getHalfWayX(), gameBoard.getHalfWayY() + YPosCounter, 14);
            ballMagnets[x].addCompToGameArena(gameFrame);
            switch (x){case 0: YPosCounter = -70; case 1: YPosCounter *= -1;}
            System.out.println("YPosCounter: " + YPosCounter);
        }
    }
    private void makeGameBall(){
        gameBall = new Ball(gameBoard.getHalfWayX(), gameBoard.getXY() + 40, 10, "YELLOW", 8);
        gameFrame.addThing(gameBall, 6);
    }
    
    
}