/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemTemplate
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   private String m_publicId;
/*     */   private String m_systemId;
/*     */   private Stylesheet m_stylesheet;
/*     */   
/*     */   public String getPublicId() {
/*  69 */     return this.m_publicId;
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
/*     */   public String getSystemId() {
/*  84 */     return this.m_systemId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocaterInfo(SourceLocator locator) {
/*  95 */     this.m_publicId = locator.getPublicId();
/*  96 */     this.m_systemId = locator.getSystemId();
/*     */     
/*  98 */     super.setLocaterInfo(locator);
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
/*     */   public StylesheetComposed getStylesheetComposed() {
/* 117 */     return this.m_stylesheet.getStylesheetComposed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stylesheet getStylesheet() {
/* 127 */     return this.m_stylesheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStylesheet(Stylesheet sheet) {
/* 137 */     this.m_stylesheet = sheet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StylesheetRoot getStylesheetRoot() {
/* 147 */     return this.m_stylesheet.getStylesheetRoot();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   private XPath m_matchPattern = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMatch(XPath v) {
/* 170 */     this.m_matchPattern = v;
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
/*     */   public XPath getMatch() {
/* 186 */     return this.m_matchPattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   private QName m_name = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private QName m_mode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(QName v) {
/* 206 */     this.m_name = v;
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
/*     */   public QName getName() {
/* 220 */     return this.m_name;
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
/*     */   public void setMode(QName v) {
/* 241 */     this.m_mode = v;
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
/*     */   public QName getMode() {
/* 255 */     return this.m_mode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   private double m_priority = Double.NEGATIVE_INFINITY;
/*     */ 
/*     */   
/*     */   public int m_frameSize;
/*     */ 
/*     */   
/*     */   int m_inArgsSize;
/*     */ 
/*     */   
/*     */   private int[] m_argsQNameIDs;
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPriority(double v) {
/* 277 */     this.m_priority = v;
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
/*     */   public double getPriority() {
/* 292 */     return this.m_priority;
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
/* 303 */     return 19;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 313 */     return "template";
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
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/* 344 */     super.compose(sroot);
/* 345 */     StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/* 346 */     Vector vnames = cstate.getVariableNames();
/* 347 */     if (null != this.m_matchPattern) {
/* 348 */       this.m_matchPattern.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
/*     */     }
/* 350 */     cstate.resetStackFrameSize();
/* 351 */     this.m_inArgsSize = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCompose(StylesheetRoot sroot) throws TransformerException {
/* 359 */     StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/* 360 */     super.endCompose(sroot);
/* 361 */     this.m_frameSize = cstate.getFrameSize();
/*     */     
/* 363 */     cstate.resetStackFrameSize();
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
/* 381 */     XPathContext xctxt = transformer.getXPathContext();
/*     */     
/* 383 */     transformer.getStackGuard().checkForInfinateLoop();
/*     */     
/* 385 */     xctxt.pushRTFContext();
/*     */     
/* 387 */     if (TransformerImpl.S_DEBUG) {
/* 388 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 393 */     transformer.executeChildTemplates(this, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 404 */     if (TransformerImpl.S_DEBUG) {
/* 405 */       transformer.getTraceManager().fireTraceEndEvent(this);
/*     */     }
/* 407 */     xctxt.popRTFContext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recompose(StylesheetRoot root) {
/* 417 */     root.recomposeTemplates(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemTemplate.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */