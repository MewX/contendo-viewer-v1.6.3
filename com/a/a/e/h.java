/*     */ package com.a.a.e;
/*     */ import com.a.a.f.g;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.InvalidMarkException;
/*     */ import java.nio.ReadOnlyBufferException;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public abstract class h extends c {
/*     */   a d;
/*     */   
/*     */   public static abstract class a {
/*     */     static class b {
/*     */       private boolean a;
/*     */       
/*     */       b(byte[] arrayOfByte) {
/*  20 */         this.a = false;
/*  21 */         this.b = arrayOfByte;
/*     */       }
/*     */       private byte[] b;
/*     */       boolean a() {
/*  25 */         return this.a;
/*     */       }
/*     */       
/*     */       int b() {
/*  29 */         return this.b.length;
/*     */       }
/*     */       
/*     */       private byte a(int pos) {
/*  33 */         return this.b[pos];
/*     */       }
/*     */       
/*     */       private int a(int pos, byte[] arrayOfByte, int off, int len) {
/*  37 */         int l = b() - pos;
/*  38 */         if (l > len) {
/*  39 */           l = len;
/*     */         }
/*  41 */         System.arraycopy(this.b, pos, arrayOfByte, off, l);
/*  42 */         return l;
/*     */       }
/*     */       
/*     */       private void a(int pos, byte b1) {
/*  46 */         this.b[pos] = b1;
/*  47 */         this.a = true;
/*     */       }
/*     */       
/*     */       private int b(int pos, byte[] arrayOfByte, int off, int len) {
/*  51 */         int l = b() - pos;
/*  52 */         if (l > len) {
/*  53 */           l = len;
/*     */         }
/*  55 */         System.arraycopy(arrayOfByte, off, this.b, pos, l);
/*  56 */         this.a = true;
/*  57 */         return l;
/*     */       }
/*     */     }
/*     */     
/*     */     public byte a(int pos) throws IOException {
/*  62 */       b page = b(pos);
/*  63 */       if (page == null) {
/*  64 */         throw new IOException();
/*     */       }
/*  66 */       return b.a(page, pos & this.d);
/*     */     }
/*     */     
/*     */     public int a(int pos, byte[] b, int off, int len) throws IOException {
/*  70 */       b page = b(pos);
/*  71 */       if (page != null) {
/*  72 */         return b.a(page, pos & this.d, b, off, len);
/*     */       }
/*  74 */       return -1;
/*     */     }
/*     */     
/*     */     public void a(int pos, byte b) throws IOException {
/*  78 */       b page = b(pos);
/*  79 */       if (page == null) {
/*  80 */         throw new IOException();
/*     */       }
/*  82 */       b.a(page, pos & this.d, b);
/*     */     }
/*     */     
/*     */     public int b(int pos, byte[] b, int off, int len) throws IOException {
/*  86 */       b page = b(pos);
/*  87 */       if (page != null) {
/*  88 */         return b.b(page, pos & this.d, b, off, len);
/*     */       }
/*  90 */       return -1;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     static class a
/*     */       implements d, e
/*     */     {
/*  98 */       protected HashMap<Integer, h.a.b> a = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public void a(com.a.a.f.a<?, ?> cache, Object key, Object entry) {
/* 105 */         h.a.b page = (h.a.b)entry;
/*     */         
/* 107 */         if (page.a()) {
/* 108 */           if (this.a == null) {
/* 109 */             this.a = new HashMap<Integer, h.a.b>();
/*     */           }
/* 111 */           this.a.put((Integer)key, page);
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public Object a(com.a.a.f.a<?, ?> cache, Object key) {
/* 120 */         return (this.a == null) ? null : this.a.remove(key);
/*     */       }
/*     */     }
/*     */     
/* 124 */     final String a = getClass().getSimpleName().intern();
/*     */     
/*     */     protected g<Integer, b> b;
/*     */     final int c;
/*     */     final int d;
/*     */     final int e;
/*     */     
/*     */     protected a(int size, int count) {
/* 132 */       this.b = new g(count);
/* 133 */       this.c = size;
/* 134 */       this.e = 1 << size;
/* 135 */       this.d = this.e - 1;
/*     */       
/* 137 */       this.b.a((c)new a());
/*     */     }
/*     */     
/*     */     protected abstract b b(int param1Int) throws IOException;
/*     */     
/*     */     protected abstract int a();
/*     */     
/*     */     protected void c(int size) {
/* 145 */       this.b.a(size);
/*     */     }
/*     */     
/*     */     int d(int pos) {
/* 149 */       return pos & (this.d ^ 0xFFFFFFFF);
/*     */     }
/*     */     
/*     */     int e(int pos) {
/* 153 */       return pos & this.d;
/*     */     }
/*     */     
/*     */     byte[] f(int size) {
/* 157 */       return new byte[Math.min(this.e, size)];
/*     */     }
/*     */     
/*     */     b g(int pos) {
/* 161 */       return (b)this.b.get(Integer.valueOf(pos >>> this.c));
/*     */     }
/*     */     
/*     */     b a(int pos, byte[] b) {
/* 165 */       Integer pno = Integer.valueOf(pos >>> this.c);
/* 166 */       b page = null;
/* 167 */       synchronized (this.b) {
/* 168 */         page = (b)this.b.get(pno);
/* 169 */         if (page == null) {
/* 170 */           page = new b(b);
/* 171 */           this.b.put(pno, page);
/*     */         } 
/*     */       } 
/* 174 */       return page;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class b extends a {
/* 179 */     int f = 0;
/* 180 */     InputStream g = null;
/* 181 */     Object h = new Object();
/*     */     
/*     */     protected b(int count) {
/* 184 */       super(13, count);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract InputStream b() throws IOException;
/*     */ 
/*     */ 
/*     */     
/*     */     protected void finalize() throws Throwable {
/* 194 */       if (this.g != null) {
/*     */         try {
/* 196 */           this.g.close();
/* 197 */         } catch (Exception exception) {}
/*     */         
/* 199 */         this.g = null;
/*     */       } 
/* 201 */       super.finalize();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void c() throws IOException {
/* 208 */       if (this.g != null) {
/* 209 */         h.a.c("StreamPageManager.reopen() check performance.");
/*     */         try {
/* 211 */           this.g.close();
/* 212 */         } catch (IOException iOException) {}
/*     */       } 
/*     */       
/* 215 */       this.g = null;
/*     */     }
/*     */     
/*     */     protected h.a.b b(int abspos) throws IOException {
/* 219 */       h.a.b page = g(abspos);
/* 220 */       if (page == null) {
/* 221 */         synchronized (this.h) {
/*     */           
/* 223 */           page = g(abspos);
/* 224 */           if (page != null) return page;
/*     */           
/* 226 */           int pagetop = d(abspos);
/* 227 */           if (this.g != null)
/*     */           {
/* 229 */             if (this.f > pagetop) {
/* 230 */               c();
/*     */             }
/*     */           }
/* 233 */           if (this.g == null) {
/* 234 */             this.f = 0;
/* 235 */             this.g = b();
/*     */           } 
/*     */           
/* 238 */           int nskip = pagetop - this.f;
/* 239 */           if (nskip > 0) {
/* 240 */             this.f += nskip;
/* 241 */             if (this.g.skip(nskip) != nskip) {
/* 242 */               throw new IOException();
/*     */             }
/*     */           } 
/*     */           
/* 246 */           byte[] arrayOfByte = f(a() - pagetop);
/*     */           
/* 248 */           com.a.a.b.b.b.b(this.g, arrayOfByte);
/* 249 */           this.f += arrayOfByte.length;
/*     */           
/* 251 */           if (this.f >= a()) {
/*     */             try {
/* 253 */               this.g.close();
/* 254 */             } catch (IOException iOException) {}
/*     */             
/* 256 */             this.g = null;
/*     */           } 
/*     */           
/* 259 */           page = a(pagetop, arrayOfByte);
/*     */         } 
/*     */       }
/* 262 */       return page;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h(a manager) {
/* 271 */     this.d = manager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 279 */     return this.d.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int pos, byte[] buf, int off, int len) throws IndexOutOfBoundsException {
/* 289 */     if (!b(pos, pos + len)) throw new IndexOutOfBoundsException(); 
/* 290 */     int abspos = pos + o();
/*     */     try {
/* 292 */       while (len > 0) {
/* 293 */         int n = this.d.a(abspos, buf, off, len);
/* 294 */         if (n <= 0) {
/*     */           break;
/*     */         }
/* 297 */         abspos += n;
/* 298 */         off += n;
/* 299 */         len -= n;
/*     */       } 
/* 301 */     } catch (IOException e) {
/* 302 */       throw new RuntimeException(e);
/*     */     } 
/*     */     
/* 305 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte b(int position) {
/* 313 */     if (!m(position)) throw new IndexOutOfBoundsException(); 
/* 314 */     int abspos = position + o();
/*     */     try {
/* 316 */       return this.d.a(abspos);
/* 317 */     } catch (IOException e) {
/* 318 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(int index, byte b) {
/* 326 */     if (!m(index)) throw new IndexOutOfBoundsException(); 
/* 327 */     int abspos = index + o();
/*     */     try {
/* 329 */       this.d.a(abspos, b);
/* 330 */     } catch (IOException e) {
/* 331 */       throw new RuntimeException(e);
/*     */     } 
/* 333 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/* 341 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/e/h.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */