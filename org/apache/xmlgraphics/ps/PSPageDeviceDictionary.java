/*     */ package org.apache.xmlgraphics.ps;
/*     */ 
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PSPageDeviceDictionary
/*     */   extends PSDictionary
/*     */ {
/*     */   private static final long serialVersionUID = 845943256485806509L;
/*     */   private boolean flushOnRetrieval;
/*     */   private PSDictionary unRetrievedContentDictionary;
/*     */   
/*     */   public Object put(Object key, Object value) {
/*  52 */     Object previousValue = super.put((K)key, (V)value);
/*  53 */     if (this.flushOnRetrieval && (
/*  54 */       previousValue == null || !previousValue.equals(value))) {
/*  55 */       this.unRetrievedContentDictionary.put((K)key, (V)value);
/*     */     }
/*     */     
/*  58 */     return previousValue;
/*     */   }
/*     */   
/*     */   public void putAll(Map m) {
/*  62 */     for (Object x : m.entrySet()) {
/*  63 */       Map.Entry e = (Map.Entry)x;
/*  64 */       put(e.getKey(), e.getValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/*  72 */     super.clear();
/*  73 */     if (this.unRetrievedContentDictionary != null) {
/*  74 */       this.unRetrievedContentDictionary.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  84 */     if (this.flushOnRetrieval) {
/*  85 */       return this.unRetrievedContentDictionary.isEmpty();
/*     */     }
/*  87 */     return super.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlushOnRetrieval(boolean flushOnRetrieval) {
/*  95 */     this.flushOnRetrieval = flushOnRetrieval;
/*  96 */     if (flushOnRetrieval) {
/*  97 */       this.unRetrievedContentDictionary = new PSDictionary();
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
/*     */   public String getContent() {
/*     */     String content;
/* 111 */     if (this.flushOnRetrieval) {
/* 112 */       content = this.unRetrievedContentDictionary.toString();
/* 113 */       this.unRetrievedContentDictionary.clear();
/*     */     } else {
/* 115 */       content = toString();
/*     */     } 
/* 117 */     return content;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/PSPageDeviceDictionary.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */