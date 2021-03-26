/*    */ package org.apache.batik.css.engine.value;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class URIValue
/*    */   extends StringValue
/*    */ {
/*    */   String cssText;
/*    */   
/*    */   public URIValue(String cssText, String uri) {
/* 37 */     super((short)20, uri);
/* 38 */     this.cssText = cssText;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCssText() {
/* 45 */     return "url(" + this.cssText + ')';
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/URIValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */