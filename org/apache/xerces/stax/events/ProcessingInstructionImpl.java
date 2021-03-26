package org.apache.xerces.stax.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.ProcessingInstruction;

public final class ProcessingInstructionImpl extends XMLEventImpl implements ProcessingInstruction {
  private final String fTarget;
  
  private final String fData;
  
  public ProcessingInstructionImpl(String paramString1, String paramString2, Location paramLocation) {
    super(3, paramLocation);
    this.fTarget = (paramString1 != null) ? paramString1 : "";
    this.fData = paramString2;
  }
  
  public String getTarget() {
    return this.fTarget;
  }
  
  public String getData() {
    return this.fData;
  }
  
  public void writeAsEncodedUnicode(Writer paramWriter) throws XMLStreamException {
    try {
      paramWriter.write("<?");
      paramWriter.write(this.fTarget);
      if (this.fData != null && this.fData.length() > 0) {
        paramWriter.write(32);
        paramWriter.write(this.fData);
      } 
      paramWriter.write("?>");
    } catch (IOException iOException) {
      throw new XMLStreamException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/stax/events/ProcessingInstructionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */