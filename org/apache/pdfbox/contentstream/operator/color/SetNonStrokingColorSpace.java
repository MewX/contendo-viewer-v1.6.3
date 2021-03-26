/*    */ package org.apache.pdfbox.contentstream.operator.color;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
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
/*    */ public class SetNonStrokingColorSpace
/*    */   extends OperatorProcessor
/*    */ {
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 40 */     COSName name = (COSName)arguments.get(0);
/*    */     
/* 42 */     PDColorSpace cs = this.context.getResources().getColorSpace(name);
/* 43 */     this.context.getGraphicsState().setNonStrokingColorSpace(cs);
/* 44 */     this.context.getGraphicsState().setNonStrokingColor(cs.getInitialColor());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 50 */     return "cs";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/color/SetNonStrokingColorSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */