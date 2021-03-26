/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.serialize.SerializerUtils;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xalan.transformer.TreeWalker2Result;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.serializer.SerializationHandler;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemCopyOf
/*     */   extends ElemTemplateElement
/*     */ {
/*  51 */   public XPath m_selectExpression = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelect(XPath expr) {
/*  61 */     this.m_selectExpression = expr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPath getSelect() {
/*  72 */     return this.m_selectExpression;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/*  83 */     super.compose(sroot);
/*     */     
/*  85 */     StylesheetRoot.ComposeState cstate = sroot.getComposeState();
/*  86 */     this.m_selectExpression.fixupVariables(cstate.getVariableNames(), cstate.getGlobalsSize());
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
/*  97 */     return 74;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 107 */     return "copy-of";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 126 */     if (TransformerImpl.S_DEBUG) {
/* 127 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/*     */     
/*     */     try {
/* 131 */       XPathContext xctxt = transformer.getXPathContext();
/* 132 */       int sourceNode = xctxt.getCurrentNode();
/* 133 */       XObject value = this.m_selectExpression.execute(xctxt, sourceNode, this);
/*     */       
/* 135 */       if (TransformerImpl.S_DEBUG) {
/* 136 */         transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "select", this.m_selectExpression, value);
/*     */       }
/*     */       
/* 139 */       SerializationHandler handler = transformer.getSerializationHandler();
/*     */       
/* 141 */       if (null != value)
/*     */       { String s; DTMIterator nl; TreeWalker2Result treeWalker2Result;
/* 143 */         int pos, type = value.getType();
/*     */ 
/*     */         
/* 146 */         switch (type)
/*     */         
/*     */         { case 1:
/*     */           case 2:
/*     */           case 3:
/* 151 */             s = value.str();
/*     */             
/* 153 */             handler.characters(s.toCharArray(), 0, s.length());
/*     */             break;
/*     */ 
/*     */           
/*     */           case 4:
/* 158 */             nl = value.iter();
/*     */ 
/*     */             
/* 161 */             treeWalker2Result = new TreeWalker2Result(transformer, handler);
/*     */ 
/*     */             
/* 164 */             while (-1 != (pos = nl.nextNode())) {
/*     */               
/* 166 */               DTM dtm = xctxt.getDTMManager().getDTM(pos);
/* 167 */               short t = dtm.getNodeType(pos);
/*     */ 
/*     */ 
/*     */               
/* 171 */               if (t == 9) {
/*     */                 
/* 173 */                 for (int child = dtm.getFirstChild(pos); child != -1; 
/* 174 */                   child = dtm.getNextSibling(child))
/*     */                 {
/* 176 */                   treeWalker2Result.traverse(child); } 
/*     */                 continue;
/*     */               } 
/* 179 */               if (t == 2) {
/*     */                 
/* 181 */                 SerializerUtils.addAttribute(handler, pos);
/*     */                 
/*     */                 continue;
/*     */               } 
/* 185 */               treeWalker2Result.traverse(pos);
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 5:
/* 191 */             SerializerUtils.outputResultTreeFragment(handler, value, transformer.getXPathContext());
/*     */             break;
/*     */ 
/*     */           
/*     */           default:
/* 196 */             s = value.str();
/*     */             
/* 198 */             handler.characters(s.toCharArray(), 0, s.length()); break; }  } 
/* 199 */     } catch (SAXException se) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 211 */       throw new TransformerException(se);
/*     */     }
/*     */     finally {
/*     */       
/* 215 */       if (TransformerImpl.S_DEBUG) {
/* 216 */         transformer.getTraceManager().fireTraceEndEvent(this);
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
/*     */   public ElemTemplateElement appendChild(ElemTemplateElement newChild) {
/* 231 */     error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), getNodeName() });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 245 */     if (callAttrs)
/* 246 */       this.m_selectExpression.getExpression().callVisitors((ExpressionOwner)this.m_selectExpression, visitor); 
/* 247 */     super.callChildVisitors(visitor, callAttrs);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemCopyOf.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */