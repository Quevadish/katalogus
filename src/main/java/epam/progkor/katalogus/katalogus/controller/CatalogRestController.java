package epam.progkor.katalogus.katalogus.controller;

import epam.progkor.katalogus.katalogus.model.Catalog;
import epam.progkor.katalogus.katalogus.service.KatalogusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cata-log")
public class CatalogRestController {

    private final KatalogusService katalogusService;

    public CatalogRestController(final KatalogusService katalogusService) {
        this.katalogusService = katalogusService;
    }

    @GetMapping
    public List<Catalog> getAllCatalog() {
        return katalogusService.getAllCatalog();

    }

    @GetMapping("/{id}")
    Catalog getCatalog(final @PathVariable("id") Long id) {
        return katalogusService.getCatalog(id);

    }

    @PostMapping
    Catalog createCatalog(final @RequestBody Catalog catalog){
        return katalogusService.createCatalog(catalog);

    }

    @PutMapping("/{id}")
    Catalog updateCatalog(final @PathVariable Long id,final @RequestBody Catalog catalogChange){
        return katalogusService.updateCatalog(id, catalogChange);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCatalog(final @PathVariable Long id) {
        katalogusService.deleteCatalog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
