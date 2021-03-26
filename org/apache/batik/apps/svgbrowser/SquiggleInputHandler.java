package org.apache.batik.apps.svgbrowser;

import java.io.File;
import org.apache.batik.util.ParsedURL;

public interface SquiggleInputHandler {
  String[] getHandledMimeTypes();
  
  String[] getHandledExtensions();
  
  String getDescription();
  
  boolean accept(File paramFile);
  
  boolean accept(ParsedURL paramParsedURL);
  
  void handle(ParsedURL paramParsedURL, JSVGViewerFrame paramJSVGViewerFrame) throws Exception;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/svgbrowser/SquiggleInputHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */