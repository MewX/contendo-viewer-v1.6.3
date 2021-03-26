package org.apache.batik.dom;

import java.util.HashMap;
import org.w3c.dom.Node;
import org.w3c.dom.stylesheets.StyleSheet;

public interface StyleSheetFactory {
  StyleSheet createStyleSheet(Node paramNode, HashMap<String, String> paramHashMap);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/StyleSheetFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */