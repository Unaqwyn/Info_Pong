package tests;

import java.io.*;
import java.net.*;


public class DiagrammTest
{
    private DatagramSocket socket;
    
    public DiagrammTest()
    {
        open();
    }
    
    public DiagrammTest(int port)
    {
        open(port);
    }

    public void open()
    {
        try {
            socket = new DatagramSocket ();
        } catch (Exception e) {
            e.printStackTrace ();
        } 
    }
    
    public void open(int port)
    {
        try {
            socket = new DatagramSocket (port);
        } catch (Exception e) {
            e.printStackTrace ();
        } 
    }
    
    public void send(String destinationsHostName, int destinationsPortNummer){
        String text = "Hoi Du.";
        byte[] inhalt = text.getBytes();
        try {
            InetSocketAddress destinationsAdresse = new InetSocketAddress(destinationsHostName, destinationsPortNummer);
            DatagramPacket packet = new DatagramPacket(inhalt, inhalt.length, destinationsAdresse);
            socket.send(packet);
        } catch (UnknownHostException e) {
            // thrown by InetAddress.getByName()
            System.out.println("Destinations host kann nicht gefunden werden.");
        } catch (IOException e) {
            // thrown by DatagrammSocket.send()
            System.out.println("Datagramm kann nicht gesendet werden.");
        }
    }
    
    public void receive()
    {
        byte[] empfangsPuffer = new byte[2048];
        DatagramPacket receivePacket = null;
        try {
            receivePacket = new DatagramPacket(empfangsPuffer, empfangsPuffer.length);
            socket.receive(receivePacket);  // blocking call
        } catch (IOException e) {
            System.out.println("Problem beim Empfang der Message.");
        }
        String text = new String(empfangsPuffer, 0, receivePacket.getLength());
        System.out.println("Datagram von " + 
            receivePacket.getAddress().getHostName() + "/" +
            receivePacket.getPort() + " mit Inhalt " + text + " erhalten");
    }
    
     public void beenden() {
        if (socket != null){
            socket.close();
        }
    }
}
