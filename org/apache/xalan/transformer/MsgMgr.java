/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
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
/*     */ 
/*     */ public class MsgMgr
/*     */ {
/*     */   private TransformerImpl m_transformer;
/*     */   
/*     */   public MsgMgr(TransformerImpl transformer) {
/*  43 */     this.m_transformer = transformer;
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
/*     */   public void message(SourceLocator srcLctr, String msg, boolean terminate) throws TransformerException {
/*  63 */     ErrorListener errHandler = this.m_transformer.getErrorListener();
/*     */     
/*  65 */     if (null != errHandler) {
/*     */       
/*  67 */       errHandler.warning(new TransformerException(msg, srcLctr));
/*     */     }
/*     */     else {
/*     */       
/*  71 */       if (terminate) {
/*  72 */         throw new TransformerException(msg, srcLctr);
/*     */       }
/*  74 */       System.out.println(msg);
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
/*     */   public void warn(SourceLocator srcLctr, String msg) throws TransformerException {
/*  90 */     warn(srcLctr, null, null, msg, null);
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
/*     */   public void warn(SourceLocator srcLctr, String msg, Object[] args) throws TransformerException {
/* 106 */     warn(srcLctr, null, null, msg, args);
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
/*     */   public void warn(SourceLocator srcLctr, Node styleNode, Node sourceNode, String msg) throws TransformerException {
/* 125 */     warn(srcLctr, styleNode, sourceNode, msg, null);
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
/*     */   public void warn(SourceLocator srcLctr, Node styleNode, Node sourceNode, String msg, Object[] args) throws TransformerException {
/* 145 */     String formattedMsg = XSLMessages.createWarning(msg, args);
/* 146 */     ErrorListener errHandler = this.m_transformer.getErrorListener();
/*     */     
/* 148 */     if (null != errHandler) {
/* 149 */       errHandler.warning(new TransformerException(formattedMsg, srcLctr));
/*     */     } else {
/* 151 */       System.out.println(formattedMsg);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void error(SourceLocator srcLctr, String msg) throws TransformerException {
/* 195 */     error(srcLctr, null, null, msg, null);
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
/*     */   public void error(SourceLocator srcLctr, String msg, Object[] args) throws TransformerException {
/* 212 */     error(srcLctr, null, null, msg, args);
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
/*     */   public void error(SourceLocator srcLctr, String msg, Exception e) throws TransformerException {
/* 229 */     error(srcLctr, msg, (Object[])null, e);
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
/*     */   public void error(SourceLocator srcLctr, String msg, Object[] args, Exception e) throws TransformerException {
/* 249 */     String formattedMsg = XSLMessages.createMessage(msg, args);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 255 */     ErrorListener errHandler = this.m_transformer.getErrorListener();
/*     */     
/* 257 */     if (null != errHandler) {
/* 258 */       errHandler.fatalError(new TransformerException(formattedMsg, srcLctr));
/*     */     } else {
/* 260 */       throw new TransformerException(formattedMsg, srcLctr);
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
/*     */   public void error(SourceLocator srcLctr, Node styleNode, Node sourceNode, String msg) throws TransformerException {
/* 279 */     error(srcLctr, styleNode, sourceNode, msg, null);
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
/*     */   public void error(SourceLocator srcLctr, Node styleNode, Node sourceNode, String msg, Object[] args) throws TransformerException {
/* 300 */     String formattedMsg = XSLMessages.createMessage(msg, args);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 306 */     ErrorListener errHandler = this.m_transformer.getErrorListener();
/*     */     
/* 308 */     if (null != errHandler) {
/* 309 */       errHandler.fatalError(new TransformerException(formattedMsg, srcLctr));
/*     */     } else {
/* 311 */       throw new TransformerException(formattedMsg, srcLctr);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/MsgMgr.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */