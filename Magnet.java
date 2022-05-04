public class Magnet {
    private int x;
    private int y;
    private int layer;

    private Ball theMagnet;
    Magnet(int x, int y, int layer){
        this.x = x;
        this.y = y;
        this.layer = layer;
        makeBall();

    }
    private void makeBall(){
        theMagnet = new Ball(x, y, 6, "WHITE");
    }
    public void addCompToGameArena(GameArena g){
        g.addThing(theMagnet, layer);
    }
}
