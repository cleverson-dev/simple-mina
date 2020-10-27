package com.segware.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ByteArrayEncoder implements ProtocolEncoder {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		byte[] bytes = (byte[]) message;
		IoBuffer ioBuffer = IoBuffer.allocate(bytes.length, false);
		ioBuffer.put(bytes);
		ioBuffer.flip();
		out.write(ioBuffer);
		System.out.println("Message sent: " + Utils.getHex(bytes, bytes.length));
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

}
