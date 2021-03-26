package com.essg.sdc.vfm.io;

import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.nio.PagedByteBuffer;
import com.essg.sdc.vfm.file.FileResource;

public class FileByteBuffer extends PagedByteBuffer {
	static class Manager extends PagedByteBuffer.StreamPageManager {
		private FileResource _file;
		private int _size = -1;

		/**
		 * コンストラクタ
		 * @param file
		 * @param count
		 */
		Manager(FileResource file, int count) {
			super(count);
			_file = file;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.nio.PagedByteBuffer.StreamPageManager#openStream()
		 */
		@Override
		protected InputStream openStream() throws IOException {
			return _file.openStream();
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.nio.PagedByteBuffer.PageManager#size()
		 */
		@Override
		protected int size() {
			if (_size < 0) {
				synchronized (_file) {
					if (_size < 0) {
						_size = (int)_file.getSize();
					}
				}
				// サイズが分かった時点でキャッシュサイズを自動調整します
				if (_size >= 0) {
					// 最小16KB(8KBx2)、最大64KB(8KBx8)、ファイルサイズの12.5%
					int limit = Math.max(2, Math.min(8, (_size >> 16)));
					setCacheLimit(limit);
				}
			}
			return _size;
		}
	}
	
	/**
	 * @param file
	 * @throws IOException 
	 */
	public FileByteBuffer(FileResource file) {
		super(new Manager(file, 8));
	}
}
