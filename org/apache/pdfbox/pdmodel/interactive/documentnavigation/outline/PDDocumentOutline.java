/*    */ package org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline;
/*    */ 
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
/*    */ public final class PDDocumentOutline
/*    */   extends PDOutlineNode
/*    */ {
/*    */   public PDDocumentOutline() {
/* 35 */     getCOSObject().setName(COSName.TYPE, COSName.OUTLINES.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDDocumentOutline(COSDictionary dic) {
/* 45 */     super(dic);
/* 46 */     getCOSObject().setName(COSName.TYPE, COSName.OUTLINES.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isNodeOpen() {
/* 52 */     return true;
/*    */   }
/*    */   
/*    */   public void openNode() {}
/*    */   
/*    */   public void closeNode() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/documentnavigation/outline/PDDocumentOutline.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */