public class Magnet {
    private int x;
    private int y;
    private int layer;
    private Ball areaOfInstance;
    private Ball theMagnet;
    private boolean firstContact = true;
    private boolean attached = false;
    private int playerName;
    Magnet(int x, int y, int layer){
        this.x = x;
        this.y = y;
        this.layer = layer;
        makeBall();
        makeAreaBall();
}

    
    
    public void attractToStriker(Striker player){

        //System.out.println("\n\nX: "+ x + "\nY: " + y);
        //System.out.println("\n\nPX: "+ player.getBase().getXPosition() + "\nPY: " + player.getBase().getYPosition());
        if(theMagnet.getXPosition() > player.getBase().getXPosition()){
            this.move(-10,0);
        }else if(theMagnet.getXPosition() < player.getBase().getXPosition()){
            this.move(10,0); 
        }
        if(theMagnet.getYPosition() > player.getBase().getYPosition()){
            this.move(0,-10);
        }else if(theMagnet.getYPosition() < player.getBase().getYPosition()){
            this.move(0,10);
        }
    }
    public void checkAttatched(Striker player, int ballNum){
        
        if((player.getBase().getXPosition() + 10 > theMagnet.getXPosition()) && (player.getBase().getXPosition() - 10 < theMagnet.getXPosition())){
            if((player.getBase().getYPosition() + 10 > theMagnet.getYPosition()) && (player.getBase().getYPosition() - 10 < theMagnet.getYPosition())){
               attached = true;
               player.setAttachArray(ballNum, true);
               //playerName = player.getPlayer();
               //player.incremmentMA();
            }else{
                player.setAttachArray(ballNum, false);
                attached = false;
               // playerName = 0;
            }
        }
    }
    private void move(int xm, int ym){
        theMagnet.move(xm,ym);
        areaOfInstance.move(xm,ym);
    }
    
    
    public double pushTo0(double num){
        if (num < -2){
            return num + 1;
        }else if (num > 2){
            return num - 1;
        }else
        return 0;
    }
    
    private void makeBall(){
        theMagnet = new Ball(x, y, 10, "WHITE", layer + 10);
    }
    private void makeAreaBall(){
        areaOfInstance = new Ball(x, y, 200, "RED", layer - 10);
    }
    public void addCompToGameArena(GameArena g){
        g.addThing(theMagnet, layer + 10);
        //g.addThing(areaOfInstance, layer - 1);
    }
    public Ball getMagnet(){
        return this.theMagnet;
    }
    public Ball getAreaBall(){
        return this.areaOfInstance;
    }
    public boolean getAttached(){
        return attached;
    }
    public int getPlayerName(){
        return this.playerName;
    }

}
