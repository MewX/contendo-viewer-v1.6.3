package javax.xml.transform.dom;

import javax.xml.transform.SourceLocator;
import org.w3c.dom.Node;

public interface DOMLocator extends SourceLocator {
  Node getOriginatingNode();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/dom/DOMLocator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */