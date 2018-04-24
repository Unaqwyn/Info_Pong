import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.management.*;
import java.util.Timer;
import java.util.TimerTask;

public class Pong
{
    public Field field;
    private JFrame fenster;
    private Container contentPane;
    private Random random;
    private Ball ball;
    private Paddle paddle;
    private static Timer timer;
    private TimerTask task;
    private JButton start;
    public static boolean init;
    
    public Pong()
    {
        System.out.print('\u000C');
        timer=new Timer();
        random=new Random();
        fenster=new JFrame("Pong");
        contentPane=fenster.getContentPane();
        contentPane.setLayout(new BorderLayout());
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(2,1));
        JButton init=new JButton("Initialize");
        init.addActionListener(e->init());
        init.setPreferredSize(new Dimension(400,20));
        panel.add(init);
        start=new JButton("Start");
        start.addActionListener(e->play());
        start.setPreferredSize(new Dimension(400,20));
        start.setEnabled(false);
        panel.add(start);
        contentPane.add(panel, BorderLayout.NORTH);
        field=new Field();
        field.setPreferredSize(new Dimension(400,400));
        contentPane.add(field, BorderLayout.CENTER);
        
        paddle=field.getPaddle();
        ball=field.getBall();
        
        init();
        fenster.pack();
        fenster.setVisible(true);
        
    }

    public void init()
    {
        setBallPos(field.getWidth()/2-ball.getWidth()/2,0);
        setPaddlePos(field.getWidth()/2-paddle.getWidth()/2);
        ball.getStartPos();
        ball.setStartSpeed();
        start.setEnabled(true);
        init=true;
    }
    
    public void setBallPos(int x, int y)
    {
        field.setBallPos(x,y);
    }
    
    public void setPaddlePos(int x)
    {
        field.setPaddlePos(x);
    }
    
    public void move()
    {
        field.move();
    }
    
    public void play()
    {
        task=new TimerTask() {
            @Override
            public void run() {
                if(ball.getSpeedY()!=0) {
                    move();
                } else {
                    cancel();
                }
            }
        };
        
        timer.schedule(task, 0, 50);
    }
}
