/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FDFJavaScript
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public FDFJavaScript() {
/*  45 */     this.dictionary = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFJavaScript(COSDictionary javaScript) {
/*  55 */     this.dictionary = javaScript;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  66 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBefore() {
/*  76 */     COSBase base = this.dictionary.getDictionaryObject(COSName.BEFORE);
/*  77 */     if (base instanceof COSString)
/*     */     {
/*  79 */       return ((COSString)base).getString();
/*     */     }
/*  81 */     if (base instanceof COSStream)
/*     */     {
/*  83 */       return ((COSStream)base).toTextString();
/*     */     }
/*     */ 
/*     */     
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBefore(String before) {
/*  98 */     this.dictionary.setItem(COSName.BEFORE, (COSBase)new COSString(before));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAfter() {
/* 108 */     COSBase base = this.dictionary.getDictionaryObject(COSName.AFTER);
/* 109 */     if (base instanceof COSString)
/*     */     {
/* 111 */       return ((COSString)base).getString();
/*     */     }
/* 113 */     if (base instanceof COSStream)
/*     */     {
/* 115 */       return ((COSStream)base).toTextString();
/*     */     }
/*     */ 
/*     */     
/* 119 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAfter(String after) {
/* 130 */     this.dictionary.setItem(COSName.AFTER, (COSBase)new COSString(after));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, PDActionJavaScript> getDoc() {
/* 141 */     Map<String, PDActionJavaScript> map = new LinkedHashMap<String, PDActionJavaScript>();
/* 142 */     COSArray array = (COSArray)this.dictionary.getDictionaryObject(COSName.DOC);
/* 143 */     if (array == null)
/*     */     {
/* 145 */       return null;
/*     */     }
/* 147 */     for (int i = 0; i < array.size(); i++)
/*     */     {
/* 149 */       PDActionFactory.createAction((COSDictionary)array.getObject(i));
/*     */     }
/* 151 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoc(Map<String, PDActionJavaScript> map) {
/* 161 */     COSArray array = new COSArray();
/* 162 */     for (Map.Entry<String, PDActionJavaScript> entry : map.entrySet()) {
/*     */       
/* 164 */       array.add((COSBase)new COSString(entry.getKey()));
/* 165 */       array.add((COSObjectable)entry.getValue());
/*     */     } 
/* 167 */     this.dictionary.setItem(COSName.DOC, (COSBase)array);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFJavaScript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */