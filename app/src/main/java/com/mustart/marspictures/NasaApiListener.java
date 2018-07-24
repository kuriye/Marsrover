package com.mustart.marspictures;

import java.util.ArrayList;

/**
 * Created by Bou's Laptop on 30/05/2018.
 */

public interface NasaApiListener     {
    public void onPictureAvailable(Picture picture);
    public void onPictureError(String error);

}
