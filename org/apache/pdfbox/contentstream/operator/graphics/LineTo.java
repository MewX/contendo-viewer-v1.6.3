/*    */ package org.apache.pdfbox.contentstream.operator.graphics;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSNumber;
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
/*    */ public class LineTo
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/* 38 */   private static final Log LOG = LogFactory.getLog(LineTo.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 43 */     if (operands.size() < 2)
/*    */     {
/* 45 */       throw new MissingOperandException(operator, operands);
/*    */     }
/* 47 */     COSBase base0 = operands.get(0);
/* 48 */     if (!(base0 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */     
/* 52 */     COSBase base1 = operands.get(1);
/* 53 */     if (!(base1 instanceof COSNumber)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 58 */     COSNumber x = (COSNumber)base0;
/* 59 */     COSNumber y = (COSNumber)base1;
/*    */     
/* 61 */     Point2D.Float pos = this.context.transformedPoint(x.floatValue(), y.floatValue());
/*    */     
/* 63 */     if (this.context.getCurrentPoint() == null) {
/*    */       
/* 65 */       LOG.warn("LineTo (" + pos.x + "," + pos.y + ") without initial MoveTo");
/* 66 */       this.context.moveTo(pos.x, pos.y);
/*    */     }
/*    */     else {
/*    */       
/* 70 */       this.context.lineTo(pos.x, pos.y);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 77 */     return "l";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/LineTo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */