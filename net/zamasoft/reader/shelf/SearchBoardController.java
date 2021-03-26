package net.zamasoft.reader.shelf;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import net.zamasoft.reader.book.f;
import net.zamasoft.reader.book.m;
import net.zamasoft.reader.util.c;

public class SearchBoardController extends AbstractBoardController implements Initializable {
  @FXML
  protected TextField queryText;
  
  private Pane b;
  
  @FXML
  private Node noBooks;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    this.noBooks.setVisible(false);
  }
  
  @FXML
  private void search(ActionEvent paramActionEvent) {
    a();
    this.noBooks.setVisible((this.booksController.booksPane.getChildren().size() == 0));
  }
  
  public void a() {
    String str = this.queryText.getText();
    this.booksController.booksPane.getChildren().clear();
    if (str.length() == 0)
      return; 
    ObservableList observableList = this.b.getChildren();
    for (Node node : observableList) {
      b b = ((a)node).a;
      if (!a(b, str))
        continue; 
      this.booksController.a(b);
    } 
    this.booksController.b();
  }
  
  @FXML
  private void close(ActionEvent paramActionEvent) {
    b();
  }
  
  public void b() {
    ((Pane)this.root.getParent()).getChildren().remove(this.root);
    this.a.toolbarController.b();
  }
  
  public void a(Pane paramPane) {
    this.b = paramPane;
  }
  
  private boolean a(b paramb, String paramString) {
    paramString = c.d(paramString);
    f f = paramb.B();
    if (a(f.i_(), paramString))
      return true; 
    if (a(f.j_(), paramString))
      return true; 
    if (a(f.f(), paramString))
      return true; 
    if (a(f.g(), paramString))
      return true; 
    if (a(f.h(), paramString))
      return true; 
    if (a(f.k_(), paramString))
      return true; 
    if (a(f.j(), paramString))
      return true; 
    if (a(f.m(), paramString))
      return true; 
    m m = paramb.C();
    if (m != null)
      for (m.a a : m.w()) {
        if (c.d(a.a()).indexOf(paramString) != -1)
          return true; 
      }  
    return false;
  }
  
  private boolean a(String[] paramArrayOfString, String paramString) {
    if (paramArrayOfString == null)
      return false; 
    for (String str : paramArrayOfString) {
      if (a(str, paramString))
        return true; 
    } 
    return false;
  }
  
  private boolean a(String paramString1, String paramString2) {
    return (paramString1 != null && c.d(paramString1).indexOf(paramString2) != -1);
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/SearchBoardController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */