/*    */ package org.apache.pdfbox.pdmodel.interactive.action;
/*    */ 
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
/*    */ public class PDActionNamed
/*    */   extends PDAction
/*    */ {
/*    */   public static final String SUB_TYPE = "Named";
/*    */   
/*    */   public PDActionNamed() {
/* 36 */     setSubType("Named");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDActionNamed(COSDictionary a) {
/* 46 */     super(a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getN() {
/* 56 */     return this.action.getNameAsString("N");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setN(String name) {
/* 66 */     this.action.setName("N", name);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionNamed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */