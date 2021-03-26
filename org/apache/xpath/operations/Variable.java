/*     */ package org.apache.xpath.operations;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.ElemVariable;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathVisitor;
/*     */ import org.apache.xpath.axes.PathComponent;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Variable
/*     */   extends Expression
/*     */   implements PathComponent
/*     */ {
/*     */   private boolean m_fixUpWasCalled = false;
/*     */   protected QName m_qname;
/*     */   protected int m_index;
/*     */   
/*     */   public void setIndex(int index) {
/*  65 */     this.m_index = index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex() {
/*  75 */     return this.m_index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsGlobal(boolean isGlobal) {
/*  85 */     this.m_isGlobal = isGlobal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getGlobal() {
/*  95 */     return this.m_isGlobal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_isGlobal = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static final java.lang.String PSUEDOVARNAMESPACE = "http://xml.apache.org/xalan/psuedovar";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/* 116 */     this.m_fixUpWasCalled = true;
/* 117 */     int sz = vars.size();
/*     */     
/* 119 */     for (int i = vars.size() - 1; i >= 0; i--) {
/*     */       
/* 121 */       QName qn = vars.elementAt(i);
/*     */       
/* 123 */       if (qn.equals(this.m_qname)) {
/*     */ 
/*     */         
/* 126 */         if (i < globalsSize) {
/*     */           
/* 128 */           this.m_isGlobal = true;
/* 129 */           this.m_index = i;
/*     */         }
/*     */         else {
/*     */           
/* 133 */           this.m_index = i - globalsSize;
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     java.lang.String msg = XPATHMessages.createXPATHMessage("ER_COULD_NOT_FIND_VAR", new Object[] { this.m_qname.toString() });
/*     */ 
/*     */     
/* 143 */     TransformerException te = new TransformerException(msg, (SourceLocator)this);
/*     */     
/* 145 */     throw new WrappedRuntimeException(te);
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
/*     */   public void setQName(QName qname) {
/* 157 */     this.m_qname = qname;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QName getQName() {
/* 167 */     return this.m_qname;
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
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 185 */     return execute(xctxt, false);
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
/*     */   public XObject execute(XPathContext xctxt, boolean destructiveOK) throws TransformerException {
/* 203 */     PrefixResolver xprefixResolver = xctxt.getNamespaceContext();
/*     */ 
/*     */ 
/*     */     
/* 207 */     if (this.m_fixUpWasCalled) {
/*     */       XObject xObject;
/*     */       XNodeSet xNodeSet;
/* 210 */       if (this.m_isGlobal) {
/* 211 */         xObject = xctxt.getVarStack().getGlobalVariable(xctxt, this.m_index, destructiveOK);
/*     */       } else {
/* 213 */         xObject = xctxt.getVarStack().getLocalVariable(xctxt, this.m_index, destructiveOK);
/*     */       } 
/* 215 */       if (null == xObject) {
/*     */ 
/*     */         
/* 218 */         warn(xctxt, "WG_ILLEGAL_VARIABLE_REFERENCE", new Object[] { this.m_qname.getLocalPart() });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 224 */         xNodeSet = new XNodeSet(xctxt.getDTMManager());
/*     */       } 
/*     */       
/* 227 */       return (XObject)xNodeSet;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 233 */     synchronized (this) {
/*     */       
/* 235 */       ElemVariable vvar = getElemVariable();
/* 236 */       if (null != vvar) {
/*     */         
/* 238 */         this.m_index = vvar.getIndex();
/* 239 */         this.m_isGlobal = vvar.getIsTopLevel();
/* 240 */         this.m_fixUpWasCalled = true;
/* 241 */         return execute(xctxt);
/*     */       } 
/*     */     } 
/* 244 */     throw new TransformerException(XPATHMessages.createXPATHMessage("ER_VAR_NOT_RESOLVABLE", new Object[] { this.m_qname.toString() }));
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
/*     */   public ElemVariable getElemVariable() {
/* 262 */     ExpressionNode owner = getExpressionOwner();
/*     */     
/* 264 */     if (null != owner && owner instanceof ElemTemplateElement) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 269 */       ElemTemplateElement prev = (ElemTemplateElement)owner;
/*     */ 
/*     */       
/* 272 */       if (!(prev instanceof org.apache.xalan.templates.Stylesheet))
/*     */       {
/* 274 */         while (!(prev.getParentNode() instanceof org.apache.xalan.templates.Stylesheet)) {
/*     */           
/* 276 */           ElemTemplateElement savedprev = prev;
/*     */           
/* 278 */           while (null != (prev = prev.getPreviousSiblingElem())) {
/*     */             
/* 280 */             if (prev instanceof ElemVariable) {
/*     */               
/* 282 */               ElemVariable vvar = (ElemVariable)prev;
/*     */               
/* 284 */               if (vvar.getName().equals(this.m_qname))
/*     */               {
/* 286 */                 return vvar;
/*     */               }
/*     */             } 
/*     */           } 
/* 290 */           prev = savedprev.getParentElem();
/*     */         } 
/*     */       }
/*     */       
/* 294 */       ElemVariable elemVariable = prev.getStylesheetRoot().getVariableOrParamComposed(this.m_qname);
/* 295 */       if (null != elemVariable)
/*     */       {
/* 297 */         return elemVariable;
/*     */       }
/*     */     } 
/*     */     
/* 301 */     return null;
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
/*     */   public boolean isStableNumber() {
/* 315 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnalysisBits() {
/* 324 */     ElemVariable vvar = getElemVariable();
/* 325 */     if (null != vvar) {
/*     */       
/* 327 */       XPath xpath = vvar.getSelect();
/* 328 */       if (null != xpath) {
/*     */         
/* 330 */         Expression expr = xpath.getExpression();
/* 331 */         if (null != expr && expr instanceof PathComponent)
/*     */         {
/* 333 */           return ((PathComponent)expr).getAnalysisBits();
/*     */         }
/*     */       } 
/*     */     } 
/* 337 */     return 67108864;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 346 */     visitor.visitVariableRef(owner, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deepEquals(Expression expr) {
/* 353 */     if (!isSameClass(expr)) {
/* 354 */       return false;
/*     */     }
/* 356 */     if (!this.m_qname.equals(((Variable)expr).m_qname)) {
/* 357 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 361 */     if (getElemVariable() != ((Variable)expr).getElemVariable()) {
/* 362 */       return false;
/*     */     }
/* 364 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPsuedoVarRef() {
/* 375 */     java.lang.String ns = this.m_qname.getNamespaceURI();
/* 376 */     if (null != ns && ns.equals("http://xml.apache.org/xalan/psuedovar"))
/*     */     {
/* 378 */       if (this.m_qname.getLocalName().startsWith("#"))
/* 379 */         return true; 
/*     */     }
/* 381 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/operations/Variable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */