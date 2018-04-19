package tech.grug.anyTech;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadUtil {
    public static void downloadImg(String url,String name,String path) throws Exception{
        File dirFile=new File(path);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }

        URL requestUrl=new URL(url);
        URLConnection connection=requestUrl.openConnection();
        InputStream in=connection.getInputStream();
        File img=new File(dirFile,name);
        img.createNewFile();
        FileOutputStream os=new FileOutputStream(path+"/"+name);
        byte[] buffer=new byte[4*1024];
        int read;
        while ((read = in.read(buffer)) > 0) {
            os.write(buffer, 0, read);
        }
        os.close();
        in.close();
}
}
