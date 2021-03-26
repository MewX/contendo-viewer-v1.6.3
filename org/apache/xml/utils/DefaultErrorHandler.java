/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
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
/*     */ public class DefaultErrorHandler
/*     */   implements ErrorListener, ErrorHandler
/*     */ {
/*     */   PrintWriter m_pw;
/*     */   
/*     */   public DefaultErrorHandler(PrintWriter pw) {
/*  49 */     this.m_pw = pw;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultErrorHandler(PrintStream pw) {
/*  57 */     this.m_pw = new PrintWriter(pw, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultErrorHandler() {
/*  65 */     this.m_pw = new PrintWriter(System.err, true);
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
/*     */   public void warning(SAXParseException exception) throws SAXException {
/*  87 */     printLocation(this.m_pw, exception);
/*  88 */     this.m_pw.println("Parser warning: " + exception.getMessage());
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
/*     */   public void error(SAXParseException exception) throws SAXException {
/* 117 */     throw exception;
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
/*     */   public void fatalError(SAXParseException exception) throws SAXException {
/* 144 */     throw exception;
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
/*     */   public void warning(TransformerException exception) throws TransformerException {
/* 166 */     printLocation(this.m_pw, exception);
/*     */     
/* 168 */     this.m_pw.println(exception.getMessage());
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
/*     */   public void error(TransformerException exception) throws TransformerException {
/* 198 */     throw exception;
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
/*     */   public void fatalError(TransformerException exception) throws TransformerException {
/* 226 */     throw exception;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void ensureLocationSet(TransformerException exception) {
/* 232 */     SourceLocator locator = null;
/* 233 */     Throwable cause = exception;
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 238 */       if (cause instanceof SAXParseException) {
/*     */         
/* 240 */         locator = new SAXSourceLocator((SAXParseException)cause);
/*     */       }
/* 242 */       else if (cause instanceof TransformerException) {
/*     */         
/* 244 */         SourceLocator causeLocator = ((TransformerException)cause).getLocator();
/* 245 */         if (null != causeLocator) {
/* 246 */           locator = causeLocator;
/*     */         }
/*     */       } 
/* 249 */       if (cause instanceof TransformerException) {
/* 250 */         cause = ((TransformerException)cause).getCause();
/* 251 */       } else if (cause instanceof SAXException) {
/* 252 */         cause = ((SAXException)cause).getException();
/*     */       } else {
/* 254 */         cause = null;
/*     */       } 
/* 256 */     } while (null != cause);
/*     */     
/* 258 */     exception.setLocator(locator);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printLocation(PrintStream pw, TransformerException exception) {
/* 263 */     printLocation(new PrintWriter(pw), exception);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printLocation(PrintStream pw, SAXParseException exception) {
/* 268 */     printLocation(new PrintWriter(pw), exception);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void printLocation(PrintWriter pw, Throwable exception) {
/* 273 */     SourceLocator locator = null;
/* 274 */     Throwable cause = exception;
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 279 */       if (cause instanceof SAXParseException) {
/*     */         
/* 281 */         locator = new SAXSourceLocator((SAXParseException)cause);
/*     */       }
/* 283 */       else if (cause instanceof TransformerException) {
/*     */         
/* 285 */         SourceLocator causeLocator = ((TransformerException)cause).getLocator();
/* 286 */         if (null != causeLocator)
/* 287 */           locator = causeLocator; 
/*     */       } 
/* 289 */       if (cause instanceof TransformerException) {
/* 290 */         cause = ((TransformerException)cause).getCause();
/* 291 */       } else if (cause instanceof WrappedRuntimeException) {
/* 292 */         cause = ((WrappedRuntimeException)cause).getException();
/* 293 */       } else if (cause instanceof SAXException) {
/* 294 */         cause = ((SAXException)cause).getException();
/*     */       } else {
/* 296 */         cause = null;
/*     */       } 
/* 298 */     } while (null != cause);
/*     */     
/* 300 */     if (null != locator) {
/*     */ 
/*     */       
/* 303 */       String id = (null != locator.getPublicId()) ? locator.getPublicId() : ((null != locator.getSystemId()) ? locator.getSystemId() : XMLMessages.createXMLMessage("ER_SYSTEMID_UNKNOWN", null));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 308 */       pw.print(id + "; " + XMLMessages.createXMLMessage("line", null) + locator.getLineNumber() + "; " + XMLMessages.createXMLMessage("column", null) + locator.getColumnNumber() + "; ");
/*     */     }
/*     */     else {
/*     */       
/* 312 */       pw.print("(" + XMLMessages.createXMLMessage("ER_LOCATION_UNKNOWN", null) + ")");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/DefaultErrorHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */