/*    */ package org.apache.pdfbox.pdmodel.interactive.form;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
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
/*    */ public class PDPushButton
/*    */   extends PDButton
/*    */ {
/*    */   public PDPushButton(PDAcroForm acroForm) {
/* 40 */     super(acroForm);
/* 41 */     setPushButton(true);
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
/*    */   PDPushButton(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
/* 53 */     super(acroForm, field, parent);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> getExportValues() {
/* 59 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setExportValues(List<String> values) {
/* 65 */     if (values != null && !values.isEmpty())
/*    */     {
/* 67 */       throw new IllegalArgumentException("A PDPushButton shall not use the Opt entry in the field dictionary");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 74 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDefaultValue() {
/* 80 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getValueAsString() {
/* 86 */     return getValue();
/*    */   }
/*    */   
/*    */   void constructAppearances() throws IOException {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDPushButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */