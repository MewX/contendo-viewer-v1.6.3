/*     */ package com.a.a.j.d;
/*     */ 
/*     */ import com.a.a.j.c.h;
/*     */ import com.a.a.j.d.a.a;
/*     */ import com.a.a.j.d.a.b;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.net.URI;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class f
/*     */   extends j
/*     */ {
/*     */   static class b
/*     */   {
/*     */     private static final String b = "THIS_STRING_SEPARATES";
/* 603 */     private static Logger c = LoggerFactory.getLogger(b.class);
/* 604 */     private String d = null;
/* 605 */     private LinkedList<String> e = new LinkedList<String>();
/* 606 */     private HashMap<String, String> f = new HashMap<String, String>();
/* 607 */     e a = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(String status) {
/* 613 */       this.d = status;
/*     */     }
/*     */     
/*     */     public String b(String key) {
/* 617 */       return this.f.get(key);
/*     */     }
/*     */     
/*     */     public void a(String key, String value) {
/* 621 */       String old = this.f.put(key, value);
/* 622 */       if (old != null && !old.equals(value)) {
/* 623 */         this.e.remove(key);
/*     */       }
/* 625 */       this.e.add(key);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(e range) throws IOException {
/* 636 */       this.a = range;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(OutputStream out) throws IOException {
/* 645 */       PrintStream ps = new PrintStream(this, out) {
/* 646 */           StringBuilder a = new StringBuilder();
/* 647 */           boolean b = f.b.a().isDebugEnabled();
/*     */ 
/*     */           
/*     */           public void print(String s) {
/* 651 */             if (this.b) this.a.append(s); 
/* 652 */             super.print(s);
/*     */           }
/*     */ 
/*     */           
/*     */           public synchronized void println() {
/* 657 */             if (this.b && f.i) {
/* 658 */               f.b.a().debug("response = " + this.a.toString());
/* 659 */               this.a = new StringBuilder();
/*     */             } 
/* 661 */             super.print("\r\n");
/*     */           }
/*     */ 
/*     */           
/*     */           public synchronized void println(String x) {
/* 666 */             print(x);
/* 667 */             println();
/*     */           }
/*     */         };
/*     */       
/* 671 */       this.f.put("MIME-Version", "1.1");
/* 672 */       this.f.put("Date", f.aa.format(new Date()));
/* 673 */       this.f.put("Accept-Ranges", "bytes");
/*     */ 
/*     */       
/* 676 */       this.f.put("Cache-Control", "no-store");
/* 677 */       this.f.put("Pragma", "no-cache");
/* 678 */       this.f.put("Expire", "-1");
/*     */       
/* 680 */       String multiPartType = null;
/* 681 */       if (this.a != null) {
/* 682 */         String contentLength = String.valueOf(this.a.e());
/* 683 */         switch (this.a.g()) {
/*     */           case 0:
/* 685 */             a("HTTP/1.1 200 OK");
/* 686 */             a("Content-Length", contentLength);
/* 687 */             if (this.a.b()) {
/* 688 */               a("Content-Encoding", this.a.c());
/*     */             }
/*     */             break;
/*     */           case 1:
/* 692 */             a("HTTP/1.1 206 Partial content");
/* 693 */             a("Content-Range", "bytes " + this.a.toString() + "/" + String.valueOf(this.a.d()));
/* 694 */             a("Content-Length", contentLength);
/*     */             break;
/*     */           default:
/* 697 */             multiPartType = b("Content-Type");
/* 698 */             a("HTTP/1.1 206 Partial content");
/* 699 */             a("Content-Type", null);
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/* 705 */       ps.println(this.d); byte b1; int i; String[] arrayOfString;
/* 706 */       for (i = (arrayOfString = f.Z).length, b1 = 0; b1 < i; ) { String key = arrayOfString[b1];
/* 707 */         String val = this.f.remove(key);
/* 708 */         if (val != null)
/* 709 */           ps.println(String.format("%s: %s", new Object[] { key, val })); 
/*     */         b1++; }
/*     */       
/* 712 */       for (String key : this.e) {
/* 713 */         String val = this.f.get(key);
/* 714 */         if (val != null) {
/* 715 */           ps.println(String.format("%s: %s", new Object[] { key, val }));
/*     */         }
/*     */       } 
/* 718 */       if (multiPartType != null) {
/* 719 */         ps.println(String.format("%s: %s", new Object[] { "Content-Type", "multipart/byteranges; boundary=THIS_STRING_SEPARATES" }));
/*     */       }
/* 721 */       ps.println();
/* 722 */       ps.flush();
/*     */ 
/*     */       
/* 725 */       if (this.a != null) {
/* 726 */         if (multiPartType == null) {
/* 727 */           InputStream is = this.a.h();
/*     */           try {
/* 729 */             com.a.a.b.b.b.a(is, ps);
/*     */           } finally {
/* 731 */             com.a.a.b.b.b.a(is);
/*     */           } 
/*     */         } else {
/* 734 */           String totalLength = String.valueOf(this.a.d());
/* 735 */           a(ps);
/* 736 */           for (e.a r : this.a.f()) {
/* 737 */             ps.println();
/* 738 */             ps.print("Content-Type");
/* 739 */             ps.print(": ");
/* 740 */             ps.println(multiPartType);
/* 741 */             ps.print("Content-Range");
/* 742 */             ps.print(": bytes ");
/* 743 */             ps.print(r.b());
/* 744 */             ps.print("/");
/* 745 */             ps.println(totalLength);
/* 746 */             ps.println();
/* 747 */             ps.flush();
/*     */             
/* 749 */             InputStream is = this.a.h();
/*     */             try {
/* 751 */               com.a.a.b.b.b.a(is, ps);
/*     */             } finally {
/* 753 */               com.a.a.b.b.b.a(is);
/*     */             } 
/* 755 */             a(ps);
/*     */           } 
/*     */         } 
/* 758 */         this.a = null;
/* 759 */         ps.flush();
/*     */       } 
/*     */     }
/*     */     
/*     */     void a(PrintStream ps) {
/* 764 */       ps.print("--");
/* 765 */       ps.println("THIS_STRING_SEPARATES");
/*     */     }
/*     */   }
/*     */   private static final String d = f.class.getSimpleName();
/*     */   private static final String e = "_HttpContentServer_session";
/*     */   public static final String a = "?passport=";
/*     */   private static final int ak = "?passport=".length();
/*     */   private static final String al = "HTTP/";
/*     */   private static final int am = 100;
/*     */   private static final int an = 0;
/*     */   private static final int ao = 30000;
/*     */   private static final int ap = 8192;
/*     */   private static final int aq = 3;
/*     */   protected ServerSocket b = null;
/*     */   static final Logger c = LoggerFactory.getLogger(f.class);
/*     */   private String ar = "HOGEHOGE";
/*     */   private final LinkedList<a> as = new LinkedList<a>();
/*     */   private final ExecutorService at = Executors.newCachedThreadPool();
/*     */   
/*     */   static class c extends Thread {
/*     */     public void run() {
/*     */       j.b[] sessions;
/*     */       synchronized (f.ac) {
/*     */         sessions = new j.b[f.ac.size()];
/*     */         int i = 0;
/*     */         for (j.b s : f.ac.values())
/*     */           sessions[i++] = s; 
/*     */       } 
/*     */       int count = sessions.length;
/*     */       long current = System.currentTimeMillis();
/*     */       if (count > 0) {
/*     */         for (int i = 0; i < count; i++);
/*     */         long l = (sessions[0]).d;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public f(d resolver) throws IOException {
/*     */     this(resolver, 0, (b)null);
/*     */   }
/*     */   
/*     */   public f(d resolver, int port) throws IOException {
/*     */     this(resolver, port, (b)null);
/*     */   }
/*     */   
/*     */   public f(d resolver, int port, b authorizer) throws IOException {
/*     */     super(resolver, port, authorizer);
/*     */     this.ar = "Basic realm=\"" + this.ar + "\"";
/*     */     this.b = a(this.ai);
/*     */   }
/*     */   
/*     */   protected ServerSocket a(int port) throws IOException {
/*     */     return new ServerSocket(port);
/*     */   }
/*     */   
/*     */   public int a() {
/*     */     return (this.b != null) ? this.b.getLocalPort() : -1;
/*     */   }
/*     */   
/*     */   public URI b() {
/*     */     return URI.create(String.valueOf(c()) + "://" + d() + ":" + a() + "/");
/*     */   }
/*     */   
/*     */   public String c() {
/*     */     return "http";
/*     */   }
/*     */   
/*     */   public String d() {
/*     */     return "127.0.0.1";
/*     */   }
/*     */   
/*     */   public String e() {
/*     */     return this.ar;
/*     */   }
/*     */   
/*     */   public Object a(String otp) {
/*     */     j.a pass = new j.a();
/*     */     String auth = (this.ah == null) ? otp : this.ah.a(otp);
/*     */     if (auth != null) {
/*     */       synchronized (ad) {
/*     */         ad.put(pass, auth);
/*     */       } 
/*     */     } else {
/*     */       c.warn("obtainPassport() 認証NG");
/*     */     } 
/*     */     return pass;
/*     */   }
/*     */   
/*     */   public void f() throws Exception {
/*     */     try {
/*     */       while (!interrupted()) {
/*     */         Socket _sock = this.b.accept();
/*     */         if (i)
/*     */           c.info("接続されました(" + a() + ") : Remote=" + _sock.getRemoteSocketAddress()); 
/*     */         a(_sock);
/*     */       } 
/*     */     } finally {
/*     */       this.b = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(Socket sock) throws IOException {
/*     */     a thread = null;
/*     */     if (!this.as.isEmpty())
/*     */       synchronized (this.as) {
/*     */         if (!this.as.isEmpty())
/*     */           thread = this.as.removeFirst(); 
/*     */       }  
/*     */     if (thread == null)
/*     */       thread = new a(); 
/*     */     thread.a(this, sock);
/*     */     this.at.execute(thread);
/*     */   }
/*     */   
/*     */   protected void a(a thread) {
/*     */     thread.a();
/*     */     if (this.as.size() < 3)
/*     */       synchronized (this.as) {
/*     */         this.as.add(thread);
/*     */       }  
/*     */   }
/*     */   
/*     */   protected a g() {
/*     */     return new a();
/*     */   }
/*     */   
/*     */   public synchronized void start() {
/*     */     c.info("Thread start");
/*     */     super.start();
/*     */   }
/*     */   
/*     */   static class a implements Runnable {
/*     */     f a = null;
/*     */     Socket b = null;
/*     */     BufferedReader c = null;
/*     */     OutputStream d = null;
/*     */     private final HashMap<String, String> g = new HashMap<String, String>();
/*     */     private String h = null;
/*     */     private String i = null;
/*     */     private String j = null;
/*     */     int e = 0;
/*     */     boolean f = false;
/*     */     private final HashMap<String, String> k = new HashMap<String, String>();
/*     */     
/*     */     public void a(f svr, Socket sock) throws IOException {
/*     */       try {
/*     */         this.a = svr;
/*     */         this.b = sock;
/*     */         this.c = new BufferedReader(new InputStreamReader(sock.getInputStream()), 8192);
/*     */         this.d = new BufferedOutputStream(sock.getOutputStream(), 8192);
/*     */       } catch (IOException e) {
/*     */         a();
/*     */         throw e;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void a() {
/*     */       this.g.clear();
/*     */       this.k.clear();
/*     */       this.h = null;
/*     */       this.i = null;
/*     */       this.j = null;
/*     */       this.e = 0;
/*     */       this.f = false;
/*     */       if (this.d != null) {
/*     */         com.a.a.b.b.b.a(this.d);
/*     */         this.d = null;
/*     */       } 
/*     */       if (this.c != null) {
/*     */         com.a.a.b.b.b.a(this.c);
/*     */         this.c = null;
/*     */       } 
/*     */       if (this.b != null) {
/*     */         try {
/*     */           this.b.close();
/*     */         } catch (IOException iOException) {}
/*     */         this.b = null;
/*     */       } 
/*     */       this.a = null;
/*     */     }
/*     */     
/*     */     public void run() {
/*     */       try {
/*     */         j.b session = null;
/*     */         Boolean first = Boolean.valueOf(true);
/*     */         Boolean keep_alive = Boolean.valueOf(true);
/*     */         this.e = Math.max(0, 1);
/*     */         while (keep_alive.booleanValue() && this.e > 0) {
/*     */           long currenttime = System.currentTimeMillis();
/*     */           this.k.clear();
/*     */           b();
/*     */           if (this.h == null)
/*     */             break; 
/*     */           String value = this.g.get("Cookie");
/*     */           if (f.i)
/*     */             f.c.debug("Cookie = " + value); 
/*     */           if (value != null) {
/*     */             String[] wk = value.split("[;,]");
/*     */             byte b;
/*     */             int i;
/*     */             String[] arrayOfString1;
/*     */             for (i = (arrayOfString1 = wk).length, b = 0; b < i; ) {
/*     */               String s = arrayOfString1[b];
/*     */               int n = s.indexOf("=");
/*     */               String k = s.substring(0, n).trim().intern();
/*     */               String v = s.substring(n + 1).trim().intern();
/*     */               this.k.put(k, v);
/*     */               b++;
/*     */             } 
/*     */           } 
/*     */           if (first.booleanValue()) {
/*     */             first = Boolean.valueOf(false);
/*     */             value = this.g.get("Connection");
/*     */             if (this.i.compareTo("1.1") >= 0) {
/*     */               if (value != null && value.toLowerCase().indexOf("close") >= 0)
/*     */                 keep_alive = Boolean.valueOf(false); 
/*     */             } else {
/*     */               keep_alive = Boolean.valueOf(false);
/*     */             } 
/*     */           } 
/*     */           value = this.g.get("Accept-Encoding");
/*     */           if (value != null)
/*     */             this.f = (value.indexOf("gzip") >= 0); 
/*     */           this.j = this.g.get("Referer");
/*     */           f.b response = new f.b();
/*     */           this.e--;
/*     */           response.a("Keep-Alive", String.format("timeout=%d, max=%d", new Object[] { Integer.valueOf(100), Integer.valueOf(this.e) }));
/*     */           if (keep_alive.booleanValue() && this.e > 0) {
/*     */             response.a("Connection", "Keep-Alive");
/*     */           } else {
/*     */             response.a("Connection", "close");
/*     */           } 
/*     */           String agent = this.g.get("User-Agent");
/*     */           if (!this.k.isEmpty()) {
/*     */             String key = this.k.get("_HttpContentServer_session");
/*     */             if (key != null) {
/*     */               synchronized (f.ac) {
/*     */                 session = f.ac.get(key);
/*     */                 if (session != null) {
/*     */                   long timeout = this.a.j();
/*     */                   if (timeout > 0L && currenttime > session.d) {
/*     */                     if (f.i)
/*     */                       f.c.warn("期限切れ"); 
/*     */                     f.ac.remove(key);
/*     */                     if (session.g != null)
/*     */                       session.g.clear(); 
/*     */                     session = null;
/*     */                   } else if (!agent.equals(session.b)) {
/*     */                     if (f.i)
/*     */                       f.c.warn("Agentが異なります"); 
/*     */                     session = null;
/*     */                   } else {
/*     */                     if (f.i)
/*     */                       f.c.warn("セッションが見つかりました key = " + key); 
/*     */                     if (timeout > 0L)
/*     */                       session.d = currenttime + timeout; 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */               if (session == null && f.i)
/*     */                 f.c.warn("セッションが見つかりません key = " + key); 
/*     */             } 
/*     */           } 
/*     */           if (session == null)
/*     */             session = new j.b(this.a, agent); 
/*     */           response.a("Set-Cookie", "_HttpContentServer_session=" + session.c + "; ; path=/");
/*     */           boolean bAuth = false;
/*     */           if (session.g != null) {
/*     */             Object passport = session.g.get();
/*     */             if (passport != null)
/*     */               bAuth = true; 
/*     */           } 
/*     */           if (!bAuth) {
/*     */             if (session.f == null || session.e < currenttime) {
/*     */               value = this.g.get("Authorization");
/*     */               if (value != null)
/*     */                 if (this.a.ah == null) {
/*     */                   session.f = value;
/*     */                 } else {
/*     */                   session.f = this.a.ah.a(value);
/*     */                 }  
/*     */             } 
/*     */             if (session.f != null) {
/*     */               bAuth = true;
/*     */               session.e = currenttime + 30000L;
/*     */             } 
/*     */           } 
/*     */           if (this.h.startsWith("GET")) {
/*     */             if (!bAuth && f.h)
/*     */               try {
/*     */                 String[] p = this.h.split(" ");
/*     */                 String path = URLDecoder.decode(p[1], "utf-8");
/*     */                 int n = path.lastIndexOf("?passport=");
/*     */                 if (n >= 0) {
/*     */                   String id = path.substring(n + f.h());
/*     */                   if (f.i)
/*     */                     f.c.debug("パスポート検索 id=" + id); 
/*     */                   synchronized (f.ad) {
/*     */                     for (j.a pass : f.ad.keySet()) {
/*     */                       if (f.i)
/*     */                         f.c.debug(String.valueOf(id) + " == " + pass.a); 
/*     */                       if (id.equals(pass.a)) {
/*     */                         session.g = new WeakReference<j.a>(pass);
/*     */                         bAuth = true;
/*     */                         if (f.i)
/*     */                           f.c.debug("パスポート検索 見つかりました"); 
/*     */                         break;
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } catch (Exception exception) {} 
/*     */             if (!bAuth && f.h) {
/*     */               response.a("HTTP/1.1 401 Unauthorized");
/*     */               response.a("WWW-Authenticate", f.a(this.a));
/*     */             } else {
/*     */               b(response);
/*     */             } 
/*     */           } else if (this.h.startsWith("HEAD")) {
/*     */             a(response);
/*     */           } else {
/*     */             response.a("HTTP/1.1 405 Method Not Allowed");
/*     */             response.a("Allow", "GET, HEAD");
/*     */           } 
/*     */           response.a(this.d);
/*     */         } 
/*     */       } catch (IOException e) {
/*     */         f.c.error("run()", e);
/*     */       } finally {
/*     */         this.a.a(this);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void b() throws IOException {
/*     */       this.g.clear();
/*     */       this.h = null;
/*     */       String buf;
/*     */       while ((buf = this.c.readLine()) != null && buf.length() != 0) {
/*     */         if (f.i)
/*     */           f.c.debug("request = " + buf); 
/*     */         if (this.h == null) {
/*     */           this.h = buf;
/*     */           int i = this.h.indexOf("HTTP/");
/*     */           if (i >= 0) {
/*     */             this.i = this.h.substring(i + "HTTP/".length());
/*     */             continue;
/*     */           } 
/*     */           this.i = "1.0";
/*     */           continue;
/*     */         } 
/*     */         int n = buf.indexOf(":");
/*     */         String key = buf;
/*     */         String value = "";
/*     */         if (n >= 0) {
/*     */           key = buf.substring(0, n).trim();
/*     */           value = buf.substring(n + 1).trim();
/*     */         } 
/*     */         this.g.put(key.intern(), value);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void a(f.b res) {
/*     */       res.a("HTTP/1.1 200 OK");
/*     */       res.a("MIME-Version", "1.0");
/*     */     }
/*     */     
/*     */     private void b(f.b res) {
/*     */       String[] p = this.h.split(" ");
/*     */       String pathSegment = p[1];
/*     */       int n = pathSegment.lastIndexOf("?");
/*     */       if (n >= 0)
/*     */         pathSegment = pathSegment.substring(0, n); 
/*     */       try {
/*     */         String path = URLDecoder.decode(pathSegment.substring(1), "UTF-8");
/*     */         if (this.j == null)
/*     */           if (this.i.compareTo("1.1") >= 0) {
/*     */             String host = this.g.get("Host");
/*     */             this.j = "http://" + host + "/" + path;
/*     */           } else {
/*     */             this.j = "http://127.0.0.1:" + this.a.a() + "/" + path;
/*     */           }  
/*     */         h file = this.a.a(this.j, path);
/*     */         if (file == null)
/*     */           throw new b(); 
/*     */         long filesize = file.f();
/*     */         try {
/*     */           e range = e.a(file, this.f, this.g.get("Range"));
/*     */           res.a("Content-Type", file.j().toString());
/*     */           res.a(range);
/*     */         } catch (c e) {
/*     */           res.a("HTTP/1.1 416 Requested range not satisfiable");
/*     */           res.a("Content-Range", "*");
/*     */           res.a("Content-Length", String.valueOf(filesize));
/*     */         } 
/*     */       } catch (a e) {
/*     */         res.a("HTTP/1.1 403 Forbidden");
/*     */       } catch (b e) {
/*     */         res.a("HTTP/1.1 404 Not Found");
/*     */       } catch (Exception e) {
/*     */         if (f.i) {
/*     */           f.c.warn("Get", e);
/*     */         } else {
/*     */           f.c.warn("Get " + e.getClass().getSimpleName());
/*     */         } 
/*     */         res.a("HTTP/1.1 404 Not Found");
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/d/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */