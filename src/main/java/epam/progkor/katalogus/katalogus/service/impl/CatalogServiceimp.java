package epam.progkor.katalogus.katalogus.service.impl;

import epam.progkor.katalogus.katalogus.model.Catalog;
import epam.progkor.katalogus.katalogus.model.Genre;
import epam.progkor.katalogus.katalogus.model.Storage;
import epam.progkor.katalogus.katalogus.model.exceptions.NotFoundExceptations;
import epam.progkor.katalogus.katalogus.service.KatalogusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class CatalogServiceimp implements KatalogusService {

    private static final List<Catalog>Data_Base = new ArrayList<>();

    static {
        Data_Base.add(new Catalog(1L,"Individual Blue(Sunset)",800,Genre.Parfüm,Storage.igen));
        Data_Base.add(new Catalog(2L,"Avon senses (kir royale)",450,Genre.Tustfürdő,Storage.igen));
        Data_Base.add(new Catalog(3L,"Faraway",300,Genre.Illatositó,Storage.nem));
        Data_Base.add(new Catalog(4L,"Avon paris (160g)", 2700,Genre.Gyertya,Storage.igen));
    }

    @Override
    public List<Catalog> getAllCatalog() {
        return Collections.unmodifiableList(Data_Base);
    }

    @Override
    public Catalog getCatalog(final Long id) {
        return Data_Base.stream()
                .filter(catalog -> catalog.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundExceptations::new);
    }

    @Override
    public Catalog createCatalog(final Catalog catalog) {
        catalog.setId(getNextId());
        Data_Base.add(catalog);
        return catalog;

    }

    @Override
    public Catalog updateCatalog(final Long id,final Catalog catalogChange) {
        final Catalog catalog = getCatalog(id);
        catalog.setName(catalogChange.getName());
        catalog.setPrice(catalogChange.getPrice());
        catalog.setGenre(catalogChange.getGenre());
        catalog.setStorage(catalogChange.getStorage());

        return catalog;
    }


    @Override
    public void deleteCatalog(final Long id) {
        final Catalog catalog = getCatalog(id);
        Data_Base.remove(catalog);
    }

    private long getNextId(){
        return getLastId() + 1L;
    }

    private long getLastId(){
        return Data_Base.stream()
                .mapToLong(Catalog::getId)
                .max()
                .orElse(0);
    }

}
