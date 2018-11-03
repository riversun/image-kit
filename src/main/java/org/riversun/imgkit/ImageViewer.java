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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * ウィンドウに指定した画像(bufferedImage)を表示する
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class ImageViewer {

    @SuppressWarnings("serial")
    public void showImageOnWindow(BufferedImage mImage) {

        final JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final int winWidth = mImage.getWidth();
        final int winHeight = mImage.getHeight();

        window.getContentPane().add(new JComponent() {

            public void paint(Graphics g) {

                final Graphics2D g2 = (Graphics2D) g;

                // 画像を描画
                g2.drawImage(mImage, 0, 0, null);
                g2.finalize();

                // ウィンドウサイズ内にイメージがぴったり入るよう調整する
                window.getContentPane().setPreferredSize(new Dimension(winWidth, winHeight));
                window.pack();
            }
        });

        window.setSize(winWidth, winHeight);
        window.setVisible(true);

    }

}