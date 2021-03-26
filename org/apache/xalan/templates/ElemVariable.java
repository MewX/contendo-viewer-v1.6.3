/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.processor.TransformerFactoryImpl;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.objects.XRTreeFrag;
/*     */ import org.apache.xpath.objects.XRTreeFragSelectWrapper;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemVariable
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   protected int m_index;
/*  64 */   int m_frameSize = -1;
/*     */   
/*     */   private XPath m_selectPattern;
/*     */   
/*     */   protected QName m_qname;
/*     */ 
/*     */   
/*     */   public ElemVariable() {}
/*     */   
/*     */   public void setIndex(int index) {
/*  74 */     this.m_index = index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex() {
/*  84 */     return this.m_index;
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
/*     */   public void setSelect(XPath v) {
/* 105 */     this.m_selectPattern = v;
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
/*     */   public XPath getSelect() {
/* 120 */     return this.m_selectPattern;
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
/*     */   public void setName(QName v) {
/* 141 */     this.m_qname = v;
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
/*     */   public QName getName() {
/* 156 */     return this.m_qname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_isTopLevel = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsTopLevel(boolean v) {
/* 174 */     this.m_isTopLevel = v;
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
/*     */   public boolean getIsTopLevel() {
/* 186 */     return this.m_isTopLevel;
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
/*     */   public int getXSLToken() {
/* 198 */     return 73;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 208 */     return "variable";
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
/*     */   public ElemVariable(ElemVariable param) throws TransformerException {
/* 221 */     this.m_selectPattern = param.m_selectPattern;
/* 222 */     this.m_qname = param.m_qname;
/* 223 */     this.m_isTopLevel = param.m_isTopLevel;
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 242 */     if (TransformerImpl.S_DEBUG) {
/* 243 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/* 245 */     int sourceNode = transformer.getXPathContext().getCurrentNode();
/*     */     
/* 247 */     XObject var = getValue(transformer, sourceNode);
/*     */ 
/*     */     
/* 250 */     transformer.getXPathContext().getVarStack().setLocalVariable(this.m_index, var);
/*     */     
/* 252 */     if (TransformerImpl.S_DEBUG) {
/* 253 */       transformer.getTraceManager().fireTraceEndEvent(this);
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
/*     */   public XObject getValue(TransformerImpl transformer, int sourceNode) throws TransformerException {
/*     */     XRTreeFrag xRTreeFrag;
/* 271 */     XPathContext xctxt = transformer.getXPathContext();
/*     */     
/* 273 */     xctxt.pushCurrentNode(sourceNode);
/*     */ 
/*     */     
/*     */     try {
/* 277 */       if (null != this.m_selectPattern) {
/*     */         
/* 279 */         XObject var = this.m_selectPattern.execute(xctxt, sourceNode, this);
/*     */         
/* 281 */         var.allowDetachToRelease(false);
/*     */         
/* 283 */         if (TransformerImpl.S_DEBUG) {
/* 284 */           transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "select", this.m_selectPattern, var);
/*     */         }
/*     */       }
/* 287 */       else if (null == getFirstChildElem()) {
/*     */         
/* 289 */         XString xString = XString.EMPTYSTRING;
/*     */       } else {
/*     */         int i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 308 */           if (this.m_parentNode instanceof Stylesheet) {
/* 309 */             i = transformer.transformToGlobalRTF(this);
/*     */           } else {
/* 311 */             i = transformer.transformToRTF(this);
/*     */           } 
/*     */         } finally {}
/*     */ 
/*     */ 
/*     */         
/* 317 */         xRTreeFrag = new XRTreeFrag(i, xctxt, this);
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/* 322 */       xctxt.popCurrentNode();
/*     */     } 
/*     */     
/* 325 */     return (XObject)xRTreeFrag;
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
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/* 338 */     if (null == this.m_selectPattern && TransformerFactoryImpl.m_optimize) {
/*     */ 
/*     */       
/* 341 */       XPath newSelect = rewriteChildToExpression(this);
/* 342 */       if (null != newSelect) {
/* 343 */         this.m_selectPattern = newSelect;
/*     */       }
/*     */     } 
/* 346 */     StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/*     */ 
/*     */ 
/*     */     
/* 350 */     Vector vnames = cstate.getVariableNames();
/* 351 */     if (null != this.m_selectPattern) {
/* 352 */       this.m_selectPattern.fixupVariables(vnames, cstate.getGlobalsSize());
/*     */     }
/*     */ 
/*     */     
/* 356 */     if (!(this.m_parentNode instanceof Stylesheet) && this.m_qname != null) {
/*     */       
/* 358 */       this.m_index = cstate.addVariableName(this.m_qname) - cstate.getGlobalsSize();
/*     */     }
/* 360 */     else if (this.m_parentNode instanceof Stylesheet) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 365 */       cstate.resetStackFrameSize();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 370 */     super.compose(sroot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCompose(StylesheetRoot sroot) throws TransformerException {
/* 380 */     super.endCompose(sroot);
/* 381 */     if (this.m_parentNode instanceof Stylesheet) {
/*     */       
/* 383 */       StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/* 384 */       this.m_frameSize = cstate.getFrameSize();
/* 385 */       cstate.resetStackFrameSize();
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
/*     */   static XPath rewriteChildToExpression(ElemTemplateElement varElem) throws TransformerException {
/* 415 */     ElemTemplateElement t = varElem.getFirstChildElem();
/*     */ 
/*     */ 
/*     */     
/* 419 */     if (null != t && null == t.getNextSiblingElem()) {
/*     */       
/* 421 */       int etype = t.getXSLToken();
/*     */       
/* 423 */       if (30 == etype) {
/*     */         
/* 425 */         ElemValueOf valueof = (ElemValueOf)t;
/*     */ 
/*     */         
/* 428 */         if (!valueof.getDisableOutputEscaping() && valueof.getDOMBackPointer() == null)
/*     */         {
/*     */           
/* 431 */           varElem.m_firstChild = null;
/*     */           
/* 433 */           return new XPath((Expression)new XRTreeFragSelectWrapper(valueof.getSelect().getExpression()));
/*     */         }
/*     */       
/* 436 */       } else if (78 == etype) {
/*     */         
/* 438 */         ElemTextLiteral lit = (ElemTextLiteral)t;
/*     */         
/* 440 */         if (!lit.getDisableOutputEscaping() && lit.getDOMBackPointer() == null) {
/*     */ 
/*     */           
/* 443 */           String str = lit.getNodeValue();
/* 444 */           XString xstr = new XString(str);
/*     */           
/* 446 */           varElem.m_firstChild = null;
/*     */           
/* 448 */           return new XPath((Expression)new XRTreeFragSelectWrapper((Expression)xstr));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 453 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recompose(StylesheetRoot root) {
/* 463 */     root.recomposeVariables(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParentElem(ElemTemplateElement p) {
/* 473 */     super.setParentElem(p);
/* 474 */     p.m_hasVariableDecl = true;
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
/*     */   protected boolean accept(XSLTVisitor visitor) {
/* 486 */     return visitor.visitVariableOrParamDecl(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 496 */     if (null != this.m_selectPattern)
/* 497 */       this.m_selectPattern.getExpression().callVisitors((ExpressionOwner)this.m_selectPattern, visitor); 
/* 498 */     super.callChildVisitors(visitor, callAttrs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPsuedoVar() {
/* 507 */     String ns = this.m_qname.getNamespaceURI();
/* 508 */     if (null != ns && ns.equals("http://xml.apache.org/xalan/psuedovar"))
/*     */     {
/* 510 */       if (this.m_qname.getLocalName().startsWith("#"))
/* 511 */         return true; 
/*     */     }
/* 513 */     return false;
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
/*     */   public ElemTemplateElement appendChild(ElemTemplateElement elem) {
/* 528 */     if (this.m_selectPattern != null) {
/*     */       
/* 530 */       error("ER_CANT_HAVE_CONTENT_AND_SELECT", new Object[] { "xsl:" + getNodeName() });
/*     */       
/* 532 */       return null;
/*     */     } 
/* 534 */     return super.appendChild(elem);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemVariable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */