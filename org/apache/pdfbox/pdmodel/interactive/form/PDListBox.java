/*    */ package org.apache.pdfbox.pdmodel.interactive.form;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
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
/*    */ public final class PDListBox
/*    */   extends PDChoice
/*    */ {
/*    */   public PDListBox(PDAcroForm acroForm) {
/* 39 */     super(acroForm);
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
/*    */   PDListBox(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/* 51 */     super(acroForm, field, parent);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTopIndex() {
/* 61 */     return getCOSObject().getInt(COSName.TI, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setTopIndex(Integer topIndex) {
/* 71 */     if (topIndex != null) {
/*    */       
/* 73 */       getCOSObject().setInt(COSName.TI, topIndex.intValue());
/*    */     }
/*    */     else {
/*    */       
/* 77 */       getCOSObject().removeItem(COSName.TI);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void constructAppearances() throws IOException {
/* 85 */     AppearanceGeneratorHelper apHelper = new AppearanceGeneratorHelper(this);
/* 86 */     apHelper.setAppearanceValue("");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDListBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */