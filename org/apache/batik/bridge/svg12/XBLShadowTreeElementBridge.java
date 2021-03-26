/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import org.apache.batik.bridge.AbstractGraphicsNodeBridge;
/*     */ import org.apache.batik.bridge.Bridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.GVTBuilder;
/*     */ import org.apache.batik.bridge.SVGUtilities;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.MutationEvent;
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
/*     */ public class XBLShadowTreeElementBridge
/*     */   extends AbstractGraphicsNodeBridge
/*     */ {
/*     */   public String getLocalName() {
/*  51 */     return "shadowTree";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  58 */     return "http://www.w3.org/2004/xbl";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  65 */     return (Bridge)new XBLShadowTreeElementBridge();
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
/*     */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/*  77 */     if (!SVGUtilities.matchUserAgent(e, ctx.getUserAgent())) {
/*  78 */       return null;
/*     */     }
/*     */     
/*  81 */     CompositeGraphicsNode cgn = new CompositeGraphicsNode();
/*     */     
/*  83 */     associateSVGContext(ctx, e, (GraphicsNode)cgn);
/*     */     
/*  85 */     return (GraphicsNode)cgn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode instantiateGraphicsNode() {
/*  93 */     return null;
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
/* 107 */     initializeDynamicSupport(ctx, e, node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDisplay(Element e) {
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMNodeInsertedEvent(MutationEvent evt) {
/* 131 */     if (evt.getTarget() instanceof Element) {
/* 132 */       handleElementAdded((CompositeGraphicsNode)this.node, this.e, (Element)evt.getTarget());
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
/*     */   public void handleElementAdded(CompositeGraphicsNode gn, Node parent, Element childElt) {
/* 145 */     GVTBuilder builder = this.ctx.getGVTBuilder();
/* 146 */     GraphicsNode childNode = builder.build(this.ctx, childElt);
/* 147 */     if (childNode == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 152 */     int idx = -1;
/* 153 */     for (Node ps = childElt.getPreviousSibling(); ps != null; 
/* 154 */       ps = ps.getPreviousSibling()) {
/* 155 */       CompositeGraphicsNode compositeGraphicsNode; if (ps.getNodeType() != 1)
/*     */         continue; 
/* 157 */       Element pse = (Element)ps;
/* 158 */       GraphicsNode psgn = this.ctx.getGraphicsNode(pse);
/* 159 */       while (psgn != null && psgn.getParent() != gn)
/*     */       {
/*     */         
/* 162 */         compositeGraphicsNode = psgn.getParent();
/*     */       }
/* 164 */       if (compositeGraphicsNode == null)
/*     */         continue; 
/* 166 */       idx = gn.indexOf(compositeGraphicsNode);
/* 167 */       if (idx == -1) {
/*     */         continue;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 173 */     idx++;
/* 174 */     gn.add(idx, childNode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/XBLShadowTreeElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */