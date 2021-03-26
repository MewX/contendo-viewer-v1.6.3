/*    */ package org.apache.batik.css.engine;
/*    */ 
/*    */ import org.apache.batik.util.ParsedURL;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FontFaceRule
/*    */   implements Rule
/*    */ {
/*    */   public static final short TYPE = 3;
/*    */   StyleMap sm;
/*    */   ParsedURL purl;
/*    */   
/*    */   public FontFaceRule(StyleMap sm, ParsedURL purl) {
/* 41 */     this.sm = sm;
/* 42 */     this.purl = purl;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public short getType() {
/* 48 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ParsedURL getURL() {
/* 54 */     return this.purl;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public StyleMap getStyleMap() {
/* 61 */     return this.sm;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString(CSSEngine eng) {
/* 68 */     StringBuffer sb = new StringBuffer();
/* 69 */     sb.append("@font-face { ");
/* 70 */     sb.append(this.sm.toString(eng));
/* 71 */     sb.append(" }\n");
/* 72 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/FontFaceRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */