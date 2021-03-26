/*     */ package jp.cssj.homare.impl.objects.barcode.a;
/*     */ 
/*     */ import org.krysalis.barcode4j.BarGroup;
/*     */ import org.krysalis.barcode4j.BarcodeDimension;
/*     */ import org.krysalis.barcode4j.ClassicBarcodeLogicHandler;
/*     */ import org.krysalis.barcode4j.HumanReadablePlacement;
/*     */ import org.krysalis.barcode4j.TextAlignment;
/*     */ import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
/*     */ import org.krysalis.barcode4j.impl.DrawingUtil;
/*     */ import org.krysalis.barcode4j.output.Canvas;
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
/*     */   implements ClassicBarcodeLogicHandler
/*     */ {
/*     */   private b a;
/*     */   private Canvas b;
/*  43 */   private double c = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BarcodeDimension d;
/*     */ 
/*     */ 
/*     */   
/*     */   private String e;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c(AbstractBarcodeBean bcBean, Canvas canvas) {
/*  58 */     if (!(bcBean instanceof b)) {
/*  59 */       throw new IllegalArgumentException("This LogicHandler can only be used with UPC and EAN barcode implementations");
/*     */     }
/*     */     
/*  62 */     this.a = (b)bcBean;
/*  63 */     this.b = canvas;
/*     */   }
/*     */   
/*     */   private double c() {
/*  67 */     if (this.a.hasQuietZone()) {
/*  68 */       return this.a.getQuietZone();
/*     */     }
/*  70 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(String msg, String formattedMsg) {
/*  76 */     this.e = msg;
/*     */     
/*  78 */     this.d = this.a.calcDimensions(msg);
/*     */     
/*  80 */     this.b.establishDimensions(this.d);
/*  81 */     this.c = c();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(BarGroup type, String submsg) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(boolean black, int width) {
/*  91 */     double w = this.a.getBarWidth(width);
/*  92 */     if (black)
/*     */     {
/*  94 */       if (this.a.getMsgPosition() == HumanReadablePlacement.HRP_BOTTOM) {
/*  95 */         double h = this.a.getBarHeight();
/*  96 */         this.b.drawRectWH(this.c, 0.0D, w, h);
/*  97 */       } else if (this.a.getMsgPosition() == HumanReadablePlacement.HRP_TOP) {
/*  98 */         double h = this.a.getBarHeight();
/*  99 */         this.b.drawRectWH(this.c, this.a.getHeight() - h, w, h);
/*     */       } else {
/* 101 */         this.b.drawRectWH(this.c, 0.0D, w, this.a.getHeight());
/*     */       } 
/*     */     }
/* 104 */     this.c += w;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {
/* 114 */     if (this.a.getMsgPosition() == HumanReadablePlacement.HRP_BOTTOM) {
/* 115 */       DrawingUtil.drawText(this.b, (AbstractBarcodeBean)this.a, this.e, c(), c() + this.d.getWidth(), this.a
/* 116 */           .getHeight(), TextAlignment.TA_JUSTIFY);
/* 117 */     } else if (this.a.getMsgPosition() == HumanReadablePlacement.HRP_TOP) {
/* 118 */       DrawingUtil.drawText(this.b, (AbstractBarcodeBean)this.a, this.e, c(), c() + this.d.getWidth(), this.a
/* 119 */           .getHumanReadableHeight(), TextAlignment.TA_JUSTIFY);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */