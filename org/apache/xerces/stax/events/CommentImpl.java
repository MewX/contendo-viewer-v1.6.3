package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Comment;

public final class CommentImpl extends XMLEventImpl implements Comment {
  private final String fText;
  
  public CommentImpl(String paramString, Location paramLocation) {
    super(5, paramLocation);
    this.fText = (paramString != null) ? paramString : "";
  }
  
  public String getText() {
    return this.fText;
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write("<!--");
      paramWriter.write(this.fText);
      paramWriter.write("-->");
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/CommentImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */