/*    */ package org.apache.pdfbox.pdmodel.graphics.color;
/*    */ 
/*    */ import org.apache.pdfbox.cos.COSBase;
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
/*    */ public abstract class PDDeviceColorSpace
/*    */   extends PDColorSpace
/*    */ {
/*    */   public String toString() {
/* 32 */     return getName();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSObject() {
/* 38 */     return (COSBase)COSName.getPDFName(getName());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/color/PDDeviceColorSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */