package serverapp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;

public class ServerThread extends Thread{
    
    ServerForm sf;
    ObjectInputStream oin;
    ObjectOutputStream out;
    ServerSocket serverSocket;
    Socket socket;
    Vector v;
    storage st;
    public ServerThread(ServerForm sf, int port){
        
        this.sf = sf;
        try{
            serverSocket = new ServerSocket(port);
            JOptionPane.showMessageDialog(sf, "Server Started");
            start();
        }
        catch(Exception e){
            
        }
        v = new Vector();
    }      
    
    //creating a method to send message
    public void sendMessage(String msg){
        try{
            out.writeObject(msg.toString());
            sf.jtaRec.append("Me: "+msg+"\n");
            st.setSource("Me");
            st.setMessage(msg);
            v.add(st);
            sf.jtaSend.setText("");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
        public void run(){
        
            while(true){
        
            try{
                //accepting incoming connections to the server using thread
                socket = serverSocket.accept();
                //calling method to create input output objects
                openReader();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    private void openReader() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try{
          oin = new ObjectInputStream(socket.getInputStream());
          out = new ObjectOutputStream(socket.getOutputStream());
          MsgRecThread mrt = new MsgRecThread(sf, oin, out);
          
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public class MsgRecThread extends Thread{
        ServerForm sf;
        ObjectInputStream oin; 
        ObjectOutputStream out;
        public MsgRecThread(ServerForm sf, ObjectInputStream oin, ObjectOutputStream out){
            this.sf = sf;
            this.oin = oin;
            this.out = out;
            start();
        }
        
        public void run(){
            while(true){
                try{
                    String clmsg = oin.readObject().toString();
                    sf.jtaRec.append("Client: "+clmsg+"\n");
                    st.setSource("Client");
                    st.setMessage(clmsg);
                    v.add(st);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}

