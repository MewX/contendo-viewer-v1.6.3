package com.essg.sdc.vfm.http;

/**
 * ワンタイムキーインターフェイス
 * @author Mine
 */
public interface OneTimeKeyInterface {
	/**
	 * このワンタイムキーが現在時刻に対して失効しているか確認します
	 * @param time 現在時刻(ミリ秒）
	 * @return true=失効, false=有効
	 */
	boolean isExpired(long time);
	
	/**
	 * キー内容を取得します
	 * @return 取得したキーデータ
	 */
	byte[] getKey();
}
