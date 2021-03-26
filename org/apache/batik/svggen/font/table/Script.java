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
/*    */ public class Script
/*    */ {
/*    */   private int defaultLangSysOffset;
/*    */   private int langSysCount;
/*    */   private LangSysRecord[] langSysRecords;
/*    */   private LangSys defaultLangSys;
/*    */   private LangSys[] langSys;
/*    */   
/*    */   protected Script(RandomAccessFile raf, int offset) throws IOException {
/* 39 */     raf.seek(offset);
/* 40 */     this.defaultLangSysOffset = raf.readUnsignedShort();
/* 41 */     this.langSysCount = raf.readUnsignedShort();
/* 42 */     if (this.langSysCount > 0) {
/* 43 */       this.langSysRecords = new LangSysRecord[this.langSysCount];
/* 44 */       for (int i = 0; i < this.langSysCount; i++) {
/* 45 */         this.langSysRecords[i] = new LangSysRecord(raf);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 50 */     if (this.langSysCount > 0) {
/* 51 */       this.langSys = new LangSys[this.langSysCount];
/* 52 */       for (int i = 0; i < this.langSysCount; i++) {
/* 53 */         raf.seek((offset + this.langSysRecords[i].getOffset()));
/* 54 */         this.langSys[i] = new LangSys(raf);
/*    */       } 
/*    */     } 
/* 57 */     if (this.defaultLangSysOffset > 0) {
/* 58 */       raf.seek((offset + this.defaultLangSysOffset));
/* 59 */       this.defaultLangSys = new LangSys(raf);
/*    */     } 
/*    */   }
/*    */   
/*    */   public LangSys getDefaultLangSys() {
/* 64 */     return this.defaultLangSys;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/Script.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */