PImage roz(PImage in) {
  PImage res=createImage(w, h, RGB);
  in.loadPixels();
  res.loadPixels();
  float x_c=float(in.height)/float(h);
  float y_c=float(in.width)/float(w);
  int buff_r, buff_g, buff_b, i__;
  for (int i=0; i<h; i++) {  
    for (int j=0; j<w; j++) {
      buff_r=0;
      buff_g=0;
      buff_b=0;
      i__=0;
      for (int i_=int(i*x_c); i_<(i+1)*x_c; i_++) { 
        for (int j_=int(j*y_c); j_<(j+1)*y_c; j_++) {
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

void rozr() {

  try
  {  
    if (parseInt(x.getText())!=0 & parseInt(y.getText())==0) {
      h=parseInt(x.getText());
      w=(int)(img.width/(img.height/h));
    } else if (parseInt(x.getText())==0 & parseInt(y.getText())!=0) {
      w=parseInt(y.getText());
      h=(int)(img.height/(img.width/w));
    } else if (parseInt(x.getText())!=0 & parseInt(y.getText())!=0) {
      h=parseInt(x.getText());
      w=parseInt(y.getText());
    } else if (parseInt(x.getText())==0 & parseInt(y.getText())==0) {
      w=(int)(img.width/slider1.getValueF());
      h=(int)(img.height/slider1.getValueF());
    };  

    label_xOut.setText(Integer.toString(h));
    label_yOut.setText(Integer.toString(w));
  } 
  catch (NumberFormatException ex) {
    ex.printStackTrace();
  }
}

PImage sit(PImage in) {
  PImage out=createImage(in.width*pix, in.height*pix, RGB);
  println("h"+in.height+" w"+in.width);
  for (int i=0; i<in.width; i++) {
    for (int j=0; j<in.height; j++) {
      for (int q=1; q<pix; q++) {
        for (int d=1; d<pix; d++) {
          out.set(i*pix+q, j*pix+d, in.get(i, j));
        }
      }
    }
  }
  return out;
}
