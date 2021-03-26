/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.KeyManager;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.QName;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.ExpressionNode;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.axes.UnionPathIterator;
/*     */ import org.apache.xpath.functions.Function2Args;
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
/*     */ public class FuncKey
/*     */   extends Function2Args
/*     */ {
/*  43 */   private static Boolean ISTRUE = new Boolean(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*  57 */     TransformerImpl transformer = (TransformerImpl)xctxt.getOwnerObject();
/*  58 */     XNodeSet nodes = null;
/*  59 */     int context = xctxt.getCurrentNode();
/*  60 */     DTM dtm = xctxt.getDTM(context);
/*  61 */     int docContext = dtm.getDocumentRoot(context);
/*     */     
/*  63 */     if (-1 == docContext);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     String xkeyname = getArg0().execute(xctxt).str();
/*  70 */     QName keyname = new QName(xkeyname, xctxt.getNamespaceContext());
/*  71 */     XObject arg = getArg1().execute(xctxt);
/*  72 */     boolean argIsNodeSetDTM = (4 == arg.getType());
/*  73 */     KeyManager kmgr = transformer.getKeyManager();
/*     */ 
/*     */     
/*  76 */     if (argIsNodeSetDTM) {
/*     */       
/*  78 */       XNodeSet ns = (XNodeSet)arg;
/*  79 */       ns.setShouldCacheNodes(true);
/*  80 */       int len = ns.getLength();
/*  81 */       if (len <= 1) {
/*  82 */         argIsNodeSetDTM = false;
/*     */       }
/*     */     } 
/*  85 */     if (argIsNodeSetDTM) {
/*     */       
/*  87 */       Hashtable usedrefs = null;
/*  88 */       DTMIterator ni = arg.iter();
/*     */       
/*  90 */       UnionPathIterator upi = new UnionPathIterator();
/*  91 */       upi.exprSetParent((ExpressionNode)this);
/*     */       int pos;
/*  93 */       while (-1 != (pos = ni.nextNode())) {
/*     */         
/*  95 */         dtm = xctxt.getDTM(pos);
/*  96 */         XMLString ref = dtm.getStringValue(pos);
/*     */         
/*  98 */         if (null == ref) {
/*     */           continue;
/*     */         }
/* 101 */         if (null == usedrefs) {
/* 102 */           usedrefs = new Hashtable();
/*     */         }
/* 104 */         if (usedrefs.get(ref) != null) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 112 */         usedrefs.put(ref, ISTRUE);
/*     */ 
/*     */         
/* 115 */         XNodeSet nl = kmgr.getNodeSetDTMByKey(xctxt, docContext, keyname, ref, xctxt.getNamespaceContext());
/*     */ 
/*     */ 
/*     */         
/* 119 */         nl.setRoot(xctxt.getCurrentNode(), xctxt);
/*     */ 
/*     */ 
/*     */         
/* 123 */         upi.addIterator((DTMIterator)nl);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       int current = xctxt.getCurrentNode();
/* 133 */       upi.setRoot(current, xctxt);
/*     */       
/* 135 */       nodes = new XNodeSet((DTMIterator)upi);
/*     */     }
/*     */     else {
/*     */       
/* 139 */       XMLString ref = arg.xstr();
/* 140 */       nodes = kmgr.getNodeSetDTMByKey(xctxt, docContext, keyname, ref, xctxt.getNamespaceContext());
/*     */ 
/*     */       
/* 143 */       nodes.setRoot(xctxt.getCurrentNode(), xctxt);
/*     */     } 
/*     */     
/* 146 */     return (XObject)nodes;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/FuncKey.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */