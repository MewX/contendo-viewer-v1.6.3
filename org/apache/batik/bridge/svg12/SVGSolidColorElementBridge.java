/*     */ package org.apache.batik.bridge.svg12;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.bridge.AnimatableGenericSVGBridge;
/*     */ import org.apache.batik.bridge.BridgeContext;
/*     */ import org.apache.batik.bridge.BridgeException;
/*     */ import org.apache.batik.bridge.CSSUtilities;
/*     */ import org.apache.batik.bridge.PaintBridge;
/*     */ import org.apache.batik.bridge.PaintServer;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.StyleMap;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.util.ParsedURL;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGSolidColorElementBridge
/*     */   extends AnimatableGenericSVGBridge
/*     */   implements PaintBridge
/*     */ {
/*     */   public String getNamespaceURI() {
/*  64 */     return "http://www.w3.org/2000/svg";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  71 */     return "solidColor";
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
/*     */   public Paint createPaint(BridgeContext ctx, Element paintElement, Element paintedElement, GraphicsNode paintedNode, float opacity) {
/*  89 */     opacity = extractOpacity(paintElement, opacity, ctx);
/*     */     
/*  91 */     return extractColor(paintElement, opacity, ctx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static float extractOpacity(Element paintElement, float opacity, BridgeContext ctx) {
/*  97 */     Map<Object, Object> refs = new HashMap<Object, Object>();
/*  98 */     CSSEngine eng = CSSUtilities.getCSSEngine(paintElement);
/*  99 */     int pidx = eng.getPropertyIndex("solid-opacity");
/*     */ 
/*     */     
/*     */     while (true) {
/* 103 */       Value opacityVal = CSSUtilities.getComputedStyle(paintElement, pidx);
/*     */ 
/*     */ 
/*     */       
/* 107 */       StyleMap sm = ((CSSStylableElement)paintElement).getComputedStyleMap(null);
/*     */       
/* 109 */       if (!sm.isNullCascaded(pidx)) {
/*     */         
/* 111 */         float attr = PaintServer.convertOpacity(opacityVal);
/* 112 */         return opacity * attr;
/*     */       } 
/*     */       
/* 115 */       String uri = XLinkSupport.getXLinkHref(paintElement);
/* 116 */       if (uri.length() == 0) {
/* 117 */         return opacity;
/*     */       }
/*     */       
/* 120 */       SVGOMDocument doc = (SVGOMDocument)paintElement.getOwnerDocument();
/* 121 */       ParsedURL purl = new ParsedURL(doc.getURL(), uri);
/*     */ 
/*     */       
/* 124 */       if (refs.containsKey(purl)) {
/* 125 */         throw new BridgeException(ctx, paintElement, "xlink.href.circularDependencies", new Object[] { uri });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 130 */       refs.put(purl, purl);
/* 131 */       paintElement = ctx.getReferencedElement(paintElement, uri);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Color extractColor(Element paintElement, float opacity, BridgeContext ctx) {
/* 138 */     Map<Object, Object> refs = new HashMap<Object, Object>();
/* 139 */     CSSEngine eng = CSSUtilities.getCSSEngine(paintElement);
/* 140 */     int pidx = eng.getPropertyIndex("solid-color");
/*     */ 
/*     */     
/*     */     while (true) {
/* 144 */       Value colorDef = CSSUtilities.getComputedStyle(paintElement, pidx);
/*     */ 
/*     */ 
/*     */       
/* 148 */       StyleMap sm = ((CSSStylableElement)paintElement).getComputedStyleMap(null);
/*     */       
/* 150 */       if (!sm.isNullCascaded(pidx)) {
/*     */         
/* 152 */         if (colorDef.getCssValueType() == 1)
/*     */         {
/* 154 */           return PaintServer.convertColor(colorDef, opacity);
/*     */         }
/* 156 */         return PaintServer.convertRGBICCColor(paintElement, colorDef.item(0), colorDef.item(1), opacity, ctx);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 164 */       String uri = XLinkSupport.getXLinkHref(paintElement);
/* 165 */       if (uri.length() == 0)
/*     */       {
/* 167 */         return new Color(0.0F, 0.0F, 0.0F, opacity);
/*     */       }
/*     */       
/* 170 */       SVGOMDocument doc = (SVGOMDocument)paintElement.getOwnerDocument();
/* 171 */       ParsedURL purl = new ParsedURL(doc.getURL(), uri);
/*     */ 
/*     */       
/* 174 */       if (refs.containsKey(purl)) {
/* 175 */         throw new BridgeException(ctx, paintElement, "xlink.href.circularDependencies", new Object[] { uri });
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 180 */       refs.put(purl, purl);
/* 181 */       paintElement = ctx.getReferencedElement(paintElement, uri);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/SVGSolidColorElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */