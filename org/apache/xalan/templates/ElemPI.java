/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.SourceLocator;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.utils.XMLChar;
/*     */ import org.apache.xpath.XPathContext;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemPI
/*     */   extends ElemTemplateElement
/*     */ {
/*  50 */   private AVT m_name_atv = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(AVT v) {
/*  60 */     this.m_name_atv = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AVT getName() {
/*  71 */     return this.m_name_atv;
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
/*  82 */     super.compose(sroot);
/*  83 */     Vector vnames = sroot.getComposeState().getVariableNames();
/*  84 */     if (null != this.m_name_atv) {
/*  85 */       this.m_name_atv.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
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
/*     */   public int getXSLToken() {
/*  98 */     return 58;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 108 */     return "processing-instruction";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 128 */     if (TransformerImpl.S_DEBUG) {
/* 129 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/* 131 */     XPathContext xctxt = transformer.getXPathContext();
/* 132 */     int sourceNode = xctxt.getCurrentNode();
/*     */     
/* 134 */     String piName = (this.m_name_atv == null) ? null : this.m_name_atv.evaluate(xctxt, sourceNode, this);
/*     */ 
/*     */     
/* 137 */     if (piName == null)
/*     */       return; 
/* 139 */     if (piName.equalsIgnoreCase("xml")) {
/*     */       
/* 141 */       transformer.getMsgMgr().warn((SourceLocator)this, "WG_PROCESSINGINSTRUCTION_NAME_CANT_BE_XML", new Object[] { "name", piName });
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 149 */     if (!this.m_name_atv.isSimple() && !XMLChar.isValidNCName(piName)) {
/*     */       
/* 151 */       transformer.getMsgMgr().warn((SourceLocator)this, "WG_PROCESSINGINSTRUCTION_NOTVALID_NCNAME", new Object[] { "name", piName });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     String data = transformer.transformToString(this);
/*     */ 
/*     */ 
/*     */     
/* 169 */     try { transformer.getResultTreeHandler().processingInstruction(piName, data); } catch (SAXException se)
/*     */     
/*     */     { 
/*     */       
/* 173 */       throw new TransformerException(se); }
/*     */ 
/*     */     
/* 176 */     if (TransformerImpl.S_DEBUG) {
/* 177 */       transformer.getTraceManager().fireTraceEndEvent(this);
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
/*     */   public ElemTemplateElement appendChild(ElemTemplateElement newChild) {
/* 192 */     int type = newChild.getXSLToken();
/*     */     
/* 194 */     switch (type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 9:
/*     */       case 17:
/*     */       case 28:
/*     */       case 30:
/*     */       case 35:
/*     */       case 36:
/*     */       case 37:
/*     */       case 42:
/*     */       case 50:
/*     */       case 72:
/*     */       case 73:
/*     */       case 74:
/*     */       case 75:
/*     */       case 78:
/* 227 */         return super.appendChild(newChild);
/*     */     } 
/*     */     error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), getNodeName() });
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemPI.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */