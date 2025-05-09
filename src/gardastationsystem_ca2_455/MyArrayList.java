package gardastationsystem_ca2_455;

/**
 *
 * @author kamil
 */

import java.util.*;

public class MyArrayList<ElemType> extends ArrayList<ElemType> {
    
    public void bubleSort(){        
        int i, j;        
        Comparable elemAtJ, elemAtJPlus;        
        for(i=0; i < size(); i++){            
            for(j=0; j < size() - 1 - i; j++){                
                elemAtJ = (Comparable) get(j);
                elemAtJPlus = (Comparable) get(j + 1);                
                if(elemAtJ.compareTo(elemAtJPlus) > 0){
                    swap(j, j+1);
                }
            }
        }
    }
    
    public void swap (int pos1, int pos2){
        ElemType objPos1 = get(pos1);
        ElemType objPos2 = get(pos2);
        remove(pos1);
        add(pos1, objPos2);
        remove(pos2);
        add(pos2, objPos1);
    }
    
    public void simpleBubbleSort() {
        int numberOfElements = size();
        boolean moreSwaps = true;
        
        
        while(moreSwaps == true){
            
            int x;
            moreSwaps = false;
            
            for(x = 0; x < size() - 1; x++){
                Comparable elemAtx = (Comparable) get(x);
                Comparable elemAtxPlus = (Comparable) get(x + 1);
                
                if(elemAtx.compareTo(elemAtxPlus) > 0){
                    
                    swap(x, x+1);
                    moreSwaps = true;
                }
            }    
        }
    }
    
    public void bubbleRecursiveSort() {
        
        bubbleRecursiveSortCall(size());
    }
    
    private void bubbleRecursiveSortCall(int n) {
        
        if (n == 1){
            return; //base case
        }
        for (int i = 0; i < n-1; i++) {
            Comparable elemAtI = (Comparable) get(i);
            Comparable elemAtIPlus = (Comparable) get(i + 1);
            
            if (elemAtI.compareTo(elemAtIPlus) > 0){
                swap(i, i+1);
            }
        }
        bubbleRecursiveSortCall(n-1);
    }
    
    int binarySearch_nonRecursive(ElemType key, int start, int end){
        boolean found;
        int middle = 0;
        found = false;
        
        while(start <= end && (found == false)){
            middle = (start + end)/2;
            if(((Comparable) get(middle)).compareTo((Comparable) key) == 0){
                found = true;
            }
            else if(((Comparable) get(middle)).compareTo((Comparable) key) < 0){
                start = middle + 1;
            }
            else{
                end = middle - 1;
            }
        }
        if(found == true)
            return middle;
        else
            return -1;
    } 
    int binarySearch_Recursive(ElemType key, int start, int end){
        int middle = 0;
        int result;
        middle = (start + end)/2;
        if (((Comparable) get(middle)).compareTo((Comparable) key) == 0){
            result = middle;
        }
        else if(start == end){
            result = -1;
        }
        else{
            if(((Comparable) get(middle)).compareTo((Comparable) key) > 0){
                result = binarySearch_Recursive(key, start, middle - 1);
            }
            else
                result = binarySearch_Recursive(key, middle + 1, end);
        }
        return result;
    }
}