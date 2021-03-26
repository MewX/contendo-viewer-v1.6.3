package org.apache.xerces.impl;

import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.parser.XMLDocumentFilter;

public interface RevalidationHandler extends XMLDocumentFilter {
  boolean characterData(String paramString, Augmentations paramAugmentations);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/impl/RevalidationHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */