/*    */ package org.apache.xalan.templates;
/*    */ 
/*    */ import java.util.Vector;
/*    */ import org.apache.xml.utils.FastStringBuffer;
/*    */ import org.apache.xml.utils.PrefixResolver;
/*    */ import org.apache.xpath.XPathContext;
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
/*    */ public class AVTPartSimple
/*    */   extends AVTPart
/*    */ {
/*    */   private String m_val;
/*    */   
/*    */   public AVTPartSimple(String val) {
/* 43 */     this.m_val = val;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSimpleString() {
/* 53 */     return this.m_val;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void fixupVariables(Vector vars, int globalsSize) {}
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
/*    */   public void evaluate(XPathContext xctxt, FastStringBuffer buf, int context, PrefixResolver nsNode) {
/* 88 */     buf.append(this.m_val);
/*    */   }
/*    */   
/*    */   public void callVisitors(XSLTVisitor visitor) {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/AVTPartSimple.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */