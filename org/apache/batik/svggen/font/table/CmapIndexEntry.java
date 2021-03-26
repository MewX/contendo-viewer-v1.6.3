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
/*    */ public class CmapIndexEntry
/*    */ {
/*    */   private int platformId;
/*    */   private int encodingId;
/*    */   private int offset;
/*    */   
/*    */   protected CmapIndexEntry(RandomAccessFile raf) throws IOException {
/* 35 */     this.platformId = raf.readUnsignedShort();
/* 36 */     this.encodingId = raf.readUnsignedShort();
/* 37 */     this.offset = raf.readInt();
/*    */   }
/*    */   
/*    */   public int getEncodingId() {
/* 41 */     return this.encodingId;
/*    */   }
/*    */   
/*    */   public int getOffset() {
/* 45 */     return this.offset;
/*    */   }
/*    */   
/*    */   public int getPlatformId() {
/* 49 */     return this.platformId;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     String platform, encoding = "";
/*    */     
/* 56 */     switch (this.platformId) { case 1:
/* 57 */         platform = " (Macintosh)"; break;
/* 58 */       case 3: platform = " (Windows)"; break;
/* 59 */       default: platform = ""; break; }
/*    */     
/* 61 */     if (this.platformId == 3)
/*    */     
/* 63 */     { switch (this.encodingId) { case 0:
/* 64 */           encoding = " (Symbol)";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 74 */           return "platform id: " + this.platformId + platform + ", encoding id: " + this.encodingId + encoding + ", offset: " + this.offset;case 1: encoding = " (Unicode)"; return "platform id: " + this.platformId + platform + ", encoding id: " + this.encodingId + encoding + ", offset: " + this.offset;case 2: encoding = " (ShiftJIS)"; return "platform id: " + this.platformId + platform + ", encoding id: " + this.encodingId + encoding + ", offset: " + this.offset;case 3: encoding = " (Big5)"; return "platform id: " + this.platformId + platform + ", encoding id: " + this.encodingId + encoding + ", offset: " + this.offset;case 4: encoding = " (PRC)"; return "platform id: " + this.platformId + platform + ", encoding id: " + this.encodingId + encoding + ", offset: " + this.offset;case 5: encoding = " (Wansung)"; return "platform id: " + this.platformId + platform + ", encoding id: " + this.encodingId + encoding + ", offset: " + this.offset;case 6: encoding = " (Johab)"; return "platform id: " + this.platformId + platform + ", encoding id: " + this.encodingId + encoding + ", offset: " + this.offset; }  encoding = ""; }  return "platform id: " + this.platformId + platform + ", encoding id: " + this.encodingId + encoding + ", offset: " + this.offset;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/CmapIndexEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */