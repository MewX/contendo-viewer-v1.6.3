/*    */ package org.apache.batik.css.engine.value;
/*    */ 
/*    */ import org.apache.batik.util.ParsedURL;
/*    */ import org.w3c.dom.DOMException;
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
/*    */ public abstract class AbstractValueFactory
/*    */ {
/*    */   public abstract String getPropertyName();
/*    */   
/*    */   protected static String resolveURI(ParsedURL base, String value) {
/* 41 */     return (new ParsedURL(base, value)).toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected DOMException createInvalidIdentifierDOMException(String ident) {
/* 48 */     Object[] p = { getPropertyName(), ident };
/* 49 */     String s = Messages.formatMessage("invalid.identifier", p);
/* 50 */     return new DOMException((short)12, s);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected DOMException createInvalidLexicalUnitDOMException(short type) {
/* 57 */     Object[] p = { getPropertyName(), Integer.valueOf(type) };
/*    */     
/* 59 */     String s = Messages.formatMessage("invalid.lexical.unit", p);
/* 60 */     return new DOMException((short)9, s);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected DOMException createInvalidFloatTypeDOMException(short t) {
/* 67 */     Object[] p = { getPropertyName(), Integer.valueOf(t) };
/* 68 */     String s = Messages.formatMessage("invalid.float.type", p);
/* 69 */     return new DOMException((short)15, s);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected DOMException createInvalidFloatValueDOMException(float f) {
/* 76 */     Object[] p = { getPropertyName(), Float.valueOf(f) };
/* 77 */     String s = Messages.formatMessage("invalid.float.value", p);
/* 78 */     return new DOMException((short)15, s);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected DOMException createInvalidStringTypeDOMException(short t) {
/* 85 */     Object[] p = { getPropertyName(), Integer.valueOf(t) };
/* 86 */     String s = Messages.formatMessage("invalid.string.type", p);
/* 87 */     return new DOMException((short)15, s);
/*    */   }
/*    */   
/*    */   protected DOMException createMalformedLexicalUnitDOMException() {
/* 91 */     Object[] p = { getPropertyName() };
/* 92 */     String s = Messages.formatMessage("malformed.lexical.unit", p);
/* 93 */     return new DOMException((short)15, s);
/*    */   }
/*    */   
/*    */   protected DOMException createDOMException() {
/* 97 */     Object[] p = { getPropertyName() };
/* 98 */     String s = Messages.formatMessage("invalid.access", p);
/* 99 */     return new DOMException((short)9, s);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/AbstractValueFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */