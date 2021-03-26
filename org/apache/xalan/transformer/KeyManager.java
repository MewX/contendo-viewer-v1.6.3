/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.ElemTemplateElement;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyManager
/*     */ {
/*  40 */   private transient Vector m_key_tables = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XNodeSet getNodeSetDTMByKey(XPathContext xctxt, int doc, QName name, XMLString ref, PrefixResolver nscontext) throws TransformerException {
/*  60 */     XNodeSet nl = null;
/*  61 */     ElemTemplateElement template = (ElemTemplateElement)nscontext;
/*     */     
/*  63 */     if (null != template && null != template.getStylesheetRoot().getKeysComposed()) {
/*     */ 
/*     */       
/*  66 */       boolean foundDoc = false;
/*     */       
/*  68 */       if (null == this.m_key_tables) {
/*     */         
/*  70 */         this.m_key_tables = new Vector(4);
/*     */       }
/*     */       else {
/*     */         
/*  74 */         int nKeyTables = this.m_key_tables.size();
/*     */         
/*  76 */         for (int i = 0; i < nKeyTables; i++) {
/*     */           
/*  78 */           KeyTable kt = this.m_key_tables.elementAt(i);
/*     */           
/*  80 */           if (kt.getKeyTableName().equals(name) && doc == kt.getDocKey()) {
/*     */             
/*  82 */             nl = kt.getNodeSetDTMByKey(name, ref);
/*     */             
/*  84 */             if (nl != null) {
/*     */               
/*  86 */               foundDoc = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  94 */       if (null == nl && !foundDoc) {
/*     */         
/*  96 */         KeyTable kt = new KeyTable(doc, nscontext, name, template.getStylesheetRoot().getKeysComposed(), xctxt);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 101 */         this.m_key_tables.addElement(kt);
/*     */         
/* 103 */         if (doc == kt.getDocKey()) {
/*     */           
/* 105 */           foundDoc = true;
/* 106 */           nl = kt.getNodeSetDTMByKey(name, ref);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     return nl;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/KeyManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */