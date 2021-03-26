/*    */ package org.apache.xpath.axes;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.compiler.Compiler;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AttributeIterator
/*    */   extends ChildTestIterator
/*    */ {
/*    */   AttributeIterator(Compiler compiler, int opPos, int analysis) throws TransformerException {
/* 45 */     super(compiler, opPos, analysis);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getNextNode() {
/* 53 */     this.m_lastFetched = (-1 == this.m_lastFetched) ? this.m_cdtm.getFirstAttribute(this.m_context) : this.m_cdtm.getNextAttribute(this.m_lastFetched);
/*    */ 
/*    */     
/* 56 */     return this.m_lastFetched;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAxis() {
/* 67 */     return 2;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/AttributeIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */