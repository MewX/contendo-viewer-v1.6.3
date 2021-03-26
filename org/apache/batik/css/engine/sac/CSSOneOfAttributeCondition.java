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
/*    */ public class CSSOneOfAttributeCondition
/*    */   extends CSSAttributeCondition
/*    */ {
/*    */   public CSSOneOfAttributeCondition(String localName, String namespaceURI, boolean specified, String value) {
/* 38 */     super(localName, namespaceURI, specified, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getConditionType() {
/* 46 */     return 7;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean match(Element e, String pseudoE) {
/* 53 */     String attr = e.getAttribute(getLocalName());
/* 54 */     String val = getValue();
/* 55 */     int i = attr.indexOf(val);
/* 56 */     if (i == -1) {
/* 57 */       return false;
/*    */     }
/* 59 */     if (i != 0 && !Character.isSpaceChar(attr.charAt(i - 1))) {
/* 60 */       return false;
/*    */     }
/* 62 */     int j = i + val.length();
/* 63 */     return (j == attr.length() || (j < attr.length() && Character.isSpaceChar(attr.charAt(j))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 71 */     return "[" + getLocalName() + "~=\"" + getValue() + "\"]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSOneOfAttributeCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */