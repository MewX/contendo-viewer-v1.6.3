/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.Marker;
/*     */ import org.apache.batik.parser.UnitProcessor;
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
/*     */ public class SVGMarkerElementBridge
/*     */   extends AnimatableGenericSVGBridge
/*     */   implements ErrorConstants, MarkerBridge
/*     */ {
/*     */   public String getLocalName() {
/*  53 */     return "marker";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Marker createMarker(BridgeContext ctx, Element markerElement, Element paintedElement) {
/*     */     double orient;
/*     */     short unitsType;
/*     */     AffineTransform markerTxf;
/*  67 */     GVTBuilder builder = ctx.getGVTBuilder();
/*     */     
/*  69 */     CompositeGraphicsNode markerContentNode = new CompositeGraphicsNode();
/*     */ 
/*     */ 
/*     */     
/*  73 */     boolean hasChildren = false;
/*  74 */     Node n = markerElement.getFirstChild();
/*  75 */     for (; n != null; 
/*  76 */       n = n.getNextSibling()) {
/*     */ 
/*     */       
/*  79 */       if (n.getNodeType() == 1) {
/*     */ 
/*     */         
/*  82 */         Element child = (Element)n;
/*  83 */         GraphicsNode markerNode = builder.build(ctx, child);
/*     */         
/*  85 */         if (markerNode != null)
/*     */         
/*     */         { 
/*  88 */           hasChildren = true;
/*  89 */           markerContentNode.getChildren().add(markerNode); } 
/*     */       } 
/*  91 */     }  if (!hasChildren) {
/*  92 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  96 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, paintedElement);
/*     */ 
/*     */ 
/*     */     
/* 100 */     float markerWidth = 3.0F;
/* 101 */     String s = markerElement.getAttributeNS(null, "markerWidth");
/* 102 */     if (s.length() != 0) {
/* 103 */       markerWidth = UnitProcessor.svgHorizontalLengthToUserSpace(s, "markerWidth", uctx);
/*     */     }
/*     */     
/* 106 */     if (markerWidth == 0.0F)
/*     */     {
/* 108 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 112 */     float markerHeight = 3.0F;
/* 113 */     s = markerElement.getAttributeNS(null, "markerHeight");
/* 114 */     if (s.length() != 0) {
/* 115 */       markerHeight = UnitProcessor.svgVerticalLengthToUserSpace(s, "markerHeight", uctx);
/*     */     }
/*     */     
/* 118 */     if (markerHeight == 0.0F)
/*     */     {
/* 120 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 125 */     s = markerElement.getAttributeNS(null, "orient");
/* 126 */     if (s.length() == 0) {
/* 127 */       orient = 0.0D;
/* 128 */     } else if ("auto".equals(s)) {
/* 129 */       orient = Double.NaN;
/*     */     } else {
/*     */       try {
/* 132 */         orient = SVGUtilities.convertSVGNumber(s);
/* 133 */       } catch (NumberFormatException nfEx) {
/* 134 */         throw new BridgeException(ctx, markerElement, nfEx, "attribute.malformed", new Object[] { "orient", s });
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     Value val = CSSUtilities.getComputedStyle(paintedElement, 52);
/*     */     
/* 143 */     float strokeWidth = val.getFloatValue();
/*     */ 
/*     */ 
/*     */     
/* 147 */     s = markerElement.getAttributeNS(null, "markerUnits");
/* 148 */     if (s.length() == 0) {
/* 149 */       unitsType = 3;
/*     */     } else {
/* 151 */       unitsType = SVGUtilities.parseMarkerCoordinateSystem(markerElement, "markerUnits", s, ctx);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     if (unitsType == 3) {
/* 162 */       markerTxf = new AffineTransform();
/* 163 */       markerTxf.scale(strokeWidth, strokeWidth);
/*     */     } else {
/* 165 */       markerTxf = new AffineTransform();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 170 */     AffineTransform preserveAspectRatioTransform = ViewBox.getPreserveAspectRatioTransform(markerElement, markerWidth, markerHeight, ctx);
/*     */ 
/*     */ 
/*     */     
/* 174 */     if (preserveAspectRatioTransform == null)
/*     */     {
/* 176 */       return null;
/*     */     }
/* 178 */     markerTxf.concatenate(preserveAspectRatioTransform);
/*     */ 
/*     */     
/* 181 */     markerContentNode.setTransform(markerTxf);
/*     */ 
/*     */     
/* 184 */     if (CSSUtilities.convertOverflow(markerElement)) {
/*     */       Rectangle2D markerClip;
/* 186 */       float[] offsets = CSSUtilities.convertClip(markerElement);
/* 187 */       if (offsets == null) {
/* 188 */         markerClip = new Rectangle2D.Float(0.0F, 0.0F, strokeWidth * markerWidth, strokeWidth * markerHeight);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 198 */         markerClip = new Rectangle2D.Float(offsets[3], offsets[0], strokeWidth * markerWidth - offsets[1] - offsets[3], strokeWidth * markerHeight - offsets[2] - offsets[0]);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 205 */       CompositeGraphicsNode comp = new CompositeGraphicsNode();
/* 206 */       comp.getChildren().add(markerContentNode);
/* 207 */       Filter clipSrc = comp.getGraphicsNodeRable(true);
/* 208 */       comp.setClip((ClipRable)new ClipRable8Bit(clipSrc, markerClip));
/* 209 */       markerContentNode = comp;
/*     */     } 
/*     */ 
/*     */     
/* 213 */     float refX = 0.0F;
/* 214 */     s = markerElement.getAttributeNS(null, "refX");
/* 215 */     if (s.length() != 0) {
/* 216 */       refX = UnitProcessor.svgHorizontalCoordinateToUserSpace(s, "refX", uctx);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 221 */     float refY = 0.0F;
/* 222 */     s = markerElement.getAttributeNS(null, "refY");
/* 223 */     if (s.length() != 0) {
/* 224 */       refY = UnitProcessor.svgVerticalCoordinateToUserSpace(s, "refY", uctx);
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
/*     */ 
/*     */     
/* 238 */     float[] ref = { refX, refY };
/* 239 */     markerTxf.transform(ref, 0, ref, 0, 1);
/* 240 */     Marker marker = new Marker((GraphicsNode)markerContentNode, new Point2D.Float(ref[0], ref[1]), orient);
/*     */ 
/*     */ 
/*     */     
/* 244 */     return marker;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGMarkerElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */