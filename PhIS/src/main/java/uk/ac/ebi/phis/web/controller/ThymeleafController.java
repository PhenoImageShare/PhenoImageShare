package uk.ac.ebi.phis.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.ac.ebi.phis.jaxb.Zygosity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilinca on 05/09/2016.
 */


@Controller
@RequestMapping("/tl")
public class ThymeleafController {



    @ModelAttribute("allTypes")
    public List<Zygosity> populateTypes() {

        List<Zygosity> zyg = new ArrayList<>();
        zyg.add(Zygosity.HEMIZYGOUS);
        zyg.add(Zygosity.HETEROZYGOUS);
        zyg.add(Zygosity.HOMOZYGOUS);
        zyg.add(Zygosity.UNSPECIFIED);

        return zyg;
    }


    @RequestMapping({"/thymeleaf"})
    public String showSeedstarters() {
        System.out.println("CALLED THYMELEAF");
        return "classpath:templates/thymeleaf_home";
    }

}
