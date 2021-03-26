/*     */ package org.apache.xpath;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XPathException
/*     */   extends TransformerException
/*     */ {
/*  39 */   Object m_styleNode = null;
/*     */ 
/*     */   
/*     */   protected Exception m_exception;
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStylesheetNode() {
/*  47 */     return this.m_styleNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStylesheetNode(Object styleNode) {
/*  56 */     this.m_styleNode = styleNode;
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
/*     */   public XPathException(String message, ExpressionNode ex) {
/*  71 */     super(message);
/*  72 */     setLocator(ex);
/*  73 */     setStylesheetNode(getStylesheetNode(ex));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPathException(String message) {
/*  83 */     super(message);
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
/*     */   public Node getStylesheetNode(ExpressionNode ex) {
/*  95 */     ExpressionNode owner = getExpressionOwner(ex);
/*     */     
/*  97 */     if (null != owner && owner instanceof Node)
/*     */     {
/*  99 */       return (Node)owner;
/*     */     }
/* 101 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ExpressionNode getExpressionOwner(ExpressionNode ex) {
/* 111 */     ExpressionNode parent = ex.exprGetParent();
/* 112 */     while (null != parent && parent instanceof Expression)
/* 113 */       parent = parent.exprGetParent(); 
/* 114 */     return parent;
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
/*     */   public XPathException(String message, Object styleNode) {
/* 129 */     super(message);
/*     */     
/* 131 */     this.m_styleNode = styleNode;
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
/*     */   public XPathException(String message, Node styleNode, Exception e) {
/* 146 */     super(message);
/*     */     
/* 148 */     this.m_styleNode = styleNode;
/* 149 */     this.m_exception = e;
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
/*     */   public XPathException(String message, Exception e) {
/* 162 */     super(message);
/*     */     
/* 164 */     this.m_exception = e;
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
/*     */   public void printStackTrace(PrintStream s) {
/* 176 */     if (s == null) {
/* 177 */       s = System.err;
/*     */     }
/*     */ 
/*     */     
/* 181 */     try { super.printStackTrace(s); } catch (Exception exception1) {}
/*     */ 
/*     */ 
/*     */     
/* 185 */     Throwable exception = this.m_exception;
/*     */     
/* 187 */     for (int i = 0; i < 10 && null != exception; i++) {
/*     */       
/* 189 */       s.println("---------");
/* 190 */       exception.printStackTrace(s);
/*     */       
/* 192 */       if (exception instanceof TransformerException) {
/*     */         
/* 194 */         TransformerException se = (TransformerException)exception;
/* 195 */         Throwable prev = exception;
/*     */         
/* 197 */         exception = se.getException();
/*     */         
/* 199 */         if (prev == exception) {
/*     */           break;
/*     */         }
/*     */       } else {
/*     */         
/* 204 */         exception = null;
/*     */       } 
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
/*     */   public String getMessage() {
/* 217 */     String lastMessage = super.getMessage();
/* 218 */     Throwable exception = this.m_exception;
/*     */     
/* 220 */     while (null != exception) {
/*     */       
/* 222 */       String nextMessage = exception.getMessage();
/*     */       
/* 224 */       if (null != nextMessage) {
/* 225 */         lastMessage = nextMessage;
/*     */       }
/* 227 */       if (exception instanceof TransformerException) {
/*     */         
/* 229 */         TransformerException se = (TransformerException)exception;
/* 230 */         Throwable prev = exception;
/*     */         
/* 232 */         exception = se.getException();
/*     */         
/* 234 */         if (prev == exception) {
/*     */           break;
/*     */         }
/*     */         continue;
/*     */       } 
/* 239 */       exception = null;
/*     */     } 
/*     */ 
/*     */     
/* 243 */     return (null != lastMessage) ? lastMessage : "";
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
/*     */   public void printStackTrace(PrintWriter s) {
/* 255 */     if (s == null) {
/* 256 */       s = new PrintWriter(System.err);
/*     */     }
/*     */ 
/*     */     
/* 260 */     try { super.printStackTrace(s); } catch (Exception exception1) {}
/*     */ 
/*     */ 
/*     */     
/* 264 */     Throwable exception = this.m_exception;
/*     */     
/* 266 */     for (int i = 0; i < 10 && null != exception; i++) {
/*     */       
/* 268 */       s.println("---------");
/*     */ 
/*     */ 
/*     */       
/* 272 */       try { exception.printStackTrace(s); } catch (Exception e)
/*     */       
/*     */       { 
/*     */         
/* 276 */         s.println("Could not print stack trace..."); }
/*     */ 
/*     */       
/* 279 */       if (exception instanceof TransformerException) {
/*     */         
/* 281 */         TransformerException se = (TransformerException)exception;
/* 282 */         Throwable prev = exception;
/*     */         
/* 284 */         exception = se.getException();
/*     */         
/* 286 */         if (prev == exception) {
/*     */           
/* 288 */           exception = null;
/*     */ 
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } else {
/* 295 */         exception = null;
/*     */       } 
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
/*     */   public Throwable getException() {
/* 308 */     return this.m_exception;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/XPathException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */