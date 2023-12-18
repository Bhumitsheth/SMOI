package com.iipl.smoi.Model;

import android.net.Uri;

public class SliderItem {

    private final int image;
    private final Uri video;

    public SliderItem(int image, Uri video) {
        this.image = image;
        this.video = video;
    }

    public int getImage() {
        return image;
    }

    public Uri getVideo() {
        return video;
    }


}
