package mbeans;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MBMaterias {

    private LinkedHashMap <String, String[]> mapaMaterias;
    
    
    public MBMaterias() {
        mapaMaterias = new LinkedHashMap<String, String[]>();
        
        mapaMaterias.put("Desarollo Web", new String[]{"LEMA", "PROG", "BBDD"});
        mapaMaterias.put("Peluqueria", new String[]{"Alisado", "Moños", "Coletas"});
        mapaMaterias.put("Marketing", new String[]{"Contabilidad", "Ventas", "Almacenaje"});
    }

    
    public LinkedHashMap<String, String[]> getMapaMaterias() {
        return mapaMaterias;
    }

    public void setMapaMaterias(LinkedHashMap<String, String[]> mapaMaterias) {
        this.mapaMaterias = mapaMaterias;
    }
    
    
    
//METODOS    
    public ArrayList<Entry> materiasAsArray(){
        
        return  new ArrayList(mapaMaterias.entrySet());
    }
    
    
    
}
