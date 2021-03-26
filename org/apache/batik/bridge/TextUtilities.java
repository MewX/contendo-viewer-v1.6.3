/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.util.ArrayList;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.parser.UnitProcessor;
/*     */ import org.apache.batik.util.CSSConstants;
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
/*     */ public abstract class TextUtilities
/*     */   implements ErrorConstants, CSSConstants
/*     */ {
/*     */   public static String getElementContent(Element e) {
/*  45 */     StringBuffer result = new StringBuffer();
/*  46 */     Node n = e.getFirstChild();
/*  47 */     for (; n != null; 
/*  48 */       n = n.getNextSibling()) {
/*  49 */       switch (n.getNodeType()) {
/*     */         case 1:
/*  51 */           result.append(getElementContent((Element)n));
/*     */           break;
/*     */         case 3:
/*     */         case 4:
/*  55 */           result.append(n.getNodeValue()); break;
/*     */       } 
/*     */     } 
/*  58 */     return result.toString();
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
/*     */   public static ArrayList svgHorizontalCoordinateArrayToUserSpace(Element element, String attrName, String valueStr, BridgeContext ctx) {
/*  76 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, element);
/*  77 */     ArrayList<Float> values = new ArrayList();
/*  78 */     StringTokenizer st = new StringTokenizer(valueStr, ", ", false);
/*  79 */     while (st.hasMoreTokens()) {
/*  80 */       values.add(Float.valueOf(UnitProcessor.svgHorizontalCoordinateToUserSpace(st.nextToken(), attrName, uctx)));
/*     */     }
/*     */ 
/*     */     
/*  84 */     return values;
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
/*     */   public static ArrayList svgVerticalCoordinateArrayToUserSpace(Element element, String attrName, String valueStr, BridgeContext ctx) {
/* 102 */     UnitProcessor.Context uctx = UnitProcessor.createContext(ctx, element);
/* 103 */     ArrayList<Float> values = new ArrayList();
/* 104 */     StringTokenizer st = new StringTokenizer(valueStr, ", ", false);
/* 105 */     while (st.hasMoreTokens()) {
/* 106 */       values.add(Float.valueOf(UnitProcessor.svgVerticalCoordinateToUserSpace(st.nextToken(), attrName, uctx)));
/*     */     }
/*     */ 
/*     */     
/* 110 */     return values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList svgRotateArrayToFloats(Element element, String attrName, String valueStr, BridgeContext ctx) {
/* 119 */     StringTokenizer st = new StringTokenizer(valueStr, ", ", false);
/* 120 */     ArrayList<Float> values = new ArrayList();
/*     */     
/* 122 */     while (st.hasMoreTokens()) {
/*     */       try {
/* 124 */         String s = st.nextToken();
/* 125 */         values.add(Float.valueOf((float)Math.toRadians(SVGUtilities.convertSVGNumber(s))));
/*     */       
/*     */       }
/* 128 */       catch (NumberFormatException nfEx) {
/* 129 */         throw new BridgeException(ctx, element, nfEx, "attribute.malformed", new Object[] { attrName, valueStr });
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 134 */     return values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Float convertFontSize(Element e) {
/* 142 */     Value v = CSSUtilities.getComputedStyle(e, 22);
/*     */     
/* 144 */     return Float.valueOf(v.getFloatValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Float convertFontStyle(Element e) {
/* 152 */     Value v = CSSUtilities.getComputedStyle(e, 25);
/*     */     
/* 154 */     switch (v.getStringValue().charAt(0)) {
/*     */       case 'n':
/* 156 */         return TextAttribute.POSTURE_REGULAR;
/*     */     } 
/* 158 */     return TextAttribute.POSTURE_OBLIQUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Float convertFontStretch(Element e) {
/* 167 */     Value v = CSSUtilities.getComputedStyle(e, 24);
/*     */     
/* 169 */     String s = v.getStringValue();
/* 170 */     switch (s.charAt(0)) {
/*     */       case 'u':
/* 172 */         if (s.charAt(6) == 'c') {
/* 173 */           return TextAttribute.WIDTH_CONDENSED;
/*     */         }
/* 175 */         return TextAttribute.WIDTH_EXTENDED;
/*     */ 
/*     */       
/*     */       case 'e':
/* 179 */         if (s.charAt(6) == 'c') {
/* 180 */           return TextAttribute.WIDTH_CONDENSED;
/*     */         }
/* 182 */         if (s.length() == 8) {
/* 183 */           return TextAttribute.WIDTH_SEMI_EXTENDED;
/*     */         }
/* 185 */         return TextAttribute.WIDTH_EXTENDED;
/*     */ 
/*     */ 
/*     */       
/*     */       case 's':
/* 190 */         if (s.charAt(6) == 'c') {
/* 191 */           return TextAttribute.WIDTH_SEMI_CONDENSED;
/*     */         }
/* 193 */         return TextAttribute.WIDTH_SEMI_EXTENDED;
/*     */     } 
/*     */ 
/*     */     
/* 197 */     return TextAttribute.WIDTH_REGULAR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Float convertFontWeight(Element e) {
/* 206 */     Value v = CSSUtilities.getComputedStyle(e, 27);
/*     */     
/* 208 */     int weight = (int)v.getFloatValue();
/*     */ 
/*     */     
/* 211 */     switch (weight) {
/*     */       case 100:
/* 213 */         return TextAttribute.WEIGHT_EXTRA_LIGHT;
/*     */       case 200:
/* 215 */         return TextAttribute.WEIGHT_LIGHT;
/*     */       case 300:
/* 217 */         return TextAttribute.WEIGHT_DEMILIGHT;
/*     */       case 400:
/* 219 */         return TextAttribute.WEIGHT_REGULAR;
/*     */       case 500:
/* 221 */         return TextAttribute.WEIGHT_SEMIBOLD;
/*     */     } 
/* 223 */     String javaVersionString = System.getProperty("java.specification.version");
/* 224 */     float javaVersion = (javaVersionString != null) ? Float.parseFloat(javaVersionString) : 1.5F;
/*     */     
/* 226 */     if (javaVersion < 1.5D)
/*     */     {
/*     */ 
/*     */       
/* 230 */       return TextAttribute.WEIGHT_BOLD;
/*     */     }
/* 232 */     switch (weight) {
/*     */       case 600:
/* 234 */         return TextAttribute.WEIGHT_MEDIUM;
/*     */       case 700:
/* 236 */         return TextAttribute.WEIGHT_BOLD;
/*     */       case 800:
/* 238 */         return TextAttribute.WEIGHT_HEAVY;
/*     */       case 900:
/* 240 */         return TextAttribute.WEIGHT_ULTRABOLD;
/*     */     } 
/* 242 */     return TextAttribute.WEIGHT_REGULAR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TextNode.Anchor convertTextAnchor(Element e) {
/* 252 */     Value v = CSSUtilities.getComputedStyle(e, 53);
/*     */     
/* 254 */     switch (v.getStringValue().charAt(0)) {
/*     */       case 's':
/* 256 */         return TextNode.Anchor.START;
/*     */       case 'm':
/* 258 */         return TextNode.Anchor.MIDDLE;
/*     */     } 
/* 260 */     return TextNode.Anchor.END;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object convertBaselineShift(Element e) {
/* 270 */     Value v = CSSUtilities.getComputedStyle(e, 1);
/*     */     
/* 272 */     if (v.getPrimitiveType() == 21) {
/* 273 */       String s = v.getStringValue();
/* 274 */       switch (s.charAt(2)) {
/*     */         case 'p':
/* 276 */           return TextAttribute.SUPERSCRIPT_SUPER;
/*     */         
/*     */         case 'b':
/* 279 */           return TextAttribute.SUPERSCRIPT_SUB;
/*     */       } 
/*     */       
/* 282 */       return null;
/*     */     } 
/*     */     
/* 285 */     return Float.valueOf(v.getFloatValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Float convertKerning(Element e) {
/* 295 */     Value v = CSSUtilities.getComputedStyle(e, 31);
/*     */     
/* 297 */     if (v.getPrimitiveType() == 21) {
/* 298 */       return null;
/*     */     }
/* 300 */     return Float.valueOf(v.getFloatValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Float convertLetterSpacing(Element e) {
/* 309 */     Value v = CSSUtilities.getComputedStyle(e, 32);
/*     */     
/* 311 */     if (v.getPrimitiveType() == 21) {
/* 312 */       return null;
/*     */     }
/* 314 */     return Float.valueOf(v.getFloatValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Float convertWordSpacing(Element e) {
/* 323 */     Value v = CSSUtilities.getComputedStyle(e, 58);
/*     */     
/* 325 */     if (v.getPrimitiveType() == 21) {
/* 326 */       return null;
/*     */     }
/* 328 */     return Float.valueOf(v.getFloatValue());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/TextUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */