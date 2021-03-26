/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FieldUtils
/*     */ {
/*     */   static class KeyValue
/*     */   {
/*     */     private final String key;
/*     */     private final String value;
/*     */     
/*     */     KeyValue(String theKey, String theValue) {
/*  50 */       this.key = theKey;
/*  51 */       this.value = theValue;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getKey() {
/*  56 */       return this.key;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getValue() {
/*  61 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*  67 */       return "(" + this.key + ", " + this.value + ")";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class KeyValueKeyComparator
/*     */     implements Serializable, Comparator<KeyValue>
/*     */   {
/*     */     private static final long serialVersionUID = 6715364290007167694L;
/*     */ 
/*     */ 
/*     */     
/*     */     public int compare(FieldUtils.KeyValue o1, FieldUtils.KeyValue o2) {
/*  82 */       return o1.key.compareTo(o2.key);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class KeyValueValueComparator
/*     */     implements Serializable, Comparator<KeyValue>
/*     */   {
/*     */     private static final long serialVersionUID = -3984095679894798265L;
/*     */ 
/*     */ 
/*     */     
/*     */     public int compare(FieldUtils.KeyValue o1, FieldUtils.KeyValue o2) {
/*  97 */       return o1.value.compareTo(o2.value);
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
/*     */   static List<KeyValue> toKeyValueList(List<String> key, List<String> value) {
/* 117 */     List<KeyValue> list = new ArrayList<KeyValue>();
/* 118 */     for (int i = 0; i < key.size(); i++)
/*     */     {
/* 120 */       list.add(new KeyValue(key.get(i), value.get(i)));
/*     */     }
/* 122 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void sortByValue(List<KeyValue> pairs) {
/* 132 */     Collections.sort(pairs, new KeyValueValueComparator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void sortByKey(List<KeyValue> pairs) {
/* 142 */     Collections.sort(pairs, new KeyValueKeyComparator());
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
/*     */   static List<String> getPairableItems(COSBase items, int pairIdx) {
/* 164 */     if (pairIdx < 0 || pairIdx > 1)
/*     */     {
/* 166 */       throw new IllegalArgumentException("Only 0 and 1 are allowed as an index into two-element arrays");
/*     */     }
/*     */     
/* 169 */     if (items instanceof COSString) {
/*     */       
/* 171 */       List<String> array = new ArrayList<String>();
/* 172 */       array.add(((COSString)items).getString());
/* 173 */       return array;
/*     */     } 
/* 175 */     if (items instanceof COSArray) {
/*     */       
/* 177 */       List<String> entryList = new ArrayList<String>();
/* 178 */       for (COSBase entry : items) {
/*     */         
/* 180 */         if (entry instanceof COSString) {
/*     */           
/* 182 */           entryList.add(((COSString)entry).getString()); continue;
/*     */         } 
/* 184 */         if (entry instanceof COSArray) {
/*     */           
/* 186 */           COSArray cosArray = (COSArray)entry;
/* 187 */           if (cosArray.size() >= pairIdx + 1 && cosArray.get(pairIdx) instanceof COSString)
/*     */           {
/* 189 */             entryList.add(((COSString)cosArray.get(pairIdx)).getString());
/*     */           }
/*     */         } 
/*     */       } 
/* 193 */       return entryList;
/*     */     } 
/* 195 */     return Collections.emptyList();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/FieldUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */