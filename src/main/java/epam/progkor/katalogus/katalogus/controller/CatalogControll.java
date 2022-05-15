package epam.progkor.katalogus.katalogus.controller;


import epam.progkor.katalogus.katalogus.model.Catalog;
import epam.progkor.katalogus.katalogus.model.exceptions.NotFoundExceptations;
import epam.progkor.katalogus.katalogus.service.KatalogusService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/cata-log")
public class CatalogControll {

    private final KatalogusService katalogusService;

    public CatalogControll(final KatalogusService katalogusService) {
        this.katalogusService  = katalogusService;
    }

    @GetMapping
    public String getAllCatalog(final Model model) {
        final List<Catalog> catalogs = katalogusService.getAllCatalog();
        model.addAttribute("catalogs",catalogs);
        return "catalog/list";
    }

    @GetMapping("/{id}")
    public String getCatalog(final Model model, final @PathVariable Long id) {
        final Catalog catalog = katalogusService.getCatalog(id);
        model.addAttribute("catalog",catalog);
        return "catalog/edit";

}

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createCatalog(final Model model,
                                final @RequestParam(value = "id", required = false) Long id,
                                final Catalog catalogChanges){
        final Catalog catalog = katalogusService.updateCatalog(id, catalogChanges);
        model.addAttribute("catalog",catalog);
        return "catalog/edit";
    }

    @GetMapping("/create")
    public String createCatalogForm(final Model model){
        return "catalog/create";
    }

    @PostMapping("/create")
    public String createCatalog(final Model model, final Catalog catalog){
       final Catalog savedcatalog = katalogusService.createCatalog(catalog);
        model.addAttribute("catalog",savedcatalog);
        return "catalog/edit";
    }


    @GetMapping("/{id}/delete")
    public String deleteCatalog(final Model model,final @PathVariable("id") Long id){
        try {
        katalogusService.deleteCatalog(id);
    }catch (NotFoundExceptations e) {
            //Ignored
        }

        final List<Catalog> catalogs = katalogusService.getAllCatalog();
        model.addAttribute("catalogs",catalogs);
        return "catalog/list";

    }
}
