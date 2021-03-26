/*     */ package com.a.a.j.c;
/*     */ 
/*     */ import com.a.a.b.c.c;
/*     */ import com.a.a.d.b;
/*     */ import com.a.a.e.b;
/*     */ import com.a.a.j.b.b;
/*     */ import com.a.a.j.b.c;
/*     */ import com.a.a.j.b.d;
/*     */ import com.a.a.j.c;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends l
/*     */   implements g
/*     */ {
/*     */   c a;
/*     */   c b;
/*     */   File c;
/*     */   
/*     */   private class a
/*     */     extends e
/*     */   {
/*     */     b a;
/*     */     
/*     */     public a(a this$0, m folder, String path, b exfile) {
/*  58 */       super(folder, path);
/*  59 */       this.a = exfile;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InputStream d() throws IOException {
/*  67 */       g f = this.b.b.c(this.d);
/*  68 */       if (f != null) {
/*  69 */         return f.d();
/*     */       }
/*     */ 
/*     */       
/*  73 */       return this.a.a((com.a.a.j.a)this.b.b);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public b e() throws IOException {
/*  81 */       g f = this.b.b.c(this.d);
/*  82 */       if (f != null) {
/*  83 */         return f.e();
/*     */       }
/*  85 */       b buf = this.a.b((com.a.a.j.a)this.b.b);
/*  86 */       if (buf != null) return buf; 
/*  87 */       return super.e();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long f() {
/*  95 */       return this.a.d((com.a.a.j.a)this.b.b);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean g() {
/* 103 */       return this.a.f((com.a.a.j.a)this.b.b);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean h() {
/* 111 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean i() {
/* 119 */       Thread th = new Thread(this) {  }
/*     */         ;
/* 121 */       th.start();
/*     */       
/* 123 */       return super.i();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean k() {
/* 131 */       return this.a.g((com.a.a.j.a)this.b.b);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InputStream l() throws IOException {
/* 139 */       if (k()) {
/* 140 */         return c.a(this.a.h((com.a.a.j.a)this.b.b), 
/* 141 */             this.a.i((com.a.a.j.a)this.b.b), null);
/*     */       }
/* 143 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long m() {
/*     */       try {
/* 152 */         if (k()) {
/* 153 */           return c.a(this.a.i((com.a.a.j.a)this.b.b), null);
/*     */         }
/* 155 */       } catch (IOException iOException) {}
/*     */       
/* 157 */       return -1L;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static final String[] f = new String[] { d.class.getName() };
/*     */ 
/*     */   
/*     */   static ArrayList<Class<? extends c>> d = new ArrayList<Class<? extends c>>();
/*     */ 
/*     */   
/*     */   static SoftReference<ArrayList<b>> e = new SoftReference<ArrayList<b>>(null);
/*     */ 
/*     */   
/*     */   public static a a(String path, g archive, c handler) {
/* 172 */     d d = new d();
/* 173 */     if (d.a((com.a.a.j.a)handler)) {
/* 174 */       return new a(path, (c)d, handler);
/*     */     }
/*     */ 
/*     */     
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   c a(String name) {
/* 187 */     c ext = null;
/*     */     
/* 189 */     try { Class<?> cls = Class.forName(name);
/* 190 */       if (!cls.isAssignableFrom(c.class)) {
/* 191 */         ext = (c)cls.newInstance();
/*     */       } }
/* 193 */     catch (ClassNotFoundException classNotFoundException) {  }
/* 194 */     catch (InstantiationException instantiationException) {  }
/* 195 */     catch (IllegalAccessException illegalAccessException) {}
/*     */     
/* 197 */     return ext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   a(String path, c extractor, c handler) {
/* 206 */     this.c = new File(path);
/* 207 */     this.a = extractor;
/* 208 */     this.b = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String b(String path) {
/* 216 */     return (new File(this.c, path)).getAbsolutePath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g c(String path) throws FileNotFoundException {
/* 223 */     if (path == null || path.length() == 0) {
/* 224 */       return n();
/*     */     }
/*     */     
/* 227 */     g file = null;
/* 228 */     if (this.b != null && this.a != null) {
/* 229 */       b exfile = this.a.a((com.a.a.j.a)this.b, new File(path));
/* 230 */       if (exfile != null) {
/* 231 */         file = new a(this, this, path, exfile);
/*     */       }
/*     */     } 
/* 234 */     return file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() {
/* 242 */     if (this.b != null) {
/* 243 */       this.b.c();
/* 244 */       this.b = null;
/*     */     } 
/* 246 */     this.a = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] a(String path, boolean subdir) {
/* 254 */     File f = null;
/* 255 */     if (path != null && path.length() > 0) {
/* 256 */       f = new File(path);
/*     */     }
/* 258 */     File[] list = this.a.b((com.a.a.j.a)this.b, f);
/* 259 */     ArrayList<String> result = new ArrayList<String>();
/* 260 */     for (int i = 0; i < list.length; i++) {
/* 261 */       if (f == null || !f.equals(list[i]))
/*     */       {
/* 263 */         result.add(list[i].getAbsolutePath());
/*     */       }
/*     */     } 
/* 266 */     return result.<String>toArray(new String[result.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String b() {
/* 274 */     return this.b.c(null).b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public m c() {
/* 282 */     return this.b.c(null).c();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream d() throws IOException {
/* 290 */     return this.b.c(null).d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b e() throws IOException {
/* 298 */     return this.b.b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long f() {
/* 306 */     return this.b.c(null).f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean g() {
/* 314 */     return this.b.c(null).g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean h() {
/* 322 */     return this.b.c(null).h();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean i() {
/* 330 */     return this.b.c(null).i();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b j() {
/* 338 */     return this.b.c(null).j();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(b type) {
/* 347 */     for (b mime : r()) {
/* 348 */       if (mime.equals(type)) return true; 
/*     */     } 
/* 350 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<Class<? extends c>> q() {
/* 358 */     synchronized (d) {
/* 359 */       if (d.isEmpty()) {
/* 360 */         ClassLoader cl = Thread.currentThread().getContextClassLoader(); byte b; int i;
/*     */         String[] arrayOfString;
/* 362 */         for (i = (arrayOfString = f).length, b = 0; b < i; ) { String name = arrayOfString[b];
/*     */           try {
/* 364 */             Class<?> clazz = cl.loadClass(name);
/* 365 */             if (!clazz.isInterface() && c.class.isAssignableFrom(clazz)) {
/* 366 */               d.add(clazz);
/*     */             }
/* 368 */           } catch (Exception exception) {}
/*     */           
/*     */           b++; }
/*     */       
/*     */       } 
/*     */     } 
/*     */     
/* 375 */     return d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArrayList<b> r() {
/* 382 */     ArrayList<b> list = null;
/*     */ 
/*     */     
/* 385 */     synchronized (e) {
/* 386 */       list = e.get();
/* 387 */       if (list == null) {
/* 388 */         list = new ArrayList<b>();
/*     */         
/* 390 */         for (Class<? extends c> cls : q()) {
/*     */           try {
/* 392 */             c extractor = cls.newInstance();
/* 393 */             list.addAll(extractor.a());
/* 394 */           } catch (Exception exception) {}
/*     */         } 
/*     */       } 
/*     */       
/* 398 */       e = new SoftReference<ArrayList<b>>(list);
/*     */     } 
/*     */     
/* 401 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean k() {
/* 409 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream l() throws IOException {
/* 417 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long m() {
/* 425 */     return -1L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g n() {
/* 433 */     return this.b.c(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL o() throws MalformedURLException {
/* 441 */     return e.b(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */