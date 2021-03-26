/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
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
/*     */ public class SVGGElementBridge
/*     */   extends AbstractGraphicsNodeBridge
/*     */ {
/*     */   public String getLocalName() {
/*  47 */     return "g";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  54 */     return new SVGGElementBridge();
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
/*  65 */     CompositeGraphicsNode gn = (CompositeGraphicsNode)super.createGraphicsNode(ctx, e);
/*     */     
/*  67 */     if (gn == null) {
/*  68 */       return null;
/*     */     }
/*  70 */     associateSVGContext(ctx, e, (GraphicsNode)gn);
/*     */ 
/*     */     
/*  73 */     RenderingHints hints = null;
/*  74 */     hints = CSSUtilities.convertColorRendering(e, hints);
/*  75 */     if (hints != null) {
/*  76 */       gn.setRenderingHints(hints);
/*     */     }
/*     */     
/*  79 */     Rectangle2D r = CSSUtilities.convertEnableBackground(e);
/*  80 */     if (r != null) {
/*  81 */       gn.setBackgroundEnable(r);
/*     */     }
/*  83 */     return (GraphicsNode)gn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode instantiateGraphicsNode() {
/*  90 */     return (GraphicsNode)new CompositeGraphicsNode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMNodeInsertedEvent(MutationEvent evt) {
/* 106 */     if (evt.getTarget() instanceof Element) {
/* 107 */       handleElementAdded((CompositeGraphicsNode)this.node, this.e, (Element)evt.getTarget());
/*     */     }
/*     */     else {
/*     */       
/* 111 */       super.handleDOMNodeInsertedEvent(evt);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleElementAdded(CompositeGraphicsNode gn, Node parent, Element childElt) {
/* 122 */     GVTBuilder builder = this.ctx.getGVTBuilder();
/* 123 */     GraphicsNode childNode = builder.build(this.ctx, childElt);
/* 124 */     if (childNode == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 129 */     int idx = -1;
/* 130 */     for (Node ps = childElt.getPreviousSibling(); ps != null; 
/* 131 */       ps = ps.getPreviousSibling()) {
/* 132 */       CompositeGraphicsNode compositeGraphicsNode; if (ps.getNodeType() != 1)
/*     */         continue; 
/* 134 */       Element pse = (Element)ps;
/* 135 */       GraphicsNode psgn = this.ctx.getGraphicsNode(pse);
/* 136 */       while (psgn != null && psgn.getParent() != gn)
/*     */       {
/*     */         
/* 139 */         compositeGraphicsNode = psgn.getParent();
/*     */       }
/* 141 */       if (compositeGraphicsNode == null)
/*     */         continue; 
/* 143 */       idx = gn.indexOf(compositeGraphicsNode);
/* 144 */       if (idx == -1) {
/*     */         continue;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 150 */     idx++;
/* 151 */     gn.add(idx, childNode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGGElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */