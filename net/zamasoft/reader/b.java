package net.zamasoft.reader;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import net.zamasoft.reader.util.j;

public class b {
  public static final ResourceBundle a = ResourceBundle.getBundle(ReaderApplication.class.getPackage().getName() + ".messages", (ResourceBundle.Control)new j());
  
  public static final String b = a.getString("appName");
  
  public static final String c = "1.6.3";
  
  public static final int d = 600;
  
  public static final int e = 520;
  
  public static final int f = 135;
  
  public static final int g = 188;
  
  public static final int h = 5;
  
  public static final File i = new File(new File(System.getProperty("user.home")), ".ConTenDoViewer");
  
  public static final File j = new File(i, "config");
  
  public static final String k = "config";
  
  public static final File l = new File(i, "default");
  
  public static final String m = ".cache";
  
  public static final String n = "sort";
  
  public static final String o = "thumb_small";
  
  public static final String p = "pdf_info";
  
  public static final int q = Math.max(1, Runtime.getRuntime().availableProcessors() / 2);
  
  public static final Color[] r = new Color[] { Color.WHITE, Color.IVORY, Color.LIGHTGRAY, Color.AQUA };
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/b.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */