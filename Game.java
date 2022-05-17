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
        gameFrame = new GameArena(2400,1000);
        makeBoard();
        makeStrikers();
        makeGoals();
        makeMagnets();
        makeGameBall();
    
        
        while (true){
                gameFrame.pause();
            
                player1.checkMovement(gameFrame);//checks for keyboard inputs then updates the movement of the striker depending the input
                player2.checkMovement(gameFrame);

                

                player1.checkWithinBoard(gameBoard.getXY(), gameBoard.getEndOfBoardX(), gameBoard.getEndOfBoardY(), gameFrame);//checks the striker is within the game...
                player2.checkWithinBoard(gameBoard.getXY(), gameBoard.getEndOfBoardX(), gameBoard.getEndOfBoardY(), gameFrame);//board boundaries.
                
                if (gameBall.collides(player1.getBase()) == true){// player 1 collision with gameBall
                    this.hit = new Collision(player1.getBase().getXPosition(), gameBall.getXPosition(), player1.getBase().getYPosition(), gameBall.getYPosition(), player1.getXSpeed(), this.gameBallXSpeed, player1.getYSpeed(), this.gameBallYSpeed);
                    gameBallXSpeed = hit.getXSpeed2();
                    gameBallYSpeed = hit.getYSpeed2();
                    
                 
                }else if (gameBall.collides(player2.getBase()) == true){// player 2 collision with gameBall
                    this.hit = new Collision(player2.getBase().getXPosition(), gameBall.getXPosition(), player2.getBase().getYPosition(), gameBall.getYPosition(), player2.getXSpeed(), this.gameBallXSpeed, player2.getYSpeed(), this.gameBallYSpeed);
                    gameBallXSpeed = hit.getXSpeed2();
                    gameBallYSpeed = hit.getYSpeed2();
                }
                checkMagnetCollision();
                
                gameBallXSpeed *= 0.9970;
                gameBallYSpeed *= 0.9970;

                deflectArenaWalls();
                updateGameBall();

               
                
                player1.resetSpeeds();
                player2.resetSpeeds();
                
            }
            
            
            
            
        }
    
  
    private void makeBoard(){
        gameBoard = new Board();
        gameBoard.addCompToGameArena(gameFrame);
    }

    private void makeStrikers(){
        player1 = new Striker(gameBoard.getWidth() / 4 + gameBoard.getXY(), gameBoard.getHeight() / 2 + gameBoard.getXY(), 12, 1);
        player2 = new Striker((gameBoard.getWidth() / 4) + (gameBoard.getWidth() / 2) + gameBoard.getXY(), (gameBoard.getHeight() / 2) + gameBoard.getXY() , 12, 2);
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
            ballMagnets[x] = new Magnet(gameBoard.getHalfWayX(), gameBoard.getHalfWayY() + YPosCounter, 7);
            ballMagnets[x].addCompToGameArena(gameFrame);
            switch (x){case 0: YPosCounter = -180; case 1: YPosCounter *= -1;}
            System.out.println("YPosCounter: " + YPosCounter);
        }
    }
    private void makeGameBall(){
        gameBall = new Ball(gameBoard.getHalfWayX(), gameBoard.getXY() + 40, 25, "YELLOW", 8);
        gameFrame.addThing(gameBall, 6);
    }
    private void updateGameBall(){

        gameBall.move(gameBallXSpeed, gameBallYSpeed);
        //gameFrame.addThing(gameBall, 8);
    }
    private void deflectArenaWalls(){
        

        if (this.gameBall.getXPosition() <= gameBoard.getXY()){
            gameBallXSpeed = -gameBallXSpeed;
        }else if(this.gameBall.getYPosition() <= gameBoard.getXY()){
            gameBallYSpeed = -gameBallYSpeed;
        }else if(this.gameBall.getXPosition() >= gameBoard.getEndOfBoardX()){
            gameBallXSpeed = -gameBallXSpeed;
        }else if(this.gameBall.getYPosition() >= gameBoard.getEndOfBoardY()){
            gameBallYSpeed = -gameBallYSpeed;
        }

        
    }
    private void updateMagnet(){
        for(int x = 0; x < 3; x++){
            ballMagnets[x].updateMagnet(player1, player2);
        }
    }
    private void checkMagnetCollision(){
        updateMagnet();
        for(int x = 0; x < 3; x++){
            if (player1.getBase().collides(ballMagnets[x].getMagnet()) == true){
                
                ballMagnets[x].attractToStriker(player1);
            }
            if (player2.getBase().collides(ballMagnets[x].getMagnet()) == true){
                ballMagnets[x].attractToStriker(player2);
            }
        }
        
    }
    
}