/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.common.COSDictionaryMap;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDAppearanceEntry
/*     */   implements COSObjectable
/*     */ {
/*     */   private COSBase entry;
/*     */   
/*     */   private PDAppearanceEntry() {}
/*     */   
/*     */   public PDAppearanceEntry(COSBase entry) {
/*  49 */     this.entry = entry;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/*  55 */     return this.entry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSubDictionary() {
/*  63 */     return !(this.entry instanceof COSStream);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStream() {
/*  71 */     return this.entry instanceof COSStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAppearanceStream getAppearanceStream() {
/*  81 */     if (!isStream())
/*     */     {
/*  83 */       throw new IllegalStateException("This entry is not an appearance stream");
/*     */     }
/*  85 */     return new PDAppearanceStream((COSStream)this.entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<COSName, PDAppearanceStream> getSubDictionary() {
/*  95 */     if (!isSubDictionary())
/*     */     {
/*  97 */       throw new IllegalStateException("This entry is not an appearance subdictionary");
/*     */     }
/*     */     
/* 100 */     COSDictionary dict = (COSDictionary)this.entry;
/* 101 */     Map<COSName, PDAppearanceStream> map = new HashMap<COSName, PDAppearanceStream>();
/*     */     
/* 103 */     for (COSName name : dict.keySet()) {
/*     */       
/* 105 */       COSBase value = dict.getDictionaryObject(name);
/*     */ 
/*     */       
/* 108 */       if (value instanceof COSStream)
/*     */       {
/* 110 */         map.put(name, new PDAppearanceStream((COSStream)value));
/*     */       }
/*     */     } 
/*     */     
/* 114 */     return (Map<COSName, PDAppearanceStream>)new COSDictionaryMap(map, dict);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAppearanceEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */