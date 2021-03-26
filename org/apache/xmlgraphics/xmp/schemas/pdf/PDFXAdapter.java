/*    */ package org.apache.xmlgraphics.xmp.schemas.pdf;
/*    */ 
/*    */ import org.apache.xmlgraphics.xmp.Metadata;
/*    */ import org.apache.xmlgraphics.xmp.XMPSchemaAdapter;
/*    */ import org.apache.xmlgraphics.xmp.XMPSchemaRegistry;
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
/*    */ public class PDFXAdapter
/*    */   extends XMPSchemaAdapter
/*    */ {
/*    */   public PDFXAdapter(Metadata meta, String namespace) {
/* 33 */     super(meta, XMPSchemaRegistry.getInstance().getSchema(namespace));
/*    */   }
/*    */   
/*    */   public void setVersion(String v) {
/* 37 */     setValue("GTS_PDFXVersion", v);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/PDFXAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */