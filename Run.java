import javax.swing.*;
import java.awt.*;
class Run{

    public static void main(String[] args){
        Game game1;
        do{
        
        game1 = new Game(0);
        
        JFrame playAgainFrame = new JFrame("");
        playAgainFrame.setVisible(true);
        playAgainFrame.setSize(400,100);
        playAgainFrame.setResizable(true);
        playAgainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playAgainFrame.setLocation(800,400);
        playAgainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

        
        JLabel question = new JLabel("Would you like to play again");
        
        game1.makeNo(playAgainFrame);
        playAgainFrame.add(question);
        game1.makeYes(playAgainFrame);

        while(game1.getNewGame() == 0){
            //System.out.println("0");
        }
        //System.out.println("1 or 2");
        //
        }while(game1.getNewGame() != 2);
       // System.out.println("QUIT");
        game1.getGameFrame().exit();
        
        
        
        
    }
    
}