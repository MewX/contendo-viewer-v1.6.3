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
/*    */ public class FeatureList
/*    */ {
/*    */   private int featureCount;
/*    */   private FeatureRecord[] featureRecords;
/*    */   private Feature[] features;
/*    */   
/*    */   public FeatureList(RandomAccessFile raf, int offset) throws IOException {
/* 37 */     raf.seek(offset);
/* 38 */     this.featureCount = raf.readUnsignedShort();
/* 39 */     this.featureRecords = new FeatureRecord[this.featureCount];
/* 40 */     this.features = new Feature[this.featureCount]; int i;
/* 41 */     for (i = 0; i < this.featureCount; i++) {
/* 42 */       this.featureRecords[i] = new FeatureRecord(raf);
/*    */     }
/* 44 */     for (i = 0; i < this.featureCount; i++) {
/* 45 */       this.features[i] = new Feature(raf, offset + this.featureRecords[i].getOffset());
/*    */     }
/*    */   }
/*    */   
/*    */   public Feature findFeature(LangSys langSys, String tag) {
/* 50 */     if (tag.length() != 4) {
/* 51 */       return null;
/*    */     }
/* 53 */     int tagVal = tag.charAt(0) << 24 | tag.charAt(1) << 16 | tag.charAt(2) << 8 | tag.charAt(3);
/*    */ 
/*    */ 
/*    */     
/* 57 */     for (int i = 0; i < this.featureCount; i++) {
/* 58 */       if (this.featureRecords[i].getTag() == tagVal && 
/* 59 */         langSys.isFeatureIndexed(i)) {
/* 60 */         return this.features[i];
/*    */       }
/*    */     } 
/*    */     
/* 64 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/FeatureList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */