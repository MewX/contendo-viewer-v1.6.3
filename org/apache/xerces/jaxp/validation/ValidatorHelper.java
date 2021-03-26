package org.apache.xerces.jaxp.validation;

import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.xml.sax.SAXException;

interface ValidatorHelper {
  void validate(Source paramSource, Result paramResult) throws SAXException, IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/jaxp/validation/ValidatorHelper.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */