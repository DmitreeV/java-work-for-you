package pet.project.javaworkforyou.vacancy.model;

import lombok.*;
import pet.project.javaworkforyou.category.model.Category;
import pet.project.javaworkforyou.company.model.Company;
import pet.project.javaworkforyou.location.model.Location;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Company company;
}
