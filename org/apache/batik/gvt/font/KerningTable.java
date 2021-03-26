/*    */ package org.apache.batik.gvt.font;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KerningTable
/*    */ {
/*    */   private Kern[] entries;
/*    */   
/*    */   public KerningTable(Kern[] entries) {
/* 41 */     this.entries = entries;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getKerningValue(int glyphCode1, int glyphCode2, String glyphUnicode1, String glyphUnicode2) {
/* 60 */     for (Kern entry : this.entries) {
/* 61 */       if (entry.matchesFirstGlyph(glyphCode1, glyphUnicode1) && entry.matchesSecondGlyph(glyphCode2, glyphUnicode2))
/*    */       {
/* 63 */         return entry.getAdjustValue();
/*    */       }
/*    */     } 
/* 66 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/KerningTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */