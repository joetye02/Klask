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
    /*
    *checks if the players striker has entered the players goal
    *
    *@param gameBall the gameBall
    *@param score the displayed score 
    *
    */
    public boolean checkGoal(Ball gameBall, Text score){
        if(gameBall.collides(this.goalHole) == true){
            incrementGC(score);
            return true;
        }
            return false;
    }
    public void incrementGC(Text score){//increment goals conceeded by this player
        goalsconceeded++;
        score.setText(String.valueOf(goalsconceeded));
    }
    public void checkStrikerGoal(Striker player, Text score, Board gameBoard){
        if(player.getBase().collides(this.goalHole) == true){
            incrementGC(score);
            player.resetPos(gameBoard);
        }
    }
}