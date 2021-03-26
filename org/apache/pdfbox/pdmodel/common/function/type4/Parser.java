/*     */ package org.apache.pdfbox.pdmodel.common.function.type4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Parser
/*     */ {
/*     */   private enum State
/*     */   {
/*  30 */     NEWLINE, WHITESPACE, COMMENT, TOKEN;
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
/*     */   public static void parse(CharSequence input, SyntaxHandler handler) {
/*  46 */     Tokenizer tokenizer = new Tokenizer(input, handler);
/*  47 */     tokenizer.tokenize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface SyntaxHandler
/*     */   {
/*     */     void newLine(CharSequence param1CharSequence);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void whitespace(CharSequence param1CharSequence);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void token(CharSequence param1CharSequence);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void comment(CharSequence param1CharSequence);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class AbstractSyntaxHandler
/*     */     implements SyntaxHandler
/*     */   {
/*     */     public void comment(CharSequence text) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void newLine(CharSequence text) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void whitespace(CharSequence text) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class Tokenizer
/*     */   {
/*     */     private static final char NUL = '\000';
/*     */ 
/*     */ 
/*     */     
/*     */     private static final char EOT = '\004';
/*     */ 
/*     */ 
/*     */     
/*     */     private static final char TAB = '\t';
/*     */ 
/*     */     
/*     */     private static final char FF = '\f';
/*     */ 
/*     */     
/*     */     private static final char CR = '\r';
/*     */ 
/*     */     
/*     */     private static final char LF = '\n';
/*     */ 
/*     */     
/*     */     private static final char SPACE = ' ';
/*     */ 
/*     */     
/*     */     private final CharSequence input;
/*     */ 
/*     */     
/*     */     private int index;
/*     */ 
/*     */     
/*     */     private final Parser.SyntaxHandler handler;
/*     */ 
/*     */     
/* 129 */     private Parser.State state = Parser.State.WHITESPACE;
/* 130 */     private final StringBuilder buffer = new StringBuilder();
/*     */ 
/*     */     
/*     */     private Tokenizer(CharSequence text, Parser.SyntaxHandler syntaxHandler) {
/* 134 */       this.input = text;
/* 135 */       this.handler = syntaxHandler;
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean hasMore() {
/* 140 */       return (this.index < this.input.length());
/*     */     }
/*     */ 
/*     */     
/*     */     private char currentChar() {
/* 145 */       return this.input.charAt(this.index);
/*     */     }
/*     */ 
/*     */     
/*     */     private char nextChar() {
/* 150 */       this.index++;
/* 151 */       if (!hasMore())
/*     */       {
/* 153 */         return '\004';
/*     */       }
/*     */ 
/*     */       
/* 157 */       return currentChar();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private char peek() {
/* 163 */       if (this.index < this.input.length() - 1)
/*     */       {
/* 165 */         return this.input.charAt(this.index + 1);
/*     */       }
/*     */ 
/*     */       
/* 169 */       return '\004';
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Parser.State nextState() {
/* 175 */       char ch = currentChar();
/* 176 */       switch (ch)
/*     */       
/*     */       { case '\n':
/*     */         case '\f':
/*     */         case '\r':
/* 181 */           this.state = Parser.State.NEWLINE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 194 */           return this.state;case '\000': case '\t': case ' ': this.state = Parser.State.WHITESPACE; return this.state;case '%': this.state = Parser.State.COMMENT; return this.state; }  this.state = Parser.State.TOKEN; return this.state;
/*     */     }
/*     */ 
/*     */     
/*     */     private void tokenize() {
/* 199 */       while (hasMore()) {
/*     */         
/* 201 */         this.buffer.setLength(0);
/* 202 */         nextState();
/* 203 */         switch (this.state) {
/*     */           
/*     */           case NEWLINE:
/* 206 */             scanNewLine();
/*     */             continue;
/*     */           case WHITESPACE:
/* 209 */             scanWhitespace();
/*     */             continue;
/*     */           case COMMENT:
/* 212 */             scanComment();
/*     */             continue;
/*     */         } 
/* 215 */         scanToken();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private void scanNewLine() {
/* 222 */       assert this.state == Parser.State.NEWLINE;
/* 223 */       char ch = currentChar();
/* 224 */       this.buffer.append(ch);
/* 225 */       if (ch == '\r' && peek() == '\n')
/*     */       {
/*     */         
/* 228 */         this.buffer.append(nextChar());
/*     */       }
/* 230 */       this.handler.newLine(this.buffer);
/* 231 */       nextChar();
/*     */     }
/*     */ 
/*     */     
/*     */     private void scanWhitespace() {
/* 236 */       assert this.state == Parser.State.WHITESPACE;
/* 237 */       this.buffer.append(currentChar());
/*     */       
/* 239 */       while (hasMore()) {
/*     */         
/* 241 */         char ch = nextChar();
/* 242 */         switch (ch) {
/*     */           
/*     */           case '\000':
/*     */           case '\t':
/*     */           case ' ':
/* 247 */             this.buffer.append(ch);
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       } 
/* 253 */       this.handler.whitespace(this.buffer);
/*     */     }
/*     */ 
/*     */     
/*     */     private void scanComment() {
/* 258 */       assert this.state == Parser.State.COMMENT;
/* 259 */       this.buffer.append(currentChar());
/*     */       
/* 261 */       while (hasMore()) {
/*     */         
/* 263 */         char ch = nextChar();
/* 264 */         switch (ch) {
/*     */           case '\n':
/*     */           case '\f':
/*     */           case '\r':
/*     */             break;
/*     */         } 
/*     */         
/* 271 */         this.buffer.append(ch);
/*     */       } 
/*     */ 
/*     */       
/* 275 */       this.handler.comment(this.buffer);
/*     */     }
/*     */ 
/*     */     
/*     */     private void scanToken() {
/* 280 */       assert this.state == Parser.State.TOKEN;
/* 281 */       char ch = currentChar();
/* 282 */       this.buffer.append(ch);
/* 283 */       switch (ch) {
/*     */         
/*     */         case '{':
/*     */         case '}':
/* 287 */           this.handler.token(this.buffer);
/* 288 */           nextChar();
/*     */           return;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 294 */       while (hasMore()) {
/*     */         
/* 296 */         ch = nextChar();
/* 297 */         switch (ch) {
/*     */           case '\000':
/*     */           case '\004':
/*     */           case '\t':
/*     */           case '\n':
/*     */           case '\f':
/*     */           case '\r':
/*     */           case ' ':
/*     */           case '{':
/*     */           case '}':
/*     */             break;
/*     */         } 
/*     */         
/* 310 */         this.buffer.append(ch);
/*     */       } 
/*     */ 
/*     */       
/* 314 */       this.handler.token(this.buffer);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/type4/Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */