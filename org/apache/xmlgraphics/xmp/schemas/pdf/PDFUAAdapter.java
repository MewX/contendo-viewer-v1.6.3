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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PDFUAAdapter
/*    */   extends XMPSchemaAdapter
/*    */ {
/*    */   private static final String PART = "part";
/*    */   
/*    */   public PDFUAAdapter(Metadata meta, String namespace) {
/* 41 */     super(meta, XMPSchemaRegistry.getInstance().getSchema(namespace));
/*    */   }
/*    */   
/*    */   public void setPart(int value) {
/* 45 */     setValue("part", Integer.toString(value));
/*    */   }
/*    */   
/*    */   public int getPart() {
/* 49 */     return Integer.parseInt(getValue("part"));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/PDFUAAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */