/*    */ package jp.cssj.homare.impl.ua.svg;
/*    */ 
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.Dimension2D;
/*    */ import java.net.URI;
/*    */ import jp.cssj.homare.ua.g;
/*    */ import jp.cssj.homare.ua.m;
/*    */ import org.apache.batik.bridge.Bridge;
/*    */ import org.apache.batik.bridge.BridgeContext;
/*    */ import org.apache.batik.bridge.DocumentLoader;
/*    */ import org.apache.batik.bridge.TextPainter;
/*    */ import org.apache.batik.bridge.URIResolver;
/*    */ import org.apache.batik.bridge.UserAgent;
/*    */ import org.w3c.dom.svg.SVGDocument;
/*    */ 
/*    */ public class a extends BridgeContext {
/*    */   protected final m a;
/*    */   protected final SVGImageLoader b;
/* 19 */   protected final g c = new g();
/*    */   
/*    */   public a(String docURI, m ua, Dimension2D viewport, SVGImageLoader loader) {
/* 22 */     super((UserAgent)new k(docURI, ua, viewport));
/* 23 */     this.a = ua;
/* 24 */     this.b = loader;
/* 25 */     setDynamic(false);
/* 26 */     i i = new i(ua);
/* 27 */     setTextPainter((TextPainter)i);
/*    */   }
/*    */   
/*    */   public g a() {
/* 31 */     return this.c;
/*    */   }
/*    */   
/*    */   public void a(Shape shape, URI uri) {
/* 35 */     this.c.add(new g.a(shape, uri));
/*    */   }
/*    */   
/*    */   public void registerSVGBridges() {
/* 39 */     super.registerSVGBridges();
/* 40 */     putBridge((Bridge)new h(this.a));
/* 41 */     putBridge((Bridge)new g(this.a));
/* 42 */     putBridge((Bridge)new f(this.a));
/*    */   }
/*    */   
/*    */   public URIResolver createURIResolver(SVGDocument doc, DocumentLoader dl) {
/* 46 */     return new j(doc, dl, this.a, this.b);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/svg/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */