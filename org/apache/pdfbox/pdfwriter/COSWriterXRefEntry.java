/*     */ package org.apache.pdfbox.pdfwriter;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSObjectKey;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class COSWriterXRefEntry
/*     */   implements Comparable<COSWriterXRefEntry>
/*     */ {
/*     */   private long offset;
/*     */   private COSBase object;
/*     */   private COSObjectKey key;
/*     */   private boolean free = false;
/*  39 */   private static final COSWriterXRefEntry NULLENTRY = new COSWriterXRefEntry(0L, null, new COSObjectKey(0L, 65535)); static {
/*  40 */     NULLENTRY.setFree(true);
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
/*     */   public COSWriterXRefEntry(long start, COSBase obj, COSObjectKey keyValue) {
/*  52 */     setOffset(start);
/*  53 */     setObject(obj);
/*  54 */     setKey(keyValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(COSWriterXRefEntry obj) {
/*  63 */     if (obj != null) {
/*     */       
/*  65 */       if (getKey().getNumber() < obj.getKey().getNumber())
/*     */       {
/*  67 */         return -1;
/*     */       }
/*  69 */       if (getKey().getNumber() > obj.getKey().getNumber())
/*     */       {
/*  71 */         return 1;
/*     */       }
/*  73 */       return 0;
/*     */     } 
/*  75 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static COSWriterXRefEntry getNullEntry() {
/*  85 */     return NULLENTRY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSObjectKey getKey() {
/*  95 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getOffset() {
/* 105 */     return this.offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFree() {
/* 115 */     return this.free;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFree(boolean newFree) {
/* 125 */     this.free = newFree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setKey(COSObjectKey newKey) {
/* 135 */     this.key = newKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setOffset(long newOffset) {
/* 145 */     this.offset = newOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getObject() {
/* 155 */     return this.object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setObject(COSBase newObject) {
/* 165 */     this.object = newObject;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfwriter/COSWriterXRefEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */