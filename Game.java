import static java.util.concurrent.TimeUnit.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Random;
public class Game implements ActionListener {

    private GameArena gameFrame;
    private Board gameBoard;
    private Striker player1, player2;
    private Goal p1G, p2G;
    private Magnet[] ballMagnets;
    public Collision hit, borderHit;

    private Ball gameBall; 
    private double gameBallXSpeed = 0;
    private double gameBallYSpeed = 0;

    private boolean collided = false;
    private int newGame;// does the user wish to play again?

    private Text p1Score, p2Score;
    private short gameOutCome = 0; 

    private int gameCount = 1;
    // 0 = no winner yet.
    // 1 = p1 wins due to p2 attaching 2 magnets
    // 2 = p2 wins due to p1 attaching 2 magnets
    // 3 = p1 gets scored 5 goals
    // 4 = p2 score 5 goals
    public Game(int newGame){
        this.newGame = newGame;
        gameFrame = new GameArena(2400,1000);
        makeBoard();
        makeStrikers();
        makeGoals();
        makeMagnets();
        makeGameBall(gameBoard.getHalfWayX(), gameBoard.getXY() + 40);
        makeText();
    
        
        while (gameOutCome == 0){
                gameFrame.pause();
    
                player1.checkMovement(gameFrame);//checks for keyboard inputs then updates the movement of the striker depending the input
                player2.checkMovement(gameFrame);
                player1.checkWithinBoard(gameBoard.getXY(), gameBoard.getEndOfBoardX(), gameBoard.getEndOfBoardY(), gameFrame, gameBoard.getHalfWayX());//checks the striker is within the game...
                player2.checkWithinBoard(gameBoard.getXY(), gameBoard.getEndOfBoardX(), gameBoard.getEndOfBoardY(), gameFrame, gameBoard.getHalfWayX());//board boundaries.
            
                if (gameBall.collides(player1.getBase()) == true){// player 1 collision with gameBall
                    this.hit = new Collision(player1.getBase().getXPosition(), gameBall.getXPosition(), player1.getBase().getYPosition(), gameBall.getYPosition(), player1.getXSpeed(), this.gameBallXSpeed * 2, player1.getYSpeed(), this.gameBallYSpeed * 2);
                    gameBallXSpeed = hit.getXSpeed2();
                    gameBallYSpeed = hit.getYSpeed2();
                }else if (gameBall.collides(player2.getBase()) == true){// player 2 collision with gameBall
                    this.hit = new Collision(player2.getBase().getXPosition(), gameBall.getXPosition(), player2.getBase().getYPosition(), gameBall.getYPosition(), player2.getXSpeed(), this.gameBallXSpeed * 2, player2.getYSpeed(), this.gameBallYSpeed * 2);
                    gameBallXSpeed = hit.getXSpeed2();
                    gameBallYSpeed = hit.getYSpeed2();
                }
                checkMagnetCollision();
                seperateMagnets();
                gameBallXSpeed *= 0.9930;
                gameBallYSpeed *= 0.9930;
                deflectArenaWalls();
                updateGameBall();
                player1.resetSpeeds();
                player2.resetSpeeds();
                updateScores();
                checkWin();
                gameCount*=1;


                
                
            }
            
            
            
            
        }
    
  
    private void makeBoard(){
        gameBoard = new Board();
        gameBoard.addCompToGameArena(gameFrame);
    }
    private void checkWin(){
        if(player1.lost(ballMagnets) == true){
            gameOutCome = 2;
        }else if(player2.lost(ballMagnets) == true){
            gameOutCome = 1;
        }else if(p1G.getGoalsconceeded() == 5){
            gameOutCome = 4;
        }else if(p2G.getGoalsconceeded() == 5){
            gameOutCome = 3;
        }
    }
    private void updateScores(){
        Random rand = new Random();
        int UB = 6;
        int RND1 = rand.nextInt(UB);
        if (p1G.checkGoal(gameBall, p2Score) == true){//p1 has conceeded
            gameFrame.removeBall(gameBall);
            if (RND1 < 3){
                makeGameBall(gameBoard.getXY() + 40, gameBoard.getXY() + 40);
            }else if (RND1 >= 3){
                makeGameBall(gameBoard.getXY() + 40, gameBoard.getEndOfBoardY() - 40);
            }
            gameBallXSpeed = 0;
            gameBallYSpeed = 0;
        }else if (p2G.checkGoal(gameBall, p1Score) == true){//p2 has conceeded
            gameFrame.removeBall(gameBall);
           if (RND1 >= 3){
                makeGameBall(gameBoard.getEndOfBoardX() - 40, gameBoard.getXY() + 40);
           }else if (RND1 < 3){
                makeGameBall(gameBoard.getEndOfBoardX() - 40, gameBoard.getEndOfBoardY() - 40);
           }    
            gameBallXSpeed = 0;
            gameBallYSpeed = 0;
        }
    }

    private void makeStrikers(){
        //p1 x = gameBoard.getWidth() / 4 + gameBoard.getXY()
        //p1 y = gameBoard.getHeight() / 2 + gameBoard.getXY()
        //p2 x = (gameBoard.getWidth() / 4) + (gameBoard.getWidth() / 2) + gameBoard.getXY()
        //p2 y = (gameBoard.getHeight() / 2) + gameBoard.getXY()

        player1 = new Striker(gameBoard.getWidth() / 4 + gameBoard.getXY(), gameBoard.getHeight() / 2 + gameBoard.getXY(), 12, 1);
        player2 = new Striker((gameBoard.getWidth() / 4) + (gameBoard.getWidth() / 2) + gameBoard.getXY(), (gameBoard.getHeight() / 2) + gameBoard.getXY(), 12, 2);
        player1.addCompToGameArena(gameFrame);
        player2.addCompToGameArena(gameFrame);
        
    }
    private void makeGoals(){
        p1G = new Goal(80 + gameBoard.getXY(), gameBoard.getHalfWayY(),5);
        p2G = new Goal(-80 + gameBoard.getEndOfBoardX(), gameBoard.getHalfWayY(), 5);
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
    private void makeGameBall(int xPosm, int yPosm){
        gameBall = new Ball(xPosm, yPosm, 40, "YELLOW", 8);
        gameFrame.addThing(gameBall, 6);
    }
    private void updateGameBall(){

        gameBall.move(gameBallXSpeed, gameBallYSpeed);
        //gameFrame.addThing(gameBall, 8);
    }
    private void makeText(){
        p1Score = new Text("0", 100, (gameBoard.getXY()/2) - 15, gameBoard.getHalfWayY() + 17, "WHITE");
        p2Score = new Text("0", 100, gameBoard.getEndOfBoardX() + 40, gameBoard.getHalfWayY() + 17, "WHITE");
        Text title = new Text("KLASK", 100, gameBoard.getHalfWayX() - 170, 100, "WHITE");
        Line titleUnderline = new Line(725, 120, 1200, 120, 10, "WHITE", 2);

        gameFrame.addThing(p1Score, 1);
        gameFrame.addThing(p2Score, 1);
        gameFrame.addThing(title, 1);
        gameFrame.addThing(titleUnderline, 1);
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
    private void seperateMagnets(){
        if(ballMagnets[0].getMagnet().getXPosition() ==  ballMagnets[1].getMagnet().getXPosition()){
              ballMagnets[0].getMagnet().setXPosition(ballMagnets[0].getMagnet().getXPosition() + 6);
              ballMagnets[1].getMagnet().setYPosition(ballMagnets[1].getMagnet().getYPosition() -4);
          } 
          if(ballMagnets[0].getMagnet().getXPosition() == ballMagnets[2].getMagnet().getXPosition()){
              ballMagnets[0].getMagnet().setXPosition(ballMagnets[0].getMagnet().getXPosition() - 7);
              ballMagnets[2].getMagnet().setYPosition(ballMagnets[2].getMagnet().getYPosition() -3);
          } 
          if(ballMagnets[1].getMagnet().getXPosition() ==  ballMagnets[2].getMagnet().getXPosition()){
              ballMagnets[1].getMagnet().setXPosition(ballMagnets[1].getMagnet().getXPosition() - 5);
              ballMagnets[2].getMagnet().setYPosition(ballMagnets[2].getMagnet().getYPosition() +7);
          } 
    }
    
    
    private void checkMagnetCollision(){
        
        for(int x = 0; x < 3; x++){
            if (player1.getBase().collides(ballMagnets[x].getAreaBall()) == true){
                //System.out.println("TRUE");
                ballMagnets[x].attractToStriker(player1);
                ballMagnets[x].checkAttatched(player1, x);
                
                
            }//else{System.out.println("FALSE");}
            if (player2.getBase().collides(ballMagnets[x].getAreaBall()) == true){
                ballMagnets[x].attractToStriker(player2);
                ballMagnets[x].checkAttatched(player2, x);
            }
        }  
    }
    public int getGameOutCome(){
        return this.gameOutCome;
    }
   JButton yes = new JButton("YES");
   JButton no = new JButton("NO");


   public void makeYes(JFrame f){
       f.add(yes);
       yes.addActionListener(this);
   }
   public void makeNo(JFrame f){
       f.add(no);
       no.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e){
       if(e.getSource() == yes){
           System.out.println("YES");
           newGame = 1;
       }
       if(e.getSource() == no){
           System.out.println("NO");
            newGame = 2;
   }
 }  
    public int getNewGame(){
        return this.newGame;
    }
    public GameArena getGameFrame(){
        return this.gameFrame;
    }
}