package net.zamasoft.reader.shelf;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.json.JsonObject;
import net.zamasoft.reader.book.b;
import net.zamasoft.reader.book.d;
import net.zamasoft.reader.book.f;
import net.zamasoft.reader.book.m;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;

public class c implements f, b {
  private File a;
  
  private String b;
  
  private String c;
  
  private String d;
  
  private String[] e;
  
  private String[] f;
  
  private String[] g;
  
  private String[] h;
  
  private long i;
  
  private final List<a> j = new ArrayList<>();
  
  public c(File paramFile, JsonObject paramJsonObject, List<JsonObject> paramList, List<String[]> paramList1, String paramString) {
    a(paramFile, paramJsonObject, paramList, paramList1, paramString);
  }
  
  public void a(File paramFile, JsonObject paramJsonObject, List<JsonObject> paramList, List<String[]> paramList1, String paramString) {
    if (paramList1 == null)
      paramList1 = Collections.emptyList(); 
    this.a = new File(paramFile, this.b = paramJsonObject.getString("id"));
    this.c = paramJsonObject.getString("name");
    this.d = paramJsonObject.getString("name_ruby");
    this.g = new String[paramList1.size()];
    this.h = new String[paramList1.size()];
    for (byte b1 = 0; b1 < paramList1.size(); b1++) {
      String[] arrayOfString = paramList1.get(b1);
      this.g[b1] = arrayOfString[0];
      this.h[b1] = arrayOfString[1];
    } 
    this.e = new String[] { paramString };
    this.f = new String[] { null };
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      this.i = simpleDateFormat.parse(paramJsonObject.getString("cover_updated_at")).getTime();
    } catch (Exception exception) {
      this.i = 0L;
    } 
    label48: for (JsonObject jsonObject : paramList) {
      a a = new a(this);
      a.b = jsonObject.getString("file_extention");
      a.c = jsonObject.getString("items_type");
      try {
        a.g = Integer.parseInt(jsonObject.getString("download_enabled_count"));
      } catch (Exception exception) {
        a.g = -1;
      } 
      try {
        a.d = simpleDateFormat.parse(jsonObject.getString("purchased_at")).getTime();
      } catch (Exception exception) {
        a.d = 0L;
      } 
      try {
        a.e = simpleDateFormat.parse(jsonObject.getString("download_enabled_at")).getTime();
      } catch (Exception exception) {
        a.e = 0L;
      } 
      try {
        a.f = simpleDateFormat.parse(jsonObject.getString("read_enabled_at")).getTime();
      } catch (Exception exception) {
        a.f = 0L;
      } 
      for (byte b2 = 0; b2 < this.j.size(); b2++) {
        a a1 = this.j.get(b2);
        if (a1.c.equals(a.c)) {
          this.j.set(b2, a);
          continue label48;
        } 
      } 
      this.j.add(a);
    } 
    if (this.j.size() >= 2 && ((a)this.j.get(0)).c.equals("R")) {
      a a = this.j.get(0);
      this.j.set(0, this.j.get(1));
      this.j.set(1, a);
    } 
  }
  
  public List<a> a() {
    return this.j;
  }
  
  public String k() {
    return this.b;
  }
  
  public f B() {
    return this;
  }
  
  public String i_() {
    return this.c;
  }
  
  public String j_() {
    return this.d;
  }
  
  public String[] f() {
    return this.g;
  }
  
  public String[] g() {
    return this.h;
  }
  
  public String[] h() {
    return this.e;
  }
  
  public String[] k_() {
    return this.f;
  }
  
  public String[] j() {
    return null;
  }
  
  public String[] m() {
    return null;
  }
  
  public long n() {
    return ((a)this.j.get(0)).d;
  }
  
  public long l() {
    return this.i;
  }
  
  public m C() {
    return null;
  }
  
  public BufferedImage d(int paramInt1, int paramInt2) {
    com.b.a.a a = com.b.a.a.a();
    BufferedImage bufferedImage = new BufferedImage(paramInt1, paramInt2, 2);
    try {
      BufferedImage bufferedImage1;
      String str = a.a(this.b);
      CloseableHttpClient closeableHttpClient = com.b.a.a.e();
      try {
        HttpGet httpGet = new HttpGet(str);
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute((HttpUriRequest)httpGet);
        bufferedImage1 = ImageIO.read(closeableHttpResponse.getEntity().getContent());
        if (closeableHttpClient != null)
          closeableHttpClient.close(); 
      } catch (Throwable throwable) {
        if (closeableHttpClient != null)
          try {
            closeableHttpClient.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          }  
        throw throwable;
      } 
      double d1 = bufferedImage1.getWidth();
      double d2 = bufferedImage1.getHeight();
      double d3 = Math.min(paramInt1 / d1, paramInt2 / d2);
      d1 *= d3;
      d2 *= d3;
      Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
      RenderingHints renderingHints = graphics2D.getRenderingHints();
      net.zamasoft.reader.util.c.a(renderingHints);
      graphics2D.setRenderingHints(renderingHints);
      graphics2D.drawImage(bufferedImage1.getScaledInstance((int)d1, (int)d2, 4), (int)((paramInt1 - d1) / 2.0D), (int)((paramInt2 - d2) / 2.0D), (ImageObserver)null);
      bufferedImage1.flush();
    } catch (IOException iOException) {
      Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
      graphics2D.setColor(Color.GRAY);
      graphics2D.fillRect(0, 0, paramInt1, paramInt2);
    } 
    return bufferedImage;
  }
  
  public File d() {
    this.a.mkdirs();
    return this.a;
  }
  
  public boolean o() {
    for (a a : this.j) {
      if (a.a())
        return true; 
    } 
    return false;
  }
  
  public int p() {
    return ((a)this.j.get(0)).g;
  }
  
  public long q() {
    return ((a)this.j.get(0)).e;
  }
  
  public long r() {
    return ((a)this.j.get(0)).f;
  }
  
  public long t() {
    for (a a : this.j) {
      if (a.a()) {
        File file1 = a.h();
        File file2 = new File(new File(file1.getParentFile(), ".cache"), file1.getName());
        File file3 = new File(file2, "config");
        return file3.exists() ? file3.lastModified() : 0L;
      } 
    } 
    return 0L;
  }
  
  public long u() {
    for (a a : this.j) {
      if (a.a()) {
        File file = a.h();
        return file.exists() ? file.lastModified() : 0L;
      } 
    } 
    return 0L;
  }
  
  public boolean z() {
    return false;
  }
  
  public b a(boolean paramBoolean) throws Exception {
    if (paramBoolean)
      return this; 
    for (a a : this.j) {
      if (a.a()) {
        File file = a.h();
        b b1 = d.a(file);
        return b1.a(false);
      } 
    } 
    return this;
  }
  
  public void b() throws IOException {
    FileUtils.deleteDirectory(d());
  }
  
  public class a {
    private String b;
    
    private String c;
    
    private long d;
    
    private long e;
    
    private long f;
    
    private int g;
    
    public a(c this$0) {}
    
    public boolean a() {
      File file1 = h();
      File file2 = i();
      return (file1.exists() && !file2.exists());
    }
    
    public String b() {
      return this.a.b;
    }
    
    public File c() {
      return this.a.d();
    }
    
    public String d() {
      return this.c;
    }
    
    public int e() {
      return this.g;
    }
    
    public long f() {
      return this.e;
    }
    
    public long g() {
      return this.f;
    }
    
    public File h() {
      return new File(UserBoardController.b, b() + "." + b() + "." + this.c);
    }
    
    public File i() {
      return new File(c(), "lock." + this.c + "." + this.b);
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/c.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */