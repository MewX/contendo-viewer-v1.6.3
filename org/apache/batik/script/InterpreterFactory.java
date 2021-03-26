package org.apache.batik.script;

import java.net.URL;

public interface InterpreterFactory {
  String[] getMimeTypes();
  
  Interpreter createInterpreter(URL paramURL, boolean paramBoolean, ImportInfo paramImportInfo);
  
  Interpreter createInterpreter(URL paramURL, boolean paramBoolean);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/script/InterpreterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */