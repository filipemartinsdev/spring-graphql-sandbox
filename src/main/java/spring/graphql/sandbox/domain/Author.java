package spring.graphql.sandbox.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "author")
@AllArgsConstructor @NoArgsConstructor @Data
public class Author {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Column(name = "name", unique = true)
  private String name;

  private String bio = null;

  @NotNull
  @JoinColumn(name = "author_id")
  @OneToMany(
    cascade = CascadeType.ALL, 
    fetch = FetchType.LAZY 
  )
  private List<Book> books = new ArrayList<>();
}
