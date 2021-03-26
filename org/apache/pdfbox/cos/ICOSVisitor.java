package org.apache.pdfbox.cos;

import java.io.IOException;

public interface ICOSVisitor {
  Object visitFromArray(COSArray paramCOSArray) throws IOException;
  
  Object visitFromBoolean(COSBoolean paramCOSBoolean) throws IOException;
  
  Object visitFromDictionary(COSDictionary paramCOSDictionary) throws IOException;
  
  Object visitFromDocument(COSDocument paramCOSDocument) throws IOException;
  
  Object visitFromFloat(COSFloat paramCOSFloat) throws IOException;
  
  Object visitFromInt(COSInteger paramCOSInteger) throws IOException;
  
  Object visitFromName(COSName paramCOSName) throws IOException;
  
  Object visitFromNull(COSNull paramCOSNull) throws IOException;
  
  Object visitFromStream(COSStream paramCOSStream) throws IOException;
  
  Object visitFromString(COSString paramCOSString) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/ICOSVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */