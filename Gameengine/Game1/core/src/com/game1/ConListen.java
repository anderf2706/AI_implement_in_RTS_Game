package com.game1;

import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.listener.ConnectionRequestListener;

public class ConListen implements ConnectionRequestListener{
    @Override
    public void onConnectDone(ConnectEvent event) {
        if(event.getResult() == WarpResponseResultCode.SUCCESS){            
			System.out.println("yipee I have connected");
        }
    }
    @Override
    public void onDisconnectDone(ConnectEvent event) {
        System.out.println("On Disconnected invoked");
    }
	
	@Override
	public void onInitUDPDone(byte arg0) {
		// TODO Auto-generated method stub
		
	}
} 