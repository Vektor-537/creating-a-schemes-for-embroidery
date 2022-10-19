import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import g4p_controls.*; 
import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_220408b extends PApplet {

// Need G4P library

// You can remove the PeasyCam import if you are not using
// the GViewPeasyCam control or the PeasyCam library.

int h=100, w=100;
PImage img; 
PImage img_; 
int [] al;
int [] is;
int buf;
int col_is=0;
boolean im=false, col=true, n_w_d=true; 
int pix=5;
int col_start=0;

public void setup() {
  
  // frameRate(30);
  al=give_all();
  is=new int[al.length];
  is=give_is();
  createGUI();
  customGUI();
  names();

  is=sort(is);
  buf=is[0];
  // Place your setup code here
}

public void draw() {

  if (col | true) { 
    background(230);
    for (int i=0; i<col_is; i++) {
      fill(is[min(i+col_start, col_is-1)]);
      rect(i*10, 0, 10, 10);
    }
    col=false;
  }
}

// Use this method to add additional statements
// to customise the GUI controls
public void customGUI() {
}
public void fileSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
    img = loadImage(selection.getAbsolutePath());
    im=true;
    h=img.height;
    w=img.width;
    println("height="+img.height+" width="+img.width);
    label_xIn.setText(Integer.toString(img.height));
    label_yIn.setText(Integer.toString(img.width));
    label_xIn.setLocalColor(6, 255);  
    label_yIn.setLocalColor(6, 255);  
    label_xOut.setLocalColor(6, 255);  
    label_yOut.setLocalColor(6, 255);
    rozr();
  }
}

public void fileSelected_o(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
    img_.save(selection.getAbsolutePath()+".png");
  }
}
public void mouseClicked() {
  if ((mouseY>=0)&(mouseY<=10))if ((mouseX>=0)&(mouseX<=10*col_is)) {
    is[mouseX/10]=color(255, 255, 255);
    is=sort(is);
    col_is--;
    col=true;
    println(mouseX/10);
    save_is();
  }
}
public void mouseWheel(MouseEvent event) {

  if ((mouseY>=0)&(mouseY<=10))if ((mouseX>=0)&(mouseX<=10*col_is)) {
    float e = event.getCount();
    col_start+=e;
    if (col_start<=0)col_start=0;
    if (col_start>=col_is)col_start=col_is;
    println(e);
    col=true;
  }
}

public int[] give_all() {
  String[] lines = loadStrings("data/al.txt");
  String[] list=new String[lines.length] ;
  int []res=new int[lines.length];
  println("there are all " + lines.length + " colors");

  for (int i = 0; i < lines.length; i++) {
    //   println( (lines[i]));
    String []m  = match(lines[i], "^#([A-Fa-f0-9]{6})");
    //  println("Found '" + m[0] + "' inside a tag.");
    res[i]=color(
      Integer.valueOf(  m[0].substring( 1, 3 ), 16 ), 
      Integer.valueOf(  m[0].substring( 3, 5 ), 16 ), 
      Integer.valueOf(  m[0].substring( 5, 7 ), 16 ) );
    list[i]=" R="+red(res[i])+" g="+green(res[i])+" b="+blue(res[i]);
    //    println(res[i]+" R="+red(res[i])+" g="+green(res[i])+" b="+blue(res[i]));
  }
  saveStrings("data/list_217071", list);
  return res;
}

public int[] give_is() {
  col_is=0;
  String[] lines = loadStrings("data/is.txt");
  String[] list=new String[lines.length] ;
  int []res=new int[al.length];
  println("there are is " + lines.length + " colors");

  for (int i = 0; i < lines.length; i++) {
    //   println( (lines[i]));
    String []m  = match(lines[i], "^#([A-Fa-f0-9]{6})");
    //  println("Found '" + m[0] + "' inside a tag.");
    res[i]=color(
      Integer.valueOf(  m[0].substring( 1, 3 ), 16 ), 
      Integer.valueOf(  m[0].substring( 3, 5 ), 16 ), 
      Integer.valueOf(  m[0].substring( 5, 7 ), 16 ) );
    list[i]=" R="+red(res[i])+" g="+green(res[i])+" b="+blue(res[i]);
    //    println(res[i]+" R="+red(res[i])+" g="+green(res[i])+" b="+blue(res[i]));
    col_is++;
  }
  //saveStrings("data/list_217071", list);
  return res;
}
public void save_is() {
  String[] m=new String[col_is];
  for (int i=0; i<col_is; i++) {
    m[i]=rgb(is[i]);
    //  if (col_is-i!=1)m[i]=m[i]+"\n";
    println(m[i]);
  }
  saveStrings("data/is.txt", m);
}
public void save_all(int in) {
  String[] m=new String[al.length+1];
  for (int i=0; i<al.length; i++) {
    m[i]=rgb(al[i]);
    //  if (col_is-i!=1)m[i]=m[i]+"\n";
    println(m[i]);
  }
  m[al.length]=rgb(in);
  println(m[al.length]);

  saveStrings("data/al.txt", m);
  al=give_all();
  is=new int[al.length];
  is=give_is();
  is=sort(is);
  buf=is[0];  
  all_color.setItems(loadStrings("list_217071"), 0);
  Add.setLocalColor(4, al[al.length-1]);
  buf=al[al.length-1];
}
public int[] sort(int o, int [] mas) {

  boolean isSorted = false;
  int buf;
  while (!isSorted) {
    isSorted = true;
    for (int i = 0; i < mas.length-1; i++) {
      if (hue(mas[i]) < hue(mas[i+1])) {
        isSorted = false;

        buf = mas[i];
        mas[i] = mas[i+1];
        mas[i+1] = buf;
      }
    }
  }
  return mas;
}
public PImage col(PImage in, int[]in_c) {
  colorMode(HSB, 255, 255, 255);
  in.loadPixels();
  for (int i=0; i<in.height; i++) {  
    for (int j=0; j<in.width; j++) {
      float buff=30;
      int rr=-1;
      for (int r=0; r<in_c.length; r++) {
        if (abs(hue(in_c[r])-hue(in.pixels[i*in.width+j]))<buff) {
          rr=r;
          buff=abs(hue(in_c[r])-hue(in.pixels[i*in.width+j]));
        }
      }
      if (rr==-1) {
        in.pixels[i*in.width+j]=color(255);
      } else {
        in.pixels[i*in.width+j]=in_c[rr];
      }
    }
  }

  in.updatePixels();
  colorMode(RGB, 255, 255, 255);

  return in;
}
public String rgb(int aa) {
  String r=Integer.toHexString((int)red(aa)).toUpperCase();
  if ((int)red(aa)<=15)  r="0"+r;
  String g=Integer.toHexString((int)green(aa)).toUpperCase();
  if ((int)green(aa)<=15)  g="0"+g;
  String b=Integer.toHexString((int)blue(aa)).toUpperCase();
  if ((int)blue(aa)<=15) b="0"+b;
  //  if (col_is-i!=1)m[i]=m[i]+"\n";
  // println("#"+r+g+b);
  return "#"+r+g+b;
}

/* =========================================================
 * ====                   WARNING                        ===
 * =========================================================
 * The code in this tab has been generated from the GUI form
 * designer and care should be taken when editing this file.
 * Only add/edit code inside the event handlers i.e. only
 * use lines between the matching comment tags. e.g.
 
 void myBtnEvents(GButton button) { //_CODE_:button1:12356:
 // It is safe to enter your event code here  
 } //_CODE_:button1:12356:
 
 * Do not rename this tab!
 * =========================================================
 */

public void button1_op(GButton source, GEvent event) { //_CODE_:op:778802:
  println("button1 - GButton >> GEvent." + event + " @ " + millis());
  selectInput("Select a file to process:", "fileSelected");
} //_CODE_:op:778802:

public void button2_sv(GButton source, GEvent event) { //_CODE_:sv:854504:
  println("button2 - GButton >> GEvent." + event + " @ " + millis());
  if (!im) {
    println("no imege");
  } else {
    rozr();
    img_=(col(roz(img), is));
    selectOutput("Select a file to write to:", "fileSelected_o");
  }


  //img.save();
} //_CODE_:sv:854504:

public void button3_wi(GButton source, GEvent event) { //_CODE_:wi:549412:
  println("button3 - GButton >> GEvent." + event + " @ " + millis()+ " @ " );
  if (!im) {
    println("no imege");
  } else {

    rozr();
    img_=sit(col(roz(img), is));

    //img_=sit(roz(img)); 
    //img_=(roz(img));
    println("h"+img_.height+"w"+img_.width);


    // window1.close();
    window1= GWindow.getWindow(this, "Window "+1, 10, 10, min(displayWidth-20, img_.width), min(displayHeight-100, img_.height), JAVA2D);
    window1.addData(new MyWinData()); 
    window1.addDrawHandler(this, "windowDraw");
    window1.addMouseHandler(this, "windowMouse");
  }
  // img_=null;
} //_CODE_:wi:549412:

public void textfield1_x(GTextField source, GEvent event) { //_CODE_:x:742375:
  //if (event=="CHANGED").. h_s=(textfield1.getSelectedText());
  println("textfield1 - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:x:742375:

public void textfield2_y(GTextField source, GEvent event) { //_CODE_:y:532651:
  // w=Integer.parseInt(textfield2.getSelectedText());
  println("textfield2 - GTextField >> GEvent." + event + " @ " + millis());
} //_CODE_:y:532651:

public void dropList1_click1(GDropList source, GEvent event) { //_CODE_:all_color:217071:
  println("dropList1 - GDropList >> GEvent." + event + " @ " + millis());
  println(source.getSelectedText()); 
  source.setOpaque(true);
  Add.setLocalColor(4, al[source.getSelectedIndex()]);
  // all_color.setLocalColor(1, al[source.getSelectedIndex()]);
  buf=al[source.getSelectedIndex()];
  col=true;
 // all_color.setFocus(true);
  //  button4.setLocalColorScheme(al[source.getSelectedIndex()]);
} //_CODE_:all_color:217071:

public void button4_Add(GButton source, GEvent event) { //_CODE_:Add:902335:
  println("button4 - GButton >> GEvent." + event + " @ " + millis());
  boolean is_f=true;
  for (int i=0; i<col_is; i++ ) {
    println(rgb(is[i])+"["+i+"]"+" =="+rgb(buf));
    if (is[i]==buf) {
      is_f=false;
      break;
    }
  }
  if (is_f) {
    is[col_is]=buf;
    col_is++;
    is=sort(is);
    col=true;
    save_is();
  }
} //_CODE_:Add:902335:

public void slider1_change1(GSlider source, GEvent event) { //_CODE_:slider1:641189:
  println("slider1 - GSlider >> GEvent." + event.toString() + " @ " + millis());
  if (im) { 
    rozr();  
    if (event.toString().charAt(0)=='R') {
      img_=sit(col(roz(img), is));
      window1.addData(new MyWinData());
    }
  }
} //_CODE_:slider1:641189:

public void button1_click1(GButton source, GEvent event) { //_CODE_:button1:499428:
  println("button1 - GButton >> GEvent." + event + " @ " + millis()); 
  if (!im) {
    println("no imege");
  } else {
    rozr();
    img_=sit(col(roz(img), is));
    selectOutput("Select a file to write to:", "fileSelected_o");
  }
} //_CODE_:button1:499428:

public void RGB_click1(GImageButton source, GEvent event) { //_CODE_:RGB_:979955:
  println("RGB - GImageButton >> GEvent." + event + " @ " + millis());

  save_all( G4P.selectColor());
} //_CODE_:RGB_:979955:

synchronized public void win_draw1(PApplet appc, GWinData data) { //_CODE_:window1:253753:
  appc.background(230);
} //_CODE_:window1:253753:



// Create all the GUI controls. 
// autogenerated do not edit
public void createGUI() {
  G4P.messagesEnabled(false);
  G4P.setGlobalColorScheme(GCScheme.BLUE_SCHEME);
  G4P.setMouseOverEnabled(false);
  surface.setTitle("Sketch Window");
  op = new GButton(this, 15, 15, 80, 30);
  op.setText("open");
  op.addEventHandler(this, "button1_op");
  sv = new GButton(this, 105, 15, 80, 30);
  sv.setText("save maket");
  sv.addEventHandler(this, "button2_sv");
  wi = new GButton(this, 285, 15, 80, 30);
  wi.setText("wiev");
  wi.addEventHandler(this, "button3_wi");
  x = new GTextField(this, 15, 60, 60, 20, G4P.SCROLLBARS_NONE);
  x.setPromptText("height");
  x.setOpaque(true);
  x.addEventHandler(this, "textfield1_x");
  y = new GTextField(this, 90, 60, 60, 20, G4P.SCROLLBARS_NONE);
  y.setPromptText("wieght");
  y.setOpaque(true);
  y.addEventHandler(this, "textfield2_y");
  all_color = new GDropList(this, 15, 90, 200, 220, 10, 10);
  all_color.setItems(loadStrings("list_217071"), 0);
  all_color.addEventHandler(this, "dropList1_click1");
  Add = new GButton(this, 219, 89, 80, 30);
  Add.setText("Add");
  Add.addEventHandler(this, "button4_Add");
  slider1 = new GSlider(this, 160, 50, 100, 35, 10.0f);
  slider1.setShowValue(true);
  slider1.setShowLimits(true);
  slider1.setLimits(1.0f, 1.0f, 10.0f);
  slider1.setNumberFormat(G4P.DECIMAL, 2);
  slider1.setOpaque(true);
  slider1.addEventHandler(this, "slider1_change1");
  label_xIn = new GLabel(this, 220, 255, 80, 20);
  label_xIn.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  label_xIn.setOpaque(true);
  label_yIn = new GLabel(this, 220, 290, 80, 20);
  label_yIn.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  label_yIn.setOpaque(true);
  label_xOut = new GLabel(this, 340, 255, 80, 20);
  label_xOut.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  label_xOut.setOpaque(true);
  label_yOut = new GLabel(this, 340, 290, 80, 20);
  label_yOut.setTextAlign(GAlign.CENTER, GAlign.MIDDLE);
  label_yOut.setOpaque(true);
  button1 = new GButton(this, 195, 15, 80, 30);
  button1.setText("save");
  button1.addEventHandler(this, "button1_click1");
  RGB_ = new GImageButton(this, 320, 60, 100, 100, new String[] { "pngegg.png", "pngegg.png", "pngegg.png" } );
  RGB_.addEventHandler(this, "RGB_click1");
 // window1 = GWindow.getWindow(this, "Window title", 0, 0, 240, 120, JAVA2D);
  //window1.noLoop();
 // window1.setActionOnClose(G4P.CLOSE_WINDOW);
  //window1.addDrawHandler(this, "win_draw1");
 // window1.loop();
}

// Variable declarations 
// autogenerated do not edit
GButton op; 
GButton sv; 
GButton wi; 
GTextField x; 
GTextField y; 
GDropList all_color; 
GButton Add; 
GSlider slider1; 
GLabel label_xIn; 
GLabel label_yIn; 
GLabel label_xOut; 
GLabel label_yOut; 
GButton button1; 
GImageButton RGB_; 
GWindow window1;
public void names() {
    op.setText("Відкрити");
  sv.setText("Збергти макет");
  wi.setText("Перегляд");
  x.setPromptText("Висота");
  y.setPromptText("Ширина");
  Add.setText("Додати");
  button1.setText("Зберегти");

  
}

public PImage roz(PImage in) {
  PImage res=createImage(w, h, RGB);
  in.loadPixels();
  res.loadPixels();
  float x_c=PApplet.parseFloat(in.height)/PApplet.parseFloat(h);
  float y_c=PApplet.parseFloat(in.width)/PApplet.parseFloat(w);
  int buff_r, buff_g, buff_b, i__;
  for (int i=0; i<h; i++) {  
    for (int j=0; j<w; j++) {
      buff_r=0;
      buff_g=0;
      buff_b=0;
      i__=0;
      for (int i_=PApplet.parseInt(i*x_c); i_<(i+1)*x_c; i_++) { 
        for (int j_=PApplet.parseInt(j*y_c); j_<(j+1)*y_c; j_++) {
          buff_r+=red(in.pixels[i_*in.width+j_]);
          buff_g+=green(in.pixels[i_*in.width+j_]);
          buff_b+=blue(in.pixels[i_*in.width+j_]);
          i__++;
        }
      }
      res.pixels[i*w+j]=color(buff_r/(i__), buff_g/(i__), buff_b/(i__));
    }
  }
  res.updatePixels();
  return res;
}
public void rozr() {

  try
  {  
    if (parseInt(x.getText())!=0) {
      h=parseInt(x.getText());
    } else {
      h=(int)(img.height/slider1.getValueF());
    };  
    if (parseInt(y.getText())!=0) {
      w=parseInt(y.getText());
    } else {
      w=(int)(img.width/slider1.getValueF());
    };

    label_xOut.setText(Integer.toString(h));
    label_yOut.setText(Integer.toString(w));
    col=true;
  } 
  catch (NumberFormatException ex) {
    ex.printStackTrace();
  }
}
public PImage sit(PImage in) {
  PImage out=createImage(in.width*pix, in.height*pix, RGB);
  println("h"+in.height+" w"+in.width);
  for (int i=0; i<in.width; i++) {
    for (int j=0; j<in.height; j++) {
      for (int q=1; q<pix; q++) {
        for (int d=1; d<pix; d++) {


          out.set(i*pix+q, j*pix+d, in.get(i, j));//кінець
        }
      }
    }
  }


  return out;
}

public void windowMouse(PApplet appc, GWinData data, MouseEvent event) {
  MyWinData data2 = (MyWinData)data;
  switch(event.getAction()) {
  case MouseEvent.PRESS:
    data2.sx = data2.ex = appc.mouseX;
    data2.sy = data2.ey = appc.mouseY;
    data2.done = false;
    break;
  case MouseEvent.RELEASE:
    data2.ex = appc.mouseX;
    data2.ey = appc.mouseY;
    data2.done = true;
    break;
  case MouseEvent.DRAG:
    data2.ex = appc.mouseX;
    data2.ey = appc.mouseY;
    break;
  }
}

/**
 * Handles drawing to the windows PApplet area
 * 
 * @param appc the PApplet object embeded into the frame
 * @param data the data for the GWindow being used
 */
public void windowDraw(PApplet appc, GWinData data) {
  MyWinData data2 = (MyWinData)data;



    appc.background(data2.col);
    appc.image(img_, 0, 0);
  
  
  if (true) {
    for (int i=0; i<=appc.width; i+=50)

    {

      if (i%50==0) {
        appc.stroke(0, 50, 100);
        appc.line(i, 0, i, appc.height);
      } else {
        appc.stroke(0, 0, 0);
        appc.line(i, 0, i, appc.height);
      }
    }
    for (int i=0; i<=appc.height; i+=50)
    {
      if (i%50==0) {
        appc.stroke(0, 50, 100);
        appc.line(0, i, appc.width, i);
      } else {
        appc.stroke(0, 0, 0);
        appc.line(0, i, appc.width, i);
      }
    }
  }



if(false)
  if (!(data2.sx == data2.ex && data2.ey == data2.ey)) {
    appc.stroke(255);
    appc.strokeWeight(2);
    appc.noFill();
    if (data2.done) {
      appc.fill(128);
    }
    appc.rectMode(CORNERS);
    appc.rect(data2.sx, data2.sy, data2.ex, data2.ey);
  }
}  

/**
 * Simple class that extends GWinData and holds the data 
 * that is specific to a particular window.
 * 
 * @author Peter Lager
 */
class MyWinData extends GWinData {
  int sx, sy, ex, ey;
  boolean done;
  int col;
}
  public void settings() {  size(480, 320, JAVA2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_220408b" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
