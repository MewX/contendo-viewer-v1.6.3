package net.zamasoft.reader.book;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class z extends Pane {
  public final j a;
  
  public final k b;
  
  public final int c;
  
  public final Pane d;
  
  public z(j paramj, k paramk, int paramInt1, double paramDouble1, double paramDouble2, Node paramNode, int paramInt2) {
    double d1;
    double d2;
    this.d = new Pane();
    this.a = paramj;
    this.b = paramk;
    this.c = paramInt1;
    setMinWidth(paramDouble1);
    setMinHeight(paramDouble2);
    getChildren().add(paramNode);
    getChildren().add(this.d);
    switch (paramInt2) {
      case 1:
        d1 = paramDouble1 - this.a.c();
        d2 = (paramDouble2 - this.a.d()) / 2.0D;
        break;
      case 2:
        d1 = 0.0D;
        d2 = (paramDouble2 - this.a.d()) / 2.0D;
        break;
      default:
        d1 = (paramDouble1 - this.a.c()) / 2.0D;
        d2 = (paramDouble2 - this.a.d()) / 2.0D;
        break;
    } 
    for (Node node : getChildren())
      node.relocate(d1, d2); 
    this.d.setMinWidth(paramj.c());
    this.d.setMinHeight(paramj.d());
  }
  
  public double a() {
    return getLayoutX() + this.d.getLayoutX();
  }
  
  public double b() {
    return getLayoutY() + this.d.getLayoutY();
  }
  
  public z() {
    this.d = new Pane();
    this.a = null;
    this.b = null;
    this.c = -1;
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/z.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */