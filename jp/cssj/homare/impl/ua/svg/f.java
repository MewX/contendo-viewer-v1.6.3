/*    */ package jp.cssj.homare.impl.ua.svg;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import org.apache.batik.anim.dom.SVGOMAElement;
/*    */ import org.apache.batik.bridge.Bridge;
/*    */ import org.apache.batik.bridge.BridgeContext;
/*    */ import org.apache.batik.bridge.SVGAElementBridge;
/*    */ import org.apache.batik.gvt.GraphicsNode;
/*    */ import org.w3c.dom.Element;
/*    */ 
/*    */ 
/*    */ public class f
/*    */   extends SVGAElementBridge
/*    */ {
/*    */   protected final m a;
/*    */   
/*    */   public f(m ua) {
/* 20 */     this.a = ua;
/*    */   }
/*    */   
/*    */   public Bridge getInstance() {
/* 24 */     return (Bridge)new f(this.a);
/*    */   }
/*    */   
/*    */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/* 28 */     super.buildGraphicsNode(ctx, e, node);
/* 29 */     a mctx = (a)ctx;
/* 30 */     SVGOMAElement a = (SVGOMAElement)e;
/* 31 */     Shape s = node.getOutline();
/* 32 */     s = node.getGlobalTransform().createTransformedShape(s);
/* 33 */     mctx.a(s, URI.create(a.getHref().getBaseVal()));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */