package org.apache.batik.apps.rasterizer;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.batik.transcoder.Transcoder;

public interface SVGConverterController {
  boolean proceedWithComputedTask(Transcoder paramTranscoder, Map paramMap, List paramList1, List paramList2);
  
  boolean proceedWithSourceTranscoding(SVGConverterSource paramSVGConverterSource, File paramFile);
  
  boolean proceedOnSourceTranscodingFailure(SVGConverterSource paramSVGConverterSource, File paramFile, String paramString);
  
  void onSourceTranscodingSuccess(SVGConverterSource paramSVGConverterSource, File paramFile);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/apps/rasterizer/SVGConverterController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */