/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.filter.Mask;
/*     */ import org.apache.batik.gvt.filter.MaskRable8Bit;
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
/*     */ public class SVGMaskElementBridge
/*     */   extends AnimatableGenericSVGBridge
/*     */   implements MaskBridge
/*     */ {
/*     */   public String getLocalName() {
/*  50 */     return "mask";
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
/*     */   
/*     */   public Mask createMask(BridgeContext ctx, Element maskElement, Element maskedElement, GraphicsNode maskedNode) {
/*     */     AffineTransform Tx;
/*     */     short coordSystemType;
/*  69 */     Rectangle2D maskRegion = SVGUtilities.convertMaskRegion(maskElement, maskedElement, maskedNode, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     GVTBuilder builder = ctx.getGVTBuilder();
/*  76 */     CompositeGraphicsNode maskNode = new CompositeGraphicsNode();
/*  77 */     CompositeGraphicsNode maskNodeContent = new CompositeGraphicsNode();
/*  78 */     maskNode.getChildren().add(maskNodeContent);
/*  79 */     boolean hasChildren = false;
/*  80 */     Node node = maskElement.getFirstChild();
/*  81 */     for (; node != null; 
/*  82 */       node = node.getNextSibling()) {
/*     */ 
/*     */       
/*  85 */       if (node.getNodeType() == 1) {
/*     */ 
/*     */ 
/*     */         
/*  89 */         Element child = (Element)node;
/*  90 */         GraphicsNode gn = builder.build(ctx, child);
/*  91 */         if (gn != null)
/*     */         
/*     */         { 
/*  94 */           hasChildren = true;
/*  95 */           maskNodeContent.getChildren().add(gn); } 
/*     */       } 
/*  97 */     }  if (!hasChildren) {
/*  98 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 103 */     String s = maskElement.getAttributeNS(null, "transform");
/* 104 */     if (s.length() != 0) {
/* 105 */       Tx = SVGUtilities.convertTransform(maskElement, "transform", s, ctx);
/*     */     } else {
/*     */       
/* 108 */       Tx = new AffineTransform();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     s = maskElement.getAttributeNS(null, "maskContentUnits");
/* 114 */     if (s.length() == 0) {
/* 115 */       coordSystemType = 1;
/*     */     } else {
/* 117 */       coordSystemType = SVGUtilities.parseCoordinateSystem(maskElement, "maskContentUnits", s, ctx);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (coordSystemType == 2) {
/* 123 */       Tx = SVGUtilities.toObjectBBox(Tx, maskedNode);
/*     */     }
/*     */     
/* 126 */     maskNodeContent.setTransform(Tx);
/*     */     
/* 128 */     Filter filter = maskedNode.getFilter();
/* 129 */     if (filter == null)
/*     */     {
/* 131 */       filter = maskedNode.getGraphicsNodeRable(true);
/*     */     }
/*     */     
/* 134 */     return (Mask)new MaskRable8Bit(filter, (GraphicsNode)maskNode, maskRegion);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGMaskElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */