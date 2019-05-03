/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Adan
 */
public enum tipoRegistro {
    SEMANAL(1),
    QUINCENAL(2),
    MENSUAL(3);
    
    private final int id;
    //private static final Map<Integer, tipoRegistro> MAP = new HashMap<>();

    /**
     * @return the tipoID
     */
    private tipoRegistro(int id){
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public static tipoRegistro registro(int n) {
        switch (n) {
            case 1:
                return tipoRegistro.SEMANAL;
            case 2:
                return tipoRegistro.QUINCENAL;
            case 3:
                return tipoRegistro.MENSUAL;
            default:
                return null;
        }
    }
    
    /*public static tipoRegistro fromId(int id){
        return MAP.get(id);
    }
    static{
        for(tipoRegistro n: values()){
            MAP.put(n.getId(), n);
        }
    }*/
}
