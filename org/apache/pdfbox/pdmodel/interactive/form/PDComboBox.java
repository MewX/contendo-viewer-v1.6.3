/*    */ package org.apache.pdfbox.pdmodel.interactive.form;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
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
/*    */ public final class PDComboBox
/*    */   extends PDChoice
/*    */ {
/*    */   private static final int FLAG_EDIT = 262144;
/*    */   
/*    */   public PDComboBox(PDAcroForm acroForm) {
/* 42 */     super(acroForm);
/* 43 */     setCombo(true);
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
/*    */   PDComboBox(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/* 55 */     super(acroForm, field, parent);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isEdit() {
/* 65 */     return getCOSObject().getFlag(COSName.FF, 262144);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEdit(boolean edit) {
/* 75 */     getCOSObject().setFlag(COSName.FF, 262144, edit);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void constructAppearances() throws IOException {
/* 82 */     AppearanceGeneratorHelper apHelper = new AppearanceGeneratorHelper(this);
/* 83 */     List<String> values = getValue();
/*    */     
/* 85 */     if (!values.isEmpty()) {
/*    */       
/* 87 */       apHelper.setAppearanceValue(values.get(0));
/*    */     }
/*    */     else {
/*    */       
/* 91 */       apHelper.setAppearanceValue("");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDComboBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */