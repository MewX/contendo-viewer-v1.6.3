/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.KeyDeclaration;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.XPathContext;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyTable
/*     */ {
/*     */   private int m_docKey;
/*     */   private Vector m_keyDeclarations;
/*  61 */   private Hashtable m_refsTable = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private XNodeSet m_keyNodes;
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDocKey() {
/*  70 */     return this.m_docKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   KeyIterator getKeyIterator() {
/*  81 */     return (KeyIterator)this.m_keyNodes.getContainedIter();
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
/*     */   public KeyTable(int doc, PrefixResolver nscontext, QName name, Vector keyDeclarations, XPathContext xctxt) throws TransformerException {
/*  98 */     this.m_docKey = doc;
/*  99 */     this.m_keyDeclarations = keyDeclarations;
/* 100 */     KeyIterator ki = new KeyIterator(name, keyDeclarations);
/*     */     
/* 102 */     this.m_keyNodes = new XNodeSet((DTMIterator)ki);
/* 103 */     this.m_keyNodes.allowDetachToRelease(false);
/* 104 */     this.m_keyNodes.setRoot(doc, xctxt);
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
/*     */   public XNodeSet getNodeSetDTMByKey(QName name, XMLString ref) {
/* 117 */     XNodeSet refNodes = (XNodeSet)getRefsTable().get(ref);
/*     */ 
/*     */ 
/*     */     
/* 121 */     try { if (refNodes != null)
/*     */       {
/* 123 */         refNodes = (XNodeSet)refNodes.cloneWithReset(); }  } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 128 */       refNodes = null; }
/*     */ 
/*     */     
/* 131 */     if (refNodes == null) {
/*     */       
/* 133 */       KeyIterator ki = (KeyIterator)this.m_keyNodes.getContainedIter();
/* 134 */       XPathContext xctxt = ki.getXPathContext();
/* 135 */       refNodes = new XNodeSet(this, xctxt.getDTMManager()) {
/*     */           private final KeyTable this$0;
/*     */           
/*     */           public void setRoot(int nodeHandle, Object environment) {}
/*     */         };
/* 140 */       refNodes.reset();
/*     */     } 
/*     */     
/* 143 */     return refNodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public QName getKeyTableName() {
/* 153 */     return getKeyIterator().getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private KeyDeclaration getKeyDeclaration() {
/* 160 */     int nDeclarations = this.m_keyDeclarations.size();
/*     */ 
/*     */     
/* 163 */     for (int i = 0; i < nDeclarations; i++) {
/*     */       
/* 165 */       KeyDeclaration kd = this.m_keyDeclarations.elementAt(i);
/*     */ 
/*     */ 
/*     */       
/* 169 */       if (kd.getName().equals(getKeyTableName()))
/*     */       {
/* 171 */         return kd;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Hashtable getRefsTable() {
/* 185 */     if (this.m_refsTable == null) {
/*     */       
/* 187 */       this.m_refsTable = new Hashtable(89);
/*     */       
/* 189 */       KeyIterator ki = (KeyIterator)this.m_keyNodes.getContainedIter();
/* 190 */       XPathContext xctxt = ki.getXPathContext();
/*     */       
/* 192 */       KeyDeclaration keyDeclaration = getKeyDeclaration();
/*     */ 
/*     */       
/* 195 */       this.m_keyNodes.reset(); int currentNode;
/* 196 */       while (-1 != (currentNode = this.m_keyNodes.nextNode())) {
/*     */ 
/*     */         
/*     */         try {
/* 200 */           XObject xuse = keyDeclaration.getUse().execute(xctxt, currentNode, ki.getPrefixResolver());
/*     */           
/* 202 */           if (xuse.getType() != 4) {
/*     */             
/* 204 */             XMLString exprResult = xuse.xstr();
/* 205 */             addValueInRefsTable(xctxt, exprResult, currentNode);
/*     */             
/*     */             continue;
/*     */           } 
/* 209 */           DTMIterator i = ((XNodeSet)xuse).iterRaw();
/*     */           
/*     */           int currentNodeInUseClause;
/* 212 */           while (-1 != (currentNodeInUseClause = i.nextNode()))
/*     */           {
/* 214 */             DTM dtm = xctxt.getDTM(currentNodeInUseClause);
/* 215 */             XMLString exprResult = dtm.getStringValue(currentNodeInUseClause);
/* 216 */             addValueInRefsTable(xctxt, exprResult, currentNode);
/*     */           }
/*     */         
/*     */         }
/*     */         catch (TransformerException te) {
/*     */           
/* 222 */           throw new WrappedRuntimeException(te);
/*     */         } 
/*     */       } 
/*     */     } 
/* 226 */     return this.m_refsTable;
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
/*     */   private void addValueInRefsTable(XPathContext xctxt, XMLString ref, int node) {
/* 238 */     XNodeSet nodes = (XNodeSet)this.m_refsTable.get(ref);
/* 239 */     if (nodes == null) {
/*     */       
/* 241 */       nodes = new XNodeSet(node, xctxt.getDTMManager());
/* 242 */       nodes.nextNode();
/* 243 */       this.m_refsTable.put(ref, nodes);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 251 */     else if (nodes.getCurrentNode() != node) {
/* 252 */       nodes.mutableNodeset().addNode(node);
/* 253 */       nodes.nextNode();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/KeyTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */