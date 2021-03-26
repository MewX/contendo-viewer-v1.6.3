package jp.cssj.b;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import jp.cssj.b.b.a;
import jp.cssj.b.c.b;
import jp.cssj.b.d.c;
import jp.cssj.e.a;
import jp.cssj.e.b;

public interface c extends Closeable {
  public static final byte a = 1;
  
  public static final byte b = 2;
  
  InputStream a(URI paramURI) throws IOException;
  
  void a(c paramc) throws IOException;
  
  void a(a parama) throws IOException;
  
  void a(b paramb) throws IOException;
  
  void a(String paramString1, String paramString2) throws IOException;
  
  OutputStream a(a parama) throws IOException;
  
  void a(b paramb) throws IOException;
  
  void a(jp.cssj.e.c paramc) throws IOException;
  
  OutputStream b(a parama) throws IOException;
  
  void b(URI paramURI) throws IOException, d;
  
  void b(b paramb) throws IOException, d;
  
  void a(boolean paramBoolean) throws IOException;
  
  void a() throws IOException;
  
  void a(byte paramByte) throws IOException;
  
  void b() throws IOException;
  
  void close() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */