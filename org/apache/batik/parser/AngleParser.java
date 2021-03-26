/*     */ package org.apache.batik.parser;
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
/*     */ public class AngleParser
/*     */   extends NumberParser
/*     */ {
/*  35 */   protected AngleHandler angleHandler = DefaultAngleHandler.INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAngleHandler(AngleHandler handler) {
/*  49 */     this.angleHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AngleHandler getAngleHandler() {
/*  56 */     return this.angleHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doParse() throws ParseException, IOException {
/*  63 */     this.angleHandler.startAngle();
/*     */     
/*  65 */     this.current = this.reader.read();
/*  66 */     skipSpaces();
/*     */     
/*     */     try {
/*  69 */       float f = parseFloat();
/*     */       
/*  71 */       this.angleHandler.angleValue(f);
/*     */       
/*  73 */       if (this.current != -1) {
/*  74 */         switch (this.current) { case 9: case 10:
/*     */           case 13:
/*     */           case 32:
/*     */             break;
/*     */           default:
/*  79 */             switch (this.current) {
/*     */               case 100:
/*  81 */                 this.current = this.reader.read();
/*  82 */                 if (this.current != 101) {
/*  83 */                   reportCharacterExpectedError('e', this.current);
/*     */                   break;
/*     */                 } 
/*  86 */                 this.current = this.reader.read();
/*  87 */                 if (this.current != 103) {
/*  88 */                   reportCharacterExpectedError('g', this.current);
/*     */                   break;
/*     */                 } 
/*  91 */                 this.angleHandler.deg();
/*  92 */                 this.current = this.reader.read();
/*     */                 break;
/*     */               case 103:
/*  95 */                 this.current = this.reader.read();
/*  96 */                 if (this.current != 114) {
/*  97 */                   reportCharacterExpectedError('r', this.current);
/*     */                   break;
/*     */                 } 
/* 100 */                 this.current = this.reader.read();
/* 101 */                 if (this.current != 97) {
/* 102 */                   reportCharacterExpectedError('a', this.current);
/*     */                   break;
/*     */                 } 
/* 105 */                 this.current = this.reader.read();
/* 106 */                 if (this.current != 100) {
/* 107 */                   reportCharacterExpectedError('d', this.current);
/*     */                   break;
/*     */                 } 
/* 110 */                 this.angleHandler.grad();
/* 111 */                 this.current = this.reader.read();
/*     */                 break;
/*     */               case 114:
/* 114 */                 this.current = this.reader.read();
/* 115 */                 if (this.current != 97) {
/* 116 */                   reportCharacterExpectedError('a', this.current);
/*     */                   break;
/*     */                 } 
/* 119 */                 this.current = this.reader.read();
/* 120 */                 if (this.current != 100) {
/* 121 */                   reportCharacterExpectedError('d', this.current);
/*     */                   break;
/*     */                 } 
/* 124 */                 this.angleHandler.rad();
/* 125 */                 this.current = this.reader.read();
/*     */                 break;
/*     */             } 
/* 128 */             reportUnexpectedCharacterError(this.current);
/*     */             break; }
/*     */       
/*     */       }
/* 132 */       skipSpaces();
/* 133 */       if (this.current != -1) {
/* 134 */         reportError("end.of.stream.expected", new Object[] { Integer.valueOf(this.current) });
/*     */       }
/*     */     }
/* 137 */     catch (NumberFormatException e) {
/* 138 */       reportUnexpectedCharacterError(this.current);
/*     */     } 
/* 140 */     this.angleHandler.endAngle();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/AngleParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */