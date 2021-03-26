/*    */ package org.apache.pdfbox.pdmodel.interactive.action;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSBoolean;
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
/*    */ public class PDActionHide
/*    */   extends PDAction
/*    */ {
/*    */   public static final String SUB_TYPE = "Hide";
/*    */   
/*    */   public PDActionHide() {
/* 42 */     setSubType("Hide");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDActionHide(COSDictionary a) {
/* 52 */     super(a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getT() {
/* 63 */     return this.action.getDictionaryObject(COSName.T);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setT(COSBase t) {
/* 71 */     this.action.setItem(COSName.T, t);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getH() {
/* 81 */     return this.action.getBoolean(COSName.H, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setH(boolean h) {
/* 89 */     this.action.setItem(COSName.H, (COSBase)COSBoolean.getBoolean(h));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionHide.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */