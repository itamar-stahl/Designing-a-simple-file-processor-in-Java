package sorting;

import java.io.File;
import java.util.Comparator;


/**
 * Comparision rule: File type (when the type is equal: absolute name)
 */
public class TypeComparator implements Comparator<File> {
    @Override
    public int compare(File f1, File f2) {
        String typeF1 = getType(f1.getName());
        String typeF2 = getType(f2.getName());
        return (!typeF1.equals(typeF2)) ? typeCompare(typeF1, typeF2) :
                f1.getName().compareTo(f2.getName());

    }

    private int typeCompare(String typeF1, String typeF2){
        return typeF1.compareTo(typeF2);
    }

    private String getType(String filename){

        if (filename.startsWith(".")){
            filename = filename.replaceFirst("\\.",""); //ignore . of hidden files
        }
        String[] splited = filename.split("\\.");
        if (splited.length == 1) return "";
        return splited[splited.length-1];
    }
}
