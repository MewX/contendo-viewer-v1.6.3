package net.zamasoft.reader.shelf;

import com.b.a.a;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import net.zamasoft.reader.a;
import net.zamasoft.reader.book.b;
import net.zamasoft.reader.book.f;
import net.zamasoft.reader.book.m;
import net.zamasoft.reader.util.c;

public class ShelfController implements Initializable, a {
  private static Logger c = Logger.getLogger(ShelfController.class.getName());
  
  @FXML
  protected TabPane tabPane;
  
  @FXML
  protected Pane userStack;
  
  @FXML
  protected Pane webStack;
  
  @FXML
  private Pane bookInfo;
  
  @FXML
  private Label titleText;
  
  @FXML
  private Label authorText;
  
  @FXML
  private Label publisherText;
  
  @FXML
  private Label detailText;
  
  @FXML
  protected ToolBarController toolbarController;
  
  protected UserBoardController a;
  
  protected WebBoardController b;
  
  private b d;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    this.tabPane.getSelectionModel().select(1);
    this.toolbarController.a(this);
    try {
      FXMLLoader fXMLLoader = c.a(UserBoardController.class.getResource("user-board.fxml"));
      Node node = (Node)fXMLLoader.load();
      this.a = (UserBoardController)fXMLLoader.getController();
      this.a.a(this);
      this.userStack.getChildren().add(node);
    } catch (IOException iOException) {
      c.log(Level.SEVERE, "棚を追加できませんでした。", iOException);
      throw new RuntimeException(iOException);
    } 
    try {
      FXMLLoader fXMLLoader = c.a(UserBoardController.class.getResource("login.fxml"));
      Node node = (Node)fXMLLoader.load();
      LoginController loginController = (LoginController)fXMLLoader.getController();
      loginController.a(this);
      this.webStack.getChildren().add(node);
    } catch (IOException iOException) {
      c.log(Level.SEVERE, "ログインフォームを追加できませんでした。", iOException);
      throw new RuntimeException(iOException);
    } 
    c();
  }
  
  public void b() {
    this.tabPane.getSelectionModel().select(1);
  }
  
  public void c() {
    if (!a.a().h())
      return; 
    try {
      if (this.b == null) {
        FXMLLoader fXMLLoader = c.a(WebBoardController.class.getResource("web-board.fxml"));
        fXMLLoader.load();
        this.b = (WebBoardController)fXMLLoader.getController();
        this.b.a(this);
      } 
      this.webStack.getChildren().add(this.b.root);
      Platform.runLater(new Runnable(this) {
            public void run() {
              this.a.b.a(true, false);
            }
          });
      this.toolbarController.b();
    } catch (IOException iOException) {
      c.log(Level.WARNING, "ウェブ書棚を追加できませんでした。", iOException);
      throw new RuntimeException(iOException);
    } 
  }
  
  public void d() {
    if (a.a().h())
      return; 
    if (this.b.b != null)
      this.b.b.b(); 
    this.webStack.getChildren().remove(this.b.root);
    this.b.c();
    this.toolbarController.b();
    this.b.booksController.c();
  }
  
  void e() {
    try {
      FXMLLoader fXMLLoader = c.a(SearchBoardController.class.getResource("search-board.fxml"));
      Node node = (Node)fXMLLoader.load();
      SearchBoardController searchBoardController = (SearchBoardController)fXMLLoader.getController();
      searchBoardController.a(this);
      switch (this.tabPane.getSelectionModel().getSelectedIndex()) {
        case 0:
          searchBoardController.a(this.a.booksController.booksPane);
          this.userStack.getChildren().add(node);
          searchBoardController.booksController.a(this.a.booksController.a());
          this.a.c = searchBoardController;
          break;
        case 1:
          searchBoardController.a(this.b.booksController.booksPane);
          this.webStack.getChildren().add(node);
          searchBoardController.booksController.a(this.b.booksController.a());
          this.b.b = searchBoardController;
          break;
        default:
          throw new IllegalStateException();
      } 
      this.toolbarController.b();
      Platform.runLater(new Runnable(this, searchBoardController) {
            public void run() {
              this.a.queryText.requestFocus();
            }
          });
    } catch (IOException iOException) {
      c.log(Level.SEVERE, "検索棚を追加できませんでした。", iOException);
      throw new RuntimeException(iOException);
    } 
  }
  
  void a(int paramInt) {
    switch (this.tabPane.getSelectionModel().getSelectedIndex()) {
      case 0:
        if (this.userStack.getChildren().get(this.userStack.getChildren().size() - 1) != this.a.root) {
          this.a.c.booksController.a(paramInt);
          return;
        } 
        break;
      case 1:
        if (this.webStack.getChildren().get(this.webStack.getChildren().size() - 1) != this.b.root) {
          this.b.b.booksController.a(paramInt);
          return;
        } 
        break;
      default:
        throw new IllegalStateException();
    } 
    AbstractBoardController abstractBoardController = f();
    abstractBoardController.booksController.a(paramInt);
  }
  
  public AbstractBoardController f() {
    switch (this.tabPane.getSelectionModel().getSelectedIndex()) {
      case 0:
        return this.a;
      case 1:
        return this.b;
    } 
    throw new IllegalStateException();
  }
  
  public void g() {
    this.a.booksController.b();
    if (this.b != null)
      this.b.booksController.b(); 
  }
  
  public b a(File paramFile) throws IOException, Exception {
    File file = c.a(paramFile, UserBoardController.b);
    try {
      b b1 = this.a.a(file);
      this.a.booksController.a((b)b1);
      this.a.booksController.b();
      return b1;
    } catch (Exception exception) {
      file.delete();
      throw exception;
    } 
  }
  
  public void a(a parama) {
    b b1 = parama.a;
    if (this.d == b1)
      return; 
    this.d = b1;
    double d = parama.localToScene(0.0D, 0.0D).getY();
    d = this.bookInfo.getParent().sceneToLocal(0.0D, d).getY();
    if (d - 10.0D < this.bookInfo.getHeight()) {
      d += parama.getHeight() + 20.0D;
    } else {
      d = 0.0D;
    } 
    this.bookInfo.setTranslateY(d);
    FadeTransition fadeTransition = new FadeTransition(Duration.millis(300.0D), (Node)this.bookInfo);
    fadeTransition.setFromValue(this.bookInfo.getOpacity());
    fadeTransition.setToValue(1.0D);
    fadeTransition.play();
    f f = b1.B();
    this.titleText.setText((f.i_() == null) ? "" : f.i_().replaceAll("[\n\r]", ""));
    StringBuffer stringBuffer = new StringBuffer();
    a(stringBuffer, f.f());
    this.authorText.setText(stringBuffer.toString().replaceAll("[\n\r]", ""));
    stringBuffer = new StringBuffer();
    a(stringBuffer, f.h());
    this.publisherText.setText(stringBuffer.toString().replaceAll("[\n\r]", ""));
    stringBuffer = new StringBuffer();
    m m = b1.C();
    if (m != null) {
      boolean bool = true;
      for (m.a a1 : m.w()) {
        if (!bool) {
          stringBuffer.append(" / ");
        } else {
          bool = false;
        } 
        stringBuffer.append(a1.a());
      } 
    } 
    this.detailText.setText(stringBuffer.toString());
  }
  
  private void a(StringBuffer paramStringBuffer, String[] paramArrayOfString) {
    boolean bool = true;
    if (paramArrayOfString == null)
      return; 
    for (String str : paramArrayOfString) {
      if (!bool) {
        paramStringBuffer.append(" / ");
      } else {
        bool = false;
      } 
      paramStringBuffer.append(str);
    } 
  }
  
  public void h() {
    if (this.d == null)
      return; 
    this.d = null;
    FadeTransition fadeTransition = new FadeTransition(Duration.millis(300.0D), (Node)this.bookInfo);
    fadeTransition.setFromValue(this.bookInfo.getOpacity());
    fadeTransition.setToValue(0.0D);
    fadeTransition.play();
  }
  
  public void a() {
    this.a.d();
    if (this.b != null)
      this.b.c(); 
    this.toolbarController.a();
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/shelf/ShelfController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */