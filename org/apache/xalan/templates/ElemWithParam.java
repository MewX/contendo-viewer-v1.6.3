/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.processor.TransformerFactoryImpl;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.apache.xpath.objects.XRTreeFrag;
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
/*     */ public class ElemWithParam
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   int m_index;
/*  58 */   private XPath m_selectPattern = null;
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
/*  69 */     this.m_selectPattern = v;
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
/*     */   public XPath getSelect() {
/*  81 */     return this.m_selectPattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   private QName m_qname = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int m_qnameID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(QName v) {
/* 103 */     this.m_qname = v;
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
/* 114 */     return this.m_qname;
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
/* 126 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 137 */     return "with-param";
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
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/* 149 */     if (null == this.m_selectPattern && TransformerFactoryImpl.m_optimize) {
/*     */ 
/*     */       
/* 152 */       XPath newSelect = ElemVariable.rewriteChildToExpression(this);
/* 153 */       if (null != newSelect)
/* 154 */         this.m_selectPattern = newSelect; 
/*     */     } 
/* 156 */     this.m_qnameID = sroot.getComposeState().getQNameID(this.m_qname);
/* 157 */     super.compose(sroot);
/*     */     
/* 159 */     Vector vnames = sroot.getComposeState().getVariableNames();
/* 160 */     if (null != this.m_selectPattern) {
/* 161 */       this.m_selectPattern.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
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
/*     */   public void setParentElem(ElemTemplateElement p) {
/* 173 */     super.setParentElem(p);
/* 174 */     p.m_hasVariableDecl = true;
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
/*     */   public XObject getValue(TransformerImpl transformer, int sourceNode) throws TransformerException {
/*     */     XRTreeFrag xRTreeFrag;
/* 192 */     XPathContext xctxt = transformer.getXPathContext();
/*     */     
/* 194 */     xctxt.pushCurrentNode(sourceNode);
/*     */ 
/*     */     
/*     */     try {
/* 198 */       if (null != this.m_selectPattern)
/*     */       {
/* 200 */         XObject var = this.m_selectPattern.execute(xctxt, sourceNode, this);
/*     */         
/* 202 */         var.allowDetachToRelease(false);
/*     */         
/* 204 */         if (TransformerImpl.S_DEBUG) {
/* 205 */           transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "select", this.m_selectPattern, var);
/*     */         }
/*     */       }
/* 208 */       else if (null == getFirstChildElem())
/*     */       {
/* 210 */         XString xString = XString.EMPTYSTRING;
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */         
/* 216 */         int df = transformer.transformToRTF(this);
/*     */         
/* 218 */         xRTreeFrag = new XRTreeFrag(df, xctxt, this);
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/* 223 */       xctxt.popCurrentNode();
/*     */     } 
/*     */     
/* 226 */     return (XObject)xRTreeFrag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 235 */     if (callAttrs && null != this.m_selectPattern)
/* 236 */       this.m_selectPattern.getExpression().callVisitors((ExpressionOwner)this.m_selectPattern, visitor); 
/* 237 */     super.callChildVisitors(visitor, callAttrs);
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
/* 252 */     if (this.m_selectPattern != null) {
/*     */       
/* 254 */       error("ER_CANT_HAVE_CONTENT_AND_SELECT", new Object[] { "xsl:" + getNodeName() });
/*     */       
/* 256 */       return null;
/*     */     } 
/* 258 */     return super.appendChild(elem);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemWithParam.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */