/*     */ package org.apache.xpath.functions;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XString;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FunctionDef1Arg
/*     */   extends FunctionOneArg
/*     */ {
/*     */   protected int getArg0AsNode(XPathContext xctxt) throws TransformerException {
/*  52 */     return (null == this.m_arg0) ? xctxt.getCurrentNode() : this.m_arg0.asNode(xctxt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean Arg0IsNodesetExpr() {
/*  62 */     return (null == this.m_arg0) ? true : this.m_arg0.isNodesetExpr();
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
/*     */   protected XMLString getArg0AsString(XPathContext xctxt) throws TransformerException {
/*  81 */     if (null == this.m_arg0) {
/*     */       
/*  83 */       int currentNode = xctxt.getCurrentNode();
/*  84 */       if (-1 == currentNode) {
/*  85 */         return (XMLString)XString.EMPTYSTRING;
/*     */       }
/*     */       
/*  88 */       DTM dtm = xctxt.getDTM(currentNode);
/*  89 */       return dtm.getStringValue(currentNode);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  94 */     return this.m_arg0.execute(xctxt).xstr();
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
/*     */   protected double getArg0AsNumber(XPathContext xctxt) throws TransformerException {
/* 114 */     if (null == this.m_arg0) {
/*     */       
/* 116 */       int currentNode = xctxt.getCurrentNode();
/* 117 */       if (-1 == currentNode) {
/* 118 */         return 0.0D;
/*     */       }
/*     */       
/* 121 */       DTM dtm = xctxt.getDTM(currentNode);
/* 122 */       XMLString str = dtm.getStringValue(currentNode);
/* 123 */       return str.toDouble();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 128 */     return this.m_arg0.execute(xctxt).num();
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
/*     */   public void checkNumberArgs(int argNum) throws WrongNumberArgsException {
/* 140 */     if (argNum > 1) {
/* 141 */       reportWrongNumberArgs();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reportWrongNumberArgs() throws WrongNumberArgsException {
/* 151 */     throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("ER_ZERO_OR_ONE", null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canTraverseOutsideSubtree() {
/* 162 */     return (null == this.m_arg0) ? false : super.canTraverseOutsideSubtree();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FunctionDef1Arg.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */