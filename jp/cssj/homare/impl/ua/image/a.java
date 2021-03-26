/*     */ package jp.cssj.homare.impl.ua.image;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URI;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.stream.FileCacheImageOutputStream;
/*     */ import jp.cssj.b.d.b;
/*     */ import jp.cssj.b.d.c;
/*     */ import jp.cssj.e.e.b;
/*     */ import jp.cssj.f.a.b;
/*     */ import jp.cssj.f.a.e;
/*     */ import jp.cssj.f.b;
/*     */ import jp.cssj.homare.impl.ua.a;
/*     */ import jp.cssj.homare.impl.ua.d;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.b;
/*     */ import jp.cssj.homare.ua.j;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.b.a.b;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.pdf.c.c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends a
/*     */   implements j
/*     */ {
/*     */   private c c;
/*     */   private c t;
/*     */   private c u;
/*     */   private BufferedImage v;
/*  47 */   private int w = 0;
/*     */   
/*     */   public void a(c results) {
/*  50 */     this.c = results;
/*     */   }
/*     */   
/*     */   public void f(byte mode) {
/*  54 */     super.f(mode);
/*  55 */     switch (mode) {
/*     */       case 1:
/*  57 */         if (this.c != b.a) {
/*  58 */           this.t = this.c;
/*  59 */           this.c = (c)b.a;
/*     */         } 
/*  61 */         w();
/*     */         break;
/*     */       case 2:
/*  64 */         this.c = this.t;
/*  65 */         w();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void w() {
/*  71 */     this.v = null;
/*  72 */     this.u = null;
/*  73 */     this.w = 0;
/*     */   }
/*     */   
/*     */   public e q() {
/*  77 */     if (this.u == null) {
/*  78 */       this.u = new c(a().a());
/*     */     }
/*  80 */     return (e)this.u;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(String name, String content) {}
/*     */ 
/*     */   
/*     */   public b s() {
/*  88 */     b((byte)2);
/*  89 */     Point2D size = new Point2D.Double(this.a, this.b);
/*  90 */     double ppi = B.U.a((m)this);
/*  91 */     double pxPerPt = ppi / 72.0D;
/*  92 */     AffineTransform at = AffineTransform.getScaleInstance(pxPerPt, pxPerPt);
/*  93 */     at.transform(size, size);
/*  94 */     int w = (int)size.getX();
/*  95 */     int h = (int)size.getY();
/*  96 */     this.v = new BufferedImage(w, h, 1);
/*  97 */     Graphics2D g2d = (Graphics2D)this.v.getGraphics();
/*     */ 
/*     */     
/* 100 */     g2d.setColor(Color.WHITE);
/* 101 */     g2d.fillRect(0, 0, w, h);
/* 102 */     g2d.setColor(Color.BLACK);
/* 103 */     g2d.setTransform(at);
/*     */ 
/*     */     
/* 106 */     if (B.V.a((m)this)) {
/* 107 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 108 */       g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*     */     } else {
/* 110 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/* 111 */       g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
/*     */     } 
/* 113 */     return (b)new b(g2d, q());
/*     */   }
/*     */   
/*     */   public void a(b gc) throws IOException {
/* 117 */     super.a(gc);
/* 118 */     String mimeType = B.J.a((m)this);
/* 119 */     b b1 = new b(URI.create("#" + ++this.w), mimeType);
/* 120 */     jp.cssj.f.a builder = this.c.a((jp.cssj.e.a)b1);
/*     */     try {
/*     */       b b2;
/* 123 */       if (builder instanceof b) {
/* 124 */         e e = new e((b)builder);
/*     */       } else {
/* 126 */         builder.b();
/* 127 */         b2 = new b(builder, 0);
/*     */       } 
/* 129 */       try (FileCacheImageOutputStream iout = new FileCacheImageOutputStream((OutputStream)b2, null)) {
/* 130 */         Iterator<ImageWriter> i = ImageIO.getImageWritersByMIMEType(mimeType);
/* 131 */         ImageWriter writer = i.next();
/*     */         try {
/* 133 */           writer.setOutput(iout);
/* 134 */           writer.write(this.v);
/*     */         } finally {
/* 136 */           writer.dispose();
/*     */         } 
/*     */       } 
/* 139 */       builder.a();
/* 140 */     } catch (IOException e) {
/* 141 */       throw new c(e);
/*     */     } finally {
/* 143 */       builder.e();
/*     */     } 
/* 145 */     if (!this.c.a()) {
/* 146 */       throw new jp.cssj.homare.ua.a((byte)1);
/*     */     }
/* 148 */     b((byte)1);
/*     */   }
/*     */   
/*     */   public void t() throws b, IOException {
/* 152 */     super.t();
/* 153 */     this.c.c();
/*     */   }
/*     */   
/*     */   public jp.cssj.homare.b.g.a b(b gc) {
/* 157 */     return (jp.cssj.homare.b.g.a)new d((m)this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/image/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */