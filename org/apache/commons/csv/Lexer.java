/*     */ package org.apache.commons.csv;
/*     */ 
/*     */ import java.io.Closeable;
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
/*     */ final class Lexer
/*     */   implements Closeable
/*     */ {
/*  41 */   private static final String CR_STRING = Character.toString('\r');
/*  42 */   private static final String LF_STRING = Character.toString('\n');
/*     */   
/*     */   private static final char DISABLED = '￾';
/*     */   
/*     */   private final char delimiter;
/*     */   
/*     */   private final char escape;
/*     */   
/*     */   private final char quoteChar;
/*     */   
/*     */   private final char commentStart;
/*     */   
/*     */   private final boolean ignoreSurroundingSpaces;
/*     */   
/*     */   private final boolean ignoreEmptyLines;
/*     */   
/*     */   private final ExtendedBufferedReader reader;
/*     */   
/*     */   private String firstEol;
/*     */ 
/*     */   
/*     */   String getFirstEol() {
/*  64 */     return this.firstEol;
/*     */   }
/*     */   
/*     */   Lexer(CSVFormat format, ExtendedBufferedReader reader) {
/*  68 */     this.reader = reader;
/*  69 */     this.delimiter = format.getDelimiter();
/*  70 */     this.escape = mapNullToDisabled(format.getEscapeCharacter());
/*  71 */     this.quoteChar = mapNullToDisabled(format.getQuoteCharacter());
/*  72 */     this.commentStart = mapNullToDisabled(format.getCommentMarker());
/*  73 */     this.ignoreSurroundingSpaces = format.getIgnoreSurroundingSpaces();
/*  74 */     this.ignoreEmptyLines = format.getIgnoreEmptyLines();
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
/*     */   Token nextToken(Token token) throws IOException {
/*  92 */     int lastChar = this.reader.getLastChar();
/*     */ 
/*     */     
/*  95 */     int c = this.reader.read();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     boolean eol = readEndOfLine(c);
/*     */ 
/*     */     
/* 103 */     if (this.ignoreEmptyLines) {
/* 104 */       while (eol && isStartOfLine(lastChar)) {
/*     */         
/* 106 */         lastChar = c;
/* 107 */         c = this.reader.read();
/* 108 */         eol = readEndOfLine(c);
/*     */         
/* 110 */         if (isEndOfFile(c)) {
/* 111 */           token.type = Token.Type.EOF;
/*     */           
/* 113 */           return token;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 119 */     if (isEndOfFile(lastChar) || (!isDelimiter(lastChar) && isEndOfFile(c))) {
/* 120 */       token.type = Token.Type.EOF;
/*     */       
/* 122 */       return token;
/*     */     } 
/*     */     
/* 125 */     if (isStartOfLine(lastChar) && isCommentStart(c)) {
/* 126 */       String line = this.reader.readLine();
/* 127 */       if (line == null) {
/* 128 */         token.type = Token.Type.EOF;
/*     */         
/* 130 */         return token;
/*     */       } 
/* 132 */       String comment = line.trim();
/* 133 */       token.content.append(comment);
/* 134 */       token.type = Token.Type.COMMENT;
/* 135 */       return token;
/*     */     } 
/*     */ 
/*     */     
/* 139 */     while (token.type == Token.Type.INVALID) {
/*     */       
/* 141 */       if (this.ignoreSurroundingSpaces) {
/* 142 */         while (isWhitespace(c) && !eol) {
/* 143 */           c = this.reader.read();
/* 144 */           eol = readEndOfLine(c);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 149 */       if (isDelimiter(c)) {
/*     */         
/* 151 */         token.type = Token.Type.TOKEN; continue;
/* 152 */       }  if (eol) {
/*     */ 
/*     */         
/* 155 */         token.type = Token.Type.EORECORD; continue;
/* 156 */       }  if (isQuoteChar(c)) {
/*     */         
/* 158 */         parseEncapsulatedToken(token); continue;
/* 159 */       }  if (isEndOfFile(c)) {
/*     */ 
/*     */         
/* 162 */         token.type = Token.Type.EOF;
/* 163 */         token.isReady = true;
/*     */         
/*     */         continue;
/*     */       } 
/* 167 */       parseSimpleToken(token, c);
/*     */     } 
/*     */     
/* 170 */     return token;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Token parseSimpleToken(Token token, int ch) throws IOException {
/*     */     while (true) {
/* 195 */       if (readEndOfLine(ch)) {
/* 196 */         token.type = Token.Type.EORECORD; break;
/*     */       } 
/* 198 */       if (isEndOfFile(ch)) {
/* 199 */         token.type = Token.Type.EOF;
/* 200 */         token.isReady = true; break;
/*     */       } 
/* 202 */       if (isDelimiter(ch)) {
/* 203 */         token.type = Token.Type.TOKEN; break;
/*     */       } 
/* 205 */       if (isEscape(ch)) {
/* 206 */         int unescaped = readEscape();
/* 207 */         if (unescaped == -1) {
/* 208 */           token.content.append((char)ch).append((char)this.reader.getLastChar());
/*     */         } else {
/* 210 */           token.content.append((char)unescaped);
/*     */         } 
/* 212 */         ch = this.reader.read(); continue;
/*     */       } 
/* 214 */       token.content.append((char)ch);
/* 215 */       ch = this.reader.read();
/*     */     } 
/*     */ 
/*     */     
/* 219 */     if (this.ignoreSurroundingSpaces) {
/* 220 */       trimTrailingSpaces(token.content);
/*     */     }
/*     */     
/* 223 */     return token;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Token parseEncapsulatedToken(Token token) throws IOException {
/* 248 */     long startLineNumber = getCurrentLineNumber();
/*     */     
/*     */     while (true) {
/* 251 */       int c = this.reader.read();
/*     */       
/* 253 */       if (isEscape(c)) {
/* 254 */         int unescaped = readEscape();
/* 255 */         if (unescaped == -1) {
/* 256 */           token.content.append((char)c).append((char)this.reader.getLastChar()); continue;
/*     */         } 
/* 258 */         token.content.append((char)unescaped); continue;
/*     */       } 
/* 260 */       if (isQuoteChar(c)) {
/* 261 */         if (isQuoteChar(this.reader.lookAhead())) {
/*     */           
/* 263 */           c = this.reader.read();
/* 264 */           token.content.append((char)c);
/*     */           continue;
/*     */         } 
/*     */         do {
/* 268 */           c = this.reader.read();
/* 269 */           if (isDelimiter(c)) {
/* 270 */             token.type = Token.Type.TOKEN;
/* 271 */             return token;
/* 272 */           }  if (isEndOfFile(c)) {
/* 273 */             token.type = Token.Type.EOF;
/* 274 */             token.isReady = true;
/* 275 */             return token;
/* 276 */           }  if (readEndOfLine(c))
/* 277 */           { token.type = Token.Type.EORECORD;
/* 278 */             return token; } 
/* 279 */         } while (isWhitespace(c));
/*     */         
/* 281 */         throw new IOException("(line " + getCurrentLineNumber() + ") invalid char between encapsulated token and delimiter");
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 286 */       if (isEndOfFile(c))
/*     */       {
/* 288 */         throw new IOException("(startline " + startLineNumber + ") EOF reached before encapsulated token finished");
/*     */       }
/*     */ 
/*     */       
/* 292 */       token.content.append((char)c);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private char mapNullToDisabled(Character c) {
/* 298 */     return (c == null) ? '￾' : c.charValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getCurrentLineNumber() {
/* 307 */     return this.reader.getCurrentLineNumber();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long getCharacterPosition() {
/* 316 */     return this.reader.getPosition();
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
/*     */   int readEscape() throws IOException {
/* 333 */     int ch = this.reader.read();
/* 334 */     switch (ch) {
/*     */       case 114:
/* 336 */         return 13;
/*     */       case 110:
/* 338 */         return 10;
/*     */       case 116:
/* 340 */         return 9;
/*     */       case 98:
/* 342 */         return 8;
/*     */       case 102:
/* 344 */         return 12;
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 12:
/*     */       case 13:
/* 350 */         return ch;
/*     */       case -1:
/* 352 */         throw new IOException("EOF whilst processing escape sequence");
/*     */     } 
/*     */     
/* 355 */     if (isMetaChar(ch)) {
/* 356 */       return ch;
/*     */     }
/*     */     
/* 359 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   void trimTrailingSpaces(StringBuilder buffer) {
/* 364 */     int length = buffer.length();
/* 365 */     while (length > 0 && Character.isWhitespace(buffer.charAt(length - 1))) {
/* 366 */       length--;
/*     */     }
/* 368 */     if (length != buffer.length()) {
/* 369 */       buffer.setLength(length);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean readEndOfLine(int ch) throws IOException {
/* 380 */     if (ch == 13 && this.reader.lookAhead() == 10) {
/*     */       
/* 382 */       ch = this.reader.read();
/*     */       
/* 384 */       if (this.firstEol == null) {
/* 385 */         this.firstEol = "\r\n";
/*     */       }
/*     */     } 
/*     */     
/* 389 */     if (this.firstEol == null) {
/* 390 */       if (ch == 10) {
/* 391 */         this.firstEol = LF_STRING;
/* 392 */       } else if (ch == 13) {
/* 393 */         this.firstEol = CR_STRING;
/*     */       } 
/*     */     }
/*     */     
/* 397 */     return (ch == 10 || ch == 13);
/*     */   }
/*     */   
/*     */   boolean isClosed() {
/* 401 */     return this.reader.isClosed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isWhitespace(int ch) {
/* 408 */     return (!isDelimiter(ch) && Character.isWhitespace((char)ch));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isStartOfLine(int ch) {
/* 418 */     return (ch == 10 || ch == 13 || ch == -2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEndOfFile(int ch) {
/* 425 */     return (ch == -1);
/*     */   }
/*     */   
/*     */   boolean isDelimiter(int ch) {
/* 429 */     return (ch == this.delimiter);
/*     */   }
/*     */   
/*     */   boolean isEscape(int ch) {
/* 433 */     return (ch == this.escape);
/*     */   }
/*     */   
/*     */   boolean isQuoteChar(int ch) {
/* 437 */     return (ch == this.quoteChar);
/*     */   }
/*     */   
/*     */   boolean isCommentStart(int ch) {
/* 441 */     return (ch == this.commentStart);
/*     */   }
/*     */   
/*     */   private boolean isMetaChar(int ch) {
/* 445 */     return (ch == this.delimiter || ch == this.escape || ch == this.quoteChar || ch == this.commentStart);
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
/*     */   public void close() throws IOException {
/* 459 */     this.reader.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/csv/Lexer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */