/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.FastStringBuffer;
/*     */ import org.apache.xml.utils.PrefixResolver;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.XPathFactory;
/*     */ import org.apache.xpath.compiler.XPathParser;
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
/*     */ public class AVTPartXPath
/*     */   extends AVTPart
/*     */ {
/*     */   private XPath m_xpath;
/*     */   
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/*  53 */     this.m_xpath.fixupVariables(vars, globalsSize);
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
/*  64 */     return this.m_xpath.getExpression().canTraverseOutsideSubtree();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AVTPartXPath(XPath xpath) {
/*  74 */     this.m_xpath = xpath;
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
/*     */   public AVTPartXPath(String val, PrefixResolver nsNode, XPathParser xpathProcessor, XPathFactory factory, XPathContext liaison) throws TransformerException {
/* 101 */     this.m_xpath = new XPath(val, null, nsNode, 0, liaison.getErrorListener());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSimpleString() {
/* 111 */     return "{" + this.m_xpath.getPatternString() + "}";
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
/*     */   public void evaluate(XPathContext xctxt, FastStringBuffer buf, int context, PrefixResolver nsNode) throws TransformerException {
/* 133 */     XObject xobj = this.m_xpath.execute(xctxt, context, nsNode);
/*     */     
/* 135 */     if (null != xobj)
/*     */     {
/* 137 */       xobj.appendToFsb(buf);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callVisitors(XSLTVisitor visitor) {
/* 146 */     this.m_xpath.getExpression().callVisitors((ExpressionOwner)this.m_xpath, visitor);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/AVTPartXPath.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */