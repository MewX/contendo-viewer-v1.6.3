package com.essg.sdc.vfm.extractor;

import java.io.IOException;
import java.io.InputStream;

import com.essg.sdc.io.zip.DeflateInfo;
import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.vfm.IOHandlerInterface;

/**
 * 書庫ファイル内の仮想ディレクトリ
 * 実体が無いディレクトリの振る舞いを実装しています。
 * @author Mine
 */
public class ExtractFileDirectory implements ExtractFileInterface {

	// インスタンスを保持
	private static final ExtractFileDirectory _instance = new ExtractFileDirectory();
	
	/**
	 * インスタンスを取得します
	 * 全て同一のインスタンスを返します。
	 * @return 取得したインスタンス
	 */
	public static ExtractFileDirectory getInstance() {
		return _instance;
	}
	
	/**
	 * コンストラクタ
	 * 個別にインスタンス化は禁止（無駄になるので）
	 * @see #getInstance()
	 */
	private ExtractFileDirectory() {
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.extractor.ExtractFileInterface#openStream(com.essg.sdc.vfm.IOHandlerInterface)
	 */
	@Override
	public InputStream openStream(IOHandlerInterface handler)
			throws IOException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.extractor.ExtractFileInterface#getBuffer(com.essg.sdc.vfm.IOHandlerInterface)
	 */
	@Override
	public ByteBuffer getBuffer(IOHandlerInterface handler) throws IOException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.extractor.ExtractFileInterface#getFile(com.essg.sdc.vfm.IOHandlerInterface)
	 */
	@Override
	public void getFile(IOHandlerInterface handler) throws IOException {
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.extractor.ExtractFileInterface#getSize(com.essg.sdc.vfm.IOHandlerInterface)
	 */
	@Override
	public long getSize(IOHandlerInterface handler) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.extractor.ExtractFileInterface#getCSize(com.essg.sdc.vfm.IOHandlerInterface)
	 */
	@Override
	public long getCSize(IOHandlerInterface handler) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.extractor.ExtractFileInterface#isDirectory(com.essg.sdc.vfm.IOHandlerInterface)
	 */
	@Override
	public boolean isDirectory(IOHandlerInterface handler) {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.extractor.ExtractFileInterface#isDeflated(com.essg.sdc.vfm.IOHandlerInterface)
	 */
	@Override
	public boolean isDeflated(IOHandlerInterface handler) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.extractor.ExtractFileInterface#openDeflatedStream(com.essg.sdc.vfm.IOHandlerInterface)
	 */
	@Override
	public InputStream openDeflatedStream(IOHandlerInterface handler)
			throws IOException {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.essg.sdc.vfm.extractor.ExtractFileInterface#getDeflatedInfo(com.essg.sdc.vfm.IOHandlerInterface)
	 */
	@Override
	public DeflateInfo getDeflatedInfo(IOHandlerInterface handler)
			throws IOException {
		return null;
	}

}
