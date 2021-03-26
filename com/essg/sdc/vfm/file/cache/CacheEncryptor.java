package com.essg.sdc.vfm.file.cache;

import java.util.Random;

import com.essg.sdc.io.security.IDecryptor;
import com.essg.sdc.io.security.IEncryptor;

final class CacheEncryptor implements IEncryptor, IDecryptor {
	private final byte[] _key;
	
	CacheEncryptor() {
		Random rnd = new Random(System.currentTimeMillis());
		_key = new byte[256];
		rnd.nextBytes(_key);
	}
	
	public int headderSize() {
		return 0;
	}
	
	public byte[] getHeadder() {
		return null;
	}
	
	public void encrypt(byte[] buf, int pos, int off, int len) {
		int keyofs = pos & 0xff;
		for (int i = off, m = (off + len); i < m; i++, keyofs++) {
			if (keyofs >= _key.length) keyofs = 0;
			buf[i] = (byte)((buf[i] ^ _key[keyofs]) & 0xff);
		}
	}
	
	public int readHeadder(byte[] buf, int off, int len) {
		return 0;
	}
	
	public void decrypt(byte[] buf, int pos, int off, int len) {
		encrypt(buf, pos, off, len);
	}
}