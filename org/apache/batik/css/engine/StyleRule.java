/*    */ package org.apache.batik.css.engine;
/*    */ 
/*    */ import org.w3c.css.sac.SelectorList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StyleRule
/*    */   implements Rule
/*    */ {
/*    */   public static final short TYPE = 0;
/*    */   protected SelectorList selectorList;
/*    */   protected StyleDeclaration styleDeclaration;
/*    */   
/*    */   public short getType() {
/* 50 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSelectorList(SelectorList sl) {
/* 57 */     this.selectorList = sl;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SelectorList getSelectorList() {
/* 64 */     return this.selectorList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setStyleDeclaration(StyleDeclaration sd) {
/* 71 */     this.styleDeclaration = sd;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StyleDeclaration getStyleDeclaration() {
/* 78 */     return this.styleDeclaration;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString(CSSEngine eng) {
/* 85 */     StringBuffer sb = new StringBuffer();
/* 86 */     if (this.selectorList != null) {
/* 87 */       sb.append(this.selectorList.item(0));
/* 88 */       for (int i = 1; i < this.selectorList.getLength(); i++) {
/* 89 */         sb.append(", ");
/* 90 */         sb.append(this.selectorList.item(i));
/*    */       } 
/*    */     } 
/* 93 */     sb.append(" {\n");
/* 94 */     if (this.styleDeclaration != null) {
/* 95 */       sb.append(this.styleDeclaration.toString(eng));
/*    */     }
/* 97 */     sb.append("}\n");
/* 98 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/StyleRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */