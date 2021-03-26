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
/*    */ public class ScriptList
/*    */ {
/* 31 */   private int scriptCount = 0;
/*    */   
/*    */   private ScriptRecord[] scriptRecords;
/*    */   private Script[] scripts;
/*    */   
/*    */   protected ScriptList(RandomAccessFile raf, int offset) throws IOException {
/* 37 */     raf.seek(offset);
/* 38 */     this.scriptCount = raf.readUnsignedShort();
/* 39 */     this.scriptRecords = new ScriptRecord[this.scriptCount];
/* 40 */     this.scripts = new Script[this.scriptCount]; int i;
/* 41 */     for (i = 0; i < this.scriptCount; i++) {
/* 42 */       this.scriptRecords[i] = new ScriptRecord(raf);
/*    */     }
/* 44 */     for (i = 0; i < this.scriptCount; i++) {
/* 45 */       this.scripts[i] = new Script(raf, offset + this.scriptRecords[i].getOffset());
/*    */     }
/*    */   }
/*    */   
/*    */   public int getScriptCount() {
/* 50 */     return this.scriptCount;
/*    */   }
/*    */   
/*    */   public ScriptRecord getScriptRecord(int i) {
/* 54 */     return this.scriptRecords[i];
/*    */   }
/*    */   
/*    */   public Script findScript(String tag) {
/* 58 */     if (tag.length() != 4) {
/* 59 */       return null;
/*    */     }
/* 61 */     int tagVal = tag.charAt(0) << 24 | tag.charAt(1) << 16 | tag.charAt(2) << 8 | tag.charAt(3);
/*    */ 
/*    */ 
/*    */     
/* 65 */     for (int i = 0; i < this.scriptCount; i++) {
/* 66 */       if (this.scriptRecords[i].getTag() == tagVal) {
/* 67 */         return this.scripts[i];
/*    */       }
/*    */     } 
/* 70 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/ScriptList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */