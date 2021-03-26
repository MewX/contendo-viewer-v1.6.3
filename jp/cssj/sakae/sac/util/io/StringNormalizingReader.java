/*     */ package jp.cssj.sakae.sac.util.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringNormalizingReader
/*     */   extends NormalizingReader
/*     */ {
/*     */   protected String string;
/*     */   protected int length;
/*     */   protected int next;
/*  82 */   protected int line = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int column;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringNormalizingReader(String s) {
/*  96 */     this.string = s;
/*  97 */     this.length = s.length();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 105 */     int result = (this.length == this.next) ? -1 : this.string.charAt(this.next++);
/* 106 */     if (result <= 13) {
/* 107 */       int c; switch (result) {
/*     */         case 13:
/* 109 */           this.column = 0;
/* 110 */           this.line++;
/* 111 */           c = (this.length == this.next) ? -1 : this.string.charAt(this.next);
/* 112 */           if (c == 10) {
/* 113 */             this.next++;
/*     */           }
/* 115 */           return 10;
/*     */         
/*     */         case 10:
/* 118 */           this.column = 0;
/* 119 */           this.line++; break;
/*     */       } 
/*     */     } 
/* 122 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLine() {
/* 129 */     return this.line;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumn() {
/* 136 */     return this.column;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 143 */     this.string = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/util/io/StringNormalizingReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */