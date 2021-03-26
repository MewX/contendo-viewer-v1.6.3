package com.essg.sdc.vfm.extractor;

import java.io.File;
import java.util.Collection;

import com.essg.sdc.net.Mime;
import com.essg.sdc.vfm.IOHandlerInterface;

/**
 * 書庫解凍処理インターフェイス
 * @author Mine
 */
public interface FileExtractor {
	/**
	 * このクラスが取り扱うコンテンツタイプを取得します。
	 * @return　コンテンツタイプ
	 */
	public Collection<Mime> getTypes();
	
	/**
	 * 一つのファイルを高速に解凍できるかを取得します。
	 * @return true=可能 / false=不可
	 */
	public boolean isFastCriticalExtractable();

	/**
	 * このクラスで入力ストリームが解凍できるファイルかを試みます
	 * @param handler
	 * @return true=成功 / false=失敗
	 */
	public boolean isExtractable(IOHandlerInterface handler);

	/**
	 * 入力ストリームから解凍処理を行います
	 * @param handler
	 * @param file　対象のファイル
	 * @return true=成功 / false=失敗
	 */
	public ExtractFileInterface extract(IOHandlerInterface handler, File file);
	
	/**
	 * ファイルの一覧を取得します
	 * @param handler
	 * @param file 対象のファイルを指定します。nullの場合は全てを返します。
	 * @return
	 */
	public File[] list(IOHandlerInterface handler, File file);
	
	// TODO 全ての処理の中断（実装予定）
	//public void abort(IOHandlerInterface handler);
}
