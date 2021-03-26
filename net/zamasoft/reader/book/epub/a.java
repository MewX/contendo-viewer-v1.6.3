package net.zamasoft.reader.book.epub;

import com.b.a.d;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import jp.cssj.d.a.o;

public class a extends b {
  private static Logger L = Logger.getLogger(a.class.getName());
  
  protected o o = null;
  
  public a(File paramFile) throws IOException, Exception {
    super(paramFile);
  }
  
  public jp.cssj.d.a.a y() throws IOException {
    return (jp.cssj.d.a.a)new d((jp.cssj.d.a.a)new o(c()), com.b.a.a.a());
  }
  
  public Media b(URI paramURI) {
    String str1;
    if (this.o == null) {
      this.o = new o(this);
      try {
        this.o.a();
      } catch (IOException iOException) {
        L.log(Level.WARNING, "オーディオサーバーを起動できませんでした", iOException);
        return null;
      } 
    } 
    String str2 = paramURI.getScheme();
    if ("http".equalsIgnoreCase("scheme") || "https".equalsIgnoreCase("scheme")) {
      str1 = paramURI.toString();
    } else if ("archive".equals(str2)) {
      this.o.a(paramURI);
      int i = this.o.d();
      str1 = "http://127.0.0.1:" + i + "/" + paramURI.toString();
    } else {
      return null;
    } 
    return new Media(str1);
  }
  
  public void a() {
    super.a();
    if (this.o != null) {
      this.o.b();
      this.o = null;
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/epub/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */