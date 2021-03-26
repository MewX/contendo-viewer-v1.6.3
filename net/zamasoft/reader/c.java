package net.zamasoft.reader;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import net.zamasoft.reader.util.SVGImage;
import org.apache.commons.lang3.SystemUtils;

public class c {
  private static Cursor a;
  
  private static Cursor b;
  
  private static Cursor c;
  
  private static Cursor d;
  
  private static Cursor e;
  
  public static final synchronized Cursor a() {
    if (a != null)
      return a; 
    ImageCursor imageCursor = new ImageCursor((Image)new SVGImage(b.class.getResource("icons/vtext.svg").toString(), 32.0F, false), 16.0D, 16.0D);
    if (!SystemUtils.IS_OS_MAC)
      a = (Cursor)imageCursor; 
    return (Cursor)imageCursor;
  }
  
  public static final synchronized Cursor b() {
    if (b != null)
      return b; 
    ImageCursor imageCursor = new ImageCursor((Image)new SVGImage(b.class.getResource("icons/zoom-in.svg").toString(), 32.0F, false), 16.0D, 16.0D);
    if (!SystemUtils.IS_OS_MAC)
      b = (Cursor)imageCursor; 
    return (Cursor)imageCursor;
  }
  
  public static final synchronized Cursor c() {
    if (c != null)
      return c; 
    ImageCursor imageCursor = new ImageCursor((Image)new SVGImage(b.class.getResource("icons/zoom-out.svg").toString(), 32.0F, false), 16.0D, 16.0D);
    if (!SystemUtils.IS_OS_MAC)
      c = (Cursor)imageCursor; 
    return (Cursor)imageCursor;
  }
  
  public static final synchronized Cursor d() {
    if (d != null)
      return d; 
    ImageCursor imageCursor = new ImageCursor((Image)new SVGImage(b.class.getResource("icons/marker-cursor.svg").toString(), 32.0F, false), 16.0D, 16.0D);
    if (!SystemUtils.IS_OS_MAC)
      d = (Cursor)imageCursor; 
    return (Cursor)imageCursor;
  }
  
  public static final synchronized Cursor e() {
    if (e != null)
      return e; 
    ImageCursor imageCursor = new ImageCursor((Image)new SVGImage(b.class.getResource("icons/move-cursor.svg").toString(), 32.0F, false), 16.0D, 16.0D);
    if (!SystemUtils.IS_OS_MAC)
      e = (Cursor)imageCursor; 
    return (Cursor)imageCursor;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/c.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */