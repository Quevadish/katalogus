package epam.progkor.katalogus.katalogus.model;

import java.util.Objects;

public class Catalog {

  private Long id;
  private String name;
  private Integer price;
  private Genre genre;
  private Storage storage;

  /**
   * termék adatai.
   *
   * @param id id.
   * @param name név.
   * @param price ár.
   * @param genre tipus.
   * @param storage raktár.
   */
  public Catalog(Long id, String name, Integer price, Genre genre, Storage storage) {
    this.name = name;
    this.price = price;
    this.id = id;
    this.genre = genre;
    this.storage = storage;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  public Storage getStorage() {
    return storage;
  }

  public void setStorage(Storage storage) {
    this.storage = storage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Catalog catalog = (Catalog) o;
    return Objects.equals(name, catalog.name) && Objects.equals(price, catalog.price)
            && Objects.equals(id, catalog.id)
            && genre == catalog.genre && storage == catalog.storage;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, price, id, genre, storage);
  }

  @Override
  public String toString() {
    return "Catalog{"
            +
            "name='" + name + '\''
            +
            ", price=" + price
            +
            ", id=" + id
            +
            ", genre=" + genre
            +
            ", storage=" + storage
            +
            '}';
  }
}
