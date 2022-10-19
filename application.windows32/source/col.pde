color[] give_all() {
  String[] lines = loadStrings("data/al.txt");
  String[] list=new String[lines.length] ;
  color []res=new color[lines.length];
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

color[] give_is() {
  col_is=0;
  String[] lines = loadStrings("data/is.txt");
  String[] list=new String[lines.length] ;
  color []res=new color[al.length];
  println("there are is " + lines.length + " colors");

  for (int i = 0; i < lines.length; i++) {
    //   println( (lines[i]));
    String []m  = match(lines[i], "^#([A-Fa-f0-9]{6})");
    //  println("Found '" + m[0] + "' inside a tag.");
    res[i]=color(
      Integer.valueOf(  m[0].substring( 1, 3 ), 16 ), 
      Integer.valueOf(  m[0].substring( 3, 5 ), 16 ), 
      Integer.valueOf(  m[0].substring( 5, 7 ), 16 ) );
    list[i]=" R="+red(res[i])+" G="+green(res[i])+" B="+blue(res[i]);
    //    println(res[i]+" R="+red(res[i])+" g="+green(res[i])+" b="+blue(res[i]));
    col_is++;
  }

  return res;
}

void save_is() {
  String[] m=new String[col_is];
  for (int i=0; i<col_is; i++) {
    m[i]=rgb(is[i]);
    println(m[i]);
  }
  saveStrings("data/is.txt", m);
}

void save_all(color in) {

  String[] m=new String[al.length+1];
  for (int i=0; i<al.length; i++) {
    m[i]=rgb(al[i]);
    println(m[i]);
  }

  m[al.length]=rgb(in);
  println(m[al.length]);
  saveStrings("data/al.txt", m);

  al=give_all();  
  al=sort(0, al);

  is=new color[al.length];
  is=give_is();
  is=sort(0, is);


  all_color.setItems(loadStrings("list_217071"), 0);
  // Add.setLocalColor(4, al[al.length-1]);
  // buf=al[al.length-1];
}

color[] sort(int o, color [] mas) {

  boolean isSorted = false;
  color buf;
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

PImage col(PImage in, color[]in_c) {
  colorMode(HSB, 255, 255, 255);
  in.loadPixels();
  for (int i=0; i<in.height; i++) {  
    for (int j=0; j<in.width; j++) {
      float buff=rad;
      int rr=-1;
      for (int r=0; r<in_c.length; r++) {
        // if (abs(hue(in_c[r])-hue(in.pixels[i*in.width+j]))<buff) {
        if (h(in_c[r], in.pixels[i*in.width+j])<buff) {

          rr=r;
          buff=h(in_c[r], in.pixels[i*in.width+j]);
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
String rgb(color aa) {
  String r=Integer.toHexString((int)red(aa)).toUpperCase();
  if ((int)red(aa)<=15)  r="0"+r;
  String g=Integer.toHexString((int)green(aa)).toUpperCase();
  if ((int)green(aa)<=15)  g="0"+g;
  String b=Integer.toHexString((int)blue(aa)).toUpperCase();
  if ((int)blue(aa)<=15) b="0"+b;
  return "#"+r+g+b;
}
int h(color in1, color in2) {
  return (int)abs(hue(in1)-hue(in2))*(1)+(int)abs(saturation(in1)-saturation(in2))*(1)+(int)abs(alpha(in1)-alpha(in2))*(1);
}
