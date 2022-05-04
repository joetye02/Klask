public class Goal{
    Ball goalHole;
    int x;
    int y;
    int layer;
    public Goal(int x, int y, int layer){
        this.x = x;
        this.y = y;
        this.layer = layer;
        makeGoal();
    }
    private void makeGoal(){
        goalHole = new Ball(x, y, 45 , "GREY", layer);
    }
    public void addCompToGameArena(GameArena g){
        g.addThing(goalHole, layer);
    }
}