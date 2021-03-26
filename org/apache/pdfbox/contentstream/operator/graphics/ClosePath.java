/*    */ package org.apache.pdfbox.contentstream.operator.graphics;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
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
/*    */ public final class ClosePath
/*    */   extends GraphicsOperatorProcessor
/*    */ {
/* 35 */   private static final Log LOG = LogFactory.getLog(ClosePath.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public void process(Operator operator, List<COSBase> operands) throws IOException {
/* 40 */     if (this.context.getCurrentPoint() == null) {
/*    */       
/* 42 */       LOG.warn("ClosePath without initial MoveTo");
/*    */       return;
/*    */     } 
/* 45 */     this.context.closePath();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 51 */     return "h";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/graphics/ClosePath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */