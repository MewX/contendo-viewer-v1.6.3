/*     */ package com.a.a.j;
/*     */ 
/*     */ import com.a.a.j.a.a;
/*     */ import com.a.a.j.c.a;
/*     */ import com.a.a.j.c.a.c;
/*     */ import com.a.a.j.c.g;
/*     */ import com.a.a.j.c.h;
/*     */ import com.a.a.j.c.k;
/*     */ import com.a.a.j.c.m;
/*     */ import com.a.a.j.c.o;
/*     */ import com.a.a.j.d.a;
/*     */ import com.a.a.j.d.a.a;
/*     */ import com.a.a.j.d.a.b;
/*     */ import com.a.a.j.d.a.c;
/*     */ import com.a.a.j.d.b;
/*     */ import com.a.a.j.d.d;
/*     */ import com.a.a.j.d.g;
/*     */ import com.a.a.j.d.h;
/*     */ import com.a.a.j.d.i;
/*     */ import com.a.a.j.d.j;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */   extends d
/*     */ {
/*     */   private static final boolean i = true;
/*     */   private WeakReference<Object> j;
/*  62 */   private File k = null;
/*     */ 
/*     */   
/*  65 */   private Map<String, m> l = new WeakHashMap<String, m>();
/*     */   
/*     */   private File m;
/*     */   
/*  69 */   private Map<String, SoftReference<g>> n = Collections.synchronizedMap(new HashMap<String, SoftReference<g>>());
/*     */ 
/*     */   
/*  72 */   private WeakHashMap<String, Collection<String>> o = new WeakHashMap<String, Collection<String>>();
/*     */ 
/*     */   
/*  75 */   private j p = null;
/*     */ 
/*     */   
/*     */   private b q;
/*     */ 
/*     */ 
/*     */   
/*     */   private static class d
/*     */     implements i
/*     */   {
/*     */     private static final int c = 1000;
/*     */ 
/*     */     
/*     */     private final long d;
/*     */ 
/*     */     
/*     */     final String a;
/*     */ 
/*     */     
/*     */     final byte[] b;
/*     */ 
/*     */ 
/*     */     
/*     */     d() {
/*  99 */       byte[] b = new byte[32];
/* 100 */       e.f.nextBytes(b);
/* 101 */       this.a = com.a.a.d.a.b(b, 10);
/* 102 */       this.b = this.a.getBytes();
/*     */ 
/*     */       
/* 105 */       this.d = System.currentTimeMillis() + 1000L;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public byte[] a() {
/* 113 */       return this.b;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean a(long time) {
/* 121 */       return (this.d < time);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 126 */   private LinkedList<i> r = new LinkedList<i>();
/*     */ 
/*     */   
/* 129 */   private d.b s = null;
/*     */ 
/*     */   
/* 132 */   private File t = null;
/*     */   
/*     */   final d.d h;
/*     */ 
/*     */   
/*     */   public static class a
/*     */     implements b
/*     */   {
/*     */     public d a(Object context, d.d keyStore) throws IOException {
/* 141 */       return new e(context, keyStore, null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/*     */     try {
/* 152 */       if (this.p != null) {
/* 153 */         this.p.interrupt();
/* 154 */         this.p = null;
/* 155 */         this.q = null;
/*     */       } 
/* 157 */       if (this.l != null) {
/* 158 */         for (String key : this.l.keySet()) {
/* 159 */           synchronized (key) {
/* 160 */             m fo = this.l.get(key);
/* 161 */             if (fo != null) {
/* 162 */               fo.a();
/*     */             }
/*     */           } 
/*     */         } 
/* 166 */         this.l.clear();
/* 167 */         this.l = null;
/*     */       } 
/* 169 */       if (this.n != null) {
/* 170 */         this.n.clear();
/* 171 */         this.n = null;
/*     */       } 
/*     */     } finally {
/* 174 */       super.finalize();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File c() {
/* 183 */     if (this.k == null) {
/* 184 */       Object context = this.j.get();
/* 185 */       this.k = b(context);
/*     */     } 
/* 187 */     return this.k;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected File b(Object context) {
/* 195 */     if (this.s != null)
/* 196 */       return this.s.a(context); 
/* 197 */     if (context instanceof d.b)
/* 198 */       return ((d.b)context).a(context); 
/* 199 */     if (this.t != null) {
/* 200 */       return this.t;
/*     */     }
/* 202 */     return b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(File cacheDir) {
/* 210 */     this.t = cacheDir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(d.b resolver) {
/* 218 */     this.s = resolver;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class b
/*     */     implements d.d
/*     */   {
/*     */     private String a;
/*     */     
/*     */     private char[] b;
/*     */ 
/*     */     
/*     */     public b(String path, char[] password) {
/* 231 */       this.a = path;
/* 232 */       this.b = password;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InputStream a(Object context) {
/* 240 */       return getClass().getClassLoader().getResourceAsStream(this.a);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char[] b(Object context) {
/* 248 */       return this.b;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 254 */   private LinkedList<k.a> u = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private e(Object context, d.d keystore) throws IOException {
/* 262 */     this.m = new File("/");
/* 263 */     this.j = new WeakReference(context);
/* 264 */     this.h = keystore;
/*     */     
/* 266 */     this.q = new b(this)
/*     */       {
/*     */         public synchronized String a(String text) {
/* 269 */           long time = System.currentTimeMillis();
/* 270 */           String key = null;
/*     */           
/* 272 */           String[] parse = text.split(" ");
/* 273 */           int cnt = 0; byte b1; int i; String[] arrayOfString1;
/* 274 */           for (i = (arrayOfString1 = parse).length, b1 = 0; b1 < i; ) { String v = arrayOfString1[b1];
/* 275 */             if (v.length() != 0)
/* 276 */               switch (cnt++) {
/*     */                 case 0:
/* 278 */                   if (!v.equalsIgnoreCase("Basic")) {
/* 279 */                     return null;
/*     */                   }
/*     */                   break;
/*     */                 case 1:
/* 283 */                   key = v;
/*     */                   break;
/*     */               }  
/*     */             b1++; }
/*     */           
/* 288 */           if (key != null) {
/* 289 */             byte[] arrayOfByte = com.a.a.d.a.a(key, 10);
/*     */             
/* 291 */             ListIterator<i> it = e.a(this.a).listIterator();
/* 292 */             while (it.hasNext()) {
/* 293 */               i otk = it.next();
/* 294 */               if (otk.a(time)) {
/* 295 */                 it.remove(); continue;
/* 296 */               }  if (a(otk.a(), arrayOfByte)) {
/* 297 */                 return "OK";
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 302 */           return null;
/*     */         }
/*     */         
/*     */         private boolean a(byte[] a, byte[] arrayOfByte1) {
/* 306 */           if (a == arrayOfByte1)
/* 307 */             return true; 
/* 308 */           if (a == null || arrayOfByte1 == null) {
/* 309 */             return false;
/*     */           }
/* 311 */           int length = a.length;
/* 312 */           if (arrayOfByte1.length < length) {
/* 313 */             return false;
/*     */           }
/* 315 */           for (int i = 0; i < length; i++) {
/* 316 */             if (a[i] != arrayOfByte1[i])
/* 317 */               return false; 
/*     */           } 
/* 319 */           return true;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public synchronized String[] a() {
/* 327 */           e.d otk = new e.d();
/* 328 */           e.a(this.a).add(otk);
/*     */ 
/*     */           
/* 331 */           return new String[] { e.b(this.a).d(), e.b(this.a).e(), otk.a, "" };
/*     */         }
/*     */       };
/*     */     
/* 335 */     if (this.h != null) {
/* 336 */       InputStream is = this.h.a(context);
/* 337 */       if (is != null) {
/*     */         try {
/* 339 */           a.warn("Keystore found.");
/* 340 */           this.p = (j)new g(this, 0, this.q);
/* 341 */           ((g)this.p).a(is, this.h.b(context));
/* 342 */           this.p.start();
/* 343 */           if (!h()) {
/* 344 */             this.p = null;
/*     */           }
/* 346 */         } catch (Exception exception) {
/* 347 */           this.p = null;
/*     */         } finally {
/* 349 */           is.close();
/*     */         } 
/*     */       }
/*     */     } 
/* 353 */     if (this.p == null || !this.p.isAlive()) {
/*     */       
/* 355 */       this.p = (j)new a(this, 0, this.q);
/*     */ 
/*     */ 
/*     */       
/* 359 */       this.p.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static class c
/*     */     extends Thread
/*     */   {
/*     */     final h a;
/*     */     
/*     */     boolean b = false;
/*     */     
/*     */     c(h server) {
/* 372 */       this.a = server;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       try {
/* 378 */         URL url = new URL(this.a.b().toString());
/* 379 */         e.a.debug("Connect test : " + url.toString());
/* 380 */         HttpURLConnection con = (HttpURLConnection)url.openConnection();
/* 381 */         if (con != null) {
/*     */           try {
/* 383 */             con.setRequestMethod("HEAD");
/* 384 */             con.setConnectTimeout(1000);
/* 385 */             con.connect();
/* 386 */             this.b = true;
/*     */           } finally {
/* 388 */             con.disconnect();
/*     */           } 
/*     */         }
/* 391 */         e.a.debug("Connect test : OK");
/* 392 */       } catch (Exception e) {
/* 393 */         e.a.warn("Connect test : " + e.toString());
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 398 */       return this.b;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean h() {
/* 407 */     c thread = new c((h)this.p);
/*     */     
/* 409 */     thread.start();
/* 410 */     while (this.p.isAlive() && thread.isAlive()) {
/*     */       try {
/* 412 */         thread.join(200L);
/* 413 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */     
/* 416 */     if (thread.isAlive()) thread.interrupt();
/*     */     
/* 418 */     return thread.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<g> a(File file, boolean subdir, boolean extract) throws IOException {
/* 426 */     return a(new ArrayList<g>(), c(file), subdir, 10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<g> a(String path, boolean subdir, boolean extract) throws IOException {
/* 434 */     return a(new ArrayList<g>(), b(path), subdir, 10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<g> a(URI uri, boolean subdir, boolean extract) throws IOException {
/* 442 */     return a(new ArrayList<g>(), b(uri), subdir, 10);
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
/*     */   protected List<g> a(List<g> list, g file, boolean subdir, int extract) throws IOException {
/* 454 */     return a(list, file, file.c(), subdir, extract);
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
/*     */   protected List<g> a(List<g> list, g file, m folder, boolean subdir, int extract) throws IOException {
/* 468 */     if (extract > 0 && file != null && a.a(file.j())) {
/* 469 */       String path = file.a_();
/* 470 */       a archive = a.a(path, file, a(path, file));
/* 471 */       if (archive != null) {
/* 472 */         if (subdir) {
/* 473 */           list = a(list, (g)null, (m)archive, subdir, extract - 1);
/*     */         } else {
/* 475 */           list.add(archive);
/*     */         } 
/* 477 */         return list;
/*     */       } 
/*     */     } 
/*     */     
/* 481 */     String[] l = folder.a(file, subdir);
/* 482 */     if (l.length == 0) {
/* 483 */       if (file == null) {
/* 484 */         if (folder instanceof g) {
/* 485 */           list.add((g)folder);
/*     */         }
/*     */       } else {
/* 488 */         list.add(file);
/*     */       } 
/*     */     } else {
/* 491 */       byte b1; int i; String[] arrayOfString; for (i = (arrayOfString = l).length, b1 = 0; b1 < i; ) { String n = arrayOfString[b1];
/* 492 */         g fi = a(folder, n);
/* 493 */         list.add(fi);
/*     */         b1++; }
/*     */     
/*     */     } 
/* 497 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI a(String path) {
/* 505 */     return com.a.a.d.c.a(d(), path);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI a(URI uri) {
/* 513 */     String path = uri.getPath();
/* 514 */     String query = uri.getQuery();
/* 515 */     if (query != null) path = String.valueOf(path) + "?" + query; 
/* 516 */     return a(path);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g b(String path) throws IOException {
/* 524 */     return c(new File(path));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g c(File file) throws IOException {
/* 532 */     return b(file.toURI());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public g b(URI uri) throws IOException {
/*     */     try {
/* 544 */       URI current = (new File(".")).toURI();
/* 545 */       uri = current.relativize(uri);
/* 546 */       String path = uri.getPath().intern();
/*     */ 
/*     */       
/* 549 */       List<String> segments = com.a.a.d.c.a(uri);
/*     */ 
/*     */       
/* 552 */       List<String> t = a(segments);
/* 553 */       if (t != segments) {
/* 554 */         segments = t;
/* 555 */         path = b(t).intern();
/*     */       } 
/*     */       
/* 558 */       synchronized (path) {
/*     */         o o;
/* 560 */         SoftReference<g> ref = this.n.get(path);
/* 561 */         if (ref != null) {
/* 562 */           g rtn = ref.get();
/* 563 */           if (rtn != null) {
/* 564 */             if (b) a.debug("File Cache Hit. path = " + path); 
/* 565 */             return rtn;
/*     */           } 
/*     */ 
/*     */           
/* 569 */           this.n.remove(ref);
/*     */         } 
/*     */         
/* 572 */         g finfo = null;
/*     */ 
/*     */         
/* 575 */         File rfile = this.m;
/*     */ 
/*     */         
/* 578 */         File vfile = null;
/*     */         
/* 580 */         m folder = null;
/*     */         
/* 582 */         int max = segments.size();
/* 583 */         int start = max;
/* 584 */         int skip = 0;
/*     */ 
/*     */         
/* 587 */         for (File f = new File(path); f != null; f = f.getParentFile(), start--) {
/* 588 */           String p = f.getAbsolutePath().intern();
/* 589 */           folder = this.l.get(p);
/* 590 */           if (folder != null) {
/* 591 */             if (b) a.debug("Folder Cache Hit. path = " + p); 
/*     */             break;
/*     */           } 
/* 594 */           if (f.isDirectory()) {
/* 595 */             rfile = f;
/* 596 */             skip = start;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         
/* 602 */         if (folder == null)
/*     */         {
/* 604 */           o = new o(rfile);
/*     */         }
/*     */         
/* 607 */         finfo = a((m)o, "");
/*     */ 
/*     */         
/* 610 */         int cnt = 0;
/* 611 */         for (String seg : segments) {
/* 612 */           if (cnt >= skip) {
/* 613 */             rfile = new File(rfile, seg);
/* 614 */             if (cnt >= start) {
/* 615 */               vfile = new File(vfile, seg);
/*     */               
/* 617 */               finfo = a((m)o, vfile.getPath());
/*     */ 
/*     */               
/* 620 */               if (finfo == null) {
/* 621 */                 if (b) a.debug("getFile() path=" + path + " File not found. folder=" + o.getClass().getSimpleName()); 
/* 622 */                 throw new FileNotFoundException();
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 627 */               if (!finfo.g() && cnt < max - 1) {
/* 628 */                 m m; String folder_path = rfile.getAbsolutePath().intern();
/*     */ 
/*     */                 
/* 631 */                 synchronized (folder_path) {
/*     */                   
/* 633 */                   m = this.l.get(folder_path);
/*     */ 
/*     */                   
/* 636 */                   if (m == null) {
/* 637 */                     m = a(finfo, rfile.getAbsolutePath());
/*     */ 
/*     */                     
/* 640 */                     if (m != null) {
/* 641 */                       this.l.put(folder_path, m);
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */                 
/* 647 */                 if (m == null) {
/* 648 */                   if (b) {
/* 649 */                     a.debug("getFile() path=" + path + " Archive not found. folder=" + folder_path + " , file=" + finfo.getClass().getSimpleName());
/*     */                   }
/* 651 */                   throw new FileNotFoundException();
/*     */                 } 
/*     */                 
/* 654 */                 vfile = null;
/*     */               } 
/*     */             } 
/*     */           } 
/* 658 */           cnt++;
/*     */         } 
/*     */ 
/*     */         
/* 662 */         if (finfo != null) {
/* 663 */           this.n.put(path, new SoftReference<g>(finfo));
/*     */         }
/*     */         
/* 666 */         return finfo;
/*     */       } 
/* 668 */     } catch (IOException ioe) {
/* 669 */       throw ioe;
/* 670 */     } catch (Throwable th) {
/* 671 */       if (b) a.error("getFile()", th); 
/* 672 */       throw new IOException(th.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private g a(m folder, String path) throws IOException {
/* 679 */     g file = folder.c(path);
/* 680 */     if (file != null && this.u != null && !this.u.isEmpty())
/*     */     {
/* 682 */       file = k.a(file, this.j.get(), this.u);
/*     */     }
/* 684 */     return file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<String> a(List<String> list) {
/* 693 */     if (list != null && !list.isEmpty()) {
/* 694 */       String path = ((String)list.get(0)).intern();
/* 695 */       Collection<String> orig = c(path);
/* 696 */       if (orig != null) {
/* 697 */         List<String> tmp = new ArrayList<String>();
/* 698 */         tmp.addAll(orig);
/* 699 */         tmp.addAll(list.subList(1, list.size()));
/* 700 */         list = tmp;
/* 701 */         if (b) {
/* 702 */           a.debug("aliasToOriginal() " + path + " -> " + orig);
/*     */         }
/*     */       } 
/*     */     } 
/* 706 */     return list;
/*     */   }
/*     */   
/*     */   private Collection<String> c(String segment) {
/* 710 */     synchronized (this.o) {
/* 711 */       return this.o.get(segment);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String b(List<String> list) {
/* 716 */     StringBuilder sb = new StringBuilder();
/* 717 */     if (list != null) {
/* 718 */       for (String s : list) {
/* 719 */         if (sb.length() != 0) sb.append("/"); 
/* 720 */         sb.append(s);
/*     */       } 
/*     */     }
/* 723 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(d resolver) {
/* 731 */     this.p.a(resolver);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(d resolver) {
/* 739 */     this.p.b(resolver);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI d() {
/* 747 */     return (this.p == null) ? null : this.p.b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String e() {
/* 755 */     return (this.p == null) ? null : this.p.d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String c(URI uri) throws IOException {
/* 764 */     String path = uri.getPath();
/* 765 */     Collection<String> org = c(uri.getPath());
/* 766 */     if (org != null) {
/* 767 */       return path;
/*     */     }
/*     */ 
/*     */     
/* 771 */     URI current = (new File(".")).toURI();
/* 772 */     uri = current.relativize(uri);
/*     */     
/* 774 */     g file = b(uri);
/* 775 */     if (file != null) {
/* 776 */       List<String> segments = com.a.a.d.c.a(uri);
/* 777 */       int size = segments.size();
/*     */       
/* 779 */       synchronized (this.o) {
/*     */         
/* 781 */         for (Map.Entry<String, Collection<String>> entry : this.o.entrySet()) {
/* 782 */           Collection<String> tmp = entry.getValue();
/* 783 */           if (tmp.size() == size) {
/* 784 */             Iterator<String> it1 = segments.iterator();
/* 785 */             Iterator<String> it2 = tmp.iterator();
/* 786 */             boolean match = true;
/* 787 */             for (int i = 0; i < size; i++) {
/* 788 */               if (((String)it1.next()).equals(it2.next())) {
/* 789 */                 match = false;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 793 */             if (match) return entry.getKey();
/*     */           
/*     */           } 
/*     */         } 
/*     */         
/* 798 */         String exttmp = segments.get(size - 1);
/* 799 */         int n = exttmp.lastIndexOf(".");
/* 800 */         String ext = "";
/* 801 */         if (n >= 0) {
/* 802 */           ext = exttmp.substring(n);
/*     */         }
/*     */ 
/*     */         
/*     */         while (true) {
/* 807 */           String alias = String.valueOf(a(8, 32)) + ext;
/* 808 */           if (!this.o.containsKey(alias) && !(new File(alias)).exists()) {
/* 809 */             this.o.put(alias, segments);
/* 810 */             return alias;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 815 */     throw new FileNotFoundException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String a(int minlen, int maxlen) {
/* 825 */     return com.a.a.h.a.b.a(f, minlen, maxlen, c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] f() {
/* 833 */     return (this.q == null) ? null : this.q.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object a(String[] onetimepass) {
/* 842 */     return (this.q == null) ? null : 
/* 843 */       this.p.a("Basic " + com.a.a.d.a.b(onetimepass[2].getBytes(), 10));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<com.a.a.d.b> d(URI uri) {
/* 851 */     Collection<com.a.a.d.b> result = null;
/*     */     try {
/* 853 */       g file = b(uri);
/* 854 */       if (file != null) {
/* 855 */         if (file.g()) {
/* 856 */           result = Arrays.asList(new com.a.a.d.b[] { d.d });
/*     */         } else {
/* 858 */           result = com.a.a.d.b.a(file.o());
/*     */         } 
/*     */       }
/* 861 */     } catch (IOException iOException) {}
/*     */     
/* 863 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private m a(g archive, String path) throws FileNotFoundException {
/*     */     a a;
/* 874 */     m folder = null;
/* 875 */     if (a.a(archive.j()))
/*     */     {
/* 877 */       a = a.a(path, archive, a(path, archive));
/*     */     }
/* 879 */     return (m)a;
/*     */   }
/*     */ 
/*     */   
/*     */   private c a(String path, g file) {
/* 884 */     return new c(file, new c(c()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(String url, String path) {
/* 892 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h b(String url, String path) throws c {
/*     */     try {
/* 902 */       return (h)b(path);
/* 903 */     } catch (FileNotFoundException fnfe) {
/* 904 */       throw new b(fnfe);
/* 905 */     } catch (a ae) {
/* 906 */       throw new a(ae);
/* 907 */     } catch (IOException iOException) {
/* 908 */       throw new c(iOException);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void a(k.a creator) {
/* 917 */     if (this.u == null) {
/* 918 */       this.u = new LinkedList<k.a>();
/*     */     }
/* 920 */     this.u.remove(creator);
/* 921 */     this.u.addFirst(creator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h g() {
/* 929 */     return (h)this.p;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */