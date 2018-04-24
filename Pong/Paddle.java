import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.*;

public class Paddle extends JPanel
{
	public Paddle(int width)
	{
		setBackground(Color.green);
		setBounds(0,0,width,20);
		setPreferredSize(new Dimension(width, 20));
	}
	
	public int[] getPaddleSize()
	{
	   int y=(int) this.getLocation().getY();
	   int x1=(int) this.getLocation().getX();
	   int x2=x1+this.getWidth();
	   int[] r={y,x1,x2};
	   return r;
	}
}
