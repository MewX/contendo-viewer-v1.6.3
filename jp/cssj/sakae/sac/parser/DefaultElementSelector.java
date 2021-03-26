/*    */ package jp.cssj.sakae.sac.parser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultElementSelector
/*    */   extends AbstractElementSelector
/*    */ {
/*    */   public DefaultElementSelector(String uri, String name) {
/* 66 */     super(uri, name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getSelectorType() {
/* 74 */     return 4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 81 */     String uri = getNamespaceURI();
/* 82 */     String name = getLocalName();
/* 83 */     if (name == null) {
/* 84 */       name = "*";
/*    */     }
/* 86 */     if (uri == null) {
/* 87 */       return name;
/*    */     }
/* 89 */     return uri + "|" + name;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/parser/DefaultElementSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */