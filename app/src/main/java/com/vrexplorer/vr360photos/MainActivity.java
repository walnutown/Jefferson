package com.vrexplorer.vr360photos;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.vrexplorer.rajawalivr.RajawaliVRActivity;
import com.vrexplorer.rajawalivr.RajawaliVRRenderer;

import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;


public class MainActivity extends RajawaliVRActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setRenderer(new VR360PhotosRenderer(this));

        setConvertTapIntoTrigger(true);
    }

    private class VR360PhotosRenderer extends RajawaliVRRenderer {

        public VR360PhotosRenderer(Context context) {
            super(context);
        }

        @Override
        public void initScene() {
            Sphere sphere = createPhotoSphereWithTexture(new Texture("photo", R.drawable.panorama));
            getCurrentScene().addChild(sphere);

            getCurrentCamera().setPosition(Vector3.ZERO);
            getCurrentCamera().setFieldOfView(75);

            super.initScene();
        }

        private Sphere createPhotoSphereWithTexture(ATexture texture) {
            Material material = new Material();
            material.setColor(0);

            try {
                material.addTexture(texture);
            } catch (ATexture.TextureException e) {
                throw new RuntimeException(e);
            }

            Sphere sphere = new Sphere(50, 64, 32);
            sphere.setScaleX(-1);
            sphere.setMaterial(material);

            return sphere;
        }

    }

}
