package com.b.a;

import com.b.a.a.b;
import com.b.a.b.a;
import com.b.a.b.a.a;
import com.b.a.b.a.b;
import com.b.a.b.b;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.NetworkInterface;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.b;
import net.zamasoft.reader.util.AlertController;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class a {
  private static Logger e = Logger.getLogger(a.class.getName());
  
  private static final Random f;
  
  public static final String a = "https://api.i-press.jpn.com/";
  
  public static final String b = "https://api.contendo.jp/";
  
  public static final String c = "https://contendo.jp/storeAPI/viewers/";
  
  private static a g = null;
  
  private final a h = new a(new b(this) {
        public byte[] a() {
          return null;
        }
        
        public int b() {
          return 3;
        }
      });
  
  private String i = null;
  
  private String j = null;
  
  private String k = null;
  
  private String l = null;
  
  private long m = 0L;
  
  private HashMap<String, Object> n = null;
  
  private boolean o = false;
  
  private boolean p = false;
  
  private BooleanProperty q = (BooleanProperty)new SimpleBooleanProperty(false);
  
  private boolean r = false;
  
  private static File s = new File(b.i, "info");
  
  Thread d;
  
  private String t;
  
  public static synchronized a a() {
    if (g == null)
      g = new a(); 
    return g;
  }
  
  private a() {
    n();
    if (this.o)
      try {
        a(this.k, this.l, this.o, this.p);
      } catch (Exception exception) {
        e.log(Level.WARNING, "自動ログインに失敗しました", exception);
        Platform.runLater(new Runnable(this) {
              public void run() {
                AlertController.a(b.a.getString("error"), b.a.getString("failOnAutoLogin"), false);
              }
            });
      }  
    this.d = new Thread(this) {
        public void run() {
          while (true) {
            synchronized (this) {
              try {
                wait(300000L);
              } catch (InterruptedException interruptedException) {}
            } 
            if (this.a.q.get())
              try {
                this.a.o();
                this.a.f();
                this.a.p();
              } catch (SecurityException securityException) {
                securityException.printStackTrace();
                Platform.runLater(new Runnable(this) {
                      public void run() {
                        this.a.a.d();
                        ReaderApplication.getInstance().j().a().d();
                        AlertController.a(b.a.getString("error"), b.a.getString("doubleLogin"), false);
                      }
                    });
              } catch (Exception exception) {
                a.e.log(Level.WARNING, "二重ログインの定期チェックに失敗しました", exception);
              }  
          } 
        }
      };
    this.d.setDaemon(true);
    this.d.start();
  }
  
  private void a(InputStream paramInputStream, byte[] paramArrayOfbyte) throws Exception {
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    SecretKeySpec secretKeySpec = new SecretKeySpec(messageDigest.digest(paramArrayOfbyte), "AES");
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(2, secretKeySpec);
    ObjectInputStream objectInputStream = new ObjectInputStream(new CipherInputStream(paramInputStream, cipher));
    try {
      this.m = objectInputStream.readLong();
      this.n = (HashMap<String, Object>)objectInputStream.readObject();
      this.k = (String)objectInputStream.readObject();
      this.l = (String)objectInputStream.readObject();
      this.p = ((Boolean)objectInputStream.readObject()).booleanValue();
      this.o = ((Boolean)objectInputStream.readObject()).booleanValue();
    } finally {
      objectInputStream.close();
    } 
  }
  
  private void n() {
    if (!s.exists())
      return; 
    try {
      FileInputStream fileInputStream = new FileInputStream(s);
      try {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = fileInputStream.read(); i != 19; i = fileInputStream.read())
          stringBuffer.append((char)i); 
        long l = Long.parseLong(stringBuffer.toString());
        byte[] arrayOfByte = a(l);
        a(fileInputStream, arrayOfByte);
        fileInputStream.close();
      } catch (Throwable throwable) {
        try {
          fileInputStream.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        } 
        throw throwable;
      } 
    } catch (Exception exception) {
      e.log(Level.WARNING, "キーの読み込みエラー", exception);
    } 
    try {
      Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
      while (enumeration.hasMoreElements()) {
        try {
          NetworkInterface networkInterface = enumeration.nextElement();
          byte[] arrayOfByte = networkInterface.getHardwareAddress();
          if (arrayOfByte == null || arrayOfByte.length != 6 || networkInterface.getParent() != null)
            continue; 
          String str = String.format("%02x:%02x:%02x:%02x:%02x:%02x", new Object[] { Byte.valueOf(arrayOfByte[0]), Byte.valueOf(arrayOfByte[1]), Byte.valueOf(arrayOfByte[2]), Byte.valueOf(arrayOfByte[3]), Byte.valueOf(arrayOfByte[4]), Byte.valueOf(arrayOfByte[5]) });
          a(new FileInputStream(s), str.getBytes("UTF-8"));
          break;
        } catch (Exception exception) {
          e.log(Level.FINE, "キーの読み込みエラー", exception);
        } 
      } 
    } catch (Exception exception) {
      e.log(Level.WARNING, "キーの読み込みエラー", exception);
    } 
  }
  
  public String b() {
    return this.k;
  }
  
  public String c() {
    return this.l;
  }
  
  public void d() {
    this.t = null;
    this.o = false;
    this.q.set(false);
    this.m = 0L;
    this.r = false;
    p();
  }
  
  public void a(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2) throws Exception {
    this.t = null;
    try {
      b(paramString1, paramString2, paramBoolean1, paramBoolean2);
    } catch (IOException iOException) {
      c(paramString1, paramString2, paramBoolean1, paramBoolean2);
    } 
  }
  
  public static CloseableHttpClient e() {
    PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000).build();
    HttpClientBuilder httpClientBuilder = HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager((HttpClientConnectionManager)poolingHttpClientConnectionManager);
    ReaderApplication readerApplication = ReaderApplication.getInstance();
    if (readerApplication != null && readerApplication.e().get()) {
      HttpHost httpHost = new HttpHost((String)ReaderApplication.getInstance().f().get(), ReaderApplication.getInstance().g().get());
      DefaultProxyRoutePlanner defaultProxyRoutePlanner = new DefaultProxyRoutePlanner(httpHost);
      httpClientBuilder.setRoutePlanner((HttpRoutePlanner)defaultProxyRoutePlanner);
      if (((String)ReaderApplication.getInstance().h().get()).length() >= 1) {
        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(new AuthScope(httpHost), (Credentials)new UsernamePasswordCredentials((String)ReaderApplication.getInstance().h().get(), (String)ReaderApplication.getInstance().i().get()));
        httpClientBuilder.setDefaultCredentialsProvider((CredentialsProvider)basicCredentialsProvider);
      } 
    } 
    return httpClientBuilder.build();
  }
  
  private void b(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2) throws IOException, SAXException {
    CloseableHttpClient closeableHttpClient = e();
    try {
      HttpPost httpPost = new HttpPost("https://api.contendo.jp/users_api/auth/");
      httpPost.setHeader("x-login-name", paramString1);
      httpPost.setHeader("x-password", DigestUtils.md5Hex(paramString2));
      JsonObject jsonObject1 = Json.createObjectBuilder().add("device_type", 2).build();
      StringEntity stringEntity = new StringEntity(jsonObject1.toString(), ContentType.create("application/json", "UTF-8"));
      httpPost.setEntity((HttpEntity)stringEntity);
      CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute((HttpUriRequest)httpPost);
      JsonObject jsonObject2 = Json.createReader(closeableHttpResponse.getEntity().getContent()).readObject();
      JsonObject jsonObject3 = jsonObject2.getJsonObject("error");
      if (jsonObject3 != null)
        throw new SecurityException(jsonObject3.getString("message")); 
      this.i = jsonObject2.getJsonObject("contendo").getString("authentication_key");
      this.j = jsonObject2.getJsonObject("ipress").getString("authentication_key");
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
        this.m = simpleDateFormat.parse(jsonObject2.getJsonObject("contendo").getString("enabled_at")).getTime();
      } catch (ParseException parseException) {}
      f();
      this.q.set(true);
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
    this.k = paramString1;
    this.l = paramString2;
    this.q.set(true);
    this.p = paramBoolean2;
    this.o = paramBoolean1;
    this.r = true;
    p();
  }
  
  private void c(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2) {
    if (this.m < System.currentTimeMillis() || this.k == null || !this.k.equals(paramString1))
      throw new e(b.a.getString("networkError")); 
    if (this.l.equals(paramString2)) {
      this.q.set(true);
      this.p = paramBoolean2;
      this.o = paramBoolean1;
      return;
    } 
    throw new SecurityException(b.a.getString("wrongId"));
  }
  
  private void o() {
    if (!this.q.get() || this.r)
      return; 
    try {
      b(this.k, this.l, this.o, this.p);
    } catch (Exception exception) {}
  }
  
  private static byte[] a(long paramLong) {
    paramLong += 984563510L;
    return new byte[] { (byte)(int)(paramLong >> 56L), (byte)(int)(paramLong >> 48L), (byte)(int)(paramLong >> 40L), (byte)(int)(paramLong >> 32L), (byte)(int)(paramLong >> 24L), (byte)(int)(paramLong >> 16L), (byte)(int)(paramLong >> 8L), (byte)(int)(paramLong >> 0L) };
  }
  
  private synchronized void p() {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      long l = f.nextLong();
      byte[] arrayOfByte = a(l);
      SecretKeySpec secretKeySpec = new SecretKeySpec(messageDigest.digest(arrayOfByte), "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(1, secretKeySpec);
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s));
      PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(bufferedOutputStream, "UTF-8"));
      printWriter.print(l);
      printWriter.print('\023');
      printWriter.flush();
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new CipherOutputStream(bufferedOutputStream, cipher));
      try {
        objectOutputStream.writeLong(this.m);
        objectOutputStream.writeObject(this.n);
        objectOutputStream.writeObject(this.k);
        objectOutputStream.writeObject(this.l);
        objectOutputStream.writeObject(Boolean.valueOf(this.p));
        objectOutputStream.writeObject(Boolean.valueOf(this.o));
      } finally {
        objectOutputStream.close();
      } 
    } catch (Exception exception) {
      e.log(Level.WARNING, "キーの保存エラー", exception);
    } 
  }
  
  public void f() throws IOException {
    try {
      CloseableHttpClient closeableHttpClient = e();
      try {
        URIBuilder uRIBuilder = new URIBuilder("https://api.i-press.jpn.com/users_api/keys/");
        String str1 = "0123456789";
        uRIBuilder.addParameter("encrypt_key", str1);
        HttpGet httpGet = new HttpGet(uRIBuilder.build());
        httpGet.setHeader("x-authentication-key", this.j);
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute((HttpUriRequest)httpGet);
        JsonObject jsonObject1 = Json.createReader(closeableHttpResponse.getEntity().getContent()).readObject();
        String str2 = jsonObject1.getString("keys");
        byte[] arrayOfByte1 = Base64.decodeBase64(str2);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        byte[] arrayOfByte2 = DigestUtils.md5(str1 + "ipress_enc");
        SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte2, "AES");
        cipher.init(2, secretKeySpec);
        StringBuffer stringBuffer = new StringBuffer();
        arrayOfByte1 = cipher.update(arrayOfByte1);
        stringBuffer.append(new String(arrayOfByte1, "UTF-8"));
        arrayOfByte1 = cipher.doFinal();
        stringBuffer.append(new String(arrayOfByte1, "UTF-8"));
        JsonObject jsonObject2 = Json.createReader(new StringReader(stringBuffer.toString())).readObject();
        ArrayList<b> arrayList = new ArrayList();
        for (JsonValue jsonValue : jsonObject2.getJsonArray("keys")) {
          b b = new b();
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          try {
            b.a(simpleDateFormat.parse(jsonValue.asJsonObject().getString("expire_at")));
          } catch (ParseException parseException) {}
          b.c(jsonValue.asJsonObject().getString("content"));
          arrayList.add(b);
        } 
        this.n = a.a(arrayList);
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
    } catch (IOException iOException) {
      throw iOException;
    } catch (Exception exception) {
      throw new SecurityException();
    } 
  }
  
  public BooleanProperty g() {
    return this.q;
  }
  
  public boolean h() {
    return this.q.get();
  }
  
  public boolean i() {
    return this.p;
  }
  
  public boolean j() {
    return this.o;
  }
  
  public Object a(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte.length >= 1024 && ((paramArrayOfbyte[0] == 73 && paramArrayOfbyte[1] == 71 && paramArrayOfbyte[2] == 69 && paramArrayOfbyte[3] == 70) || (paramArrayOfbyte[0] == 67 && paramArrayOfbyte[1] == 68 && paramArrayOfbyte[2] == 69 && paramArrayOfbyte[3] == 70))) {
      if (paramArrayOfbyte.length != 1024) {
        byte[] arrayOfByte = new byte[1024];
        System.arraycopy(paramArrayOfbyte, 0, arrayOfByte, 0, 1024);
        paramArrayOfbyte = arrayOfByte;
      } 
      int i = b(paramArrayOfbyte);
      if (i != 0)
        return this.h.a(i); 
    } 
    return null;
  }
  
  private int b(byte[] paramArrayOfbyte) {
    if (!this.q.get())
      throw new f(b.a.getString("requireLogin")); 
    byte[] arrayOfByte1 = new byte[12];
    byte[] arrayOfByte2 = new byte[12];
    System.arraycopy(paramArrayOfbyte, 4, arrayOfByte1, 0, 12);
    System.arraycopy(paramArrayOfbyte, 16, arrayOfByte2, 0, 12);
    String str1 = (new String(paramArrayOfbyte, 28, 32)).intern();
    String str2 = a.a(this.n, str1);
    if (str2 != null) {
      int i = this.h.a(str2.getBytes());
      this.h.b(this.h.b());
      return i;
    } 
    return 0;
  }
  
  public void a(byte[] paramArrayOfbyte, Object paramObject, int paramInt1, int paramInt2, int paramInt3) {
    b.a(paramObject, paramArrayOfbyte, paramInt2, paramArrayOfbyte, paramInt1, paramInt2, paramInt3);
  }
  
  public a k() {
    String str1;
    String str2;
    String str3;
    try {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      documentBuilderFactory.setValidating(false);
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      CloseableHttpClient closeableHttpClient = e();
      try {
        boolean bool = SystemUtils.IS_OS_MAC ? true : true;
        HttpPost httpPost = new HttpPost("https://contendo.jp/storeAPI/viewers/");
        httpPost.setHeader("SOAPAction", "http://demomall.contendo.jp/storeAPI/viewers/#chkViewerUpdate");
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://demomall.contendo.jp/storeAPI/viewers/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><SOAP-ENV:Body><ns1:chkViewerUpdate><deviceType xsi:type=\"xsd:string\">" + bool + "</deviceType><version xsi:type=\"xsd:string\">1.6.3</version></ns1:chkViewerUpdate></SOAP-ENV:Body></SOAP-ENV:Envelope>";
        StringEntity stringEntity = new StringEntity(str, ContentType.create("text/xml", "UTF-8"));
        httpPost.setEntity((HttpEntity)stringEntity);
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute((HttpUriRequest)httpPost);
        Document document = documentBuilder.parse(new InputSource(closeableHttpResponse.getEntity().getContent()));
        str1 = XPathAPI.eval(document, "//status/text()").toString();
        str3 = XPathAPI.eval(document, "//errCd/text()").toString();
        str2 = XPathAPI.eval(document, "//message/text()").toString();
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
    } catch (ParserConfigurationException|javax.xml.transform.TransformerException parserConfigurationException) {
      throw new RuntimeException(parserConfigurationException);
    } catch (IOException|SAXException iOException) {
      e.log(Level.WARNING, "更新チェックエラー", iOException);
      return null;
    } 
    if (str1.equals("0"))
      return null; 
    if (str1.equals("1"))
      return new a(false, str2); 
    if (str1.equals("2"))
      return new a(true, str2); 
    e.log(Level.WARNING, "更新チェックエラー: errCd=" + str3);
    return null;
  }
  
  public boolean l() {
    return this.r;
  }
  
  public JsonObject a(boolean paramBoolean) throws IOException {
    if (!this.q.get())
      throw new f(b.a.getString("requireLogin")); 
    if (paramBoolean)
      this.t = null; 
    o();
    f();
    p();
    CloseableHttpClient closeableHttpClient = e();
    try {
      HttpGet httpGet = new HttpGet("https://api.contendo.jp/users_api/items/");
      httpGet.setHeader("x-authentication-key", this.i);
      if (this.t != null)
        httpGet.setHeader("x-search-key", this.t); 
      CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute((HttpUriRequest)httpGet);
      JsonObject jsonObject1 = Json.createReader(closeableHttpResponse.getEntity().getContent()).readObject();
      this.t = jsonObject1.getString("next_search_key");
      JsonObject jsonObject2 = jsonObject1;
      if (closeableHttpClient != null)
        closeableHttpClient.close(); 
      return jsonObject2;
    } catch (Throwable throwable) {
      if (closeableHttpClient != null)
        try {
          closeableHttpClient.close();
        } catch (Throwable throwable1) {
          throwable.addSuppressed(throwable1);
        }  
      throw throwable;
    } 
  }
  
  public String a(String paramString) throws IOException {
    if (!this.q.get())
      throw new f(b.a.getString("requireLogin")); 
    try {
      CloseableHttpClient closeableHttpClient = e();
      try {
        URIBuilder uRIBuilder = new URIBuilder("https://api.contendo.jp/users_api/covers_download/");
        uRIBuilder.addParameter("items_id", paramString);
        HttpGet httpGet = new HttpGet(uRIBuilder.build());
        httpGet.setHeader("x-authentication-key", this.i);
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute((HttpUriRequest)httpGet);
        JsonObject jsonObject = Json.createReader(closeableHttpResponse.getEntity().getContent()).readObject();
        String str = jsonObject.getString("covers_url");
        if (closeableHttpClient != null)
          closeableHttpClient.close(); 
        return str;
      } catch (Throwable throwable) {
        if (closeableHttpClient != null)
          try {
            closeableHttpClient.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          }  
        throw throwable;
      } 
    } catch (URISyntaxException uRISyntaxException) {
      throw new RuntimeException(uRISyntaxException);
    } 
  }
  
  public String m() {
    o();
    return this.i;
  }
  
  static {
    Random random;
  }
  
  static {
    try {
      random = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      random = null;
    } 
    f = random;
  }
  
  public static class a {
    public final boolean a;
    
    public final String b;
    
    public a(boolean param1Boolean, String param1String) {
      this.a = param1Boolean;
      this.b = param1String;
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/b/a/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */