package tests;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Write a description of class Interface here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Interface extends JFrame
{
    private static final long serialVersionUID = -2937100503312197315L;

    private JButton send;
    private JButton register;
    private JButton unregister;
    private JButton filterOn;
    private JLabel filterLabel; 
    private JTextField textFilter;
    private JTextField textServer;
    private JTextField textName;
    private TextArea receivedMessages;
    private JTextField sendMessages;
    private Communication communication;
    private String user;
    private String host;
    private boolean filterActive=false;

    private static final  int     SERVER_PORT = 5555;
    private static final  String  SERVER_HOST = "localhost";
    private static final  String  CRLF        = "\r\n";
    private static final int PORT=1234;

    private Thread receiver;

    public Interface()
    {
        communication=new Communication();
        System.out.print('\u000C');

        addWindowListener (new WindowAdapter() {
                public void windowClosing(WindowEvent w) {
                    quit();
                }
            });

        receiver = new Thread() {
            public void run () { 
                while (true) {
                    try {
                        // Wait to receive a datagram, blocking call
                        communication.waitForMessage ();
                        // datagram was received
                        Message message = communication.getMessage ();
                        // check message type
                        if (message instanceof PostingMessage) {
                            // it is a posting message
                            final PostingMessage p = (PostingMessage) message;
                            // add the message text to the messages textarea
                            Runnable appendText = new Runnable () {
                                    public void run () {
                                        String user = p.getUser ();
                                        String text = p.getText ();
                                        receivedMessages.append (user + ": " + text);
                                        receivedMessages.append (CRLF); 
                                    } 
                                };
                            if(filterActive)
                            {
                                for(String u: textFilter.getText().split(";"))
                                {
                                    if(u.equals(p.getUser()))
                                    {
                                        SwingUtilities.invokeLater (appendText);
                                    }
                                }
                            }
                            else
                            {
                                SwingUtilities.invokeLater (appendText);
                            }
                        } 
                    } catch (Exception e) {
                        // swallow all exceptions
                    } 
                } 
            }   
        };

        
        System.out.println ("Setup Chat Client");
        // open the communication channel
        communication.open();
        // start a background thread to receive messages from the server
        receiver.start ();

        setLayout(new BorderLayout());
        add(getNorth(), BorderLayout.NORTH);
        add(getCenter(), BorderLayout.CENTER);
        add(getSouth(), BorderLayout.SOUTH);

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
        textServer=new JTextField();
        panel2.add(textServer,BorderLayout.CENTER);
        panel.add(panel2);
        JPanel panel3=new JPanel();
        panel3.setLayout(new BorderLayout());
        JLabel label2= new JLabel("Name:");
        panel3.add(label2,BorderLayout.WEST);
        textName= new JTextField();
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
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(createFilterPanel());
        receivedMessages=new TextArea();
        panel.add(receivedMessages);
        return panel;
    }

    private JPanel createFilterPanel()
    {
        JPanel filter=new JPanel();
        filter.setLayout(new BorderLayout());
        filterLabel=new JLabel("Filter:");
        filterLabel.setEnabled(false);
        filter.add(filterLabel, BorderLayout.WEST);
        textFilter=new JTextField();
        textFilter.setEnabled(false);
        filter.add(textFilter, BorderLayout.CENTER);
        filterOn=new JButton("Filter einschalten");
        filterOn.addActionListener(e->filterGedrueckt());
        filter.add(filterOn, BorderLayout.EAST);
        return filter;
    }

    private void filterGedrueckt()
    {
        filterActive=!filterActive;
        textFilter.setEnabled(filterActive);
        filterLabel.setEnabled(filterActive); 
        if(filterActive) {
            filterOn.setText("Filter ausschalten");
        } else {
            filterOn.setText("Filter einschalten");
        }
    }

    public JPanel getSouth()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        sendMessages= new JTextField();
        panel.add(sendMessages,BorderLayout.CENTER);
        send= new JButton("Send");
        send.addActionListener(e->send());
        panel.add(send,BorderLayout.EAST);
        return panel;
    }

    private void register()
    {

        user = textName.getText ();
        host = textServer.getText ();
        System.out.println ("Register " + user);
        communication.sendMessage (host, SERVER_PORT, new RegisterMessage(user));
    }

    private void unregister()
    {
        System.out.println ("Unregister " + user);
        communication.sendMessage (host, SERVER_PORT, new UnregisterMessage(user));
        host=null;
        user= null;
    }

    private void send()
    {
        String text= sendMessages.getText();
        System.out.println(user + ": "+text);
        communication.sendMessage(host, SERVER_PORT, new PostingMessage(user, text));
        //receivedMessages.setText("");
    }

    private void quit () {
        // quit the application
        System.out.println ("Quit Chat Client");
        // closing the communication while the receiver thread is still listening is not correct
        communication.close ();
        System.out.println ("Done.");
        System.exit (0);
    } 
}
