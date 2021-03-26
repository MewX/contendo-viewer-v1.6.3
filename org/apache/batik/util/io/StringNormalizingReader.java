/*     */ package org.apache.batik.util.io;
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
/*     */ public class StringNormalizingReader
/*     */   extends NormalizingReader
/*     */ {
/*     */   protected String string;
/*     */   protected int length;
/*     */   protected int next;
/*  49 */   protected int line = 1;
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
/*     */   public StringNormalizingReader(String s) {
/*  61 */     this.string = s;
/*  62 */     this.length = s.length();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  71 */     int result = (this.length == this.next) ? -1 : this.string.charAt(this.next++);
/*  72 */     if (result <= 13) {
/*  73 */       int c; switch (result) {
/*     */         case 13:
/*  75 */           this.column = 0;
/*  76 */           this.line++;
/*  77 */           c = (this.length == this.next) ? -1 : this.string.charAt(this.next);
/*  78 */           if (c == 10) {
/*  79 */             this.next++;
/*     */           }
/*  81 */           return 10;
/*     */         
/*     */         case 10:
/*  84 */           this.column = 0;
/*  85 */           this.line++; break;
/*     */       } 
/*     */     } 
/*  88 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLine() {
/*  95 */     return this.line;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumn() {
/* 102 */     return this.column;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 109 */     this.string = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/io/StringNormalizingReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */