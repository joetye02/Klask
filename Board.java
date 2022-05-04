public class Board{

    Rectangle gameBoard;
    Rectangle gameBorder;

    Line halfWay;

    Board(){
        
        addBorder();
        addgameBoard();
        addHalfWayLine();
    }

    private void addBorder(){
        gameBorder = new Rectangle(150, 150, 640, 350, "GREY");
    }
    private void addgameBoard(){
        gameBoard = new Rectangle(165, 165, 610, 320, "BLUE");
    }
    private void addHalfWayLine(){
        halfWay = new Line((gameBoard.getWidth() / 2) + gameBoard.getXPosition(),//X coordinate for the Centre of the game board.
                            gameBoard.getYPosition(),// y coordinate for the top of the game board.
                            gameBoard.getWidth() / 2 + gameBoard.getXPosition(),//x coordingate for the Centre of the game board.
                            gameBoard.getHeight() + gameBoard.getYPosition(),// y coordinate for the bottom of the game board.
                            1, "GREY", 5);
    }
    public void addCompToGameArena(GameArena g){
        g.addThing(gameBorder, 6);
        g.addThing(gameBoard, 5);
        g.addThing(halfWay, 5);
    }
    public int getWidth(){
        return 610;
    }
    public int getHeight(){
        return 320;
    }
    public int getXY(){
        return 165;
    }
    public int getHalfWayX(){
        return (getWidth() / 2) + getXY();
    }
    public int getHalfWayY(){
        return (getHeight() / 2) + getXY();
    }
    public int getEndOfBoardX(){
        return getXY() + getWidth();
    }
    
    
}