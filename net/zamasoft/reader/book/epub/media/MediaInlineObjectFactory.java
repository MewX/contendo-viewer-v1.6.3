package net.zamasoft.reader.book.epub.media;

import jp.cssj.homare.css.InlineObjectFactory;
import jp.cssj.homare.css.a;
import jp.cssj.homare.css.h;
import jp.cssj.homare.xml.d;

public class MediaInlineObjectFactory implements InlineObjectFactory {
  protected static final d a = new d("http://www.w3.org/1999/xhtml", "html", "video");
  
  protected static final d b = new d("http://www.w3.org/1999/xhtml", "html", "audio");
  
  protected static final d c = new d("http://www.w3.org/1999/xhtml", "html", "source");
  
  public boolean match(a parama) {
    a a1 = parama;
    return (a.a(a1) || b.a(a1));
  }
  
  public h createInlineObject() {
    return new b();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/media/MediaInlineObjectFactory.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */