/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.processor.StylesheetHandler;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xml.utils.FastStringBuffer;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.StringBufferPool;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AVT
/*     */   implements Serializable, XSLTVisitable
/*     */ {
/*  45 */   private String m_simpleString = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private Vector m_parts = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private String m_rawName;
/*     */ 
/*     */   
/*     */   private String m_name;
/*     */ 
/*     */   
/*     */   private String m_uri;
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRawName() {
/*  66 */     return this.m_rawName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRawName(String rawName) {
/*  76 */     this.m_rawName = rawName;
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
/*     */   public String getName() {
/*  92 */     return this.m_name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 102 */     this.m_name = name;
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
/*     */   public String getURI() {
/* 118 */     return this.m_uri;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURI(String uri) {
/* 128 */     this.m_uri = uri;
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
/*     */   public AVT(StylesheetHandler handler, String uri, String name, String rawName, String stringedValue, ElemTemplateElement owner) throws TransformerException {
/* 150 */     this.m_uri = uri;
/* 151 */     this.m_name = name;
/* 152 */     this.m_rawName = rawName;
/*     */     
/* 154 */     StringTokenizer tokenizer = new StringTokenizer(stringedValue, "{}\"'", true);
/*     */     
/* 156 */     int nTokens = tokenizer.countTokens();
/*     */     
/* 158 */     if (nTokens < 2) {
/*     */       
/* 160 */       this.m_simpleString = stringedValue;
/*     */     }
/*     */     else {
/*     */       
/* 164 */       FastStringBuffer buffer = StringBufferPool.get();
/* 165 */       FastStringBuffer exprBuffer = StringBufferPool.get();
/*     */ 
/*     */       
/*     */       try {
/* 169 */         this.m_parts = new Vector(nTokens + 1);
/*     */         
/* 171 */         String t = null;
/* 172 */         String lookahead = null;
/* 173 */         String error = null;
/*     */         
/* 175 */         while (tokenizer.hasMoreTokens()) {
/*     */           
/* 177 */           if (lookahead != null) {
/*     */             
/* 179 */             t = lookahead;
/* 180 */             lookahead = null;
/*     */           } else {
/*     */             
/* 183 */             t = tokenizer.nextToken();
/*     */           } 
/* 185 */           if (t.length() == 1) {
/*     */             
/* 187 */             switch (t.charAt(0)) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               case '"':
/*     */               case '\'':
/* 194 */                 buffer.append(t);
/*     */                 break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               case '{':
/*     */                 try {
/* 204 */                   lookahead = tokenizer.nextToken();
/*     */                   
/* 206 */                   if (lookahead.equals("{")) {
/*     */ 
/*     */ 
/*     */                     
/* 210 */                     buffer.append(lookahead);
/*     */                     
/* 212 */                     lookahead = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     break;
/*     */                   } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 227 */                   if (buffer.length() > 0) {
/*     */                     
/* 229 */                     this.m_parts.addElement(new AVTPartSimple(buffer.toString()));
/* 230 */                     buffer.setLength(0);
/*     */                   } 
/*     */                   
/* 233 */                   exprBuffer.setLength(0);
/*     */                   
/* 235 */                   while (null != lookahead) {
/*     */                     
/* 237 */                     if (lookahead.length() == 1) {
/*     */                       String quote; XPath xpath;
/* 239 */                       switch (lookahead.charAt(0)) {
/*     */ 
/*     */ 
/*     */ 
/*     */                         
/*     */                         case '"':
/*     */                         case '\'':
/* 246 */                           exprBuffer.append(lookahead);
/*     */                           
/* 248 */                           quote = lookahead;
/*     */ 
/*     */                           
/* 251 */                           lookahead = tokenizer.nextToken();
/*     */                           
/* 253 */                           while (!lookahead.equals(quote)) {
/*     */                             
/* 255 */                             exprBuffer.append(lookahead);
/*     */                             
/* 257 */                             lookahead = tokenizer.nextToken();
/*     */                           } 
/*     */                           
/* 260 */                           exprBuffer.append(lookahead);
/*     */                           
/* 262 */                           lookahead = tokenizer.nextToken();
/*     */                           continue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                         
/*     */                         case '{':
/* 270 */                           error = XSLMessages.createMessage("ER_NO_CURLYBRACE", null);
/*     */ 
/*     */                           
/* 273 */                           lookahead = null;
/*     */                           continue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                         
/*     */                         case '}':
/* 282 */                           buffer.setLength(0);
/*     */                           
/* 284 */                           xpath = handler.createXPath(exprBuffer.toString(), owner);
/*     */ 
/*     */                           
/* 287 */                           this.m_parts.addElement(new AVTPartXPath(xpath));
/*     */                           
/* 289 */                           lookahead = null;
/*     */                           continue;
/*     */                       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 297 */                       exprBuffer.append(lookahead);
/*     */                       
/* 299 */                       lookahead = tokenizer.nextToken();
/*     */ 
/*     */ 
/*     */                       
/*     */                       continue;
/*     */                     } 
/*     */ 
/*     */                     
/* 307 */                     exprBuffer.append(lookahead);
/*     */                     
/* 309 */                     lookahead = tokenizer.nextToken();
/*     */                   } 
/*     */ 
/*     */                   
/* 313 */                   if (error != null);
/*     */                 }
/* 315 */                 catch (NoSuchElementException ex) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 323 */                   error = XSLMessages.createMessage("ER_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { name, stringedValue });
/*     */                 } 
/*     */                 break;
/*     */ 
/*     */               
/*     */               case '}':
/* 329 */                 lookahead = tokenizer.nextToken();
/*     */                 
/* 331 */                 if (lookahead.equals("}")) {
/*     */ 
/*     */ 
/*     */                   
/* 335 */                   buffer.append(lookahead);
/*     */                   
/* 337 */                   lookahead = null;
/*     */ 
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */ 
/*     */ 
/*     */                 
/* 345 */                 try { handler.warn("WG_FOUND_CURLYBRACE", null); } catch (SAXException se)
/*     */                 
/*     */                 { 
/*     */                   
/* 349 */                   throw new TransformerException(se); }
/*     */ 
/*     */                 
/* 352 */                 buffer.append("}");
/*     */                 break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               default:
/* 363 */                 buffer.append(t);
/*     */                 break;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           } else {
/* 371 */             buffer.append(t);
/*     */           } 
/*     */           
/* 374 */           if (null != error) {
/*     */ 
/*     */ 
/*     */             
/* 378 */             try { handler.warn("WG_ATTR_TEMPLATE", new Object[] { error }); } catch (SAXException se)
/*     */             
/*     */             { 
/*     */ 
/*     */               
/* 383 */               throw new TransformerException(se); }
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         
/* 390 */         if (buffer.length() > 0)
/*     */         {
/* 392 */           this.m_parts.addElement(new AVTPartSimple(buffer.toString()));
/* 393 */           buffer.setLength(0);
/*     */         }
/*     */       
/*     */       } finally {
/*     */         
/* 398 */         StringBufferPool.free(buffer);
/* 399 */         StringBufferPool.free(exprBuffer);
/*     */       } 
/*     */     } 
/*     */     
/* 403 */     if (null == this.m_parts && null == this.m_simpleString)
/*     */     {
/*     */ 
/*     */       
/* 407 */       this.m_simpleString = "";
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
/*     */   public String getSimpleString() {
/* 419 */     if (null != this.m_simpleString)
/*     */     {
/* 421 */       return this.m_simpleString;
/*     */     }
/* 423 */     if (null != this.m_parts) {
/*     */       String s;
/* 425 */       FastStringBuffer buf = StringBufferPool.get();
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 430 */         buf.setLength(0);
/*     */         
/* 432 */         int n = this.m_parts.size();
/*     */         
/* 434 */         for (int i = 0; i < n; i++) {
/*     */           
/* 436 */           AVTPart part = this.m_parts.elementAt(i);
/*     */           
/* 438 */           buf.append(part.getSimpleString());
/*     */         } 
/*     */         
/* 441 */         s = buf.toString();
/*     */       }
/*     */       finally {
/*     */         
/* 445 */         StringBufferPool.free(buf);
/*     */       } 
/*     */       
/* 448 */       return s;
/*     */     } 
/*     */ 
/*     */     
/* 452 */     return "";
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
/*     */   public String evaluate(XPathContext xctxt, int context, PrefixResolver nsNode) throws TransformerException {
/* 473 */     FastStringBuffer buf = StringBufferPool.get();
/*     */ 
/*     */     
/*     */     try {
/* 477 */       if (null != this.m_simpleString)
/*     */       {
/* 479 */         return this.m_simpleString;
/*     */       }
/* 481 */       if (null != this.m_parts) {
/*     */         
/* 483 */         buf.setLength(0);
/*     */         
/* 485 */         int n = this.m_parts.size();
/*     */         
/* 487 */         for (int i = 0; i < n; i++) {
/*     */           
/* 489 */           AVTPart part = this.m_parts.elementAt(i);
/*     */           
/* 491 */           part.evaluate(xctxt, buf, context, nsNode);
/*     */         } 
/*     */         
/* 494 */         return buf.toString();
/*     */       } 
/*     */ 
/*     */       
/* 498 */       return "";
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 503 */       StringBufferPool.free(buf);
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
/*     */   public boolean isContextInsensitive() {
/* 520 */     return (null != this.m_simpleString);
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
/*     */   public boolean canTraverseOutsideSubtree() {
/* 532 */     if (null != this.m_parts) {
/*     */       
/* 534 */       int n = this.m_parts.size();
/*     */       
/* 536 */       for (int i = 0; i < n; i++) {
/*     */         
/* 538 */         AVTPart part = this.m_parts.elementAt(i);
/*     */         
/* 540 */         if (part.canTraverseOutsideSubtree()) {
/* 541 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 545 */     return false;
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
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/* 560 */     if (null != this.m_parts) {
/*     */       
/* 562 */       int n = this.m_parts.size();
/*     */       
/* 564 */       for (int i = 0; i < n; i++) {
/*     */         
/* 566 */         AVTPart part = this.m_parts.elementAt(i);
/*     */         
/* 568 */         part.fixupVariables(vars, globalsSize);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(XSLTVisitor visitor) {
/* 578 */     if (visitor.visitAVT(this) && null != this.m_parts) {
/*     */       
/* 580 */       int n = this.m_parts.size();
/*     */       
/* 582 */       for (int i = 0; i < n; i++) {
/*     */         
/* 584 */         AVTPart part = this.m_parts.elementAt(i);
/*     */         
/* 586 */         part.callVisitors(visitor);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSimple() {
/* 596 */     return (this.m_simpleString != null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/AVT.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */