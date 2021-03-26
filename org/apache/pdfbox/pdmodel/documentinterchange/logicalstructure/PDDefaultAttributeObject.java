/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDDefaultAttributeObject
/*     */   extends PDAttributeObject
/*     */ {
/*     */   public PDDefaultAttributeObject() {}
/*     */   
/*     */   public PDDefaultAttributeObject(COSDictionary dictionary) {
/*  50 */     super(dictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getAttributeNames() {
/*  61 */     List<String> attrNames = new ArrayList<String>();
/*  62 */     for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)getCOSObject().entrySet()) {
/*     */       
/*  64 */       COSName key = entry.getKey();
/*  65 */       if (!COSName.O.equals(key))
/*     */       {
/*  67 */         attrNames.add(key.getName());
/*     */       }
/*     */     } 
/*  70 */     return attrNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getAttributeValue(String attrName) {
/*  81 */     return getCOSObject().getDictionaryObject(attrName);
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
/*     */   protected COSBase getAttributeValue(String attrName, COSBase defaultValue) {
/*  93 */     COSBase value = getCOSObject().getDictionaryObject(attrName);
/*  94 */     if (value == null)
/*     */     {
/*  96 */       return defaultValue;
/*     */     }
/*  98 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttribute(String attrName, COSBase attrValue) {
/* 109 */     COSBase old = getAttributeValue(attrName);
/* 110 */     getCOSObject().setItem(COSName.getPDFName(attrName), attrValue);
/* 111 */     potentiallyNotifyChanged(old, attrValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 118 */     StringBuilder sb = (new StringBuilder()).append(super.toString()).append(", attributes={");
/* 119 */     Iterator<String> it = getAttributeNames().iterator();
/* 120 */     while (it.hasNext()) {
/*     */       
/* 122 */       String name = it.next();
/* 123 */       sb.append(name).append('=').append(getAttributeValue(name));
/* 124 */       if (it.hasNext())
/*     */       {
/* 126 */         sb.append(", ");
/*     */       }
/*     */     } 
/* 129 */     return sb.append('}').toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/logicalstructure/PDDefaultAttributeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */