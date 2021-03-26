package net.zamasoft.reader.book;

import java.awt.Toolkit;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.a;
import net.zamasoft.reader.util.SVGImage;
import org.apache.commons.lang3.ArrayUtils;

public class ToolBarController implements Initializable, a {
  @FXML
  protected Pane root;
  
  @FXML
  private Pane toolbar;
  
  @FXML
  private ToggleButton searchButton;
  
  @FXML
  private ToggleButton markButton;
  
  @FXML
  private ToggleButton bookmarkButton;
  
  @FXML
  private ToggleButton textSizeButton;
  
  @FXML
  private ToggleButton writingModeButton;
  
  @FXML
  private Button fullScreenButton;
  
  @FXML
  private Button spreadButton;
  
  @FXML
  private Button invertButton;
  
  private Button a;
  
  @FXML
  private Pane searchBox;
  
  @FXML
  private Pane bookmarkBox;
  
  @FXML
  private Pane writingModeBox;
  
  @FXML
  private Pane textSizeBox;
  
  @FXML
  private TextField queryText;
  
  @FXML
  private Button searchListButton;
  
  @FXML
  private TextField bookmarkText;
  
  @FXML
  private Button addBookmarkButton;
  
  @FXML
  private ToggleGroup writingModeToggleGroup;
  
  @FXML
  private ToggleGroup textSizeToggleGroup;
  
  private BookController b;
  
  private ListView<b> c;
  
  private ChangeListener<Boolean> d = new ChangeListener<Boolean>(this) {
      public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
        this.a.c();
      }
    };
  
  private static final double[] e = new double[] { 0.7D, 0.8D, 0.9D, 1.0D, 1.2D, 1.4D, 1.6D, 2.0D };
  
  private ChangeListener<Toggle> f = new ChangeListener<Toggle>(this) {
      public void a(ObservableValue<? extends Toggle> param1ObservableValue, Toggle param1Toggle1, Toggle param1Toggle2) {
        if (param1Toggle2 == null) {
          this.a.textSizeToggleGroup.selectToggle(param1Toggle1);
          return;
        } 
        int i = this.a.textSizeToggleGroup.getToggles().indexOf(param1Toggle2);
        double d = ToolBarController.e[i];
        (this.a.b.g.l_()).o = d;
        this.a.b.sidepaneController.a(this.a.b.g.l_());
        this.a.b.q();
        this.a.c();
      }
    };
  
  private ChangeListener<Toggle> g = new ChangeListener<Toggle>(this) {
      public void a(ObservableValue<? extends Toggle> param1ObservableValue, Toggle param1Toggle1, Toggle param1Toggle2) {
        if (param1Toggle2 == null) {
          (this.a.b.g.l_()).p = 0;
        } else {
          int i = this.a.writingModeToggleGroup.getToggles().indexOf(param1Toggle2);
          (this.a.b.g.l_()).p = i + 1;
        } 
        this.a.b.sidepaneController.a(this.a.b.g.l_());
        this.a.b.q();
        this.a.c();
      }
    };
  
  private boolean h = false;
  
  private void a(Node paramNode, boolean paramBoolean) {
    paramNode.setVisible(paramBoolean);
    if (paramBoolean) {
      paramNode.setLayoutY(this.toolbar.getHeight());
    } else {
      paramNode.setLayoutY(0.0D);
    } 
  }
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    this.searchButton.selectedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            this.a.a((Node)this.a.searchBox, param1Boolean2.booleanValue());
            if (param1Boolean2.booleanValue()) {
              this.a.b.a(new A(this.a.b, this.a.queryText));
              this.a.queryText.requestFocus();
            } else {
              this.a.b.a(new x(this.a.b));
              if (this.a.c != null)
                this.a.root.getChildren().remove(this.a.c); 
            } 
          }
        });
    this.queryText.setOnAction(paramActionEvent -> {
          this.searchListButton.fire();
          paramActionEvent.consume();
        });
    this.bookmarkButton.selectedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            this.a.a((Node)this.a.bookmarkBox, param1Boolean2.booleanValue());
            if (param1Boolean2.booleanValue()) {
              this.a.bookmarkText.setText((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
              this.a.bookmarkText.requestFocus();
            } 
          }
        });
    this.bookmarkText.setOnAction(paramActionEvent -> {
          this.addBookmarkButton.fire();
          paramActionEvent.consume();
        });
    this.markButton.selectedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            if (param1Boolean2.booleanValue()) {
              this.a.b.a(new r(this.a.b));
            } else {
              this.a.b.a(new x(this.a.b));
            } 
          }
        });
    this.textSizeButton.selectedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            this.a.a((Node)this.a.textSizeBox, param1Boolean2.booleanValue());
            if (param1Boolean2.booleanValue()) {
              this.a.textSizeBox.relocate(this.a.textSizeButton.getLayoutX() - (this.a.textSizeBox.getWidth() - this.a.textSizeButton.getWidth() - 10.0D) / 2.0D + 26.0D, this.a.textSizeBox.getLayoutY());
              this.a.textSizeToggleGroup.selectedToggleProperty().addListener(this.a.f);
            } else {
              this.a.textSizeToggleGroup.selectedToggleProperty().removeListener(this.a.f);
            } 
          }
        });
    this.writingModeButton.selectedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            this.a.a((Node)this.a.writingModeBox, param1Boolean2.booleanValue());
            if (param1Boolean2.booleanValue()) {
              this.a.writingModeBox.relocate(this.a.writingModeButton.getLayoutX() - (this.a.writingModeBox.getWidth() - this.a.writingModeButton.getWidth() - 10.0D) / 2.0D, this.a.writingModeBox.getLayoutY());
              this.a.writingModeToggleGroup.selectedToggleProperty().addListener(this.a.g);
            } else {
              this.a.writingModeToggleGroup.selectedToggleProperty().removeListener(this.a.g);
            } 
          }
        });
    this.searchButton.focusedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            if (param1Boolean2.booleanValue())
              this.a.b.a(true); 
          }
        });
    ReaderApplication.getInstance().k().fullScreenProperty().addListener(this.d);
  }
  
  public void a(BookController paramBookController) {
    this.b = paramBookController;
  }
  
  public void b() {
    this.textSizeButton.setSelected(false);
    this.writingModeButton.setSelected(false);
  }
  
  public void c() {
    Pane pane2 = (Pane)this.spreadButton.getGraphic();
    ((Node)pane2.getChildren().get(0)).setVisible(((this.b.g.l_()).n == 0));
    ((Node)pane2.getChildren().get(1)).setVisible(((this.b.g.l_()).n == 1));
    ((Node)pane2.getChildren().get(2)).setVisible(((this.b.g.l_()).n == 2));
    boolean bool = ReaderApplication.getInstance().k().isFullScreen();
    Pane pane3 = (Pane)this.fullScreenButton.getGraphic();
    ((Node)pane3.getChildren().get(0)).setVisible(!bool);
    ((Node)pane3.getChildren().get(1)).setVisible(bool);
    Pane pane1 = (Pane)this.invertButton.getGraphic();
    ((Node)pane1.getChildren().get(0)).setVisible(!(this.b.g.l_()).w);
    ((Node)pane1.getChildren().get(1)).setVisible((this.b.g.l_()).w);
    if (!this.b.g.s()) {
      pane1 = (Pane)this.spreadButton.getGraphic();
      ((ImageView)pane1.getChildren().get(0)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/spread-1.svg").toString(), 64.0F));
      ((ImageView)pane1.getChildren().get(1)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/spread-2.svg").toString(), 64.0F));
    } 
    if (!this.b.g.s() && this.writingModeBox.getChildren().size() != 2) {
      StackPane stackPane1 = (StackPane)this.writingModeButton.getGraphic();
      StackPane stackPane2 = (StackPane)stackPane1.getChildren().get(0);
      ((ImageView)stackPane2.getChildren().get(0)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/dir-l2r.svg").toString(), 64.0F));
      ((ImageView)stackPane2.getChildren().get(1)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/dir-r2l.svg").toString(), 64.0F));
      StackPane stackPane3 = (StackPane)stackPane1.getChildren().get(1);
      ((ImageView)stackPane3.getChildren().get(0)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/dir-l2r-selected.svg").toString(), 64.0F));
      ((ImageView)stackPane3.getChildren().get(1)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/dir-r2l-selected.svg").toString(), 64.0F));
      this.writingModeBox.getChildren().remove(this.writingModeBox.getChildren().size() - 1);
      ToggleButton toggleButton1 = (ToggleButton)this.writingModeBox.getChildren().get(0);
      stackPane2 = (StackPane)toggleButton1.getGraphic();
      ((ImageView)stackPane2.getChildren().get(0)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/dir-l2r.svg").toString(), 64.0F));
      ((ImageView)stackPane2.getChildren().get(1)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/dir-l2r-selected.svg").toString(), 64.0F));
      ToggleButton toggleButton2 = (ToggleButton)this.writingModeBox.getChildren().get(1);
      stackPane3 = (StackPane)toggleButton2.getGraphic();
      ((ImageView)stackPane3.getChildren().get(0)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/dir-r2l.svg").toString(), 64.0F));
      ((ImageView)stackPane3.getChildren().get(1)).setImage((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/dir-r2l-selected.svg").toString(), 64.0F));
      this.a = new Button();
      stackPane1 = new StackPane();
      ImageView imageView = new ImageView((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/zoom.svg").toString(), 48.0F));
      imageView.setFitWidth(48.0D);
      imageView.setPickOnBounds(true);
      imageView.setPreserveRatio(true);
      imageView.getStyleClass().add("normal");
      stackPane1.getChildren().add(imageView);
      imageView = new ImageView((Image)new SVGImage(net.zamasoft.reader.b.class.getResource("icons/zoom-selected.svg").toString(), 48.0F));
      imageView.setFitWidth(48.0D);
      imageView.setPickOnBounds(true);
      imageView.setPreserveRatio(true);
      imageView.getStyleClass().add("selected");
      stackPane1.getChildren().add(imageView);
      this.a.setGraphic((Node)stackPane1);
      this.a.setTooltip(new Tooltip(net.zamasoft.reader.b.a.getString("zoom")));
      this.toolbar.getChildren().add(1, this.a);
      this.a.setOnAction(new EventHandler<ActionEvent>(this) {
            public void a(ActionEvent param1ActionEvent) {
              this.a.b.a(0.0D);
            }
          });
    } 
    pane1 = (Pane)this.writingModeButton.getGraphic();
    int i = (this.b.g.l_()).p;
    if (i == 0)
      if (this.b.g.l() == 2) {
        i = 2;
      } else {
        i = 1;
      }  
    for (Node node : pane1.getChildren()) {
      Pane pane = (Pane)node;
      ((Node)pane.getChildren().get(0)).setVisible((i == 1));
      ((Node)pane.getChildren().get(1)).setVisible((i == 2));
      ((Node)pane.getChildren().get(2)).setVisible((i == 3));
    } 
    if ((this.b.g.l_()).p != 0)
      ((Toggle)this.writingModeToggleGroup.getToggles().get((this.b.g.l_()).p - 1)).setSelected(true); 
    if (!this.b.g.s()) {
      this.toolbar.getChildren().remove(this.textSizeButton);
    } else {
      int j = ArrayUtils.indexOf(e, (this.b.g.l_()).o);
      if (j != -1)
        ((Toggle)this.textSizeToggleGroup.getToggles().get(j)).setSelected(true); 
      if (this.c != null) {
        this.root.getChildren().remove(this.c);
        this.c = null;
        return;
      } 
    } 
  }
  
  @FXML
  private void searchLeftAction(ActionEvent paramActionEvent) {
    a((this.b.g.l() == 1));
  }
  
  @FXML
  private void searchRightAction(ActionEvent paramActionEvent) {
    a(!(this.b.g.l() == 1));
  }
  
  @FXML
  private void bookmarkAction(ActionEvent paramActionEvent) {
    String str = this.bookmarkText.getText().trim();
    if (str.length() == 0)
      return; 
    this.b.g.p().add(new n(str, this.b.g.q()));
    this.bookmarkButton.selectedProperty().set(false);
  }
  
  @FXML
  private void searchListAction(ActionEvent paramActionEvent) {
    if (this.c != null) {
      this.root.getChildren().remove(this.c);
      this.c = null;
      return;
    } 
    String str = this.queryText.getText();
    if (str.length() == 0)
      return; 
    this.c = new ListView();
    this.c.setCellFactory(new Callback<ListView<b>, ListCell<b>>(this, str) {
          public ListCell<ToolBarController.b> a(ListView<ToolBarController.b> param1ListView) {
            return new ToolBarController.a(this.b, this.a);
          }
        });
    this.c.setOnMousePressed(new EventHandler<MouseEvent>(this) {
          public void a(MouseEvent param1MouseEvent) {
            ToolBarController.b b = (ToolBarController.b)this.a.c.getSelectionModel().getSelectedItem();
            if (b == null)
              return; 
            try {
              this.a.b.a(b.a, b.b);
              this.a.b.n();
              D d = D.a(b.a, b.b, b.c, b.d, false);
              this.a.b.o();
              this.a.b.a(d, false);
              this.a.h = true;
            } catch (Exception exception) {
              this.a.b.a(exception);
            } 
          }
        });
    Pane pane1 = (Pane)this.root.getScene().getRoot();
    Pane pane2 = new Pane();
    pane2.setMinWidth(pane1.getWidth());
    pane2.setMinHeight(pane1.getHeight());
    pane1.getChildren().add(pane2);
    pane2.setCursor(Cursor.WAIT);
    this.root.setDisable(true);
    Thread thread = new Thread(this, str, pane1, pane2) {
        public void run() {
          try {
            this.d.a(this.a);
          } finally {
            Platform.runLater(new Runnable(this) {
                  public void run() {
                    this.a.b.getChildren().remove(this.a.c);
                    this.a.d.root.setDisable(false);
                  }
                });
          } 
        }
      };
    thread.start();
  }
  
  private void a(String paramString) {
    ObservableList observableList = FXCollections.observableArrayList();
    for (byte b = 0; b < this.b.g.k(); b++) {
      k k;
      try {
        k = this.b.g.a(b);
        k.v_();
      } catch (Exception exception) {
        this.b.a(exception);
        return;
      } 
      byte b1 = 0;
      while (b1 < k.s_()) {
        j j = k.e(b1);
        String str = j.h();
        if (b1 > 0) {
          String str1 = k.e(b1 - 1).h();
          int m = Math.max(0, str1.length() - paramString.length() + 1);
          String str2 = str1.substring(m) + str1.substring(m);
          int n = str2.indexOf(paramString);
          if (n != -1) {
            int i1 = paramString.length() - str1.length() - m + n;
            b b2 = new b(this, k, b1 - 1, m + n, str1.length() - 1, str1.substring(Math.max(0, m + n - 12), m + n), str.substring(i1, Math.min(str.length(), i1 + 12)));
            observableList.add(b2);
          } 
        } 
        for (int i = 0;; i++) {
          i = str.indexOf(paramString, i);
          if (i == -1) {
            b1++;
            continue;
          } 
          int m = i + paramString.length();
          b b2 = new b(this, k, b1, i, m - 1, str.substring(Math.max(0, i - 12), i), str.substring(m, Math.min(str.length(), m + 12)));
          observableList.add(b2);
        } 
      } 
    } 
    Platform.runLater(new Runnable(this, observableList) {
          public void run() {
            this.b.c.setItems(this.a);
            this.b.root.getChildren().add(this.b.c);
            this.b.c.setLayoutY(this.b.root.getHeight());
          }
        });
  }
  
  private void a(boolean paramBoolean) {
    String str = this.queryText.getText();
    if (str.length() == 0)
      return; 
    Pane pane1 = (Pane)this.root.getScene().getRoot();
    Pane pane2 = new Pane();
    pane2.setMinWidth(pane1.getWidth());
    pane2.setMinHeight(pane1.getHeight());
    pane1.getChildren().add(pane2);
    pane2.setCursor(Cursor.WAIT);
    this.root.setDisable(true);
    if (this.c != null) {
      this.root.getChildren().remove(this.c);
      this.c = null;
    } 
    this.b.o();
    Thread thread = new Thread(this, str, paramBoolean, pane1, pane2) {
        public void run() {
          try {
            this.e.a(this.a, this.b);
          } finally {
            Platform.runLater(new Runnable(this) {
                  public void run() {
                    this.a.c.getChildren().remove(this.a.d);
                    this.a.e.root.setDisable(false);
                  }
                });
          } 
        }
      };
    thread.start();
  }
  
  private void a(String paramString, boolean paramBoolean) {
    try {
      byte b = paramBoolean ? -1 : 1;
      int i = this.b.g.l;
      int j = this.b.g.k.r_();
      if (j == -1)
        j = 0; 
      if (this.h) {
        this.h = false;
        z z = this.b.c[1];
        if (!paramBoolean && z != null && z.a != null) {
          j = z.b.r_();
          i = z.c;
        } 
        i += b;
        if (i < 0) {
          if (--j < 0)
            j = this.b.g.k() - 1; 
          i = this.b.g.a(j).s_() - 1;
        } else if (i >= this.b.g.a(j).s_()) {
          if (++j >= this.b.g.k())
            j = 0; 
          i = 0;
        } 
      } 
      int k = j;
      int m = i;
      boolean bool = true;
      int n = j;
      label78: while (true) {
        if (n == -1)
          n = this.b.g.k() - 1; 
        k k1 = this.b.g.a(n);
        k1.v_();
        int i1;
        for (i1 = (n == k) ? i : (paramBoolean ? (k1.s_() - 1) : 0);; i1 = (i1 + b) % k1.s_()) {
          j j1 = k1.e(i1);
          String str = j1.h();
          boolean bool1 = (str.indexOf(paramString) != -1) ? true : false;
          if (!bool1 && i1 > 0) {
            String str1 = k1.e(i1 - 1).h();
            String str2 = str1.substring(Math.max(0, str1.length() - paramString.length() + 1)) + str1.substring(Math.max(0, str1.length() - paramString.length() + 1));
            bool1 = (str2.indexOf(paramString) != -1) ? true : false;
          } 
          if (!bool1 && i1 < k1.s_() - 1) {
            String str1 = k1.e(i1 + 1).h();
            String str2 = str.substring(Math.max(0, str.length() - paramString.length() + 1)) + str.substring(Math.max(0, str.length() - paramString.length() + 1));
            bool1 = (str2.indexOf(paramString) != -1) ? true : false;
          } 
          if (bool1) {
            k k2 = k1;
            int i2 = i1;
            this.b.a(k2, i2);
            Platform.runLater(new Runnable(this, paramString) {
                  public void run() {
                    try {
                      this.b.b.n();
                    } catch (Exception exception) {
                      this.b.b.a(exception);
                      return;
                    } 
                    for (byte b = 0; b < 2; b++) {
                      z z = this.b.b.c[b];
                      if (z != null && z.a != null) {
                        String str = z.a.h();
                        int i = -1;
                        if (z.c > 0) {
                          String str1 = z.b.e(z.c - 1).h();
                          String str2 = str1.substring(Math.max(0, str1.length() - this.a.length() + 1)) + str1.substring(Math.max(0, str1.length() - this.a.length() + 1));
                          int j = str2.indexOf(this.a);
                          if (j != -1) {
                            D d = D.a(z.b, z.c, 0, j, false);
                            this.b.b.a(d, false);
                            this.b.h = true;
                          } 
                        } 
                        while (true) {
                          i = str.indexOf(this.a, i + 1);
                          if (i == -1) {
                            if (z.c < z.b.s_() - 1) {
                              String str1 = z.b.e(z.c + 1).h();
                              String str2 = str.substring(Math.max(0, str.length() - this.a.length() + 1)) + str.substring(Math.max(0, str.length() - this.a.length() + 1));
                              int k = str2.indexOf(this.a);
                              if (k != -1) {
                                D d1 = D.a(z.b, z.c, str.length() - this.a.length() + k + 1, str.length() - 1, false);
                                this.b.b.a(d1, false);
                                this.b.h = true;
                              } 
                            } 
                            break;
                          } 
                          int j = i + this.a.length() - 1;
                          D d = D.a(z.b, z.c, i, j, false);
                          this.b.b.a(d, false);
                          this.b.h = true;
                        } 
                      } 
                    } 
                  }
                });
            break;
          } 
          if (!bool && n == k && i1 == m) {
            Toolkit.getDefaultToolkit().beep();
            break;
          } 
          bool = false;
          if (paramBoolean) {
            if (i1 <= 0) {
              i = this.b.g.a(k).s_() - 1;
              continue label78;
            } 
            continue;
          } 
          if (i1 == k1.s_() - 1) {
            i = 0;
            n = (n + b) % this.b.g.k();
            continue;
          } 
          continue;
        } 
        break;
      } 
    } catch (Exception exception) {
      this.b.a(exception);
    } 
  }
  
  @FXML
  private void toShelfAction(ActionEvent paramActionEvent) {
    ReaderApplication.getInstance().j().a();
  }
  
  @FXML
  private void spreadAction(ActionEvent paramActionEvent) {
    (this.b.g.l_()).n = ((this.b.g.l_()).n + 1) % 3;
    c();
    this.b.sidepaneController.a(this.b.g.l_());
    this.b.q();
  }
  
  @FXML
  private void fullScreenAction(ActionEvent paramActionEvent) {
    Stage stage = ReaderApplication.getInstance().k();
    stage.setFullScreen(!stage.isFullScreen());
  }
  
  @FXML
  private void invertAction(ActionEvent paramActionEvent) {
    (this.b.g.l_()).w = !(this.b.g.l_()).w;
    c();
    this.b.sidepaneController.a(this.b.g.l_());
    this.b.q();
  }
  
  public void a() {
    ReaderApplication.getInstance().k().fullScreenProperty().removeListener(this.d);
  }
  
  private class b {
    public final k a;
    
    public final int b;
    
    public final int c;
    
    public final int d;
    
    public final String e;
    
    public final String f;
    
    public b(ToolBarController this$0, k param1k, int param1Int1, int param1Int2, int param1Int3, String param1String1, String param1String2) {
      this.a = param1k;
      this.b = param1Int1;
      this.c = param1Int2;
      this.d = param1Int3;
      this.e = param1String1;
      this.f = param1String2;
    }
  }
  
  class a extends ListCell<b> {
    a(ToolBarController this$0, String param1String) {}
    
    protected void a(ToolBarController.b param1b, boolean param1Boolean) {
      super.updateItem(param1b, param1Boolean);
      if (param1Boolean)
        return; 
      VBox vBox = new VBox();
      Label label1 = new Label(this.b.b.g.c(param1b.a.r_(), param1b.b));
      vBox.getChildren().add(label1);
      HBox hBox = new HBox();
      ObservableList observableList = hBox.getChildren();
      Label label2 = new Label("…" + param1b.e);
      label2.setStyle("-fx-font: normal 14px Meiryo;");
      observableList.add(label2);
      Label label3 = new Label(this.a);
      label3.setStyle("-fx-font: normal 14px Meiryo; -fx-background-color: LightGreen;");
      observableList.add(label3);
      Label label4 = new Label(param1b.f + "…");
      label4.setStyle("-fx-font: normal 14px Meiryo;");
      observableList.add(label4);
      vBox.getChildren().add(hBox);
      setGraphic((Node)vBox);
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/ToolBarController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */