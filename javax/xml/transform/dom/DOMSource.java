package javax.xml.transform.dom;

import javax.xml.transform.Source;
import org.w3c.dom.Node;

public class DOMSource implements Source {
  private Node a;
  
  private String b;
  
  public static final String FEATURE = "http://javax.xml.transform.dom.DOMSource/feature";
  
  public DOMSource() {}
  
  public DOMSource(Node paramNode) {
    setNode(paramNode);
  }
  
  public DOMSource(Node paramNode, String paramString) {
    setNode(paramNode);
    setSystemId(paramString);
  }
  
  public void setNode(Node paramNode) {
    this.a = paramNode;
  }
  
  public Node getNode() {
    return this.a;
  }
  
  public void setSystemId(String paramString) {
    this.b = paramString;
  }
  
  public String getSystemId() {
    return this.b;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/dom/DOMSource.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */