/*     */ package jp.cssj.sakae.pdf.b;
/*     */ 
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import jp.cssj.sakae.pdf.h;
/*     */ import jp.cssj.sakae.pdf.i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends a
/*     */ {
/*     */   protected URI a;
/*     */   
/*     */   public URI c() {
/*  21 */     return this.a;
/*     */   }
/*     */   
/*     */   public void a(URI uri) {
/*  25 */     this.a = uri;
/*     */   }
/*     */   
/*     */   public void a(h out, i pageOut) throws IOException {
/*  29 */     super.a(out, pageOut);
/*     */ 
/*     */     
/*  32 */     out.a("Border");
/*  33 */     out.i();
/*  34 */     out.a(0);
/*  35 */     out.a(0);
/*  36 */     out.a(0);
/*  37 */     out.j();
/*  38 */     out.k();
/*     */     
/*  40 */     out.a("Subtype");
/*  41 */     out.a("Link");
/*  42 */     out.k();
/*     */     
/*  44 */     int pdfVersion = pageOut.d().a().h();
/*  45 */     if (pdfVersion >= 1600 && !this.c.equals(this.c.getBounds2D())) {
/*     */       
/*  47 */       double[] cord = new double[6];
/*     */ 
/*     */       
/*  50 */       int corners = 0;
/*  51 */       boolean bad = false, rect = true;
/*  52 */       double x = 0.0D, y = 0.0D;
/*  53 */       for (PathIterator pathIterator = this.c.getPathIterator(null); !pathIterator.isDone(); pathIterator.next()) {
/*  54 */         int type = pathIterator.currentSegment(cord);
/*  55 */         switch (type) {
/*     */           case 1:
/*  57 */             if (x != cord[0] && y != cord[1]) {
/*  58 */               rect = false;
/*     */             }
/*  60 */             x = cord[0];
/*  61 */             y = cord[1];
/*  62 */             corners++;
/*     */             break;
/*     */           case 0:
/*  65 */             x = cord[0];
/*  66 */             y = cord[1];
/*  67 */             corners++;
/*     */             break;
/*     */           case 2:
/*     */           case 3:
/*  71 */             bad = true;
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/*  79 */       if (!rect && !bad && corners == 4) {
/*  80 */         out.a("QuadPoints");
/*  81 */         out.i();
/*  82 */         double pageHeight = pageOut.b();
/*  83 */         for (PathIterator pathIterator1 = this.c.getPathIterator(null); !pathIterator1.isDone(); pathIterator1.next()) {
/*  84 */           int type = pathIterator1.currentSegment(cord);
/*  85 */           if (type == 0 || type == 1) {
/*  86 */             x = cord[0];
/*  87 */             y = cord[1];
/*  88 */             out.a(x);
/*  89 */             out.a(pageHeight - y);
/*     */           } 
/*     */         } 
/*  92 */         out.j();
/*  93 */         out.k();
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     if (this.a.toString().startsWith("#")) {
/*  98 */       out.a("Dest");
/*  99 */       out.e(this.a.getFragment());
/* 100 */       out.k();
/*     */     } else {
/* 102 */       out.a("A");
/* 103 */       out.g();
/* 104 */       out.a("S");
/* 105 */       out.a("URI");
/* 106 */       out.k();
/* 107 */       out.a("URI");
/* 108 */       out.c(this.a.toASCIIString());
/* 109 */       out.h();
/* 110 */       out.k();
/*     */     } 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 115 */     return "Link: " + this.a;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */