/*    */ package org.apache.batik.css.engine.sac;
/*    */ 
/*    */ import org.apache.batik.css.engine.CSSStylableElement;
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
/*    */ public class CSSClassCondition
/*    */   extends CSSAttributeCondition
/*    */ {
/*    */   public CSSClassCondition(String localName, String namespaceURI, String value) {
/* 39 */     super(localName, namespaceURI, true, value);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getConditionType() {
/* 47 */     return 9;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean match(Element e, String pseudoE) {
/* 54 */     if (!(e instanceof CSSStylableElement))
/* 55 */       return false; 
/* 56 */     String attr = ((CSSStylableElement)e).getCSSClass();
/* 57 */     String val = getValue();
/* 58 */     int attrLen = attr.length();
/* 59 */     int valLen = val.length();
/*    */     
/* 61 */     int i = attr.indexOf(val);
/* 62 */     while (i != -1) {
/* 63 */       if ((i == 0 || Character.isSpaceChar(attr.charAt(i - 1))) && (
/* 64 */         i + valLen == attrLen || Character.isSpaceChar(attr.charAt(i + valLen))))
/*    */       {
/* 66 */         return true;
/*    */       }
/*    */       
/* 69 */       i = attr.indexOf(val, i + valLen);
/*    */     } 
/* 71 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 78 */     return '.' + getValue();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/sac/CSSClassCondition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */