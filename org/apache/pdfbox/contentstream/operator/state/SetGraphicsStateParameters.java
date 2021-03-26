/*    */ package org.apache.pdfbox.contentstream.operator.state;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.pdfbox.contentstream.operator.MissingOperandException;
/*    */ import org.apache.pdfbox.contentstream.operator.Operator;
/*    */ import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
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
/*    */ public class SetGraphicsStateParameters
/*    */   extends OperatorProcessor
/*    */ {
/* 39 */   private static final Log LOG = LogFactory.getLog(SetGraphicsStateParameters.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public void process(Operator operator, List<COSBase> arguments) throws IOException {
/* 44 */     if (arguments.isEmpty())
/*    */     {
/* 46 */       throw new MissingOperandException(operator, arguments);
/*    */     }
/* 48 */     COSBase base0 = arguments.get(0);
/* 49 */     if (!(base0 instanceof COSName)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 55 */     COSName graphicsName = (COSName)base0;
/* 56 */     PDExtendedGraphicsState gs = this.context.getResources().getExtGState(graphicsName);
/* 57 */     if (gs == null) {
/*    */       
/* 59 */       LOG.error("name for 'gs' operator not found in resources: /" + graphicsName.getName());
/*    */       return;
/*    */     } 
/* 62 */     gs.copyIntoGraphicsState(this.context.getGraphicsState());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 68 */     return "gs";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/contentstream/operator/state/SetGraphicsStateParameters.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */