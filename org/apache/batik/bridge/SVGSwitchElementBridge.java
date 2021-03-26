/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
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
/*     */ public class SVGSwitchElementBridge
/*     */   extends SVGGElementBridge
/*     */ {
/*     */   protected Element selectedChild;
/*     */   
/*     */   public String getLocalName() {
/*  51 */     return "switch";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  58 */     return new SVGSwitchElementBridge();
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
/*  69 */     GraphicsNode refNode = null;
/*  70 */     GVTBuilder builder = ctx.getGVTBuilder();
/*  71 */     this.selectedChild = null;
/*  72 */     for (Node n = e.getFirstChild(); n != null; n = n.getNextSibling()) {
/*  73 */       if (n.getNodeType() == 1) {
/*  74 */         Element ref = (Element)n;
/*  75 */         if (n instanceof org.w3c.dom.svg.SVGTests && SVGUtilities.matchUserAgent(ref, ctx.getUserAgent())) {
/*     */           
/*  77 */           this.selectedChild = ref;
/*  78 */           refNode = builder.build(ctx, ref);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*  84 */     if (refNode == null) {
/*  85 */       return null;
/*     */     }
/*     */     
/*  88 */     CompositeGraphicsNode group = (CompositeGraphicsNode)super.createGraphicsNode(ctx, e);
/*     */     
/*  90 */     if (group == null) {
/*  91 */       return null;
/*     */     }
/*     */     
/*  94 */     group.add(refNode);
/*     */     
/*  96 */     return (GraphicsNode)group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 112 */     this.selectedChild = null;
/* 113 */     super.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleElementAdded(CompositeGraphicsNode gn, Node parent, Element childElt) {
/* 123 */     Node n = childElt.getPreviousSibling();
/* 124 */     for (; n != null; 
/* 125 */       n = n.getPreviousSibling()) {
/* 126 */       if (n == childElt) {
/*     */         return;
/*     */       }
/*     */     } 
/* 130 */     if (childElt instanceof org.w3c.dom.svg.SVGTests && SVGUtilities.matchUserAgent(childElt, this.ctx.getUserAgent())) {
/*     */       
/* 132 */       if (this.selectedChild != null) {
/* 133 */         gn.remove(0);
/* 134 */         disposeTree(this.selectedChild);
/*     */       } 
/* 136 */       this.selectedChild = childElt;
/* 137 */       GVTBuilder builder = this.ctx.getGVTBuilder();
/* 138 */       GraphicsNode refNode = builder.build(this.ctx, childElt);
/* 139 */       if (refNode != null) {
/* 140 */         gn.add(refNode);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleChildElementRemoved(Element e) {
/* 150 */     CompositeGraphicsNode gn = (CompositeGraphicsNode)this.node;
/* 151 */     if (this.selectedChild == e) {
/* 152 */       gn.remove(0);
/* 153 */       disposeTree(this.selectedChild);
/* 154 */       this.selectedChild = null;
/* 155 */       GraphicsNode refNode = null;
/* 156 */       GVTBuilder builder = this.ctx.getGVTBuilder();
/* 157 */       Node n = e.getNextSibling();
/* 158 */       for (; n != null; 
/* 159 */         n = n.getNextSibling()) {
/* 160 */         if (n.getNodeType() == 1) {
/* 161 */           Element ref = (Element)n;
/* 162 */           if (n instanceof org.w3c.dom.svg.SVGTests && SVGUtilities.matchUserAgent(ref, this.ctx.getUserAgent())) {
/*     */ 
/*     */             
/* 165 */             refNode = builder.build(this.ctx, ref);
/* 166 */             this.selectedChild = ref;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 172 */       if (refNode != null)
/* 173 */         gn.add(refNode); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGSwitchElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */