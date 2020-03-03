package com.example.gto.m2techgateway.sub_Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.gto.m2techgateway.R;

import java.io.InputStream;
import java.io.OutputStream;

public class Most_Video_Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.most_video);

        //비디오 뷰의 생성(1)
        VideoView videoView = (VideoView)this.findViewById(R.id.videoView);
        videoView.requestFocus();
        videoView.setMediaController(new MediaController(this));

        try{
            /*  case1  */
            //videoView.setVideoPath("/sdcard/be_my_baby5.mp4");

            /*  case2  */
            //videoView.setVideoURI(
            //		Uri.parse("http://192.168.200.105:8080/web/be_my_baby5.mp4"));

            /* case 3 */
            //Raw 자원의 파일 저장(2)
            raw2file(this,R.raw.sample_animation_1280x720,"sample_animation_1280x720.mp4");

            //동영ㅇ상의 재생 (3)
            String path = getFilesDir().getAbsolutePath()+"/sample_animation_1280x720.mp4";
            videoView.setVideoPath(path);
            videoView.start();

        }catch(Exception e){
            android.util.Log.e("", e.toString());
        }
    }
    // Raw 자원의 파일 보존
    private void raw2file(Context context,
                          int resID, String fileName) throws Exception {
        InputStream in=context.getResources().openRawResource(resID);
        in2file(context,in,fileName);
    }

    // 입력 스트림의 파일 보존
    private void in2file(Context context,
                         InputStream in,String fileName)
            throws Exception {
        int size;
        byte[] w=new byte[1024];
        OutputStream out=null;
        try {
            out=context.openFileOutput(fileName,Context.MODE_WORLD_READABLE);
            while (true) {
                size=in.read(w);
                if (size<=0) break;
                out.write(w,0,size);
            };
            out.close();
            in.close();
        } catch (Exception e) {
            try {
                if (in !=null) in.close();
                if (out!=null) out.close();
            } catch (Exception e2) {
            }
            throw e;
        }
    }


}
