/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.extensions.ExtensionNamespaceSupport;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xpath.VariableStack;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemExsltFunction
/*     */   extends ElemTemplate
/*     */ {
/*     */   public int getXSLToken() {
/*  48 */     return 88;
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
/*     */   public String getNodeName() {
/*  60 */     return "function";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void execute(TransformerImpl transformer, XObject[] args) throws TransformerException {
/*  66 */     XPathContext xctxt = transformer.getXPathContext();
/*  67 */     VariableStack vars = xctxt.getVarStack();
/*     */ 
/*     */ 
/*     */     
/*  71 */     int thisFrame = vars.getStackFrame();
/*  72 */     int nextFrame = vars.link(this.m_frameSize);
/*     */     
/*  74 */     if (this.m_inArgsSize < args.length) {
/*  75 */       throw new TransformerException("function called with too many args");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  80 */     if (this.m_inArgsSize > 0) {
/*  81 */       vars.clearLocalSlots(0, this.m_inArgsSize);
/*     */       
/*  83 */       if (args.length > 0) {
/*  84 */         vars.setStackFrame(thisFrame);
/*  85 */         NodeList children = getChildNodes();
/*     */         
/*  87 */         for (int i = 0; i < args.length; i++) {
/*  88 */           Node child = children.item(i);
/*  89 */           if (children.item(i) instanceof ElemParam) {
/*  90 */             ElemParam param = (ElemParam)children.item(i);
/*  91 */             vars.setLocalVariable(param.getIndex(), args[i], nextFrame);
/*     */           } 
/*     */         } 
/*     */         
/*  95 */         vars.setStackFrame(nextFrame);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     if (TransformerImpl.S_DEBUG) {
/* 105 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/* 107 */     vars.setStackFrame(nextFrame);
/* 108 */     transformer.executeChildTemplates(this, true);
/*     */ 
/*     */     
/* 111 */     vars.unlink(thisFrame);
/*     */     
/* 113 */     if (TransformerImpl.S_DEBUG) {
/* 114 */       transformer.getTraceManager().fireTraceEndEvent(this);
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
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/* 129 */     super.compose(sroot);
/*     */ 
/*     */     
/* 132 */     String namespace = getName().getNamespace();
/* 133 */     String handlerClass = "org.apache.xalan.extensions.ExtensionHandlerExsltFunction";
/* 134 */     Object[] args = { namespace, sroot };
/* 135 */     ExtensionNamespaceSupport extNsSpt = new ExtensionNamespaceSupport(namespace, handlerClass, args);
/*     */     
/* 137 */     sroot.getExtensionNamespacesManager().registerExtension(extNsSpt);
/*     */ 
/*     */     
/* 140 */     if (!namespace.equals("http://exslt.org/functions")) {
/*     */       
/* 142 */       namespace = "http://exslt.org/functions";
/* 143 */       args = new Object[] { namespace, sroot };
/* 144 */       extNsSpt = new ExtensionNamespaceSupport(namespace, handlerClass, args);
/* 145 */       sroot.getExtensionNamespacesManager().registerExtension(extNsSpt);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemExsltFunction.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */