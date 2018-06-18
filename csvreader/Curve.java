import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Beschreiben Sie hier die Klasse Curve.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Curve
{
    private String name;
    private ArrayList<Point2D.Double> data;
   
    public Curve(String name)
    {
        this.name=name;
        data= new ArrayList<>();
    }
    
    public String getName()
    {
        return name;
    }
    
    public void addPoint(double x, double y)
    {
        data.add(new Point2D.Double(x,y));
    }
    
    public int size()
    {
        return data.size();
    }
    
    public Point2D.Double getPoint(int i)
    {
        return data.get(i);
    }
    
    public void exportCurve(String separator) throws IOException {
       BufferedWriter bw = new BufferedWriter(new FileWriter(new File(name+".csv")));
       for(Point2D.Double point: data) {
           bw.write(point.x + separator + point.y);
           bw.newLine();
       }
       bw.close();       
    }
}
