package org.apache.xerces.dom;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;

public class CommentImpl extends CharacterDataImpl implements CharacterData, Comment {
  static final long serialVersionUID = -2685736833408134044L;
  
  public CommentImpl(CoreDocumentImpl paramCoreDocumentImpl, String paramString) {
    super(paramCoreDocumentImpl, paramString);
  }
  
  public short getNodeType() {
    return 8;
  }
  
  public String getNodeName() {
    return "#comment";
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/dom/CommentImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */