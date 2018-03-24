package com.sell;

import org.junit.Test;

import java.io.File;

//修改文件夹中的文件名
public class ModifyFileName {
    private final String direction = "E:\\视频\\SpringBoot 微信点餐系统";
    public final String patterStr = "[更多IT教程 www.51zxba.com] ";

    @Test
    public void modifyFileName() {
        File file = new File(direction);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.getName().contains(patterStr)) {
                    f.renameTo(new File(direction + "\\" + f.getName().replace(patterStr, "")));
                }
            }
        }
    }
}
