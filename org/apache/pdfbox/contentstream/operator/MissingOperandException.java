/*    */ package org.apache.pdfbox.contentstream.operator;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
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
/*    */ public final class MissingOperandException
/*    */   extends IOException
/*    */ {
/*    */   public MissingOperandException(Operator operator, List<COSBase> operands) {
/* 31 */     super("Operator " + operator.getName() + " has too few operands: " + operands);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/MissingOperandException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */