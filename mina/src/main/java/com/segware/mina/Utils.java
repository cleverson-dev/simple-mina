package com.segware.mina;

public class Utils {
	public static String getHex(byte[] data, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			String hexStr = Integer.toHexString(((int) data[i]) & 0xFF);
			if (hexStr.length() < 2) {
				sb.append("0").append(hexStr.toUpperCase());
			} else {
				sb.append(hexStr.toUpperCase());
			}
		}
		return sb.toString();
	}
	
	public static byte[] hexToByteArray(String hex) {
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        final byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = Integer.valueOf(hex.substring(i * 2, i * 2 + 2), 16).byteValue();
        }
        return bytes;
    }
}
