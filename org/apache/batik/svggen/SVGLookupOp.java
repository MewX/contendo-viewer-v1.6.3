/*     */ package org.apache.batik.svggen;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.ByteLookupTable;
/*     */ import java.awt.image.LookupOp;
/*     */ import java.awt.image.LookupTable;
/*     */ import java.util.Arrays;
/*     */ import org.w3c.dom.Document;
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
/*     */ public class SVGLookupOp
/*     */   extends AbstractSVGFilterConverter
/*     */ {
/*     */   private static final double GAMMA = 0.4166666666666667D;
/*  53 */   private static final int[] linearToSRGBLut = new int[256];
/*  54 */   private static final int[] sRGBToLinear = new int[256];
/*     */   
/*     */   static {
/*  57 */     for (int i = 0; i < 256; i++) {
/*     */       
/*  59 */       float value = i / 255.0F;
/*  60 */       if (value <= 0.0031308D) {
/*  61 */         value *= 12.92F;
/*     */       } else {
/*  63 */         value = 1.055F * (float)Math.pow(value, 0.4166666666666667D) - 0.055F;
/*     */       } 
/*  65 */       linearToSRGBLut[i] = Math.round(value * 255.0F);
/*     */ 
/*     */       
/*  68 */       value = i / 255.0F;
/*  69 */       if (value <= 0.04045D) {
/*  70 */         value /= 12.92F;
/*     */       } else {
/*  72 */         value = (float)Math.pow(((value + 0.055F) / 1.055F), 2.4D);
/*     */       } 
/*     */       
/*  75 */       sRGBToLinear[i] = Math.round(value * 255.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLookupOp(SVGGeneratorContext generatorContext) {
/*  83 */     super(generatorContext);
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
/*     */   public SVGFilterDescriptor toSVG(BufferedImageOp filter, Rectangle filterRect) {
/* 100 */     if (filter instanceof LookupOp) {
/* 101 */       return toSVG((LookupOp)filter);
/*     */     }
/* 103 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGFilterDescriptor toSVG(LookupOp lookupOp) {
/* 114 */     SVGFilterDescriptor filterDesc = (SVGFilterDescriptor)this.descMap.get(lookupOp);
/*     */ 
/*     */     
/* 117 */     Document domFactory = this.generatorContext.domFactory;
/*     */     
/* 119 */     if (filterDesc == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 124 */       Element filterDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "filter");
/*     */       
/* 126 */       Element feComponentTransferDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "feComponentTransfer");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 140 */       String[] lookupTables = convertLookupTables(lookupOp);
/*     */       
/* 142 */       Element feFuncR = domFactory.createElementNS("http://www.w3.org/2000/svg", "feFuncR");
/*     */       
/* 144 */       Element feFuncG = domFactory.createElementNS("http://www.w3.org/2000/svg", "feFuncG");
/*     */       
/* 146 */       Element feFuncB = domFactory.createElementNS("http://www.w3.org/2000/svg", "feFuncB");
/*     */       
/* 148 */       Element feFuncA = null;
/* 149 */       String type = "table";
/*     */       
/* 151 */       if (lookupTables.length == 1) {
/* 152 */         feFuncR.setAttributeNS((String)null, "type", type);
/* 153 */         feFuncG.setAttributeNS((String)null, "type", type);
/* 154 */         feFuncB.setAttributeNS((String)null, "type", type);
/* 155 */         feFuncR.setAttributeNS((String)null, "tableValues", lookupTables[0]);
/*     */         
/* 157 */         feFuncG.setAttributeNS((String)null, "tableValues", lookupTables[0]);
/*     */         
/* 159 */         feFuncB.setAttributeNS((String)null, "tableValues", lookupTables[0]);
/*     */       
/*     */       }
/* 162 */       else if (lookupTables.length >= 3) {
/* 163 */         feFuncR.setAttributeNS((String)null, "type", type);
/* 164 */         feFuncG.setAttributeNS((String)null, "type", type);
/* 165 */         feFuncB.setAttributeNS((String)null, "type", type);
/* 166 */         feFuncR.setAttributeNS((String)null, "tableValues", lookupTables[0]);
/*     */         
/* 168 */         feFuncG.setAttributeNS((String)null, "tableValues", lookupTables[1]);
/*     */         
/* 170 */         feFuncB.setAttributeNS((String)null, "tableValues", lookupTables[2]);
/*     */ 
/*     */         
/* 173 */         if (lookupTables.length == 4) {
/* 174 */           feFuncA = domFactory.createElementNS("http://www.w3.org/2000/svg", "feFuncA");
/*     */           
/* 176 */           feFuncA.setAttributeNS((String)null, "type", type);
/* 177 */           feFuncA.setAttributeNS((String)null, "tableValues", lookupTables[3]);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 182 */       feComponentTransferDef.appendChild(feFuncR);
/* 183 */       feComponentTransferDef.appendChild(feFuncG);
/* 184 */       feComponentTransferDef.appendChild(feFuncB);
/* 185 */       if (feFuncA != null) {
/* 186 */         feComponentTransferDef.appendChild(feFuncA);
/*     */       }
/* 188 */       filterDef.appendChild(feComponentTransferDef);
/*     */       
/* 190 */       filterDef.setAttributeNS((String)null, "id", this.generatorContext.idGenerator.generateID("componentTransfer"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 204 */       String filterAttrBuf = "url(#" + filterDef.getAttributeNS((String)null, "id") + ")";
/*     */       
/* 206 */       filterDesc = new SVGFilterDescriptor(filterAttrBuf, filterDef);
/*     */       
/* 208 */       this.defSet.add(filterDef);
/* 209 */       this.descMap.put(lookupOp, filterDesc);
/*     */     } 
/*     */     
/* 212 */     return filterDesc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] convertLookupTables(LookupOp lookupOp) {
/* 220 */     LookupTable lookupTable = lookupOp.getTable();
/* 221 */     int nComponents = lookupTable.getNumComponents();
/*     */     
/* 223 */     if (nComponents != 1 && nComponents != 3 && nComponents != 4) {
/* 224 */       throw new SVGGraphics2DRuntimeException("BufferedImage LookupOp should have 1, 3 or 4 lookup arrays");
/*     */     }
/* 226 */     StringBuffer[] lookupTableBuf = new StringBuffer[nComponents];
/* 227 */     for (int i = 0; i < nComponents; i++) {
/* 228 */       lookupTableBuf[i] = new StringBuffer();
/*     */     }
/* 230 */     if (!(lookupTable instanceof ByteLookupTable)) {
/* 231 */       int[] src = new int[nComponents];
/* 232 */       int[] dest = new int[nComponents];
/* 233 */       int offset = lookupTable.getOffset();
/*     */ 
/*     */       
/*     */       int k;
/*     */ 
/*     */       
/* 239 */       for (k = 0; k < offset; k++) {
/*     */         
/* 241 */         for (int m = 0; m < nComponents; m++)
/*     */         {
/* 243 */           lookupTableBuf[m].append(doubleString(k / 255.0D)).append(" ");
/*     */         }
/*     */       } 
/*     */       
/* 247 */       for (k = offset; k <= 255; k++) {
/*     */         
/* 249 */         Arrays.fill(src, k);
/*     */ 
/*     */         
/* 252 */         lookupTable.lookupPixel(src, dest);
/*     */ 
/*     */         
/* 255 */         for (int m = 0; m < nComponents; m++) {
/* 256 */           lookupTableBuf[m].append(doubleString(dest[m] / 255.0D)).append(" ");
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 261 */       byte[] src = new byte[nComponents];
/* 262 */       byte[] dest = new byte[nComponents];
/*     */       
/* 264 */       int offset = lookupTable.getOffset();
/*     */ 
/*     */       
/*     */       int k;
/*     */ 
/*     */       
/* 270 */       for (k = 0; k < offset; k++) {
/*     */         
/* 272 */         for (int m = 0; m < nComponents; m++)
/*     */         {
/* 274 */           lookupTableBuf[m].append(doubleString(k / 255.0D)).append(" ");
/*     */         }
/*     */       } 
/* 277 */       for (k = 0; k <= 255; k++) {
/*     */         
/* 279 */         Arrays.fill(src, (byte)(0xFF & k));
/*     */ 
/*     */         
/* 282 */         ((ByteLookupTable)lookupTable).lookupPixel(src, dest);
/*     */ 
/*     */         
/* 285 */         for (int m = 0; m < nComponents; m++) {
/* 286 */           lookupTableBuf[m].append(doubleString((0xFF & dest[m]) / 255.0D)).append(" ");
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 291 */     String[] lookupTables = new String[nComponents];
/* 292 */     for (int j = 0; j < nComponents; j++) {
/* 293 */       lookupTables[j] = lookupTableBuf[j].toString().trim();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     return lookupTables;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGLookupOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */