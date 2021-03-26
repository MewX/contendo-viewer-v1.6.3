/*     */ package org.apache.fontbox.type1;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Type1Lexer
/*     */ {
/*  50 */   private static final Log LOG = LogFactory.getLog(Type1Lexer.class);
/*     */   
/*     */   private final ByteBuffer buffer;
/*     */   private Token aheadToken;
/*  54 */   private int openParens = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Type1Lexer(byte[] bytes) throws IOException {
/*  63 */     this.buffer = ByteBuffer.wrap(bytes);
/*  64 */     this.aheadToken = readToken(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Token nextToken() throws IOException {
/*  73 */     Token curToken = this.aheadToken;
/*     */     
/*  75 */     this.aheadToken = readToken(curToken);
/*  76 */     return curToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Token peekToken() {
/*  85 */     return this.aheadToken;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private char getChar() {
/*  93 */     return (char)this.buffer.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Token readToken(Token prevToken) throws IOException {
/*     */     while (true) {
/* 105 */       boolean skip = false;
/* 106 */       while (this.buffer.hasRemaining()) {
/*     */         
/* 108 */         char c = getChar();
/*     */ 
/*     */         
/* 111 */         if (c == '%') {
/*     */ 
/*     */           
/* 114 */           readComment(); continue;
/*     */         } 
/* 116 */         if (c == '(')
/*     */         {
/* 118 */           return readString();
/*     */         }
/* 120 */         if (c == ')')
/*     */         {
/*     */           
/* 123 */           throw new IOException("unexpected closing parenthesis");
/*     */         }
/* 125 */         if (c == '[')
/*     */         {
/* 127 */           return new Token(c, Token.START_ARRAY);
/*     */         }
/* 129 */         if (c == '{')
/*     */         {
/* 131 */           return new Token(c, Token.START_PROC);
/*     */         }
/* 133 */         if (c == ']')
/*     */         {
/* 135 */           return new Token(c, Token.END_ARRAY);
/*     */         }
/* 137 */         if (c == '}')
/*     */         {
/* 139 */           return new Token(c, Token.END_PROC);
/*     */         }
/* 141 */         if (c == '/')
/*     */         {
/* 143 */           return new Token(readRegular(), Token.LITERAL);
/*     */         }
/* 145 */         if (c == '<') {
/*     */           
/* 147 */           char c2 = getChar();
/* 148 */           if (c2 == c)
/*     */           {
/* 150 */             return new Token("<<", Token.START_DICT);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 155 */           this.buffer.position(this.buffer.position() - 1);
/* 156 */           return new Token(c, Token.NAME);
/*     */         } 
/*     */         
/* 159 */         if (c == '>') {
/*     */           
/* 161 */           char c2 = getChar();
/* 162 */           if (c2 == c)
/*     */           {
/* 164 */             return new Token(">>", Token.END_DICT);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 169 */           this.buffer.position(this.buffer.position() - 1);
/* 170 */           return new Token(c, Token.NAME);
/*     */         } 
/*     */         
/* 173 */         if (Character.isWhitespace(c)) {
/*     */           
/* 175 */           skip = true; continue;
/*     */         } 
/* 177 */         if (c == '\000') {
/*     */           
/* 179 */           LOG.warn("NULL byte in font, skipped");
/* 180 */           skip = true;
/*     */           
/*     */           continue;
/*     */         } 
/* 184 */         this.buffer.position(this.buffer.position() - 1);
/*     */ 
/*     */         
/* 187 */         Token number = tryReadNumber();
/* 188 */         if (number != null)
/*     */         {
/* 190 */           return number;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 195 */         String name = readRegular();
/* 196 */         if (name == null)
/*     */         {
/*     */           
/* 199 */           throw new DamagedFontException("Could not read token at position " + this.buffer
/* 200 */               .position());
/*     */         }
/*     */         
/* 203 */         if (name.equals("RD") || name.equals("-|")) {
/*     */ 
/*     */           
/* 206 */           if (prevToken.getKind() == Token.INTEGER)
/*     */           {
/* 208 */             return readCharString(prevToken.intValue());
/*     */           }
/*     */ 
/*     */           
/* 212 */           throw new IOException("expected INTEGER before -| or RD");
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 217 */         return new Token(name, Token.NAME);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 223 */       if (!skip) {
/* 224 */         return null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Token tryReadNumber() {
/* 232 */     this.buffer.mark();
/*     */     
/* 234 */     StringBuilder sb = new StringBuilder();
/* 235 */     StringBuilder radix = null;
/* 236 */     char c = getChar();
/* 237 */     boolean hasDigit = false;
/*     */ 
/*     */     
/* 240 */     if (c == '+' || c == '-') {
/*     */       
/* 242 */       sb.append(c);
/* 243 */       c = getChar();
/*     */     } 
/*     */ 
/*     */     
/* 247 */     while (Character.isDigit(c)) {
/*     */       
/* 249 */       sb.append(c);
/* 250 */       c = getChar();
/* 251 */       hasDigit = true;
/*     */     } 
/*     */ 
/*     */     
/* 255 */     if (c == '.') {
/*     */       
/* 257 */       sb.append(c);
/* 258 */       c = getChar();
/*     */     }
/* 260 */     else if (c == '#') {
/*     */ 
/*     */       
/* 263 */       radix = sb;
/* 264 */       sb = new StringBuilder();
/* 265 */       c = getChar();
/*     */     } else {
/* 267 */       if (sb.length() == 0 || !hasDigit) {
/*     */ 
/*     */         
/* 270 */         this.buffer.reset();
/* 271 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 276 */       this.buffer.position(this.buffer.position() - 1);
/* 277 */       return new Token(sb.toString(), Token.INTEGER);
/*     */     } 
/*     */ 
/*     */     
/* 281 */     if (Character.isDigit(c)) {
/*     */       
/* 283 */       sb.append(c);
/* 284 */       c = getChar();
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 289 */       this.buffer.reset();
/* 290 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 294 */     while (Character.isDigit(c)) {
/*     */       
/* 296 */       sb.append(c);
/* 297 */       c = getChar();
/*     */     } 
/*     */ 
/*     */     
/* 301 */     if (c == 'E') {
/*     */       
/* 303 */       sb.append(c);
/* 304 */       c = getChar();
/*     */ 
/*     */       
/* 307 */       if (c == '-') {
/*     */         
/* 309 */         sb.append(c);
/* 310 */         c = getChar();
/*     */       } 
/*     */ 
/*     */       
/* 314 */       if (Character.isDigit(c)) {
/*     */         
/* 316 */         sb.append(c);
/* 317 */         c = getChar();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 322 */         this.buffer.reset();
/* 323 */         return null;
/*     */       } 
/*     */ 
/*     */       
/* 327 */       while (Character.isDigit(c)) {
/*     */         
/* 329 */         sb.append(c);
/* 330 */         c = getChar();
/*     */       } 
/*     */     } 
/*     */     
/* 334 */     this.buffer.position(this.buffer.position() - 1);
/* 335 */     if (radix != null) {
/*     */       
/* 337 */       Integer val = Integer.valueOf(Integer.parseInt(sb.toString(), Integer.parseInt(radix.toString())));
/* 338 */       return new Token(val.toString(), Token.INTEGER);
/*     */     } 
/* 340 */     return new Token(sb.toString(), Token.REAL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String readRegular() {
/* 349 */     StringBuilder sb = new StringBuilder();
/* 350 */     while (this.buffer.hasRemaining()) {
/*     */       
/* 352 */       this.buffer.mark();
/* 353 */       char c = getChar();
/* 354 */       if (Character.isWhitespace(c) || c == '(' || c == ')' || c == '<' || c == '>' || c == '[' || c == ']' || c == '{' || c == '}' || c == '/' || c == '%') {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 361 */         this.buffer.reset();
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 366 */       sb.append(c);
/*     */     } 
/*     */     
/* 369 */     String regular = sb.toString();
/* 370 */     if (regular.length() == 0)
/*     */     {
/* 372 */       return null;
/*     */     }
/* 374 */     return regular;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String readComment() {
/* 382 */     StringBuilder sb = new StringBuilder();
/* 383 */     while (this.buffer.hasRemaining()) {
/*     */       
/* 385 */       char c = getChar();
/* 386 */       if (c == '\r' || c == '\n') {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 392 */       sb.append(c);
/*     */     } 
/*     */     
/* 395 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Token readString() {
/* 403 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 405 */     while (this.buffer.hasRemaining()) {
/*     */       
/* 407 */       char c1, c = getChar();
/*     */ 
/*     */       
/* 410 */       switch (c) {
/*     */         
/*     */         case '(':
/* 413 */           this.openParens++;
/* 414 */           sb.append('(');
/*     */           continue;
/*     */         case ')':
/* 417 */           if (this.openParens == 0)
/*     */           {
/*     */             
/* 420 */             return new Token(sb.toString(), Token.STRING);
/*     */           }
/* 422 */           sb.append(')');
/* 423 */           this.openParens--;
/*     */           continue;
/*     */         
/*     */         case '\\':
/* 427 */           c1 = getChar();
/* 428 */           switch (c1) {
/*     */             case 'n':
/*     */             case 'r':
/* 431 */               sb.append("\n"); break;
/* 432 */             case 't': sb.append('\t'); break;
/* 433 */             case 'b': sb.append('\b'); break;
/* 434 */             case 'f': sb.append('\f'); break;
/* 435 */             case '\\': sb.append('\\'); break;
/* 436 */             case '(': sb.append('('); break;
/* 437 */             case ')': sb.append(')');
/*     */               break;
/*     */           } 
/* 440 */           if (Character.isDigit(c1)) {
/*     */             
/* 442 */             String num = String.valueOf(new char[] { c1, getChar(), getChar() });
/* 443 */             Integer code = Integer.valueOf(Integer.parseInt(num, 8));
/* 444 */             sb.append((char)code.intValue());
/*     */           } 
/*     */           continue;
/*     */         case '\n':
/*     */         case '\r':
/* 449 */           sb.append("\n");
/*     */           continue;
/*     */       } 
/* 452 */       sb.append(c);
/*     */     } 
/*     */ 
/*     */     
/* 456 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Token readCharString(int length) {
/* 464 */     this.buffer.get();
/* 465 */     byte[] data = new byte[length];
/* 466 */     this.buffer.get(data);
/* 467 */     return new Token(data, Token.CHARSTRING);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/type1/Type1Lexer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */