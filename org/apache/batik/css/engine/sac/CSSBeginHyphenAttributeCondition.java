/*    */ package org.apache.batik.css.engine.sac;
/*    */ 
/*    */ import org.w3c.dom.Element;
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
/*    */ public class CSSBeginHyphenAttributeCondition
/*    */   extends CSSAttributeCondition
/*    */ {
/*    */   public CSSBeginHyphenAttributeCondition(String localName, String namespaceURI, boolean specified, String value) {
/* 40 */     super(localName, namespaceURI, specified, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getConditionType() {
/* 48 */     return 8;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean match(Element e, String pseudoE) {
/* 55 */     return e.getAttribute(getLocalName()).startsWith(getValue());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     return '[' + getLocalName() + "|=\"" + getValue() + "\"]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSBeginHyphenAttributeCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */