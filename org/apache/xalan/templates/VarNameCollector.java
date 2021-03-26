/*    */ package org.apache.xalan.templates;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.xml.utils.QName;
/*    */ import org.apache.xpath.ExpressionOwner;
/*    */ import org.apache.xpath.XPathVisitor;
/*    */ import org.apache.xpath.operations.Variable;
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
/*    */ public class VarNameCollector
/*    */   extends XPathVisitor
/*    */ {
/* 33 */   Vector m_refs = new Vector();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void reset() {
/* 40 */     this.m_refs.removeAllElements();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getVarCount() {
/* 49 */     return this.m_refs.size();
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
/*    */   boolean doesOccur(QName refName) {
/* 61 */     return this.m_refs.contains(refName);
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
/*    */   public boolean visitVariableRef(ExpressionOwner owner, Variable var) {
/* 73 */     this.m_refs.addElement(var.getQName());
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/VarNameCollector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */