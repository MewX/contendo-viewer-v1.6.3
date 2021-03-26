/*     */ package c.a.a.b;
/*     */ 
/*     */ import c.a.a.e;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   extends b
/*     */   implements e
/*     */ {
/*     */   private static final int h = 65535;
/*  77 */   private int i = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private OutputStream j;
/*     */ 
/*     */   
/*  84 */   int c = 0;
/*     */ 
/*     */   
/*  87 */   public static int d = 1024;
/*     */ 
/*     */ 
/*     */   
/*     */   byte[] e;
/*     */ 
/*     */   
/*     */   byte[] f;
/*     */ 
/*     */   
/*  97 */   int g = 0;
/*     */ 
/*     */   
/* 100 */   private int ae = 0;
/*     */ 
/*     */   
/* 103 */   private int af = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c(File file, int mb) throws IOException {
/* 123 */     super(mb);
/* 124 */     this.j = new BufferedOutputStream(new FileOutputStream(file), d);
/* 125 */     e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c(String fname, int mb) throws IOException {
/* 148 */     super(mb);
/* 149 */     this.j = new BufferedOutputStream(new FileOutputStream(fname), d);
/*     */     
/* 151 */     e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c(OutputStream os, int mb) throws IOException {
/* 172 */     super(mb);
/* 173 */     this.j = os;
/* 174 */     e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int a() {
/* 187 */     return this.b - this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 196 */     if (a() >= 0) {
/* 197 */       return this.c;
/*     */     }
/*     */     
/* 200 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] head, int hlen, boolean sim, boolean sop, boolean eph) throws IOException {
/* 245 */     int len = hlen + (sop ? 6 : 0) + (eph ? 2 : 0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     if (!sim) {
/*     */       
/* 252 */       if (a() < len) {
/* 253 */         len = a();
/*     */       }
/*     */       
/* 256 */       if (len > 0) {
/*     */         
/* 258 */         if (sop) {
/*     */ 
/*     */           
/* 261 */           this.e[4] = (byte)(this.g >> 8);
/* 262 */           this.e[5] = (byte)this.g;
/* 263 */           this.j.write(this.e, 0, 6);
/* 264 */           this.g++;
/* 265 */           if (this.g > 65535)
/*     */           {
/* 267 */             this.g = 0;
/*     */           }
/*     */         } 
/* 270 */         this.j.write(head, 0, hlen);
/*     */         
/* 272 */         this.c += len;
/*     */ 
/*     */         
/* 275 */         if (eph) {
/* 276 */           this.j.write(this.f, 0, 2);
/*     */         }
/*     */ 
/*     */         
/* 280 */         this.af += len;
/*     */       } 
/*     */     } 
/* 283 */     return len;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(byte[] body, int blen, boolean sim, boolean roiInPkt, int roiLen) throws IOException {
/* 322 */     int len = blen;
/*     */ 
/*     */     
/* 325 */     if (!sim) {
/*     */       
/* 327 */       len = blen;
/* 328 */       if (a() < len) {
/* 329 */         len = a();
/*     */       }
/* 331 */       if (blen > 0) {
/* 332 */         this.j.write(body, 0, len);
/*     */       }
/*     */       
/* 335 */       this.c += len;
/*     */ 
/*     */       
/* 338 */       if (roiInPkt) {
/* 339 */         this.ae += this.af + roiLen;
/* 340 */         this.af = len - roiLen;
/*     */       } else {
/* 342 */         this.af += len;
/*     */       } 
/*     */     } 
/* 345 */     return len;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() throws IOException {
/* 357 */     this.j.write(-1);
/* 358 */     this.j.write(-39);
/*     */     
/* 360 */     this.c += 2;
/*     */     
/* 362 */     this.j.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int d() {
/* 371 */     return this.ae;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(d he) throws IOException {
/* 385 */     this.c += he.c();
/* 386 */     he.a(this.j);
/*     */     
/* 388 */     this.g = 0;
/*     */ 
/*     */     
/* 391 */     this.af += he.c();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void e() {
/* 402 */     this.e = new byte[6];
/* 403 */     this.e[0] = -1;
/* 404 */     this.e[1] = -111;
/* 405 */     this.e[2] = 0;
/* 406 */     this.e[3] = 4;
/*     */ 
/*     */ 
/*     */     
/* 410 */     this.f = new byte[2];
/* 411 */     this.f[0] = -1;
/* 412 */     this.f[1] = -110;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/b/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */