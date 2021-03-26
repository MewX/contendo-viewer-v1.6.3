package org.apache.fontbox.type1;

import java.io.IOException;
import org.apache.fontbox.cff.Type1CharString;

public interface Type1CharStringReader {
  Type1CharString getType1CharString(String paramString) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/type1/Type1CharStringReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */