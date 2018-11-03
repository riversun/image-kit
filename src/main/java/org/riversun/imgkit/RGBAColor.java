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

import java.awt.Color;

/**
 * 
 * HTML Color(#FF00FFなど)やアルファ付RGB指定をjava.awt.Colorに変換する
 * 
 * @author Tom Misawa (riversun.org@gmail.com)
 *
 */
public class RGBAColor {

    private final Color mColor;

    public RGBAColor(int r, int g, int b, float alpha) {
        final int a = (int) (255f * alpha);
        mColor = new Color(r, g, b, a);
    }

    public RGBAColor(Color color) {
        mColor = color;
    }

    public RGBAColor(int r, int g, int b) {
        this(r, g, b, 1.0f);
    }

    public RGBAColor(String RRGGBB, float alpha) {
        final Color c = Color.decode(RRGGBB);
        mColor = new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (255f * alpha));
    }

    public RGBAColor(String RRGGBB) {
        this(RRGGBB, 1.0f);
    }

    public boolean isTransparent() {
        return (mColor.getAlpha() == 0);
    }

    public boolean isNotTransparent() {
        return !isTransparent();
    }

    public Color getColor() {
        return mColor;
    }
}
