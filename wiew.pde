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
}  

class MyWinData extends GWinData {
  int sx, sy, ex, ey;
  boolean done;
  int col;
}
