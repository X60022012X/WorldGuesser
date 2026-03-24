package worldguesser;

import java.util.Map;

public class CountryData {
    
    public static final Map<String, String[]> idsByCountry = Map.ofEntries(
        Map.entry("Denmark", new String[] {"gl", "dk", "fo"}),
        Map.entry("Iceland", new String[] {"is"}),
        Map.entry("Norway", new String[] {"no"}),
        Map.entry("Sweden", new String[] {"se"}),
        Map.entry("Finland", new String[] {"fi"}),
        Map.entry("United Kingdom", new String[] {"gb-gbn", "path1207", "gb-nir", "im"}),
        Map.entry("Ireland", new String[] {"ie"}),
        Map.entry("Netherlands", new String[] {"path5848"}),
        Map.entry("Belgium", new String[] {"be"}),
        Map.entry("Czechia", new String[] {"cz"}),
        Map.entry("Estonia", new String[] {"ee"}),
        Map.entry("Latvia", new String[] {"lv"}),
        Map.entry("Lithuania", new String[] {"lt"}),
        Map.entry("Belarus", new String[] {"by"}),
        Map.entry("Russia", new String[] {"ru-main", "ru-kgd"}),
        Map.entry("Poland", new String[] {"pl"}),
        Map.entry("Ukraine", new String[] {"path3849"}),
        Map.entry("Moldova", new String[] {"path3950"}),
        Map.entry("Romania", new String[] {"ro"}),
        Map.entry("Bulgaria", new String[] {"bg"}),
        Map.entry("Serbia", new String[] {"rs", "xk"}),
        Map.entry("Hungary", new String[] {"hu"}),
        Map.entry("Slovakia", new String[] {"sk"}),
        Map.entry("Austria", new String[] {"at"}),
        Map.entry("Switzerland", new String[] {"ch"}),
        Map.entry("Italy", new String[] {"it"}),
        Map.entry("Slovenia", new String[] {"si"}),
        Map.entry("Croatia", new String[] {"hr"}),
        Map.entry("Bosnia and Herzegovina", new String[] {"ba"}),
        Map.entry("Montenegro", new String[] {"me"}),
        Map.entry("Albania", new String[] {"al"}),
        Map.entry("North Macedonia", new String[] {"mk"}),
        Map.entry("Greece", new String[] {"gr"}),
        Map.entry("Türkiye", new String[] {"tr"}),
        Map.entry("Spain", new String[] {"es"}),
        Map.entry("Portugal", new String[] {"pt"}),
        Map.entry("Cyprus", new String[] {"nc"}),
        Map.entry("Germany", new String[] {"path5816"}),
        Map.entry("France", new String[] {"fr"}),
        Map.entry("Luxembourg", new String[] {"path5742"})
        //Map.entry("Malta", new String[] {"mt"}),
        //Map.entry("Andorra", new String[] {"ad"}),
        //Map.entry("Liechtenstein", new String[] {"li"}),
        //Map.entry("San Marino", new String[] {"sm"}),
        //Map.entry("Vatican City", new String[] {"va"}),
        //Map.entry("Monaco", new String[] {"mc"})
    );
}