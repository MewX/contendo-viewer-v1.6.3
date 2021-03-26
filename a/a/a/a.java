package a.a.a;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

public abstract class a {
  public static final int a = 5000;
  
  public static final String b = "text/plain";
  
  public static final String c = "text/html";
  
  private static final String d = "NanoHttpd.QUERY_STRING";
  
  private int e;
  
  private ServerSocket f;
  
  private Set<Socket> g = new HashSet<>();
  
  private Thread h;
  
  private a i;
  
  private o j;
  
  public a() {
    a(new g(this));
    a(new d());
  }
  
  private static final void a(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
      } catch (IOException iOException) {} 
  }
  
  private static final void c(Socket paramSocket) {
    if (paramSocket != null)
      try {
        paramSocket.close();
      } catch (IOException iOException) {} 
  }
  
  private static final void a(ServerSocket paramServerSocket) {
    if (paramServerSocket != null)
      try {
        paramServerSocket.close();
      } catch (IOException iOException) {} 
  }
  
  public void a() throws IOException {
    Random random = new Random();
    while (true) {
      try {
        this.f = new ServerSocket();
        this.e = 49152 + random.nextInt(16382);
        this.f.bind(new InetSocketAddress(InetAddress.getByName(null), this.e));
      } catch (Exception exception) {
        continue;
      } 
      this.h = new Thread(new Runnable(this) {
            public void run() {
              do {
                try {
                  Socket socket = this.a.f.accept();
                  this.a.a(socket);
                  socket.setSoTimeout(5000);
                  InputStream inputStream = socket.getInputStream();
                  this.a.i.a(new Runnable(this, socket, inputStream) {
                        public void run() {
                          OutputStream outputStream = null;
                          try {
                            outputStream = this.a.getOutputStream();
                            a.n n = this.c.a.j.a();
                            a.h h = new a.h(this.c.a, n, this.b, outputStream, this.a.getInetAddress());
                            while (!this.a.isClosed())
                              h.a(); 
                          } catch (Exception exception) {
                            if (!(exception instanceof SocketException) || !"NanoHttpd Shutdown".equals(exception.getMessage()))
                              exception.printStackTrace(); 
                          } finally {
                            a.a(outputStream);
                            a.a(this.b);
                            a.c(this.a);
                            this.c.a.b(this.a);
                          } 
                        }
                      });
                } catch (IOException iOException) {}
              } while (!this.a.f.isClosed());
            }
          });
      this.h.setDaemon(true);
      this.h.setName("NanoHttpd Main Listener");
      this.h.start();
      return;
    } 
  }
  
  public void b() {
    try {
      a(this.f);
      c();
      this.h.join();
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public synchronized void a(Socket paramSocket) {
    this.g.add(paramSocket);
  }
  
  public synchronized void b(Socket paramSocket) {
    this.g.remove(paramSocket);
  }
  
  public synchronized void c() {
    for (Socket socket : this.g)
      c(socket); 
  }
  
  public final int d() {
    return (this.f == null) ? -1 : this.f.getLocalPort();
  }
  
  public final boolean e() {
    return (this.f != null && this.h != null);
  }
  
  public final boolean f() {
    return (e() && !this.f.isClosed() && this.h.isAlive());
  }
  
  @Deprecated
  public k a(String paramString, j paramj, Map<String, String> paramMap1, Map<String, String> paramMap2, Map<String, String> paramMap3) {
    return new k(k.a.l, "text/plain", "Not Found");
  }
  
  public k a(i parami) {
    HashMap<Object, Object> hashMap = new HashMap<>();
    j j = parami.f();
    if (j.b.equals(j) || j.c.equals(j))
      try {
        parami.a((Map)hashMap);
      } catch (IOException iOException) {
        return new k(k.a.o, "text/plain", "SERVER INTERNAL ERROR: IOException: " + iOException.getMessage());
      } catch (l l) {
        return new k(l.a(), "text/plain", l.getMessage());
      }  
    Map<String, String> map = parami.b();
    map.put("NanoHttpd.QUERY_STRING", parami.c());
    return a(parami.e(), j, parami.d(), map, (Map)hashMap);
  }
  
  protected String a(String paramString) {
    String str = null;
    try {
      str = URLDecoder.decode(paramString, "UTF8");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {}
    return str;
  }
  
  protected Map<String, List<String>> a(Map<String, String> paramMap) {
    return b(paramMap.get("NanoHttpd.QUERY_STRING"));
  }
  
  protected Map<String, List<String>> b(String paramString) {
    HashMap<Object, Object> hashMap = new HashMap<>();
    if (paramString != null) {
      StringTokenizer stringTokenizer = new StringTokenizer(paramString, "&");
      while (stringTokenizer.hasMoreTokens()) {
        String str1 = stringTokenizer.nextToken();
        int i = str1.indexOf('=');
        String str2 = (i >= 0) ? a(str1.substring(0, i)).trim() : a(str1).trim();
        if (!hashMap.containsKey(str2))
          hashMap.put(str2, new ArrayList()); 
        String str3 = (i >= 0) ? a(str1.substring(i + 1)) : null;
        if (str3 != null)
          ((List<String>)hashMap.get(str2)).add(str3); 
      } 
    } 
    return (Map)hashMap;
  }
  
  public void a(a parama) {
    this.i = parama;
  }
  
  public void a(o paramo) {
    this.j = paramo;
  }
  
  public class c implements Iterable<String> {
    private HashMap<String, String> b = new HashMap<>();
    
    private ArrayList<a.b> c = new ArrayList<>();
    
    public c(a this$0, Map<String, String> param1Map) {
      String str = param1Map.get("cookie");
      if (str != null) {
        String[] arrayOfString = str.split(";");
        for (String str1 : arrayOfString) {
          String[] arrayOfString1 = str1.trim().split("=");
          if (arrayOfString1.length == 2)
            this.b.put(arrayOfString1[0], arrayOfString1[1]); 
        } 
      } 
    }
    
    public Iterator<String> iterator() {
      return this.b.keySet().iterator();
    }
    
    public String a(String param1String) {
      return this.b.get(param1String);
    }
    
    public void a(String param1String1, String param1String2, int param1Int) {
      this.c.add(new a.b(param1String1, param1String2, a.b.a(param1Int)));
    }
    
    public void a(a.b param1b) {
      this.c.add(param1b);
    }
    
    public void b(String param1String) {
      a(param1String, "-delete-", -30);
    }
    
    public void a(a.k param1k) {
      for (a.b b : this.c)
        param1k.a("Set-Cookie", b.a()); 
    }
  }
  
  public static class b {
    private String a;
    
    private String b;
    
    private String c;
    
    public b(String param1String1, String param1String2, String param1String3) {
      this.a = param1String1;
      this.b = param1String2;
      this.c = param1String3;
    }
    
    public b(String param1String1, String param1String2) {
      this(param1String1, param1String2, 30);
    }
    
    public b(String param1String1, String param1String2, int param1Int) {
      this.a = param1String1;
      this.b = param1String2;
      this.c = a(param1Int);
    }
    
    public String a() {
      String str = "%s=%s; expires=%s";
      return String.format(str, new Object[] { this.a, this.b, this.c });
    }
    
    public static String a(int param1Int) {
      Calendar calendar = Calendar.getInstance();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
      simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      calendar.add(5, param1Int);
      return simpleDateFormat.format(calendar.getTime());
    }
  }
  
  protected class h implements i {
    public static final int a = 8192;
    
    private final a.n c;
    
    private final OutputStream d;
    
    private PushbackInputStream e;
    
    private int f;
    
    private int g;
    
    private String h;
    
    private a.j i;
    
    private Map<String, String> j;
    
    private Map<String, String> k;
    
    private a.c l;
    
    private String m;
    
    public h(a this$0, a.n param1n, InputStream param1InputStream, OutputStream param1OutputStream) {
      this.c = param1n;
      this.e = new PushbackInputStream(param1InputStream, 8192);
      this.d = param1OutputStream;
    }
    
    public h(a this$0, a.n param1n, InputStream param1InputStream, OutputStream param1OutputStream, InetAddress param1InetAddress) {
      this.c = param1n;
      this.e = new PushbackInputStream(param1InputStream, 8192);
      this.d = param1OutputStream;
      String str = (param1InetAddress.isLoopbackAddress() || param1InetAddress.isAnyLocalAddress()) ? "127.0.0.1" : param1InetAddress.getHostAddress().toString();
      this.k = new HashMap<>();
      this.k.put("remote-addr", str);
      this.k.put("http-client-ip", str);
    }
    
    public void a() throws IOException {
      try {
        byte[] arrayOfByte = new byte[8192];
        this.f = 0;
        this.g = 0;
        int k = -1;
        try {
          k = this.e.read(arrayOfByte, 0, 8192);
        } catch (Exception exception) {
          a.a(this.e);
          a.a(this.d);
          throw new SocketException("NanoHttpd Shutdown");
        } 
        if (k == -1) {
          a.a(this.e);
          a.a(this.d);
          throw new SocketException("NanoHttpd Shutdown");
        } 
        while (k > 0) {
          this.g += k;
          this.f = a(arrayOfByte, this.g);
          if (this.f > 0)
            break; 
          k = this.e.read(arrayOfByte, this.g, 8192 - this.g);
        } 
        if (this.f < this.g)
          this.e.unread(arrayOfByte, this.f, this.g - this.f); 
        this.j = new HashMap<>();
        if (null == this.k)
          this.k = new HashMap<>(); 
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(arrayOfByte, 0, this.g)));
        HashMap<Object, Object> hashMap = new HashMap<>();
        a(bufferedReader, (Map)hashMap, this.j, this.k);
        this.i = a.j.b((String)hashMap.get("method"));
        if (this.i == null)
          throw new a.l(a.k.a.i, "BAD REQUEST: Syntax error."); 
        this.h = (String)hashMap.get("uri");
        this.l = new a.c(this.b, this.k);
        a.k k1 = this.b.a(this);
        if (k1 == null)
          throw new a.l(a.k.a.o, "SERVER INTERNAL ERROR: Serve() returned a null response."); 
        this.l.a(k1);
        k1.a(this.i);
        k1.a(this.d);
      } catch (SocketException socketException) {
        throw socketException;
      } catch (SocketTimeoutException socketTimeoutException) {
        throw socketTimeoutException;
      } catch (IOException iOException) {
        a.k k = new a.k(a.k.a.o, "text/plain", "SERVER INTERNAL ERROR: IOException: " + iOException.getMessage());
        k.a(this.d);
        a.a(this.d);
      } catch (l l) {
        a.k k = new a.k(l.a(), "text/plain", l.getMessage());
        k.a(this.d);
        a.a(this.d);
      } finally {
        this.c.b();
      } 
    }
    
    public void a(Map<String, String> param1Map) throws IOException, a.l {
      RandomAccessFile randomAccessFile = null;
      BufferedReader bufferedReader = null;
      try {
        long l;
        randomAccessFile = i();
        if (this.k.containsKey("Content-Length")) {
          l = Integer.parseInt(this.k.get("Content-Length"));
        } else if (this.f < this.g) {
          l = (this.g - this.f);
        } else {
          l = 0L;
        } 
        byte[] arrayOfByte = new byte[512];
        while (this.g >= 0 && l > 0L) {
          this.g = this.e.read(arrayOfByte, 0, (int)Math.min(l, 512L));
          l -= this.g;
          if (this.g > 0)
            randomAccessFile.write(arrayOfByte, 0, this.g); 
        } 
        MappedByteBuffer mappedByteBuffer = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile.length());
        randomAccessFile.seek(0L);
        FileInputStream fileInputStream = new FileInputStream(randomAccessFile.getFD());
        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        if (a.j.c.equals(this.i)) {
          String str1 = "";
          String str2 = this.k.get("content-type");
          StringTokenizer stringTokenizer = null;
          if (str2 != null) {
            stringTokenizer = new StringTokenizer(str2, ",; ");
            if (stringTokenizer.hasMoreTokens())
              str1 = stringTokenizer.nextToken(); 
          } 
          if ("multipart/form-data".equalsIgnoreCase(str1)) {
            if (!stringTokenizer.hasMoreTokens())
              throw new a.l(a.k.a.i, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html"); 
            String str3 = "boundary=";
            int k = str2.indexOf(str3) + str3.length();
            String str4 = str2.substring(k, str2.length());
            if (str4.startsWith("\"") && str4.endsWith("\""))
              str4 = str4.substring(1, str4.length() - 1); 
            a(str4, mappedByteBuffer, bufferedReader, this.j, param1Map);
          } else {
            String str = "";
            StringBuilder stringBuilder = new StringBuilder();
            char[] arrayOfChar = new char[512];
            int k;
            for (k = bufferedReader.read(arrayOfChar); k >= 0 && !str.endsWith("\r\n"); k = bufferedReader.read(arrayOfChar)) {
              str = String.valueOf(arrayOfChar, 0, k);
              stringBuilder.append(str);
            } 
            str = stringBuilder.toString().trim();
            a(str, this.j);
          } 
        } else if (a.j.b.equals(this.i)) {
          param1Map.put("content", a(mappedByteBuffer, 0, mappedByteBuffer.limit()));
        } 
      } finally {
        a.a(randomAccessFile);
        a.a(bufferedReader);
      } 
    }
    
    private void a(BufferedReader param1BufferedReader, Map<String, String> param1Map1, Map<String, String> param1Map2, Map<String, String> param1Map3) throws a.l {
      try {
        String str1 = param1BufferedReader.readLine();
        if (str1 == null)
          return; 
        StringTokenizer stringTokenizer = new StringTokenizer(str1);
        if (!stringTokenizer.hasMoreTokens())
          throw new a.l(a.k.a.i, "BAD REQUEST: Syntax error. Usage: GET /example/file.html"); 
        param1Map1.put("method", stringTokenizer.nextToken());
        if (!stringTokenizer.hasMoreTokens())
          throw new a.l(a.k.a.i, "BAD REQUEST: Missing URI. Usage: GET /example/file.html"); 
        String str2 = stringTokenizer.nextToken();
        int k = str2.indexOf('?');
        if (k >= 0) {
          a(str2.substring(k + 1), param1Map2);
          str2 = this.b.a(str2.substring(0, k));
        } else {
          str2 = this.b.a(str2);
        } 
        if (stringTokenizer.hasMoreTokens())
          for (String str = param1BufferedReader.readLine(); str != null && str.trim().length() > 0; str = param1BufferedReader.readLine()) {
            int m = str.indexOf(':');
            if (m >= 0)
              param1Map3.put(str.substring(0, m).trim().toLowerCase(Locale.US), str.substring(m + 1).trim()); 
          }  
        param1Map1.put("uri", str2);
      } catch (IOException iOException) {
        throw new a.l(a.k.a.o, "SERVER INTERNAL ERROR: IOException: " + iOException.getMessage(), iOException);
      } 
    }
    
    private void a(String param1String, ByteBuffer param1ByteBuffer, BufferedReader param1BufferedReader, Map<String, String> param1Map1, Map<String, String> param1Map2) throws a.l {
      try {
        int[] arrayOfInt = a(param1ByteBuffer, param1String.getBytes());
        byte b = 1;
        String str = param1BufferedReader.readLine();
        while (str != null) {
          if (!str.contains(param1String))
            throw new a.l(a.k.a.i, "BAD REQUEST: Content type is multipart/form-data but next chunk does not start with boundary. Usage: GET /example/file.html"); 
          b++;
          HashMap<Object, Object> hashMap = new HashMap<>();
          for (str = param1BufferedReader.readLine(); str != null && str.trim().length() > 0; str = param1BufferedReader.readLine()) {
            int k = str.indexOf(':');
            if (k != -1)
              hashMap.put(str.substring(0, k).trim().toLowerCase(Locale.US), str.substring(k + 1).trim()); 
          } 
          if (str != null) {
            String str1 = (String)hashMap.get("content-disposition");
            if (str1 == null)
              throw new a.l(a.k.a.i, "BAD REQUEST: Content type is multipart/form-data but no content-disposition info found. Usage: GET /example/file.html"); 
            StringTokenizer stringTokenizer = new StringTokenizer(str1, "; ");
            HashMap<Object, Object> hashMap1 = new HashMap<>();
            while (stringTokenizer.hasMoreTokens()) {
              String str4 = stringTokenizer.nextToken();
              int k = str4.indexOf('=');
              if (k != -1)
                hashMap1.put(str4.substring(0, k).trim().toLowerCase(Locale.US), str4.substring(k + 1).trim()); 
            } 
            String str2 = (String)hashMap1.get("name");
            str2 = str2.substring(1, str2.length() - 1);
            String str3 = "";
            if (hashMap.get("content-type") == null) {
              while (str != null && !str.contains(param1String)) {
                str = param1BufferedReader.readLine();
                if (str != null) {
                  int k = str.indexOf(param1String);
                  if (k == -1) {
                    str3 = str3 + str3;
                    continue;
                  } 
                  str3 = str3 + str3;
                } 
              } 
            } else {
              if (b > arrayOfInt.length)
                throw new a.l(a.k.a.o, "Error processing request"); 
              int k = a(param1ByteBuffer, arrayOfInt[b - 2]);
              String str4 = a(param1ByteBuffer, k, arrayOfInt[b - 1] - k - 4);
              param1Map2.put(str2, str4);
              str3 = (String)hashMap1.get("filename");
              str3 = str3.substring(1, str3.length() - 1);
              do {
                str = param1BufferedReader.readLine();
              } while (str != null && !str.contains(param1String));
            } 
            param1Map1.put(str2, str3);
          } 
        } 
      } catch (IOException iOException) {
        throw new a.l(a.k.a.o, "SERVER INTERNAL ERROR: IOException: " + iOException.getMessage(), iOException);
      } 
    }
    
    private int a(byte[] param1ArrayOfbyte, int param1Int) {
      for (byte b = 0; b + 3 < param1Int; b++) {
        if (param1ArrayOfbyte[b] == 13 && param1ArrayOfbyte[b + 1] == 10 && param1ArrayOfbyte[b + 2] == 13 && param1ArrayOfbyte[b + 3] == 10)
          return b + 4; 
      } 
      return 0;
    }
    
    private int[] a(ByteBuffer param1ByteBuffer, byte[] param1ArrayOfbyte) {
      byte b1 = 0;
      int k = -1;
      ArrayList<Integer> arrayList = new ArrayList();
      for (int m = 0; m < param1ByteBuffer.limit(); m++) {
        if (param1ByteBuffer.get(m) == param1ArrayOfbyte[b1]) {
          if (!b1)
            k = m; 
          if (++b1 == param1ArrayOfbyte.length) {
            arrayList.add(Integer.valueOf(k));
            b1 = 0;
            k = -1;
          } 
        } else {
          m -= b1;
          b1 = 0;
          k = -1;
        } 
      } 
      int[] arrayOfInt = new int[arrayList.size()];
      for (byte b2 = 0; b2 < arrayOfInt.length; b2++)
        arrayOfInt[b2] = ((Integer)arrayList.get(b2)).intValue(); 
      return arrayOfInt;
    }
    
    private String a(ByteBuffer param1ByteBuffer, int param1Int1, int param1Int2) {
      String str = "";
      if (param1Int2 > 0) {
        FileOutputStream fileOutputStream = null;
        try {
          a.m m = this.c.a();
          ByteBuffer byteBuffer = param1ByteBuffer.duplicate();
          fileOutputStream = new FileOutputStream(m.c());
          FileChannel fileChannel = fileOutputStream.getChannel();
          byteBuffer.position(param1Int1).limit(param1Int1 + param1Int2);
          fileChannel.write(byteBuffer.slice());
          str = m.c();
        } catch (Exception exception) {
          System.err.println("Error: " + exception.getMessage());
        } finally {
          a.a(fileOutputStream);
        } 
      } 
      return str;
    }
    
    private RandomAccessFile i() {
      try {
        a.m m = this.c.a();
        return new RandomAccessFile(m.c(), "rw");
      } catch (Exception exception) {
        System.err.println("Error: " + exception.getMessage());
        return null;
      } 
    }
    
    private int a(ByteBuffer param1ByteBuffer, int param1Int) {
      int k;
      for (k = param1Int; k < param1ByteBuffer.limit() && (param1ByteBuffer.get(k) != 13 || param1ByteBuffer.get(++k) != 10 || param1ByteBuffer.get(++k) != 13 || param1ByteBuffer.get(++k) != 10); k++);
      return k + 1;
    }
    
    private void a(String param1String, Map<String, String> param1Map) {
      if (param1String == null) {
        this.m = "";
        return;
      } 
      this.m = param1String;
      StringTokenizer stringTokenizer = new StringTokenizer(param1String, "&");
      while (stringTokenizer.hasMoreTokens()) {
        String str = stringTokenizer.nextToken();
        int k = str.indexOf('=');
        if (k >= 0) {
          param1Map.put(this.b.a(str.substring(0, k)).trim(), this.b.a(str.substring(k + 1)));
          continue;
        } 
        param1Map.put(this.b.a(str).trim(), "");
      } 
    }
    
    public final Map<String, String> b() {
      return this.j;
    }
    
    public String c() {
      return this.m;
    }
    
    public final Map<String, String> d() {
      return this.k;
    }
    
    public final String e() {
      return this.h;
    }
    
    public final a.j f() {
      return this.i;
    }
    
    public final InputStream g() {
      return this.e;
    }
    
    public a.c h() {
      return this.l;
    }
  }
  
  public static interface i {
    void a() throws IOException;
    
    Map<String, String> b();
    
    Map<String, String> d();
    
    String e();
    
    String c();
    
    a.j f();
    
    InputStream g();
    
    a.c h();
    
    void a(Map<String, String> param1Map) throws IOException, a.l;
  }
  
  private class g implements o {
    private g(a this$0) {}
    
    public a.n a() {
      return new a.f();
    }
  }
  
  public static final class l extends Exception {
    private static final long a = 1L;
    
    private final a.k.a b;
    
    public l(a.k.a param1a, String param1String) {
      super(param1String);
      this.b = param1a;
    }
    
    public l(a.k.a param1a, String param1String, Exception param1Exception) {
      super(param1String, param1Exception);
      this.b = param1a;
    }
    
    public a.k.a a() {
      return this.b;
    }
  }
  
  public static class k {
    private a a;
    
    private String b;
    
    private InputStream c;
    
    private Map<String, String> d = new HashMap<>();
    
    private a.j e;
    
    private boolean f;
    
    public k(String param1String) {
      this(a.b, "text/html", param1String);
    }
    
    public k(a param1a, String param1String, InputStream param1InputStream) {
      this.a = param1a;
      this.b = param1String;
      this.c = param1InputStream;
    }
    
    public k(a param1a, String param1String1, String param1String2) {
      this.a = param1a;
      this.b = param1String1;
      try {
        this.c = (param1String2 != null) ? new ByteArrayInputStream(param1String2.getBytes("UTF-8")) : null;
      } catch (UnsupportedEncodingException unsupportedEncodingException) {
        unsupportedEncodingException.printStackTrace();
      } 
    }
    
    public void a(String param1String1, String param1String2) {
      this.d.put(param1String1, param1String2);
    }
    
    protected void a(OutputStream param1OutputStream) {
      String str = this.b;
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
      simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      try {
        if (this.a == null)
          throw new Error("sendResponse(): Status can't be null."); 
        PrintWriter printWriter = new PrintWriter(param1OutputStream);
        printWriter.print("HTTP/1.1 " + this.a.c() + " \r\n");
        if (str != null)
          printWriter.print("Content-Type: " + str + "\r\n"); 
        if (this.d == null || this.d.get("Date") == null)
          printWriter.print("Date: " + simpleDateFormat.format(new Date()) + "\r\n"); 
        if (this.d != null)
          for (String str1 : this.d.keySet()) {
            String str2 = this.d.get(str1);
            printWriter.print(str1 + ": " + str1 + "\r\n");
          }  
        a(printWriter, this.d);
        if (this.e != a.j.e && this.f) {
          a(param1OutputStream, printWriter);
        } else {
          b(param1OutputStream, printWriter);
        } 
        param1OutputStream.flush();
        a.a(this.c);
      } catch (IOException iOException) {}
    }
    
    protected void a(PrintWriter param1PrintWriter, Map<String, String> param1Map) {
      boolean bool = false;
      for (String str : param1Map.keySet())
        bool |= str.equalsIgnoreCase("connection"); 
      if (!bool)
        param1PrintWriter.print("Connection: keep-alive\r\n"); 
    }
    
    private void a(OutputStream param1OutputStream, PrintWriter param1PrintWriter) throws IOException {
      param1PrintWriter.print("Transfer-Encoding: chunked\r\n");
      param1PrintWriter.print("\r\n");
      param1PrintWriter.flush();
      char c = '䀀';
      byte[] arrayOfByte1 = "\r\n".getBytes();
      byte[] arrayOfByte2 = new byte[c];
      int i;
      while ((i = this.c.read(arrayOfByte2)) != -1) {
        param1OutputStream.write(String.format("%x\r\n", new Object[] { Integer.valueOf(i) }).getBytes());
        param1OutputStream.write(arrayOfByte2, 0, i);
        param1OutputStream.write(arrayOfByte1);
      } 
      param1OutputStream.write(String.format("0\r\n\r\n", new Object[0]).getBytes());
    }
    
    private void b(OutputStream param1OutputStream, PrintWriter param1PrintWriter) throws IOException {
      int i = (this.c != null) ? this.c.available() : 0;
      if (!this.d.containsKey("Content-Length"))
        param1PrintWriter.print("Content-Length: " + i + "\r\n"); 
      param1PrintWriter.print("\r\n");
      param1PrintWriter.flush();
      if (this.e != a.j.e && this.c != null) {
        char c = '䀀';
        byte[] arrayOfByte = new byte[c];
        while (i) {
          int m = this.c.read(arrayOfByte, 0, (i > c) ? c : i);
          if (m == -1)
            break; 
          param1OutputStream.write(arrayOfByte, 0, m);
          i -= m;
        } 
      } 
    }
    
    public a a() {
      return this.a;
    }
    
    public void a(a param1a) {
      this.a = param1a;
    }
    
    public String b() {
      return this.b;
    }
    
    public void a(String param1String) {
      this.b = param1String;
    }
    
    public InputStream c() {
      return this.c;
    }
    
    public void a(InputStream param1InputStream) {
      this.c = param1InputStream;
    }
    
    public a.j d() {
      return this.e;
    }
    
    public void a(a.j param1j) {
      this.e = param1j;
    }
    
    public void a(boolean param1Boolean) {
      this.f = param1Boolean;
    }
    
    public enum a {
      a(101, "Switching Protocols"),
      b(200, "OK"),
      c(201, "Created"),
      d(202, "Accepted"),
      e(204, "No Content"),
      f(206, "Partial Content"),
      g(301, "Moved Permanently"),
      h(304, "Not Modified"),
      i(400, "Bad Request"),
      j(401, "Unauthorized"),
      k(403, "Forbidden"),
      l(404, "Not Found"),
      m(405, "Method Not Allowed"),
      n(416, "Requested Range Not Satisfiable"),
      o(500, "Internal Server Error");
      
      private final int p;
      
      private final String q;
      
      public static a[] a() {
        return (a[])r.clone();
      }
      
      public static a a(String param2String) {
        return Enum.<a>valueOf(a.class, param2String);
      }
      
      a(int param2Int1, String param2String1) {
        this.p = param2Int1;
        this.q = param2String1;
      }
      
      public int b() {
        return this.p;
      }
      
      public String c() {
        return "" + this.p + " " + this.p;
      }
    }
  }
  
  public static class e implements m {
    private File a;
    
    private OutputStream b;
    
    public e(String param1String) throws IOException {
      this.a = File.createTempFile("NanoHTTPD-", "", new File(param1String));
      this.b = new FileOutputStream(this.a);
    }
    
    public OutputStream a() throws Exception {
      return this.b;
    }
    
    public void b() throws Exception {
      a.a(this.b);
      this.a.delete();
    }
    
    public String c() {
      return this.a.getAbsolutePath();
    }
  }
  
  public static class f implements n {
    private final String a = System.getProperty("java.io.tmpdir");
    
    private final List<a.m> b = new ArrayList<>();
    
    public a.m a() throws Exception {
      a.e e = new a.e(this.a);
      this.b.add(e);
      return e;
    }
    
    public void b() {
      for (a.m m : this.b) {
        try {
          m.b();
        } catch (Exception exception) {}
      } 
      this.b.clear();
    }
  }
  
  public static class d implements a {
    private long a;
    
    public void a(Runnable param1Runnable) {
      this.a++;
      Thread thread = new Thread(param1Runnable);
      thread.setDaemon(true);
      thread.setName("NanoHttpd Request Processor (#" + this.a + ")");
      thread.start();
    }
  }
  
  public static interface m {
    OutputStream a() throws Exception;
    
    void b() throws Exception;
    
    String c();
  }
  
  public static interface n {
    a.m a() throws Exception;
    
    void b();
  }
  
  public static interface o {
    a.n a();
  }
  
  public static interface a {
    void a(Runnable param1Runnable);
  }
  
  public enum j {
    a, b, c, d, e, f;
    
    public static j[] a() {
      return (j[])g.clone();
    }
    
    public static j a(String param1String) {
      return Enum.<j>valueOf(j.class, param1String);
    }
    
    static j b(String param1String) {
      for (j j1 : a()) {
        if (j1.toString().equalsIgnoreCase(param1String))
          return j1; 
      } 
      return null;
    }
  }
  
  public enum a {
    a(101, "Switching Protocols"),
    b(200, "OK"),
    c(201, "Created"),
    d(202, "Accepted"),
    e(204, "No Content"),
    f(206, "Partial Content"),
    g(301, "Moved Permanently"),
    h(304, "Not Modified"),
    i(400, "Bad Request"),
    j(401, "Unauthorized"),
    k(403, "Forbidden"),
    l(404, "Not Found"),
    m(405, "Method Not Allowed"),
    n(416, "Requested Range Not Satisfiable"),
    o(500, "Internal Server Error");
    
    private final int p;
    
    private final String q;
    
    public static a[] a() {
      return (a[])r.clone();
    }
    
    public static a a(String param1String) {
      return Enum.<a>valueOf(a.class, param1String);
    }
    
    a(int param1Int1, String param1String1) {
      this.p = param1Int1;
      this.q = param1String1;
    }
    
    public int b() {
      return this.p;
    }
    
    public String c() {
      return "" + this.p + " " + this.p;
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/a/a/a/a.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */