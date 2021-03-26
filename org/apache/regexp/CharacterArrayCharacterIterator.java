/*     */ package org.apache.regexp;
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
/*     */ public final class CharacterArrayCharacterIterator
/*     */   implements CharacterIterator
/*     */ {
/*     */   private final char[] src;
/*     */   private final int off;
/*     */   private final int len;
/*     */   
/*     */   public CharacterArrayCharacterIterator(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
/*  76 */     this.src = paramArrayOfchar;
/*  77 */     this.off = paramInt1;
/*  78 */     this.len = paramInt2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String substring(int paramInt1, int paramInt2) {
/*  84 */     return new String(this.src, this.off + paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String substring(int paramInt) {
/*  90 */     return new String(this.src, this.off + paramInt, this.len);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public char charAt(int paramInt) {
/*  96 */     return this.src[this.off + paramInt];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnd(int paramInt) {
/* 102 */     return !(paramInt < this.len);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/CharacterArrayCharacterIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */