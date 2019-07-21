import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;
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

    }
}