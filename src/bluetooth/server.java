/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetooth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import uipackage.MainFrame;

/**
 *
 * @author sersanor
 */
public class server extends Thread {

    private static final String MY_UUID = "fa87c0d0afac11de8a390800200c9a66";

    private int mStatus;

    private static final int NONE = 0;
    private static final int LISTENING = 1;
    private static final int SERVING = 2;
    private static final int CHATTING = 3;

    // VARS
    private StreamConnection con;
    private StreamConnectionNotifier service;
    private OutputStream dos;
    private InputStream dis;
    private InputStreamReader daf;
    private BufferedReader sd;
    private RemoteDevice dev;
    
    // UI
    public static MainFrame GUI;

    public server(MainFrame ui) {
        GUI = ui;
        mStatus = NONE;
        try {
            LocalDevice local = LocalDevice.getLocalDevice();
            System.out.println("Serverted:\n"
                    + local.getBluetoothAddress()
                    + "\n" + local.getFriendlyName());

        } catch (Exception e) {

        }
    }

    public void setState(int state) {
        System.out.println("STATUS: " + mStatus + " -> " + state);
        mStatus = state;
    }

    public void run() {
        setName("SERVER THREAD");
        try {
            newServer();
            checkStatus();
            try {
                con.close();
                service.close();
            } catch (IOException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
public void checkStatus() throws InterruptedException{
    dataThread dThread = new dataThread(dis);
    dThread.start();
        try {
            dThread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            setState(SERVING);
            this.sleep(3000);
        }
}
    
    public void newServer() {
        try {
            UUID uuid = new UUID(MY_UUID, false);
            String url = "btspp://localhost:" + uuid + ";name=FMM SERVER";
            service = (StreamConnectionNotifier) Connector.open(url);
            con = (StreamConnection) service.acceptAndOpen();
            dos = con.openOutputStream();
            dis = con.openInputStream();
            setState(LISTENING);
            daf = new InputStreamReader(System.in);
            sd = new BufferedReader(daf);
            dev = RemoteDevice.getRemoteDevice(con);
            System.out.println("Remote device address: " + dev.getBluetoothAddress());
            System.out.println("Remote device name: " + dev.getFriendlyName(true));
            setState(SERVING);
            GUI.setStatus(true);
        } catch (IOException e) {
            System.err.print(e.toString());
            this.interrupt();
        }
       
    }

    public void disconnect() {
        System.out.println("CONNECTION CLOSED");
        this.interrupt();
    }

    private class dataThread extends Thread {

    private InputStream input;

        private dataThread(InputStream dis) {
            setName("DATA THREAD");
            setState(CHATTING);
            input = dis;
        }

        public void run() {
            try {
                dataThread.sleep(5000);
                byte[] buffer = new byte[1024];
                int bytes_read = 0;
                while ((bytes_read=input.read(buffer)) != -1) {
                    String received = new String(buffer, 0, bytes_read);
                    System.out.println("Message: " + received);
                    updateConsole(received);
                }
            } catch (IOException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public void updateConsole(String cad){
            GUI.writeConsole(cad+"\n");
        }

    }
}
