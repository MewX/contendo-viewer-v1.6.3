/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.StackGuard;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xml.utils.IntStack;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.VariableStack;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ import org.apache.xpath.objects.XObject;
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
/*     */ public class ElemApplyTemplates
/*     */   extends ElemCallTemplate
/*     */ {
/*  57 */   private QName m_mode = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMode(QName mode) {
/*  66 */     this.m_mode = mode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QName getMode() {
/*  76 */     return this.m_mode;
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
/*     */   private boolean m_isDefaultTemplate = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsDefaultTemplate(boolean b) {
/* 104 */     this.m_isDefaultTemplate = b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSLToken() {
/* 115 */     return 50;
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
/* 126 */     super.compose(sroot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 136 */     return "apply-templates";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 152 */     transformer.pushCurrentTemplateRuleIsNull(false);
/*     */     
/* 154 */     boolean pushMode = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 162 */       QName mode = transformer.getMode();
/*     */       
/* 164 */       if (!this.m_isDefaultTemplate)
/*     */       {
/* 166 */         if ((null == mode && null != this.m_mode) || (null != mode && !mode.equals(this.m_mode))) {
/*     */ 
/*     */           
/* 169 */           pushMode = true;
/*     */           
/* 171 */           transformer.pushMode(this.m_mode);
/*     */         } 
/*     */       }
/* 174 */       if (TransformerImpl.S_DEBUG) {
/* 175 */         transformer.getTraceManager().fireTraceEvent(this);
/*     */       }
/* 177 */       transformSelectedNodes(transformer);
/*     */     }
/*     */     finally {
/*     */       
/* 181 */       if (TransformerImpl.S_DEBUG) {
/* 182 */         transformer.getTraceManager().fireTraceEndEvent(this);
/*     */       }
/* 184 */       if (pushMode) {
/* 185 */         transformer.popMode();
/*     */       }
/* 187 */       transformer.popCurrentTemplateRuleIsNull();
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
/*     */   public void transformSelectedNodes(TransformerImpl transformer) throws TransformerException {
/* 205 */     XPathContext xctxt = transformer.getXPathContext();
/* 206 */     int sourceNode = xctxt.getCurrentNode();
/* 207 */     DTMIterator sourceNodes = this.m_selectExpression.asIterator(xctxt, sourceNode);
/* 208 */     VariableStack vars = xctxt.getVarStack();
/* 209 */     int nParams = getParamElemCount();
/* 210 */     int thisframe = vars.getStackFrame();
/* 211 */     StackGuard guard = transformer.getStackGuard();
/* 212 */     boolean check = (guard.getRecursionLimit() > -1);
/*     */     
/* 214 */     boolean pushContextNodeListFlag = false;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 219 */       xctxt.pushCurrentNode(-1);
/* 220 */       xctxt.pushCurrentExpressionNode(-1);
/* 221 */       xctxt.pushSAXLocatorNull();
/* 222 */       transformer.pushElemTemplateElement(null);
/* 223 */       Vector keys = (this.m_sortElems == null) ? null : transformer.processSortKeys(this, sourceNode);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       if (null != keys) {
/* 229 */         sourceNodes = sortNodes(xctxt, keys, sourceNodes);
/*     */       }
/* 231 */       if (TransformerImpl.S_DEBUG)
/*     */       {
/* 233 */         transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "select", new XPath(this.m_selectExpression), (XObject)new XNodeSet(sourceNodes));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 238 */       SerializationHandler rth = transformer.getSerializationHandler();
/*     */       
/* 240 */       StylesheetRoot sroot = transformer.getStylesheet();
/* 241 */       TemplateList tl = sroot.getTemplateListComposed();
/* 242 */       boolean quiet = transformer.getQuietConflictWarnings();
/*     */ 
/*     */       
/* 245 */       DTM dtm = xctxt.getDTM(sourceNode);
/*     */       
/* 247 */       int argsFrame = -1;
/* 248 */       if (nParams > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 253 */         argsFrame = vars.link(nParams);
/* 254 */         vars.setStackFrame(thisframe);
/*     */         
/* 256 */         for (int i = 0; i < nParams; i++) {
/*     */           
/* 258 */           ElemWithParam ewp = this.m_paramElems[i];
/* 259 */           if (TransformerImpl.S_DEBUG)
/* 260 */             transformer.getTraceManager().fireTraceEvent(ewp); 
/* 261 */           XObject obj = ewp.getValue(transformer, sourceNode);
/* 262 */           if (TransformerImpl.S_DEBUG) {
/* 263 */             transformer.getTraceManager().fireTraceEndEvent(ewp);
/*     */           }
/* 265 */           vars.setLocalVariable(i, obj, argsFrame);
/*     */         } 
/* 267 */         vars.setStackFrame(argsFrame);
/*     */       } 
/*     */       
/* 270 */       xctxt.pushContextNodeList(sourceNodes);
/* 271 */       pushContextNodeListFlag = true;
/*     */       
/* 273 */       IntStack currentNodes = xctxt.getCurrentNodeStack();
/*     */       
/* 275 */       IntStack currentExpressionNodes = xctxt.getCurrentExpressionNodeStack();
/*     */ 
/*     */       
/*     */       int child;
/*     */       
/* 280 */       while (-1 != (child = sourceNodes.nextNode())) {
/*     */         boolean bool;
/* 282 */         currentNodes.setTop(child);
/* 283 */         currentExpressionNodes.setTop(child);
/*     */         
/* 285 */         if (xctxt.getDTM(child) != dtm)
/*     */         {
/* 287 */           dtm = xctxt.getDTM(child);
/*     */         }
/*     */         
/* 290 */         int exNodeType = dtm.getExpandedTypeID(child);
/*     */         
/* 292 */         int nodeType = dtm.getNodeType(child);
/*     */         
/* 294 */         QName mode = transformer.getMode();
/*     */         
/* 296 */         ElemTemplate template = tl.getTemplateFast(xctxt, child, exNodeType, mode, -1, quiet, dtm);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 301 */         if (null == template) {
/*     */           
/* 303 */           switch (nodeType) {
/*     */             
/*     */             case 1:
/*     */             case 11:
/* 307 */               template = sroot.getDefaultRule();
/*     */               break;
/*     */ 
/*     */ 
/*     */             
/*     */             case 2:
/*     */             case 3:
/*     */             case 4:
/* 315 */               transformer.pushPairCurrentMatched(sroot.getDefaultTextRule(), child);
/* 316 */               transformer.setCurrentElement(sroot.getDefaultTextRule());
/*     */               
/* 318 */               dtm.dispatchCharactersEvents(child, (ContentHandler)rth, false);
/* 319 */               transformer.popCurrentMatched();
/*     */               continue;
/*     */             case 9:
/* 322 */               template = sroot.getDefaultRootRule();
/*     */               break;
/*     */ 
/*     */             
/*     */             default:
/*     */               continue;
/*     */           } 
/*     */ 
/*     */         
/*     */         } else {
/* 332 */           transformer.setCurrentElement(template);
/*     */         } 
/*     */         
/* 335 */         transformer.pushPairCurrentMatched(template, child);
/* 336 */         if (check) {
/* 337 */           guard.checkForInfinateLoop();
/*     */         }
/*     */         
/* 340 */         if (template.m_frameSize > 0) {
/*     */           
/* 342 */           xctxt.pushRTFContext();
/* 343 */           bool = vars.getStackFrame();
/* 344 */           vars.link(template.m_frameSize);
/*     */ 
/*     */           
/* 347 */           if (template.m_inArgsSize > 0) {
/*     */             
/* 349 */             int paramIndex = 0;
/* 350 */             ElemTemplateElement elem = template.getFirstChildElem();
/* 351 */             while (null != elem) {
/*     */               
/* 353 */               if (41 == elem.getXSLToken()) {
/*     */                 
/* 355 */                 ElemParam ep = (ElemParam)elem;
/*     */                 
/*     */                 int i;
/* 358 */                 for (i = 0; i < nParams; i++) {
/*     */                   
/* 360 */                   ElemWithParam ewp = this.m_paramElems[i];
/* 361 */                   if (ewp.m_qnameID == ep.m_qnameID) {
/*     */                     
/* 363 */                     XObject obj = vars.getLocalVariable(i, argsFrame);
/* 364 */                     vars.setLocalVariable(paramIndex, obj);
/*     */                     break;
/*     */                   } 
/*     */                 } 
/* 368 */                 if (i == nParams) {
/* 369 */                   vars.setLocalVariable(paramIndex, null);
/*     */                 }
/*     */ 
/*     */                 
/* 373 */                 paramIndex++; elem = elem.getNextSiblingElem();
/*     */               } 
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } else {
/* 379 */           bool = false;
/*     */         } 
/*     */         
/* 382 */         if (TransformerImpl.S_DEBUG) {
/* 383 */           transformer.getTraceManager().fireTraceEvent(template);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 388 */         ElemTemplateElement t = template.m_firstChild;
/* 389 */         for (; t != null; t = t.m_nextSibling) {
/*     */           
/* 391 */           xctxt.setSAXLocator((SourceLocator)t);
/*     */           
/*     */           try {
/* 394 */             transformer.pushElemTemplateElement(t);
/* 395 */             t.execute(transformer);
/*     */           }
/*     */           finally {
/*     */             
/* 399 */             transformer.popElemTemplateElement();
/*     */           } 
/*     */         } 
/*     */         
/* 403 */         if (TransformerImpl.S_DEBUG) {
/* 404 */           transformer.getTraceManager().fireTraceEndEvent(template);
/*     */         }
/* 406 */         if (template.m_frameSize > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 423 */           vars.unlink(bool);
/* 424 */           xctxt.popRTFContext();
/*     */         } 
/*     */         
/* 427 */         transformer.popCurrentMatched();
/*     */       }
/*     */     
/*     */     }
/*     */     catch (SAXException se) {
/*     */       
/* 433 */       transformer.getErrorListener().fatalError(new TransformerException(se));
/*     */     }
/*     */     finally {
/*     */       
/* 437 */       if (TransformerImpl.S_DEBUG) {
/* 438 */         transformer.getTraceManager().fireSelectedEndEvent(sourceNode, this, "select", new XPath(this.m_selectExpression), (XObject)new XNodeSet(sourceNodes));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 443 */       if (nParams > 0)
/* 444 */         vars.unlink(thisframe); 
/* 445 */       xctxt.popSAXLocator();
/* 446 */       if (pushContextNodeListFlag) xctxt.popContextNodeList(); 
/* 447 */       transformer.popElemTemplateElement();
/* 448 */       xctxt.popCurrentExpressionNode();
/* 449 */       xctxt.popCurrentNode();
/* 450 */       sourceNodes.detach();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemApplyTemplates.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */