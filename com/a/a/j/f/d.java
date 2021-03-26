/*     */ package com.a.a.j.f;
/*     */ 
/*     */ import com.a.a.b.b.b;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class d
/*     */   extends Thread
/*     */ {
/*  22 */   private static final Logger a = LoggerFactory.getLogger(d.class);
/*     */   
/*  24 */   private ServerSocket b = null;
/*  25 */   private Socket c = null;
/*     */ 
/*     */   
/*     */   private boolean d = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public d() throws IOException {
/*  33 */     this(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d(int port) throws IOException {
/*  42 */     this.b = new ServerSocket(port);
/*  43 */     a.debug("ポート番号 = " + b());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/*  51 */     return (this.b != null) ? this.b.getLocalPort() : -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract InputStream a() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*  68 */       a.debug("接続待機中(" + b() + ")");
/*  69 */       this.c = this.b.accept();
/*  70 */       a.debug("接続されました(" + b() + ") : Remote=" + this.c.getRemoteSocketAddress());
/*  71 */       BufferedInputStream bis = b.a(a());
/*  72 */       BufferedOutputStream bos = b.a(this.c.getOutputStream());
/*     */       
/*  74 */       this.d = true;
/*  75 */       b.a(bis, bos);
/*  76 */       b.a(bos);
/*  77 */       b.a(bis);
/*  78 */       this.c.close();
/*  79 */       a.trace("run() - 終了");
/*  80 */     } catch (IOException e) {
/*  81 */       a.error("run()", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void start() {
/*  91 */     a.debug("Thread start");
/*  92 */     super.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean c() {
/* 100 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Socket a(long millisec) throws IOException {
/* 111 */     Socket sock = new Socket();
/* 112 */     if (getState() == Thread.State.NEW) {
/* 113 */       start();
/*     */     }
/* 115 */     if (getState() == Thread.State.TERMINATED) {
/* 116 */       throw new IllegalStateException();
/*     */     }
/*     */     
/* 119 */     long start = System.currentTimeMillis();
/* 120 */     long expire = millisec + start;
/* 121 */     sock.connect(this.b.getLocalSocketAddress());
/*     */     try {
/* 123 */       while (!c()) {
/* 124 */         if (System.currentTimeMillis() > expire) {
/* 125 */           throw new TimeoutException();
/*     */         }
/* 127 */         Thread.sleep(10L);
/*     */       } 
/* 129 */     } catch (Exception e) {
/*     */       try {
/* 131 */         sock.close();
/* 132 */       } catch (Exception exception) {}
/*     */       
/* 134 */       if (e instanceof IOException) throw (IOException)e; 
/* 135 */       throw new IOException(e.toString());
/*     */     } 
/* 137 */     return sock;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/f/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */