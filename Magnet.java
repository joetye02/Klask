public class Magnet {
    private int x;
    private int y;
    private int layer;
    private Ball areaOfInstance;
    private Ball theMagnet;
    Magnet(int x, int y, int layer){
        this.x = x;
        this.y = y;
        this.layer = layer;
        makeBall();
        makeAreaBall();

    }
    private void makeBall(){
        theMagnet = new Ball(x, y, 6, "WHITE", layer);
    }
    private void makeAreaBall(){
        areaOfInstance = new Ball(x, y, 50, "RED", layer - 1);
    }
    public void addCompToGameArena(GameArena g){
        g.addThing(theMagnet, layer);
        g.addThing(areaOfInstance, layer - 1);
    }
}
