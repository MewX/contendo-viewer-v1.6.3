package net.zamasoft.reader.util;

import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import jp.cssj.e.b;
import jp.cssj.e.d.a;
import jp.cssj.sakae.c.a.a;
import jp.cssj.sakae.c.a.b;
import jp.cssj.sakae.pdf.c.g;
import net.zamasoft.reader.b;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;

public final class c {
  private static Logger d = Logger.getLogger(c.class.getName());
  
  public static final FilenameFilter a = new FilenameFilter() {
      public boolean accept(File param1File, String param1String) {
        return !param1String.startsWith(".");
      }
    };
  
  private static final String e = "\\/:*?\"<>|.";
  
  public static final File b;
  
  public static final File c;
  
  private static g f;
  
  public static FXMLLoader a(URL paramURL) {
    FXMLLoader fXMLLoader = new FXMLLoader(paramURL);
    fXMLLoader.setResources(b.a);
    fXMLLoader.setClassLoader(c.class.getClassLoader());
    return fXMLLoader;
  }
  
  public static File a(File paramFile1, File paramFile2) throws IOException {
    String str = paramFile1.getName();
    str = a(str);
    File file = a(paramFile2, str);
    FileUtils.copyFile(paramFile1, file);
    return file;
  }
  
  public static String a(String paramString) throws IOException {
    paramString = new String(paramString.getBytes("MS932"), "MS932");
    return paramString.replace('?', '_');
  }
  
  public static File b(File paramFile1, File paramFile2) throws IOException {
    File file = a(paramFile2, paramFile1.getName());
    FileUtils.moveFile(paramFile1, file);
    return file;
  }
  
  public static File a(File paramFile, String paramString) {
    File file = new File(paramFile, paramString);
    if (!file.exists())
      return file; 
    String str1 = FilenameUtils.getExtension(paramString);
    if (str1.length() != 0)
      str1 = "." + str1; 
    String str2 = FilenameUtils.getBaseName(paramString);
    byte b = 0;
    while (true) {
      file = new File(paramFile, str2 + "(" + str2 + ")" + ++b);
      if (!file.exists())
        return file; 
    } 
  }
  
  public static String b(String paramString) {
    return a(paramString, "\\/:*?\"<>|.", false);
  }
  
  private static String a(String paramString1, String paramString2, boolean paramBoolean) {
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < paramString1.length(); b++) {
      char c1 = paramString1.charAt(b);
      if (paramString2.indexOf(c1) != -1 || c1 == '%') {
        String str = Integer.toHexString(c1).toUpperCase();
        stringBuffer.append("%");
        if (str.length() <= 1)
          stringBuffer.append(0); 
        stringBuffer.append(str);
      } else if (paramBoolean && c1 > 'ÿ') {
        String str = Integer.toHexString(c1).toUpperCase();
        stringBuffer.append("%u");
        for (int i = str.length(); i < 4; i++)
          stringBuffer.append('0'); 
        stringBuffer.append(str);
      } else {
        stringBuffer.append(c1);
      } 
    } 
    return stringBuffer.toString();
  }
  
  public static String c(String paramString) {
    StringBuffer stringBuffer = new StringBuffer();
    for (byte b = 0; b < paramString.length(); b++) {
      char c1 = paramString.charAt(b);
      if (c1 == '%' && b + 2 < paramString.length()) {
        char c2 = paramString.charAt(++b);
        if (c2 == 'u') {
          if (b + 4 >= paramString.length()) {
            stringBuffer.append("%u");
          } else {
            String str = "" + paramString.charAt(++b) + paramString.charAt(++b) + paramString.charAt(++b) + paramString.charAt(++b);
            try {
              stringBuffer.append((char)Integer.parseInt(str, 16));
            } catch (NumberFormatException numberFormatException) {
              stringBuffer.append("%u").append(str);
            } 
          } 
        } else {
          String str = "" + c2 + c2;
          try {
            stringBuffer.append((char)Integer.parseInt(str, 16));
          } catch (NumberFormatException numberFormatException) {
            stringBuffer.append('%').append(str);
          } 
        } 
      } else {
        stringBuffer.append(c1);
      } 
    } 
    return stringBuffer.toString();
  }
  
  public static g a() {
    return f;
  }
  
  public static void a(RenderingHints paramRenderingHints) {
    paramRenderingHints.add(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
    paramRenderingHints.add(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
    paramRenderingHints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
    paramRenderingHints.add(new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC));
    paramRenderingHints.add(new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));
    paramRenderingHints.add(new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY));
    paramRenderingHints.add(new RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE));
    paramRenderingHints.add(new RenderingHints(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON));
    paramRenderingHints.add(new RenderingHints(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE));
  }
  
  public static String d(String paramString) {
    if (paramString == null)
      return ""; 
    paramString = paramString.toUpperCase();
    paramString = Normalizer.normalize(paramString, Normalizer.Form.NFKC);
    StringBuffer stringBuffer = new StringBuffer(paramString);
    for (byte b = 0; b < stringBuffer.length(); b++) {
      char c1 = stringBuffer.charAt(b);
      if (c1 >= 'ぁ' && c1 <= 'ん')
        stringBuffer.setCharAt(b, (char)(c1 - 12353 + 12449)); 
    } 
    return stringBuffer.toString();
  }
  
  public static boolean a(double paramDouble) {
    Runtime runtime = Runtime.getRuntime();
    double d1 = runtime.maxMemory();
    double d2 = runtime.freeMemory();
    double d3 = runtime.totalMemory();
    double d4 = (d1 - d3 + d2) / d1;
    return (d4 < paramDouble);
  }
  
  public static void a(ComboBox<String> paramComboBox) {
    ObservableList observableList = (ObservableList)paramComboBox.itemsProperty().get();
    for (byte b = 0; b < observableList.size(); b++) {
      String str1 = (String)observableList.get(b);
      if (str1.startsWith("%"))
        observableList.set(b, b.a.getString(str1.substring(1))); 
    } 
    String str = (String)paramComboBox.getValue();
    if (str.startsWith("%"))
      paramComboBox.setValue(b.a.getString(str.substring(1))); 
  }
  
  public static int b() {
    Integer integer = (Integer)Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval");
    return (integer == null) ? 500 : integer.intValue();
  }
  
  static {
    try {
      f = new g();
      String str = SystemUtils.IS_OS_WINDOWS ? System.getenv("WINDIR") : null;
      if (str != null) {
        File file4 = new File(str, "Fonts\\HGRME.TTC");
        if (file4.exists()) {
          a a = new a();
          a.a = (b)new a(file4);
          a.d = new jp.cssj.sakae.c.a.c(b.f);
          a.e = 600;
          try {
            f.a(a);
          } catch (Exception exception) {
            d.log(Level.WARNING, "フォント読み込みに失敗しました", exception);
          } 
        } 
        File file5 = new File(str, "Fonts\\HGRGE.TTC");
        if (file5.exists()) {
          a a = new a();
          a.a = (b)new a(file5);
          a.d = new jp.cssj.sakae.c.a.c(b.g, b.j);
          a.e = 600;
          try {
            f.a(a);
          } catch (Exception exception) {
            d.log(Level.WARNING, "フォント読み込みに失敗しました", exception);
          } 
        } 
      } 
      if (SystemUtils.IS_OS_MAC) {
        File file4 = new File(str, "/System/Library/Fonts/ヒラギノ明朝 ProN.ttc");
        if (file4.exists()) {
          a a = new a();
          a.a = (b)new a(file4);
          a.d = new jp.cssj.sakae.c.a.c(b.f);
          a.e = 600;
          a.b = 1;
          try {
            f.a(a);
          } catch (Exception exception) {
            d.log(Level.WARNING, "フォント読み込みに失敗しました", exception);
          } 
        } 
        File file5 = new File(str, "/System/Library/Fonts/ヒラギノ角ゴシック W6.ttc");
        if (file5.exists()) {
          a a = new a();
          a.a = (b)new a(file5);
          a.d = new jp.cssj.sakae.c.a.c(b.g, b.j);
          a.e = 600;
          try {
            f.a(a);
          } catch (Exception exception) {
            d.log(Level.WARNING, "フォント読み込みに失敗しました", exception);
          } 
        } 
      } 
      File file1 = new File(b.i, "fonts");
      file1.mkdirs();
      File file2 = b = new File(file1, "ipaexg.ttf");
      if (!file2.exists()) {
        InputStream inputStream = b.class.getResourceAsStream("resources/fonts/ipaexg.ttf");
        try {
          FileOutputStream fileOutputStream = new FileOutputStream(file2);
          try {
            IOUtils.copy(inputStream, fileOutputStream);
            fileOutputStream.close();
          } catch (Throwable throwable) {
            try {
              fileOutputStream.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            } 
            throw throwable;
          } 
          if (inputStream != null)
            inputStream.close(); 
        } catch (Throwable throwable) {
          if (inputStream != null)
            try {
              inputStream.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            }  
          throw throwable;
        } 
      } 
      a a2 = new a();
      a2.a = (b)new a(file2);
      a2.d = new jp.cssj.sakae.c.a.c(b.g, b.j);
      f.a(a2);
      a a1 = new a();
      File file3 = c = new File(file1, "ipaexm.ttf");
      if (!file3.exists()) {
        InputStream inputStream = b.class.getResourceAsStream("resources/fonts/ipaexm.ttf");
        try {
          FileOutputStream fileOutputStream = new FileOutputStream(file3);
          try {
            IOUtils.copy(inputStream, fileOutputStream);
            fileOutputStream.close();
          } catch (Throwable throwable) {
            try {
              fileOutputStream.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            } 
            throw throwable;
          } 
          if (inputStream != null)
            inputStream.close(); 
        } catch (Throwable throwable) {
          if (inputStream != null)
            try {
              inputStream.close();
            } catch (Throwable throwable1) {
              throwable.addSuppressed(throwable1);
            }  
          throw throwable;
        } 
      } 
      a1.a = (b)new a(file3);
      a1.d = new jp.cssj.sakae.c.a.c(b.f);
      f.a(a1);
      if (str != null) {
        File file = new File(str, "Fonts\\msmincho.ttc");
        if (file.exists()) {
          a a = new a();
          a.a = (b)new a(file);
          a.d = new jp.cssj.sakae.c.a.c(b.f);
          try {
            f.a(a);
          } catch (Exception exception) {
            d.log(Level.WARNING, "フォント読み込みに失敗しました", exception);
          } 
        } 
        file3 = new File(str, "Fonts\\meiryo.ttc");
        if (file3.exists()) {
          a a = new a();
          a.a = (b)new a(file3);
          a.d = new jp.cssj.sakae.c.a.c(b.f);
          try {
            f.a(a);
          } catch (Exception exception) {
            d.log(Level.WARNING, "フォント読み込みに失敗しました", exception);
          } 
        } 
      } 
      if (SystemUtils.IS_OS_MAC) {
        File file4 = new File(str, "/System/Library/Fonts/ヒラギノ明朝 ProN.ttc");
        if (file4.exists()) {
          a a = new a();
          a.a = (b)new a(file4);
          a.d = new jp.cssj.sakae.c.a.c(b.f);
          try {
            f.a(a);
          } catch (Exception exception) {
            d.log(Level.WARNING, "フォント読み込みに失敗しました", exception);
          } 
        } 
        file3 = new File(str, "/System/Library/Fonts/ヒラギノ角ゴシック W3.ttc");
        if (file3.exists()) {
          a a = new a();
          a.a = (b)new a(file3);
          a.d = new jp.cssj.sakae.c.a.c(b.g, b.j);
          try {
            f.a(a);
          } catch (Exception exception) {
            d.log(Level.WARNING, "フォント読み込みに失敗しました", exception);
          } 
        } 
        File file5 = new File(str, "/System/Library/Fonts/Apple Symbols.ttf");
        if (file5.exists()) {
          a a = new a();
          a.a = (b)new a(file5);
          a.d = new jp.cssj.sakae.c.a.c(b.f);
          try {
            f.a(a);
          } catch (Exception exception) {
            d.log(Level.WARNING, "フォント読み込みに失敗しました", exception);
          } 
        } 
      } 
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/util/c.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */