import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Administrator on 2017/9/3 0003.
 */
public class TestMain {

    class Film{
        private String name;
        private int amout;

        public Film(String name, int amout) {
            this.name = name;
            this.amout = amout;
        }

        public String getName() {
            return name;
        }

        public int getAmout() {
            return amout;
        }

        public void decreaseAmout(){
            amout--;
        }
    }

    class VideoStore{
        private Film[] inventory;

        public VideoStore(){
            inventory = new Film[4];
            inventory[0] = new Film("蜘蛛侠", 20);
            inventory[1] = new Film("我机器人", 20);
            inventory[2] = new Film("天下无贼", 20);
            inventory[3] = new Film("功夫", 20);
        }

        public void buyFilm(String filmName){
            boolean isExistFilmName = false;
            boolean hasFilmName = false;
            switch (filmName){
                case "蜘蛛侠":
                    if(inventory[0].getAmout()>=0){
                        hasFilmName = true;
                        inventory[0].decreaseAmout();
                    }
                    isExistFilmName = true;
                    break;
                case "我机器人":
                    if(inventory[1].getAmout()>=0){
                        hasFilmName = true;
                        inventory[1].decreaseAmout();
                    }
                    isExistFilmName = true;
                    break;
                case "天下无贼":
                    if(inventory[2].getAmout()>=0){
                        hasFilmName = true;
                        inventory[2].decreaseAmout();
                    }
                    isExistFilmName = true;
                    break;
                case "功夫":
                    if(inventory[3].getAmout()>=0){
                        hasFilmName = true;
                        inventory[3].decreaseAmout();
                    }
                    isExistFilmName = true;
                    break;
                default:
                    isExistFilmName = false;
            }
            if(isExistFilmName){
                System.out.println("本店没有此影碟");
                return;
            }

            if(!hasFilmName){
                System.out.println("此影碟已售完");
                return;
            }
        }


    }

    public static void main(String []args){
        Map<String, String> mCommonSweeperViewShowMap = new HashMap<>();
        mCommonSweeperViewShowMap.put("aaa1", "abc");
        mCommonSweeperViewShowMap.put("aaa2", "222");
        mCommonSweeperViewShowMap.put("aaa3", "333");
        mCommonSweeperViewShowMap.put("aaa4", "444");
        mCommonSweeperViewShowMap.put("aaa5", "555");
        mCommonSweeperViewShowMap.put("aaa6", "666");
        System.out.println(mCommonSweeperViewShowMap.toString());
        Iterator iter = mCommonSweeperViewShowMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            mCommonSweeperViewShowMap.put(key, null);
        }
        System.out.println(mCommonSweeperViewShowMap.toString());
        mCommonSweeperViewShowMap.remove("aaa6");
        System.out.println(mCommonSweeperViewShowMap.toString());

        iter = mCommonSweeperViewShowMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key="+key+"  value="+value);
        }

    }
}