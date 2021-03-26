/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import org.apache.batik.dom.AbstractNode;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.ext.awt.MultipleGradientPaint;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGGradientElementBridge
/*     */   extends AnimatableGenericSVGBridge
/*     */   implements ErrorConstants, PaintBridge
/*     */ {
/*     */   public Paint createPaint(BridgeContext ctx, Element paintElement, Element paintedElement, GraphicsNode paintedNode, float opacity) {
/*     */     AffineTransform transform;
/*  70 */     List stops = extractStop(paintElement, opacity, ctx);
/*     */     
/*  72 */     if (stops == null) {
/*  73 */       return null;
/*     */     }
/*  75 */     int stopLength = stops.size();
/*     */     
/*  77 */     if (stopLength == 1) {
/*  78 */       return ((Stop)stops.get(0)).color;
/*     */     }
/*  80 */     float[] offsets = new float[stopLength];
/*  81 */     Color[] colors = new Color[stopLength];
/*  82 */     Iterator<Stop> iter = stops.iterator();
/*  83 */     for (int i = 0; iter.hasNext(); i++) {
/*  84 */       Stop stop = iter.next();
/*  85 */       offsets[i] = stop.offset;
/*  86 */       colors[i] = stop.color;
/*     */     } 
/*     */ 
/*     */     
/*  90 */     MultipleGradientPaint.CycleMethodEnum spreadMethod = MultipleGradientPaint.NO_CYCLE;
/*     */     
/*  92 */     String s = SVGUtilities.getChainableAttributeNS(paintElement, null, "spreadMethod", ctx);
/*     */     
/*  94 */     if (s.length() != 0) {
/*  95 */       spreadMethod = convertSpreadMethod(paintElement, s, ctx);
/*     */     }
/*     */ 
/*     */     
/*  99 */     MultipleGradientPaint.ColorSpaceEnum colorSpace = CSSUtilities.convertColorInterpolation(paintElement);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     s = SVGUtilities.getChainableAttributeNS(paintElement, null, "gradientTransform", ctx);
/*     */     
/* 106 */     if (s.length() != 0) {
/* 107 */       transform = SVGUtilities.convertTransform(paintElement, "gradientTransform", s, ctx);
/*     */     } else {
/*     */       
/* 110 */       transform = new AffineTransform();
/*     */     } 
/*     */     
/* 113 */     Paint paint = buildGradient(paintElement, paintedElement, paintedNode, spreadMethod, colorSpace, transform, colors, offsets, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     return paint;
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
/*     */   protected abstract Paint buildGradient(Element paramElement1, Element paramElement2, GraphicsNode paramGraphicsNode, MultipleGradientPaint.CycleMethodEnum paramCycleMethodEnum, MultipleGradientPaint.ColorSpaceEnum paramColorSpaceEnum, AffineTransform paramAffineTransform, Color[] paramArrayOfColor, float[] paramArrayOffloat, BridgeContext paramBridgeContext);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static MultipleGradientPaint.CycleMethodEnum convertSpreadMethod(Element paintElement, String s, BridgeContext ctx) {
/* 160 */     if ("repeat".equals(s)) {
/* 161 */       return MultipleGradientPaint.REPEAT;
/*     */     }
/* 163 */     if ("reflect".equals(s)) {
/* 164 */       return MultipleGradientPaint.REFLECT;
/*     */     }
/* 166 */     if ("pad".equals(s)) {
/* 167 */       return MultipleGradientPaint.NO_CYCLE;
/*     */     }
/* 169 */     throw new BridgeException(ctx, paintElement, "attribute.malformed", new Object[] { "spreadMethod", s });
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
/*     */   protected static List extractStop(Element paintElement, float opacity, BridgeContext ctx) {
/* 187 */     List<ParsedURL> refs = new LinkedList();
/*     */     while (true) {
/* 189 */       List stops = extractLocalStop(paintElement, opacity, ctx);
/* 190 */       if (stops != null) {
/* 191 */         return stops;
/*     */       }
/* 193 */       String uri = XLinkSupport.getXLinkHref(paintElement);
/* 194 */       if (uri.length() == 0) {
/* 195 */         return null;
/*     */       }
/*     */       
/* 198 */       String baseURI = ((AbstractNode)paintElement).getBaseURI();
/* 199 */       ParsedURL purl = new ParsedURL(baseURI, uri);
/*     */       
/* 201 */       if (contains(refs, purl)) {
/* 202 */         throw new BridgeException(ctx, paintElement, "xlink.href.circularDependencies", new Object[] { uri });
/*     */       }
/*     */ 
/*     */       
/* 206 */       refs.add(purl);
/* 207 */       paintElement = ctx.getReferencedElement(paintElement, uri);
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
/*     */   protected static List extractLocalStop(Element gradientElement, float opacity, BridgeContext ctx) {
/* 222 */     LinkedList<Stop> stops = null;
/* 223 */     Stop previous = null;
/* 224 */     Node n = gradientElement.getFirstChild();
/* 225 */     for (; n != null; 
/* 226 */       n = n.getNextSibling()) {
/*     */       
/* 228 */       if (n.getNodeType() == 1) {
/*     */ 
/*     */ 
/*     */         
/* 232 */         Element e = (Element)n;
/* 233 */         Bridge bridge = ctx.getBridge(e);
/* 234 */         if (bridge != null && bridge instanceof SVGStopElementBridge)
/*     */         
/*     */         { 
/* 237 */           Stop stop = ((SVGStopElementBridge)bridge).createStop(ctx, gradientElement, e, opacity);
/*     */           
/* 239 */           if (stops == null) {
/* 240 */             stops = new LinkedList();
/*     */           }
/* 242 */           if (previous != null && 
/* 243 */             stop.offset < previous.offset) {
/* 244 */             stop.offset = previous.offset;
/*     */           }
/*     */           
/* 247 */           stops.add(stop);
/* 248 */           previous = stop; } 
/*     */       } 
/* 250 */     }  return stops;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean contains(List urls, ParsedURL key) {
/* 260 */     for (Object url : urls) {
/* 261 */       if (key.equals(url))
/* 262 */         return true; 
/*     */     } 
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Stop
/*     */   {
/*     */     public Color color;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float offset;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Stop(Color color, float offset) {
/* 284 */       this.color = color;
/* 285 */       this.offset = offset;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SVGStopElementBridge
/*     */     extends AnimatableGenericSVGBridge
/*     */     implements Bridge
/*     */   {
/*     */     public String getLocalName() {
/* 299 */       return "stop";
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
/*     */     public AbstractSVGGradientElementBridge.Stop createStop(BridgeContext ctx, Element gradientElement, Element stopElement, float opacity) {
/*     */       float offset;
/* 315 */       String s = stopElement.getAttributeNS((String)null, "offset");
/* 316 */       if (s.length() == 0) {
/* 317 */         throw new BridgeException(ctx, stopElement, "attribute.missing", new Object[] { "offset" });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 323 */         offset = SVGUtilities.convertRatio(s);
/* 324 */       } catch (NumberFormatException nfEx) {
/* 325 */         throw new BridgeException(ctx, stopElement, nfEx, "attribute.malformed", new Object[] { "offset", s, nfEx });
/*     */       } 
/*     */ 
/*     */       
/* 329 */       Color color = CSSUtilities.convertStopColor(stopElement, opacity, ctx);
/*     */ 
/*     */       
/* 332 */       return new AbstractSVGGradientElementBridge.Stop(color, offset);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/AbstractSVGGradientElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */