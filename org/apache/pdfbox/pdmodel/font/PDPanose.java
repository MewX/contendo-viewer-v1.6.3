/*    */ package org.apache.pdfbox.pdmodel.font;
/*    */ 
/*    */ import java.util.Arrays;
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
/*    */ public class PDPanose
/*    */ {
/*    */   private final byte[] bytes;
/*    */   
/*    */   public PDPanose(byte[] bytes) {
/* 34 */     this.bytes = bytes;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFamilyClass() {
/* 45 */     return this.bytes[0] << 8 | this.bytes[1];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDPanoseClassification getPanose() {
/* 55 */     byte[] panose = Arrays.copyOfRange(this.bytes, 2, 12);
/* 56 */     return new PDPanoseClassification(panose);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDPanose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */