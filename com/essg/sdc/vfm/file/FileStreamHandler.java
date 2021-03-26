package com.essg.sdc.vfm.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import com.essg.sdc.io.util.StreamUtil;

public class FileStreamHandler extends URLStreamHandler {
	
	final FileInterface _file;

	class FileURLConnection extends URLConnection {
		/**
		 * @param url
		 */
		protected FileURLConnection(URL url) {
			super(url);
		}

		@Override
		public void connect() throws IOException {
		}

		/* (non-Javadoc)
		 * @see java.net.URLConnection#getInputStream()
		 */
		@Override
		public InputStream getInputStream() throws IOException {
			InputStream is = _file.openStream();
			if (is != null && !is.markSupported()) {
				is = StreamUtil.wrapBufferedInputStream(is);
			}
			return is;
		}
	}
	
	/**
	 * コンストラクタ
	 * @param file
	 */
	public FileStreamHandler(FileInterface file) {
		_file = file;
	}
	
	/* (non-Javadoc)
	 * @see java.net.URLStreamHandler#openConnection(java.net.URL)
	 */
	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		return new FileURLConnection(u);
	}

}
