/*    */ package net.a.a.g;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.font.TextLayout;
/*    */ import java.awt.geom.AffineTransform;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class k
/*    */   implements b
/*    */ {
/*    */   private final TextLayout b;
/*    */   private final Color c;
/*    */   private final float d;
/*    */   private final float e;
/*    */   private final AffineTransform f;
/*    */   
/*    */   public k(TextLayout paramTextLayout, float paramFloat, Color paramColor) {
/* 53 */     if (!a && paramTextLayout == null) throw new AssertionError(); 
/* 54 */     this.b = paramTextLayout;
/* 55 */     this.c = paramColor;
/* 56 */     this.d = paramFloat;
/* 57 */     this.e = 0.0F;
/* 58 */     this.f = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public k(TextLayout paramTextLayout, float paramFloat1, float paramFloat2, AffineTransform paramAffineTransform, Color paramColor) {
/* 78 */     if (!a && paramTextLayout == null) throw new AssertionError(); 
/* 79 */     this.b = paramTextLayout;
/* 80 */     this.c = paramColor;
/* 81 */     this.d = paramFloat1;
/* 82 */     this.e = paramFloat2;
/* 83 */     this.f = paramAffineTransform;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(float paramFloat1, float paramFloat2, Graphics2D paramGraphics2D) {
/* 88 */     paramGraphics2D.setColor(this.c);
/* 89 */     AffineTransform affineTransform = paramGraphics2D.getTransform();
/* 90 */     paramGraphics2D.translate((paramFloat1 + this.d), (paramFloat2 + this.e));
/* 91 */     if (this.f != null) {
/* 92 */       paramGraphics2D.transform(this.f);
/*    */     }
/* 94 */     this.b.draw(paramGraphics2D, 0.0F, 0.0F);
/* 95 */     paramGraphics2D.setTransform(affineTransform);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/g/k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */