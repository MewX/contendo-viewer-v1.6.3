/*    */ package org.apache.batik.ext.awt.image.rendered;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.ByteLookupTable;
/*    */ import java.awt.image.LookupOp;
/*    */ import java.awt.image.LookupTable;
/*    */ import java.awt.image.WritableRaster;
/*    */ import java.util.Map;
/*    */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*    */ import org.apache.batik.ext.awt.image.TransferFunction;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ComponentTransferRed
/*    */   extends AbstractRed
/*    */ {
/*    */   LookupOp operation;
/*    */   
/*    */   public ComponentTransferRed(CachableRed src, TransferFunction[] funcs, RenderingHints hints) {
/* 49 */     super(src, src.getBounds(), GraphicsUtil.coerceColorModel(src.getColorModel(), false), src.getSampleModel(), (Map)null);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     byte[][] tableData = { funcs[1].getLookupTable(), funcs[2].getLookupTable(), funcs[3].getLookupTable(), funcs[0].getLookupTable() };
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
/* 65 */     this.operation = new LookupOp(new ByteLookupTable(0, tableData), hints) {  }
/*    */       ;
/*    */   }
/*    */   
/*    */   public WritableRaster copyData(WritableRaster wr) {
/* 70 */     CachableRed src = getSources().get(0);
/*    */     
/* 72 */     wr = src.copyData(wr);
/* 73 */     GraphicsUtil.coerceData(wr, src.getColorModel(), false);
/*    */     
/* 75 */     WritableRaster srcWR = wr.createWritableTranslatedChild(0, 0);
/*    */     
/* 77 */     this.operation.filter(srcWR, srcWR);
/*    */     
/* 79 */     return wr;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/ComponentTransferRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */