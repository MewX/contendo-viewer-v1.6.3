/*    */ package org.apache.batik.dom.svg;
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
/*    */ public class LiveAttributeException
/*    */   extends RuntimeException
/*    */ {
/*    */   public static final short ERR_ATTRIBUTE_MISSING = 0;
/*    */   public static final short ERR_ATTRIBUTE_MALFORMED = 1;
/*    */   public static final short ERR_ATTRIBUTE_NEGATIVE = 2;
/*    */   protected Element e;
/*    */   protected String attributeName;
/*    */   protected short code;
/*    */   protected String value;
/*    */   
/*    */   public LiveAttributeException(Element e, String an, short code, String val) {
/* 68 */     this.e = e;
/* 69 */     this.attributeName = an;
/* 70 */     this.code = code;
/* 71 */     this.value = val;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Element getElement() {
/* 78 */     return this.e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getAttributeName() {
/* 85 */     return this.attributeName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getCode() {
/* 92 */     return this.code;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 99 */     return this.value;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/LiveAttributeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */