/*    */ package org.apache.batik.css.engine;
/*    */ 
/*    */ import org.w3c.css.sac.SACMediaList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MediaRule
/*    */   extends StyleSheet
/*    */   implements Rule
/*    */ {
/*    */   public static final short TYPE = 1;
/*    */   protected SACMediaList mediaList;
/*    */   
/*    */   public short getType() {
/* 45 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMediaList(SACMediaList ml) {
/* 52 */     this.mediaList = ml;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SACMediaList getMediaList() {
/* 59 */     return this.mediaList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString(CSSEngine eng) {
/* 66 */     StringBuffer sb = new StringBuffer();
/* 67 */     sb.append("@media");
/* 68 */     if (this.mediaList != null) {
/* 69 */       for (int j = 0; j < this.mediaList.getLength(); j++) {
/* 70 */         sb.append(' ');
/* 71 */         sb.append(this.mediaList.item(j));
/*    */       } 
/*    */     }
/* 74 */     sb.append(" {\n");
/* 75 */     for (int i = 0; i < this.size; i++) {
/* 76 */       sb.append(this.rules[i].toString(eng));
/*    */     }
/* 78 */     sb.append("}\n");
/* 79 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/MediaRule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */