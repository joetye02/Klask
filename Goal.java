public class Goal {
    private Ball goalHole;
    private int x;
    private int y;
    private int layer;
    private int goalsconceeded = 0;

    public Goal(int x, int y, int layer){
        this.x = x;
        this.y = y;
        this.layer = layer;
        makeGoal();
    }
    private void makeGoal(){
        goalHole = new Ball(x, y, 90 , "GREY", layer);
    }
    public void addCompToGameArena(GameArena g){
        g.addThing(goalHole, layer);
    }
    public int getGoalsconceeded(){
        return this.goalsconceeded;
    }
    public boolean checkGoal(Ball gameBall, Text score){
        if(gameBall.collides(this.goalHole) == true){
            goalsconceeded++;
            score.setText(String.valueOf(goalsconceeded));
            return true;
        }
            return false;
    }
}