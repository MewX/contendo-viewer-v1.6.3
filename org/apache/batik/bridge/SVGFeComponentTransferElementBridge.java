/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.ext.awt.image.ComponentTransferFunction;
/*     */ import org.apache.batik.ext.awt.image.ConcreteComponentTransferFunction;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.ComponentTransferRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
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
/*     */ public class SVGFeComponentTransferElementBridge
/*     */   extends AbstractSVGFilterPrimitiveElementBridge
/*     */ {
/*     */   public String getLocalName() {
/*  53 */     return "feComponentTransfer";
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter createFilter(BridgeContext ctx, Element filterElement, Element filteredElement, GraphicsNode filteredNode, Filter inputFilter, Rectangle2D filterRegion, Map filterMap) {
/*  83 */     Filter in = getIn(filterElement, filteredElement, filteredNode, inputFilter, filterMap, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     if (in == null) {
/*  90 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     Rectangle2D defaultRegion = in.getBounds2D();
/*  97 */     Rectangle2D primitiveRegion = SVGUtilities.convertFilterPrimitiveRegion(filterElement, filteredElement, filteredNode, defaultRegion, filterRegion, ctx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     ComponentTransferFunction funcR = null;
/* 109 */     ComponentTransferFunction funcG = null;
/* 110 */     ComponentTransferFunction funcB = null;
/* 111 */     ComponentTransferFunction funcA = null;
/*     */     
/* 113 */     Node n = filterElement.getFirstChild();
/* 114 */     for (; n != null; 
/* 115 */       n = n.getNextSibling()) {
/*     */       
/* 117 */       if (n.getNodeType() == 1) {
/*     */ 
/*     */ 
/*     */         
/* 121 */         Element e = (Element)n;
/* 122 */         Bridge bridge = ctx.getBridge(e);
/* 123 */         if (bridge != null && bridge instanceof SVGFeFuncElementBridge) {
/*     */ 
/*     */           
/* 126 */           SVGFeFuncElementBridge funcBridge = (SVGFeFuncElementBridge)bridge;
/*     */           
/* 128 */           ComponentTransferFunction func = funcBridge.createComponentTransferFunction(filterElement, e);
/*     */           
/* 130 */           if (funcBridge instanceof SVGFeFuncRElementBridge) {
/* 131 */             funcR = func;
/* 132 */           } else if (funcBridge instanceof SVGFeFuncGElementBridge) {
/* 133 */             funcG = func;
/* 134 */           } else if (funcBridge instanceof SVGFeFuncBElementBridge) {
/* 135 */             funcB = func;
/* 136 */           } else if (funcBridge instanceof SVGFeFuncAElementBridge) {
/* 137 */             funcA = func;
/*     */           } 
/*     */         } 
/*     */       } 
/* 141 */     }  ComponentTransferRable8Bit componentTransferRable8Bit = new ComponentTransferRable8Bit(in, funcA, funcR, funcG, funcB);
/*     */ 
/*     */ 
/*     */     
/* 145 */     handleColorInterpolationFilters((Filter)componentTransferRable8Bit, filterElement);
/*     */     
/* 147 */     PadRable8Bit padRable8Bit = new PadRable8Bit((Filter)componentTransferRable8Bit, primitiveRegion, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 150 */     updateFilterMap(filterElement, (Filter)padRable8Bit, filterMap);
/*     */     
/* 152 */     return (Filter)padRable8Bit;
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
/*     */   public static class SVGFeFuncAElementBridge
/*     */     extends SVGFeFuncElementBridge
/*     */   {
/*     */     public String getLocalName() {
/* 169 */       return "feFuncA";
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
/*     */   public static class SVGFeFuncRElementBridge
/*     */     extends SVGFeFuncElementBridge
/*     */   {
/*     */     public String getLocalName() {
/* 187 */       return "feFuncR";
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
/*     */   public static class SVGFeFuncGElementBridge
/*     */     extends SVGFeFuncElementBridge
/*     */   {
/*     */     public String getLocalName() {
/* 205 */       return "feFuncG";
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
/*     */   public static class SVGFeFuncBElementBridge
/*     */     extends SVGFeFuncElementBridge
/*     */   {
/*     */     public String getLocalName() {
/* 223 */       return "feFuncB";
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
/*     */   protected static abstract class SVGFeFuncElementBridge
/*     */     extends AnimatableGenericSVGBridge
/*     */   {
/*     */     public ComponentTransferFunction createComponentTransferFunction(Element filterElement, Element funcElement) {
/*     */       float arrayOfFloat1[], amplitude, slope, v[], exponent, intercept, offset;
/* 248 */       int type = convertType(funcElement, this.ctx);
/* 249 */       switch (type) {
/*     */         case 2:
/* 251 */           arrayOfFloat1 = convertTableValues(funcElement, this.ctx);
/* 252 */           if (arrayOfFloat1 == null) {
/* 253 */             return ConcreteComponentTransferFunction.getIdentityTransfer();
/*     */           }
/* 255 */           return ConcreteComponentTransferFunction.getDiscreteTransfer(arrayOfFloat1);
/*     */ 
/*     */         
/*     */         case 0:
/* 259 */           return ConcreteComponentTransferFunction.getIdentityTransfer();
/*     */ 
/*     */         
/*     */         case 4:
/* 263 */           amplitude = AbstractSVGFilterPrimitiveElementBridge.convertNumber(funcElement, "amplitude", 1.0F, this.ctx);
/*     */ 
/*     */           
/* 266 */           exponent = AbstractSVGFilterPrimitiveElementBridge.convertNumber(funcElement, "exponent", 1.0F, this.ctx);
/*     */ 
/*     */           
/* 269 */           offset = AbstractSVGFilterPrimitiveElementBridge.convertNumber(funcElement, "offset", 0.0F, this.ctx);
/*     */ 
/*     */           
/* 272 */           return ConcreteComponentTransferFunction.getGammaTransfer(amplitude, exponent, offset);
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 277 */           slope = AbstractSVGFilterPrimitiveElementBridge.convertNumber(funcElement, "slope", 1.0F, this.ctx);
/*     */ 
/*     */           
/* 280 */           intercept = AbstractSVGFilterPrimitiveElementBridge.convertNumber(funcElement, "intercept", 0.0F, this.ctx);
/*     */ 
/*     */           
/* 283 */           return ConcreteComponentTransferFunction.getLinearTransfer(slope, intercept);
/*     */ 
/*     */         
/*     */         case 1:
/* 287 */           v = convertTableValues(funcElement, this.ctx);
/* 288 */           if (v == null) {
/* 289 */             return ConcreteComponentTransferFunction.getIdentityTransfer();
/*     */           }
/* 291 */           return ConcreteComponentTransferFunction.getTableTransfer(v);
/*     */       } 
/*     */ 
/*     */       
/* 295 */       throw new RuntimeException("invalid convertType:" + type);
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
/*     */     protected static float[] convertTableValues(Element e, BridgeContext ctx) {
/* 308 */       String s = e.getAttributeNS((String)null, "tableValues");
/* 309 */       if (s.length() == 0) {
/* 310 */         return null;
/*     */       }
/* 312 */       StringTokenizer tokens = new StringTokenizer(s, " ,");
/* 313 */       float[] v = new float[tokens.countTokens()];
/*     */       try {
/* 315 */         for (int i = 0; tokens.hasMoreTokens(); i++) {
/* 316 */           v[i] = SVGUtilities.convertSVGNumber(tokens.nextToken());
/*     */         }
/* 318 */       } catch (NumberFormatException nfEx) {
/* 319 */         throw new BridgeException(ctx, e, nfEx, "attribute.malformed", new Object[] { "tableValues", s });
/*     */       } 
/*     */ 
/*     */       
/* 323 */       return v;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected static int convertType(Element e, BridgeContext ctx) {
/* 334 */       String s = e.getAttributeNS((String)null, "type");
/* 335 */       if (s.length() == 0) {
/* 336 */         throw new BridgeException(ctx, e, "attribute.missing", new Object[] { "type" });
/*     */       }
/*     */       
/* 339 */       if ("discrete".equals(s)) {
/* 340 */         return 2;
/*     */       }
/* 342 */       if ("identity".equals(s)) {
/* 343 */         return 0;
/*     */       }
/* 345 */       if ("gamma".equals(s)) {
/* 346 */         return 4;
/*     */       }
/* 348 */       if ("linear".equals(s)) {
/* 349 */         return 3;
/*     */       }
/* 351 */       if ("table".equals(s)) {
/* 352 */         return 1;
/*     */       }
/* 354 */       throw new BridgeException(ctx, e, "attribute.malformed", new Object[] { "type", s });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFeComponentTransferElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */