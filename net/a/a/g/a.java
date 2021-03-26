/*    */ package net.a.a.g;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Rectangle2D;
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
/*    */ 
/*    */ public class a
/*    */   implements b
/*    */ {
/*    */   private final Color a;
/*    */   private final float b;
/*    */   private final float c;
/*    */   private final float d;
/*    */   
/*    */   public a(Color paramColor, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 52 */     this.a = paramColor;
/* 53 */     this.b = paramFloat1;
/* 54 */     this.c = paramFloat2;
/* 55 */     this.d = paramFloat3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(float paramFloat1, float paramFloat2, Graphics2D paramGraphics2D) {
/* 60 */     paramGraphics2D.setColor(this.a);
/* 61 */     paramGraphics2D.fill(new Rectangle2D.Float(paramFloat1, paramFloat2 - this.b, this.d, this.b + this.c));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/g/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */