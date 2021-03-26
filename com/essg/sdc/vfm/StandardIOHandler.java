package com.essg.sdc.vfm;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import com.essg.sdc.io.CloseListenableOutputStream;
import com.essg.sdc.io.ICloseableListener;
import com.essg.sdc.io.util.StreamUtil;
import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.vfm.file.FileInterface;
import com.essg.sdc.vfm.file.WriteableFileInterface;
import com.essg.sdc.vfm.file.cache.CacheFolder;

/**
 * @author ess0083
 *
 */
public class StandardIOHandler implements IOHandlerInterface, ICloseableListener {
	FileInterface _input;
	CacheFolder _cache;
	
	HashMap<String, WriteableFileInterface> mapPath = new HashMap<String, WriteableFileInterface>();

	// 出力ストリームとPathを一時的に保持します
	HashMap<Closeable, String> mapStream = new HashMap<Closeable, String>();
	
	// PathとFileを一時的に保持します
	HashMap<String, WriteableFileInterface> mapFile = new HashMap<String, WriteableFileInterface>();
	
	/**
	 * コンストラクタ
	 * @param archive
	 * @param wkdir
	 */
	public StandardIOHandler(FileInterface archive, CacheFolder cache) {
		_input = archive;
		_cache = cache;
	}
	
	public FileInterface getFile(String path) {
		if (path == null || path.length() == 0) {
			return _input;
		}
		FileInterface p = null;
		synchronized (mapPath) {
			p = mapPath.get(path);
		}
		return p;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IOHandlerInterface#abandon()
	 */
	public void abandon() {
		synchronized (mapStream) {
			for (Closeable out : mapStream.keySet()) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
			mapStream.clear();
			mapFile.clear();
		}
		synchronized (mapPath) {
			mapPath.clear();
		}
		_cache.abandon();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.IOHandlerInterface#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		return _input.openStream();
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.IOHandlerInterface#getInputBuffer()
	 */
	@Override
	public ByteBuffer getInputBuffer() throws IOException {
		return _input.getBuffer();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.IOHandlerInterface#getInputStream()
	 */
	@Override
	public InputStream getInputStream(File file) throws IOException {
		FileInterface f = null;
		synchronized (mapPath) {
			f = mapPath.get(file.getPath());
		}
		if (f == null) throw new FileNotFoundException();
		
		return f.openStream();
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.IOHandlerInterface#getInputBuffer()
	 */
	@Override
	public ByteBuffer getInputBuffer(File file) throws IOException {
		FileInterface f = null;
		synchronized (mapPath) {
			f = mapPath.get(file.getPath());
		}
		if (f == null) throw new FileNotFoundException();

		return f.getBuffer();
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.IOHandlerInterface#getOutputStream(java.io.File)
	 */
	@Override
	public OutputStream getOutputStream(File file)
			throws IOException {
		return getOutputStream(file, file.getPath());
	}

	/**
	 * @param fpath
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private OutputStream getOutputStream(File fpath, String path)
			throws IOException {
		path = path.intern();
		OutputStream os = null;
		
		WriteableFileInterface f = null;
		f = mapPath.get(path);
		if (f == null) {
			f = _cache.getWriteableFile(path);
		}
//		mkdir(fpath.getParent());
		
		os = f.openOutputStream();
		if (!(os instanceof CloseListenableOutputStream)) {
			os = new CloseListenableOutputStream(os);
		}
		
		synchronized (mapStream) {
			mapStream.put(os, path);
			mapFile.put(path, f);
		}
		((CloseListenableOutputStream)os).setOnCloseListener(this);
		return os;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileExtractor.IOHandlerInterface#abortOutput(java.io.OutputStream)
	 */
	@Override
	public void abortOutput(OutputStream stream) {
		String path = null;
		synchronized (mapStream) {
			path = mapStream.remove(stream);
		}
		if (path != null) {
			abortOutput(path);
		}
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileExtractor.IOHandlerInterface#abortOutput(java.io.File)
	 */
	@Override
	public void abortOutput(File file) {
		abortOutput(file.getAbsolutePath());
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileExtractor.IOHandlerInterface#abortOutput(java.lang.String)
	 */
	@Override
	public void abortOutput(String path) {
		FileInterface f = null;
		synchronized (mapStream) {
			f = mapFile.remove(path);
		}
		if (f == null) {
			synchronized (mapPath) {
				mapPath.remove(path);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileExtractor.IOHandlerInterface#mkdir(java.io.File)
	 */
	@Override
	public void mkdir(File file) throws FileNotFoundException {
		mkdir(file.getAbsolutePath());
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.FileExtractor.IOHandlerInterface#mkdir(java.lang.String)
	 */
	@Override
	public void mkdir(String path) throws FileNotFoundException {
//		if (path == null) return;
//		
//		LinkedList<String> listPath = new LinkedList<String>();
//		File vfile = new File(path);
//		synchronized (mapPath) {
//			while (vfile != null) {
//				// フォルダの登録を確認します
//				if (mapPath.containsKey(vfile.getAbsolutePath())) {
//					// 登録済みの場合
//					if (!mapPath.get(vfile.getAbsolutePath()).isDirectory()) {
//						throw new FileNotFoundException("Parent path is not a directory.");
//					}
//					break;
//				}
//				listPath.add(vfile.getAbsolutePath());
//				
//				// 親フォルダに移動します
//				vfile = vfile.getParentFile();
//			}
//			
//			// 未登録のフォルダを登録します
//			while (!listPath.isEmpty()) {
//				// どちらからでも良いが、親フォルダから登録します（気分の問題）
//				String tmp = listPath.removeLast();
//				if (!tmp.equals("/")) {
//					mapPath.put(tmp, new DirectoryFile(_input.getFolder(), tmp));
//				}
//			}
//		}
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.io.ICloseableListener#onStreamClosed(java.io.Closeable, boolean)
	 */
	@Override
	public void onStreamClosed(Closeable stream, boolean success) {
		String path = null;
		WriteableFileInterface f = null;
		synchronized (mapStream) {
			path = mapStream.remove(stream);
			f = mapFile.remove(path);
		}
		if (success && path != null && f != null) {
			synchronized (mapPath) {
				mapPath.put(path, f);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.IOHandlerInterface#copyStream(java.io.InputStream, java.io.File)
	 */
	@Override
	public void copyStream(InputStream istream, String opath) throws IOException {
		copyStream(istream, new File(opath));
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.IOHandlerInterface#copyStream(java.io.InputStream, java.lang.String)
	 */
	@Override
	public void copyStream(InputStream istream, File ofile)
			throws IOException {
		OutputStream os = getOutputStream(ofile);
		try {
			copyStream(istream, os);
		} catch (IOException e) {
			abortOutput(os);
			throw e;
		} finally {
			os.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.essg.sdc.android.bookreader.contents.IOHandlerInterface#copyStream(java.io.InputStream, java.io.OutputStream)
	 */
	@Override
	public void copyStream(InputStream istream, OutputStream ostream)
			throws IOException {
		StreamUtil.copy(istream, ostream);
	}
}