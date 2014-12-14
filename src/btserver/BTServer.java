package btserver;

import javax.bluetooth.*;
import javax.bluetooth.UUID;
import uipackage.MainFrame;
import bluetooth.server;
import java.util.logging.Handler;
import bluetooth.server;
public class BTServer{
         
    private static server myServer = null;
    public static MainFrame GUI;
	
    public static void startserver() {
        // HANDLER
           myServer = new server(GUI);
           myServer.start();
    }
    public static void checkStatus() throws InterruptedException{
        while(myServer.isAlive()){
            myServer.join();
        }
        myServer = null;
        GUI.setStatus(false);
        startserver();
        GUI.clearConsole();
    }

    /**
     *
     * @param args
     * @throws BluetoothStateException
     */
    public static void main( String args[] ) throws BluetoothStateException{
        // HANDLER
        
        
        try {
	LocalDevice local = LocalDevice.getLocalDevice();
        // LOCAL INFO
        MainFrame ui = new MainFrame();
        GUI = ui;
        ui.setVisible(true);
        ui.setServerAdd(local.getBluetoothAddress());
        ui.setServerName(local.getFriendlyName());
        
        startserver();
        while(true)
        checkStatus();
        }
      catch (Exception e) {System.err.print(e.toString());}
    }
}  //main