package tests;

import java.awt.*;
import javax.swing.*;


/**
 * Write a description of class Interface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Interface extends JFrame
{
    private JButton send;
    private JButton register;
    private JButton unregister;
    private TextField textServer;
    private TextField textName;
    private TextArea receivedMessages;
    private TextField sendMessages;
    private Communication communication;
    private String user;
    private String host;
    
    
    public Interface(Communication communication)
    {
        setLayout(new BorderLayout());
        add(getNorth(), BorderLayout.NORTH);
        add(getCenter(), BorderLayout.CENTER);
        add(getSouth(), BorderLayout.SOUTH);
        this.communication=communication;
        
        pack();
        setVisible(true);
    }
    
    public Interface()
    {
        setLayout(new BorderLayout());
        add(getNorth(), BorderLayout.NORTH);
        add(getCenter(), BorderLayout.CENTER);
        add(getSouth(), BorderLayout.SOUTH);
        this.communication=null;
        
        pack();
        setVisible(true);
    }
    
    public JPanel getNorth()
    {
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(2,1));
        JPanel panel2= new JPanel();
        panel2.setLayout(new BorderLayout());
        JLabel label= new JLabel("Server:");
        panel2.add(label,BorderLayout.WEST);
        textServer=new TextField();
        panel2.add(textServer,BorderLayout.CENTER);
        panel.add(panel2);
        JPanel panel3=new JPanel();
        panel3.setLayout(new BorderLayout());
        JLabel label2= new JLabel("Name:");
        panel3.add(label2,BorderLayout.WEST);
        textName= new TextField();
        panel3.add(textName,BorderLayout.CENTER);
        JPanel panel4= new JPanel();
        panel4.setLayout(new FlowLayout());
        register= new JButton("Register");
        register.addActionListener(e->register());
        unregister= new JButton("Unregister");
        unregister.addActionListener(e->unregister());
        panel4.add(register);
        panel4.add(unregister);
        panel3.add(panel4,BorderLayout.EAST);
        panel.add(panel3);
        return panel;
    }
    
    public JPanel getCenter()
    {
        JPanel panel=new JPanel();
        receivedMessages=new TextArea();
        panel.add(receivedMessages);
        return panel;
    }
    
    public JPanel getSouth()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        sendMessages= new TextField();
        panel.add(sendMessages,BorderLayout.CENTER);
        send= new JButton("Send");
        panel.add(send,BorderLayout.EAST);
        return panel;
    }
    
    private void register()
    {
        host=textServer.getText();
        user=textName.getText();
    }
    
    private void unregister()
    {
        host=null;
        user= null;
    }
}
