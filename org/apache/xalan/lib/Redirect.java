/*     */ package org.apache.xalan.lib;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.util.Hashtable;
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.apache.xalan.extensions.XSLProcessorContext;
/*     */ import org.apache.xalan.templates.ElemExtensionCall;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.OutputProperties;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.w3c.dom.Node;
/*     */ import org.xml.sax.ContentHandler;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Redirect
/*     */ {
/* 127 */   protected Hashtable m_formatterListeners = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   protected Hashtable m_outputStreams = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean DEFAULT_APPEND_OPEN = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean DEFAULT_APPEND_WRITE = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void open(XSLProcessorContext context, ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
/* 155 */     String fileName = getFilename(context, elem);
/* 156 */     Object flistener = this.m_formatterListeners.get(fileName);
/* 157 */     if (null == flistener) {
/*     */       
/* 159 */       String mkdirsExpr = elem.getAttribute("mkdirs", context.getContextNode(), context.getTransformer());
/*     */ 
/*     */       
/* 162 */       boolean mkdirs = (mkdirsExpr != null) ? ((mkdirsExpr.equals("true") || mkdirsExpr.equals("yes"))) : true;
/*     */ 
/*     */ 
/*     */       
/* 166 */       String appendExpr = elem.getAttribute("append", context.getContextNode(), context.getTransformer());
/* 167 */       boolean append = (appendExpr != null) ? ((appendExpr.equals("true") || appendExpr.equals("yes"))) : false;
/*     */ 
/*     */       
/* 170 */       ContentHandler contentHandler = makeFormatterListener(context, elem, fileName, true, mkdirs, append);
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
/*     */   public void write(XSLProcessorContext context, ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
/*     */     ContentHandler contentHandler;
/* 184 */     String fileName = getFilename(context, elem);
/* 185 */     Object flObject = this.m_formatterListeners.get(fileName);
/*     */     
/* 187 */     boolean inTable = false;
/* 188 */     if (null == flObject) {
/*     */       
/* 190 */       String mkdirsExpr = elem.getAttribute("mkdirs", context.getContextNode(), context.getTransformer());
/*     */ 
/*     */ 
/*     */       
/* 194 */       boolean mkdirs = (mkdirsExpr != null) ? ((mkdirsExpr.equals("true") || mkdirsExpr.equals("yes"))) : true;
/*     */ 
/*     */ 
/*     */       
/* 198 */       String appendExpr = elem.getAttribute("append", context.getContextNode(), context.getTransformer());
/* 199 */       boolean append = (appendExpr != null) ? ((appendExpr.equals("true") || appendExpr.equals("yes"))) : false;
/*     */ 
/*     */       
/* 202 */       contentHandler = makeFormatterListener(context, elem, fileName, true, mkdirs, append);
/*     */     }
/*     */     else {
/*     */       
/* 206 */       inTable = true;
/* 207 */       contentHandler = (ContentHandler)flObject;
/*     */     } 
/*     */     
/* 210 */     TransformerImpl transf = context.getTransformer();
/*     */     
/* 212 */     transf.executeChildTemplates((ElemTemplateElement)elem, context.getContextNode(), context.getMode(), contentHandler);
/*     */ 
/*     */ 
/*     */     
/* 216 */     if (!inTable) {
/*     */       
/* 218 */       OutputStream ostream = (OutputStream)this.m_outputStreams.get(fileName);
/* 219 */       if (null != ostream) {
/*     */ 
/*     */ 
/*     */         
/* 223 */         try { contentHandler.endDocument(); } catch (SAXException se)
/*     */         
/*     */         { 
/*     */           
/* 227 */           throw new TransformerException(se); }
/*     */         
/* 229 */         ostream.close();
/* 230 */         this.m_outputStreams.remove(fileName);
/* 231 */         this.m_formatterListeners.remove(fileName);
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
/*     */ 
/*     */   
/*     */   public void close(XSLProcessorContext context, ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
/* 246 */     String fileName = getFilename(context, elem);
/* 247 */     Object formatterObj = this.m_formatterListeners.get(fileName);
/* 248 */     if (null != formatterObj) {
/*     */       
/* 250 */       ContentHandler fl = (ContentHandler)formatterObj;
/*     */ 
/*     */       
/* 253 */       try { fl.endDocument(); } catch (SAXException se)
/*     */       
/*     */       { 
/*     */         
/* 257 */         throw new TransformerException(se); }
/*     */       
/* 259 */       OutputStream ostream = (OutputStream)this.m_outputStreams.get(fileName);
/* 260 */       if (null != ostream) {
/*     */         
/* 262 */         ostream.close();
/* 263 */         this.m_outputStreams.remove(fileName);
/*     */       } 
/* 265 */       this.m_formatterListeners.remove(fileName);
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
/*     */   private String getFilename(XSLProcessorContext context, ElemExtensionCall elem) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
/* 279 */     String str1, fileNameExpr = elem.getAttribute("select", context.getContextNode(), context.getTransformer());
/*     */ 
/*     */ 
/*     */     
/* 283 */     if (null != fileNameExpr) {
/*     */       
/* 285 */       XPathContext xctxt = context.getTransformer().getXPathContext();
/*     */       
/* 287 */       XPath myxpath = new XPath(fileNameExpr, (SourceLocator)elem, xctxt.getNamespaceContext(), 0);
/* 288 */       XObject xobj = myxpath.execute(xctxt, context.getContextNode(), (PrefixResolver)elem);
/* 289 */       str1 = xobj.str();
/* 290 */       if (null == str1 || str1.length() == 0)
/*     */       {
/* 292 */         str1 = elem.getAttribute("file", context.getContextNode(), context.getTransformer());
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 299 */       str1 = elem.getAttribute("file", context.getContextNode(), context.getTransformer());
/*     */     } 
/*     */     
/* 302 */     if (null == str1)
/*     */     {
/* 304 */       context.getTransformer().getMsgMgr().error((SourceLocator)elem, (Node)elem, context.getContextNode(), "ER_REDIRECT_COULDNT_GET_FILENAME");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 309 */     return str1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String urlToFileName(String base) {
/* 317 */     if (null != base)
/*     */     {
/* 319 */       if (base.startsWith("file:////")) {
/*     */         
/* 321 */         base = base.substring(7);
/*     */       }
/* 323 */       else if (base.startsWith("file:///")) {
/*     */         
/* 325 */         base = base.substring(6);
/*     */       }
/* 327 */       else if (base.startsWith("file://")) {
/*     */         
/* 329 */         base = base.substring(5);
/*     */       }
/* 331 */       else if (base.startsWith("file:/")) {
/*     */         
/* 333 */         base = base.substring(5);
/*     */       }
/* 335 */       else if (base.startsWith("file:")) {
/*     */         
/* 337 */         base = base.substring(4);
/*     */       } 
/*     */     }
/* 340 */     return base;
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
/*     */   private ContentHandler makeFormatterListener(XSLProcessorContext context, ElemExtensionCall elem, String fileName, boolean shouldPutInTable, boolean mkdirs, boolean append) throws MalformedURLException, FileNotFoundException, IOException, TransformerException {
/* 357 */     File file = new File(fileName);
/* 358 */     TransformerImpl transformer = context.getTransformer();
/*     */ 
/*     */     
/* 361 */     if (!file.isAbsolute()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 370 */       Result outputTarget = transformer.getOutputTarget(); String base;
/* 371 */       if (null != outputTarget && (base = outputTarget.getSystemId()) != null) {
/* 372 */         base = urlToFileName(base);
/*     */       }
/*     */       else {
/*     */         
/* 376 */         base = urlToFileName(transformer.getBaseURLOfSource());
/*     */       } 
/*     */       
/* 379 */       if (null != base) {
/*     */         
/* 381 */         File baseFile = new File(base);
/* 382 */         file = new File(baseFile.getParent(), fileName);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 387 */     if (mkdirs) {
/*     */       
/* 389 */       String dirStr = file.getParent();
/* 390 */       if (null != dirStr && dirStr.length() > 0) {
/*     */         
/* 392 */         File dir = new File(dirStr);
/* 393 */         dir.mkdirs();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 399 */     OutputProperties format = transformer.getOutputFormat();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 407 */     FileOutputStream ostream = new FileOutputStream(file.getPath(), append);
/*     */ 
/*     */ 
/*     */     
/* 411 */     try { SerializationHandler flistener = transformer.createSerializationHandler(new StreamResult(ostream), format);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 416 */       try { flistener.startDocument(); } catch (SAXException se)
/*     */       
/*     */       { 
/*     */         
/* 420 */         throw new TransformerException(se); }
/*     */       
/* 422 */       if (shouldPutInTable) {
/*     */         
/* 424 */         this.m_outputStreams.put(fileName, ostream);
/* 425 */         this.m_formatterListeners.put(fileName, flistener);
/*     */       } 
/* 427 */       return (ContentHandler)flistener; } catch (TransformerException te)
/*     */     
/*     */     { 
/*     */       
/* 431 */       throw new TransformerException(te); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/Redirect.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */