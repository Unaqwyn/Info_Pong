import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.util.Random;

public class Ball extends JPanel
{
    public int vx=0;
    public int vy=0;
    public int x;
    public int y;
    private Random random;
    
    public Ball()
    {
        setBackground(Color.black);
        setBounds(200,0,20,20);
        setPreferredSize(new Dimension(20,20));
        random=new Random();
    }

    public void getStartPos()
    {
        x=(int) this.getLocation().getX();
        y=(int) this.getLocation().getY();
    }
    
    public int getSpeedY()
    {
       return vy;
    }
    
    public int[] newPos()
    {
        if(vy!=0)
            {
                x+=vx;
                if(x<=0)
                {
                    x*=(-1);
                    vx*=(-1);
                    vx+=(random.nextInt(3)-1);
                    if(vx==0) vx++;
                }
                if(x>=380)
                {
                    x=760-x;
                    vx*=(-1);
                    vx+=(random.nextInt(3)-1);
                    if(vx==0) vx--;
                }
                y+=vy;
                if(y<=0)
                {
                    y*=(-1);
                    vy*=(-1);
                }
                if((y>=Field.getPaddleSize()[0]-this.getHeight()&& y-vy<=Field.getPaddleSize()[0]-this.getHeight())&&vy>0)
                {
                    if(x+(this.getWidth()/2)>=Field.getPaddleSize()[1] && (x+(this.getWidth()/2))<= Field.getPaddleSize()[2])
                    {
                        int k=(int) Field.getPaddle().getLocation().getY()*2;
                        y=k-(y+2*this.getHeight());
                        vy*=-1;
                        --vy;
                         vx+=(random.nextInt(3)-1);
                         while(vx==0)
                         {
                             vx=vy*(random.nextInt(3)-1)/10;
                         }
                    }
                }
                if(y>=380)
                {
                    y=380;
                    vy=0;
                    System.out.println("Lost");
                }
            }    
        int[] r={x,y};
        return r;
    }
    
    public void setStartSpeed()
    {
       Random random=new Random();
       while(vx==0)
       {
           vx=random.nextInt(21);
           vx-=10;
       }
       vy=5; 
       
    }
}
