package net.zamasoft.reader.shelf;

import com.b.a.a;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import net.zamasoft.reader.b;
import org.apache.commons.io.FileUtils;

public class WebBoardController extends AbstractBoardController implements Initializable {
  private static Logger c = Logger.getLogger(WebBoardController.class.getName());
  
  private File d = new File(b.i, "books/web");
  
  protected SearchBoardController b;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    try {
      File file = d();
      if (file.exists()) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        try {
          bufferedReader.readLine();
          int i = 0;
          try {
            i = Integer.parseInt(bufferedReader.readLine());
          } catch (Exception exception) {}
          this.booksController.a(i);
        } finally {
          bufferedReader.close();
        } 
      } 
    } catch (Exception exception) {
      c.log(Level.WARNING, "sortファイルを読み込めませんでした。", exception);
    } 
  }
  
  protected File b() {
    String str = a.a().b();
    str = str.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    return new File(this.d, str);
  }
  
  public void a() {
    a(true, true);
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2) {
    JsonObject jsonObject;
    boolean bool = false;
    a a = a.a();
    File file = e();
    try {
      jsonObject = a.a(paramBoolean1);
      if (paramBoolean1)
        FileUtils.write(file, jsonObject.toString(), "UTF-8"); 
      bool = true;
    } catch (Exception exception) {
      c.log(Level.WARNING, "ウェブ書棚接続エラー", exception);
      paramBoolean1 = true;
      if (!file.exists())
        return; 
      try {
        JsonReader jsonReader = Json.createReader(new StringReader(FileUtils.readFileToString(file, "UTF-8")));
        jsonObject = jsonReader.readObject();
      } catch (IOException iOException) {
        c.log(Level.WARNING, "ウェブ書棚キャッシュを読み込めません", exception);
        return;
      } 
    } 
    if (paramBoolean1)
      this.booksController.c(); 
    HashMap<Object, Object> hashMap1 = new HashMap<>();
    JsonArray jsonArray1 = jsonObject.getJsonArray("items_types");
    for (JsonValue jsonValue : jsonArray1) {
      String str = jsonValue.asJsonObject().getString("items_id");
      List<JsonObject> list = (List)hashMap1.get(str);
      if (list == null) {
        list = new ArrayList();
        hashMap1.put(str, list);
      } 
      list.add(jsonValue.asJsonObject());
    } 
    HashMap<Object, Object> hashMap2 = new HashMap<>();
    JsonArray jsonArray2 = jsonObject.getJsonArray("authors");
    for (JsonValue jsonValue : jsonArray2)
      hashMap2.put(jsonValue.asJsonObject().getString("id"), jsonValue.asJsonObject()); 
    HashMap<Object, Object> hashMap3 = new HashMap<>();
    JsonArray jsonArray3 = jsonObject.getJsonArray("publishers");
    for (JsonValue jsonValue : jsonArray3)
      hashMap3.put(jsonValue.asJsonObject().getString("id"), jsonValue.asJsonObject()); 
    HashMap<Object, Object> hashMap4 = new HashMap<>();
    JsonArray jsonArray4 = jsonObject.getJsonArray("items_authors");
    for (JsonValue jsonValue : jsonArray4) {
      JsonObject jsonObject1 = (JsonObject)hashMap2.get(jsonValue.asJsonObject().getString("authors_id"));
      List<String[]> list = (List)hashMap4.get(jsonValue.asJsonObject().getString("items_id"));
      if (list == null)
        hashMap4.put(jsonValue.asJsonObject().getString("items_id"), list = new ArrayList()); 
      list.add(new String[] { jsonObject1.getString("name"), jsonObject1.getString("name_ruby") });
    } 
    HashSet<File> hashSet = new HashSet();
    JsonArray jsonArray5 = jsonObject.getJsonArray("items");
    for (JsonValue jsonValue : jsonArray5) {
      List<String[]> list = (List)hashMap4.get(jsonValue.asJsonObject().getString("id"));
      JsonObject jsonObject1 = (JsonObject)hashMap3.get(jsonValue.asJsonObject().getString("publishers_id"));
      c c = new c(b(), jsonValue.asJsonObject(), (List<JsonObject>)hashMap1.get(jsonValue.asJsonObject().getString("id")), list, jsonObject1.getString("name"));
      if (!paramBoolean1) {
        a a1 = this.booksController.a(c.d());
        if (a1 != null) {
          c = (c)a1.a;
          c.a(b(), jsonValue.asJsonObject(), (List<JsonObject>)hashMap1.get(jsonValue.asJsonObject().getString("id")), list, jsonObject1.getString("name"));
        } 
      } 
      if (bool && paramBoolean2)
        (new File(c.d(), "thumb_small")).delete(); 
      this.booksController.a(c, !paramBoolean1);
      hashSet.add(c.d());
    } 
    this.booksController.b();
    if (paramBoolean1)
      for (File file1 : b().listFiles()) {
        if (!file1.getName().startsWith(".") && file1.isDirectory() && !hashSet.contains(file1))
          try {
            FileUtils.forceDelete(file1);
          } catch (IOException iOException) {
            c.log(Level.WARNING, "ウェブ書棚の不要なファイルを削除できません", iOException);
          }  
      }  
    if (this.b != null)
      this.b.a(); 
  }
  
  private File d() {
    return new File(b(), "sort");
  }
  
  private File e() {
    return new File(b(), "items");
  }
  
  public void c() {
    try {
      File file = d();
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file)), "UTF-8");
      try {
        outputStreamWriter.write("1.6.3");
        outputStreamWriter.write("\n");
        outputStreamWriter.write(String.valueOf(this.booksController.a()));
        outputStreamWriter.write("\n");
      } finally {
        outputStreamWriter.close();
      } 
    } catch (Exception exception) {
      c.log(Level.WARNING, "sortファイルを保存できませんでした。", exception);
    } 
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/WebBoardController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */