/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.KeyDeclaration;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.axes.OneStepIteratorForward;
/*     */ import org.apache.xpath.axes.PredicatedNodeTest;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyIterator
/*     */   extends OneStepIteratorForward
/*     */ {
/*     */   private QName m_name;
/*     */   private Vector m_keyDeclarations;
/*     */   
/*     */   public QName getName() {
/*  55 */     return this.m_name;
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
/*     */   public Vector getKeyDeclarations() {
/*  70 */     return this.m_keyDeclarations;
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
/*     */   KeyIterator(QName name, Vector keyDeclarations) {
/*  84 */     super(16);
/*  85 */     this.m_keyDeclarations = keyDeclarations;
/*     */     
/*  87 */     this.m_name = name;
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
/*     */   public short acceptNode(int testNode) {
/* 103 */     boolean foundKey = false;
/* 104 */     KeyIterator ki = (KeyIterator)((PredicatedNodeTest)this).m_lpi;
/* 105 */     XPathContext xctxt = ki.getXPathContext();
/* 106 */     Vector keys = ki.getKeyDeclarations();
/*     */     
/* 108 */     QName name = ki.getName();
/*     */ 
/*     */     
/*     */     try {
/* 112 */       int nDeclarations = keys.size();
/*     */ 
/*     */       
/* 115 */       for (int i = 0; i < nDeclarations; i++) {
/*     */         
/* 117 */         KeyDeclaration kd = keys.elementAt(i);
/*     */ 
/*     */ 
/*     */         
/* 121 */         if (kd.getName().equals(name)) {
/*     */ 
/*     */           
/* 124 */           foundKey = true;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 129 */           XPath matchExpr = kd.getMatch();
/* 130 */           double score = matchExpr.getMatchScore(xctxt, testNode);
/*     */           
/* 132 */           kd.getMatch(); if (score != Double.NEGATIVE_INFINITY)
/*     */           {
/*     */             
/* 135 */             return 1;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } catch (TransformerException transformerException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     if (!foundKey) {
/* 146 */       throw new RuntimeException(XSLMessages.createMessage("ER_NO_XSLKEY_DECLARATION", new Object[] { name.getLocalName() }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 151 */     return 2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/KeyIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */