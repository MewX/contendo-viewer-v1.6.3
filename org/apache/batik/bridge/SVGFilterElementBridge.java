/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.FilterChainRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.FloodRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
/*     */ import org.apache.batik.gvt.GraphicsNode;
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
/*     */ public class SVGFilterElementBridge
/*     */   extends AnimatableGenericSVGBridge
/*     */   implements ErrorConstants, FilterBridge
/*     */ {
/*  53 */   protected static final Color TRANSPARENT_BLACK = new Color(0, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  64 */     return "filter";
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
/*     */   public Filter createFilter(BridgeContext ctx, Element filterElement, Element filteredElement, GraphicsNode filteredNode) {
/*  81 */     Rectangle2D filterRegion = SVGUtilities.convertFilterChainRegion(filterElement, filteredElement, filteredNode, ctx);
/*     */     
/*  83 */     if (filterRegion == null) {
/*  84 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  88 */     Filter sourceGraphic = filteredNode.getGraphicsNodeRable(true);
/*     */     
/*  90 */     PadRable8Bit padRable8Bit = new PadRable8Bit(sourceGraphic, filterRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */     
/*  94 */     FilterChainRable8Bit filterChainRable8Bit = new FilterChainRable8Bit((Filter)padRable8Bit, filterRegion);
/*     */ 
/*     */ 
/*     */     
/*  98 */     float[] filterRes = SVGUtilities.convertFilterRes(filterElement, ctx);
/*  99 */     filterChainRable8Bit.setFilterResolutionX((int)filterRes[0]);
/* 100 */     filterChainRable8Bit.setFilterResolutionY((int)filterRes[1]);
/*     */ 
/*     */ 
/*     */     
/* 104 */     Map<Object, Object> filterNodeMap = new HashMap<Object, Object>(11);
/* 105 */     filterNodeMap.put("SourceGraphic", padRable8Bit);
/*     */ 
/*     */     
/* 108 */     Filter in = buildFilterPrimitives(filterElement, filterRegion, filteredElement, filteredNode, (Filter)padRable8Bit, filterNodeMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     if (in == null)
/*     */     {
/* 117 */       return null; } 
/* 118 */     if (in == padRable8Bit)
/*     */     {
/* 120 */       in = createEmptyFilter(filterElement, filterRegion, filteredElement, filteredNode, ctx);
/*     */     }
/*     */     
/* 123 */     filterChainRable8Bit.setSource(in);
/* 124 */     return (Filter)filterChainRable8Bit;
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
/*     */   protected static Filter createEmptyFilter(Element filterElement, Rectangle2D filterRegion, Element filteredElement, GraphicsNode filteredNode, BridgeContext ctx) {
/* 137 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(null, filterElement, filteredElement, filteredNode, filterRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     return (Filter)new FloodRable8Bit(primitiveRegion, TRANSPARENT_BLACK);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Filter buildFilterPrimitives(Element filterElement, Rectangle2D filterRegion, Element filteredElement, GraphicsNode filteredNode, Filter in, Map filterNodeMap, BridgeContext ctx) {
/* 172 */     List<ParsedURL> refs = new LinkedList();
/*     */     while (true) {
/* 174 */       Filter newIn = buildLocalFilterPrimitives(filterElement, filterRegion, filteredElement, filteredNode, in, filterNodeMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 181 */       if (newIn != in) {
/* 182 */         return newIn;
/*     */       }
/* 184 */       String uri = XLinkSupport.getXLinkHref(filterElement);
/* 185 */       if (uri.length() == 0) {
/* 186 */         return in;
/*     */       }
/*     */       
/* 189 */       SVGOMDocument doc = (SVGOMDocument)filterElement.getOwnerDocument();
/* 190 */       ParsedURL url = new ParsedURL(doc.getURLObject(), uri);
/* 191 */       if (refs.contains(url)) {
/* 192 */         throw new BridgeException(ctx, filterElement, "xlink.href.circularDependencies", new Object[] { uri });
/*     */       }
/*     */ 
/*     */       
/* 196 */       refs.add(url);
/* 197 */       filterElement = ctx.getReferencedElement(filterElement, uri);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Filter buildLocalFilterPrimitives(Element filterElement, Rectangle2D filterRegion, Element filteredElement, GraphicsNode filteredNode, Filter in, Map filterNodeMap, BridgeContext ctx) {
/* 224 */     Node n = filterElement.getFirstChild();
/* 225 */     for (; n != null; 
/* 226 */       n = n.getNextSibling()) {
/*     */       
/* 228 */       if (n.getNodeType() == 1) {
/*     */ 
/*     */         
/* 231 */         Element e = (Element)n;
/* 232 */         Bridge bridge = ctx.getBridge(e);
/* 233 */         if (bridge != null && bridge instanceof FilterPrimitiveBridge) {
/*     */ 
/*     */           
/* 236 */           FilterPrimitiveBridge filterBridge = (FilterPrimitiveBridge)bridge;
/* 237 */           Filter filterNode = filterBridge.createFilter(ctx, e, filteredElement, filteredNode, in, filterRegion, filterNodeMap);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 244 */           if (filterNode == null) {
/* 245 */             return null;
/*     */           }
/* 247 */           in = filterNode;
/*     */         } 
/*     */       } 
/* 250 */     }  return in;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFilterElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */