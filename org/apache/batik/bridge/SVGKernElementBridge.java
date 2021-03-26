/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.batik.gvt.font.Kern;
/*     */ import org.apache.batik.gvt.font.UnicodeRange;
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
/*     */ 
/*     */ public abstract class SVGKernElementBridge
/*     */   extends AbstractSVGBridge
/*     */ {
/*     */   public Kern createKern(BridgeContext ctx, Element kernElement, SVGGVTFont font) {
/*     */     int[] firstGlyphs, secondGlyphs;
/*  52 */     String u1 = kernElement.getAttributeNS(null, "u1");
/*  53 */     String u2 = kernElement.getAttributeNS(null, "u2");
/*  54 */     String g1 = kernElement.getAttributeNS(null, "g1");
/*  55 */     String g2 = kernElement.getAttributeNS(null, "g2");
/*  56 */     String k = kernElement.getAttributeNS(null, "k");
/*  57 */     if (k.length() == 0) {
/*  58 */       k = "0";
/*     */     }
/*     */ 
/*     */     
/*  62 */     float kernValue = Float.parseFloat(k);
/*     */ 
/*     */     
/*  65 */     int firstGlyphLen = 0, secondGlyphLen = 0;
/*  66 */     int[] firstGlyphSet = null;
/*  67 */     int[] secondGlyphSet = null;
/*  68 */     List<UnicodeRange> firstUnicodeRanges = new ArrayList();
/*  69 */     List<UnicodeRange> secondUnicodeRanges = new ArrayList();
/*     */ 
/*     */     
/*  72 */     StringTokenizer st = new StringTokenizer(u1, ",");
/*  73 */     while (st.hasMoreTokens()) {
/*  74 */       String token = st.nextToken();
/*  75 */       if (token.startsWith("U+")) {
/*  76 */         firstUnicodeRanges.add(new UnicodeRange(token)); continue;
/*     */       } 
/*  78 */       int[] glyphCodes = font.getGlyphCodesForUnicode(token);
/*  79 */       if (firstGlyphSet == null) {
/*  80 */         firstGlyphSet = glyphCodes;
/*  81 */         firstGlyphLen = glyphCodes.length; continue;
/*     */       } 
/*  83 */       if (firstGlyphLen + glyphCodes.length > firstGlyphSet.length) {
/*     */         
/*  85 */         int sz = firstGlyphSet.length * 2;
/*  86 */         if (sz < firstGlyphLen + glyphCodes.length)
/*  87 */           sz = firstGlyphLen + glyphCodes.length; 
/*  88 */         int[] tmp = new int[sz];
/*  89 */         System.arraycopy(firstGlyphSet, 0, tmp, 0, firstGlyphLen);
/*  90 */         firstGlyphSet = tmp;
/*     */       } 
/*  92 */       for (int glyphCode : glyphCodes) firstGlyphSet[firstGlyphLen++] = glyphCode;
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  98 */     st = new StringTokenizer(u2, ",");
/*  99 */     while (st.hasMoreTokens()) {
/* 100 */       String token = st.nextToken();
/* 101 */       if (token.startsWith("U+")) {
/* 102 */         secondUnicodeRanges.add(new UnicodeRange(token)); continue;
/*     */       } 
/* 104 */       int[] glyphCodes = font.getGlyphCodesForUnicode(token);
/* 105 */       if (secondGlyphSet == null) {
/* 106 */         secondGlyphSet = glyphCodes;
/* 107 */         secondGlyphLen = glyphCodes.length; continue;
/*     */       } 
/* 109 */       if (secondGlyphLen + glyphCodes.length > secondGlyphSet.length) {
/*     */         
/* 111 */         int sz = secondGlyphSet.length * 2;
/* 112 */         if (sz < secondGlyphLen + glyphCodes.length)
/* 113 */           sz = secondGlyphLen + glyphCodes.length; 
/* 114 */         int[] tmp = new int[sz];
/* 115 */         System.arraycopy(secondGlyphSet, 0, tmp, 0, secondGlyphLen);
/* 116 */         secondGlyphSet = tmp;
/*     */       } 
/* 118 */       for (int glyphCode : glyphCodes) secondGlyphSet[secondGlyphLen++] = glyphCode;
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 124 */     st = new StringTokenizer(g1, ",");
/* 125 */     while (st.hasMoreTokens()) {
/* 126 */       String token = st.nextToken();
/* 127 */       int[] glyphCodes = font.getGlyphCodesForName(token);
/* 128 */       if (firstGlyphSet == null) {
/* 129 */         firstGlyphSet = glyphCodes;
/* 130 */         firstGlyphLen = glyphCodes.length; continue;
/*     */       } 
/* 132 */       if (firstGlyphLen + glyphCodes.length > firstGlyphSet.length) {
/*     */         
/* 134 */         int sz = firstGlyphSet.length * 2;
/* 135 */         if (sz < firstGlyphLen + glyphCodes.length)
/* 136 */           sz = firstGlyphLen + glyphCodes.length; 
/* 137 */         int[] tmp = new int[sz];
/* 138 */         System.arraycopy(firstGlyphSet, 0, tmp, 0, firstGlyphLen);
/* 139 */         firstGlyphSet = tmp;
/*     */       } 
/* 141 */       for (int glyphCode : glyphCodes) firstGlyphSet[firstGlyphLen++] = glyphCode;
/*     */     
/*     */     } 
/*     */ 
/*     */     
/* 146 */     st = new StringTokenizer(g2, ",");
/* 147 */     while (st.hasMoreTokens()) {
/* 148 */       String token = st.nextToken();
/* 149 */       int[] glyphCodes = font.getGlyphCodesForName(token);
/* 150 */       if (secondGlyphSet == null) {
/* 151 */         secondGlyphSet = glyphCodes;
/* 152 */         secondGlyphLen = glyphCodes.length; continue;
/*     */       } 
/* 154 */       if (secondGlyphLen + glyphCodes.length > secondGlyphSet.length) {
/*     */         
/* 156 */         int sz = secondGlyphSet.length * 2;
/* 157 */         if (sz < secondGlyphLen + glyphCodes.length)
/* 158 */           sz = secondGlyphLen + glyphCodes.length; 
/* 159 */         int[] tmp = new int[sz];
/* 160 */         System.arraycopy(secondGlyphSet, 0, tmp, 0, secondGlyphLen);
/* 161 */         secondGlyphSet = tmp;
/*     */       } 
/* 163 */       for (int glyphCode : glyphCodes) secondGlyphSet[secondGlyphLen++] = glyphCode;
/*     */     
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 169 */     if (firstGlyphLen == 0 || firstGlyphLen == firstGlyphSet.length) {
/*     */       
/* 171 */       firstGlyphs = firstGlyphSet;
/*     */     } else {
/* 173 */       firstGlyphs = new int[firstGlyphLen];
/* 174 */       System.arraycopy(firstGlyphSet, 0, firstGlyphs, 0, firstGlyphLen);
/*     */     } 
/*     */     
/* 177 */     if (secondGlyphLen == 0 || secondGlyphLen == secondGlyphSet.length) {
/*     */       
/* 179 */       secondGlyphs = secondGlyphSet;
/*     */     } else {
/* 181 */       secondGlyphs = new int[secondGlyphLen];
/* 182 */       System.arraycopy(secondGlyphSet, 0, secondGlyphs, 0, secondGlyphLen);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 187 */     UnicodeRange[] firstRanges = new UnicodeRange[firstUnicodeRanges.size()];
/* 188 */     firstUnicodeRanges.toArray(firstRanges);
/*     */ 
/*     */     
/* 191 */     UnicodeRange[] secondRanges = new UnicodeRange[secondUnicodeRanges.size()];
/* 192 */     secondUnicodeRanges.toArray(secondRanges);
/*     */ 
/*     */     
/* 195 */     return new Kern(firstGlyphs, secondGlyphs, firstRanges, secondRanges, kernValue);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGKernElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */