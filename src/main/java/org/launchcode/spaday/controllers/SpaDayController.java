package org.launchcode.spaday.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@Controller
public class SpaDayController {

    public boolean checkSkinType(String skinType, String facialType) {
        if (skinType.equals("oily")) {
            return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating");
        }
        else if (skinType.equals("combination")) {
            return facialType.equals("Microdermabrasion") || facialType.equals("Rejuvenating") || facialType.equals("Enzyme Peel");
        }
        else if (skinType.equals("dry")) {
            return facialType.equals("Rejuvenating") || facialType.equals("Hydrofacial");
        }
        else {
            return true;
        }
    }

    @GetMapping(value="")
    public String customerForm () {
        return "form";
    }

    @PostMapping("profile")
    public String spaMenu(@RequestParam String name, @RequestParam String skintype, @RequestParam String manipedi, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("skintype", skintype);
        model.addAttribute("manipedi", manipedi);

        ArrayList<String> facials = new ArrayList<>();
        facials.add("Microdermabrasion");
        facials.add("Hydrofacial");
        facials.add("Rejuvenating");
        facials.add("Enzyme Peel");

        ArrayList<String> appropriateFacials = new ArrayList<>();
        for (int i = 0; i < facials.size(); i ++) {
            if (checkSkinType(skintype,facials.get(i))) {
                appropriateFacials.add(facials.get(i));
            }
        }

        model.addAttribute("appropriateFacials", appropriateFacials);

        ArrayList<String> polishColors = new ArrayList<>();
        polishColors.add("magenta");
        polishColors.add("red");
        polishColors.add("blue");
        polishColors.add("lavender");
        polishColors.add("darkgreen");

        model.addAttribute("polishColors", polishColors);

        return "menu";
    }
}
