/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSDictionaryMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDDeviceNAttributes
/*     */ {
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDDeviceNAttributes() {
/*  44 */     this.dictionary = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDeviceNAttributes(COSDictionary attributes) {
/*  53 */     this.dictionary = attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSDictionary() {
/*  62 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, PDSeparation> getColorants() throws IOException {
/*  72 */     Map<String, PDSeparation> actuals = new HashMap<String, PDSeparation>();
/*  73 */     COSDictionary colorants = (COSDictionary)this.dictionary.getDictionaryObject(COSName.COLORANTS);
/*  74 */     if (colorants == null) {
/*     */       
/*  76 */       colorants = new COSDictionary();
/*  77 */       this.dictionary.setItem(COSName.COLORANTS, (COSBase)colorants);
/*     */     } 
/*  79 */     for (COSName name : colorants.keySet()) {
/*     */       
/*  81 */       COSBase value = colorants.getDictionaryObject(name);
/*  82 */       actuals.put(name.getName(), (PDSeparation)PDColorSpace.create(value));
/*     */     } 
/*  84 */     return (Map<String, PDSeparation>)new COSDictionaryMap(actuals, colorants);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDeviceNProcess getProcess() {
/*  93 */     COSDictionary process = (COSDictionary)this.dictionary.getDictionaryObject(COSName.PROCESS);
/*  94 */     if (process == null)
/*     */     {
/*  96 */       return null;
/*     */     }
/*  98 */     return new PDDeviceNProcess(process);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNChannel() {
/* 107 */     return "NChannel".equals(this.dictionary.getNameAsString(COSName.SUBTYPE));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorants(Map<String, PDColorSpace> colorants) {
/* 116 */     COSDictionary colorantDict = null;
/* 117 */     if (colorants != null)
/*     */     {
/* 119 */       colorantDict = COSDictionaryMap.convert(colorants);
/*     */     }
/* 121 */     this.dictionary.setItem(COSName.COLORANTS, (COSBase)colorantDict);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 127 */     StringBuilder sb = new StringBuilder(this.dictionary.getNameAsString(COSName.SUBTYPE));
/* 128 */     sb.append('{');
/* 129 */     PDDeviceNProcess process = getProcess();
/* 130 */     if (process != null) {
/*     */       
/* 132 */       sb.append(getProcess());
/* 133 */       sb.append(' ');
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 139 */       Map<String, PDSeparation> colorants = getColorants();
/* 140 */       sb.append("Colorants{");
/* 141 */       for (Map.Entry<String, PDSeparation> col : colorants.entrySet()) {
/*     */         
/* 143 */         sb.append('"');
/* 144 */         sb.append(col.getKey());
/* 145 */         sb.append("\": ");
/* 146 */         sb.append(col.getValue());
/* 147 */         sb.append(' ');
/*     */       } 
/* 149 */       sb.append('}');
/*     */     }
/* 151 */     catch (IOException e) {
/*     */       
/* 153 */       sb.append("ERROR");
/*     */     } 
/* 155 */     sb.append('}');
/* 156 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDDeviceNAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */