package epam.progkor.katalogus.katalogus.service;

import epam.progkor.katalogus.katalogus.model.Catalog;
import java.util.List;

public interface KatalogusService {

  List<Catalog> getAllCatalog();

  Catalog getCatalog(Long id);

  Catalog createCatalog(Catalog catalog);

  Catalog updateCatalog(Long id, Catalog catalogChange);

  void deleteCatalog(Long id);

}
