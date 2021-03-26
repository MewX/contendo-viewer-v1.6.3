/*    */ package org.apache.batik.css.engine.value.svg12;
/*    */ 
/*    */ import org.apache.batik.css.engine.value.AbstractValue;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ICCNamedColor
/*    */   extends AbstractValue
/*    */ {
/*    */   public static final String ICC_NAMED_COLOR_FUNCTION = "icc-named-color";
/*    */   protected String colorProfile;
/*    */   protected String colorName;
/*    */   
/*    */   public ICCNamedColor(String profileName, String colorName) {
/* 48 */     this.colorProfile = profileName;
/* 49 */     this.colorName = colorName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public short getCssValueType() {
/* 57 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getColorProfile() throws DOMException {
/* 64 */     return this.colorProfile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getColorName() throws DOMException {
/* 71 */     return this.colorName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getCssText() {
/* 78 */     StringBuffer sb = new StringBuffer("icc-named-color");
/* 79 */     sb.append('(');
/* 80 */     sb.append(this.colorProfile);
/* 81 */     sb.append(", ");
/* 82 */     sb.append(this.colorName);
/* 83 */     sb.append(')');
/* 84 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 89 */     return getCssText();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/value/svg12/ICCNamedColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */