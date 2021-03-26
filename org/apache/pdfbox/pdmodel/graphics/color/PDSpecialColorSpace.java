/*    */ package org.apache.pdfbox.pdmodel.graphics.color;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSBase;
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
/*    */ public abstract class PDSpecialColorSpace
/*    */   extends PDColorSpace
/*    */ {
/*    */   public COSBase getCOSObject() {
/* 31 */     return (COSBase)this.array;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDSpecialColorSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */