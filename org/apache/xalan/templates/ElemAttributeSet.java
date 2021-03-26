/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.utils.QName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemAttributeSet
/*     */   extends ElemUse
/*     */ {
/*  47 */   public QName m_qname = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(QName name) {
/*  57 */     this.m_qname = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QName getName() {
/*  68 */     return this.m_qname;
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
/*  79 */     return 40;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  89 */     return "attribute-set";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 106 */     if (TransformerImpl.S_DEBUG) {
/* 107 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/* 109 */     if (transformer.isRecursiveAttrSet(this))
/*     */     {
/* 111 */       throw new TransformerException(XSLMessages.createMessage("ER_XSLATTRSET_USED_ITSELF", new Object[] { this.m_qname.getLocalPart() }));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     transformer.pushElemAttributeSet(this);
/* 118 */     super.execute(transformer);
/*     */     
/* 120 */     ElemAttribute attr = (ElemAttribute)getFirstChildElem();
/*     */     
/* 122 */     while (null != attr) {
/*     */       
/* 124 */       attr.execute(transformer);
/*     */       
/* 126 */       attr = (ElemAttribute)attr.getNextSiblingElem();
/*     */     } 
/*     */     
/* 129 */     transformer.popElemAttributeSet();
/*     */     
/* 131 */     if (TransformerImpl.S_DEBUG) {
/* 132 */       transformer.getTraceManager().fireTraceEndEvent(this);
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
/*     */   public ElemTemplateElement appendChildElem(ElemTemplateElement newChild) {
/* 153 */     int type = newChild.getXSLToken();
/*     */     
/* 155 */     switch (type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 48:
/* 167 */         return appendChild(newChild);
/*     */     } 
/*     */     error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), getNodeName() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recompose(StylesheetRoot root) {
/* 177 */     root.recomposeAttributeSets(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemAttributeSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */