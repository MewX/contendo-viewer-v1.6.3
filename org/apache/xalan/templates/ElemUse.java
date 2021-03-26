/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
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
/*     */ public class ElemUse
/*     */   extends ElemTemplateElement
/*     */ {
/*  45 */   private QName[] m_attributeSetsNames = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUseAttributeSets(Vector v) {
/*  61 */     int n = v.size();
/*     */     
/*  63 */     this.m_attributeSetsNames = new QName[n];
/*     */     
/*  65 */     for (int i = 0; i < n; i++)
/*     */     {
/*  67 */       this.m_attributeSetsNames[i] = v.elementAt(i);
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
/*     */   public void setUseAttributeSets(QName[] v) {
/*  84 */     this.m_attributeSetsNames = v;
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
/*     */   public QName[] getUseAttributeSets() {
/* 102 */     return this.m_attributeSetsNames;
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
/*     */   public void applyAttrSets(TransformerImpl transformer, StylesheetRoot stylesheet) throws TransformerException {
/* 124 */     applyAttrSets(transformer, stylesheet, this.m_attributeSetsNames);
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
/*     */   private void applyAttrSets(TransformerImpl transformer, StylesheetRoot stylesheet, QName[] attributeSetsNames) throws TransformerException {
/* 147 */     if (null != attributeSetsNames) {
/*     */       
/* 149 */       int nNames = attributeSetsNames.length;
/*     */       
/* 151 */       for (int i = 0; i < nNames; i++) {
/*     */         
/* 153 */         QName qname = attributeSetsNames[i];
/* 154 */         Vector attrSets = stylesheet.getAttributeSetComposed(qname);
/*     */         
/* 156 */         if (null != attrSets) {
/*     */           
/* 158 */           int nSets = attrSets.size();
/*     */ 
/*     */ 
/*     */           
/* 162 */           for (int k = nSets - 1; k >= 0; k--)
/*     */           {
/* 164 */             ElemAttributeSet attrSet = attrSets.elementAt(k);
/*     */ 
/*     */             
/* 167 */             attrSet.execute(transformer);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 172 */           throw new TransformerException(XSLMessages.createMessage("ER_NO_ATTRIB_SET", new Object[] { qname }), this);
/*     */         } 
/*     */       } 
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 202 */     if (null != this.m_attributeSetsNames)
/*     */     {
/* 204 */       applyAttrSets(transformer, getStylesheetRoot(), this.m_attributeSetsNames);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemUse.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */