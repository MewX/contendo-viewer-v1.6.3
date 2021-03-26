package net.zamasoft.reader.book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import net.zamasoft.reader.ReaderApplication;
import net.zamasoft.reader.util.c;

public class SidePaneController implements Initializable {
  private static Logger a = Logger.getLogger(SidePaneController.class.getName());
  
  @FXML
  protected TabPane root;
  
  @FXML
  protected Tab markerTab;
  
  @FXML
  private Button applyButton;
  
  @FXML
  private Button resetButton;
  
  @FXML
  private Button saveDefaultButton;
  
  @FXML
  private TreeView<String> outline;
  
  @FXML
  private ListView<s> markers;
  
  @FXML
  private ListView<n> bookmarks;
  
  @FXML
  private ComboBox<Double> textSize;
  
  @FXML
  private ComboBox<String> writingMode;
  
  @FXML
  private ComboBox<String> spread;
  
  @FXML
  private ComboBox<String> background;
  
  @FXML
  private ComboBox<String> invert;
  
  @FXML
  private ComboBox<String> lineHeight;
  
  @FXML
  private ComboBox<String> letterSpacing;
  
  @FXML
  private ComboBox<String> fontFamily;
  
  @FXML
  private ComboBox<String> ruby;
  
  @FXML
  private ComboBox<String> flip;
  
  @FXML
  private ComboBox<String> internetSearch;
  
  @FXML
  private Slider brightnessSlider;
  
  @FXML
  private CheckBox displayRequiredCheck;
  
  @FXML
  private Label displayOrientationLabel;
  
  @FXML
  private ComboBox<String> displayOrientationCombo;
  
  private BookController b;
  
  private final Map<TreeItem<String>, m.a> c = new HashMap<>();
  
  private p d;
  
  public void initialize(URL paramURL, ResourceBundle paramResourceBundle) {
    c.a(this.writingMode);
    c.a(this.spread);
    c.a(this.background);
    c.a(this.invert);
    c.a(this.lineHeight);
    c.a(this.letterSpacing);
    c.a(this.fontFamily);
    c.a(this.ruby);
    c.a(this.flip);
    c.a(this.internetSearch);
    c.a(this.displayOrientationCombo);
    this.markers.setCellFactory(new Callback<ListView<s>, ListCell<s>>(this) {
          public ListCell<s> a(ListView<s> param1ListView) {
            return new SidePaneController.b();
          }
        });
    this.bookmarks.setCellFactory(new Callback<ListView<n>, ListCell<n>>(this) {
          public ListCell<n> a(ListView<n> param1ListView) {
            return new SidePaneController.a(this.a);
          }
        });
    this.d = new p();
    if (net.zamasoft.reader.b.l.exists()) {
      Properties properties = new Properties();
      try {
        FileInputStream fileInputStream = new FileInputStream(net.zamasoft.reader.b.l);
        try {
          properties.loadFromXML(fileInputStream);
        } finally {
          fileInputStream.close();
        } 
        this.d.b(properties);
      } catch (Exception exception) {
        a.log(Level.WARNING, "設定ファイルを読み込めませんでした。", exception);
      } 
    } 
    this.root.focusedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            if (param1Boolean2.booleanValue())
              this.a.b.b(true); 
          }
        });
  }
  
  public void a(BookController paramBookController) {
    this.b = paramBookController;
    this.brightnessSlider.valueProperty().set(ReaderApplication.getInstance().a().doubleValue());
    this.b.maskPane.setOpacity(this.brightnessSlider.getValue());
    this.brightnessSlider.valueProperty().addListener(new ChangeListener<Number>(this, paramBookController) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            this.a.maskPane.setOpacity(param1Number2.doubleValue());
            ReaderApplication.getInstance().a().set(param1Number2.doubleValue());
          }
        });
    this.displayRequiredCheck.selectedProperty().set(ReaderApplication.getInstance().b().get());
    this.displayRequiredCheck.selectedProperty().addListener(new ChangeListener<Boolean>(this) {
          public void a(ObservableValue<? extends Boolean> param1ObservableValue, Boolean param1Boolean1, Boolean param1Boolean2) {
            ReaderApplication.getInstance().b().set(param1Boolean2.booleanValue());
          }
        });
    if (ReaderApplication.getInstance().c() == null) {
      this.displayOrientationLabel.setVisible(false);
      this.displayOrientationCombo.setVisible(false);
    } else {
      int i = ReaderApplication.getInstance().c().get();
      if (i == 4) {
        i = 3;
      } else if (i == 8) {
        i = 4;
      } 
      this.displayOrientationCombo.getSelectionModel().select(i);
      this.displayOrientationCombo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(this) {
            public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
              int i = param1Number2.intValue();
              if (i == 3) {
                i = 4;
              } else if (i == 4) {
                i = 8;
              } 
              ReaderApplication.getInstance().c().set(i);
            }
          });
    } 
  }
  
  @FXML
  private void outlineMouseClicked(MouseEvent paramMouseEvent) {
    m.a a = this.c.get(this.outline.getSelectionModel().getSelectedItem());
    if (a == null)
      return; 
    this.b.a(a.b());
  }
  
  @FXML
  private void markersMouseClicked(MouseEvent paramMouseEvent) {
    s s = (s)this.markers.getSelectionModel().getSelectedItem();
    if (s == null)
      return; 
    this.b.a(s.b());
  }
  
  @FXML
  private void bookmarksMouseClicked(MouseEvent paramMouseEvent) {
    n n = (n)this.bookmarks.getSelectionModel().getSelectedItem();
    if (n == null)
      return; 
    this.b.a(n.a);
  }
  
  private void a(TreeItem<String> paramTreeItem, m.a[] paramArrayOfa) {
    for (m.a a1 : paramArrayOfa) {
      TreeItem<String> treeItem = new TreeItem(a1.a());
      paramTreeItem.getChildren().add(treeItem);
      this.c.put(treeItem, a1);
      a(treeItem, a1.c());
    } 
    paramTreeItem.setExpanded(true);
  }
  
  public void a() {
    if (!this.b.g.s() && ((ObservableList)this.writingMode.itemsProperty().get()).size() == 4) {
      ((ObservableList)this.writingMode.itemsProperty().get()).remove(3);
      ((ObservableList)this.writingMode.itemsProperty().get()).set(1, net.zamasoft.reader.b.a.getString("ltr"));
      ((ObservableList)this.writingMode.itemsProperty().get()).set(2, net.zamasoft.reader.b.a.getString("rtl"));
      ((ObservableList)this.spread.itemsProperty().get()).set(0, net.zamasoft.reader.b.a.getString("spread-with-cover"));
      GridPane gridPane = (GridPane)this.textSize.getParent();
      ObservableList observableList = gridPane.getChildren();
      observableList.remove(observableList.indexOf(this.textSize) - 1);
      observableList.remove(this.textSize);
      observableList.remove(observableList.indexOf(this.background) - 1);
      observableList.remove(this.background);
      observableList.remove(observableList.indexOf(this.lineHeight) - 1);
      observableList.remove(this.lineHeight);
      observableList.remove(observableList.indexOf(this.letterSpacing) - 1);
      observableList.remove(this.letterSpacing);
      observableList.remove(observableList.indexOf(this.fontFamily) - 1);
      observableList.remove(this.fontFamily);
      observableList.remove(observableList.indexOf(this.ruby) - 1);
      observableList.remove(this.ruby);
      for (byte b = 0; b < observableList.size(); b++)
        GridPane.setRowIndex((Node)observableList.get(b), Integer.valueOf(b / 2)); 
    } 
    m m = this.b.g.C();
    if (m != null) {
      TreeItem<String> treeItem = new TreeItem(m.v());
      a(treeItem, m.x());
      this.outline.setRoot(treeItem);
    } 
    this.markers.setItems(this.b.g.o());
    this.bookmarks.setItems(this.b.g.p());
    p p1 = this.b.g.l_();
    a(p1);
    ChangeListener<Object> changeListener = new ChangeListener<Object>(this) {
        public void changed(ObservableValue<? extends Object> param1ObservableValue, Object param1Object1, Object param1Object2) {
          this.a.c();
        }
      };
    this.textSize.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.writingMode.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.spread.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.background.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.invert.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.lineHeight.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.letterSpacing.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.fontFamily.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.ruby.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.flip.getSelectionModel().selectedItemProperty().addListener(changeListener);
    this.internetSearch.getSelectionModel().selectedItemProperty().addListener(changeListener);
    c();
  }
  
  private void c() {
    p p1 = new p();
    b(p1);
    this.applyButton.setDisable(p1.equals(this.b.g.l_()));
    this.resetButton.setDisable(p1.equals(new p()));
    this.saveDefaultButton.setDisable(p1.equals(this.d));
  }
  
  public void a(p paramp) {
    this.textSize.setValue(Double.valueOf(paramp.o));
    this.ruby.getSelectionModel().select(paramp.q ? 0 : 1);
    this.writingMode.getSelectionModel().select(paramp.p);
    this.spread.getSelectionModel().select(paramp.n);
    if (paramp.s == 0.0D) {
      this.lineHeight.getSelectionModel().select(0);
    } else {
      this.lineHeight.setValue(String.valueOf(paramp.s));
    } 
    if (paramp.t == 0.0D) {
      this.letterSpacing.getSelectionModel().select(0);
    } else {
      this.letterSpacing.setValue(String.valueOf(paramp.t));
    } 
    if (paramp.r == null) {
      this.fontFamily.getSelectionModel().select(0);
    } else if (paramp.r.equals("serif")) {
      this.fontFamily.getSelectionModel().select(1);
    } else if (paramp.r.equals("sans-serif")) {
      this.fontFamily.getSelectionModel().select(2);
    } 
    this.background.getSelectionModel().select(paramp.v);
    this.invert.getSelectionModel().select(paramp.w ? 1 : 0);
    this.flip.getSelectionModel().select(paramp.u);
    this.internetSearch.getSelectionModel().select(paramp.z);
  }
  
  private void b(p paramp) {
    paramp.o = ((Double)this.textSize.getValue()).doubleValue();
    paramp.q = (this.ruby.getSelectionModel().getSelectedIndex() == 0);
    paramp.p = this.writingMode.getSelectionModel().getSelectedIndex();
    paramp.n = this.spread.getSelectionModel().getSelectedIndex();
    paramp.s = (this.lineHeight.getSelectionModel().getSelectedIndex() == 0) ? 0.0D : Double.parseDouble((String)this.lineHeight.getValue());
    paramp.t = (this.letterSpacing.getSelectionModel().getSelectedIndex() == 0) ? 0.0D : Double.parseDouble((String)this.letterSpacing.getValue());
    switch (this.fontFamily.getSelectionModel().getSelectedIndex()) {
      case 1:
        paramp.r = "serif";
        break;
      case 2:
        paramp.r = "sans-serif";
        break;
      default:
        paramp.r = null;
        break;
    } 
    paramp.v = this.background.getSelectionModel().getSelectedIndex();
    paramp.w = (this.invert.getSelectionModel().getSelectedIndex() == 1);
    paramp.u = this.flip.getSelectionModel().getSelectedIndex();
    paramp.z = this.internetSearch.getSelectionModel().getSelectedIndex();
  }
  
  @FXML
  private void applyConfigAction(ActionEvent paramActionEvent) {
    p p1 = this.b.g.l_();
    b(p1);
    c();
    this.b.toolbarController.c();
    this.b.q();
  }
  
  @FXML
  private void resetConfigAction(ActionEvent paramActionEvent) {
    a(new p());
  }
  
  @FXML
  private void saveDefaultAction(ActionEvent paramActionEvent) {
    p p1 = new p();
    b(p1);
    Properties properties = new Properties();
    p1.a(properties);
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(net.zamasoft.reader.b.l);
      try {
        properties.storeToXML(fileOutputStream, net.zamasoft.reader.b.b);
      } finally {
        fileOutputStream.close();
      } 
    } catch (Exception exception) {
      a.log(Level.WARNING, "設定ファイルを保存できませんでした。", exception);
    } 
    this.d.b(properties);
    c();
  }
  
  public void b() {
    this.textSize.hide();
    this.writingMode.hide();
    this.spread.hide();
    this.background.hide();
    this.invert.hide();
    this.lineHeight.hide();
    this.letterSpacing.hide();
    this.fontFamily.hide();
    this.ruby.hide();
    this.flip.hide();
    this.internetSearch.hide();
  }
  
  private class a extends ListCell<n> {
    private a(SidePaneController this$0) {}
    
    protected void a(n param1n, boolean param1Boolean) {
      super.updateItem(param1n, param1Boolean);
      BorderPane borderPane = new BorderPane();
      if (!param1Boolean) {
        Label label = new Label(param1n.b);
        borderPane.setLeft((Node)label);
        Button button = new Button(net.zamasoft.reader.b.a.getString("delete"));
        borderPane.setRight((Node)button);
        button.setOnMouseClicked(new EventHandler<MouseEvent>(this, param1n) {
              public void a(MouseEvent param2MouseEvent) {
                this.b.a.b.g.p().remove(this.a);
              }
            });
      } 
      setGraphic((Node)borderPane);
    }
  }
  
  private static class b extends ListCell<s> {
    protected void a(s param1s, boolean param1Boolean) {
      super.updateItem(param1s, param1Boolean);
      VBox vBox = new VBox();
      if (!param1Boolean) {
        ObservableList observableList = vBox.getChildren();
        Label label = new Label(param1s.c);
        label.setStyle("-fx-background-color: Yellow; -fx-text-fill: Black;");
        observableList.add(label);
        if (param1s.d.length() > 0) {
          Label label1 = new Label(param1s.d);
          label1.setStyle("-fx-font: normal 14px Meiryo; -fx-padding: 0 0 0 1em;");
          observableList.add(label1);
        } 
      } 
      setGraphic((Node)vBox);
    }
  }
  
  class null implements EventHandler<MouseEvent> {
    null(SidePaneController this$0, n param1n) {}
    
    public void a(MouseEvent param1MouseEvent) {
      this.b.a.b.g.p().remove(this.a);
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/SidePaneController.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */