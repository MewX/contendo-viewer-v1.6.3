/*     */ package com.a.a.j.d;
/*     */ 
/*     */ import com.a.a.j.c.h;
/*     */ import com.a.a.j.d.a.c;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Random;
/*     */ import java.util.TimeZone;
/*     */ import java.util.WeakHashMap;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ public abstract class j
/*     */   extends Thread
/*     */   implements h
/*     */ {
/*     */   protected static boolean h = true;
/*     */   protected static boolean i = false;
/*     */   private static final int a = 3600000;
/*  27 */   private long b = 3600000L;
/*     */   
/*     */   protected static final String j = "HTTP/1.1 200 OK";
/*     */   
/*     */   protected static final String k = "HTTP/1.1 206 Partial content";
/*     */   
/*     */   protected static final String l = "HTTP/1.1 401 Unauthorized";
/*     */   
/*     */   protected static final String m = "HTTP/1.1 403 Forbidden";
/*     */   
/*     */   protected static final String n = "HTTP/1.1 404 Not Found";
/*     */   
/*     */   protected static final String o = "HTTP/1.1 405 Method Not Allowed";
/*     */   protected static final String p = "HTTP/1.1 416 Requested range not satisfiable";
/*     */   protected static final String q = "HTTP/";
/*     */   protected static final String r = "Connection";
/*     */   protected static final String s = "Keep-Alive";
/*     */   protected static final String t = "Content-Length";
/*     */   protected static final String u = "Content-Language";
/*     */   protected static final String v = "EEE, dd MMM yyyy HH:mm:ss zzz";
/*     */   protected static final String w = "EEE, dd-MMM-yyyy HH:mm:ss z";
/*     */   protected static final String x = "EEE, dd MMM yyyyy HH:mm:ss z";
/*     */   protected static final String y = "EEEEEEEEE, dd-MMM-yy HH:mm:ss z";
/*     */   protected static final String z = "MIME-Version";
/*     */   protected static final String A = "WWW-Authenticate";
/*     */   protected static final String B = "Content-Encoding";
/*     */   protected static final String C = "Date";
/*     */   protected static final String D = "Server";
/*     */   protected static final String E = "Allow";
/*     */   protected static final String F = "Last-Modified";
/*     */   protected static final String G = "Expire";
/*     */   protected static final String H = "Content-Type";
/*     */   protected static final String I = "Content-Range";
/*     */   protected static final String J = "Content-MD5";
/*     */   protected static final String K = "Content-Location";
/*     */   protected static final String L = "Accept-Ranges";
/*     */   protected static final String M = "Authorization";
/*     */   protected static final String N = "Accept-Encoding";
/*     */   protected static final String O = "close";
/*     */   protected static final String P = "Pragma";
/*     */   protected static final String Q = "Cache-Control";
/*     */   protected static final String R = "Range";
/*     */   protected static final String S = "Cookie";
/*     */   protected static final String T = "Set-Cookie";
/*     */   protected static final String U = "User-Agent";
/*     */   protected static final String V = "Referer";
/*     */   protected static final String W = "HEAD";
/*     */   protected static final String X = "GET";
/*     */   protected static final String Y = "\r\n";
/*  76 */   protected static final String[] Z = new String[] { 
/*  77 */       "MIME-Version", 
/*  78 */       "WWW-Authenticate", 
/*  79 */       "Allow", 
/*  80 */       "Accept-Ranges", 
/*  81 */       "Server", 
/*  82 */       "Date", 
/*  83 */       "Content-Encoding", 
/*  84 */       "Content-Language", 
/*  85 */       "Content-Length", 
/*  86 */       "Content-Location", 
/*  87 */       "Content-MD5", 
/*  88 */       "Content-Range", 
/*  89 */       "Content-Type", 
/*  90 */       "Cache-Control", 
/*  91 */       "Pragma", 
/*  92 */       "Content-Type", 
/*  93 */       "Expire", 
/*  94 */       "Last-Modified", 
/*  95 */       "Set-Cookie", 
/*  96 */       "Keep-Alive", 
/*  97 */       "Connection" };
/*     */ 
/*     */   
/* 100 */   protected static final DateFormat aa = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
/* 101 */   protected static final DateFormat ab = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", Locale.US);
/* 102 */   protected static final HashMap<String, b> ac = new HashMap<String, b>(89);
/* 103 */   protected static final WeakHashMap<a, String> ad = new WeakHashMap<a, String>(89);
/*     */   
/* 105 */   protected static final Random ae = (Random)new com.a.a.h.a.a(); protected final Logger af; protected d ag;
/*     */   
/*     */   static {
/* 108 */     ab.setTimeZone(TimeZone.getTimeZone("GMT"));
/*     */   }
/*     */   protected b ah; protected int ai;
/*     */   
/*     */   static class a { final String a;
/* 113 */     private static HashSet<String> b = null;
/*     */     
/*     */     a() {
/* 116 */       synchronized (a.class) {
/* 117 */         if (b == null) {
/* 118 */           b = new HashSet<String>();
/*     */         }
/*     */         
/*     */         while (true) {
/* 122 */           String id = com.a.a.h.a.b.a(j.ae, 16, 16, null);
/* 123 */           if (!b.contains(id)) {
/* 124 */             b.add(id);
/* 125 */             this.a = id;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void finalize() throws Throwable {
/*     */       try {
/* 135 */         synchronized (a.class) {
/* 136 */           b.remove(this.a);
/* 137 */           if (b.isEmpty()) b = null; 
/*     */         } 
/*     */       } finally {
/* 140 */         super.finalize();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 149 */       return this.a;
/*     */     } }
/*     */ 
/*     */   
/*     */   protected static class b {
/*     */     final long a;
/*     */     final String b;
/*     */     final String c;
/*     */     long d;
/* 158 */     long e = 0L;
/*     */     String f;
/*     */     WeakReference<j.a> g;
/*     */     HashMap<String, Object> h;
/*     */     
/*     */     b(j svr, String agent) {
/* 164 */       this.a = System.currentTimeMillis();
/* 165 */       this.d = this.a + svr.j();
/* 166 */       this.b = agent;
/* 167 */       this.c = svr.a(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   LinkedList<WeakReference<d>> aj = new LinkedList<WeakReference<d>>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public j(d resolver, int port, b authorizer) {
/* 181 */     this.ag = resolver;
/* 182 */     this.ai = port;
/* 183 */     this.ah = authorizer;
/* 184 */     this.af = LoggerFactory.getLogger(getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/* 191 */       this.af.info("開始 ポート番号 = " + a());
/* 192 */       f();
/* 193 */     } catch (Exception e) {
/* 194 */       e.printStackTrace();
/*     */     } finally {
/* 196 */       this.af.info("終了 ポート番号 = " + a());
/* 197 */       this.ag = null;
/* 198 */       this.ah = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void f() throws Exception;
/*     */ 
/*     */ 
/*     */   
/*     */   public long j() {
/* 209 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(long time) {
/* 217 */     this.b = time;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object a(String otp) {
/* 225 */     a pass = null;
/* 226 */     String auth = (this.ah == null) ? otp : this.ah.a(otp);
/* 227 */     if (auth != null) {
/* 228 */       pass = new a();
/* 229 */       this.af.debug("パスポート発行 id=" + pass.a);
/* 230 */       synchronized (ad) {
/* 231 */         ad.put(pass, auth);
/*     */       } 
/*     */     } else {
/* 234 */       this.af.warn("obtainPassport() 認証NG");
/*     */     } 
/* 236 */     return pass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String a(b s) {
/* 246 */     synchronized (ac) {
/*     */       while (true) {
/* 248 */         String key = com.a.a.h.a.b.a(ae, 16, 16, null);
/* 249 */         if (!ac.containsKey(key)) {
/* 250 */           ac.put(key, s);
/* 251 */           this.af.warn("createSessionKey count = " + ac.size());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(d resolver) {
/* 260 */     synchronized (this.aj) {
/* 261 */       this.aj.addFirst(new WeakReference<d>(resolver));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(d resolver) {
/* 269 */     synchronized (this.aj) {
/* 270 */       ListIterator<WeakReference<d>> it = this.aj.listIterator();
/* 271 */       while (it.hasNext()) {
/* 272 */         WeakReference<d> ref = it.next();
/* 273 */         d res = ref.get();
/* 274 */         if (res == null || res == resolver) it.remove();
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h a(String url, String path) throws c {
/* 284 */     h file = null;
/* 285 */     if (path != null) {
/* 286 */       if (!this.aj.isEmpty()) {
/* 287 */         synchronized (this.aj) {
/* 288 */           if (!this.aj.isEmpty()) {
/* 289 */             ListIterator<WeakReference<d>> it = this.aj.listIterator();
/* 290 */             while (it.hasNext()) {
/* 291 */               WeakReference<d> ref = it.next();
/* 292 */               d r = ref.get();
/* 293 */               if (r == null) {
/*     */                 
/* 295 */                 it.remove();
/*     */                 continue;
/*     */               } 
/* 298 */               if (r.a(url, path)) {
/*     */                 
/* 300 */                 file = r.b(url, path);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/* 308 */       if (file == null) {
/* 309 */         file = this.ag.b(url, path);
/*     */       }
/*     */     } 
/* 312 */     return file;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/d/j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */