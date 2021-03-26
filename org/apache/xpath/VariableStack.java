/*     */ package org.apache.xpath;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xalan.templates.ElemVariable;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.QName;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VariableStack
/*     */   implements Cloneable
/*     */ {
/*     */   public static final int CLEARLIMITATION = 1024;
/*     */   XObject[] _stackFrames;
/*     */   int _frameTop;
/*     */   private int _currentFrameBottom;
/*     */   int[] _links;
/*     */   int _linksTop;
/*     */   
/*     */   public VariableStack() {
/*  73 */     this._stackFrames = new XObject[8192];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     this._links = new int[4096];
/*     */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized Object clone() throws CloneNotSupportedException {
/*     */     VariableStack vs = (VariableStack)super.clone();
/*     */     vs._stackFrames = (XObject[])this._stackFrames.clone();
/*     */     vs._links = (int[])this._links.clone();
/*     */     return vs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public XObject elementAt(int i) {
/* 109 */     return this._stackFrames[i];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 119 */     return this._frameTop;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 130 */     this._frameTop = 0;
/* 131 */     this._linksTop = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     this._links[this._linksTop++] = 0;
/* 137 */     this._stackFrames = new XObject[this._stackFrames.length];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStackFrame(int sf) {
/* 147 */     this._currentFrameBottom = sf;
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
/*     */   public int getStackFrame() {
/* 159 */     return this._currentFrameBottom;
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
/*     */   public int link(int size) {
/* 180 */     this._currentFrameBottom = this._frameTop;
/* 181 */     this._frameTop += size;
/*     */     
/* 183 */     if (this._frameTop >= this._stackFrames.length) {
/*     */       
/* 185 */       XObject[] newsf = new XObject[this._stackFrames.length + 4096 + size];
/*     */       
/* 187 */       System.arraycopy(this._stackFrames, 0, newsf, 0, this._stackFrames.length);
/*     */       
/* 189 */       this._stackFrames = newsf;
/*     */     } 
/*     */     
/* 192 */     if (this._linksTop + 1 >= this._links.length) {
/*     */       
/* 194 */       int[] newlinks = new int[this._links.length + 2048];
/*     */       
/* 196 */       System.arraycopy(this._links, 0, newlinks, 0, this._links.length);
/*     */       
/* 198 */       this._links = newlinks;
/*     */     } 
/*     */     
/* 201 */     this._links[this._linksTop++] = this._currentFrameBottom;
/*     */     
/* 203 */     return this._currentFrameBottom;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unlink() {
/* 212 */     this._frameTop = this._links[--this._linksTop];
/* 213 */     this._currentFrameBottom = this._links[this._linksTop - 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unlink(int currentFrame) {
/* 224 */     this._frameTop = this._links[--this._linksTop];
/* 225 */     this._currentFrameBottom = currentFrame;
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
/*     */   public void setLocalVariable(int index, XObject val) {
/* 239 */     this._stackFrames[index + this._currentFrameBottom] = val;
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
/*     */   public void setLocalVariable(int index, XObject val, int stackFrame) {
/* 254 */     this._stackFrames[index + stackFrame] = val;
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
/*     */   public XObject getLocalVariable(XPathContext xctxt, int index) throws TransformerException {
/* 275 */     index += this._currentFrameBottom;
/*     */     
/* 277 */     XObject val = this._stackFrames[index];
/*     */     
/* 279 */     if (null == val) {
/* 280 */       throw new TransformerException(XPATHMessages.createXPATHMessage("ER_VARIABLE_ACCESSED_BEFORE_BIND", null), xctxt.getSAXLocator());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 285 */     if (val.getType() == 600) {
/* 286 */       this._stackFrames[index] = val.execute(xctxt); return val.execute(xctxt);
/*     */     } 
/* 288 */     return val;
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
/*     */   public XObject getLocalVariable(int index, int frame) throws TransformerException {
/* 307 */     index += frame;
/*     */     
/* 309 */     XObject val = this._stackFrames[index];
/*     */     
/* 311 */     return val;
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
/*     */   public XObject getLocalVariable(XPathContext xctxt, int index, boolean destructiveOK) throws TransformerException {
/* 332 */     index += this._currentFrameBottom;
/*     */     
/* 334 */     XObject val = this._stackFrames[index];
/*     */     
/* 336 */     if (null == val) {
/* 337 */       throw new TransformerException(XPATHMessages.createXPATHMessage("ER_VARIABLE_ACCESSED_BEFORE_BIND", null), xctxt.getSAXLocator());
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 342 */     if (val.getType() == 600) {
/* 343 */       this._stackFrames[index] = val.execute(xctxt); return val.execute(xctxt);
/*     */     } 
/* 345 */     return destructiveOK ? val : val.getFresh();
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
/*     */   public boolean isLocalSet(int index) throws TransformerException {
/* 360 */     return (this._stackFrames[index + this._currentFrameBottom] != null);
/*     */   }
/*     */ 
/*     */   
/* 364 */   private static XObject[] m_nulls = new XObject[1024];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearLocalSlots(int start, int len) {
/* 378 */     start += this._currentFrameBottom;
/*     */     
/* 380 */     System.arraycopy(m_nulls, 0, this._stackFrames, start, len);
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
/*     */   public void setGlobalVariable(int index, XObject val) {
/* 394 */     this._stackFrames[index] = val;
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
/*     */   public XObject getGlobalVariable(XPathContext xctxt, int index) throws TransformerException {
/* 415 */     XObject val = this._stackFrames[index];
/*     */ 
/*     */     
/* 418 */     if (val.getType() == 600) {
/* 419 */       this._stackFrames[index] = val.execute(xctxt); return val.execute(xctxt);
/*     */     } 
/* 421 */     return val;
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
/*     */   public XObject getGlobalVariable(XPathContext xctxt, int index, boolean destructiveOK) throws TransformerException {
/* 442 */     XObject val = this._stackFrames[index];
/*     */ 
/*     */     
/* 445 */     if (val.getType() == 600) {
/* 446 */       this._stackFrames[index] = val.execute(xctxt); return val.execute(xctxt);
/*     */     } 
/* 448 */     return destructiveOK ? val : val.getFresh();
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
/*     */   public XObject getVariableOrParam(XPathContext xctxt, QName qname) throws TransformerException {
/* 469 */     PrefixResolver prefixResolver = xctxt.getNamespaceContext();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 478 */     if (prefixResolver instanceof ElemTemplateElement) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 483 */       ElemTemplateElement prev = (ElemTemplateElement)prefixResolver;
/*     */ 
/*     */       
/* 486 */       if (!(prev instanceof org.apache.xalan.templates.Stylesheet))
/*     */       {
/* 488 */         while (!(prev.getParentNode() instanceof org.apache.xalan.templates.Stylesheet)) {
/*     */           
/* 490 */           ElemTemplateElement savedprev = prev;
/*     */           
/* 492 */           while (null != (prev = prev.getPreviousSiblingElem())) {
/*     */             
/* 494 */             if (prev instanceof ElemVariable) {
/*     */               
/* 496 */               ElemVariable vvar = (ElemVariable)prev;
/*     */               
/* 498 */               if (vvar.getName().equals(qname))
/* 499 */                 return getLocalVariable(xctxt, vvar.getIndex()); 
/*     */             } 
/*     */           } 
/* 502 */           prev = savedprev.getParentElem();
/*     */         } 
/*     */       }
/*     */       
/* 506 */       ElemVariable elemVariable = prev.getStylesheetRoot().getVariableOrParamComposed(qname);
/* 507 */       if (null != elemVariable) {
/* 508 */         return getGlobalVariable(xctxt, elemVariable.getIndex());
/*     */       }
/*     */     } 
/* 511 */     throw new TransformerException(XPATHMessages.createXPATHMessage("ER_VAR_NOT_RESOLVABLE", new Object[] { qname.toString() }));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/VariableStack.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */