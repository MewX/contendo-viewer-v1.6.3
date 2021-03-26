/*    */ package jp.cssj.a;
/*    */ 
/*    */ import org.apache.xerces.util.XMLAttributesImpl;
/*    */ import org.apache.xerces.xni.QName;
/*    */ import org.apache.xerces.xni.XMLAttributes;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class d
/*    */ {
/*    */   public c.a a;
/*    */   public final QName b;
/*    */   public final XMLAttributes c;
/*    */   
/*    */   public d(c.a prop, QName qname, XMLAttributes attributes) {
/* 55 */     this.a = prop;
/* 56 */     this.b = new QName(qname);
/* 57 */     if (attributes != null) {
/* 58 */       int length = attributes.getLength();
/* 59 */       if (length > 0) {
/* 60 */         QName aqname = new QName();
/* 61 */         XMLAttributesImpl xMLAttributesImpl2 = new XMLAttributesImpl();
/* 62 */         for (int i = 0; i < length; i++) {
/* 63 */           attributes.getName(i, aqname);
/* 64 */           String type = attributes.getType(i);
/* 65 */           String value = attributes.getValue(i);
/* 66 */           String nonNormalizedValue = attributes.getNonNormalizedValue(i);
/* 67 */           boolean specified = attributes.isSpecified(i);
/* 68 */           xMLAttributesImpl2.addAttribute(aqname, type, value);
/* 69 */           xMLAttributesImpl2.setNonNormalizedValue(i, nonNormalizedValue);
/* 70 */           xMLAttributesImpl2.setSpecified(i, specified);
/*    */         } 
/* 72 */         XMLAttributesImpl xMLAttributesImpl1 = xMLAttributesImpl2;
/*    */       } else {
/* 74 */         attributes = null;
/*    */       } 
/*    */     } 
/* 77 */     this.c = attributes;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 84 */     return super.toString() + this.b;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */