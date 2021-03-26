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
/*     */ public class Metaphone
/*     */   implements StringEncoder
/*     */ {
/*     */   private static final String VOWELS = "AEIOU";
/*     */   private static final String FRONTV = "EIY";
/*     */   private static final String VARSON = "CSPTG";
/*  73 */   private int maxCodeLen = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String metaphone(String txt) {
/*  93 */     boolean hard = false;
/*     */     int txtLength;
/*  95 */     if (txt == null || (txtLength = txt.length()) == 0) {
/*  96 */       return "";
/*     */     }
/*     */     
/*  99 */     if (txtLength == 1) {
/* 100 */       return txt.toUpperCase(Locale.ENGLISH);
/*     */     }
/*     */     
/* 103 */     char[] inwd = txt.toUpperCase(Locale.ENGLISH).toCharArray();
/*     */     
/* 105 */     StringBuilder local = new StringBuilder(40);
/* 106 */     StringBuilder code = new StringBuilder(10);
/*     */     
/* 108 */     switch (inwd[0]) {
/*     */       case 'G':
/*     */       case 'K':
/*     */       case 'P':
/* 112 */         if (inwd[1] == 'N') {
/* 113 */           local.append(inwd, 1, inwd.length - 1); break;
/*     */         } 
/* 115 */         local.append(inwd);
/*     */         break;
/*     */       
/*     */       case 'A':
/* 119 */         if (inwd[1] == 'E') {
/* 120 */           local.append(inwd, 1, inwd.length - 1); break;
/*     */         } 
/* 122 */         local.append(inwd);
/*     */         break;
/*     */       
/*     */       case 'W':
/* 126 */         if (inwd[1] == 'R') {
/* 127 */           local.append(inwd, 1, inwd.length - 1);
/*     */           break;
/*     */         } 
/* 130 */         if (inwd[1] == 'H') {
/* 131 */           local.append(inwd, 1, inwd.length - 1);
/* 132 */           local.setCharAt(0, 'W'); break;
/*     */         } 
/* 134 */         local.append(inwd);
/*     */         break;
/*     */       
/*     */       case 'X':
/* 138 */         inwd[0] = 'S';
/* 139 */         local.append(inwd);
/*     */         break;
/*     */       default:
/* 142 */         local.append(inwd);
/*     */         break;
/*     */     } 
/* 145 */     int wdsz = local.length();
/* 146 */     int n = 0;
/*     */     
/* 148 */     while (code.length() < getMaxCodeLen() && n < wdsz) {
/*     */       
/* 150 */       char symb = local.charAt(n);
/*     */       
/* 152 */       if (symb != 'C' && isPreviousChar(local, n, symb)) {
/* 153 */         n++;
/*     */       } else {
/* 155 */         switch (symb) {
/*     */           case 'A':
/*     */           case 'E':
/*     */           case 'I':
/*     */           case 'O':
/*     */           case 'U':
/* 161 */             if (n == 0) {
/* 162 */               code.append(symb);
/*     */             }
/*     */             break;
/*     */           case 'B':
/* 166 */             if (isPreviousChar(local, n, 'M') && 
/* 167 */               isLastChar(wdsz, n)) {
/*     */               break;
/*     */             }
/* 170 */             code.append(symb);
/*     */             break;
/*     */           
/*     */           case 'C':
/* 174 */             if (isPreviousChar(local, n, 'S') && 
/* 175 */               !isLastChar(wdsz, n) && "EIY"
/* 176 */               .indexOf(local.charAt(n + 1)) >= 0) {
/*     */               break;
/*     */             }
/* 179 */             if (regionMatch(local, n, "CIA")) {
/* 180 */               code.append('X');
/*     */               break;
/*     */             } 
/* 183 */             if (!isLastChar(wdsz, n) && "EIY"
/* 184 */               .indexOf(local.charAt(n + 1)) >= 0) {
/* 185 */               code.append('S');
/*     */               break;
/*     */             } 
/* 188 */             if (isPreviousChar(local, n, 'S') && 
/* 189 */               isNextChar(local, n, 'H')) {
/* 190 */               code.append('K');
/*     */               break;
/*     */             } 
/* 193 */             if (isNextChar(local, n, 'H')) {
/* 194 */               if (n == 0 && wdsz >= 3 && 
/*     */                 
/* 196 */                 isVowel(local, 2)) {
/* 197 */                 code.append('K'); break;
/*     */               } 
/* 199 */               code.append('X');
/*     */               break;
/*     */             } 
/* 202 */             code.append('K');
/*     */             break;
/*     */           
/*     */           case 'D':
/* 206 */             if (!isLastChar(wdsz, n + 1) && 
/* 207 */               isNextChar(local, n, 'G') && "EIY"
/* 208 */               .indexOf(local.charAt(n + 2)) >= 0) {
/* 209 */               code.append('J'); n += 2; break;
/*     */             } 
/* 211 */             code.append('T');
/*     */             break;
/*     */           
/*     */           case 'G':
/* 215 */             if (isLastChar(wdsz, n + 1) && 
/* 216 */               isNextChar(local, n, 'H')) {
/*     */               break;
/*     */             }
/* 219 */             if (!isLastChar(wdsz, n + 1) && 
/* 220 */               isNextChar(local, n, 'H') && 
/* 221 */               !isVowel(local, n + 2)) {
/*     */               break;
/*     */             }
/* 224 */             if (n > 0 && (
/* 225 */               regionMatch(local, n, "GN") || 
/* 226 */               regionMatch(local, n, "GNED"))) {
/*     */               break;
/*     */             }
/* 229 */             if (isPreviousChar(local, n, 'G')) {
/*     */               
/* 231 */               hard = true;
/*     */             } else {
/* 233 */               hard = false;
/*     */             } 
/* 235 */             if (!isLastChar(wdsz, n) && "EIY"
/* 236 */               .indexOf(local.charAt(n + 1)) >= 0 && !hard) {
/*     */               
/* 238 */               code.append('J'); break;
/*     */             } 
/* 240 */             code.append('K');
/*     */             break;
/*     */           
/*     */           case 'H':
/* 244 */             if (isLastChar(wdsz, n)) {
/*     */               break;
/*     */             }
/* 247 */             if (n > 0 && "CSPTG"
/* 248 */               .indexOf(local.charAt(n - 1)) >= 0) {
/*     */               break;
/*     */             }
/* 251 */             if (isVowel(local, n + 1)) {
/* 252 */               code.append('H');
/*     */             }
/*     */             break;
/*     */           case 'F':
/*     */           case 'J':
/*     */           case 'L':
/*     */           case 'M':
/*     */           case 'N':
/*     */           case 'R':
/* 261 */             code.append(symb);
/*     */             break;
/*     */           case 'K':
/* 264 */             if (n > 0) {
/* 265 */               if (!isPreviousChar(local, n, 'C'))
/* 266 */                 code.append(symb); 
/*     */               break;
/*     */             } 
/* 269 */             code.append(symb);
/*     */             break;
/*     */           
/*     */           case 'P':
/* 273 */             if (isNextChar(local, n, 'H')) {
/*     */               
/* 275 */               code.append('F'); break;
/*     */             } 
/* 277 */             code.append(symb);
/*     */             break;
/*     */           
/*     */           case 'Q':
/* 281 */             code.append('K');
/*     */             break;
/*     */           case 'S':
/* 284 */             if (regionMatch(local, n, "SH") || 
/* 285 */               regionMatch(local, n, "SIO") || 
/* 286 */               regionMatch(local, n, "SIA")) {
/* 287 */               code.append('X'); break;
/*     */             } 
/* 289 */             code.append('S');
/*     */             break;
/*     */           
/*     */           case 'T':
/* 293 */             if (regionMatch(local, n, "TIA") || 
/* 294 */               regionMatch(local, n, "TIO")) {
/* 295 */               code.append('X');
/*     */               break;
/*     */             } 
/* 298 */             if (regionMatch(local, n, "TCH")) {
/*     */               break;
/*     */             }
/*     */ 
/*     */             
/* 303 */             if (regionMatch(local, n, "TH")) {
/* 304 */               code.append('0'); break;
/*     */             } 
/* 306 */             code.append('T');
/*     */             break;
/*     */           
/*     */           case 'V':
/* 310 */             code.append('F'); break;
/*     */           case 'W':
/*     */           case 'Y':
/* 313 */             if (!isLastChar(wdsz, n) && 
/* 314 */               isVowel(local, n + 1)) {
/* 315 */               code.append(symb);
/*     */             }
/*     */             break;
/*     */           case 'X':
/* 319 */             code.append('K');
/* 320 */             code.append('S');
/*     */             break;
/*     */           case 'Z':
/* 323 */             code.append('S');
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 329 */         n++;
/*     */       } 
/* 331 */       if (code.length() > getMaxCodeLen()) {
/* 332 */         code.setLength(getMaxCodeLen());
/*     */       }
/*     */     } 
/* 335 */     return code.toString();
/*     */   }
/*     */   
/*     */   private boolean isVowel(StringBuilder string, int index) {
/* 339 */     return ("AEIOU".indexOf(string.charAt(index)) >= 0);
/*     */   }
/*     */   
/*     */   private boolean isPreviousChar(StringBuilder string, int index, char c) {
/* 343 */     boolean matches = false;
/* 344 */     if (index > 0 && index < string
/* 345 */       .length()) {
/* 346 */       matches = (string.charAt(index - 1) == c);
/*     */     }
/* 348 */     return matches;
/*     */   }
/*     */   
/*     */   private boolean isNextChar(StringBuilder string, int index, char c) {
/* 352 */     boolean matches = false;
/* 353 */     if (index >= 0 && index < string
/* 354 */       .length() - 1) {
/* 355 */       matches = (string.charAt(index + 1) == c);
/*     */     }
/* 357 */     return matches;
/*     */   }
/*     */   
/*     */   private boolean regionMatch(StringBuilder string, int index, String test) {
/* 361 */     boolean matches = false;
/* 362 */     if (index >= 0 && index + test
/* 363 */       .length() - 1 < string.length()) {
/* 364 */       String substring = string.substring(index, index + test.length());
/* 365 */       matches = substring.equals(test);
/*     */     } 
/* 367 */     return matches;
/*     */   }
/*     */   
/*     */   private boolean isLastChar(int wdsz, int n) {
/* 371 */     return (n + 1 == wdsz);
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
/*     */   
/*     */   public Object encode(Object obj) throws EncoderException {
/* 389 */     if (!(obj instanceof String)) {
/* 390 */       throw new EncoderException("Parameter supplied to Metaphone encode is not of type java.lang.String");
/*     */     }
/* 392 */     return metaphone((String)obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String encode(String str) {
/* 403 */     return metaphone(str);
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
/*     */   public boolean isMetaphoneEqual(String str1, String str2) {
/* 415 */     return metaphone(str1).equals(metaphone(str2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxCodeLen() {
/* 422 */     return this.maxCodeLen;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxCodeLen(int maxCodeLen) {
/* 428 */     this.maxCodeLen = maxCodeLen;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/codec/language/Metaphone.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */