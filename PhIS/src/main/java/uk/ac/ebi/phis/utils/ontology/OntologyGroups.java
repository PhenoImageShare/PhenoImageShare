package uk.ac.ebi.phis.utils.ontology;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Created by ilinca on 02/09/2016.
 */
public class OntologyGroups {

    Map<Species, Set<String>> speciesMap;
    Map<Subjects, Set<String>> subjectMap;

    public OntologyGroups(){

        speciesMap = new HashMap<>();
        speciesMap.put(Species.MUS_MUSCULUS, new HashSet<String>());
        speciesMap.get(Species.MUS_MUSCULUS).add("ma");
        speciesMap.get(Species.MUS_MUSCULUS).add("emap");
        speciesMap.get(Species.MUS_MUSCULUS).add("emapa");
        speciesMap.get(Species.MUS_MUSCULUS).add("mp");
        speciesMap.get(Species.MUS_MUSCULUS).add("cmpo");
        speciesMap.get(Species.MUS_MUSCULUS).add("mpath");

        speciesMap.put(Species.DROSOPHILA_MELANOGASTER, new HashSet<String>());
        speciesMap.get(Species.DROSOPHILA_MELANOGASTER).add("fbbt");
        speciesMap.get(Species.DROSOPHILA_MELANOGASTER).add("cmpo");

        speciesMap.put(Species.HOMO_SAPIENS, new HashSet<String>());
        speciesMap.get(Species.HOMO_SAPIENS).add("fma");
        speciesMap.get(Species.HOMO_SAPIENS).add("uberon");
        speciesMap.get(Species.HOMO_SAPIENS).add("hp");
        speciesMap.get(Species.HOMO_SAPIENS).add("cmpo");


        subjectMap = new HashMap<>();
        subjectMap.put(Subjects.ANATOMY, new HashSet<String>());
        subjectMap.get(Subjects.ANATOMY).add("ma");
        subjectMap.get(Subjects.ANATOMY).add("emap");
        subjectMap.get(Subjects.ANATOMY).add("emapa");
        subjectMap.get(Subjects.ANATOMY).add("fbbt");
        subjectMap.get(Subjects.ANATOMY).add("fma");
        subjectMap.get(Subjects.ANATOMY).add("uberon");

        subjectMap.put(Subjects.PHENOTYPE, new HashSet<String>());
        subjectMap.get(Subjects.PHENOTYPE).add("mp");
        subjectMap.get(Subjects.PHENOTYPE).add("cmpo");
        subjectMap.get(Subjects.PHENOTYPE).add("mpath");
        subjectMap.get(Subjects.PHENOTYPE).add("hp");

    }


    public enum Species{
        MUS_MUSCULUS, DROSOPHILA_MELANOGASTER, HOMO_SAPIENS
    }


    public enum Subjects{
        PHENOTYPE, ANATOMY
    }


    public Set<String> getDefaultOntologies( Species species, Subjects subjects){

        Set<String> res = null;

        if ( species != null){
            res = speciesMap.get(species);
        }
        if (subjects != null){
            if (res == null){
                res = subjectMap.get(subjects);
            } else {
                res.retainAll(subjectMap.get(subjects));
            }
        }

        return res;

    }


}
