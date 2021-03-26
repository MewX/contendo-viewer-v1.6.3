/*     */ package org.apache.xalan.extensions;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.Stylesheet;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.ref.DTMNodeList;
/*     */ import org.apache.xml.utils.StringVector;
/*     */ import org.apache.xml.utils.SystemIDResolver;
/*     */ import org.apache.xpath.XPathProcessorException;
/*     */ import org.apache.xpath.functions.FuncExtFunction;
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
/*     */ public class ExtensionHandlerGeneral
/*     */   extends ExtensionHandler
/*     */ {
/*     */   private String m_scriptSrc;
/*     */   private String m_scriptSrcURL;
/*  61 */   private Hashtable m_functions = new Hashtable();
/*     */ 
/*     */   
/*  64 */   private Hashtable m_elements = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object m_engine;
/*     */ 
/*     */ 
/*     */   
/*  73 */   private Method m_engineCall = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String BSF_MANAGER = "com.ibm.bsf.BSFManager";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String BSF_ENGINE = "com.ibm.bsf.BSFEngine";
/*     */ 
/*     */   
/*  84 */   private static final Integer NEG1INT = new Integer(-1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Class array$Ljava$lang$Object;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensionHandlerGeneral(String namespaceUri, StringVector elemNames, StringVector funcNames, String scriptLang, String scriptSrcURL, String scriptSrc, String systemId) throws TransformerException {
/* 108 */     super(namespaceUri, scriptLang);
/*     */     
/* 110 */     if (elemNames != null) {
/*     */       
/* 112 */       Object junk = new Object();
/* 113 */       int n = elemNames.size();
/*     */       
/* 115 */       for (int i = 0; i < n; i++) {
/*     */         
/* 117 */         String tok = elemNames.elementAt(i);
/*     */         
/* 119 */         this.m_elements.put(tok, junk);
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     if (funcNames != null) {
/*     */       
/* 125 */       Object junk = new Object();
/* 126 */       int n = funcNames.size();
/*     */       
/* 128 */       for (int i = 0; i < n; i++) {
/*     */         
/* 130 */         String tok = funcNames.elementAt(i);
/*     */         
/* 132 */         this.m_functions.put(tok, junk);
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     this.m_scriptSrcURL = scriptSrcURL;
/* 137 */     this.m_scriptSrc = scriptSrc;
/*     */     
/* 139 */     if (this.m_scriptSrcURL != null) {
/*     */       
/* 141 */       URL url = null;
/*     */       
/* 143 */       try { url = new URL(this.m_scriptSrcURL); } catch (MalformedURLException mue)
/*     */       
/*     */       { 
/*     */         
/* 147 */         int indexOfColon = this.m_scriptSrcURL.indexOf(':');
/* 148 */         int indexOfSlash = this.m_scriptSrcURL.indexOf('/');
/*     */         
/* 150 */         if (indexOfColon != -1 && indexOfSlash != -1 && indexOfColon < indexOfSlash) {
/*     */ 
/*     */ 
/*     */           
/* 154 */           url = null;
/* 155 */           throw new TransformerException(XSLMessages.createMessage("ER_COULD_NOT_FIND_EXTERN_SCRIPT", new Object[] { this.m_scriptSrcURL }), mue);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 161 */         try { url = new URL(new URL(SystemIDResolver.getAbsoluteURI(systemId)), this.m_scriptSrcURL); } catch (MalformedURLException mue2)
/*     */         
/*     */         { 
/*     */           
/* 165 */           throw new TransformerException(XSLMessages.createMessage("ER_COULD_NOT_FIND_EXTERN_SCRIPT", new Object[] { this.m_scriptSrcURL }), mue2); }
/*     */          }
/*     */ 
/*     */ 
/*     */       
/* 170 */       if (url != null) {
/*     */ 
/*     */         
/*     */         try { 
/* 174 */           URLConnection uc = url.openConnection();
/* 175 */           InputStream is = uc.getInputStream();
/* 176 */           byte[] bArray = new byte[uc.getContentLength()];
/* 177 */           is.read(bArray);
/* 178 */           this.m_scriptSrc = new String(bArray); } catch (IOException ioe)
/*     */         
/*     */         { 
/*     */ 
/*     */           
/* 183 */           throw new TransformerException(XSLMessages.createMessage("ER_COULD_NOT_FIND_EXTERN_SCRIPT", new Object[] { this.m_scriptSrcURL }), ioe); }
/*     */       
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 190 */     Object manager = null;
/*     */ 
/*     */     
/* 193 */     try { manager = ObjectFactory.newInstance("com.ibm.bsf.BSFManager", ObjectFactory.findClassLoader(), true); } catch (ConfigurationError e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 198 */       e.printStackTrace(); }
/*     */ 
/*     */     
/* 201 */     if (manager == null)
/*     */     {
/* 203 */       throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_INIT_BSFMGR", null));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 208 */     try { Method loadScriptingEngine = manager.getClass().getMethod("loadScriptingEngine", new Class[] { String.class });
/*     */ 
/*     */       
/* 211 */       this.m_engine = loadScriptingEngine.invoke(manager, new Object[] { scriptLang });
/*     */ 
/*     */       
/* 214 */       Method engineExec = this.m_engine.getClass().getMethod("exec", new Class[] { String.class, int.class, int.class, Object.class });
/*     */ 
/*     */ 
/*     */       
/* 218 */       engineExec.invoke(this.m_engine, new Object[] { "XalanScript", NEG1INT, NEG1INT, this.m_scriptSrc }); } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 223 */       e.printStackTrace();
/*     */       
/* 225 */       throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_CMPL_EXTENSN", null), e); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFunctionAvailable(String function) {
/* 236 */     return (this.m_functions.get(function) != null);
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
/*     */   public boolean isElementAvailable(String element) {
/* 248 */     return (this.m_elements.get(element) != null);
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
/*     */   public Object callFunction(String funcName, Vector args, Object methodKey, ExpressionContext exprContext) throws TransformerException {
/*     */     
/* 272 */     try { Object[] argArray = new Object[args.size()];
/*     */       
/* 274 */       for (int i = 0; i < argArray.length; i++) {
/*     */         
/* 276 */         Object o = args.elementAt(i);
/*     */         
/* 278 */         argArray[i] = (o instanceof XObject) ? ((XObject)o).object() : o;
/* 279 */         o = argArray[i];
/* 280 */         if (null != o && o instanceof DTMIterator)
/*     */         {
/* 282 */           argArray[i] = new DTMNodeList((DTMIterator)o);
/*     */         }
/*     */       } 
/*     */       
/* 286 */       if (this.m_engineCall == null) {
/* 287 */         this.m_engineCall = this.m_engine.getClass().getMethod("call", new Class[] { Object.class, String.class, (array$Ljava$lang$Object == null) ? (array$Ljava$lang$Object = class$("[Ljava.lang.Object;")) : array$Ljava$lang$Object });
/*     */       }
/*     */ 
/*     */       
/* 291 */       return this.m_engineCall.invoke(this.m_engine, new Object[] { null, funcName, argArray }); } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 296 */       e.printStackTrace();
/*     */       
/* 298 */       String msg = e.getMessage();
/*     */       
/* 300 */       if (null != msg) {
/*     */         
/* 302 */         if (msg.startsWith("Stopping after fatal error:"))
/*     */         {
/* 304 */           msg = msg.substring("Stopping after fatal error:".length());
/*     */         }
/*     */ 
/*     */         
/* 308 */         throw new TransformerException(e);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 314 */       throw new TransformerException(XSLMessages.createMessage("ER_CANNOT_CREATE_EXTENSN", new Object[] { funcName, e })); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object callFunction(FuncExtFunction extFunction, Vector args, ExpressionContext exprContext) throws TransformerException {
/* 334 */     return callFunction(extFunction.getFunctionName(), args, extFunction.getMethodKey(), exprContext);
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
/*     */   public void processElement(String localPart, ElemTemplateElement element, TransformerImpl transformer, Stylesheet stylesheetTree, Object methodKey) throws TransformerException, IOException {
/* 365 */     Object result = null;
/* 366 */     XSLProcessorContext xpc = new XSLProcessorContext(transformer, stylesheetTree);
/*     */ 
/*     */ 
/*     */     
/* 370 */     try { Vector argv = new Vector(2);
/*     */       
/* 372 */       argv.addElement(xpc);
/* 373 */       argv.addElement(element);
/*     */       
/* 375 */       result = callFunction(localPart, argv, methodKey, transformer.getXPathContext().getExpressionContext()); } catch (XPathProcessorException e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 382 */       throw new TransformerException(e.getMessage(), e); }
/*     */ 
/*     */     
/* 385 */     if (result != null)
/*     */     {
/* 387 */       xpc.outputToResultTree(stylesheetTree, result);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExtensionHandlerGeneral.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */