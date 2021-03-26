/*    */ package org.apache.xpath.objects;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xml.utils.WrappedRuntimeException;
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
/*    */ public class XBooleanStatic
/*    */   extends XBoolean
/*    */ {
/*    */   boolean m_val;
/*    */   
/*    */   public XBooleanStatic(boolean b) {
/* 41 */     super(b);
/*    */     
/* 43 */     this.m_val = b;
/*    */   }
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
/*    */   public boolean equals(XObject obj2) {
/*    */     
/* 59 */     try { return (this.m_val == obj2.bool()); } catch (TransformerException te)
/*    */     
/*    */     { 
/*    */       
/* 63 */       throw new WrappedRuntimeException(te); }
/*    */   
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XBooleanStatic.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */