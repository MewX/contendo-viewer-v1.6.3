/*      */ package com.a.a.j.d;
/*      */ 
/*      */ import com.a.a.j.c.h;
/*      */ import com.a.a.j.d.a.a;
/*      */ import com.a.a.j.d.a.b;
/*      */ import com.a.a.j.d.a.c;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.net.InetSocketAddress;
/*      */ import java.net.URI;
/*      */ import java.net.URLDecoder;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.CharBuffer;
/*      */ import java.nio.channels.SelectionKey;
/*      */ import java.nio.channels.Selector;
/*      */ import java.nio.channels.ServerSocketChannel;
/*      */ import java.nio.channels.SocketChannel;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.charset.CharsetDecoder;
/*      */ import java.util.Collection;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Queue;
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import java.util.concurrent.ExecutorService;
/*      */ import java.util.concurrent.Executors;
/*      */ import java.util.concurrent.LinkedBlockingQueue;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class a
/*      */   extends j
/*      */ {
/*      */   private static final String c = "_HttpContentServer_session";
/*      */   
/*      */   static class c
/*      */   {
/*      */     private static final String d = "THIS_STRING_SEPARATES";
/* 1066 */     private static Logger e = LoggerFactory.getLogger(c.class);
/* 1067 */     private String f = null;
/* 1068 */     private LinkedList<String> g = new LinkedList<String>();
/* 1069 */     private HashMap<String, String> h = new HashMap<String, String>();
/* 1070 */     e a = null;
/*      */     
/* 1072 */     ByteBuffer b = null;
/* 1073 */     String c = null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void a(String status) {
/* 1079 */       this.f = status;
/*      */     }
/*      */     
/*      */     public String b(String key) {
/* 1083 */       return this.h.get(key);
/*      */     }
/*      */     
/*      */     public void a(String key, String value) {
/* 1087 */       String old = this.h.put(key, value);
/* 1088 */       if (old != null && !old.equals(value)) {
/* 1089 */         this.g.remove(key);
/*      */       }
/* 1091 */       this.g.add(key);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void a(e range) throws IOException {
/* 1102 */       this.a = range;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void a(OutputStream out) throws IOException {
/* 1111 */       PrintStream ps = new PrintStream(this, out, false, "utf-8") {
/* 1112 */           StringBuilder a = a.i ? new StringBuilder() : null;
/*      */ 
/*      */           
/*      */           public void print(String s) {
/* 1116 */             if (a.i) this.a.append(s); 
/* 1117 */             super.print(s);
/*      */           }
/*      */ 
/*      */           
/*      */           public void println() {
/* 1122 */             if (a.i) {
/* 1123 */               a.c.a().trace("response = " + this.a.toString());
/* 1124 */               this.a = new StringBuilder();
/*      */             } 
/* 1126 */             super.print("\r\n");
/*      */           }
/*      */ 
/*      */           
/*      */           public void println(String x) {
/* 1131 */             print(x);
/* 1132 */             println();
/*      */           }
/*      */         };
/*      */       
/* 1136 */       this.h.put("MIME-Version", "1.1");
/* 1137 */       this.h.put("Date", a.aa.format(new Date()));
/* 1138 */       this.h.put("Accept-Ranges", "bytes");
/*      */ 
/*      */       
/* 1141 */       this.h.put("Cache-Control", "no-store");
/* 1142 */       this.h.put("Pragma", "no-cache");
/* 1143 */       this.h.put("Expire", "-1");
/*      */       
/* 1145 */       String multiPartType = null;
/* 1146 */       if (this.a != null) {
/* 1147 */         String contentLength = String.valueOf(this.a.e());
/* 1148 */         switch (this.a.g()) {
/*      */           case 0:
/* 1150 */             a("HTTP/1.1 200 OK");
/* 1151 */             a("Content-Length", contentLength);
/* 1152 */             if (this.a.b()) {
/* 1153 */               a("Content-Encoding", this.a.c());
/*      */             }
/*      */             break;
/*      */           case 1:
/* 1157 */             a("HTTP/1.1 206 Partial content");
/* 1158 */             a("Content-Range", "bytes " + this.a.toString() + "/" + String.valueOf(this.a.d()));
/* 1159 */             a("Content-Length", contentLength);
/*      */             break;
/*      */           default:
/* 1162 */             multiPartType = b("Content-Type");
/* 1163 */             a("HTTP/1.1 206 Partial content");
/* 1164 */             a("Content-Type", null);
/*      */             break;
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/* 1170 */       ps.println(this.f); byte b; int i; String[] arrayOfString;
/* 1171 */       for (i = (arrayOfString = a.Z).length, b = 0; b < i; ) { String key = arrayOfString[b];
/* 1172 */         String val = this.h.remove(key);
/* 1173 */         if (val != null)
/* 1174 */           ps.println(String.format("%s: %s", new Object[] { key, val })); 
/*      */         b++; }
/*      */       
/* 1177 */       for (String key : this.g) {
/* 1178 */         String val = this.h.get(key);
/* 1179 */         if (val != null) {
/* 1180 */           ps.println(String.format("%s: %s", new Object[] { key, val }));
/*      */         }
/*      */       } 
/* 1183 */       if (multiPartType != null) {
/* 1184 */         ps.println(String.format("%s: %s", new Object[] { "Content-Type", "multipart/byteranges; boundary=THIS_STRING_SEPARATES" }));
/*      */       }
/* 1186 */       ps.println();
/*      */ 
/*      */ 
/*      */       
/* 1190 */       if (this.a != null) {
/* 1191 */         if (multiPartType == null) {
/* 1192 */           InputStream is = this.a.h();
/*      */           try {
/* 1194 */             com.a.a.b.b.b.a(is, ps);
/*      */           } finally {
/* 1196 */             com.a.a.b.b.b.a(is);
/*      */           } 
/*      */         } else {
/* 1199 */           String totalLength = String.valueOf(this.a.d());
/* 1200 */           a(ps);
/* 1201 */           for (e.a r : this.a.f()) {
/* 1202 */             ps.println();
/* 1203 */             ps.print("Content-Type");
/* 1204 */             ps.print(": ");
/* 1205 */             ps.println(multiPartType);
/* 1206 */             ps.print("Content-Range");
/* 1207 */             ps.print(": bytes ");
/* 1208 */             ps.print(r.b());
/* 1209 */             ps.print("/");
/* 1210 */             ps.println(totalLength);
/* 1211 */             ps.println();
/* 1212 */             ps.flush();
/*      */             
/* 1214 */             InputStream is = this.a.h();
/*      */             try {
/* 1216 */               com.a.a.b.b.b.a(is, ps);
/*      */             } finally {
/* 1218 */               com.a.a.b.b.b.a(is);
/*      */             } 
/* 1220 */             a(ps);
/*      */           } 
/*      */         } 
/* 1223 */         this.a = null;
/* 1224 */         ps.flush();
/*      */       } 
/*      */     }
/*      */     
/*      */     void a(PrintStream ps) {
/* 1229 */       ps.print("--");
/* 1230 */       ps.println("THIS_STRING_SEPARATES");
/*      */     }
/*      */   }
/*      */   private static final int d = "?passport=".length();
/*      */   private static final int e = 60;
/*      */   private static final int ak = 200;
/*      */   private static final int al = 30000;
/*      */   private static final int am = 1024;
/*      */   private static final int an = 3;
/*      */   private static final int ao = 4;
/*      */   private static final int ap = 8;
/*      */   protected static final Logger a = LoggerFactory.getLogger(a.class);
/*      */   protected static final Queue<Object> b = new ConcurrentLinkedQueue();
/*      */   private String aq = "HOGEHOGE";
/*      */   private final LinkedList<a> ar = new LinkedList<a>();
/*      */   private final ExecutorService as = Executors.newCachedThreadPool();
/*      */   private ServerSocketChannel at;
/*      */   private int au = -1;
/*      */   
/*      */   public a(d resolver) throws IOException {
/*      */     this(resolver, 0, (b)null);
/*      */   }
/*      */   
/*      */   public a(d resolver, int port) throws IOException {
/*      */     this(resolver, port, (b)null);
/*      */   }
/*      */   
/*      */   public a(d resolver, int port, b authorizer) throws IOException {
/*      */     super(resolver, port, authorizer);
/*      */     this.aq = "Basic realm=\"" + this.aq + "\"";
/*      */     this.at = a(this.ai);
/*      */   }
/*      */   
/*      */   protected ServerSocketChannel a(int port) throws IOException {
/*      */     ServerSocketChannel channel = ServerSocketChannel.open();
/*      */     channel.configureBlocking(false);
/*      */     InetSocketAddress endpoint = new InetSocketAddress("127.0.0.1", port);
/*      */     channel.socket().bind(endpoint);
/*      */     return channel;
/*      */   }
/*      */   
/*      */   public int a() {
/*      */     if (this.au < 0 && this.at != null)
/*      */       this.au = this.at.socket().getLocalPort(); 
/*      */     return this.au;
/*      */   }
/*      */   
/*      */   public URI b() {
/*      */     return URI.create(String.valueOf(c()) + "://" + d() + ":" + a() + "/");
/*      */   }
/*      */   
/*      */   public String c() {
/*      */     return "http";
/*      */   }
/*      */   
/*      */   public String d() {
/*      */     return "127.0.0.1";
/*      */   }
/*      */   
/*      */   public String e() {
/*      */     return this.aq;
/*      */   }
/*      */   
/*      */   public void f() throws Exception {
/*      */     try {
/*      */       Selector selector = null;
/*      */       for (int i = 0; i < 2; i++) {
/*      */         try {
/*      */           selector = Selector.open();
/*      */           break;
/*      */         } catch (Exception e) {
/*      */           if (i > 0)
/*      */             throw e; 
/*      */           System.setProperty("java.net.preferIPv6Addresses", "false");
/*      */         } 
/*      */       } 
/*      */       this.at.register(selector, 16);
/*      */       while (!interrupted() && selector.select() > 0) {
/*      */         Iterator<SelectionKey> itSelection = selector.selectedKeys().iterator();
/*      */         while (itSelection.hasNext()) {
/*      */           SelectionKey selKey = itSelection.next();
/*      */           itSelection.remove();
/*      */           if (selKey.isAcceptable()) {
/*      */             SocketChannel channel = this.at.accept();
/*      */             if (a.i)
/*      */               a.info("接続されました(" + a() + ") : Remote=" + channel.socket().getRemoteSocketAddress()); 
/*      */             a(channel);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } finally {
/*      */       if (this.at != null && this.at.isOpen())
/*      */         com.a.a.b.b.b.a(this.at); 
/*      */       if (!this.as.isShutdown())
/*      */         this.as.shutdown(); 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void a(SocketChannel channel) throws IOException {
/*      */     a thread = null;
/*      */     if (!this.ar.isEmpty())
/*      */       synchronized (this.ar) {
/*      */         if (!this.ar.isEmpty())
/*      */           thread = this.ar.removeFirst(); 
/*      */       }  
/*      */     if (thread == null)
/*      */       thread = new a(); 
/*      */     thread.a(this, channel);
/*      */     this.as.execute(thread);
/*      */   }
/*      */   
/*      */   protected void a(a thread) {
/*      */     if (this.ar.size() < 3)
/*      */       synchronized (this.ar) {
/*      */         this.ar.add(thread);
/*      */       }  
/*      */   }
/*      */   
/*      */   protected a g() {
/*      */     return new a();
/*      */   }
/*      */   
/*      */   public static ByteBuffer h() {
/*      */     ByteBuffer buf = null;
/*      */     while (buf == null) {
/*      */       Object obj = b.poll();
/*      */       if (obj == null)
/*      */         break; 
/*      */       if (obj instanceof ByteBuffer) {
/*      */         buf = (ByteBuffer)obj;
/*      */         continue;
/*      */       } 
/*      */       if (obj instanceof Reference) {
/*      */         obj = ((Reference)obj).get();
/*      */         if (obj instanceof ByteBuffer)
/*      */           buf = (ByteBuffer)obj; 
/*      */       } 
/*      */     } 
/*      */     if (buf == null)
/*      */       buf = ByteBuffer.allocate(1024); 
/*      */     return buf;
/*      */   }
/*      */   
/*      */   public static void a(Collection<ByteBuffer> bufs) {
/*      */     for (ByteBuffer buf : bufs)
/*      */       a(buf); 
/*      */   }
/*      */   
/*      */   public static void a(ByteBuffer buf) {
/*      */     if (buf != null && buf.capacity() == 1024) {
/*      */       buf.clear();
/*      */       if (b.size() < 8) {
/*      */         b.add(buf);
/*      */       } else {
/*      */         b.add(new WeakReference<ByteBuffer>(buf));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public synchronized void start() {
/*      */     a.info("Thread start");
/*      */     super.start();
/*      */   }
/*      */   
/*      */   static class b implements Runnable {
/*      */     a.a a = null;
/*      */     private final LinkedBlockingQueue<ByteBuffer> g = new LinkedBlockingQueue<ByteBuffer>(4);
/*      */     private final HashMap<String, String> h = new HashMap<String, String>();
/*      */     private final HashMap<String, String> i = new HashMap<String, String>();
/*      */     private String j = null;
/*      */     private String k = null;
/*      */     private boolean l;
/*      */     private String m = null;
/*      */     int b = 0;
/*      */     boolean c = true;
/*      */     boolean d = false;
/*      */     j.b e = null;
/*      */     private boolean n;
/*      */     boolean f = false;
/*      */     
/*      */     static class a extends OutputStream {
/*      */       private static final String d = "OutputStream is already closed.";
/*      */       a.b a;
/*      */       ByteBuffer b = null;
/*      */       boolean c = false;
/*      */       
/*      */       a(a.b req) {
/*      */         this.a = req;
/*      */       }
/*      */       
/*      */       public void write(int i) throws IOException {
/*      */         a();
/*      */         this.b.put((byte)i);
/*      */       }
/*      */       
/*      */       public void write(byte[] arrayOfByte, int off, int len) throws IOException {
/*      */         a();
/*      */         while (len > 0) {
/*      */           if (this.b == null)
/*      */             this.b = a.h(); 
/*      */           int r = this.b.remaining();
/*      */           int l = Math.min(len, r);
/*      */           if (l > 0)
/*      */             this.b.put(arrayOfByte, off, l); 
/*      */           if (r == l)
/*      */             flush(); 
/*      */           off += l;
/*      */           len -= l;
/*      */         } 
/*      */       }
/*      */       
/*      */       private void a() throws IOException {
/*      */         if (this.c)
/*      */           throw new IOException("OutputStream is already closed."); 
/*      */         if (this.b == null) {
/*      */           this.b = a.h();
/*      */         } else if (!this.b.hasRemaining()) {
/*      */           flush();
/*      */           this.b = a.h();
/*      */         } 
/*      */       }
/*      */       
/*      */       public void flush() throws IOException {
/*      */         a(false);
/*      */       }
/*      */       
/*      */       private void a(boolean close) throws IOException {
/*      */         if (this.c)
/*      */           throw new IOException("OutputStream is already closed."); 
/*      */         try {
/*      */           if (this.b != null) {
/*      */             this.b.flip();
/*      */             if (this.b.hasRemaining()) {
/*      */               a.b.a(this.a, this.b);
/*      */               this.b = null;
/*      */             } else {
/*      */               this.b.clear();
/*      */             } 
/*      */           } 
/*      */         } finally {
/*      */           if (close) {
/*      */             this.c = true;
/*      */             if (this.b == null)
/*      */               this.b = ByteBuffer.wrap(new byte[0]); 
/*      */             this.b.limit(0);
/*      */             a.b.a(this.a, this.b);
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*      */       public void close() throws IOException {
/*      */         a(true);
/*      */         this.b = null;
/*      */         this.a = null;
/*      */       }
/*      */     }
/*      */     
/*      */     void a() {
/*      */       this.a = null;
/*      */       this.h.clear();
/*      */       this.i.clear();
/*      */       this.j = null;
/*      */       this.k = null;
/*      */       this.m = null;
/*      */       this.d = false;
/*      */       this.e = null;
/*      */       this.c = true;
/*      */       ByteBuffer buf;
/*      */       while ((buf = this.g.poll()) != null)
/*      */         a.a(buf); 
/*      */     }
/*      */     
/*      */     void a(String buf) {
/*      */       if (this.j == null) {
/*      */         this.j = buf;
/*      */         int n = this.j.indexOf("HTTP/");
/*      */         this.l = false;
/*      */         if (n >= 0) {
/*      */           this.k = this.j.substring(n + "HTTP/".length());
/*      */           if (this.k.compareTo("1.1") >= 0)
/*      */             this.l = true; 
/*      */         } else {
/*      */           this.k = "1.0";
/*      */         } 
/*      */       } else {
/*      */         int n = buf.indexOf(":");
/*      */         String key = buf;
/*      */         String value = "";
/*      */         if (n >= 0) {
/*      */           key = buf.substring(0, n).trim();
/*      */           value = buf.substring(n + 1).trim();
/*      */         } 
/*      */         if ("Cookie".equals(key)) {
/*      */           if (value != null) {
/*      */             String[] wk = value.split("[;,]");
/*      */             byte b1;
/*      */             int i;
/*      */             String[] arrayOfString1;
/*      */             for (i = (arrayOfString1 = wk).length, b1 = 0; b1 < i; ) {
/*      */               String s = arrayOfString1[b1];
/*      */               n = s.indexOf("=");
/*      */               String k = s.substring(0, n).trim().intern();
/*      */               String v = s.substring(n + 1).trim().intern();
/*      */               this.i.put(k, v);
/*      */               b1++;
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */           this.h.put(key.intern(), value);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     String b() {
/*      */       return this.k;
/*      */     }
/*      */     
/*      */     boolean c() {
/*      */       String value = this.h.get("Connection");
/*      */       if (this.l) {
/*      */         if (value != null && value.toLowerCase().indexOf("close") >= 0)
/*      */           this.c = false; 
/*      */       } else {
/*      */         this.c = false;
/*      */       } 
/*      */       return this.c;
/*      */     }
/*      */     
/*      */     void a(a.a conn, int remain) {
/*      */       this.a = conn;
/*      */       this.b = remain;
/*      */       if (this.b > 0 && c()) {
/*      */         this.n = false;
/*      */       } else {
/*      */         this.n = true;
/*      */       } 
/*      */       long currenttime = System.currentTimeMillis();
/*      */       this.e = conn.a();
/*      */       long timeout = conn.b();
/*      */       if (this.e == null) {
/*      */         String agent = this.h.get("User-Agent");
/*      */         if (!this.i.isEmpty()) {
/*      */           String key = this.i.get("_HttpContentServer_session");
/*      */           if (key != null) {
/*      */             synchronized (a.ac) {
/*      */               this.e = a.ac.get(key);
/*      */               if (this.e != null)
/*      */                 if (timeout > 0L && currenttime > this.e.d) {
/*      */                   if (a.i)
/*      */                     a.a.warn("期限切れ"); 
/*      */                   a.ac.remove(key);
/*      */                   if (this.e.g != null)
/*      */                     this.e.g.clear(); 
/*      */                   this.e = null;
/*      */                 } else if (!agent.equals(this.e.b)) {
/*      */                   if (a.i)
/*      */                     a.a.warn("Agentが異なります"); 
/*      */                   this.e = null;
/*      */                 } else if (a.i) {
/*      */                   a.a.warn("セッションが見つかりました key = " + key);
/*      */                 }  
/*      */             } 
/*      */             if (this.e == null && a.i)
/*      */               a.a.warn("セッションが見つかりません key = " + key); 
/*      */           } 
/*      */         } 
/*      */         if (this.e == null)
/*      */           this.e = this.a.b(agent); 
/*      */       } 
/*      */       this.e.d = currenttime + timeout;
/*      */       this.a.a(this.e);
/*      */       this.f = false;
/*      */       if (this.e.g != null) {
/*      */         Object passport = this.e.g.get();
/*      */         if (passport != null)
/*      */           this.f = true; 
/*      */       } 
/*      */       if (!this.f) {
/*      */         if (this.e.f == null || this.e.e < currenttime) {
/*      */           String str = this.h.get("Authorization");
/*      */           if (str != null)
/*      */             this.e.f = this.a.a(str); 
/*      */         } 
/*      */         if (this.e.f != null) {
/*      */           this.f = true;
/*      */           this.e.e = currenttime + 30000L;
/*      */         } 
/*      */       } 
/*      */       String value = this.h.get("Accept-Encoding");
/*      */       if (value != null)
/*      */         this.d = (value.indexOf("gzip") >= 0); 
/*      */       this.m = this.h.get("Referer");
/*      */     }
/*      */     
/*      */     boolean d() {
/*      */       return this.n;
/*      */     }
/*      */     
/*      */     public void run() {
/*      */       OutputStream out = null;
/*      */       try {
/*      */         a.c response = new a.c();
/*      */         out = new a(this);
/*      */         response.a("Keep-Alive", String.format("timeout=%d, max=%d", new Object[] { Integer.valueOf(60), Integer.valueOf(this.b) }));
/*      */         if (this.n) {
/*      */           response.a("Connection", "close");
/*      */         } else {
/*      */           response.a("Connection", "Keep-Alive");
/*      */         } 
/*      */         response.a("Set-Cookie", "_HttpContentServer_session=" + this.e.c + "; ; path=/");
/*      */         if (this.j.startsWith("GET")) {
/*      */           if (!this.f && a.h)
/*      */             try {
/*      */               String[] p = this.j.split(" ");
/*      */               String path = URLDecoder.decode(p[1], "utf-8");
/*      */               int n = path.lastIndexOf("?passport=");
/*      */               if (n >= 0) {
/*      */                 String id = path.substring(n + a.i());
/*      */                 if (a.i)
/*      */                   a.a.debug("パスポート検索 id=" + id); 
/*      */                 synchronized (a.ad) {
/*      */                   for (j.a pass : a.ad.keySet()) {
/*      */                     if (a.i)
/*      */                       a.a.debug(String.valueOf(id) + " == " + pass.a); 
/*      */                     if (id.equals(pass.a)) {
/*      */                       this.e.g = new WeakReference<j.a>(pass);
/*      */                       this.f = true;
/*      */                       if (a.i)
/*      */                         a.a.debug("パスポート検索 見つかりました"); 
/*      */                       break;
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */             } catch (Exception exception) {} 
/*      */           if (!this.f && a.h) {
/*      */             response.a("HTTP/1.1 401 Unauthorized");
/*      */             response.a("WWW-Authenticate", this.a.e());
/*      */           } else {
/*      */             b(response);
/*      */           } 
/*      */         } else if (this.j.startsWith("HEAD")) {
/*      */           a(response);
/*      */         } else {
/*      */           response.a("HTTP/1.1 405 Method Not Allowed");
/*      */           response.a("Allow", "GET, HEAD");
/*      */         } 
/*      */         response.a(out);
/*      */       } catch (Exception exception) {
/*      */       
/*      */       } finally {
/*      */         if (out != null)
/*      */           com.a.a.b.b.b.a(out); 
/*      */       } 
/*      */     }
/*      */     
/*      */     private void a(ByteBuffer buf) throws IOException {
/*      */       a.a conn = this.a;
/*      */       if (Thread.interrupted() && buf.hasRemaining())
/*      */         throw new IOException("Aborted!!"); 
/*      */       while (!this.g.offer(buf, 500L, TimeUnit.MILLISECONDS)) {
/*      */         if (Thread.interrupted())
/*      */           throw new IOException("Aborted!!"); 
/*      */       } 
/*      */       if (conn != null)
/*      */         conn.a(this); 
/*      */     }
/*      */     
/*      */     public ByteBuffer e() {
/*      */       ByteBuffer buf = this.g.poll();
/*      */       return buf;
/*      */     }
/*      */     
/*      */     public boolean f() {
/*      */       return !this.g.isEmpty();
/*      */     }
/*      */     
/*      */     private void a(a.c res) {
/*      */       res.a("HTTP/1.1 200 OK");
/*      */       res.a("MIME-Version", "1.0");
/*      */     }
/*      */     
/*      */     private void b(a.c res) {
/*      */       String[] p = this.j.split(" ");
/*      */       String pathSegment = p[1];
/*      */       int n = pathSegment.lastIndexOf("?");
/*      */       if (n >= 0)
/*      */         pathSegment = pathSegment.substring(0, n); 
/*      */       try {
/*      */         String path = URLDecoder.decode(pathSegment.substring(1), "UTF-8");
/*      */         if (this.m == null)
/*      */           if (this.l) {
/*      */             String host = this.h.get("Host");
/*      */             this.m = "http://" + host + "/" + path;
/*      */           } else {
/*      */             this.m = "http://127.0.0.1:" + this.a.d() + "/" + path;
/*      */           }  
/*      */         h file = this.a.a(this.m, path);
/*      */         if (file == null)
/*      */           throw new b(); 
/*      */         long filesize = file.f();
/*      */         try {
/*      */           e range = e.a(file, this.d, this.h.get("Range"));
/*      */           res.a("Content-Type", file.j().toString());
/*      */           res.a(range);
/*      */         } catch (c e) {
/*      */           res.a("HTTP/1.1 416 Requested range not satisfiable");
/*      */           res.a("Content-Range", "*");
/*      */           res.a("Content-Length", String.valueOf(filesize));
/*      */         } 
/*      */       } catch (a e) {
/*      */         res.a("HTTP/1.1 403 Forbidden");
/*      */       } catch (b e) {
/*      */         res.a("HTTP/1.1 404 Not Found");
/*      */       } catch (Exception e) {
/*      */         if (a.i) {
/*      */           a.a.warn("Get", e);
/*      */         } else {
/*      */           a.a.warn("Get " + e.getClass().getSimpleName());
/*      */         } 
/*      */         res.a("HTTP/1.1 404 Not Found");
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static class a implements Runnable {
/*      */     a a = null;
/*      */     SocketChannel b = null;
/*      */     Selector c = null;
/*      */     private final Charset d;
/*      */     private final CharsetDecoder e;
/*      */     private boolean f = false;
/*      */     private ExecutorService g = Executors.newSingleThreadExecutor();
/*      */     private final Queue<a.b> h = new LinkedList<a.b>();
/*      */     private j.b i = null;
/*      */     private a.b j;
/*      */     
/*      */     public a() {
/*      */       this.d = Charset.forName("utf-8");
/*      */       this.e = this.d.newDecoder();
/*      */     }
/*      */     
/*      */     public void a(j.b session) {
/*      */       this.i = session;
/*      */     }
/*      */     
/*      */     public j.b a() {
/*      */       return this.i;
/*      */     }
/*      */     
/*      */     public long b() {
/*      */       return this.a.j();
/*      */     }
/*      */     
/*      */     public void a(a svr, SocketChannel channel) throws IOException {
/*      */       this.a = svr;
/*      */       this.b = channel;
/*      */       this.e.reset();
/*      */     }
/*      */     
/*      */     public void c() {
/*      */       if (this.c != null) {
/*      */         if (this.c.isOpen())
/*      */           try {
/*      */             this.c.close();
/*      */           } catch (Exception exception) {} 
/*      */         this.c = null;
/*      */       } 
/*      */       if (this.b != null) {
/*      */         if (this.b.isOpen())
/*      */           com.a.a.b.b.b.a(this.b); 
/*      */         this.b = null;
/*      */       } 
/*      */       this.a = null;
/*      */       this.i = null;
/*      */     }
/*      */     
/*      */     public String a(String value) {
/*      */       String authkey;
/*      */       if (this.a.ah == null) {
/*      */         authkey = value;
/*      */       } else {
/*      */         authkey = this.a.ah.a(value);
/*      */       } 
/*      */       return authkey;
/*      */     }
/*      */     
/*      */     public h a(String url, String path) throws c {
/*      */       return this.a.a(url, path);
/*      */     }
/*      */     
/*      */     public int d() {
/*      */       return this.a.a();
/*      */     }
/*      */     
/*      */     public String e() {
/*      */       return this.a.e();
/*      */     }
/*      */     
/*      */     j.b b(String agent) {
/*      */       return new j.b(this.a, agent);
/*      */     }
/*      */     
/*      */     ByteBuffer f() {
/*      */       ByteBuffer buf = null;
/*      */       while (buf == null) {
/*      */         buf = this.j.e();
/*      */         if (buf == null)
/*      */           break; 
/*      */         if (!buf.hasRemaining()) {
/*      */           if (a.i)
/*      */             a.a.debug("getNextBuffer() Request EOF"); 
/*      */           this.j.a();
/*      */           this.j = this.h.poll();
/*      */           if (this.j == null)
/*      */             break; 
/*      */           buf = null;
/*      */         } 
/*      */       } 
/*      */       return buf;
/*      */     }
/*      */     
/*      */     public void a(a.b request) {
/*      */       if (this.j == request && !this.f)
/*      */         this.c.wakeup(); 
/*      */     }
/*      */     
/*      */     public void run() {
/*      */       List<Runnable> list;
/*      */       a svr;
/*      */       try {
/*      */         this.c = Selector.open();
/*      */         this.b.configureBlocking(false);
/*      */         this.b.register(this.c, 1);
/*      */         ByteBuffer readBuf = a.h();
/*      */         CharBuffer reqestBuf = CharBuffer.allocate(1024);
/*      */         StringBuilder sb = new StringBuilder();
/*      */         a.b req = null;
/*      */         boolean endRead = false;
/*      */         ByteBuffer writeBuf = null;
/*      */         int _remain = Math.max(200, 1);
/*      */         this.f = false;
/*      */         this.j = null;
/*      */         if (this.g == null || this.g.isShutdown())
/*      */           this.g = Executors.newSingleThreadExecutor(); 
/*      */         boolean timeout = false;
/*      */         while (!Thread.interrupted() && !endRead) {
/*      */           int nSelect = this.c.select(60000L);
/*      */           if (nSelect == 0 && this.j == null && writeBuf != null) {
/*      */             if (timeout) {
/*      */               a.a.trace("Timeout");
/*      */               break;
/*      */             } 
/*      */             timeout = true;
/*      */           } else {
/*      */             timeout = false;
/*      */           } 
/*      */           if (Thread.interrupted())
/*      */             break; 
/*      */           if (!this.f && this.j != null) {
/*      */             writeBuf = f();
/*      */             if (writeBuf != null) {
/*      */               this.f = true;
/*      */               this.b.register(this.c, 5);
/*      */             } 
/*      */           } 
/*      */           Iterator<SelectionKey> itSelection = this.c.selectedKeys().iterator();
/*      */           while (itSelection.hasNext()) {
/*      */             SelectionKey selkey = itSelection.next();
/*      */             itSelection.remove();
/*      */             if (selkey.isWritable())
/*      */               do {
/*      */                 if (writeBuf != null && !writeBuf.hasRemaining()) {
/*      */                   a.a(writeBuf);
/*      */                   writeBuf = f();
/*      */                 } 
/*      */                 if (writeBuf == null) {
/*      */                   this.f = false;
/*      */                   this.b.register(this.c, 1);
/*      */                 } else {
/*      */                   try {
/*      */                     this.b.write(writeBuf);
/*      */                   } catch (Throwable th) {
/*      */                     a.a.error("buf = " + writeBuf);
/*      */                     throw th;
/*      */                   } 
/*      */                   if (writeBuf.hasRemaining())
/*      */                     break; 
/*      */                 } 
/*      */               } while (writeBuf != null); 
/*      */             if (selkey.isReadable()) {
/*      */               boolean bRead = false;
/*      */               if (this.b.isOpen())
/*      */                 while (this.b.read(readBuf) > 0) {
/*      */                   bRead = true;
/*      */                   readBuf.flip();
/*      */                   while (com.a.a.e.a.a.a(sb, this.e, readBuf, reqestBuf, false)) {
/*      */                     String line = sb.toString();
/*      */                     sb = new StringBuilder();
/*      */                     if (a.i && a.a.isTraceEnabled())
/*      */                       a.a.trace("request=" + line); 
/*      */                     if (line.length() == 0) {
/*      */                       if (req != null && a.b.a(req) != null) {
/*      */                         if (_remain > 0)
/*      */                           _remain--; 
/*      */                         req.a(this, _remain);
/*      */                         if (this.j == null) {
/*      */                           this.j = req;
/*      */                         } else {
/*      */                           this.h.add(req);
/*      */                         } 
/*      */                         this.g.execute(req);
/*      */                         req = null;
/*      */                       } 
/*      */                       continue;
/*      */                     } 
/*      */                     if (req == null)
/*      */                       req = new a.b(); 
/*      */                     req.a(line);
/*      */                   } 
/*      */                 }  
/*      */               if (!bRead) {
/*      */                 a.a.debug("_channel is closed");
/*      */                 endRead = true;
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } catch (Throwable e) {
/*      */         a.a.warn("run()", e.toString());
/*      */       } finally {
/*      */         a.a.debug("finally");
/*      */         List<Runnable> list1 = this.g.shutdownNow();
/*      */         this.g = null;
/*      */         for (Runnable r : list1) {
/*      */           if (r instanceof a.b) {
/*      */             ((a.b)r).a();
/*      */             this.h.remove(r);
/*      */           } 
/*      */         } 
/*      */         if (this.j != null) {
/*      */           this.j.a();
/*      */           this.j = null;
/*      */         } 
/*      */         a a1 = this.a;
/*      */         c();
/*      */         a.a.debug("recycle");
/*      */         a1.a(this);
/*      */       } 
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/d/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */