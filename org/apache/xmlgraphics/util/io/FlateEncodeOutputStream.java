/*    */ package org.apache.xmlgraphics.util.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.util.zip.DeflaterOutputStream;
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
/*    */ public class FlateEncodeOutputStream
/*    */   extends DeflaterOutputStream
/*    */   implements Finalizable
/*    */ {
/*    */   public FlateEncodeOutputStream(OutputStream out) {
/* 37 */     super(out);
/*    */   }
/*    */ 
/*    */   
/*    */   public void finalizeStream() throws IOException {
/* 42 */     finish();
/* 43 */     flush();
/*    */ 
/*    */     
/* 46 */     this.def.end();
/*    */     
/* 48 */     if (this.out instanceof Finalizable)
/* 49 */       ((Finalizable)this.out).finalizeStream(); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/io/FlateEncodeOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */