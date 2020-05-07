package limitless.android.androiddevelopment.Model;

import java.util.ArrayList;
import java.util.List;

public class Section {

    public String name;
    public int image;
    public boolean hasNew;
    public boolean showData;
    public List<Inner> innerList;
    public Class<?> aClass;
    public boolean hasData;

    public Section(){
        this.name = "";
        this.image = -1;
        this.hasNew = false;
        this.showData = false;
        this.innerList = new ArrayList<>();
        this.aClass = null;
        this.hasData = false;
    }

    public Section(String name, int image, boolean hasNew, boolean showData, List<Inner> innerList, Class<?> aClass, boolean hasData){
        this.name = name;
        this.image = image;
        this.hasNew = hasNew;
        this.showData = showData;
        this.innerList = innerList;
        this.aClass = aClass;
        this.hasData = hasData;
    }

    public static class Inner {
        public String name;
        public boolean isNew;
        public Class<?> aClass1;

        public Inner(){
            this.name = "";
            this.isNew = false;
            this.aClass1 = null;
        }

        public Inner(String name, boolean isNew, Class<?> aClass1){
            this.name = name;
            this.isNew = isNew;
            this.aClass1 = aClass1;
        }

    }

}
