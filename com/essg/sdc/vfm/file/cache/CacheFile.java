package com.essg.sdc.vfm.file.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import com.essg.sdc.io.DecryptInputStream;
import com.essg.sdc.io.EncryptOutputStream;
import com.essg.sdc.io.security.DecryptRandomAccessable;
import com.essg.sdc.io.security.IDecryptor;
import com.essg.sdc.io.security.IEncryptor;
import com.essg.sdc.io.util.StreamUtil;
import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.vfm.file.FolderInterface;
import com.essg.sdc.vfm.file.PhysicalFileInfo;
import com.essg.sdc.vfm.file.WriteableFileInterface;

public class CacheFile extends PhysicalFileInfo implements WriteableFileInterface {
	class CacheEncryptor implements IEncryptor, IDecryptor {
		private final byte[] _key;
		
		CacheEncryptor() {
			Random rnd = new Random(System.currentTimeMillis());
			int len = (rnd.nextInt() & 0x0f);
			len += 32;
			_key = new byte[len];
			rnd.nextBytes(_key);
		}
		
		public int headderSize() {
			return 0;
		}
		
		public byte[] getHeadder() {
			return null;
		}
		
		public void encrypt(byte[] buf, int pos, int off, int len) {
			int keyofs = pos % _key.length;
			for (int i = 0; i < len; i++, off++) {
				buf[off] = (byte)((buf[off] ^ _key[keyofs++]) & 0xff);
				if (keyofs >= _key.length) {
					keyofs = 0;
				}
			}
		}
		
		public int readHeadder(byte[] buf, int off, int len) {
			return 0;
		}
		
		public void decrypt(byte[] buf, int pos, int off, int len) {
			encrypt(buf, pos, off, len);
		}
	}
	
	private final CacheEncryptor _encryptor;

	/**
	 * コンストラクタ
	 * @param folder
	 * @param path
	 * @param file
	 * @throws FileNotFoundException
	 */
	public CacheFile(FolderInterface folder, String path, File file)
			throws FileNotFoundException {
		super(folder, path, file);
		
		_encryptor = new CacheEncryptor();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.PhysicalFileInfo#openStream()
	 */
	@Override
	public
	InputStream openStream() throws IOException {
		return new DecryptInputStream(super.openStream(), _encryptor);
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.PhysicalFileInfo#getBuffer()
	 */
	@Override
	public
	ByteBuffer getBuffer() throws IOException {
		return ByteBuffer.NEW(new DecryptRandomAccessable(super.getBuffer(), _encryptor));
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.WriteableFileInterface#openOutputStream()
	 */
	@Override
	public OutputStream openOutputStream() throws FileNotFoundException {
		try {
			return new EncryptOutputStream(StreamUtil.openOutputStream(_file), _encryptor);
		} catch (IOException e) {
			throw new FileNotFoundException();
		}
	}

}
