/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.res.XSLMessages;
/*     */ import org.apache.xalan.templates.KeyDeclaration;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.axes.ChildTestIterator;
/*     */ import org.apache.xpath.axes.LocPathIterator;
/*     */ import org.apache.xpath.objects.XNodeSet;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyRefIterator
/*     */   extends ChildTestIterator
/*     */ {
/*     */   DTMIterator m_keysNodes;
/*     */   protected XMLString m_ref;
/*     */   protected QName m_name;
/*     */   protected Vector m_keyDeclarations;
/*     */   
/*     */   public KeyRefIterator(QName name, XMLString ref, Vector keyDecls, DTMIterator ki) {
/*  49 */     super(null);
/*  50 */     this.m_name = name;
/*  51 */     this.m_ref = ref;
/*  52 */     this.m_keyDeclarations = keyDecls;
/*  53 */     this.m_keysNodes = ki;
/*  54 */     setWhatToShow(-1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getNextNode() {
/*     */     int next;
/*  66 */     while (-1 != (next = this.m_keysNodes.nextNode())) {
/*     */       
/*  68 */       if (1 == filterNode(next))
/*     */         break; 
/*     */     } 
/*  71 */     ((LocPathIterator)this).m_lastFetched = next;
/*     */     
/*  73 */     return next;
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
/*     */   public short filterNode(int testNode) {
/*  90 */     boolean foundKey = false;
/*  91 */     Vector keys = this.m_keyDeclarations;
/*     */     
/*  93 */     QName name = this.m_name;
/*  94 */     KeyIterator ki = (KeyIterator)((XNodeSet)this.m_keysNodes).getContainedIter();
/*  95 */     XPathContext xctxt = ki.getXPathContext();
/*     */     
/*  97 */     if (null == xctxt) {
/*  98 */       assertion(false, "xctxt can not be null here!");
/*     */     }
/*     */     
/*     */     try {
/* 102 */       XMLString lookupKey = this.m_ref;
/*     */ 
/*     */       
/* 105 */       int nDeclarations = keys.size();
/*     */ 
/*     */       
/* 108 */       for (int i = 0; i < nDeclarations; i++) {
/*     */         
/* 110 */         KeyDeclaration kd = keys.elementAt(i);
/*     */ 
/*     */ 
/*     */         
/* 114 */         if (kd.getName().equals(name)) {
/*     */ 
/*     */           
/* 117 */           foundKey = true;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 122 */           XObject xuse = kd.getUse().execute(xctxt, testNode, ki.getPrefixResolver());
/*     */           
/* 124 */           if (xuse.getType() != 4) {
/*     */             
/* 126 */             XMLString exprResult = xuse.xstr();
/*     */             
/* 128 */             if (lookupKey.equals(exprResult)) {
/* 129 */               return 1;
/*     */             }
/*     */           } else {
/*     */             
/* 133 */             DTMIterator nl = ((XNodeSet)xuse).iterRaw();
/*     */             
/*     */             int useNode;
/* 136 */             while (-1 != (useNode = nl.nextNode())) {
/*     */               
/* 138 */               DTM dtm = getDTM(useNode);
/* 139 */               XMLString exprResult = dtm.getStringValue(useNode);
/* 140 */               if (null != exprResult && lookupKey.equals(exprResult)) {
/* 141 */                 return 1;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } catch (TransformerException te) {
/*     */       
/* 149 */       throw new WrappedRuntimeException(te);
/*     */     } 
/*     */     
/* 152 */     if (!foundKey) {
/* 153 */       throw new RuntimeException(XSLMessages.createMessage("ER_NO_XSLKEY_DECLARATION", new Object[] { name.getLocalName() }));
/*     */     }
/*     */ 
/*     */     
/* 157 */     return 2;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/KeyRefIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */