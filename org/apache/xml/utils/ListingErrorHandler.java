/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ import org.xml.sax.ErrorHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.SAXParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListingErrorHandler
/*     */   implements ErrorListener, ErrorHandler
/*     */ {
/*  54 */   protected PrintWriter m_pw = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean throwOnWarning;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean throwOnError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean throwOnFatalError;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void warning(SAXParseException exception) throws SAXException {
/* 102 */     logExceptionLocation(this.m_pw, exception);
/*     */ 
/*     */     
/* 105 */     this.m_pw.println("warning: " + exception.getMessage());
/* 106 */     this.m_pw.flush();
/*     */     
/* 108 */     if (getThrowOnWarning()) {
/* 109 */       throw exception;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(SAXParseException exception) throws SAXException {
/* 141 */     logExceptionLocation(this.m_pw, exception);
/* 142 */     this.m_pw.println("error: " + exception.getMessage());
/* 143 */     this.m_pw.flush();
/*     */     
/* 145 */     if (getThrowOnError()) {
/* 146 */       throw exception;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatalError(SAXParseException exception) throws SAXException {
/* 173 */     logExceptionLocation(this.m_pw, exception);
/* 174 */     this.m_pw.println("fatalError: " + exception.getMessage());
/* 175 */     this.m_pw.flush();
/*     */     
/* 177 */     if (getThrowOnFatalError()) {
/* 178 */       throw exception;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void warning(TransformerException exception) throws TransformerException {
/* 206 */     logExceptionLocation(this.m_pw, exception);
/* 207 */     this.m_pw.println("warning: " + exception.getMessage());
/* 208 */     this.m_pw.flush();
/*     */     
/* 210 */     if (getThrowOnWarning()) {
/* 211 */       throw exception;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(TransformerException exception) throws TransformerException {
/* 233 */     logExceptionLocation(this.m_pw, exception);
/* 234 */     this.m_pw.println("error: " + exception.getMessage());
/* 235 */     this.m_pw.flush();
/*     */     
/* 237 */     if (getThrowOnError()) {
/* 238 */       throw exception;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fatalError(TransformerException exception) throws TransformerException {
/* 261 */     logExceptionLocation(this.m_pw, exception);
/* 262 */     this.m_pw.println("error: " + exception.getMessage());
/* 263 */     this.m_pw.flush();
/*     */     
/* 265 */     if (getThrowOnError()) {
/* 266 */       throw exception;
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
/*     */   public static void logExceptionLocation(PrintWriter pw, Throwable exception) {
/* 284 */     if (null == pw) {
/* 285 */       pw = new PrintWriter(System.err, true);
/*     */     }
/* 287 */     SourceLocator locator = null;
/* 288 */     Throwable cause = exception;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 294 */       if (cause instanceof SAXParseException) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 301 */         locator = new SAXSourceLocator((SAXParseException)cause);
/*     */       }
/* 303 */       else if (cause instanceof TransformerException) {
/*     */         
/* 305 */         SourceLocator causeLocator = ((TransformerException)cause).getLocator();
/* 306 */         if (null != causeLocator)
/*     */         {
/* 308 */           locator = causeLocator;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 313 */       if (cause instanceof TransformerException) {
/* 314 */         cause = ((TransformerException)cause).getCause();
/* 315 */       } else if (cause instanceof WrappedRuntimeException) {
/* 316 */         cause = ((WrappedRuntimeException)cause).getException();
/* 317 */       } else if (cause instanceof SAXException) {
/* 318 */         cause = ((SAXException)cause).getException();
/*     */       } else {
/* 320 */         cause = null;
/*     */       } 
/* 322 */     } while (null != cause);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 327 */     if (null != locator) {
/*     */       
/* 329 */       String id = (locator.getPublicId() != locator.getPublicId()) ? locator.getPublicId() : ((null != locator.getSystemId()) ? locator.getSystemId() : "SystemId-Unknown");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 334 */       pw.print(id + ":Line=" + locator.getLineNumber() + ";Column=" + locator.getColumnNumber() + ": ");
/*     */       
/* 336 */       pw.println("exception:" + exception.getMessage());
/* 337 */       pw.println("root-cause:" + ((null != cause) ? cause.getMessage() : "null"));
/*     */       
/* 339 */       logSourceLine(pw, locator);
/*     */     }
/*     */     else {
/*     */       
/* 343 */       pw.print("SystemId-Unknown:locator-unavailable: ");
/* 344 */       pw.println("exception:" + exception.getMessage());
/* 345 */       pw.println("root-cause:" + ((null != cause) ? cause.getMessage() : "null"));
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
/*     */   public static void logSourceLine(PrintWriter pw, SourceLocator locator) {
/* 362 */     if (null == locator) {
/*     */       return;
/*     */     }
/* 365 */     if (null == pw) {
/* 366 */       pw = new PrintWriter(System.err, true);
/*     */     }
/* 368 */     String url = locator.getSystemId();
/*     */ 
/*     */ 
/*     */     
/* 372 */     if (null == url) {
/*     */       
/* 374 */       pw.println("line: (No systemId; cannot read file)");
/* 375 */       pw.println();
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 383 */     try { int line = locator.getLineNumber();
/* 384 */       int column = locator.getColumnNumber();
/* 385 */       pw.println("line: " + getSourceLine(url, line));
/* 386 */       StringBuffer buf = new StringBuffer("line: ");
/* 387 */       for (int i = 1; i < column; i++)
/*     */       {
/* 389 */         buf.append(' ');
/*     */       }
/* 391 */       buf.append('^');
/* 392 */       pw.println(buf.toString()); } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 396 */       pw.println("line: logSourceLine unavailable due to: " + e.getMessage());
/* 397 */       pw.println(); }
/*     */   
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
/*     */   protected static String getSourceLine(String sourceUrl, int lineNum) throws Exception {
/* 411 */     URL url = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 416 */     try { url = new URL(sourceUrl); } catch (MalformedURLException mue)
/*     */     
/*     */     { 
/*     */       
/* 420 */       int indexOfColon = sourceUrl.indexOf(':');
/* 421 */       int indexOfSlash = sourceUrl.indexOf('/');
/*     */       
/* 423 */       if (indexOfColon != -1 && indexOfSlash != -1 && indexOfColon < indexOfSlash)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 429 */         throw mue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 434 */       url = new URL(SystemIDResolver.getAbsoluteURI(sourceUrl)); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 439 */     String line = null;
/* 440 */     InputStream is = null;
/* 441 */     BufferedReader br = null;
/*     */ 
/*     */     
/*     */     try {
/* 445 */       URLConnection uc = url.openConnection();
/* 446 */       is = uc.getInputStream();
/* 447 */       br = new BufferedReader(new InputStreamReader(is));
/*     */ 
/*     */ 
/*     */       
/* 451 */       for (int i = 1; i <= lineNum; i++)
/*     */       {
/* 453 */         line = br.readLine();
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */       
/* 461 */       br.close();
/* 462 */       is.close();
/*     */     } 
/*     */ 
/*     */     
/* 466 */     return line;
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
/*     */   public void setThrowOnWarning(boolean b) {
/* 484 */     this.throwOnWarning = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getThrowOnWarning() {
/* 494 */     return this.throwOnWarning;
/*     */   }
/*     */   
/*     */   public ListingErrorHandler(PrintWriter pw) {
/* 498 */     this.throwOnWarning = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 530 */     this.throwOnError = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 563 */     this.throwOnFatalError = true; if (null == pw) throw new NullPointerException(XMLMessages.createXMLMessage("ER_ERRORHANDLER_CREATED_WITH_NULL_PRINTWRITER", null));  this.m_pw = pw; } public ListingErrorHandler() { this.throwOnWarning = false; this.throwOnError = true; this.throwOnFatalError = true;
/*     */     this.m_pw = new PrintWriter(System.err, true); }
/*     */ 
/*     */   
/*     */   public void setThrowOnError(boolean b) {
/*     */     this.throwOnError = b;
/*     */   }
/*     */   
/*     */   public boolean getThrowOnError() {
/*     */     return this.throwOnError;
/*     */   }
/*     */   
/*     */   public void setThrowOnFatalError(boolean b) {
/*     */     this.throwOnFatalError = b;
/*     */   }
/*     */   
/*     */   public boolean getThrowOnFatalError() {
/*     */     return this.throwOnFatalError;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/ListingErrorHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */