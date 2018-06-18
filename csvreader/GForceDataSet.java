import java.util.ArrayList;
import java.io.IOException;
/**
 * Beschreiben Sie hier die Klasse GForceDataSet.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class GForceDataSet
{
    private static final int TIME = 0;
    private static final int ACCELERATION_X=1;
    private static final int ACCELERATION_Y=2;
    private static final int ACCELERATION_Z=3;
    private static final int ACCELERATION_ABSOLUTE=4;
    
    private ArrayList<Curve> curves;
    
    public GForceDataSet()
    {
        curves= new ArrayList<>();
    }
    
    public void importData(String filename,String seperator) throws Exception    {
        CSVReader reader=new CSVReader(filename, seperator);
        Curve xCurve= new Curve("x-force");
        Curve yCurve= new Curve("y-force");
        Curve zCurve= new Curve("z-force");
        String[] data;
        
        data= reader.getNextData();
        
        data= reader.getNextData();
        while(null!=data)
        {
            double time = Double.parseDouble(data[TIME]);
            double gForceX = Double.parseDouble(data[ACCELERATION_X]);
            double gForceY = Double.parseDouble(data[ACCELERATION_Y]);
            double gForceZ = Double.parseDouble(data[ACCELERATION_Z]);
            xCurve.addPoint(time, gForceX);
            yCurve.addPoint(time, gForceY);
            zCurve.addPoint(time, gForceZ);
            
            data= reader.getNextData();
            
        }
        curves.add(xCurve);
        curves.add(yCurve);
        curves.add(zCurve);
    }
    
    public void exportData(String separator) throws IOException 
    {
        for(Curve curve: curves) {
            curve.exportCurve(separator);
        }
    }
}
