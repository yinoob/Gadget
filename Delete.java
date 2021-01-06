import java.io.File;
import java.util.logging.Logger;

public class Delete {

    public static void func(File file){
        Logger log = Logger.getLogger("test.log");
        File[] fs=file.listFiles();
        for(File f:fs){
            if(f.isDirectory()){
                func(f);
            }else if(f.isFile()){
                if(f.getName().endsWith("mp3")||f.getName().endsWith("m4a")){
                    log.info("已删除"+file.getName());
                    f.delete();
                }


            }
        }
    }

    public static void main(String[] args){
        String filePath="F:\\tianyi\\download";
        File file=new File(filePath);
        func(file);
    }

}
