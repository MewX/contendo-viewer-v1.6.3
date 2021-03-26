/*    */ package org.apache.pdfbox.pdmodel.graphics.form;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSStream;
/*    */ import org.apache.pdfbox.pdmodel.PDDocument;
/*    */ import org.apache.pdfbox.pdmodel.ResourceCache;
/*    */ import org.apache.pdfbox.pdmodel.common.PDStream;
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
/*    */ public class PDTransparencyGroup
/*    */   extends PDFormXObject
/*    */ {
/*    */   public PDTransparencyGroup(PDStream stream) {
/* 38 */     super(stream);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDTransparencyGroup(COSStream stream, ResourceCache cache) {
/* 47 */     super(stream, cache);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDTransparencyGroup(PDDocument document) {
/* 56 */     super(document);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/form/PDTransparencyGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */