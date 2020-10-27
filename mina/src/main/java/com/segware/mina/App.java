package com.segware.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class App 
{
	
    private static int TIMEOUT = 3000;
    private static String HOSTNAME = "192.168.7.203";
    private static int PORT = 6667;
	
    public static void main( String[] args ) throws Throwable
    {
    	NioSocketConnector connector = new NioSocketConnector();
    	
    	configureConnector(connector);
    	
    	IoSession session = connect(connector);
        if (session != null) {
            sendCommands(session);
        }
        //close(connector, session);
    }
    
    public static void configureConnector(final NioSocketConnector connector) {
        connector.setConnectTimeoutMillis(TIMEOUT);

        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ByteArrayCodecFactory()));
        connector.getFilterChain().addLast("logger", new LoggingFilter());

        connector.setHandler(new ClientHandler());
    }
    
    private static IoSession connect(final NioSocketConnector connector) throws InterruptedException {
        IoSession session = null;
        try {
            ConnectFuture future = connector.connect(new
            InetSocketAddress(HOSTNAME, PORT));
            future.awaitUninterruptibly();
            if (!future.isConnected()) {
            	System.err.println("Failed to connect.");
            }
            session = future.getSession();
        } catch (RuntimeIoException e) {
            System.err.println("Failed to connect.");
            e.printStackTrace();
        }
        return session;
    }
    
    private static void sendCommands(final IoSession session) {
    	byte[] recuperaStatusarticoes = { (byte) 0x0A, (byte) 0x00, (byte) 0xE4, (byte) 0x04, (byte) 0x02, (byte) 0x00, (byte) 0x03, 
    			                          (byte) 0x12, (byte) 0x34, (byte) 0xFF, (byte) 0xD4, (byte) 0xE3, (byte) 0x71, (byte) 0x79 };
    	
    	byte[] info = { (byte) 0x0A, (byte) 0x00, (byte) 0xE4, (byte) 0x04,
    			        (byte) 0x00, (byte) 0x01/*,
    			        (byte) 0x??, (byte) 0x??,
    			        (byte) 0x??, (byte) 0x??*/};
    	
    	byte[] test = Utils.hexToByteArray("FAFF800288E4B4ACEAAFCB67D9BA0B1F1A07A8128153E50F873A");
    	
    	session.write(test);
    }
    
    private static void close(final NioSocketConnector connector,
    final IoSession session) {
        if (session != null) {
            if (session.isConnected()) {
                session.close(false);
                session.getCloseFuture().awaitUninterruptibly();
            }
        }
        connector.dispose();
    }
}
