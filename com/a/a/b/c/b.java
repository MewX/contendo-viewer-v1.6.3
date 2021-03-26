/*     */ package com.a.a.b.c;
/*     */ 
/*     */ import com.a.a.b.g;
/*     */ import com.a.a.b.h;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.SequenceInputStream;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.zip.Inflater;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import java.util.zip.ZipException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends File
/*     */   implements d, Iterable<b.b>
/*     */ {
/*     */   private static final long a = 1L;
/*     */   private int b;
/*     */   private int c;
/*     */   private long d;
/*     */   private long e;
/*     */   private int f;
/*  40 */   private SoftReference<List<b>> g = null;
/*  41 */   private com.a.a.e.b h = null;
/*     */   
/*     */   public static class b implements a, d {
/*  44 */     private b Y = null;
/*     */     
/*     */     private long Z;
/*     */     
/*     */     int a;
/*     */     
/*     */     int b;
/*     */     
/*     */     int c;
/*     */     
/*     */     private int aa;
/*     */     
/*     */     long d;
/*     */     
/*     */     private long ab;
/*     */     
/*     */     private long ac;
/*     */     
/*     */     private long ad;
/*     */     
/*     */     private String ae;
/*     */     byte[] e;
/*     */     String f;
/*     */     int g;
/*     */     int h;
/*     */     int i;
/*     */     private long af;
/*     */     
/*     */     protected b(b zipfile) {
/*  73 */       this.Y = zipfile;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String e() {
/*  81 */       return this.ae;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private void a(String name) {
/*  89 */       this.ae = name;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected long f() {
/*  97 */       return this.Z;
/*     */     }
/*     */     
/*     */     private void c(long pos) {
/* 101 */       this.Z = pos;
/*     */     }
/*     */     
/*     */     protected long g() {
/* 105 */       return this.af;
/*     */     }
/*     */     
/*     */     private void d(long offset) {
/* 109 */       this.af = offset;
/*     */     }
/*     */     
/*     */     public long a() {
/* 113 */       return this.ac;
/*     */     }
/*     */     
/*     */     private void e(long csize) {
/* 117 */       this.ac = csize;
/*     */     }
/*     */     
/*     */     public long b() {
/* 121 */       return this.ad;
/*     */     }
/*     */     
/*     */     void a(long size) {
/* 125 */       this.ad = size;
/*     */     }
/*     */     
/*     */     public boolean h() {
/* 129 */       return !(this.Y != null && (this.i & 0x10) != 16 && !this.ae.endsWith("/") && !this.ae.endsWith("\\"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InputStream i() throws IOException {
/* 138 */       return a(false);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InputStream a(boolean noinflate) throws IOException {
/* 147 */       return this.Y.a(this, noinflate);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public com.a.a.e.b j() throws IOException {
/* 155 */       return this.Y.b(this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int c() {
/* 162 */       return this.aa;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void a(int method) {
/* 169 */       this.aa = method;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int k() {
/* 176 */       return this.c;
/*     */     }
/*     */     
/*     */     public long d() {
/* 180 */       return this.ab;
/*     */     }
/*     */     
/*     */     void b(long crc) {
/* 184 */       this.ab = crc;
/*     */     }
/*     */     
/*     */     public b() {}
/*     */   }
/*     */   
/*     */   public class a
/*     */   {
/*     */     long a;
/*     */     int b;
/*     */     int c;
/*     */     long d;
/*     */     long e;
/*     */     long f;
/*     */     long g;
/*     */     String h;
/*     */     byte[] i;
/*     */     
/*     */     public a(b this$0) {}
/*     */   }
/*     */   
/*     */   public b(File f) {
/* 206 */     super(f, "");
/* 207 */     if (f instanceof b) {
/* 208 */       this.h = ((b)f).h;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(com.a.a.e.b buf) {
/* 216 */     super("dummy-path");
/* 217 */     this.h = buf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream a(String path) throws IOException {
/* 226 */     b fzentry = b(path);
/* 227 */     if (fzentry == null || fzentry.h()) throw new FileNotFoundException(); 
/* 228 */     return a(fzentry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream a(b fzentry) throws IOException {
/* 237 */     return a(fzentry, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream a(b fzentry, boolean noinflate) throws IOException {
/* 247 */     if (fzentry == null || fzentry.h()) throw new FileNotFoundException(); 
/* 248 */     h zf = b.a(fzentry).a();
/* 249 */     zf.a(fzentry.g());
/* 250 */     a entry = c(new byte[512], zf);
/* 251 */     long pos = zf.a();
/* 252 */     zf.close();
/*     */     
/* 254 */     InputStream stream = a(b.a(fzentry), pos, fzentry.a());
/* 255 */     if (!(stream instanceof BufferedInputStream)) {
/* 256 */       stream = new BufferedInputStream(stream, 8192);
/*     */     }
/* 258 */     if (entry.c == 8 && !noinflate) {
/*     */       
/* 260 */       byte[] dummy = new byte[1];
/* 261 */       stream = new SequenceInputStream(stream, new ByteArrayInputStream(dummy));
/*     */       
/* 263 */       stream = new InflaterInputStream(stream, new Inflater(true));
/*     */     } 
/* 265 */     return stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public com.a.a.e.b b(b fzentry) throws IOException {
/* 274 */     if (fzentry == null || fzentry.h()) throw new FileNotFoundException();
/*     */     
/* 276 */     h zf = b.a(fzentry).a();
/* 277 */     zf.a(fzentry.g());
/* 278 */     a entry = c(new byte[512], zf);
/* 279 */     long pos = zf.a();
/* 280 */     zf.close();
/*     */ 
/*     */     
/* 283 */     if (entry.c == 8) return null;
/*     */     
/* 285 */     com.a.a.e.b b1 = null;
/* 286 */     if ((b.a(fzentry)).h == null) {
/* 287 */       b1 = com.a.a.e.b.a(b.a(fzentry).a());
/*     */     } else {
/* 289 */       b1 = (b.a(fzentry)).h.x();
/*     */     } 
/* 291 */     b1 = b1.h((int)pos).u().g((int)fzentry.b());
/* 292 */     return b1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   h a() throws IOException {
/* 300 */     if (this.h == null) {
/* 301 */       return h.a(this);
/*     */     }
/* 303 */     return h.a(this.h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected InputStream a(b file, long position, long length) throws IOException {
/*     */     com.a.a.e.b.a a;
/* 314 */     InputStream stream = null;
/* 315 */     if (file.h == null) {
/* 316 */       stream = new FileInputStream(file);
/* 317 */       stream.skip(position);
/* 318 */       g g = new g(stream, length);
/*     */     } else {
/* 320 */       com.a.a.e.b b1 = file.h.x().h((int)position).u().g((int)length);
/* 321 */       a = new com.a.a.e.b.a(b1);
/*     */     } 
/* 323 */     return (InputStream)a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(String path) {
/* 331 */     b entry = null;
/*     */     
/* 333 */     for (b e : d()) {
/* 334 */       if (e.e().equals(path)) {
/* 335 */         entry = e;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 340 */     return entry;
/*     */   }
/*     */   
/*     */   public Enumeration<? extends b> b() {
/* 344 */     return new Enumeration<b>(this)
/*     */       {
/*     */         Iterator<b.b> a;
/*     */         
/*     */         public boolean hasMoreElements() {
/* 349 */           return this.a.hasNext();
/*     */         }
/*     */ 
/*     */         
/*     */         public b.b a() {
/* 354 */           return this.a.next();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<b> c() {
/* 362 */     return new ArrayList<b>(d());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<b> d() {
/* 369 */     List<b> _entries = null;
/* 370 */     if (this.g == null || (_entries = this.g.get()) == null) {
/* 371 */       synchronized (this) {
/* 372 */         if (this.g == null || (_entries = this.g.get()) == null) {
/*     */           try {
/* 374 */             byte[] buf = new byte[512];
/* 375 */             h zf = a();
/*     */             try {
/* 377 */               a(buf, zf);
/* 378 */               _entries = b(buf, zf);
/* 379 */               for (b dir : _entries) {
/* 380 */                 zf.a(dir.g());
/* 381 */                 c(buf, zf);
/*     */               } 
/*     */             } finally {
/*     */               
/*     */               try {
/* 386 */                 zf.close();
/*     */               } finally {
/* 388 */                 zf = null;
/*     */               } 
/*     */             } 
/* 391 */           } catch (IOException iOException) {}
/*     */           
/* 393 */           if (_entries == null) {
/* 394 */             _entries = new LinkedList<b>();
/*     */           }
/* 396 */           this.g = new SoftReference<List<b>>(_entries);
/*     */         } 
/*     */       } 
/*     */     }
/* 400 */     return _entries;
/*     */   }
/*     */   
/*     */   private void a(byte[] buf, h zf) throws IOException {
/* 404 */     long size = zf.b();
/*     */     
/* 406 */     zf.a(size - 22L);
/* 407 */     zf.b(buf, 0, 22);
/*     */     
/* 409 */     if (d(buf, 0) != 101010256L) {
/* 410 */       throw new ZipException("Terminater not detected");
/*     */     }
/*     */     
/* 413 */     this.b = c(buf, 8);
/* 414 */     this.c = c(buf, 10);
/* 415 */     this.d = d(buf, 12);
/* 416 */     this.e = d(buf, 16);
/* 417 */     this.f = c(buf, 20);
/*     */   }
/*     */   
/*     */   private List<b> b(byte[] buf, h zf) throws IOException {
/* 421 */     zf.a(this.e);
/*     */     
/* 423 */     ArrayList<b> list = new ArrayList<b>(this.b);
/* 424 */     for (int i = 0; i < this.b; i++) {
/* 425 */       b dir = new b(this);
/* 426 */       b.a(dir, zf.a());
/*     */       
/* 428 */       zf.b(buf, 0, 46);
/*     */       
/* 430 */       if (d(buf, 0) != 33639248L) {
/* 431 */         throw new ZipException("Central directory sign not detected");
/*     */       }
/*     */       
/* 434 */       dir.a = c(buf, 4);
/* 435 */       dir.b = c(buf, 6);
/* 436 */       dir.c = c(buf, 8);
/* 437 */       dir.a(c(buf, 10));
/* 438 */       dir.d = d(buf, 12);
/* 439 */       dir.b(d(buf, 16));
/* 440 */       b.b(dir, d(buf, 20));
/* 441 */       dir.a(d(buf, 24));
/* 442 */       int name_len = c(buf, 28);
/* 443 */       int extra_len = c(buf, 30);
/* 444 */       int comment_len = c(buf, 32);
/* 445 */       dir.g = c(buf, 34);
/* 446 */       dir.h = c(buf, 36);
/* 447 */       dir.i = c(buf, 38);
/* 448 */       b.c(dir, d(buf, 42));
/*     */ 
/*     */       
/* 451 */       byte[] arrayOfByte = a(buf, name_len);
/* 452 */       zf.b(arrayOfByte, 0, name_len);
/* 453 */       b.a(dir, b(arrayOfByte, name_len));
/*     */       
/* 455 */       if (extra_len > 0) {
/* 456 */         arrayOfByte = new byte[extra_len];
/* 457 */         zf.b(arrayOfByte, 0, extra_len);
/* 458 */         dir.e = arrayOfByte;
/*     */       } else {
/* 460 */         dir.e = null;
/*     */       } 
/* 462 */       if (comment_len > 0) {
/* 463 */         arrayOfByte = a(buf, comment_len);
/* 464 */         zf.b(arrayOfByte, 0, comment_len);
/* 465 */         dir.f = b(arrayOfByte, comment_len);
/*     */       } else {
/* 467 */         dir.f = null;
/*     */       } 
/*     */       
/* 470 */       list.add(dir);
/*     */     } 
/*     */     
/* 473 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private a c(byte[] buf, h zf) throws IOException {
/* 482 */     zf.b(buf, 0, 30);
/*     */     
/* 484 */     if (d(buf, 0) != 67324752L) {
/* 485 */       throw new IOException();
/*     */     }
/*     */     
/* 488 */     a e = new a(this);
/*     */ 
/*     */     
/* 491 */     int filename_len = c(buf, 26);
/*     */     
/* 493 */     e.b = c(buf, 6);
/* 494 */     if ((e.b & 0x1) == 1) {
/* 495 */       throw new ZipException("encrypted ZIP entry not supported");
/*     */     }
/* 497 */     e.c = c(buf, 8);
/* 498 */     e.d = d(buf, 10);
/* 499 */     if ((e.b & 0x8) == 8) {
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 507 */       e.e = d(buf, 14);
/* 508 */       e.f = d(buf, 18);
/* 509 */       e.g = d(buf, 22);
/*     */     } 
/* 511 */     int ext_len = c(buf, 28);
/*     */ 
/*     */ 
/*     */     
/* 515 */     byte[] arrayOfByte = buf;
/* 516 */     if (arrayOfByte.length < filename_len) {
/* 517 */       arrayOfByte = new byte[filename_len];
/*     */     }
/* 519 */     zf.b(arrayOfByte, 0, filename_len);
/* 520 */     e.h = b(arrayOfByte, filename_len);
/*     */ 
/*     */ 
/*     */     
/* 524 */     if (ext_len > 0) {
/* 525 */       arrayOfByte = new byte[ext_len];
/* 526 */       zf.b(arrayOfByte, 0, ext_len);
/* 527 */       e.i = arrayOfByte;
/*     */     } 
/*     */     
/* 530 */     e.a = zf.a();
/*     */     
/* 532 */     return e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(byte[] buf, h zf, a e) throws IOException {
/* 542 */     long remain = zf.a() - e.a + e.f;
/* 543 */     if ((e.b & 0x8) == 8) {
/*     */       
/* 545 */       zf.b(buf, 0, 16);
/* 546 */       long sig = d(buf, 0);
/* 547 */       if (sig != 134695760L) {
/* 548 */         e.e = sig;
/* 549 */         e.f = d(buf, 4);
/* 550 */         e.g = d(buf, 8);
/* 551 */         zf.a(zf.a() - 4L);
/*     */       } else {
/* 553 */         e.e = d(buf, 4);
/* 554 */         e.f = d(buf, 8);
/* 555 */         e.g = d(buf, 12);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] a(byte[] buf, int len) {
/* 566 */     if (buf.length <= len) return buf; 
/* 567 */     return new byte[len];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String b(byte[] arrayOfByte, int len) throws IOException {
/* 577 */     String name = new String(arrayOfByte, 0, len, com.a.a.g.a.b(arrayOfByte, 0, len));
/* 578 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int c(byte[] arrayOfByte, int off) {
/* 586 */     return arrayOfByte[off] & 0xFF | (arrayOfByte[off + 1] & 0xFF) << 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long d(byte[] arrayOfByte, int off) {
/* 594 */     return c(arrayOfByte, off) | c(arrayOfByte, off + 2) << 16L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<b> iterator() {
/* 603 */     return c().iterator();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/b/c/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */