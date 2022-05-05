public class Striker{
    Ball base;
    Ball head;
    Rectangle neck;

    int x;
    int y;
    int width = 4;
    int height = 10;
    int layer;
    int player; // 1 = p1, 2 = p2
    public Striker(int x, int y, int layer, int player){
        this.x = x;
        this.y = y;
        this.layer = layer;
        this.player = player;
        drawHead();
        drawNeck();
        drawBase();
    }
    private void drawBase(){//called by contructor to build the bottom of the striker
        base = new Ball(x + 4, y + 28, 20, "BLACK");
        
    }
    private void drawNeck(){//called by contructor to build the neck of the striker
        neck = new Rectangle(x, y, 8, 24, "BLACK");
    }
    private void drawHead(){//clalled by contructor to build the head of the striker
        head = new Ball(x + 4, y, 8, "BLACK");
    }
    public void checkMovement(GameArena g){

        
            if (g.upPressed() == true && this.player == 2){
                this.moveUp(g);
            }else if (g.rightPressed() == true && this.player == 2){
                this.moveRight(g);
            }else if (g.leftPressed() == true && this.player == 2){
                this.moveLeft(g);
            }else if (g.downPressed() == true && this.player == 2){
                this.moveDown(g);
            }else if (g.letterPressed('W') == true && this.player == 1){
                this.moveUp(g);
            }else if (g.letterPressed('A') == true && this.player == 1){
                this.moveLeft(g);
            }else if (g.letterPressed('S') == true && this.player == 1){
                this.moveDown(g);
            }else if (g.letterPressed('D') == true && this.player == 1){
                this.moveRight(g);
            }
        
    }

    public void addCompToGameArena(GameArena g){
        g.addThing(base, layer);
        g.addThing(head, layer);
        g.addThing(neck, layer);
    }
    public void resetCoords(int xChange, int yChange, GameArena g){
        base.move(xChange, yChange);
        neck.move(xChange, yChange);
        head.move(xChange, yChange);
        addCompToGameArena(g);
    }
    public void moveUp(GameArena g){   
        resetCoords(0, -4, g);
    }
    public void moveLeft(GameArena g){   
        resetCoords(-4, 0, g);
    }
    public void moveRight(GameArena g){   
        resetCoords(4, 0, g);
    }
    public void moveDown(GameArena g){   
        resetCoords(0, 4, g);
    }
    

}