public class Magnet {
    private int x;
    private int y;
    private int layer;
    private Ball areaOfInstance;
    private Ball theMagnet;
    private double xSpeed, ySpeed;
    private boolean attracted = false;
    private int attractedPlayer; //p1 = 1/p2 = 2
    private double xDiff, yDiff;
    Magnet(int x, int y, int layer){
        this.x = x;
        this.y = y;
        this.layer = layer;
        makeBall();
        makeAreaBall();

    }
    public void attractToStriker(Striker player){
        attractedPlayer = player.getPlayer();
        attracted = true;
        xSpeed = player.getXSpeed();
        ySpeed = player.getYSpeed();
        xDiff = x - player.getX();
        yDiff = y - player.getY();
        

        this.xDiff = pushTo0(xDiff);
        this.yDiff = pushTo0(yDiff);
    
        this.xSpeed += (xDiff/xDiff);
        this.ySpeed += (yDiff/yDiff);
    }
    public double pushTo0(double num){
        if (num < -2){
            return num + 1;
        }else if (num > 2){
            return num - 1;
        }else
        return 0;
    }
    public void updateMagnet(Striker player1, Striker player2){
        
        if (attracted == true){
            if(attractedPlayer == 1){
                xSpeed = player1.getXSpeed();
                ySpeed = player1.getYSpeed();
            }else if(attractedPlayer == 2){
                xSpeed = player2.getXSpeed();
                ySpeed = player2.getYSpeed();
            }
            
        }
        theMagnet.move(xSpeed, ySpeed);
        
    }
    private void makeBall(){
        theMagnet = new Ball(x, y, 18, "WHITE", layer + 10);
    }
    private void makeAreaBall(){
        areaOfInstance = new Ball(x, y, 130, "RED", layer - 1);
    }
    public void addCompToGameArena(GameArena g){
        g.addThing(theMagnet, layer + 10);
        g.addThing(areaOfInstance, layer - 1);
    }
    public Ball getMagnet(){
        return this.theMagnet;
    }
}
