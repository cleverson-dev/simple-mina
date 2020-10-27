package com.segware.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {
	private byte[] recuperaStatusarticoes = { (byte) 0x0A, (byte) 0x00, (byte) 0xE4, (byte) 0x04, (byte) 0x02, (byte) 0x00, (byte) 0x03, 
            (byte) 0x12, (byte) 0x34, (byte) 0xFF, (byte) 0xD4, (byte) 0xE3, (byte) 0x71, (byte) 0x79 };
	
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        byte[] bytes = (byte[]) message;
        System.out.println("Message received: " + Utils.getHex(bytes, bytes.length));
        session.write(recuperaStatusarticoes);
    }
}
