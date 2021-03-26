package com.essg.sdc.vfm.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.essg.sdc.net.Mime;
import com.essg.sdc.nio.ByteBuffer;
import com.essg.sdc.nio.stream.ByteBufferInputStream;
import com.essg.sdc.vfm.file.FileResource;
import com.essg.sdc.vfm.io.FileByteBuffer;

public class HttpRangeSpec {
	private static final Mime[] STREAMING_MIMES = {
		new Mime("audio/*"),
		new Mime("video/*"),
	};
	
	public static class Range {
		String original;
		long begin = -1;
		long end = -1;
		InputStream istream = null;
		
		public Range(String txt, long begin, long end) {
//			original = txt;
			original = begin + "-" + end;
			this.begin = begin;
			this.end = end;
		}
		
		public long size() {
			return end - begin + 1;
		}
		
		public String getRangeText() {
			return original;
		}
		
		public InputStream getStream() {
			return istream;
		}
		
		private void setStream(InputStream istream) {
			this.istream = istream;
		}
	}
	
	private final List<Range> _list;
	private long _totalSize;
	private final String _encode;
	private InputStream _istream;
	private long _contentSize;
	
	public static HttpRangeSpec getInstance(final FileResource file, boolean zipsupported, String ranges) throws HttpException, IOException {
		HttpRangeSpec range = null;
		
		InputStream is = null;
		long size = 0;
		Mime mime = file.getType();
		String enc = null;
		
		// レンジを処理します
		LinkedList<Range> list = null;
		if (ranges != null) {
			list = new LinkedList<Range>();
			int i = 0;
			int m = ranges.length();
			boolean loop = true;
			
			int n = ranges.indexOf("=");
			if (n >= 0) {
				i = n + 1;
			}
			
			for (;loop && i < m; i++) {
				Long start = null;
				Long end = null;
				int s = -1;
				int e = -1;
				for (; i < m; i++) {
					char c = ranges.charAt(i);
					if (c == ',') break;
					switch (c) {
					case ' ':
						if (s >= 0 && e < 0) e = i;
						break;
					case '-':
						// この時は不正です
						if (start != null) throw new HttpException("Range request error : Syntax error, detect multi '-' chars.");
						start = parseNum(ranges, s, e, i);
						s = -1;
						e = -1;
						break;
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						// この時は不正です
						if (e >= 0) throw new HttpException("Range request error : Illegal number format.");
						if (s < 0) s = i;
						break;
					default:
						loop = false;
						break;
					}
				}
				
				if (start == null) throw new HttpException("Range request error : Start offset not defined.");
				end = parseNum(ranges, s, e, i);
				if (start < 0) {
					if (end < 0) throw new HttpException("Range request error : Syntax error, Offset not detected.");
					// ～ファイル終端まで（バイト数で指定）
					list.add(new Range("-" + end.toString(), Math.max(file.getSize() - end, 0), file.getSize() - 1));
				} else {
					if (end < 0) {
						// 開始位置～ファイル終端まで
						list.add(new Range(start.toString() + "-", start, file.getSize() - 1));
					} else {
						// 開始位置～終了位置まで
						end = Math.min(end, file.getSize() - 1);
						if (start > end) throw new HttpException("Range request error : Illegal range offset.");
						list.add(new Range(start.toString() + "-" + end.toString(), start, end));
					}
				}
			}
		}
		
		//
		if (list == null || list.size() == 0) {
			if (zipsupported) {
				if (file.isSupportGZip()) {
					for (Mime m : STREAMING_MIMES) {
						if (mime.equals(m)) {
							zipsupported = false;
							break;
						}
					}
				} else {
					zipsupported = false;
				}
			}
			if (zipsupported) {
				is = file.openGZipStream();
				size = file.gzipStreamLength();
				enc = "gzip";
			} else {
    			is = file.openStream();
    			size = file.getSize();
			}
		} else {
			// 隣接した範囲を結合します
			if (list.size() > 1) {
				ListIterator<Range> it = list.listIterator();
				Range prev = null;
				while (it.hasNext()) {
					Range cur = it.next();
					if (prev != null) {
						if ((prev.end + 1) == cur.begin) {
							prev.end = cur.end;
							it.remove();
							continue;
						}
					}
					prev = cur;
				}
			}
			
			// 初回は遅いかもしれないがキャッシュ効果に期待してバッファーを利用します
			ByteBuffer buf = null;
			try {
				buf = file.getBuffer();
			} catch (IOException e) {
			}
			if (buf == null) buf = new FileByteBuffer(file);
			size = file.getSize();
			for (Range r : list) {
				int n = (int)r.size();
				r.setStream(new ByteBufferInputStream(buf.position((int)r.begin).slice().limit(n)));
			}
		}
		
		//
		range = new HttpRangeSpec(is, list, size, enc);
		
		return range;
	}
	
	private static Long parseNum(String txt, int s, int e, int i) {
		if (s >= 0) {
			String num = txt.substring(s, e < 0 ? i : e);
			if (num.length() > 0) {
				return Long.parseLong(num);
			}
		}
		return Long.valueOf(-1);
	}
	
	private HttpRangeSpec(InputStream istream, List<Range> list, long size, String enc) {
		_istream = istream;
		_list = list;
		_totalSize = size;
		_encode = enc;
		_contentSize = _totalSize;
		if (list != null && list.size() > 0) {
			_contentSize = 0;
			for (Range r : list) {
				_contentSize += r.size();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < _list.size(); i++) {
			if (i > 0) sb.append(",");
			sb.append(_list.get(i).getRangeText());
		}
		return sb.toString();
	}
	
	public boolean isMultiPart() {
		return _list.size() > 1;
	}
	
	public boolean isEncoded() {
		return _encode != null;
	}
	
	public String getEncode() {
		return _encode;
	}
	
	public long totalLength() {
		return _totalSize;
	}
	
	public long contentLength() {
		return _contentSize;
	}
	
	public List<Range> getRanges() {
		return Collections.unmodifiableList(_list);
	}
	
	/**
	 * @return
	 */
	public int size() {
		return _list == null ? 0 : _list.size();
	}
	
	public InputStream getStream() {
		if (_istream != null) return _istream;
		if (_list != null && !_list.isEmpty()) {
			return _list.get(0).getStream();
		}
		return null;
	}

}
