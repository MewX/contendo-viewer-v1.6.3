package net.zamasoft.reader.book;

import com.sun.jna.platform.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.zamasoft.reader.shelf.b;
import org.apache.commons.io.FileUtils;

public abstract class b implements b {
  private static Logger o = Logger.getLogger(b.class.getName());
  
  public static final int a = 1;
  
  public static final int b = 2;
  
  public static final int c = 0;
  
  public static final int d = 0;
  
  public static final int e = 1;
  
  public static final int f = 2;
  
  public static final int g = 3;
  
  private File p;
  
  protected p h = new p();
  
  protected int i;
  
  protected int j;
  
  protected k k;
  
  protected int l = 0;
  
  protected final SimpleIntegerProperty m = new SimpleIntegerProperty();
  
  protected ObservableList<n> n = FXCollections.observableArrayList();
  
  protected b(File paramFile) {
    a(paramFile);
  }
  
  public void a(int paramInt1, int paramInt2) throws Exception {
    this.h = new p();
    if (net.zamasoft.reader.b.l.exists()) {
      Properties properties = new Properties();
      try {
        FileInputStream fileInputStream = new FileInputStream(net.zamasoft.reader.b.l);
        try {
          properties.loadFromXML(fileInputStream);
        } finally {
          fileInputStream.close();
        } 
        this.h.b(properties);
      } catch (Exception exception) {
        o.log(Level.WARNING, "設定ファイルを読み込めませんでした。", exception);
      } 
    } 
    File file = e();
    if (file != null && file.exists() && file.length() > 0L) {
      Properties properties = new Properties();
      try {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
          properties.loadFromXML(fileInputStream);
        } finally {
          fileInputStream.close();
        } 
        this.h.b(properties);
        b(paramInt1, paramInt2);
        a(properties);
      } catch (SecurityException securityException) {
        throw securityException;
      } catch (Exception exception) {
        o.log(Level.WARNING, "設定ファイルを読み込めませんでした。", exception);
        b(paramInt1, paramInt2);
      } 
    } else {
      b(paramInt1, paramInt2);
    } 
    if (this.k == null) {
      this.k = a(0);
      this.l = 0;
    } 
  }
  
  protected abstract void a(Properties paramProperties) throws Exception;
  
  public void a() {
    if (this.k == null)
      return; 
    File file = e();
    if (file == null)
      return; 
    Properties properties = new Properties();
    this.h.a(properties);
    b(properties);
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      try {
        properties.storeToXML(fileOutputStream, net.zamasoft.reader.b.b);
      } finally {
        fileOutputStream.close();
      } 
    } catch (Exception exception) {
      o.log(Level.WARNING, "設定ファイルを保存できませんでした。", exception);
    } 
    i.a();
  }
  
  protected abstract void b(Properties paramProperties);
  
  public void b() throws IOException {
    FileUtils.getInstance().moveToTrash(new File[] { c() });
    FileUtils.deleteDirectory(d());
  }
  
  public void b(int paramInt1, int paramInt2) {
    this.i = paramInt1 - 4;
    this.j = paramInt2 - 4;
  }
  
  public File c() {
    return this.p;
  }
  
  public void a(File paramFile) {
    this.p = paramFile;
  }
  
  public File d() {
    File file = new File(new File(this.p.getParentFile(), ".cache"), this.p.getName());
    if (!file.exists())
      file.mkdirs(); 
    return file;
  }
  
  public File e() {
    File file = d();
    return new File(file, "config");
  }
  
  public p l_() {
    return this.h;
  }
  
  public int m_() {
    return (this.i - 1) / 2;
  }
  
  public int n_() {
    return this.i;
  }
  
  public int i() {
    return this.j;
  }
  
  public IntegerProperty o_() {
    return (IntegerProperty)this.m;
  }
  
  public abstract int k();
  
  public abstract k a(int paramInt) throws Exception;
  
  public abstract String b(int paramInt) throws Exception;
  
  public abstract String c(int paramInt1, int paramInt2);
  
  public abstract k a(h paramh) throws Exception;
  
  public abstract k a(URI paramURI) throws Exception;
  
  public abstract int l();
  
  public int p_() {
    return (l() == 1) ? 1 : 2;
  }
  
  public int q_() {
    return (l() == 1) ? 2 : 1;
  }
  
  public abstract int a(k paramk, int paramInt);
  
  public abstract int c(int paramInt) throws Exception;
  
  public abstract y d(int paramInt) throws Exception;
  
  public abstract ObservableList<s> o();
  
  public abstract List<s> a(k paramk);
  
  public ObservableList<n> p() {
    return this.n;
  }
  
  public h q() {
    return this.k.e(this.l).e();
  }
  
  public abstract void r();
  
  public abstract boolean s();
  
  public long t() {
    return e().lastModified();
  }
  
  public long u() {
    return c().lastModified();
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof b) ? c().equals(((b)paramObject).c()) : false;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/b.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */