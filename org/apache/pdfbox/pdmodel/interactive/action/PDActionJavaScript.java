/*    */ package org.apache.pdfbox.pdmodel.interactive.action;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.cos.COSStream;
/*    */ import org.apache.pdfbox.cos.COSString;
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
/*    */ public class PDActionJavaScript
/*    */   extends PDAction
/*    */ {
/*    */   public static final String SUB_TYPE = "JavaScript";
/*    */   
/*    */   public PDActionJavaScript() {
/* 42 */     setSubType("JavaScript");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDActionJavaScript(String js) {
/* 52 */     this();
/* 53 */     setAction(js);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDActionJavaScript(COSDictionary a) {
/* 63 */     super(a);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void setAction(String sAction) {
/* 71 */     this.action.setString(COSName.JS, sAction);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getAction() {
/* 79 */     COSBase base = this.action.getDictionaryObject(COSName.JS);
/* 80 */     if (base instanceof COSString)
/*    */     {
/* 82 */       return ((COSString)base).getString();
/*    */     }
/* 84 */     if (base instanceof COSStream)
/*    */     {
/* 86 */       return ((COSStream)base).toTextString();
/*    */     }
/*    */ 
/*    */     
/* 90 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionJavaScript.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */