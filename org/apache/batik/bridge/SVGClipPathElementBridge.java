/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import org.apache.batik.anim.dom.SVGOMUseElement;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.ShapeNode;
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
/*     */ public class SVGClipPathElementBridge
/*     */   extends AnimatableGenericSVGBridge
/*     */   implements ClipBridge
/*     */ {
/*     */   public String getLocalName() {
/*  54 */     return "clipPath";
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
/*     */   
/*     */   public ClipRable createClip(BridgeContext ctx, Element clipElement, Element clipedElement, GraphicsNode clipedNode) {
/*     */     AffineTransform Tx;
/*     */     short coordSystemType;
/*  74 */     String s = clipElement.getAttributeNS((String)null, "transform");
/*  75 */     if (s.length() != 0) {
/*  76 */       Tx = SVGUtilities.convertTransform(clipElement, "transform", s, ctx);
/*     */     } else {
/*     */       
/*  79 */       Tx = new AffineTransform();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  84 */     s = clipElement.getAttributeNS((String)null, "clipPathUnits");
/*  85 */     if (s.length() == 0) {
/*  86 */       coordSystemType = 1;
/*     */     } else {
/*  88 */       coordSystemType = SVGUtilities.parseCoordinateSystem(clipElement, "clipPathUnits", s, ctx);
/*     */     } 
/*     */ 
/*     */     
/*  92 */     if (coordSystemType == 2) {
/*  93 */       Tx = SVGUtilities.toObjectBBox(Tx, clipedNode);
/*     */     }
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
/* 105 */     Area clipPath = new Area();
/* 106 */     GVTBuilder builder = ctx.getGVTBuilder();
/* 107 */     boolean hasChildren = false;
/* 108 */     Node node = clipElement.getFirstChild();
/* 109 */     for (; node != null; 
/* 110 */       node = node.getNextSibling()) {
/*     */ 
/*     */       
/* 113 */       if (node.getNodeType() == 1) {
/*     */ 
/*     */ 
/*     */         
/* 117 */         Element child = (Element)node;
/* 118 */         GraphicsNode clipNode = builder.build(ctx, child);
/*     */         
/* 120 */         if (clipNode != null)
/*     */         
/*     */         { 
/* 123 */           hasChildren = true;
/*     */ 
/*     */           
/* 126 */           if (child instanceof SVGOMUseElement) {
/* 127 */             Node shadowChild = ((SVGOMUseElement)child).getCSSFirstChild();
/*     */ 
/*     */             
/* 130 */             if (shadowChild != null && shadowChild.getNodeType() == 1)
/*     */             {
/* 132 */               child = (Element)shadowChild;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 137 */           int wr = CSSUtilities.convertClipRule(child);
/* 138 */           GeneralPath path = new GeneralPath(clipNode.getOutline());
/* 139 */           path.setWindingRule(wr);
/*     */           
/* 141 */           AffineTransform at = clipNode.getTransform();
/* 142 */           if (at == null) { at = Tx; }
/* 143 */           else { at.preConcatenate(Tx); }
/*     */           
/* 145 */           Shape outline = at.createTransformedShape(path);
/*     */ 
/*     */           
/* 148 */           ShapeNode outlineNode = new ShapeNode();
/* 149 */           outlineNode.setShape(outline);
/* 150 */           ClipRable clip = CSSUtilities.convertClipPath(child, (GraphicsNode)outlineNode, ctx);
/*     */ 
/*     */           
/* 153 */           if (clip != null) {
/* 154 */             Area area = new Area(outline);
/* 155 */             area.subtract(new Area(clip.getClipPath()));
/* 156 */             outline = area;
/*     */           } 
/* 158 */           clipPath.add(new Area(outline)); } 
/*     */       } 
/* 160 */     }  if (!hasChildren) {
/* 161 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 165 */     ShapeNode clipPathNode = new ShapeNode();
/* 166 */     clipPathNode.setShape(clipPath);
/*     */ 
/*     */     
/* 169 */     ClipRable clipElementClipPath = CSSUtilities.convertClipPath(clipElement, (GraphicsNode)clipPathNode, ctx);
/*     */     
/* 171 */     if (clipElementClipPath != null) {
/* 172 */       clipPath.subtract(new Area(clipElementClipPath.getClipPath()));
/*     */     }
/*     */     
/* 175 */     Filter filter = clipedNode.getFilter();
/* 176 */     if (filter == null)
/*     */     {
/* 178 */       filter = clipedNode.getGraphicsNodeRable(true);
/*     */     }
/*     */     
/* 181 */     boolean useAA = false;
/*     */     
/* 183 */     RenderingHints hints = CSSUtilities.convertShapeRendering(clipElement, null);
/* 184 */     if (hints != null) {
/* 185 */       Object o = hints.get(RenderingHints.KEY_ANTIALIASING);
/* 186 */       useAA = (o == RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     } 
/*     */     
/* 189 */     return (ClipRable)new ClipRable8Bit(filter, clipPath, useAA);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGClipPathElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */