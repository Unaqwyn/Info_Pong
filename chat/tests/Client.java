package tests;


import java.io.*;
import java.net.*;
import java.util.Map;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Client
{
    private Communication communication;
    private Map<String, SocketAddress> list;
    private Thread receiver;
    
    public Client()
    {
        communication=new Communication();
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    System.out.println ("Closing communication ...");
                    if (communication != null) {
                        communication.close ();
                    }
                    System.out.println ("Communication closed.");
                }
        });
        
        
        receiver = new Thread() {
            public void run () { 
                while (true) {
                    getMessage();
                } 
            }   
        };
        
        
        
    }
    
    private void addClient(Message m)
    {
        String user=m.getUser();
        SocketAddress address=communication.getSocketAddress();
        boolean exists=false;
        for(Map.Entry<String, SocketAddress> entry : list.entrySet())
        {
            String userComp=entry.getKey();
            SocketAddress addressComp=entry.getValue();
            if( user.equals(userComp)&& address.equals(addressComp))
            {
                exists=true;
            }
        }
        if(!exists) list.put(user,address);
    }
    
    private void removeClient(Message m)
    {
        String user=m.getUser();
        SocketAddress address=communication.getSocketAddress();
        
        for(Map.Entry<String, SocketAddress> entry : list.entrySet())
        {
            if(entry.getKey().equals(user) && entry.getValue().equals(address))
            {
                list.remove(entry);
            }
        }
    }
    
    private void getMessage()
    {
        communication.waitForMessage();
        Message receivedMessage=communication.getMessage();
        if(receivedMessage instanceof RegisterMessage)
        {
            addClient(receivedMessage);
        }
        else if(receivedMessage instanceof UnregisterMessage)
        {
            removeClient(receivedMessage);
        }
        for(Map.Entry<String, SocketAddress> entry : list.entrySet())
        {
            SocketAddress address=entry.getValue();
            communication.sendMessage(address, receivedMessage);
        }
    }
}
