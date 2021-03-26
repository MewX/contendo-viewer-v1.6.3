package org.apache.batik.apps.svgbrowser;

import org.w3c.dom.Element;

public interface NodePickerController {
  boolean isEditable();
  
  boolean canEdit(Element paramElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/NodePickerController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */