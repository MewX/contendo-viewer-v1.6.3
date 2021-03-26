/*    */ package org.apache.fontbox.ttf;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ 
/*    */ public class IndexToLocationTable
/*    */   extends TTFTable
/*    */ {
/*    */   private static final short SHORT_OFFSETS = 0;
/*    */   private static final short LONG_OFFSETS = 1;
/*    */   public static final String TAG = "loca";
/*    */   private long[] offsets;
/*    */   
/*    */   IndexToLocationTable(TrueTypeFont font) {
/* 40 */     super(font);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/* 52 */     HeaderTable head = ttf.getHeader();
/* 53 */     int numGlyphs = ttf.getNumberOfGlyphs();
/* 54 */     this.offsets = new long[numGlyphs + 1];
/* 55 */     for (int i = 0; i < numGlyphs + 1; i++) {
/*    */       
/* 57 */       if (head.getIndexToLocFormat() == 0) {
/*    */         
/* 59 */         this.offsets[i] = (data.readUnsignedShort() * 2);
/*    */       }
/* 61 */       else if (head.getIndexToLocFormat() == 1) {
/*    */         
/* 63 */         this.offsets[i] = data.readUnsignedInt();
/*    */       }
/*    */       else {
/*    */         
/* 67 */         throw new IOException("Error:TTF.loca unknown offset format.");
/*    */       } 
/*    */     } 
/* 70 */     this.initialized = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long[] getOffsets() {
/* 77 */     return this.offsets;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setOffsets(long[] offsetsValue) {
/* 84 */     this.offsets = offsetsValue;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/IndexToLocationTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */