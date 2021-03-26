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
/*    */ public class CurveTo
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/* 38 */   private static final Log LOG = LogFactory.getLog(CurveTo.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 43 */     if (operands.size() < 6)
/*    */     {
/* 45 */       throw new MissingOperandException(operator, operands);
/*    */     }
/* 47 */     if (!checkArrayTypesClass(operands, COSNumber.class)) {
/*    */       return;
/*    */     }
/*    */     
/* 51 */     COSNumber x1 = (COSNumber)operands.get(0);
/* 52 */     COSNumber y1 = (COSNumber)operands.get(1);
/* 53 */     COSNumber x2 = (COSNumber)operands.get(2);
/* 54 */     COSNumber y2 = (COSNumber)operands.get(3);
/* 55 */     COSNumber x3 = (COSNumber)operands.get(4);
/* 56 */     COSNumber y3 = (COSNumber)operands.get(5);
/*    */     
/* 58 */     Point2D.Float point1 = this.context.transformedPoint(x1.floatValue(), y1.floatValue());
/* 59 */     Point2D.Float point2 = this.context.transformedPoint(x2.floatValue(), y2.floatValue());
/* 60 */     Point2D.Float point3 = this.context.transformedPoint(x3.floatValue(), y3.floatValue());
/*    */     
/* 62 */     if (this.context.getCurrentPoint() == null) {
/*    */       
/* 64 */       LOG.warn("curveTo (" + point3.x + "," + point3.y + ") without initial MoveTo");
/* 65 */       this.context.moveTo(point3.x, point3.y);
/*    */     }
/*    */     else {
/*    */       
/* 69 */       this.context.curveTo(point1.x, point1.y, point2.x, point2.y, point3.x, point3.y);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 78 */     return "c";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/CurveTo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */