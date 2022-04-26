package models;

import lib.CDDLib;

public class BDDArrays {

    private long pointer;
    int numTraces;
    int numBools;
    private int[][] vars;
    private int[][] values;

    public BDDArrays(long pointer) {
        this.pointer = pointer;
        numTraces= importNumTraces();
        numBools = importNumBools();
        vars = importVars();
        values = importValues();
    }

    public int[][] getVars() {
        return vars;
    }

    public int[][] getValues() {
        return values;
    }

    public int importNumTraces() {
           return CDDLib.getNumTracesFromBDDArray(pointer);
    }

    public int importNumBools() {
        return CDDLib.getNumBoolsFromBDDArray(pointer);
    }

    public int[][] importVars() {
        checkForNull();
        if(vars != null){
            return vars;
        }else{

            CDDLib.getVarsFromBDDArray(pointer);
            return null;//new CDD(CDDLib.getVarsFromBDDArray(pointer));
        }
    }

    public int[][] importValues() {
        checkForNull();

        if(values != null){
            return values;
        }else{
            //CDDLib.getValuesFromBDDArray(pointer);
            return null;//(CDDLib.getValuesFromBDDArray(pointer));
        }
    }

    private void checkForNull(){
        if(pointer == 0){
            throw new NullPointerException("BDD arrays result is null");
        }
    }
}
