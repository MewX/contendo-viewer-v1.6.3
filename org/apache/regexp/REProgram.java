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
/*     */ public class REProgram
/*     */ {
/*     */   static final int OPT_HASBACKREFS = 1;
/*     */   char[] instruction;
/*     */   int lenInstruction;
/*     */   char[] prefix;
/*     */   int flags;
/*     */   
/*     */   public REProgram(char[] paramArrayOfchar) {
/*  90 */     this(paramArrayOfchar, paramArrayOfchar.length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public REProgram(char[] paramArrayOfchar, int paramInt) {
/* 100 */     setInstructions(paramArrayOfchar, paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char[] getInstructions() {
/* 112 */     if (this.lenInstruction != 0) {
/*     */ 
/*     */       
/* 115 */       char[] arrayOfChar = new char[this.lenInstruction];
/* 116 */       System.arraycopy(this.instruction, 0, arrayOfChar, 0, this.lenInstruction);
/* 117 */       return arrayOfChar;
/*     */     } 
/* 119 */     return null;
/*     */   }
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
/*     */   public void setInstructions(char[] paramArrayOfchar, int paramInt) {
/* 135 */     this.instruction = paramArrayOfchar;
/* 136 */     this.lenInstruction = paramInt;
/*     */ 
/*     */     
/* 139 */     this.flags = 0;
/* 140 */     this.prefix = null;
/*     */ 
/*     */     
/* 143 */     if (paramArrayOfchar != null && paramInt != 0) {
/*     */ 
/*     */       
/* 146 */       if (paramInt >= 3 && paramArrayOfchar[0] == '|') {
/*     */ 
/*     */         
/* 149 */         char c = paramArrayOfchar[2];
/* 150 */         if (paramArrayOfchar[c] == 'E')
/*     */         {
/*     */           
/* 153 */           if (paramInt >= 6 && paramArrayOfchar[3] == 'A') {
/*     */ 
/*     */             
/* 156 */             char c1 = paramArrayOfchar[4];
/* 157 */             this.prefix = new char[c1];
/* 158 */             System.arraycopy(paramArrayOfchar, 6, this.prefix, 0, c1);
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 166 */       for (int i = 0; i < paramInt; i += 3) {
/*     */         
/* 168 */         switch (paramArrayOfchar[i]) {
/*     */           
/*     */           case '[':
/* 171 */             i += paramArrayOfchar[i + 1] * 2;
/*     */             break;
/*     */           
/*     */           case 'A':
/* 175 */             i += paramArrayOfchar[i + 1];
/*     */             break;
/*     */           
/*     */           case '#':
/* 179 */             this.flags |= 0x1;
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/REProgram.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */