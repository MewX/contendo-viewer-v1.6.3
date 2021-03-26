/*    */ package org.apache.pdfbox.pdmodel.common.function;
/*    */ 
/*    */ import java.io.IOException;
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
/*    */ public class PDFunctionTypeIdentity
/*    */   extends PDFunction
/*    */ {
/*    */   public PDFunctionTypeIdentity(COSBase function) {
/* 31 */     super(null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getFunctionType() {
/* 38 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] eval(float[] input) throws IOException {
/* 45 */     return input;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return "FunctionTypeIdentity";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/PDFunctionTypeIdentity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */