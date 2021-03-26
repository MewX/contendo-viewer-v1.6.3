/*     */ package org.apache.pdfbox.pdmodel.graphics.color;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDDeviceNProcess
/*     */ {
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDDeviceNProcess() {
/*  42 */     this.dictionary = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDeviceNProcess(COSDictionary attributes) {
/*  51 */     this.dictionary = attributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSDictionary() {
/*  60 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getColorSpace() throws IOException {
/*  70 */     COSBase cosColorSpace = this.dictionary.getDictionaryObject(COSName.COLORSPACE);
/*  71 */     if (cosColorSpace == null)
/*     */     {
/*  73 */       return null;
/*     */     }
/*  75 */     return PDColorSpace.create(cosColorSpace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getComponents() {
/*  84 */     List<String> components = new ArrayList<String>();
/*  85 */     COSArray cosComponents = (COSArray)this.dictionary.getDictionaryObject(COSName.COMPONENTS);
/*  86 */     if (cosComponents == null)
/*     */     {
/*  88 */       return components;
/*     */     }
/*  90 */     for (COSBase name : cosComponents)
/*     */     {
/*  92 */       components.add(((COSName)name).getName());
/*     */     }
/*  94 */     return components;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     StringBuilder sb = new StringBuilder("Process{");
/*     */     
/*     */     try {
/* 103 */       sb.append(getColorSpace());
/* 104 */       for (String component : getComponents())
/*     */       {
/* 106 */         sb.append(" \"");
/* 107 */         sb.append(component);
/* 108 */         sb.append('"');
/*     */       }
/*     */     
/* 111 */     } catch (IOException e) {
/*     */       
/* 113 */       sb.append("ERROR");
/*     */     } 
/* 115 */     sb.append('}');
/* 116 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDDeviceNProcess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */