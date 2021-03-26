/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import javax.xml.transform.ErrorListener;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.NodeSetDTM;
/*     */ import org.apache.xpath.SourceTreeManager;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.functions.Function2Args;
/*     */ import org.apache.xpath.functions.WrongNumberArgsException;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FuncDocument
/*     */   extends Function2Args
/*     */ {
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  72 */     int context = xctxt.getCurrentNode();
/*  73 */     DTM dtm = xctxt.getDTM(context);
/*     */     
/*  75 */     int docContext = dtm.getDocumentRoot(context);
/*  76 */     XObject arg = getArg0().execute(xctxt);
/*     */     
/*  78 */     String base = "";
/*  79 */     Expression arg1Expr = getArg1();
/*     */     
/*  81 */     if (null != arg1Expr) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  88 */       XObject arg2 = arg1Expr.execute(xctxt);
/*     */       
/*  90 */       if (4 == arg2.getType())
/*     */       {
/*  92 */         int baseNode = arg2.iter().nextNode();
/*     */         
/*  94 */         if (baseNode == -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  99 */           warn(xctxt, "WG_EMPTY_SECOND_ARG", null);
/* 100 */           XNodeSet xNodeSet = new XNodeSet(xctxt.getDTMManager());
/* 101 */           return (XObject)xNodeSet;
/*     */         } 
/* 103 */         DTM baseDTM = xctxt.getDTM(baseNode);
/* 104 */         base = baseDTM.getDocumentBaseURI();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 122 */         arg2.iter();
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       assertion((null != xctxt.getNamespaceContext()), "Namespace context can not be null!");
/* 138 */       base = xctxt.getNamespaceContext().getBaseIdentifier();
/*     */     } 
/*     */     
/* 141 */     XNodeSet nodes = new XNodeSet(xctxt.getDTMManager());
/* 142 */     NodeSetDTM mnl = nodes.mutableNodeset();
/* 143 */     DTMIterator iterator = (4 == arg.getType()) ? arg.iter() : null;
/*     */     
/* 145 */     int pos = -1;
/*     */     
/* 147 */     while (null == iterator || -1 != (pos = iterator.nextNode())) {
/*     */       
/* 149 */       XMLString ref = (null != iterator) ? xctxt.getDTM(pos).getStringValue(pos) : arg.xstr();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 160 */       if (null == arg1Expr && -1 != pos) {
/*     */         
/* 162 */         DTM baseDTM = xctxt.getDTM(pos);
/* 163 */         base = baseDTM.getDocumentBaseURI();
/*     */       } 
/*     */       
/* 166 */       if (null == ref) {
/*     */         continue;
/*     */       }
/* 169 */       if (-1 == docContext)
/*     */       {
/* 171 */         error(xctxt, "ER_NO_CONTEXT_OWNERDOC", null);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 180 */       int indexOfColon = ref.indexOf(58);
/* 181 */       int indexOfSlash = ref.indexOf(47);
/*     */       
/* 183 */       if (indexOfColon != -1 && indexOfSlash != -1 && indexOfColon < indexOfSlash)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 188 */         base = null;
/*     */       }
/*     */       
/* 191 */       int newDoc = getDoc(xctxt, context, ref.toString(), base);
/*     */ 
/*     */       
/* 194 */       if (-1 != newDoc)
/*     */       {
/*     */         
/* 197 */         if (!mnl.contains(newDoc))
/*     */         {
/* 199 */           mnl.addElement(newDoc);
/*     */         }
/*     */       }
/*     */       
/* 203 */       if (null == iterator || newDoc == -1) {
/*     */         break;
/*     */       }
/*     */     } 
/* 207 */     return (XObject)nodes;
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
/*     */   int getDoc(XPathContext xctxt, int context, String uri, String base) throws TransformerException {
/*     */     Source source;
/*     */     int newDoc;
/* 228 */     SourceTreeManager treeMgr = xctxt.getSourceTreeManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     try { source = treeMgr.resolveURI(base, uri, xctxt.getSAXLocator());
/* 235 */       newDoc = treeMgr.getNode(source); } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */       
/* 239 */       throw new TransformerException(ioe.getMessage(), xctxt.getSAXLocator(), ioe); } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 244 */       throw new TransformerException(te); }
/*     */ 
/*     */     
/* 247 */     if (-1 != newDoc) {
/* 248 */       return newDoc;
/*     */     }
/*     */     
/* 251 */     if (uri.length() == 0) {
/*     */ 
/*     */       
/* 254 */       uri = xctxt.getNamespaceContext().getBaseIdentifier();
/*     */ 
/*     */       
/* 257 */       try { source = treeMgr.resolveURI(base, uri, xctxt.getSAXLocator()); } catch (IOException ioe)
/*     */       
/*     */       { 
/*     */         
/* 261 */         throw new TransformerException(ioe.getMessage(), xctxt.getSAXLocator(), ioe); }
/*     */     
/*     */     } 
/*     */ 
/*     */     
/* 266 */     String diagnosticsString = null;
/*     */ 
/*     */ 
/*     */     
/* 270 */     try { if (null != uri && uri.toString().length() > 0)
/*     */       
/* 272 */       { newDoc = treeMgr.getSourceTree(source, xctxt.getSAXLocator(), xctxt);
/*     */          }
/*     */       
/*     */       else
/*     */       
/* 277 */       { warn(xctxt, "WG_CANNOT_MAKE_URL_FROM", new Object[] { ((base == null) ? "" : base) + uri }); }  } catch (Throwable throwable)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 284 */       newDoc = -1;
/*     */ 
/*     */ 
/*     */       
/* 288 */       while (throwable instanceof WrappedRuntimeException)
/*     */       {
/* 290 */         throwable = ((WrappedRuntimeException)throwable).getException();
/*     */       }
/*     */ 
/*     */       
/* 294 */       if (throwable instanceof NullPointerException || throwable instanceof ClassCastException)
/*     */       {
/*     */         
/* 297 */         throw new WrappedRuntimeException((Exception)throwable);
/*     */       }
/*     */ 
/*     */       
/* 301 */       StringWriter sw = new StringWriter();
/* 302 */       PrintWriter diagnosticsWriter = new PrintWriter(sw);
/*     */       
/* 304 */       if (throwable instanceof TransformerException) {
/*     */         
/* 306 */         TransformerException spe = (TransformerException)throwable;
/*     */ 
/*     */         
/* 309 */         Throwable e = spe;
/*     */         
/* 311 */         while (null != e)
/*     */         {
/* 313 */           if (null != e.getMessage())
/*     */           {
/* 315 */             diagnosticsWriter.println(" (" + e.getClass().getName() + "): " + e.getMessage());
/*     */           }
/*     */ 
/*     */           
/* 319 */           if (e instanceof TransformerException) {
/*     */             
/* 321 */             TransformerException spe2 = (TransformerException)e;
/*     */             
/* 323 */             SourceLocator locator = spe2.getLocator();
/* 324 */             if (null != locator && null != locator.getSystemId()) {
/* 325 */               diagnosticsWriter.println("   ID: " + locator.getSystemId() + " Line #" + locator.getLineNumber() + " Column #" + locator.getColumnNumber());
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 330 */             e = spe2.getException();
/*     */             
/* 332 */             if (e instanceof WrappedRuntimeException)
/* 333 */               e = ((WrappedRuntimeException)e).getException(); 
/*     */             continue;
/*     */           } 
/* 336 */           e = null;
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 342 */         diagnosticsWriter.println(" (" + throwable.getClass().getName() + "): " + throwable.getMessage());
/*     */       } 
/*     */ 
/*     */       
/* 346 */       diagnosticsString = throwable.getMessage(); }
/*     */ 
/*     */     
/* 349 */     if (-1 == newDoc)
/*     */     {
/*     */ 
/*     */       
/* 353 */       if (null != diagnosticsString) {
/*     */         
/* 355 */         warn(xctxt, "WG_CANNOT_LOAD_REQUESTED_DOC", new Object[] { diagnosticsString });
/*     */       }
/*     */       else {
/*     */         
/* 359 */         warn(xctxt, "WG_CANNOT_LOAD_REQUESTED_DOC", new Object[] { (uri == null) ? (((base == null) ? "" : base) + uri) : uri.toString() });
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 371 */     return newDoc;
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
/*     */   public void error(XPathContext xctxt, String msg, Object[] args) throws TransformerException {
/* 390 */     String formattedMsg = XSLMessages.createMessage(msg, args);
/* 391 */     ErrorListener errHandler = xctxt.getErrorListener();
/* 392 */     TransformerException spe = new TransformerException(formattedMsg, xctxt.getSAXLocator());
/*     */ 
/*     */     
/* 395 */     if (null != errHandler) {
/* 396 */       errHandler.error(spe);
/*     */     } else {
/* 398 */       System.out.println(formattedMsg);
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
/*     */   public void warn(XPathContext xctxt, String msg, Object[] args) throws TransformerException {
/* 416 */     String formattedMsg = XSLMessages.createWarning(msg, args);
/* 417 */     ErrorListener errHandler = xctxt.getErrorListener();
/* 418 */     TransformerException spe = new TransformerException(formattedMsg, xctxt.getSAXLocator());
/*     */ 
/*     */     
/* 421 */     if (null != errHandler) {
/* 422 */       errHandler.warning(spe);
/*     */     } else {
/* 424 */       System.out.println(formattedMsg);
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
/*     */   public void checkNumberArgs(int argNum) throws WrongNumberArgsException {
/* 437 */     if (argNum < 1 || argNum > 2) {
/* 438 */       reportWrongNumberArgs();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/* 448 */     throw new WrongNumberArgsException(XSLMessages.createMessage("ER_ONE_OR_TWO", null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNodesetExpr() {
/* 457 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/FuncDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */