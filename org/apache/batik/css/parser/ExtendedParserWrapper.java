/*     */ package org.apache.batik.css.parser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringReader;
/*     */ import java.util.Locale;
/*     */ import java.util.StringTokenizer;
/*     */ import org.w3c.css.sac.CSSException;
/*     */ import org.w3c.css.sac.ConditionFactory;
/*     */ import org.w3c.css.sac.DocumentHandler;
/*     */ import org.w3c.css.sac.ErrorHandler;
/*     */ import org.w3c.css.sac.InputSource;
/*     */ import org.w3c.css.sac.LexicalUnit;
/*     */ import org.w3c.css.sac.Parser;
/*     */ import org.w3c.css.sac.SACMediaList;
/*     */ import org.w3c.css.sac.SelectorFactory;
/*     */ import org.w3c.css.sac.SelectorList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtendedParserWrapper
/*     */   implements ExtendedParser
/*     */ {
/*     */   public Parser parser;
/*     */   
/*     */   public static ExtendedParser wrap(Parser p) {
/*  55 */     if (p instanceof ExtendedParser) {
/*  56 */       return (ExtendedParser)p;
/*     */     }
/*  58 */     return new ExtendedParserWrapper(p);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtendedParserWrapper(Parser parser) {
/*  65 */     this.parser = parser;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getParserVersion() {
/*  72 */     return this.parser.getParserVersion();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocale(Locale locale) throws CSSException {
/*  79 */     this.parser.setLocale(locale);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentHandler(DocumentHandler handler) {
/*  87 */     this.parser.setDocumentHandler(handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectorFactory(SelectorFactory selectorFactory) {
/*  95 */     this.parser.setSelectorFactory(selectorFactory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConditionFactory(ConditionFactory conditionFactory) {
/* 103 */     this.parser.setConditionFactory(conditionFactory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setErrorHandler(ErrorHandler handler) {
/* 111 */     this.parser.setErrorHandler(handler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseStyleSheet(InputSource source) throws CSSException, IOException {
/* 120 */     this.parser.parseStyleSheet(source);
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
/*     */   public void parseStyleSheet(String uri) throws CSSException, IOException {
/* 145 */     this.parser.parseStyleSheet(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseStyleDeclaration(InputSource source) throws CSSException, IOException {
/* 154 */     this.parser.parseStyleDeclaration(source);
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
/*     */   public void parseStyleDeclaration(String source) throws CSSException, IOException {
/* 169 */     this.parser.parseStyleDeclaration(new InputSource(new StringReader(source)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parseRule(InputSource source) throws CSSException, IOException {
/* 179 */     this.parser.parseRule(source);
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
/*     */   public void parseRule(String source) throws CSSException, IOException {
/* 192 */     this.parser.parseRule(new InputSource(new StringReader(source)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SelectorList parseSelectors(InputSource source) throws CSSException, IOException {
/* 200 */     return this.parser.parseSelectors(source);
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
/*     */   public SelectorList parseSelectors(String source) throws CSSException, IOException {
/* 215 */     return this.parser.parseSelectors(new InputSource(new StringReader(source)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LexicalUnit parsePropertyValue(InputSource source) throws CSSException, IOException {
/* 226 */     return this.parser.parsePropertyValue(source);
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
/*     */   public LexicalUnit parsePropertyValue(String source) throws CSSException, IOException {
/* 241 */     return this.parser.parsePropertyValue(new InputSource(new StringReader(source)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean parsePriority(InputSource source) throws CSSException, IOException {
/* 252 */     return this.parser.parsePriority(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SACMediaList parseMedia(String mediaText) throws CSSException, IOException {
/* 260 */     CSSSACMediaList result = new CSSSACMediaList();
/* 261 */     if (!"all".equalsIgnoreCase(mediaText)) {
/* 262 */       StringTokenizer st = new StringTokenizer(mediaText, " ,");
/* 263 */       while (st.hasMoreTokens()) {
/* 264 */         result.append(st.nextToken());
/*     */       }
/*     */     } 
/* 267 */     return result;
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
/*     */   public boolean parsePriority(String source) throws CSSException, IOException {
/* 282 */     return this.parser.parsePriority(new InputSource(new StringReader(source)));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/ExtendedParserWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */