/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import org.apache.batik.anim.dom.XBLOMContentElement;
/*     */ import org.apache.batik.bridge.AbstractGraphicsNodeBridge;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.GVTBuilder;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class XBLContentElementBridge
/*     */   extends AbstractGraphicsNodeBridge
/*     */ {
/*     */   protected ContentChangedListener contentChangedListener;
/*     */   protected ContentManager contentManager;
/*     */   
/*     */   public String getLocalName() {
/*  64 */     return "content";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  71 */     return "http://www.w3.org/2004/xbl";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  78 */     return (Bridge)new XBLContentElementBridge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/*  89 */     CompositeGraphicsNode gn = buildCompositeGraphicsNode(ctx, e, (CompositeGraphicsNode)null);
/*  90 */     return (GraphicsNode)gn;
/*     */   }
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
/*     */   public CompositeGraphicsNode buildCompositeGraphicsNode(BridgeContext ctx, Element e, CompositeGraphicsNode cgn) {
/* 106 */     XBLOMContentElement content = (XBLOMContentElement)e;
/* 107 */     AbstractDocument doc = (AbstractDocument)e.getOwnerDocument();
/* 108 */     DefaultXBLManager xm = (DefaultXBLManager)doc.getXBLManager();
/* 109 */     this.contentManager = xm.getContentManager(e);
/*     */     
/* 111 */     if (cgn == null) {
/* 112 */       cgn = new CompositeGraphicsNode();
/* 113 */       associateSVGContext(ctx, e, (GraphicsNode)cgn);
/*     */     } else {
/* 115 */       int s = cgn.size();
/* 116 */       for (int i = 0; i < s; i++) {
/* 117 */         cgn.remove(0);
/*     */       }
/*     */     } 
/*     */     
/* 121 */     GVTBuilder builder = ctx.getGVTBuilder();
/* 122 */     NodeList nl = this.contentManager.getSelectedContent(content);
/* 123 */     if (nl != null) {
/* 124 */       for (int i = 0; i < nl.getLength(); i++) {
/* 125 */         Node n = nl.item(i);
/* 126 */         if (n.getNodeType() == 1) {
/* 127 */           GraphicsNode gn = builder.build(ctx, (Element)n);
/* 128 */           if (gn != null) {
/* 129 */             cgn.add(gn);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 135 */     if (ctx.isDynamic() && 
/* 136 */       this.contentChangedListener == null) {
/*     */       
/* 138 */       this.contentChangedListener = new ContentChangedListener();
/* 139 */       this.contentManager.addContentSelectionChangedListener(content, this.contentChangedListener);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 144 */     return cgn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode instantiateGraphicsNode() {
/* 152 */     return null;
/*     */   }
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
/*     */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/* 166 */     initializeDynamicSupport(ctx, e, node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDisplay(Element e) {
/* 174 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 190 */     super.dispose();
/*     */     
/* 192 */     if (this.contentChangedListener != null) {
/* 193 */       this.contentManager.removeContentSelectionChangedListener((XBLOMContentElement)this.e, this.contentChangedListener);
/*     */     }
/*     */   }
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
/*     */   protected class ContentChangedListener
/*     */     implements ContentSelectionChangedListener
/*     */   {
/*     */     public void contentSelectionChanged(ContentSelectionChangedEvent csce) {
/* 210 */       XBLContentElementBridge.this.buildCompositeGraphicsNode(XBLContentElementBridge.this.ctx, XBLContentElementBridge.this.e, (CompositeGraphicsNode)XBLContentElementBridge.this.node);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/XBLContentElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */