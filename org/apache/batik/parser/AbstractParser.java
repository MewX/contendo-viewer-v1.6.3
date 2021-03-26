/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import org.apache.batik.i18n.LocalizableSupport;
/*     */ import org.apache.batik.util.io.NormalizingReader;
/*     */ import org.apache.batik.util.io.StreamNormalizingReader;
/*     */ import org.apache.batik.util.io.StringNormalizingReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractParser
/*     */   implements Parser
/*     */ {
/*     */   public static final String BUNDLE_CLASSNAME = "org.apache.batik.parser.resources.Messages";
/*  50 */   protected ErrorHandler errorHandler = new DefaultErrorHandler();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected LocalizableSupport localizableSupport = new LocalizableSupport("org.apache.batik.parser.resources.Messages", AbstractParser.class.getClassLoader());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected NormalizingReader reader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int current;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrent() {
/*  73 */     return this.current;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocale(Locale l) {
/*  80 */     this.localizableSupport.setLocale(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getLocale() {
/*  87 */     return this.localizableSupport.getLocale();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatMessage(String key, Object[] args) throws MissingResourceException {
/*  96 */     return this.localizableSupport.formatMessage(key, args);
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
/*     */   public void setErrorHandler(ErrorHandler handler) {
/* 112 */     this.errorHandler = handler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parse(Reader r) throws ParseException {
/*     */     try {
/* 120 */       this.reader = (NormalizingReader)new StreamNormalizingReader(r);
/* 121 */       doParse();
/* 122 */     } catch (IOException e) {
/* 123 */       this.errorHandler.error(new ParseException(createErrorMessage("io.exception", null), e));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parse(InputStream is, String enc) throws ParseException {
/*     */     try {
/* 135 */       this.reader = (NormalizingReader)new StreamNormalizingReader(is, enc);
/* 136 */       doParse();
/* 137 */     } catch (IOException e) {
/* 138 */       this.errorHandler.error(new ParseException(createErrorMessage("io.exception", null), e));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parse(String s) throws ParseException {
/*     */     try {
/* 149 */       this.reader = (NormalizingReader)new StringNormalizingReader(s);
/* 150 */       doParse();
/* 151 */     } catch (IOException e) {
/* 152 */       this.errorHandler.error(new ParseException(createErrorMessage("io.exception", null), e));
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
/*     */   protected abstract void doParse() throws ParseException, IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportError(String key, Object[] args) throws ParseException {
/* 172 */     this.errorHandler.error(new ParseException(createErrorMessage(key, args), this.reader.getLine(), this.reader.getColumn()));
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
/*     */   protected void reportCharacterExpectedError(char expectedChar, int currentChar) {
/* 185 */     reportError("character.expected", new Object[] { Character.valueOf(expectedChar), Integer.valueOf(currentChar) });
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
/*     */   protected void reportUnexpectedCharacterError(int currentChar) {
/* 198 */     reportError("character.unexpected", new Object[] { Integer.valueOf(currentChar) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String createErrorMessage(String key, Object[] args) {
/*     */     try {
/* 210 */       return formatMessage(key, args);
/* 211 */     } catch (MissingResourceException e) {
/* 212 */       return key;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getBundleClassName() {
/* 221 */     return "org.apache.batik.parser.resources.Messages";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void skipSpaces() throws IOException {
/*     */     while (true) {
/* 229 */       switch (this.current) {
/*     */         default:
/*     */           return;
/*     */         case 9:
/*     */         case 10:
/*     */         case 13:
/*     */         case 32:
/*     */           break;
/* 237 */       }  this.current = this.reader.read();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void skipCommaSpaces() throws IOException {
/*     */     while (true) {
/* 246 */       switch (this.current) {
/*     */         default:
/*     */           break;
/*     */         case 9:
/*     */         case 10:
/*     */         case 13:
/*     */         case 32:
/*     */           break;
/* 254 */       }  this.current = this.reader.read();
/*     */     } 
/* 256 */     if (this.current == 44)
/*     */       while (true) {
/* 258 */         switch (this.current = this.reader.read()) {
/*     */           case 9:
/*     */           case 10:
/*     */           case 13:
/*     */           case 32:
/*     */             break;
/*     */         } 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/AbstractParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */