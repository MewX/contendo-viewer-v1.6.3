/*    */ package org.apache.xalan.trace;
/*    */ 
/*    */ import java.util.EventListener;
/*    */ import org.apache.xalan.templates.ElemTemplateElement;
/*    */ import org.apache.xalan.transformer.TransformerImpl;
/*    */ import org.apache.xpath.XPath;
/*    */ import org.apache.xpath.objects.XObject;
/*    */ import org.w3c.dom.Node;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SelectionEvent
/*    */   implements EventListener
/*    */ {
/*    */   public final ElemTemplateElement m_styleNode;
/*    */   public final TransformerImpl m_processor;
/*    */   public final Node m_sourceNode;
/*    */   public final String m_attributeName;
/*    */   public final XPath m_xpath;
/*    */   public final XObject m_selection;
/*    */   
/*    */   public SelectionEvent(TransformerImpl processor, Node sourceNode, ElemTemplateElement styleNode, String attributeName, XPath xpath, XObject selection) {
/* 82 */     this.m_processor = processor;
/* 83 */     this.m_sourceNode = sourceNode;
/* 84 */     this.m_styleNode = styleNode;
/* 85 */     this.m_attributeName = attributeName;
/* 86 */     this.m_xpath = xpath;
/* 87 */     this.m_selection = selection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/trace/SelectionEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */