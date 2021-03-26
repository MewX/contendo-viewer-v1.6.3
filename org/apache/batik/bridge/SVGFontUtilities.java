/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.css.engine.CSSEngine;
/*     */ import org.apache.batik.css.engine.FontFaceRule;
/*     */ import org.apache.batik.gvt.font.GVTFontFace;
/*     */ import org.apache.batik.gvt.font.GVTFontFamily;
/*     */ import org.apache.batik.gvt.font.UnresolvedFontFamily;
/*     */ import org.apache.batik.util.SVGConstants;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGFontUtilities
/*     */   implements SVGConstants
/*     */ {
/*     */   public static List getFontFaces(Document doc, BridgeContext ctx) {
/*  53 */     Map fontFamilyMap = ctx.getFontFamilyMap();
/*  54 */     List<SVGFontFace> ret = (List)fontFamilyMap.get(doc);
/*  55 */     if (ret != null) {
/*  56 */       return ret;
/*     */     }
/*  58 */     ret = new LinkedList();
/*     */     
/*  60 */     NodeList fontFaceElements = doc.getElementsByTagNameNS("http://www.w3.org/2000/svg", "font-face");
/*     */ 
/*     */ 
/*     */     
/*  64 */     SVGFontFaceElementBridge fontFaceBridge = (SVGFontFaceElementBridge)ctx.getBridge("http://www.w3.org/2000/svg", "font-face");
/*     */ 
/*     */     
/*  67 */     for (int i = 0; i < fontFaceElements.getLength(); i++) {
/*  68 */       Element fontFaceElement = (Element)fontFaceElements.item(i);
/*  69 */       ret.add(fontFaceBridge.createFontFace(ctx, fontFaceElement));
/*     */     } 
/*     */ 
/*     */     
/*  73 */     CSSEngine engine = ((SVGOMDocument)doc).getCSSEngine();
/*  74 */     List sms = engine.getFontFaces();
/*  75 */     for (Object sm : sms) {
/*  76 */       FontFaceRule ffr = (FontFaceRule)sm;
/*  77 */       ret.add(CSSFontFace.createCSSFontFace(engine, ffr));
/*     */     } 
/*  79 */     return ret;
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
/*     */   public static GVTFontFamily getFontFamily(Element textElement, BridgeContext ctx, String fontFamilyName, String fontWeight, String fontStyle) {
/* 109 */     String fontKeyName = fontFamilyName.toLowerCase() + " " + fontWeight + " " + fontStyle;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     Map<Document, List> fontFamilyMap = ctx.getFontFamilyMap();
/* 115 */     GVTFontFamily fontFamily = (GVTFontFamily)fontFamilyMap.get(fontKeyName);
/*     */     
/* 117 */     if (fontFamily != null) {
/* 118 */       return fontFamily;
/*     */     }
/*     */ 
/*     */     
/* 122 */     Document doc = textElement.getOwnerDocument();
/*     */     
/* 124 */     List fontFaces = (List)fontFamilyMap.get(doc);
/*     */     
/* 126 */     if (fontFaces == null) {
/* 127 */       fontFaces = getFontFaces(doc, ctx);
/* 128 */       fontFamilyMap.put(doc, fontFaces);
/*     */     } 
/*     */ 
/*     */     
/* 132 */     Iterator<FontFace> iter = fontFaces.iterator();
/* 133 */     List<GVTFontFamily> svgFontFamilies = new LinkedList();
/* 134 */     while (iter.hasNext()) {
/* 135 */       FontFace fontFace = iter.next();
/*     */       
/* 137 */       if (!fontFace.hasFamilyName(fontFamilyName)) {
/*     */         continue;
/*     */       }
/*     */       
/* 141 */       String fontFaceStyle = fontFace.getFontStyle();
/* 142 */       if (fontFaceStyle.equals("all") || fontFaceStyle.indexOf(fontStyle) != -1) {
/*     */         
/* 144 */         GVTFontFamily ffam = fontFace.getFontFamily(ctx);
/* 145 */         if (ffam != null) {
/* 146 */           svgFontFamilies.add(ffam);
/*     */         }
/*     */       } 
/*     */     } 
/* 150 */     if (svgFontFamilies.size() == 1) {
/*     */       
/* 152 */       fontFamilyMap.put(fontKeyName, (List)svgFontFamilies.get(0));
/* 153 */       return svgFontFamilies.get(0);
/*     */     } 
/* 155 */     if (svgFontFamilies.size() > 1) {
/*     */       
/* 157 */       String fontWeightNumber = getFontWeightNumberString(fontWeight);
/*     */ 
/*     */       
/* 160 */       List<String> fontFamilyWeights = new ArrayList(svgFontFamilies.size());
/* 161 */       for (GVTFontFamily svgFontFamily : svgFontFamilies) {
/*     */         
/* 163 */         GVTFontFace fontFace = ((GVTFontFamily)svgFontFamily).getFontFace();
/* 164 */         String fontFaceWeight = fontFace.getFontWeight();
/* 165 */         fontFaceWeight = getFontWeightNumberString(fontFaceWeight);
/* 166 */         fontFamilyWeights.add(fontFaceWeight);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 173 */       List<String> newFontFamilyWeights = new ArrayList<String>(fontFamilyWeights); int i;
/* 174 */       for (i = 100; i <= 900; i += 100) {
/* 175 */         String weightString = String.valueOf(i);
/* 176 */         boolean matched = false;
/* 177 */         int minDifference = 1000;
/* 178 */         int minDifferenceIndex = 0;
/* 179 */         for (int j = 0; j < fontFamilyWeights.size(); j++) {
/* 180 */           String fontFamilyWeight = fontFamilyWeights.get(j);
/* 181 */           if (fontFamilyWeight.indexOf(weightString) > -1) {
/* 182 */             matched = true;
/*     */             break;
/*     */           } 
/* 185 */           StringTokenizer st = new StringTokenizer(fontFamilyWeight, " ,");
/*     */           
/* 187 */           while (st.hasMoreTokens()) {
/* 188 */             int weightNum = Integer.parseInt(st.nextToken());
/* 189 */             int difference = Math.abs(weightNum - i);
/* 190 */             if (difference < minDifference) {
/* 191 */               minDifference = difference;
/* 192 */               minDifferenceIndex = j;
/*     */             } 
/*     */           } 
/*     */         } 
/* 196 */         if (!matched) {
/* 197 */           String newFontFamilyWeight = (new StringBuilder()).append(newFontFamilyWeights.get(minDifferenceIndex)).append(", ").append(weightString).toString();
/*     */ 
/*     */           
/* 200 */           newFontFamilyWeights.set(minDifferenceIndex, newFontFamilyWeight);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 207 */       for (i = 0; i < svgFontFamilies.size(); i++) {
/* 208 */         String fontFaceWeight = newFontFamilyWeights.get(i);
/* 209 */         if (fontFaceWeight.indexOf(fontWeightNumber) > -1) {
/* 210 */           fontFamilyMap.put(fontKeyName, (List)svgFontFamilies.get(i));
/* 211 */           return svgFontFamilies.get(i);
/*     */         } 
/*     */       } 
/*     */       
/* 215 */       fontFamilyMap.put(fontKeyName, (List)svgFontFamilies.get(0));
/* 216 */       return svgFontFamilies.get(0);
/*     */     } 
/*     */ 
/*     */     
/* 220 */     UnresolvedFontFamily unresolvedFontFamily = new UnresolvedFontFamily(fontFamilyName);
/*     */     
/* 222 */     fontFamilyMap.put(fontKeyName, unresolvedFontFamily);
/* 223 */     return (GVTFontFamily)unresolvedFontFamily;
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
/*     */   protected static String getFontWeightNumberString(String fontWeight) {
/* 237 */     if (fontWeight.equals("normal"))
/* 238 */       return "400"; 
/* 239 */     if (fontWeight.equals("bold"))
/* 240 */       return "700"; 
/* 241 */     if (fontWeight.equals("all")) {
/* 242 */       return "100, 200, 300, 400, 500, 600, 700, 800, 900";
/*     */     }
/* 244 */     return fontWeight;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGFontUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */