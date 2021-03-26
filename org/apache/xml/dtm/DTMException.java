/*     */ package org.apache.xml.dtm;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DTMException
/*     */   extends RuntimeException
/*     */ {
/*     */   SourceLocator locator;
/*     */   Throwable containedException;
/*     */   
/*     */   public SourceLocator getLocator() {
/*  48 */     return this.locator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocator(SourceLocator location) {
/*  58 */     this.locator = location;
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
/*     */   public Throwable getException() {
/*  72 */     return this.containedException;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Throwable getCause() {
/*  82 */     return (this.containedException == this) ? null : this.containedException;
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
/*     */   public synchronized Throwable initCause(Throwable cause) {
/* 113 */     if (this.containedException == null && cause != null) {
/* 114 */       throw new IllegalStateException(XMLMessages.createXMLMessage("ER_CANNOT_OVERWRITE_CAUSE", null));
/*     */     }
/*     */     
/* 117 */     if (cause == this) {
/* 118 */       throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_SELF_CAUSATION_NOT_PERMITTED", null));
/*     */     }
/*     */ 
/*     */     
/* 122 */     this.containedException = cause;
/*     */     
/* 124 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMException(String message) {
/* 134 */     super(message);
/*     */     
/* 136 */     this.containedException = null;
/* 137 */     this.locator = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMException(Throwable e) {
/* 147 */     super(e.getMessage());
/*     */     
/* 149 */     this.containedException = e;
/* 150 */     this.locator = null;
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
/*     */   public DTMException(String message, Throwable e) {
/* 165 */     super((message == null || message.length() == 0) ? e.getMessage() : message);
/*     */ 
/*     */ 
/*     */     
/* 169 */     this.containedException = e;
/* 170 */     this.locator = null;
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
/*     */   public DTMException(String message, SourceLocator locator) {
/* 185 */     super(message);
/*     */     
/* 187 */     this.containedException = null;
/* 188 */     this.locator = locator;
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
/*     */   public DTMException(String message, SourceLocator locator, Throwable e) {
/* 202 */     super(message);
/*     */     
/* 204 */     this.containedException = e;
/* 205 */     this.locator = locator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessageAndLocation() {
/* 214 */     StringBuffer sbuffer = new StringBuffer();
/* 215 */     String message = getMessage();
/*     */     
/* 217 */     if (null != message) {
/* 218 */       sbuffer.append(message);
/*     */     }
/*     */     
/* 221 */     if (null != this.locator) {
/* 222 */       String systemID = this.locator.getSystemId();
/* 223 */       int line = this.locator.getLineNumber();
/* 224 */       int column = this.locator.getColumnNumber();
/*     */       
/* 226 */       if (null != systemID) {
/* 227 */         sbuffer.append("; SystemID: ");
/* 228 */         sbuffer.append(systemID);
/*     */       } 
/*     */       
/* 231 */       if (0 != line) {
/* 232 */         sbuffer.append("; Line#: ");
/* 233 */         sbuffer.append(line);
/*     */       } 
/*     */       
/* 236 */       if (0 != column) {
/* 237 */         sbuffer.append("; Column#: ");
/* 238 */         sbuffer.append(column);
/*     */       } 
/*     */     } 
/*     */     
/* 242 */     return sbuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocationAsString() {
/* 253 */     if (null != this.locator) {
/* 254 */       StringBuffer sbuffer = new StringBuffer();
/* 255 */       String systemID = this.locator.getSystemId();
/* 256 */       int line = this.locator.getLineNumber();
/* 257 */       int column = this.locator.getColumnNumber();
/*     */       
/* 259 */       if (null != systemID) {
/* 260 */         sbuffer.append("; SystemID: ");
/* 261 */         sbuffer.append(systemID);
/*     */       } 
/*     */       
/* 264 */       if (0 != line) {
/* 265 */         sbuffer.append("; Line#: ");
/* 266 */         sbuffer.append(line);
/*     */       } 
/*     */       
/* 269 */       if (0 != column) {
/* 270 */         sbuffer.append("; Column#: ");
/* 271 */         sbuffer.append(column);
/*     */       } 
/*     */       
/* 274 */       return sbuffer.toString();
/*     */     } 
/* 276 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printStackTrace() {
/* 286 */     printStackTrace(new PrintWriter(System.err, true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printStackTrace(PrintStream s) {
/* 296 */     printStackTrace(new PrintWriter(s));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printStackTrace(PrintWriter s) {
/* 307 */     if (s == null) {
/* 308 */       s = new PrintWriter(System.err, true);
/*     */     }
/*     */ 
/*     */     
/* 312 */     try { String locInfo = getLocationAsString();
/*     */       
/* 314 */       if (null != locInfo) {
/* 315 */         s.println(locInfo);
/*     */       }
/*     */       
/* 318 */       super.printStackTrace(s); } catch (Throwable throwable) {}
/*     */ 
/*     */     
/* 321 */     Throwable exception = getException();
/*     */     
/* 323 */     for (int i = 0; i < 10 && null != exception; i++) {
/* 324 */       s.println("---------");
/*     */ 
/*     */       
/* 327 */       try { if (exception instanceof DTMException) {
/* 328 */           String locInfo = ((DTMException)exception).getLocationAsString();
/*     */ 
/*     */ 
/*     */           
/* 332 */           if (null != locInfo) {
/* 333 */             s.println(locInfo);
/*     */           }
/*     */         } 
/*     */         
/* 337 */         exception.printStackTrace(s); } catch (Throwable e)
/*     */       
/* 339 */       { s.println("Could not print stack trace..."); }
/*     */ 
/*     */ 
/*     */       
/* 343 */       try { Method meth = exception.getClass().getMethod("getException", null);
/*     */ 
/*     */ 
/*     */         
/* 347 */         if (null != meth)
/* 348 */         { Throwable prev = exception;
/*     */           
/* 350 */           exception = (Throwable)meth.invoke(exception, null);
/*     */           
/* 352 */           if (prev == exception) {
/*     */             break;
/*     */           } }
/*     */         else
/* 356 */         { exception = null; }  } catch (InvocationTargetException ite)
/*     */       
/*     */       { 
/* 359 */         exception = null; } catch (IllegalAccessException iae)
/*     */       
/* 361 */       { exception = null; } catch (NoSuchMethodException nsme)
/*     */       
/* 363 */       { exception = null; }
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/DTMException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */