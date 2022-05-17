package epam.progkor.katalogus.katalogus.controller;


import epam.progkor.katalogus.katalogus.model.Catalog;
import epam.progkor.katalogus.katalogus.model.exceptions.NotFoundExceptations;
import epam.progkor.katalogus.katalogus.service.KatalogusService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/cata-log")
public class CatalogControll {

  private final KatalogusService katalogusService;

  public CatalogControll(final KatalogusService katalogusService) {
    this.katalogusService = katalogusService;
  }

  /**
   * maga a termékek.
   *
   * @param model model.
   * @return return.
   */
  @GetMapping
  public String getAllCatalog(final Model model) {
    final List<Catalog> catalogs = katalogusService.getAllCatalog();
    model.addAttribute("catalogs", catalogs);
    return "catalog/list";
  }

  /**
   * termékek modositása.
   *
   * @param model model.
   * @param id    id.
   * @return return.
   */
  @GetMapping("/{id}")
  public String getCatalog(final Model model, final @PathVariable Long id) {
    final Catalog catalog = katalogusService.getCatalog(id);
    model.addAttribute("catalog", catalog);
    return "catalog/edit";

  }

  /**
   * frissités.
   *
   * @param model          model.
   * @param id             id.
   * @param catalogChanges áruváltozások.
   * @return return.
   */
  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String createCatalog(final Model model,
                              final @RequestParam(value = "id", required = false) Long id,
                              final Catalog catalogChanges) {
    final Catalog catalog = katalogusService.updateCatalog(id, catalogChanges);
    model.addAttribute("catalog", catalog);
    return "catalog/edit";
  }

  /**
   * létrehozás.
   *
   * @param model   model.
   * @param catalog katalogus.
   * @return return.
   */
  @PostMapping("/create")
  public String createCatalog(final Model model, final Catalog catalog) {
    final Catalog savedcatalog = katalogusService.createCatalog(catalog);
    model.addAttribute("catalog", savedcatalog);
    return "catalog/edit";
  }

  @GetMapping("/create")
  public String createCatalogForm(final Model model) {
    return "catalog/create";
  }

  /**
   * törlés.
   *
   * @param model model.
   * @param id    id.
   * @return return.
   */
  @GetMapping("/{id}/delete")
  public String deleteCatalog(final Model model, final @PathVariable("id") Long id) {
    try {
      katalogusService.deleteCatalog(id);
    } catch (NotFoundExceptations e) {
      //Ignored
    }

    final List<Catalog> catalogs = katalogusService.getAllCatalog();
    model.addAttribute("catalogs", catalogs);
    return "catalog/list";

  }
}
