/*    */ package jp.cssj.homare.ua;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.net.URI;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class g
/*    */   extends ArrayList<g.a>
/*    */ {
/*    */   private static final long a = 1L;
/*    */   
/*    */   public static class a {
/*    */     public final URI a;
/*    */     public final Shape b;
/*    */     
/*    */     public a(Shape shape, URI href) {
/* 18 */       this.a = href;
/* 19 */       this.b = shape;
/*    */     }
/*    */     
/*    */     public String toString() {
/* 23 */       StringBuffer buff = new StringBuffer();
/* 24 */       buff.append("href=" + this.a);
/* 25 */       buff.append(";shape=" + this.b);
/* 26 */       return buff.toString();
/*    */     }
/*    */   }
/*    */   
/*    */   public g a(AffineTransform t) {
/* 31 */     g im = new g();
/* 32 */     for (a area : this) {
/* 33 */       im.add(new a(t.createTransformedShape(area.b), area.a));
/*    */     }
/* 35 */     return im;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */