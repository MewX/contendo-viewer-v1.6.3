/*    */ package org.apache.batik.svggen.font.table;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LookupList
/*    */ {
/*    */   private int lookupCount;
/*    */   private int[] lookupOffsets;
/*    */   private Lookup[] lookups;
/*    */   
/*    */   public LookupList(RandomAccessFile raf, int offset, LookupSubtableFactory factory) throws IOException {
/* 38 */     raf.seek(offset);
/* 39 */     this.lookupCount = raf.readUnsignedShort();
/* 40 */     this.lookupOffsets = new int[this.lookupCount];
/* 41 */     this.lookups = new Lookup[this.lookupCount]; int i;
/* 42 */     for (i = 0; i < this.lookupCount; i++) {
/* 43 */       this.lookupOffsets[i] = raf.readUnsignedShort();
/*    */     }
/* 45 */     for (i = 0; i < this.lookupCount; i++) {
/* 46 */       this.lookups[i] = new Lookup(factory, raf, offset + this.lookupOffsets[i]);
/*    */     }
/*    */   }
/*    */   
/*    */   public Lookup getLookup(Feature feature, int index) {
/* 51 */     if (feature.getLookupCount() > index) {
/* 52 */       int i = feature.getLookupListIndex(index);
/* 53 */       return this.lookups[i];
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/LookupList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */