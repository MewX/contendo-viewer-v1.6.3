/*    */ package jp.cssj.sakae.d;
/*    */ 
/*    */ import java.awt.geom.Dimension2D;
/*    */ import org.apache.batik.bridge.UserAgentAdapter;
/*    */ 
/*    */ public class h
/*    */   extends UserAgentAdapter {
/*    */   protected final Dimension2D c;
/*    */   
/*    */   public h(Dimension2D viewport) {
/* 11 */     this.c = viewport;
/* 12 */     addStdFeatures();
/*    */   }
/*    */   
/*    */   public Dimension2D getViewportSize() {
/* 16 */     return this.c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/d/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */