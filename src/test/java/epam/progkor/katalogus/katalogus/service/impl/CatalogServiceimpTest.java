package epam.progkor.katalogus.katalogus.service.impl;

import epam.progkor.katalogus.katalogus.model.Catalog;
import epam.progkor.katalogus.katalogus.model.Genre;
import epam.progkor.katalogus.katalogus.model.Storage;
import epam.progkor.katalogus.katalogus.model.exceptions.NotFoundExceptations;
import epam.progkor.katalogus.katalogus.service.KatalogusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CatalogServiceimpTest {

  public static final long UNKNOWN_CATA_LOG_ID = -1L;
  public static final String AXE_CATA_LOG_NAME = "Axe";

  private static final Catalog INDIVIDUAL_BLUE_CATA_LOG = new Catalog(1L, "Individual Blue(Sunset)", 800, Genre.Parfüm, Storage.igen);
  private static final Catalog AVON_SENSES_CATA_LOG = new Catalog(2L, "Avon senses (kir royale)", 450, Genre.Tustfürdő, Storage.igen);
  private static final Catalog FARAWAY_CATA_LOG = new Catalog(3L, "Faraway", 300, Genre.Illatositó, Storage.nem);
  private static final Catalog AVON_PARIS_CATA_LOG = new Catalog(4L, "Avon paris (160g)", 2700, Genre.Gyertya, Storage.igen);
  private static final List<Catalog> CATA_LOGS = List.of(
          INDIVIDUAL_BLUE_CATA_LOG,
          AVON_SENSES_CATA_LOG,
          FARAWAY_CATA_LOG,
          AVON_PARIS_CATA_LOG
  );
  private KatalogusService underTest;

  @BeforeEach
  void setUp() {
    underTest = new CatalogServiceimp(CATA_LOGS);

  }

  /*@Test
  void getAllCatalogShouldReturnAllCatalog() {
// when
    final List<Catalog> actual = underTest.getAllCatalog();
    // then
    assertThat(actual).isEqualTo(CATA_LOGS);
  }

  @Test
  void getCatalogShouldReturnCatalogWhenGivenIdOfExistingCatalog() {
// when
    final Catalog actual = underTest.getCatalog(AVON_PARIS_CATA_LOG.getId());
    // then
    assertThat(actual).isEqualTo(AVON_PARIS_CATA_LOG);
  }
*/
  @Test
  void getCatalogShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingCatalog() {
    // when then
    assertThrows(NotFoundExceptations.class, () -> underTest.getCatalog(UNKNOWN_CATA_LOG_ID));
  }

  @Test
  void createCatalogShouldReturnCatalogWhenDelegateIt() {
// given
    final Catalog axeCatalog = new Catalog(null, AXE_CATA_LOG_NAME, 500, Genre.Parfüm, Storage.igen);
    final Catalog expectedAxeCatalog = new Catalog(5L, AXE_CATA_LOG_NAME, 500, Genre.Parfüm, Storage.igen);
// when
    final Catalog actual = underTest.createCatalog(axeCatalog);
    // then
    assertThat(actual).isEqualTo(expectedAxeCatalog);
  }

  @Test
  void updateCatalogShouldReturnUpdatedCatalogWhenGivenIdOfExistingCatalog() {
// given
    final Catalog axeCatalog = new Catalog(null, AXE_CATA_LOG_NAME, 500, Genre.Parfüm, Storage.igen);
    final Catalog expectedCatalog = new Catalog(AVON_PARIS_CATA_LOG.getId(), AXE_CATA_LOG_NAME, 500, Genre.Parfüm, Storage.igen);
// when
    final Catalog actual = underTest.updateCatalog(AVON_PARIS_CATA_LOG.getId(), axeCatalog);
    // then
    assertThat(actual).isEqualTo(expectedCatalog);
  }

  @Test
  void updateCatalogShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingCatalog() {
// given
    final Catalog axeCatalog = new Catalog(null, AXE_CATA_LOG_NAME, 500, Genre.Parfüm, Storage.igen);
    // when then
    assertThrows(NotFoundExceptations.class, () -> underTest.updateCatalog(UNKNOWN_CATA_LOG_ID, axeCatalog));
  }

  /*
  void deleteCatalogShouldDeleteCatalogWhenGivenIdOfCatalog() {
// given
    final List<Catalog> expectedCatalogss = List.of(AVON_PARIS_CATA_LOG);
    // when
    underTest.deleteCatalog(INDIVIDUAL_BLUE_CATA_LOG.getId());
    final List<Catalog> actual = underTest.getAllCatalog();
    // then
    assertThat(actual).isEqualTo(expectedCatalogss);
  }
*/
}


