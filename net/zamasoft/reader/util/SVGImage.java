package net.zamasoft.reader.util;

import java.awt.RenderingHints;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import javafx.beans.NamedArg;
import javafx.scene.image.Image;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.b;
import org.apache.batik.gvt.renderer.ImageRenderer;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class SVGImage extends Image {
  public SVGImage(@NamedArg("url") String paramString, @NamedArg("width") float paramFloat, @NamedArg("renderScale") boolean paramBoolean) {
    super(convert(paramString, paramFloat, paramFloat, paramBoolean));
  }
  
  public SVGImage(@NamedArg("url") String paramString, @NamedArg("width") float paramFloat1, @NamedArg("height") float paramFloat2, @NamedArg("renderScale") boolean paramBoolean) {
    super(convert(paramString, paramFloat1, paramFloat2, paramBoolean));
  }
  
  public SVGImage(@NamedArg("url") String paramString, @NamedArg("width") float paramFloat) {
    super(convert(paramString, paramFloat, paramFloat, true));
  }
  
  public SVGImage(@NamedArg("url") String paramString, @NamedArg("width") float paramFloat1, @NamedArg("height") float paramFloat2) {
    super(convert(paramString, paramFloat1, paramFloat2, true));
  }
  
  public static final String convert(String paramString, float paramFloat1, float paramFloat2, boolean paramBoolean) {
    try {
      double d = paramBoolean ? ReaderApplication.getRenderScale() : 1.0D;
      paramFloat1 = (float)(paramFloat1 * d);
      paramFloat2 = (float)(paramFloat2 * d);
      long l = URI.create(paramString).toURL().openConnection().getLastModified();
      File file1 = new File(b.i, ".icons");
      file1.mkdirs();
      int i = paramString.lastIndexOf('/');
      File file2 = new File(file1, paramString.substring(i + 1) + "x" + paramString.substring(i + 1) + ".png");
      if (!file2.exists() || l != file2.lastModified()) {
        PNGTranscoder pNGTranscoder = new PNGTranscoder() {
            protected ImageRenderer createRenderer() {
              ImageRenderer imageRenderer = super.createRenderer();
              RenderingHints renderingHints = imageRenderer.getRenderingHints();
              c.a(renderingHints);
              imageRenderer.setRenderingHints(renderingHints);
              return imageRenderer;
            }
          };
        pNGTranscoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, new Float(paramFloat1));
        pNGTranscoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, new Float(paramFloat2));
        TranscoderInput transcoderInput = new TranscoderInput(paramString);
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        TranscoderOutput transcoderOutput = new TranscoderOutput(fileOutputStream);
        pNGTranscoder.transcode(transcoderInput, transcoderOutput);
        fileOutputStream.flush();
        fileOutputStream.close();
        file2.setLastModified(l);
      } 
      return file2.toURI().toString();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      return paramString;
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/SVGImage.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */