/*    */ package jp.cssj.sakae.d;
/*    */ 
/*    */ import java.awt.AlphaComposite;
/*    */ import java.awt.Composite;
/*    */ import org.apache.batik.bridge.BridgeContext;
/*    */ import org.apache.batik.bridge.GVTBuilder;
/*    */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*    */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ public class b extends GVTBuilder {
/*    */   private final boolean a;
/*    */   
/*    */   public b(boolean forceVector) {
/* 16 */     this.a = forceVector;
/*    */   }
/*    */   
/*    */   public b() {
/* 20 */     this(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void buildComposite(BridgeContext ctx, Element e, CompositeGraphicsNode cgn) {
/* 27 */     super.buildComposite(ctx, e, cgn);
/* 28 */     for (int i = 0; i < cgn.size(); i++) {
/* 29 */       GraphicsNode gn = (GraphicsNode)cgn.get(i);
/* 30 */       Composite c = gn.getComposite();
/* 31 */       if (c instanceof AlphaComposite) {
/*    */ 
/*    */         
/* 34 */         AlphaComposite ac = (AlphaComposite)c;
/* 35 */         if (ac.getRule() == 3 && ac.getAlpha() < 1.0D)
/*    */         {
/*    */           
/* 38 */           gn.setFilter((Filter)new c(gn, this.a));
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/d/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */