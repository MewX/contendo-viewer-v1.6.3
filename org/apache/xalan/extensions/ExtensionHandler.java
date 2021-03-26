/*    */ package org.apache.xalan.extensions;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Vector;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xalan.templates.ElemTemplateElement;
/*    */ import org.apache.xalan.templates.Stylesheet;
/*    */ import org.apache.xalan.transformer.TransformerImpl;
/*    */ import org.apache.xpath.functions.FuncExtFunction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ExtensionHandler
/*    */ {
/*    */   protected String m_namespaceUri;
/*    */   protected String m_scriptLang;
/*    */   
/*    */   static Class getClassForName(String className) throws ClassNotFoundException {
/* 59 */     if (className.equals("org.apache.xalan.xslt.extensions.Redirect")) {
/* 60 */       className = "org.apache.xalan.lib.Redirect";
/*    */     }
/*    */     
/* 63 */     return ObjectFactory.findProviderClass(className, ObjectFactory.findClassLoader(), true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ExtensionHandler(String namespaceUri, String scriptLang) {
/* 76 */     this.m_namespaceUri = namespaceUri;
/* 77 */     this.m_scriptLang = scriptLang;
/*    */   }
/*    */   
/*    */   public abstract boolean isFunctionAvailable(String paramString);
/*    */   
/*    */   public abstract boolean isElementAvailable(String paramString);
/*    */   
/*    */   public abstract Object callFunction(String paramString, Vector paramVector, Object paramObject, ExpressionContext paramExpressionContext) throws TransformerException;
/*    */   
/*    */   public abstract Object callFunction(FuncExtFunction paramFuncExtFunction, Vector paramVector, ExpressionContext paramExpressionContext) throws TransformerException;
/*    */   
/*    */   public abstract void processElement(String paramString, ElemTemplateElement paramElemTemplateElement, TransformerImpl paramTransformerImpl, Stylesheet paramStylesheet, Object paramObject) throws TransformerException, IOException;
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExtensionHandler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */