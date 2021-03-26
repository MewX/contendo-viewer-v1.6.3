/*     */ package org.apache.commons.codec.language;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.apache.commons.codec.EncoderException;
/*     */ import org.apache.commons.codec.StringEncoder;
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
/*     */ public class ColognePhonetic
/*     */   implements StringEncoder
/*     */ {
/* 185 */   private static final char[] AEIJOUY = new char[] { 'A', 'E', 'I', 'J', 'O', 'U', 'Y' };
/* 186 */   private static final char[] SCZ = new char[] { 'S', 'C', 'Z' };
/* 187 */   private static final char[] WFPV = new char[] { 'W', 'F', 'P', 'V' };
/* 188 */   private static final char[] GKQ = new char[] { 'G', 'K', 'Q' };
/* 189 */   private static final char[] CKQ = new char[] { 'C', 'K', 'Q' };
/* 190 */   private static final char[] AHKLOQRUX = new char[] { 'A', 'H', 'K', 'L', 'O', 'Q', 'R', 'U', 'X' };
/* 191 */   private static final char[] SZ = new char[] { 'S', 'Z' };
/* 192 */   private static final char[] AHOUKQX = new char[] { 'A', 'H', 'O', 'U', 'K', 'Q', 'X' };
/* 193 */   private static final char[] TDX = new char[] { 'T', 'D', 'X' };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private abstract class CologneBuffer
/*     */   {
/*     */     protected final char[] data;
/*     */ 
/*     */ 
/*     */     
/* 204 */     protected int length = 0;
/*     */     
/*     */     public CologneBuffer(char[] data) {
/* 207 */       this.data = data;
/* 208 */       this.length = data.length;
/*     */     }
/*     */     
/*     */     public CologneBuffer(int buffSize) {
/* 212 */       this.data = new char[buffSize];
/* 213 */       this.length = 0;
/*     */     }
/*     */     
/*     */     protected abstract char[] copyData(int param1Int1, int param1Int2);
/*     */     
/*     */     public int length() {
/* 219 */       return this.length;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 224 */       return new String(copyData(0, this.length));
/*     */     }
/*     */   }
/*     */   
/*     */   private class CologneOutputBuffer
/*     */     extends CologneBuffer {
/*     */     public CologneOutputBuffer(int buffSize) {
/* 231 */       super(buffSize);
/*     */     }
/*     */     
/*     */     public void addRight(char chr) {
/* 235 */       this.data[this.length] = chr;
/* 236 */       this.length++;
/*     */     }
/*     */ 
/*     */     
/*     */     protected char[] copyData(int start, int length) {
/* 241 */       char[] newData = new char[length];
/* 242 */       System.arraycopy(this.data, start, newData, 0, length);
/* 243 */       return newData;
/*     */     }
/*     */   }
/*     */   
/*     */   private class CologneInputBuffer
/*     */     extends CologneBuffer {
/*     */     public CologneInputBuffer(char[] data) {
/* 250 */       super(data);
/*     */     }
/*     */     
/*     */     public void addLeft(char ch) {
/* 254 */       this.length++;
/* 255 */       this.data[getNextPos()] = ch;
/*     */     }
/*     */ 
/*     */     
/*     */     protected char[] copyData(int start, int length) {
/* 260 */       char[] newData = new char[length];
/* 261 */       System.arraycopy(this.data, this.data.length - this.length + start, newData, 0, length);
/* 262 */       return newData;
/*     */     }
/*     */     
/*     */     public char getNextChar() {
/* 266 */       return this.data[getNextPos()];
/*     */     }
/*     */     
/*     */     protected int getNextPos() {
/* 270 */       return this.data.length - this.length;
/*     */     }
/*     */     
/*     */     public char removeNext() {
/* 274 */       char ch = getNextChar();
/* 275 */       this.length--;
/* 276 */       return ch;
/*     */     }
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
/*     */   
/*     */   private static boolean arrayContains(char[] arr, char key) {
/* 294 */     for (char element : arr) {
/* 295 */       if (element == key) {
/* 296 */         return true;
/*     */       }
/*     */     } 
/* 299 */     return false;
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
/*     */   public String colognePhonetic(String text) {
/* 314 */     if (text == null) {
/* 315 */       return null;
/*     */     }
/*     */     
/* 318 */     CologneInputBuffer input = new CologneInputBuffer(preprocess(text));
/* 319 */     CologneOutputBuffer output = new CologneOutputBuffer(input.length() * 2);
/*     */ 
/*     */ 
/*     */     
/* 323 */     char CHAR_FIRST_POS = '/';
/* 324 */     char CHAR_IGNORE = '-';
/*     */     
/* 326 */     char lastChar = '-';
/* 327 */     char lastCode = '/';
/*     */ 
/*     */ 
/*     */     
/* 331 */     while (input.length() > 0) {
/* 332 */       char nextChar, code, chr = input.removeNext();
/*     */       
/* 334 */       if (input.length() > 0) {
/* 335 */         nextChar = input.getNextChar();
/*     */       } else {
/* 337 */         nextChar = '-';
/*     */       } 
/*     */ 
/*     */       
/* 341 */       if (chr == 'H' || chr < 'A' || chr > 'Z') {
/*     */         continue;
/*     */       }
/*     */       
/* 345 */       if (arrayContains(AEIJOUY, chr)) {
/* 346 */         code = '0';
/* 347 */       } else if (chr == 'B' || (chr == 'P' && nextChar != 'H')) {
/* 348 */         code = '1';
/* 349 */       } else if ((chr == 'D' || chr == 'T') && !arrayContains(SCZ, nextChar)) {
/* 350 */         code = '2';
/* 351 */       } else if (arrayContains(WFPV, chr)) {
/* 352 */         code = '3';
/* 353 */       } else if (arrayContains(GKQ, chr)) {
/* 354 */         code = '4';
/* 355 */       } else if (chr == 'X' && !arrayContains(CKQ, lastChar)) {
/* 356 */         code = '4';
/* 357 */         input.addLeft('S');
/* 358 */       } else if (chr == 'S' || chr == 'Z') {
/* 359 */         code = '8';
/* 360 */       } else if (chr == 'C') {
/* 361 */         if (lastCode == '/') {
/* 362 */           if (arrayContains(AHKLOQRUX, nextChar)) {
/* 363 */             code = '4';
/*     */           } else {
/* 365 */             code = '8';
/*     */           }
/*     */         
/* 368 */         } else if (arrayContains(SZ, lastChar) || !arrayContains(AHOUKQX, nextChar)) {
/* 369 */           code = '8';
/*     */         } else {
/* 371 */           code = '4';
/*     */         }
/*     */       
/* 374 */       } else if (arrayContains(TDX, chr)) {
/* 375 */         code = '8';
/* 376 */       } else if (chr == 'R') {
/* 377 */         code = '7';
/* 378 */       } else if (chr == 'L') {
/* 379 */         code = '5';
/* 380 */       } else if (chr == 'M' || chr == 'N') {
/* 381 */         code = '6';
/*     */       } else {
/* 383 */         code = chr;
/*     */       } 
/*     */       
/* 386 */       if (code != '-' && ((lastCode != code && (code != '0' || lastCode == '/')) || code < '0' || code > '8')) {
/* 387 */         output.addRight(code);
/*     */       }
/*     */       
/* 390 */       lastChar = chr;
/* 391 */       lastCode = code;
/*     */     } 
/* 393 */     return output.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object encode(Object object) throws EncoderException {
/* 398 */     if (!(object instanceof String)) {
/* 399 */       throw new EncoderException("This method's parameter was expected to be of the type " + String.class
/* 400 */           .getName() + ". But actually it was of the type " + object
/*     */           
/* 402 */           .getClass().getName() + ".");
/*     */     }
/*     */     
/* 405 */     return encode((String)object);
/*     */   }
/*     */ 
/*     */   
/*     */   public String encode(String text) {
/* 410 */     return colognePhonetic(text);
/*     */   }
/*     */   
/*     */   public boolean isEncodeEqual(String text1, String text2) {
/* 414 */     return colognePhonetic(text1).equals(colognePhonetic(text2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private char[] preprocess(String text) {
/* 422 */     char[] chrs = text.toUpperCase(Locale.GERMAN).toCharArray();
/*     */     
/* 424 */     for (int index = 0; index < chrs.length; index++) {
/* 425 */       switch (chrs[index]) {
/*     */         case 'Ä':
/* 427 */           chrs[index] = 'A';
/*     */           break;
/*     */         case 'Ü':
/* 430 */           chrs[index] = 'U';
/*     */           break;
/*     */         case 'Ö':
/* 433 */           chrs[index] = 'O';
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/* 439 */     return chrs;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/codec/language/ColognePhonetic.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */