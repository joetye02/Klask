public class Striker{
    Ball base;
    Ball head;
    Rectangle neck;

    int x;
    int y;
    int width = 4;
    int height = 10;
    int layer;
    public Striker(int x, int y, int layer){
        this.x = x;
        this.y = y;
        this.layer = layer;
        drawHead();
        drawNeck();
        drawBase();
    }
    private void drawBase(){
        base = new Ball(x + 4, y + 28, 20, "BLACK");
        
    }
    private void drawNeck(){
        neck = new Rectangle(x, y, 8, 24, "BLACK");
    }
    private void drawHead(){
        head = new Ball(x + 4, y, 8, "BLACK");
    }
    public void addCompToGameArena(GameArena g){
        g.addThing(base, layer);
        g.addThing(head, layer);
        g.addThing(neck, layer);
    }
    public void resetCoords(int xChange, int yChange){
        base.move(xChange, yChange);
        neck.move(xChange, yChange);
        head.move(xChange, yChange);
    }
    public void moveUp(){
        
        resetCoords(10, 0);
        //y = y - 10;
    }
    

}