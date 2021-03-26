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
/*    */ public class XAPMMAdapter
/*    */   extends XMPSchemaAdapter
/*    */ {
/*    */   public XAPMMAdapter(Metadata meta, String namespace) {
/* 33 */     super(meta, XMPSchemaRegistry.getInstance().getSchema(namespace));
/*    */   }
/*    */   
/*    */   public void setVersion(String v) {
/* 37 */     setValue("VersionID", v);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRenditionClass(String c) {
/* 45 */     setValue("RenditionClass", c);
/*    */   }
/*    */   
/*    */   public void setInstanceID(String v) {
/* 49 */     setValue("InstanceID", v);
/*    */   }
/*    */   
/*    */   public void setDocumentID(String v) {
/* 53 */     setValue("DocumentID", v);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/XAPMMAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */