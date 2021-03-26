/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.ext.awt.image.ConcreteComponentTransferFunction;
/*     */ import org.apache.batik.ext.awt.image.renderable.ComponentTransferRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.AbstractGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.PatternPaint;
/*     */ import org.apache.batik.gvt.RootGraphicsNode;
/*     */ import org.apache.batik.util.ParsedURL;
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
/*     */ public class SVGPatternElementBridge
/*     */   extends AnimatableGenericSVGBridge
/*     */   implements ErrorConstants, PaintBridge
/*     */ {
/*     */   public String getLocalName() {
/*  60 */     return "pattern";
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
/*     */   
/*     */   public Paint createPaint(BridgeContext ctx, Element patternElement, Element paintedElement, GraphicsNode paintedNode, float opacity) {
/*     */     AffineTransform patternTransform;
/*     */     short contentCoordSystem;
/*  81 */     RootGraphicsNode patternContentNode = (RootGraphicsNode)ctx.getElementData(patternElement);
/*     */ 
/*     */     
/*  84 */     if (patternContentNode == null) {
/*  85 */       patternContentNode = extractPatternContent(patternElement, ctx);
/*  86 */       ctx.setElementData(patternElement, patternContentNode);
/*     */     } 
/*  88 */     if (patternContentNode == null) {
/*  89 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  94 */     Rectangle2D patternRegion = SVGUtilities.convertPatternRegion(patternElement, paintedElement, paintedNode, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     String s = SVGUtilities.getChainableAttributeNS(patternElement, null, "patternTransform", ctx);
/*     */     
/* 103 */     if (s.length() != 0) {
/* 104 */       patternTransform = SVGUtilities.convertTransform(patternElement, "patternTransform", s, ctx);
/*     */     } else {
/*     */       
/* 107 */       patternTransform = new AffineTransform();
/*     */     } 
/*     */ 
/*     */     
/* 111 */     boolean overflowIsHidden = CSSUtilities.convertOverflow(patternElement);
/*     */ 
/*     */ 
/*     */     
/* 115 */     s = SVGUtilities.getChainableAttributeNS(patternElement, null, "patternContentUnits", ctx);
/*     */     
/* 117 */     if (s.length() == 0) {
/* 118 */       contentCoordSystem = 1;
/*     */     } else {
/* 120 */       contentCoordSystem = SVGUtilities.parseCoordinateSystem(patternElement, "patternContentUnits", s, ctx);
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
/* 149 */     AffineTransform patternContentTransform = new AffineTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     patternContentTransform.translate(patternRegion.getX(), patternRegion.getY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     String viewBoxStr = SVGUtilities.getChainableAttributeNS(patternElement, null, "viewBox", ctx);
/*     */ 
/*     */     
/* 165 */     if (viewBoxStr.length() > 0) {
/*     */ 
/*     */       
/* 168 */       String aspectRatioStr = SVGUtilities.getChainableAttributeNS(patternElement, null, "preserveAspectRatio", ctx);
/*     */       
/* 170 */       float w = (float)patternRegion.getWidth();
/* 171 */       float h = (float)patternRegion.getHeight();
/* 172 */       AffineTransform preserveAspectRatioTransform = ViewBox.getPreserveAspectRatioTransform(patternElement, viewBoxStr, aspectRatioStr, w, h, ctx);
/*     */ 
/*     */ 
/*     */       
/* 176 */       patternContentTransform.concatenate(preserveAspectRatioTransform);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 181 */     else if (contentCoordSystem == 2) {
/* 182 */       AffineTransform patternContentUnitsTransform = new AffineTransform();
/*     */       
/* 184 */       Rectangle2D objectBoundingBox = paintedNode.getGeometryBounds();
/*     */       
/* 186 */       patternContentUnitsTransform.translate(objectBoundingBox.getX(), objectBoundingBox.getY());
/*     */ 
/*     */ 
/*     */       
/* 190 */       patternContentUnitsTransform.scale(objectBoundingBox.getWidth(), objectBoundingBox.getHeight());
/*     */ 
/*     */ 
/*     */       
/* 194 */       patternContentTransform.concatenate(patternContentUnitsTransform);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 204 */     PatternGraphicsNode patternGraphicsNode = new PatternGraphicsNode((GraphicsNode)patternContentNode);
/*     */     
/* 206 */     patternGraphicsNode.setTransform(patternContentTransform);
/*     */ 
/*     */     
/* 209 */     if (opacity != 1.0F) {
/* 210 */       Filter filter = patternGraphicsNode.getGraphicsNodeRable(true);
/* 211 */       ComponentTransferRable8Bit componentTransferRable8Bit = new ComponentTransferRable8Bit(filter, ConcreteComponentTransferFunction.getLinearTransfer(opacity, 0.0F), ConcreteComponentTransferFunction.getIdentityTransfer(), ConcreteComponentTransferFunction.getIdentityTransfer(), ConcreteComponentTransferFunction.getIdentityTransfer());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 218 */       patternGraphicsNode.setFilter((Filter)componentTransferRable8Bit);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 223 */     return (Paint)new PatternPaint((GraphicsNode)patternGraphicsNode, patternRegion, !overflowIsHidden, patternTransform);
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
/*     */ 
/*     */   
/*     */   protected static RootGraphicsNode extractPatternContent(Element patternElement, BridgeContext ctx) {
/* 243 */     List<ParsedURL> refs = new LinkedList();
/*     */     while (true) {
/* 245 */       RootGraphicsNode content = extractLocalPatternContent(patternElement, ctx);
/*     */       
/* 247 */       if (content != null) {
/* 248 */         return content;
/*     */       }
/* 250 */       String uri = XLinkSupport.getXLinkHref(patternElement);
/* 251 */       if (uri.length() == 0) {
/* 252 */         return null;
/*     */       }
/*     */       
/* 255 */       SVGOMDocument doc = (SVGOMDocument)patternElement.getOwnerDocument();
/*     */       
/* 257 */       ParsedURL purl = new ParsedURL(doc.getURL(), uri);
/* 258 */       if (!purl.complete()) {
/* 259 */         throw new BridgeException(ctx, patternElement, "uri.malformed", new Object[] { uri });
/*     */       }
/*     */ 
/*     */       
/* 263 */       if (contains(refs, purl)) {
/* 264 */         throw new BridgeException(ctx, patternElement, "xlink.href.circularDependencies", new Object[] { uri });
/*     */       }
/*     */ 
/*     */       
/* 268 */       refs.add(purl);
/* 269 */       patternElement = ctx.getReferencedElement(patternElement, uri);
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
/*     */   protected static RootGraphicsNode extractLocalPatternContent(Element e, BridgeContext ctx) {
/* 283 */     GVTBuilder builder = ctx.getGVTBuilder();
/* 284 */     RootGraphicsNode content = null;
/* 285 */     for (Node n = e.getFirstChild(); n != null; n = n.getNextSibling()) {
/*     */       
/* 287 */       if (n.getNodeType() == 1) {
/*     */ 
/*     */ 
/*     */         
/* 291 */         GraphicsNode gn = builder.build(ctx, (Element)n);
/*     */         
/* 293 */         if (gn != null) {
/*     */           
/* 295 */           if (content == null) {
/* 296 */             content = new RootGraphicsNode();
/*     */           }
/* 298 */           content.getChildren().add(gn);
/*     */         } 
/*     */       } 
/* 301 */     }  return content;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean contains(List urls, ParsedURL key) {
/* 311 */     for (Object url : urls) {
/* 312 */       if (key.equals(url))
/* 313 */         return true; 
/*     */     } 
/* 315 */     return false;
/*     */   }
/*     */   
/*     */   public static class PatternGraphicsNode extends AbstractGraphicsNode { GraphicsNode pcn;
/*     */     Rectangle2D pBounds;
/*     */     Rectangle2D gBounds;
/*     */     Rectangle2D sBounds;
/*     */     Shape oShape;
/*     */     
/*     */     public PatternGraphicsNode(GraphicsNode gn) {
/* 325 */       this.pcn = gn;
/*     */     }
/*     */     public void primitivePaint(Graphics2D g2d) {
/* 328 */       this.pcn.paint(g2d);
/*     */     }
/*     */     public Rectangle2D getPrimitiveBounds() {
/* 331 */       if (this.pBounds != null) return this.pBounds; 
/* 332 */       this.pBounds = this.pcn.getTransformedBounds(IDENTITY);
/* 333 */       return this.pBounds;
/*     */     }
/*     */     public Rectangle2D getGeometryBounds() {
/* 336 */       if (this.gBounds != null) return this.gBounds; 
/* 337 */       this.gBounds = this.pcn.getTransformedGeometryBounds(IDENTITY);
/* 338 */       return this.gBounds;
/*     */     }
/*     */     public Rectangle2D getSensitiveBounds() {
/* 341 */       if (this.sBounds != null) return this.sBounds; 
/* 342 */       this.sBounds = this.pcn.getTransformedSensitiveBounds(IDENTITY);
/* 343 */       return this.sBounds;
/*     */     }
/*     */     public Shape getOutline() {
/* 346 */       if (this.oShape != null) return this.oShape; 
/* 347 */       this.oShape = this.pcn.getOutline();
/* 348 */       AffineTransform tr = this.pcn.getTransform();
/* 349 */       if (tr != null)
/* 350 */         this.oShape = tr.createTransformedShape(this.oShape); 
/* 351 */       return this.oShape;
/*     */     }
/*     */     protected void invalidateGeometryCache() {
/* 354 */       this.pBounds = null;
/* 355 */       this.gBounds = null;
/* 356 */       this.sBounds = null;
/* 357 */       this.oShape = null;
/* 358 */       super.invalidateGeometryCache();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGPatternElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */