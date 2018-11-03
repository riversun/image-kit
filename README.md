# Overview
"Image Kit" is a simple image manipulation library for java. 

It is licensed under [MIT](https://opensource.org/licenses/MIT).

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.riversun/image-kit/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.riversun/image-kit)

# How to use

## Show Image on window

```
  BufferedImage image = ImageUtil.loadImage(new File("c:/temp/image.png"));
  ImageViewer iv = new ImageViewer();
  iv.showImageOnWindow(image);
  
```

![image](https://riversun.github.io/img/lena.png)

