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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImportRule
/*    */   extends MediaRule
/*    */ {
/*    */   public static final short TYPE = 2;
/*    */   protected ParsedURL uri;
/*    */   
/*    */   public short getType() {
/* 45 */     return 2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setURI(ParsedURL u) {
/* 52 */     this.uri = u;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ParsedURL getURI() {
/* 59 */     return this.uri;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString(CSSEngine eng) {
/* 66 */     StringBuffer sb = new StringBuffer();
/* 67 */     sb.append("@import \"");
/* 68 */     sb.append(this.uri);
/* 69 */     sb.append("\"");
/* 70 */     if (this.mediaList != null) {
/* 71 */       for (int i = 0; i < this.mediaList.getLength(); i++) {
/* 72 */         sb.append(' ');
/* 73 */         sb.append(this.mediaList.item(i));
/*    */       } 
/*    */     }
/* 76 */     sb.append(";\n");
/* 77 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/ImportRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */