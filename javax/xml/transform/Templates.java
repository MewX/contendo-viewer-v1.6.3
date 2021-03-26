package javax.xml.transform;

import java.util.Properties;

public interface Templates {
  Transformer newTransformer() throws TransformerConfigurationException;
  
  Properties getOutputProperties();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/javax/xml/transform/Templates.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */