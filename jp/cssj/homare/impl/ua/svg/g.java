/*    */ package jp.cssj.homare.impl.ua.svg;
/*    */ 
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.net.URI;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import jp.cssj.e.b;
/*    */ import jp.cssj.e.e.d;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import org.apache.batik.bridge.Bridge;
/*    */ import org.apache.batik.bridge.BridgeContext;
/*    */ import org.apache.batik.bridge.SVGImageElementBridge;
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ import org.apache.batik.gvt.ShapeNode;
/*    */ import org.apache.batik.util.ParsedURL;
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ class g
/*    */   extends SVGImageElementBridge
/*    */ {
/* 22 */   private static final Logger b = Logger.getLogger(g.class.getName());
/*    */   protected final m a;
/*    */   
/*    */   public g(m ua) {
/* 26 */     this.a = ua;
/*    */   }
/*    */   
/*    */   public Bridge getInstance() {
/* 30 */     return (Bridge)new g(this.a);
/*    */   }
/*    */   
/*    */   protected GraphicsNode createImageGraphicsNode(BridgeContext ctx, Element e, ParsedURL purl) {
/* 34 */     Rectangle2D bounds = getImageBounds(ctx, e);
/* 35 */     if (bounds.getWidth() == 0.0D || bounds.getHeight() == 0.0D) {
/* 36 */       ShapeNode sn = new ShapeNode();
/* 37 */       sn.setShape(bounds);
/* 38 */       return (GraphicsNode)sn;
/*    */     } 
/*    */     
/* 41 */     String purlStr = purl.toString();
/*    */     try {
/* 43 */       URI uri = d.a("UTF-8", purlStr);
/* 44 */       b source = this.a.b(uri);
/*    */       try {
/* 46 */         b image = this.a.c(source);
/* 47 */         d node = new d(image);
/* 48 */         Rectangle2D imgBounds = node.getPrimitiveBounds();
/* 49 */         float[] vb = new float[4];
/* 50 */         vb[0] = 0.0F;
/* 51 */         vb[1] = 0.0F;
/* 52 */         vb[2] = (float)imgBounds.getWidth();
/* 53 */         vb[3] = (float)imgBounds.getHeight();
/*    */         
/* 55 */         initializeViewport(ctx, e, (GraphicsNode)node, vb, bounds);
/* 56 */         return (GraphicsNode)node;
/*    */       } finally {
/* 58 */         this.a.a(source);
/*    */       } 
/* 60 */     } catch (Exception ex) {
/* 61 */       b.log(Level.FINE, "画像を読み込めません:" + purlStr, ex);
/* 62 */       ShapeNode sn = new ShapeNode();
/* 63 */       sn.setShape(bounds);
/* 64 */       return (GraphicsNode)sn;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */