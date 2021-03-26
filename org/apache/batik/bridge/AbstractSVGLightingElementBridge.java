/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.ext.awt.image.DistantLight;
/*     */ import org.apache.batik.ext.awt.image.Light;
/*     */ import org.apache.batik.ext.awt.image.PointLight;
/*     */ import org.apache.batik.ext.awt.image.SpotLight;
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
/*     */ public abstract class AbstractSVGLightingElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   protected static Light extractLight(Element filterElement, BridgeContext ctx) {
/*  55 */     Color color = CSSUtilities.convertLightingColor(filterElement, ctx);
/*     */     
/*  57 */     Node n = filterElement.getFirstChild();
/*  58 */     for (; n != null; 
/*  59 */       n = n.getNextSibling()) {
/*     */       
/*  61 */       if (n.getNodeType() == 1) {
/*     */ 
/*     */ 
/*     */         
/*  65 */         Element e = (Element)n;
/*  66 */         Bridge bridge = ctx.getBridge(e);
/*  67 */         if (bridge != null && bridge instanceof AbstractSVGLightElementBridge)
/*     */         {
/*     */ 
/*     */           
/*  71 */           return ((AbstractSVGLightElementBridge)bridge).createLight(ctx, filterElement, e, color); } 
/*     */       } 
/*     */     } 
/*  74 */     return null;
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
/*     */   protected static double[] convertKernelUnitLength(Element filterElement, BridgeContext ctx) {
/*  86 */     String s = filterElement.getAttributeNS(null, "kernelUnitLength");
/*     */     
/*  88 */     if (s.length() == 0) {
/*  89 */       return null;
/*     */     }
/*  91 */     double[] units = new double[2];
/*  92 */     StringTokenizer tokens = new StringTokenizer(s, " ,");
/*     */     try {
/*  94 */       units[0] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/*  95 */       if (tokens.hasMoreTokens()) {
/*  96 */         units[1] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/*     */       } else {
/*  98 */         units[1] = units[0];
/*     */       } 
/* 100 */     } catch (NumberFormatException nfEx) {
/* 101 */       throw new BridgeException(ctx, filterElement, nfEx, "attribute.malformed", new Object[] { "kernelUnitLength", s });
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 106 */     if (tokens.hasMoreTokens() || units[0] <= 0.0D || units[1] <= 0.0D) {
/* 107 */       throw new BridgeException(ctx, filterElement, "attribute.malformed", new Object[] { "kernelUnitLength", s });
/*     */     }
/*     */ 
/*     */     
/* 111 */     return units;
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
/*     */   protected static abstract class AbstractSVGLightElementBridge
/*     */     extends AnimatableGenericSVGBridge
/*     */   {
/*     */     public abstract Light createLight(BridgeContext param1BridgeContext, Element param1Element1, Element param1Element2, Color param1Color);
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
/*     */   public static class SVGFeSpotLightElementBridge
/*     */     extends AbstractSVGLightElementBridge
/*     */   {
/*     */     public String getLocalName() {
/* 149 */       return "feSpotLight";
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
/*     */     public Light createLight(BridgeContext ctx, Element filterElement, Element lightElement, Color color) {
/* 166 */       double x = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "x", 0.0F, ctx);
/*     */ 
/*     */       
/* 169 */       double y = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "y", 0.0F, ctx);
/*     */ 
/*     */       
/* 172 */       double z = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "z", 0.0F, ctx);
/*     */ 
/*     */       
/* 175 */       double px = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "pointsAtX", 0.0F, ctx);
/*     */ 
/*     */ 
/*     */       
/* 179 */       double py = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "pointsAtY", 0.0F, ctx);
/*     */ 
/*     */ 
/*     */       
/* 183 */       double pz = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "pointsAtZ", 0.0F, ctx);
/*     */ 
/*     */ 
/*     */       
/* 187 */       double specularExponent = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "specularExponent", 1.0F, ctx);
/*     */ 
/*     */ 
/*     */       
/* 191 */       double limitingConeAngle = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "limitingConeAngle", 90.0F, ctx);
/*     */ 
/*     */       
/* 194 */       return (Light)new SpotLight(x, y, z, px, py, pz, specularExponent, limitingConeAngle, color);
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
/*     */   public static class SVGFeDistantLightElementBridge
/*     */     extends AbstractSVGLightElementBridge
/*     */   {
/*     */     public String getLocalName() {
/* 217 */       return "feDistantLight";
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
/*     */     public Light createLight(BridgeContext ctx, Element filterElement, Element lightElement, Color color) {
/* 234 */       double azimuth = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "azimuth", 0.0F, ctx);
/*     */ 
/*     */ 
/*     */       
/* 238 */       double elevation = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "elevation", 0.0F, ctx);
/*     */ 
/*     */       
/* 241 */       return (Light)new DistantLight(azimuth, elevation, color);
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
/*     */   public static class SVGFePointLightElementBridge
/*     */     extends AbstractSVGLightElementBridge
/*     */   {
/*     */     public String getLocalName() {
/* 260 */       return "fePointLight";
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
/*     */     public Light createLight(BridgeContext ctx, Element filterElement, Element lightElement, Color color) {
/* 277 */       double x = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "x", 0.0F, ctx);
/*     */ 
/*     */       
/* 280 */       double y = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "y", 0.0F, ctx);
/*     */ 
/*     */       
/* 283 */       double z = AbstractSVGFilterPrimitiveElementBridge.convertNumber(lightElement, "z", 0.0F, ctx);
/*     */       
/* 285 */       return (Light)new PointLight(x, y, z, color);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/AbstractSVGLightingElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */