/*    */ package org.apache.xalan.xsltc.compiler;
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
/*    */ abstract class AttributeValue
/*    */   extends Expression
/*    */ {
/*    */   public static final AttributeValue create(SyntaxTreeNode parent, String text, Parser parser) {
/*    */     AttributeValue attributeValue;
/* 33 */     if (text.indexOf('{') != -1) {
/* 34 */       attributeValue = new AttributeValueTemplate(text, parser, parent);
/*    */     }
/* 36 */     else if (text.indexOf('}') != -1) {
/* 37 */       attributeValue = new AttributeValueTemplate(text, parser, parent);
/*    */     } else {
/*    */       
/* 40 */       attributeValue = new SimpleAttributeValue(text);
/* 41 */       attributeValue.setParser(parser);
/* 42 */       attributeValue.setParent(parent);
/*    */     } 
/* 44 */     return attributeValue;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/AttributeValue.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */