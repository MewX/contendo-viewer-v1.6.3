/*     */ package jp.cssj.sakae.b.b;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.io.IOException;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.pdf.d.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   implements ImageObserver, a
/*     */ {
/*     */   private BufferedImage a;
/*     */   private final String b;
/*  25 */   private int c = -1, d = -1;
/*     */   
/*     */   public b(BufferedImage image, String altString) {
/*  28 */     if (image == null) {
/*  29 */       throw new NullPointerException();
/*     */     }
/*  31 */     this.a = image;
/*  32 */     this.b = altString;
/*     */   }
/*     */   
/*     */   public b(BufferedImage image) {
/*  36 */     this(image, null);
/*     */   }
/*     */   
/*     */   public BufferedImage d() {
/*  40 */     return this.a;
/*     */   }
/*     */   
/*     */   public synchronized double a() {
/*  44 */     if (this.c == -1) {
/*  45 */       this.c = this.a.getWidth(this);
/*  46 */       while (this.c == -1) {
/*     */         try {
/*  48 */           wait(1000L);
/*  49 */         } catch (InterruptedException e) {
/*  50 */           return (this.c = 0);
/*     */         } 
/*     */       } 
/*     */     } 
/*  54 */     return this.c;
/*     */   }
/*     */   
/*     */   public synchronized double b() {
/*  58 */     if (this.d == -1) {
/*  59 */       this.d = this.a.getHeight(this);
/*  60 */       while (this.d == -1) {
/*     */         try {
/*  62 */           wait(1000L);
/*  63 */         } catch (InterruptedException e) {
/*  64 */           return (this.d = 0);
/*     */         } 
/*     */       } 
/*     */     } 
/*  68 */     return this.d;
/*     */   }
/*     */   
/*     */   public String c() {
/*  72 */     return this.b;
/*     */   }
/*     */   
/*     */   public void a(jp.cssj.sakae.c.b gc) throws c {
/*  76 */     if (gc instanceof a) {
/*     */       try {
/*  78 */         jp.cssj.sakae.c.b.b image = ((a)gc).b().d().a(this.a);
/*  79 */         gc.a(image);
/*  80 */       } catch (IOException e) {
/*  81 */         throw new c(e);
/*     */       } 
/*     */     } else {
/*  84 */       Image image = this.a;
/*  85 */       Graphics2D g2d = ((jp.cssj.sakae.b.a.b)gc).b();
/*  86 */       AffineTransform at = g2d.getTransform();
/*  87 */       g2d.drawImage(image, null, null);
/*  88 */       if (image != this.a) {
/*  89 */         g2d.setTransform(at);
/*  90 */         image.flush();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public synchronized boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
/*  96 */     if ((infoflags & 0xC0) != 0) {
/*  97 */       this.c = this.d = 0;
/*     */     } else {
/*  99 */       if ((infoflags & 0x1) != 0) {
/* 100 */         this.c = width;
/*     */       }
/* 102 */       if ((infoflags & 0x2) != 0) {
/* 103 */         this.d = height;
/*     */       }
/*     */     } 
/* 106 */     notifyAll();
/* 107 */     return (this.c == -1 || this.d == -1);
/*     */   }
/*     */   
/*     */   public synchronized void e() {
/* 111 */     if (this.a != null) {
/* 112 */       this.a.flush();
/* 113 */       this.a = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 118 */     super.finalize();
/* 119 */     e();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/b/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */