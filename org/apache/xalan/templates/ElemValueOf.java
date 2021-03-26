/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
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
/*     */ public class ElemValueOf
/*     */   extends ElemTemplateElement
/*     */ {
/*  52 */   private XPath m_selectExpression = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean m_isDot = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelect(XPath v) {
/*  71 */     if (null != v) {
/*     */       
/*  73 */       String s = v.getPatternString();
/*     */       
/*  75 */       this.m_isDot = (null != s && s.equals("."));
/*     */     } 
/*     */     
/*  78 */     this.m_selectExpression = v;
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
/*     */   public XPath getSelect() {
/*  91 */     return this.m_selectExpression;
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
/*     */   private boolean m_disableOutputEscaping = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisableOutputEscaping(boolean v) {
/* 122 */     this.m_disableOutputEscaping = v;
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
/*     */   public boolean getDisableOutputEscaping() {
/* 147 */     return this.m_disableOutputEscaping;
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
/* 159 */     return 30;
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
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/* 175 */     super.compose(sroot);
/*     */     
/* 177 */     Vector vnames = sroot.getComposeState().getVariableNames();
/*     */     
/* 179 */     if (null != this.m_selectExpression) {
/* 180 */       this.m_selectExpression.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 191 */     return "value-of";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 214 */     XPathContext xctxt = transformer.getXPathContext();
/* 215 */     SerializationHandler rth = transformer.getResultTreeHandler();
/*     */     
/* 217 */     if (TransformerImpl.S_DEBUG) {
/* 218 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     try { xctxt.pushNamespaceContext(this);
/*     */       
/* 251 */       int current = xctxt.getCurrentNode();
/*     */       
/* 253 */       xctxt.pushCurrentNodeAndExpression(current, current);
/*     */       
/* 255 */       if (this.m_disableOutputEscaping) {
/* 256 */         rth.processingInstruction("javax.xml.transform.disable-output-escaping", "");
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 261 */       try { Expression expr = this.m_selectExpression.getExpression();
/*     */         
/* 263 */         if (TransformerImpl.S_DEBUG)
/*     */         {
/* 265 */           XObject obj = expr.execute(xctxt);
/*     */           
/* 267 */           transformer.getTraceManager().fireSelectedEvent(current, this, "select", this.m_selectExpression, obj);
/*     */           
/* 269 */           obj.dispatchCharactersEvents((ContentHandler)rth);
/*     */         }
/*     */         else
/*     */         {
/* 273 */           expr.executeCharsToContentHandler(xctxt, (ContentHandler)rth);
/*     */         }
/*     */          }
/*     */       finally
/*     */       
/* 278 */       { if (this.m_disableOutputEscaping) {
/* 279 */           rth.processingInstruction("javax.xml.transform.enable-output-escaping", "");
/*     */         }
/*     */         
/* 282 */         xctxt.popNamespaceContext();
/* 283 */         xctxt.popCurrentNodeAndExpression(); }  } catch (SAXException se)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */       
/* 289 */       throw new TransformerException(se); } catch (RuntimeException re)
/*     */     
/*     */     { 
/* 292 */       TransformerException te = new TransformerException(re);
/* 293 */       te.setLocator((SourceLocator)this);
/* 294 */       throw te; }
/*     */     
/*     */     finally
/*     */     
/* 298 */     { if (TransformerImpl.S_DEBUG) {
/* 299 */         transformer.getTraceManager().fireTraceEndEvent(this);
/*     */       } }
/*     */   
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
/*     */   public ElemTemplateElement appendChild(ElemTemplateElement newChild) {
/* 315 */     error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), getNodeName() });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 320 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 329 */     if (callAttrs)
/* 330 */       this.m_selectExpression.getExpression().callVisitors((ExpressionOwner)this.m_selectExpression, visitor); 
/* 331 */     super.callChildVisitors(visitor, callAttrs);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemValueOf.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */