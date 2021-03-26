package javax.xml.transform.dom;

import javax.xml.transform.Result;
import org.w3c.dom.Node;

public class DOMResult implements Result {
  public static final String FEATURE = "http://javax.xml.transform.dom.DOMResult/feature";
  
  private Node a = null;
  
  private Node b = null;
  
  private String c = null;
  
  public DOMResult() {
    setNode(null);
    setNextSibling(null);
    setSystemId(null);
  }
  
  public DOMResult(Node paramNode) {
    setNode(paramNode);
    setNextSibling(null);
    setSystemId(null);
  }
  
  public DOMResult(Node paramNode, String paramString) {
    setNode(paramNode);
    setNextSibling(null);
    setSystemId(paramString);
  }
  
  public DOMResult(Node paramNode1, Node paramNode2) {
    if (paramNode2 != null) {
      if (paramNode1 == null)
        throw new IllegalArgumentException("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node."); 
      if ((paramNode1.compareDocumentPosition(paramNode2) & 0x10) == 0)
        throw new IllegalArgumentException("Cannot create a DOMResult when the nextSibling is not contained by the node."); 
    } 
    setNode(paramNode1);
    setNextSibling(paramNode2);
    setSystemId(null);
  }
  
  public DOMResult(Node paramNode1, Node paramNode2, String paramString) {
    if (paramNode2 != null) {
      if (paramNode1 == null)
        throw new IllegalArgumentException("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node."); 
      if ((paramNode1.compareDocumentPosition(paramNode2) & 0x10) == 0)
        throw new IllegalArgumentException("Cannot create a DOMResult when the nextSibling is not contained by the node."); 
    } 
    setNode(paramNode1);
    setNextSibling(paramNode2);
    setSystemId(paramString);
  }
  
  public void setNode(Node paramNode) {
    if (this.b != null) {
      if (paramNode == null)
        throw new IllegalStateException("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node."); 
      if ((paramNode.compareDocumentPosition(this.b) & 0x10) == 0)
        throw new IllegalArgumentException("Cannot create a DOMResult when the nextSibling is not contained by the node."); 
    } 
    this.a = paramNode;
  }
  
  public Node getNode() {
    return this.a;
  }
  
  public void setNextSibling(Node paramNode) {
    if (paramNode != null) {
      if (this.a == null)
        throw new IllegalStateException("Cannot create a DOMResult when the nextSibling is contained by the \"null\" node."); 
      if ((this.a.compareDocumentPosition(paramNode) & 0x10) == 0)
        throw new IllegalArgumentException("Cannot create a DOMResult when the nextSibling is not contained by the node."); 
    } 
    this.b = paramNode;
  }
  
  public Node getNextSibling() {
    return this.b;
  }
  
  public void setSystemId(String paramString) {
    this.c = paramString;
  }
  
  public String getSystemId() {
    return this.c;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/dom/DOMResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */