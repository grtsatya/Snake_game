package game;
import javax.swing.*;
public class Game extends JFrame  {

    Game (){
        super("Snake Game");
        add(new Board());
        pack();
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Game();
    }
    
}
