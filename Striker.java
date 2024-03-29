public class Striker{
    Ball base;
    Ball head;
    Rectangle neck;

    private int x;
    private int y;
    private int width = 4;
    private int height = 10;
    private int layer;
    private int player; // 1 = p1, 2 = p2
    private double xSpeed;
    private double ySpeed;

    private boolean[] attachedMagnets = new boolean[3];
   

    public Striker(int x, int y, int layer, int player){
        this.x = x;
        this.y = y;
        this.layer = layer;
        this.player = player;

        //this.xSpeed = xSpeed;
        //this.ySpeed = ySpeed;

        resetAttachArray();
        drawHead();
        drawNeck();
        drawBase();
    }
    private void drawBase(){//called by contructor to build the bottom of the striker
        base = new Ball(x + 4, y + 40, 40, "BLACK");
        
    }
    private void drawNeck(){//called by contructor to build the neck of the striker
        neck = new Rectangle(x - 4, y - 16, 16, 40, "BLACK");
    }
    private void drawHead(){//clalled by contructor to build the head of the striker
        head = new Ball(x + 4, y - 20, 20, "BLACK");
    }
    public void checkMovement(GameArena g){

            if (g.upPressed() == true && this.player == 2){
                this.moveUp(g);
            }
            if (g.rightPressed() == true && this.player == 2){
                this.moveRight(g);
            }
            if (g.leftPressed() == true && this.player == 2){
                this.moveLeft(g);
            }
            if (g.downPressed() == true && this.player == 2){
                this.moveDown(g);
            }
            if (g.letterPressed('W') == true && this.player == 1){
                this.moveUp(g);
            }
            if (g.letterPressed('A') == true && this.player == 1){
                this.moveLeft(g);
            }
            if (g.letterPressed('S') == true && this.player == 1){
                this.moveDown(g);
            }
            if (g.letterPressed('D') == true && this.player == 1){
                this.moveRight(g);
            }
        
    }
    public boolean lost(Magnet[] ballMagnets){
        
        int counter1 = 0;
 
        for (int x=0;x<3;x++){
            if (attachedMagnets[x] == true){
                
                counter1 += 1;
            }
            //System.out.println(x + ":   " + attachedMagnets[x]);
            //System.out.println("MA1: " + counter1);
            
            
        }
        if (counter1 > 1){
            return true;
        }else{
            return false;
        }
    }
    public void resetPos(Board gameBoard){
        int newXpos = 0;
        if(player == 1){newXpos = 400;}else{newXpos = 1200;}
        base.setXPosition(x + 4);
        neck.setXPosition(x - 4);
        head.setXPosition(x + 4);
        base.setYPosition(y + 40);
        neck.setYPosition( y - 16);
        head.setYPosition(y - 20);
    }
    
    public void checkWithinBoard(int XY, int endBoardX, int endBoardY, GameArena g, int halfWayX){
        if (this.base.getXPosition() < XY){
            moveRight(g);
        }
        if (this.base.getYPosition() < XY){
            moveDown(g);
        }
        if (this.base.getXPosition() > endBoardX){
            moveLeft(g);
        }
        if (this.base.getYPosition() > endBoardY){
            moveUp(g);
        }
        if(this.player == 1 && this.base.getXPosition() >= halfWayX){
            moveLeft(g);
        }else if(this.player == 2 && this.base.getXPosition() <= halfWayX){
            moveRight(g);
        }

    }
    
    

    public void addCompToGameArena(GameArena g){
        g.addThing(base, layer);
        g.addThing(head, layer);
        g.addThing(neck, layer);
    }
    public void resetCoords(GameArena g){
        base.move(xSpeed, ySpeed);
        neck.move(xSpeed, ySpeed);
        head.move(xSpeed, ySpeed);
 
    }
    public void moveUp(GameArena g){ 
        ySpeed= -10;
        resetCoords(g);
       
        
    }
    public void moveLeft(GameArena g){  
        xSpeed = -10;
        resetCoords(g);
      

    }
    public void moveRight(GameArena g){  
        xSpeed = 10;
        resetCoords(g);
       
    }
    public void moveDown(GameArena g){ 
        ySpeed= 10; 
        resetCoords(g);
        
    }
    private void resetAttachArray(){
        for (int i = 0; i < 3; i++){
            attachedMagnets[i] = false;
        }
    }
    
    public double getXSpeed(){
        return this.xSpeed;
    }
    public double getYSpeed(){
        return this.ySpeed;
    }
    public void setXSpeed(double speed){
       this.xSpeed = speed;
    }
    public void setYSpeed(double speed){
        this.ySpeed = speed;
    }
    public void resetSpeeds(){
        this.xSpeed = 0;
        this.ySpeed = 0;
    }
    public Ball getBase(){
        return this.base;
    }
    public int getPlayer(){
        return this.player;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setAttachArray(int index, boolean b){
        attachedMagnets[index] = b;
    }
    
    

}