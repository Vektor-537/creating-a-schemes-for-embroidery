// Need G4P library
import g4p_controls.*;
// You can remove the PeasyCam import if you are not using
// the GViewPeasyCam control or the PeasyCam library.
import peasy.*;
int h=100, w=100;
PImage img; 
PImage img_; 
color [] al;
color [] is;
color buf;
int col_is=0;
boolean im=false, col=true, n_w_d=true; 
int pix=5;
int col_start=0;

public void setup() {
  size(480, 320, JAVA2D);
  // frameRate(30);
  al=give_all();
  is=new color[al.length];
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
void fileSelected(File selection) {
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

void fileSelected_o(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
    img_.save(selection.getAbsolutePath()+".png");
  }
}
void mouseClicked() {
  if ((mouseY>=0)&(mouseY<=10))if ((mouseX>=0)&(mouseX<=10*col_is)) {
    is[mouseX/10]=color(255, 255, 255);
    is=sort(is);
    col_is--;
    col=true;
    println(mouseX/10);
    save_is();
  }
}
void mouseWheel(MouseEvent event) {

  if ((mouseY>=0)&(mouseY<=10))if ((mouseX>=0)&(mouseX<=10*col_is)) {
    float e = event.getCount();
    col_start+=e;
    if (col_start<=0)col_start=0;
    if (col_start>=col_is)col_start=col_is;
    println(e);
    col=true;
  }
}