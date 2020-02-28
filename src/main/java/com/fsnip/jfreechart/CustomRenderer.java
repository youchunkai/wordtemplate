package com.fsnip.jfreechart;

import org.jfree.chart.renderer.category.BarRenderer;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

/**
 * Desc:
 * Author:Kevin
 * Date:2020/2/28
 **/
public class CustomRenderer extends BarRenderer {
    private static final long serialVersionUID = 784630226449158436L;
    private Paint[] colors;
    //初始化柱子颜色
    private static String[] Corlors = {"#00E400","#FFFF00","#FF7E00","#FF0000","#99004C","#7E0023"};

    public CustomRenderer() {
        colors = new Paint[Corlors.length];
        for (int i = 0; i < Corlors.length; i++) {
            colors[i] = Color.decode(Corlors[i]);
        }
    }

    public CustomRenderer(List<HashMap<String, Object>> datas){

        colors = new Paint[datas.size()];
        int i = 0;
        for (HashMap data : datas) {
            int value = Integer.valueOf(data.get("value").toString());
            String color = Corlors[getIndex(value)];
            System.out.println(color);
            colors[i] = Color.decode(color);
            i++;
        }
    }

    //重写该方法 让每次获取的paint 都不一样
    public Paint getItemPaint(int i, int j) {
        return colors[j%colors.length];
    }


    private static int getIndex(int value) {
        int i = 1;
        if(value <= 50){
            i = 1;
        }else if(value <= 100){
            i = 2;
        }else if(value <= 150){
            i = 3;
        }else if(value <= 200){
            i = 4;
        }else if(value <= 300){
            i = 5;
        }else if(value > 300){
            i = 6;
        }

        return i-1;
    }
}
