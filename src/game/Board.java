package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Board extends JPanel implements ActionListener {
    
    private Image apple;
    private Image dot;
    private Image head;
    private int dots;
    private Timer timer;
    private final int AllDots=1200;
    private final int DotSize=10;
    private final int randomPosition = 30;
    private int applex;
    private int appley;
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    
    private final int x[]= new int [AllDots];
    private final int y[]= new int [AllDots];
    Board(){
        addKeyListener(new TAdapter());
        
        setBackground(Color.BLACK);
        setFocusable(true);
        loadImages();
        initGame();
    }
    public void loadImages(){
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("game/icons/apple.png"));
        apple = I1.getImage();
        ImageIcon I2 = new ImageIcon(ClassLoader.getSystemResource("game/icons/dot.png"));
        dot = I2.getImage();
        ImageIcon I3 = new ImageIcon(ClassLoader.getSystemResource("game/icons/head.png"));
        head = I3.getImage();
    }
    public void initGame(){
        dots = 3;
        
        
        for(int i =0;i<dots;i++){
            y[i]=50;
            x[i]=50-i*DotSize;
            
        }
        
        locateApple();
        timer = new Timer(140,this);
        timer.start();
    }
    public void locateApple(){
        int r = (int)(Math.random()*randomPosition);
        applex = r*DotSize;
        
        r = (int)(Math.random()*randomPosition);
        appley = r* DotSize;
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(inGame){
            g.drawImage(apple, applex, appley, this);
            for (int i =0;i<dots;i++){
                if(i== 0){
                    g.drawImage(head,x[i],y[i],this);
                }
                else{
                    g.drawImage(dot, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }else{ 
            gameOver(g);
        }    
    }
        public void gameOver(Graphics g){
        String msg = "Gam Over! Better Luck\n NEXT TIME ";
        Font font = new Font ("SAN_SERIF",Font.BOLD,18);
        FontMetrics metrices = getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(msg, (400- metrices.stringWidth(msg))/2, 300/2);
    }
    public void move(){
        for (int i= dots;i>0;i--){
            x[i]= x[i - 1];
            y[i]= y[i - 1];
        }
        if(leftDirection){
            x[0] = x[0]-DotSize;
        }if(rightDirection){
            x[0] = x[0]+DotSize;
        }if(upDirection){
            y[0] = y[0]-DotSize;
        }if(downDirection){
            y[0] = y[0]+DotSize;
        }
    }
    public void checkApple(){
        if((x[0] == applex)&&(y[0]== appley)){
            dots++;
            locateApple();
        }
    }
    public void checkCollision(){
        
    for(int i = dots;i>0;i--){
            if((i>4) && (x[0] == x[i]) && (y[0] == y[i])){
                inGame = false;
            }
        }
        if (y [0]>=400){
            inGame = false ;
        }
         if (x [0]>=400){
            inGame = false ;
        }
          if (y [0] < 0){
            inGame = false ;
        }
           if (x [0] < 0){
            inGame = false ;
        }
           if(!inGame){
               timer.stop();
           }
    }
    
    public void actionPerformed(ActionEvent ae){
       if(inGame){
            checkApple();
            checkCollision();
            move();
            repaint();
       }
        
    }
    public class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
          int key = e.getKeyCode();
          if (key == KeyEvent.VK_LEFT && (!rightDirection)){
              leftDirection = true;
              upDirection = false;
              downDirection = false;
          }
          if (key == KeyEvent.VK_RIGHT && (!leftDirection)){
              rightDirection = true;
              upDirection = false;
              downDirection = false;
          }
          if (key == KeyEvent.VK_UP && (!downDirection)){
              upDirection = true;
              leftDirection = false;
              rightDirection = false;
          }
          if (key == KeyEvent.VK_DOWN && (!upDirection)){
              downDirection = true;
              leftDirection = false;
              rightDirection = false;
          }
            
        }
    }
}
