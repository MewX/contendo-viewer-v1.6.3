/*    */ package org.apache.batik.transcoder.wmf.tosvg;
/*    */ 
/*    */ import java.awt.Font;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WMFFont
/*    */ {
/*    */   public Font font;
/*    */   public int charset;
/* 32 */   public int underline = 0;
/* 33 */   public int strikeOut = 0;
/* 34 */   public int italic = 0;
/* 35 */   public int weight = 0;
/* 36 */   public int orientation = 0;
/* 37 */   public int escape = 0;
/*    */   
/*    */   public WMFFont(Font font, int charset) {
/* 40 */     this.font = font;
/* 41 */     this.charset = charset;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public WMFFont(Font font, int charset, int underline, int strikeOut, int italic, int weight, int orient, int escape) {
/* 47 */     this.font = font;
/* 48 */     this.charset = charset;
/* 49 */     this.underline = underline;
/* 50 */     this.strikeOut = strikeOut;
/* 51 */     this.italic = italic;
/* 52 */     this.weight = weight;
/* 53 */     this.orientation = orient;
/* 54 */     this.escape = escape;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/WMFFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */