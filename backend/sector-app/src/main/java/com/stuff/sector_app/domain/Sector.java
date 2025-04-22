package com.stuff.sector_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="sectors")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Transient // Not persisted
    private List<Sector> children = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "selectedSectors")
    private Set<UserData> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Sector sector) return sector.id.equals(id);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
