
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
