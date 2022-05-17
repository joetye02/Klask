import static java.util.concurrent.TimeUnit.*;
public class Game{

    private GameArena gameFrame;
    private Board gameBoard;
    private Striker player1, player2;
    private Goal p1G, p2G;
    private Magnet[] ballMagnets;
    public Collision hit, borderHit;

    private Ball gameBall; 
    private double gameBallXSpeed = 0;
    private double gameBallYSpeed = 0;


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
                player1.checkMovement(gameFrame);//checks for keyboard inputs then updates the movement of the striker depending the input
                player2.checkMovement(gameFrame);

                

                player1.checkWithinBoard(gameBoard.getXY(), gameBoard.getEndOfBoardX(), gameBoard.getEndOfBoardY(), gameFrame);//checks the striker is within the game...
                player2.checkWithinBoard(gameBoard.getXY(), gameBoard.getEndOfBoardX(), gameBoard.getEndOfBoardY(), gameFrame);//board boundaries.
                
                if (gameBall.collides(player1.getBase()) == true){
                    this.hit = new Collision(player1.getBase().getXPosition(), gameBall.getXPosition(), player1.getBase().getYPosition(), gameBall.getYPosition(),
                                        player1.getXSpeed(), this.gameBallXSpeed, player1.getYSpeed(), this.gameBallYSpeed);
                    gameBallXSpeed = hit.getXSpeed2();
                    gameBallYSpeed = hit.getYSpeed2();
                    
                    
                }else if (gameBall.collides(player2.getBase()) == true){
                    //hit = new Collision();
                }
               


                deflectArenaWalls();
                updateGameBall();
                player1.resetSpeeds();
                player2.resetSpeeds();
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
    private void updateGameBall(){

        gameBall.move(gameBallXSpeed, gameBallYSpeed);
        gameFrame.addThing(gameBall, 8);
    }
    private void deflectArenaWalls(){
        

        if (this.gameBall.getXPosition() - 2 < gameBoard.getXY()){
            Line tempLine = new Line(gameBoard.getXY(), gameBoard.getXY(), gameBoard.getXY(), gameBoard.getEndOfBoardY(), 3, "WHITE");
            gameFrame.addThing(tempLine, 1);
            this.borderHit = new Collision(tempLine.getXStart(), gameBall.getXPosition(), gameBall.getYPosition(), gameBall.getYPosition(),
            2, this.gameBallXSpeed, 2, this.gameBallYSpeed);
        }else if(this.gameBall.getYPosition() <= gameBoard.getXY()){

        }else if(this.gameBall.getXPosition() >= gameBoard.getEndOfBoardX()){

        }else if(this.gameBall.getYPosition() >= gameBoard.getEndOfBoardY()){

        }

        
    }
    
}