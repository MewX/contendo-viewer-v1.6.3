package org.apache.xpath;

import javax.xml.transform.SourceLocator;

public interface ExpressionNode extends SourceLocator {
  void exprSetParent(ExpressionNode paramExpressionNode);
  
  ExpressionNode exprGetParent();
  
  void exprAddChild(ExpressionNode paramExpressionNode, int paramInt);
  
  ExpressionNode exprGetChild(int paramInt);
  
  int exprGetNumChildren();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/ExpressionNode.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */