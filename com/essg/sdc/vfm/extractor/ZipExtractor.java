package com.essg.sdc.vfm.extractor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.essg.sdc.io.util.StreamUtil;
import com.essg.sdc.io.zip.DeflateInfo;
import com.essg.sdc.io.zip.FastZipFile;
import com.essg.sdc.io.zip.FastZipFile.FZipEntry;
import com.essg.sdc.net.Mime;
import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.vfm.IOHandlerInterface;

public class ZipExtractor implements FileExtractor {
	
	// 扱えるMimeタイプのリスト
	private static final List<Mime> CONTENTS_TYPES = Arrays.asList(new Mime[]{ new Mime("application", "zip") });
	
	// ログ出力
	private static final Logger logger = LoggerFactory.getLogger(ZipExtractor.class);

	private class FZipFileInfo implements ExtractFileInterface {
		FZipEntry _entry;

		/**
		 * @param entry
		 */
		protected FZipFileInfo(FZipEntry entry) {
			_entry = entry;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.extractor.ExtractFileInterface#openStream(com.essg.sdc.android.bookreader.contents.IOHandlerInterface)
		 */
		@Override
		public InputStream openStream(IOHandlerInterface handler) throws IOException {
			return _entry.getInputStream();
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.extractor.ExtractFileInterface#getBuffer(com.essg.sdc.android.bookreader.contents.IOHandlerInterface)
		 */
		@Override
		public ByteBuffer getBuffer(IOHandlerInterface handler)	throws IOException {
			ByteBuffer buf = _entry.getBuffer();
//			if (buf == null) {
//				//IOHandler経由でキャッシュできるByteBufferを生成する
//				File tmp = new File(_entry.getName());
//				try {
//					buf = handler.getInputBuffer(tmp);
//				} catch (FileNotFoundException e) {
//					getFile(handler);
//					buf = handler.getInputBuffer(tmp);
//				}
//			}
			return buf;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.extractor.ExtractFileInterface#getFile(com.essg.sdc.android.bookreader.contents.IOHandlerInterface)
		 */
		@Override
		public void getFile(IOHandlerInterface handler) throws IOException {
			if (!isDirectory(handler)) {
				logger.debug("getFile() -start- " + _entry.getName());
				InputStream is = openStream(handler);
				try {
					OutputStream os = handler.getOutputStream(new File(_entry.getName()));
					try {
						handler.copyStream(is, os);
					} catch (IOException e) {
						handler.abortOutput(os);
						throw e;
					} finally {
						StreamUtil.close(os);
					}
				} finally {
					StreamUtil.close(is);
					logger.debug("getFile() - end - " + _entry.getName());
				}
			}
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.extractor.ExtractFileInterface#getSize(com.essg.sdc.android.bookreader.contents.IOHandlerInterface)
		 */
		@Override
		public long getSize(IOHandlerInterface handler) {
			return _entry.getSize();
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.extractor.ExtractFileInterface#getCSize(com.essg.sdc.android.bookreader.contents.IOHandlerInterface)
		 */
		public long getCSize(IOHandlerInterface handler) {
			return _entry.getCsize();
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.extractor.ExtractFileInterface#isDirectory(com.essg.sdc.android.bookreader.contents.IOHandlerInterface)
		 */
		@Override
		public boolean isDirectory(IOHandlerInterface handler) {
			return _entry.isDirectory();
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.extractor.ExtractFileInterface#isDeflate(com.essg.sdc.android.bookreader.contents.IOHandlerInterface)
		 */
		@Override
		public boolean isDeflated(IOHandlerInterface handler) {
			return ((_entry.getMethod() & FZipEntry.DEFLATED) != 0);
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.extractor.ExtractFileInterface#openDeflatedStream(com.essg.sdc.android.bookreader.contents.IOHandlerInterface)
		 */
		@Override
		public InputStream openDeflatedStream(IOHandlerInterface handler)
				throws IOException {
			if (isDeflated(handler)) {
				return _entry.getInputStream(true);
			}
			return null;
		}

		/* (non-Javadoc)
		 * @see com.essg.sdc.android.bookreader.contents.extractor.ExtractFileInterface#getDeflatedInfo(com.essg.sdc.android.bookreader.contents.IOHandlerInterface)
		 */
		@Override
		public DeflateInfo getDeflatedInfo(IOHandlerInterface handler)
				throws IOException {
			return _entry;
		}

	}

	private HashMap<String, ExtractFileInterface> mapEntry = new HashMap<String, ExtractFileInterface>();
	private FastZipFile zipFile = null;

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileExtractor#getType()
	 */
	@Override
	public Collection<Mime> getTypes() {
		return CONTENTS_TYPES;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileExtractor#isFastCriticalExtractable()
	 */
	public boolean isFastCriticalExtractable() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileExtractor#isExtractable(com.essg.sdc.android.bookreader.contents.FileExtractor.IOHandlerInterface)
	 */
	@Override
	public boolean isExtractable(IOHandlerInterface handler) {
		try {
//			zipFile = new FastZipFile(handler.getInputFile());
			zipFile = new FastZipFile(handler.getInputBuffer());
			Enumeration<? extends FZipEntry> en = zipFile.entries();
			while (en.hasMoreElements()) {
				FZipEntry entry = en.nextElement();
				//Log.d(CLASS_NAME, "Zip Name = " + entry.getName());
				File f = new File(entry.getName());
				while (f != null) {
					String n = f.getAbsolutePath();
					if (mapEntry.containsKey(n)) break;
					if (entry == null) entry = new FZipEntry();
					mapEntry.put(n, new FZipFileInfo(entry));
					entry = null;
					f = f.getParentFile();
				}
			}
			return mapEntry.size() > 0;
		} catch (Exception e) {
			logger.error("isExtractable", e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileExtractor#extract(com.essg.sdc.android.bookreader.contents.FileExtractor.IOHandlerInterface, java.io.File)
	 */
	@Override
	public ExtractFileInterface extract(IOHandlerInterface handler, File file) {
		try {
			String path = file.getAbsolutePath();
			ExtractFileInterface entry = mapEntry.get(path);
			if (entry == null) {
				logger.warn("extract() not found in map :" + path);
				for (String p : mapEntry.keySet()) {
					File f = new File(p).getParentFile();
					if (f != null && f.equals(file)) {
						logger.warn("extract() make directory");
						entry = ExtractFileDirectory.getInstance();
						mapEntry.put(path, entry);
						break;
					}
				}
			}

			return entry;
		} catch (Exception e) {
			logger.error("extract", e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.extractor.FileExtractor#list(com.essg.sdc.android.bookreader.contents.IOHandlerInterface, java.io.File)
	 */
	@Override
	public File[] list(IOHandlerInterface handler, File file) {
		String path = file == null ? null : file.getAbsolutePath();
		ArrayList<File> list = new ArrayList<File>();
		for (String key : mapEntry.keySet()) {
			if (path == null || key.startsWith(path)) {
				list.add(new File(key));
			}
		}
		return list.toArray(new File[list.size()]);
	}
}
