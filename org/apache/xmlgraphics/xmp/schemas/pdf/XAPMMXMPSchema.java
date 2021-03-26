/*    */ package org.apache.xmlgraphics.xmp.schemas.pdf;
/*    */ 
/*    */ import org.apache.xmlgraphics.xmp.Metadata;
/*    */ import org.apache.xmlgraphics.xmp.XMPSchema;
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
/*    */ public class XAPMMXMPSchema
/*    */   extends XMPSchema
/*    */ {
/*    */   public static final String NAMESPACE = "http://ns.adobe.com/xap/1.0/mm/";
/*    */   
/*    */   public XAPMMXMPSchema() {
/* 30 */     super("http://ns.adobe.com/xap/1.0/mm/", "xmpMM");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static XAPMMAdapter getAdapter(Metadata meta) {
/* 40 */     return new XAPMMAdapter(meta, "http://ns.adobe.com/xap/1.0/mm/");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/schemas/pdf/XAPMMXMPSchema.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */