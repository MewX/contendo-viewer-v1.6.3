/*     */ package jp.cssj.homare.impl.ua.svg;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics2D;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.net.URI;
/*     */ import jp.cssj.b.d.b;
/*     */ import jp.cssj.b.d.c;
/*     */ import jp.cssj.e.a;
/*     */ import jp.cssj.e.e.b;
/*     */ import jp.cssj.f.a;
/*     */ import jp.cssj.f.a.b;
/*     */ import jp.cssj.f.a.e;
/*     */ import jp.cssj.f.b;
/*     */ import jp.cssj.homare.b.g.a;
/*     */ import jp.cssj.homare.impl.ua.a;
/*     */ import jp.cssj.homare.impl.ua.d;
/*     */ import jp.cssj.homare.ua.a;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.b;
/*     */ import jp.cssj.homare.ua.j;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.b.a.b;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.pdf.c.c;
/*     */ import org.apache.batik.dom.GenericDOMImplementation;
/*     */ import org.apache.batik.svggen.SVGGraphics2D;
/*     */ import org.w3c.dom.DOMImplementation;
/*     */ import org.w3c.dom.Document;
/*     */ 
/*     */ public class l
/*     */   extends a
/*     */   implements j
/*     */ {
/*     */   private c c;
/*     */   private c t;
/*     */   private c u;
/*     */   private SVGGraphics2D v;
/*  44 */   private int w = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(c results) {
/*  51 */     this.c = results;
/*     */   }
/*     */   
/*     */   public void f(byte mode) {
/*  55 */     super.f(mode);
/*  56 */     switch (mode) {
/*     */       case 1:
/*  58 */         if (this.c != b.a) {
/*  59 */           this.t = this.c;
/*  60 */           this.c = (c)b.a;
/*     */         } 
/*  62 */         w();
/*     */         break;
/*     */       case 2:
/*  65 */         this.c = this.t;
/*  66 */         w();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void w() {
/*  72 */     this.v = null;
/*  73 */     this.u = null;
/*  74 */     this.w = 0;
/*     */   }
/*     */   
/*     */   public e q() {
/*  78 */     if (this.u == null) {
/*  79 */       this.u = new c(a().a());
/*     */     }
/*  81 */     return (e)this.u;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(String name, String content) {}
/*     */ 
/*     */   
/*     */   public b s() {
/*  89 */     b((byte)2);
/*  90 */     Dimension dim = new Dimension((int)this.a, (int)this.b);
/*     */     
/*  92 */     DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
/*  93 */     Document doc = domImpl.createDocument(null, "svg", null);
/*  94 */     this.v = new SVGGraphics2D(doc);
/*  95 */     this.v.setSVGCanvasSize(dim);
/*  96 */     b gc = new b((Graphics2D)this.v, (e)this.u);
/*  97 */     return (b)gc;
/*     */   }
/*     */   
/*     */   public void a(b gc) throws IOException {
/* 101 */     super.a(gc);
/* 102 */     String mimeType = B.J.a((m)this);
/* 103 */     b b1 = new b(URI.create("#" + ++this.w), mimeType);
/* 104 */     a builder = this.c.a((a)b1);
/*     */     try {
/*     */       b b2;
/* 107 */       if (builder instanceof b) {
/* 108 */         e e = new e((b)builder);
/*     */       } else {
/* 110 */         builder.b();
/* 111 */         b2 = new b(builder, 0);
/*     */       } 
/* 113 */       try (Writer writer = new OutputStreamWriter((OutputStream)b2, "UTF-8")) {
/* 114 */         this.v.stream(writer, true);
/*     */       } 
/* 116 */       builder.a();
/* 117 */     } catch (IOException e) {
/* 118 */       throw new c(e);
/*     */     } finally {
/* 120 */       builder.e();
/*     */     } 
/* 122 */     if (!this.c.a()) {
/* 123 */       throw new a((byte)1);
/*     */     }
/* 125 */     b((byte)1);
/*     */   }
/*     */   
/*     */   public void t() throws b, IOException {
/* 129 */     super.t();
/* 130 */     this.c.c();
/*     */   }
/*     */   
/*     */   public a b(b gc) {
/* 134 */     return (a)new d((m)this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/l.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */