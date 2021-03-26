/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
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
/*     */ public class SVGBridgeExtension
/*     */   implements BridgeExtension
/*     */ {
/*     */   public float getPriority() {
/*  42 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator getImplementedExtensions() {
/*  51 */     return Collections.EMPTY_LIST.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAuthor() {
/*  59 */     return "The Apache Batik Team.";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContactAddress() {
/*  66 */     return "batik-dev@xmlgraphics.apache.org";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURL() {
/*  74 */     return "http://xml.apache.org/batik";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/*  83 */     return "The required SVG 1.0 tags";
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
/*     */   public void registerTags(BridgeContext ctx) {
/*  97 */     ctx.putBridge(new SVGAElementBridge());
/*  98 */     ctx.putBridge(new SVGAltGlyphElementBridge());
/*  99 */     ctx.putBridge(new SVGCircleElementBridge());
/* 100 */     ctx.putBridge(new SVGClipPathElementBridge());
/* 101 */     ctx.putBridge(new SVGColorProfileElementBridge());
/* 102 */     ctx.putBridge(new SVGDescElementBridge());
/* 103 */     ctx.putBridge(new SVGEllipseElementBridge());
/* 104 */     ctx.putBridge(new SVGFeBlendElementBridge());
/* 105 */     ctx.putBridge(new SVGFeColorMatrixElementBridge());
/* 106 */     ctx.putBridge(new SVGFeComponentTransferElementBridge());
/* 107 */     ctx.putBridge(new SVGFeCompositeElementBridge());
/* 108 */     ctx.putBridge(new SVGFeComponentTransferElementBridge.SVGFeFuncAElementBridge());
/* 109 */     ctx.putBridge(new SVGFeComponentTransferElementBridge.SVGFeFuncRElementBridge());
/* 110 */     ctx.putBridge(new SVGFeComponentTransferElementBridge.SVGFeFuncGElementBridge());
/* 111 */     ctx.putBridge(new SVGFeComponentTransferElementBridge.SVGFeFuncBElementBridge());
/* 112 */     ctx.putBridge(new SVGFeConvolveMatrixElementBridge());
/* 113 */     ctx.putBridge(new SVGFeDiffuseLightingElementBridge());
/* 114 */     ctx.putBridge(new SVGFeDisplacementMapElementBridge());
/* 115 */     ctx.putBridge(new AbstractSVGLightingElementBridge.SVGFeDistantLightElementBridge());
/* 116 */     ctx.putBridge(new SVGFeFloodElementBridge());
/* 117 */     ctx.putBridge(new SVGFeGaussianBlurElementBridge());
/* 118 */     ctx.putBridge(new SVGFeImageElementBridge());
/* 119 */     ctx.putBridge(new SVGFeMergeElementBridge());
/* 120 */     ctx.putBridge(new SVGFeMergeElementBridge.SVGFeMergeNodeElementBridge());
/* 121 */     ctx.putBridge(new SVGFeMorphologyElementBridge());
/* 122 */     ctx.putBridge(new SVGFeOffsetElementBridge());
/* 123 */     ctx.putBridge(new AbstractSVGLightingElementBridge.SVGFePointLightElementBridge());
/* 124 */     ctx.putBridge(new SVGFeSpecularLightingElementBridge());
/* 125 */     ctx.putBridge(new AbstractSVGLightingElementBridge.SVGFeSpotLightElementBridge());
/* 126 */     ctx.putBridge(new SVGFeTileElementBridge());
/* 127 */     ctx.putBridge(new SVGFeTurbulenceElementBridge());
/* 128 */     ctx.putBridge(new SVGFontElementBridge());
/* 129 */     ctx.putBridge(new SVGFontFaceElementBridge());
/* 130 */     ctx.putBridge(new SVGFilterElementBridge());
/* 131 */     ctx.putBridge(new SVGGElementBridge());
/* 132 */     ctx.putBridge(new SVGGlyphElementBridge());
/* 133 */     ctx.putBridge(new SVGHKernElementBridge());
/* 134 */     ctx.putBridge(new SVGImageElementBridge());
/* 135 */     ctx.putBridge(new SVGLineElementBridge());
/* 136 */     ctx.putBridge(new SVGLinearGradientElementBridge());
/* 137 */     ctx.putBridge(new SVGMarkerElementBridge());
/* 138 */     ctx.putBridge(new SVGMaskElementBridge());
/* 139 */     ctx.putBridge(new SVGMissingGlyphElementBridge());
/* 140 */     ctx.putBridge(new SVGPathElementBridge());
/* 141 */     ctx.putBridge(new SVGPatternElementBridge());
/* 142 */     ctx.putBridge(new SVGPolylineElementBridge());
/* 143 */     ctx.putBridge(new SVGPolygonElementBridge());
/* 144 */     ctx.putBridge(new SVGRadialGradientElementBridge());
/* 145 */     ctx.putBridge(new SVGRectElementBridge());
/* 146 */     ctx.putBridge(new AbstractSVGGradientElementBridge.SVGStopElementBridge());
/* 147 */     ctx.putBridge(new SVGSVGElementBridge());
/* 148 */     ctx.putBridge(new SVGSwitchElementBridge());
/* 149 */     ctx.putBridge(new SVGTextElementBridge());
/* 150 */     ctx.putBridge(new SVGTextPathElementBridge());
/* 151 */     ctx.putBridge(new SVGTitleElementBridge());
/* 152 */     ctx.putBridge(new SVGUseElementBridge());
/* 153 */     ctx.putBridge(new SVGVKernElementBridge());
/* 154 */     ctx.putBridge(new SVGSetElementBridge());
/* 155 */     ctx.putBridge(new SVGAnimateElementBridge());
/* 156 */     ctx.putBridge(new SVGAnimateColorElementBridge());
/* 157 */     ctx.putBridge(new SVGAnimateTransformElementBridge());
/* 158 */     ctx.putBridge(new SVGAnimateMotionElementBridge());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDynamicElement(Element e) {
/* 169 */     String ns = e.getNamespaceURI();
/* 170 */     if (!"http://www.w3.org/2000/svg".equals(ns)) {
/* 171 */       return false;
/*     */     }
/* 173 */     String ln = e.getLocalName();
/* 174 */     if (ln.equals("script") || ln.startsWith("animate") || ln.equals("set"))
/*     */     {
/*     */       
/* 177 */       return true;
/*     */     }
/* 179 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGBridgeExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */