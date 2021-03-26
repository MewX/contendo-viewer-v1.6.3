/*    */ package org.apache.xmlgraphics.xmp.schemas.pdf;
/*    */ 
/*    */ import java.util.Date;
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
/*    */ public class PDFVTAdapter
/*    */   extends XMPSchemaAdapter
/*    */ {
/*    */   public PDFVTAdapter(Metadata meta, String namespace) {
/* 35 */     super(meta, XMPSchemaRegistry.getInstance().getSchema(namespace));
/*    */   }
/*    */   
/*    */   public void setVersion(String v) {
/* 39 */     setValue("GTS_PDFVTVersion", v);
/*    */   }
/*    */   
/*    */   public void setModifyDate(Date modifyDate) {
/* 43 */     setDateValue("GTS_PDFVTModDate", modifyDate);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/PDFVTAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */