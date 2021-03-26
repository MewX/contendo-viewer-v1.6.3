/*     */ package org.apache.xalan.extensions;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.ElemExsltFunction;
/*     */ import org.apache.xalan.templates.ElemTemplate;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.Stylesheet;
/*     */ import org.apache.xalan.templates.StylesheetRoot;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.functions.FuncExtFunction;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.objects.XString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExtensionHandlerExsltFunction
/*     */   extends ExtensionHandler
/*     */ {
/*     */   private String m_namespace;
/*     */   private StylesheetRoot m_stylesheet;
/*  51 */   private static final QName RESULTQNAME = new QName("http://exslt.org/functions", "result");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExtensionHandlerExsltFunction(String ns, StylesheetRoot stylesheet) {
/*  59 */     super(ns, "xml");
/*  60 */     this.m_namespace = ns;
/*  61 */     this.m_stylesheet = stylesheet;
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
/*     */   public void processElement(String localPart, ElemTemplateElement element, TransformerImpl transformer, Stylesheet stylesheetTree, Object methodKey) throws TransformerException, IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ElemExsltFunction getFunction(String funcName) {
/*  82 */     QName qname = new QName(this.m_namespace, funcName);
/*  83 */     ElemTemplate templ = this.m_stylesheet.getTemplateComposed(qname);
/*  84 */     if (templ != null && templ instanceof ElemExsltFunction) {
/*  85 */       return (ElemExsltFunction)templ;
/*     */     }
/*  87 */     return null;
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
/*     */   public boolean isFunctionAvailable(String funcName) {
/*  99 */     return (getFunction(funcName) != null);
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
/*     */   public boolean isElementAvailable(String elemName) {
/* 113 */     if (!(new QName(this.m_namespace, elemName)).equals(RESULTQNAME))
/*     */     {
/* 115 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 119 */     ElemTemplateElement elem = this.m_stylesheet.getFirstChildElem();
/* 120 */     while (elem != null && elem != this.m_stylesheet) {
/*     */       
/* 122 */       if (elem instanceof org.apache.xalan.templates.ElemExsltFuncResult && ancestorIsFunction(elem))
/* 123 */         return true; 
/* 124 */       ElemTemplateElement nextElem = elem.getFirstChildElem();
/* 125 */       if (nextElem == null)
/* 126 */         nextElem = elem.getNextSiblingElem(); 
/* 127 */       if (nextElem == null)
/* 128 */         nextElem = elem.getParentElem(); 
/* 129 */       elem = nextElem;
/*     */     } 
/*     */     
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean ancestorIsFunction(ElemTemplateElement child) {
/* 142 */     while (child.getParentElem() != null && !(child.getParentElem() instanceof StylesheetRoot)) {
/*     */       
/* 144 */       if (child.getParentElem() instanceof ElemExsltFunction)
/* 145 */         return true; 
/* 146 */       child = child.getParentElem();
/*     */     } 
/* 148 */     return false;
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
/*     */   public Object callFunction(String funcName, Vector args, Object methodKey, ExpressionContext exprContext) throws TransformerException {
/* 165 */     throw new TransformerException("This method should not be called.");
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
/*     */   public Object callFunction(FuncExtFunction extFunction, Vector args, ExpressionContext exprContext) throws TransformerException {
/* 183 */     ExpressionNode parent = extFunction.exprGetParent();
/* 184 */     while (parent != null && !(parent instanceof ElemTemplate))
/*     */     {
/* 186 */       parent = parent.exprGetParent();
/*     */     }
/*     */     
/* 189 */     ElemTemplate callerTemplate = (parent != null) ? (ElemTemplate)parent : null;
/*     */ 
/*     */     
/* 192 */     XObject[] methodArgs = new XObject[args.size()];
/*     */ 
/*     */     
/* 195 */     try { for (int i = 0; i < methodArgs.length; i++)
/*     */       {
/* 197 */         methodArgs[i] = XObject.create(args.elementAt(i));
/*     */       }
/*     */       
/* 200 */       ElemExsltFunction elemFunc = getFunction(extFunction.getFunctionName());
/*     */       
/* 202 */       if (null != elemFunc) {
/* 203 */         XPathContext context = exprContext.getXPathContext();
/* 204 */         TransformerImpl transformer = (TransformerImpl)context.getOwnerObject();
/* 205 */         transformer.pushCurrentFuncResult(null);
/*     */         
/* 207 */         elemFunc.execute(transformer, methodArgs);
/*     */         
/* 209 */         XObject val = (XObject)transformer.popCurrentFuncResult();
/* 210 */         return (val == null) ? new XString("") : val;
/*     */       } 
/*     */ 
/*     */       
/* 214 */       throw new TransformerException(XSLMessages.createMessage("ER_FUNCTION_NOT_FOUND", new Object[] { extFunction.getFunctionName() })); } catch (TransformerException e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 219 */       throw e; } catch (Exception e)
/*     */     
/*     */     { 
/*     */       
/* 223 */       throw new TransformerException(e); }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExtensionHandlerExsltFunction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */