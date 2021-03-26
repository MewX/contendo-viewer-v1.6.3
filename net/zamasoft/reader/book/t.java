package net.zamasoft.reader.book;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import net.zamasoft.reader.b;
import net.zamasoft.reader.util.SVGImage;
import org.apache.commons.lang3.SystemUtils;

public class t extends HBox implements v {
  private MediaPlayer a;
  
  private Duration b;
  
  private Slider c;
  
  private Label d;
  
  private Slider e;
  
  private Button f;
  
  private boolean g = false;
  
  private final ImageView h = new ImageView((Image)new SVGImage(b.class.getResource("icons/audio-play.svg").toString(), 30.0F));
  
  private final ImageView i;
  
  private final ImageView j;
  
  private final ImageView k;
  
  private final ImageView l;
  
  private final ImageView m;
  
  private long n;
  
  protected double computeMinWidth(double paramDouble) {
    return 300.0D;
  }
  
  protected double computeMinHeight(double paramDouble) {
    return 30.0D;
  }
  
  protected double computePrefWidth(double paramDouble) {
    return 300.0D;
  }
  
  protected double computePrefHeight(double paramDouble) {
    return 30.0D;
  }
  
  protected double computeMaxHeight(double paramDouble) {
    return 30.0D;
  }
  
  public t(i parami, MediaPlayer paramMediaPlayer) {
    this.h.setFitWidth(30.0D);
    this.h.setFitHeight(30.0D);
    this.i = new ImageView((Image)new SVGImage(b.class.getResource("icons/audio-mute.svg").toString(), 30.0F));
    this.i.setFitWidth(30.0D);
    this.i.setFitHeight(30.0D);
    this.j = new ImageView((Image)new SVGImage(b.class.getResource("icons/audio-volume.svg").toString(), 30.0F));
    this.j.setFitWidth(30.0D);
    this.j.setFitHeight(30.0D);
    this.k = new ImageView((Image)new SVGImage(b.class.getResource("icons/audio-pause.svg").toString(), 30.0F));
    this.k.setFitWidth(30.0D);
    this.k.setFitHeight(30.0D);
    this.l = new ImageView((Image)new SVGImage(b.class.getResource("icons/video-full.svg").toString(), 30.0F));
    this.l.setFitWidth(30.0D);
    this.l.setFitHeight(30.0D);
    this.m = new ImageView((Image)new SVGImage(b.class.getResource("icons/video-exit.svg").toString(), 30.0F));
    this.m.setFitWidth(30.0D);
    this.m.setFitHeight(30.0D);
    this.n = 0L;
    this.a = paramMediaPlayer;
    setId("audioControls");
    setAlignment(Pos.CENTER);
    setPadding(new Insets(5.0D, 10.0D, 5.0D, 10.0D));
    Button button1 = new Button();
    button1.setGraphic((Node)this.h);
    button1.setOnAction(new EventHandler<ActionEvent>(this, paramMediaPlayer, button1) {
          public void a(ActionEvent param1ActionEvent) {
            this.c.b();
            MediaPlayer.Status status = this.a.getStatus();
            if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED)
              return; 
            if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED) {
              if (this.a.getCurrentTime().equals(this.a.getStopTime()) || status != MediaPlayer.Status.PAUSED)
                this.a.seek(this.a.getStartTime()); 
              this.a.play();
              this.b.setGraphic((Node)this.c.k);
            } else {
              this.a.pause();
            } 
          }
        });
    paramMediaPlayer.currentTimeProperty().addListener(new InvalidationListener(this) {
          public void invalidated(Observable param1Observable) {
            this.a.b();
          }
        });
    paramMediaPlayer.setOnPlaying(new Runnable(this, button1, parami) {
          public void run() {
            this.a.setGraphic((Node)this.c.k);
            if (this.b != null) {
              this.b.b();
              this.c.a();
            } 
          }
        });
    paramMediaPlayer.setOnPaused(new Runnable(this, button1, parami) {
          public void run() {
            this.a.setGraphic((Node)this.c.h);
            if (this.b != null)
              this.b.c(); 
          }
        });
    paramMediaPlayer.setOnEndOfMedia(new Runnable(this, button1, parami) {
          public void run() {
            this.a.setGraphic((Node)this.c.h);
            if (this.b != null)
              this.b.d(); 
          }
        });
    this.b = paramMediaPlayer.getMedia().getDuration();
    paramMediaPlayer.setOnReady(new Runnable(this, paramMediaPlayer) {
          public void run() {
            this.b.b = this.a.getMedia().getDuration();
            this.b.c();
            this.b.b();
            this.b.d.setText(t.a(this.b.b));
          }
        });
    getChildren().add(button1);
    this.c = new Slider();
    HBox.setHgrow((Node)this.c, Priority.ALWAYS);
    this.c.valueProperty().addListener(new InvalidationListener(this) {
          public void invalidated(Observable param1Observable) {
            if (SystemUtils.IS_OS_MAC && this.a.c.isValueChanging())
              return; 
            this.a.a();
          }
        });
    this.c.setOnMouseReleased(paramMouseEvent -> a());
    getChildren().add(this.c);
    this.d = new Label();
    this.d.setPrefWidth(50.0D);
    this.d.setMinWidth(50.0D);
    this.d.setTextAlignment(TextAlignment.CENTER);
    getChildren().add(this.d);
    Button button2 = new Button();
    button2.setGraphic((Node)this.j);
    button2.setOnAction(new EventHandler<ActionEvent>(this, paramMediaPlayer, button2) {
          public void a(ActionEvent param1ActionEvent) {
            if (this.c.g = !this.c.g) {
              this.a.setMute(true);
              this.b.setGraphic((Node)this.c.i);
            } else {
              this.a.setMute(false);
              this.b.setGraphic((Node)this.c.j);
            } 
          }
        });
    getChildren().add(button2);
    this.e = new Slider();
    this.e.setPrefWidth(50.0D);
    this.e.setMaxWidth(Double.NEGATIVE_INFINITY);
    this.e.setMinWidth(50.0D);
    this.e.valueProperty().addListener(new ChangeListener<Number>(this, paramMediaPlayer) {
          public void a(ObservableValue<? extends Number> param1ObservableValue, Number param1Number1, Number param1Number2) {
            this.a.setVolume(this.b.e.getValue() / 100.0D);
          }
        });
    getChildren().add(this.e);
    if (parami != null && parami.f) {
      this.f = new Button();
      this.f.setGraphic((Node)this.l);
      this.f.setOnAction(new EventHandler<ActionEvent>(this, parami) {
            public void a(ActionEvent param1ActionEvent) {
              this.a.f();
            }
          });
      getChildren().add(this.f);
    } 
  }
  
  private void a() {
    if (this.a.getStatus() == MediaPlayer.Status.READY) {
      this.a.play();
      this.a.pause();
    } 
    Duration duration1 = this.b.multiply(this.c.getValue() / 100.0D);
    Duration duration2 = this.a.getCurrentTime();
    double d = Math.abs(duration1.toSeconds() - duration2.toSeconds());
    if (d >= 1.0D && (!SystemUtils.IS_OS_MAC || this.n <= System.currentTimeMillis() - 500L)) {
      this.a.seek(duration1);
      this.n = System.currentTimeMillis();
    } 
    this.d.setText(a(duration1));
  }
  
  public void a(boolean paramBoolean) {
    if (paramBoolean) {
      this.f.setGraphic((Node)this.m);
    } else {
      this.f.setGraphic((Node)this.l);
    } 
  }
  
  private void b() {
    Platform.runLater(new Runnable(this) {
          public void run() {
            Duration duration = (Duration)this.a.a.currentTimeProperty().getValue();
            this.a.c.setDisable(this.a.b.isUnknown());
            if (!this.a.c.isDisabled() && this.a.b.greaterThan(Duration.ZERO) && !this.a.c.isValueChanging())
              this.a.c.setValue(duration.divide(this.a.b.toMillis()).toMillis() * 100.0D); 
          }
        });
  }
  
  private void c() {
    Platform.runLater(new Runnable(this) {
          public void run() {
            if (!this.a.e.isValueChanging())
              this.a.e.setValue((int)Math.round(this.a.a.getVolume() * 100.0D)); 
          }
        });
  }
  
  private static String a(Duration paramDuration) {
    int i = (int)Math.floor(paramDuration.toSeconds());
    int j = i / 3600;
    if (j > 0)
      i -= j * 60 * 60; 
    int k = i / 60;
    int m = i - j * 60 * 60 - k * 60;
    return (j > 0) ? String.format("%d:%02d:%02d", new Object[] { Integer.valueOf(j), Integer.valueOf(k), Integer.valueOf(m) }) : String.format("%02d:%02d", new Object[] { Integer.valueOf(k), Integer.valueOf(m) });
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/reader/book/t.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */