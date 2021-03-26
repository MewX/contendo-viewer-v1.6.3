/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.VariableStack;
/*     */ import org.apache.xpath.XPathContext;
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
/*     */ public class ElemCallTemplate
/*     */   extends ElemForEach
/*     */ {
/*  51 */   public QName m_templateName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(QName name) {
/*  62 */     this.m_templateName = name;
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
/*     */   public QName getName() {
/*  74 */     return this.m_templateName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private ElemTemplate m_template = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSLToken() {
/*  91 */     return 17;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 101 */     return "call-template";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/* 112 */     super.compose(sroot);
/*     */ 
/*     */ 
/*     */     
/* 116 */     int length = getParamElemCount();
/* 117 */     for (int i = 0; i < length; i++) {
/*     */       
/* 119 */       ElemWithParam ewp = getParamElem(i);
/* 120 */       ewp.compose(sroot);
/*     */     } 
/*     */     
/* 123 */     if (null != this.m_templateName && null == this.m_template) {
/* 124 */       this.m_template = getStylesheetRoot().getTemplateComposed(this.m_templateName);
/*     */ 
/*     */       
/* 127 */       if (null == this.m_template) {
/* 128 */         String themsg = XSLMessages.createMessage("ER_ELEMTEMPLATEELEM_ERR", new Object[] { this.m_templateName });
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 133 */         throw new TransformerException(themsg, this);
/*     */       } 
/*     */ 
/*     */       
/* 137 */       length = getParamElemCount();
/* 138 */       for (int j = 0; j < length; j++) {
/*     */         
/* 140 */         ElemWithParam ewp = getParamElem(j);
/* 141 */         ewp.m_index = -1;
/*     */ 
/*     */         
/* 144 */         int etePos = 0;
/* 145 */         ElemTemplateElement ete = this.m_template.getFirstChildElem();
/* 146 */         while (null != ete) {
/*     */           
/* 148 */           if (ete.getXSLToken() == 41) {
/*     */             
/* 150 */             ElemParam ep = (ElemParam)ete;
/* 151 */             if (ep.getName().equals(ewp.getName()))
/*     */             {
/* 153 */               ewp.m_index = etePos;
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 158 */             etePos++;
/*     */             ete = ete.getNextSiblingElem();
/*     */           } 
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCompose(StylesheetRoot sroot) throws TransformerException {
/* 170 */     int length = getParamElemCount();
/* 171 */     for (int i = 0; i < length; i++) {
/*     */       
/* 173 */       ElemWithParam ewp = getParamElem(i);
/* 174 */       ewp.endCompose(sroot);
/*     */     } 
/*     */     
/* 177 */     super.endCompose(sroot);
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 195 */     if (TransformerImpl.S_DEBUG) {
/* 196 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/* 198 */     if (null != this.m_template) {
/*     */       
/* 200 */       XPathContext xctxt = transformer.getXPathContext();
/* 201 */       VariableStack vars = xctxt.getVarStack();
/*     */       
/* 203 */       int thisframe = vars.getStackFrame();
/* 204 */       int nextFrame = vars.link(this.m_template.m_frameSize);
/*     */ 
/*     */ 
/*     */       
/* 208 */       if (this.m_template.m_inArgsSize > 0) {
/*     */         
/* 210 */         vars.clearLocalSlots(0, this.m_template.m_inArgsSize);
/*     */         
/* 212 */         if (null != this.m_paramElems) {
/*     */           
/* 214 */           int currentNode = xctxt.getCurrentNode();
/* 215 */           vars.setStackFrame(thisframe);
/* 216 */           int size = this.m_paramElems.length;
/*     */           
/* 218 */           for (int i = 0; i < size; i++) {
/*     */             
/* 220 */             ElemWithParam ewp = this.m_paramElems[i];
/* 221 */             if (ewp.m_index >= 0) {
/*     */               
/* 223 */               if (TransformerImpl.S_DEBUG)
/* 224 */                 transformer.getTraceManager().fireTraceEvent(ewp); 
/* 225 */               XObject obj = ewp.getValue(transformer, currentNode);
/* 226 */               if (TransformerImpl.S_DEBUG) {
/* 227 */                 transformer.getTraceManager().fireTraceEndEvent(ewp);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 232 */               vars.setLocalVariable(ewp.m_index, obj, nextFrame);
/*     */             } 
/*     */           } 
/* 235 */           vars.setStackFrame(nextFrame);
/*     */         } 
/*     */       } 
/*     */       
/* 239 */       SourceLocator savedLocator = xctxt.getSAXLocator();
/*     */ 
/*     */       
/*     */       try {
/* 243 */         xctxt.setSAXLocator((SourceLocator)this.m_template);
/*     */ 
/*     */         
/* 246 */         transformer.pushElemTemplateElement(this.m_template);
/* 247 */         this.m_template.execute(transformer);
/*     */       }
/*     */       finally {
/*     */         
/* 251 */         transformer.popElemTemplateElement();
/* 252 */         xctxt.setSAXLocator(savedLocator);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 262 */         vars.unlink(thisframe);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 267 */       transformer.getMsgMgr().error((SourceLocator)this, "ER_TEMPLATE_NOT_FOUND", new Object[] { this.m_templateName });
/*     */     } 
/*     */ 
/*     */     
/* 271 */     if (TransformerImpl.S_DEBUG) {
/* 272 */       transformer.getTraceManager().fireTraceEndEvent(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 278 */   protected ElemWithParam[] m_paramElems = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getParamElemCount() {
/* 286 */     return (this.m_paramElems == null) ? 0 : this.m_paramElems.length;
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
/*     */   public ElemWithParam getParamElem(int i) {
/* 298 */     return this.m_paramElems[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParamElem(ElemWithParam ParamElem) {
/* 308 */     if (null == this.m_paramElems) {
/*     */       
/* 310 */       this.m_paramElems = new ElemWithParam[1];
/* 311 */       this.m_paramElems[0] = ParamElem;
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 317 */       int length = this.m_paramElems.length;
/* 318 */       ElemWithParam[] ewp = new ElemWithParam[length + 1];
/* 319 */       System.arraycopy(this.m_paramElems, 0, ewp, 0, length);
/* 320 */       this.m_paramElems = ewp;
/* 321 */       ewp[length] = ParamElem;
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
/*     */   public ElemTemplateElement appendChild(ElemTemplateElement newChild) {
/* 342 */     int type = newChild.getXSLToken();
/*     */     
/* 344 */     if (2 == type)
/*     */     {
/* 346 */       setParamElem((ElemWithParam)newChild);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 351 */     return super.appendChild(newChild);
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
/*     */   public void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 371 */     super.callChildVisitors(visitor, callAttrs);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemCallTemplate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */