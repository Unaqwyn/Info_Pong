import java.awt.*;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Field extends JPanel
{
    public static Paddle paddle;
    public static Ball ball;
    private Container contentPane;
    public static int[] sizeField;
    
    public Field()
    {
        setBackground(Color.yellow);
        setBounds(0,0,400,400);   
        setLayout(null);
        ball=new Ball();
        paddle=new Paddle(100);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                if(Pong.init)
                {
                    setPaddlePos(e.getX());
                }
            }
        });
        this.add(ball);
        this.add(paddle);
    }
    
    public static int[] getPaddleSize()
    {
        return paddle.getPaddleSize();
    }
    
    public void setBallPos(int x, int y)
    {
        ball.setLocation(x,y);
    }
    
    public void setPaddlePos(int x)
    {
        if(x<=this.getWidth()-paddle.getWidth())
        {
            paddle.setLocation(x, this.getHeight()-paddle.getHeight()-30);
        }
        else
        {
            paddle.setLocation(this.getWidth()-paddle.getWidth(), this.getHeight()-paddle.getHeight()-30);
        }
    }
    
    public Ball getBall()
    {
        return ball;
    }
    
    public static Paddle getPaddle()
    {
        return paddle;
    }
    
    public void move()
    {
        int[] r=ball.newPos();
        setBallPos(r[0], r[1]);
    }
}

