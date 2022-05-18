public class Board{

    Rectangle gameBoard;
    Rectangle gameBorder;

    final static int WIDTH = 800 * 2;//default = 610
    final static int HEIGHT = 400 * 2;//default = 320
    final static int XY = 165;

    Line halfWay;

    Board(){
        
        addBorder();
        addgameBoard();
        addHalfWayLine();
    }

    private void addBorder(){
        gameBorder = new Rectangle(150, 150, (WIDTH + 30), (HEIGHT + 30), "GREY");
    }
    private void addgameBoard(){
        gameBoard = new Rectangle(165, 165, WIDTH, HEIGHT , "BLUE");
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
        return WIDTH;
    }
    public int getHeight(){
        return HEIGHT;
    }
    public int getXY(){
        return XY;
    }
    public int getHalfWayX(){
        return (WIDTH / 2) + XY;
    }
    public int getHalfWayY(){
        return (HEIGHT / 2) + XY;
    }
    public int getEndOfBoardX(){
        return XY + WIDTH;
    }
    public int getEndOfBoardY(){
        return XY + HEIGHT;
    }
    
    
}