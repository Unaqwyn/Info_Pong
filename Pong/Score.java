import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.util.Random;
/**
 * Beschreiben Sie hier die Klasse Score.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Score extends JLabel
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int score;

    /**
     * Konstruktor für Objekte der Klasse Score
     */
    public Score()
    {
        setBackground(Color.red);
        setBounds(0,0,300,60);
        setPreferredSize(new Dimension(300,60));
        setText(toString());
    }
    
    public void update()
    {
        score++;
        setText(toString());
    }
    
    public String toString()
    {
        return "score: " + score;
    }

    
}
