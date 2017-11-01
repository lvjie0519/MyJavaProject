package design.patterns;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public class LSP {

    public class Window{
        public void show(View child){
            child.draw();
        }
    }

    public abstract class View{
        public abstract void draw();
    }

    public class Button extends View{

        @Override
        public void draw() {

        }
    }

    public class TextView extends View{

        @Override
        public void draw() {

        }
    }

}
