/*
 * ImageKit
 * 
 * Copyright 2010-2018 Tom Misawa, riversun.org@gmail.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of 
 * this software and associated documentation files (the "Software"), to deal in the 
 * Software without restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the 
 * Software, and to permit persons to whom the Software is furnished to do so, 
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all 
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 *  INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package org.riversun.imgkit;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * 各種Graphic2D描画ユーティリティ
 * 
 * 透明塗りつぶし、BufferedImageのクローン生成、BufferedImageの回転
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class ImageUtil {
    private ImageUtil() {

    }

    /**
     * 指定した矩形領域を透明色にする
     * 
     * @param g
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static void fillRectByTransparent(Graphics2D g, int x, int y, int width, int height) {

        final Composite composite = g.getComposite();
        final AlphaComposite alphaComposit = AlphaComposite.getInstance(AlphaComposite.SRC_OUT, 0);
        g.setComposite(alphaComposit);
        g.fillRect(x, y, width, height);
        g.setComposite(composite);
    }

    /**
     * 指定したShapeが示す領域を透明色にする
     * 
     * @param g
     * @param shape
     */
    public static void fillShapeByTransparent(Graphics2D g, Shape shape, boolean eraseBorder) {
        final Composite composite = g.getComposite();
        final AlphaComposite alphaComposit = AlphaComposite.getInstance(AlphaComposite.SRC_OUT, 0);
        g.setComposite(alphaComposit);
        if (eraseBorder) {
            // make sure erase border
            g.draw(shape);
        }
        g.fill(shape);
        g.setComposite(composite);
    }

    /**
     * BufferedImageのディープコピーを作成する ディープコピーなので元の画像データを変更しても、本メソッドによるコピー結果には反映されない
     * 
     * @param srcImage
     * @return
     */
    public static BufferedImage deepCopyImage(BufferedImage srcImage) {

        final BufferedImage outImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getType());

        final Graphics2D g = outImage.createGraphics();
        g.drawImage(srcImage, 0, 0, null);
        g.dispose();

        return outImage;
    }

    /**
     * 指定したimageを回転したimageを生成する
     * 
     * @param image
     * @param angleDeg
     * @return
     */
    public static BufferedImage rotateImage(BufferedImage image, int angleDeg) {

        final int height = image.getHeight();
        final int width = image.getWidth();

        final BufferedImage targetImage = new BufferedImage(height, width, image.getType());
        final Graphics2D g2d = targetImage.createGraphics();

        final AffineTransform transform = new AffineTransform();

        transform.translate(height / 2 - width / 2, width / 2 - height / 2);
        transform.rotate(Math.toRadians(angleDeg), width / 2, height / 2);

        g2d.setTransform(transform);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return targetImage;
    }

    /**
     * 画像を読み込む
     * 
     * @param file
     * @return
     */
    public static BufferedImage loadImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(file + " failed to load." + e.getMessage());
        }

    }
}
