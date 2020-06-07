package com.janiszewski.MSSQLConnection.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length=30, unique=true, nullable=false)
    private String shortName;
    /*@Column(length=1024)
    private String fullName;
    private String description;*/

    @OneToOne(optional=false, cascade = CascadeType.ALL)
    private TenantDetails details;

    public Tenant(String shortName/*, String fullName, String description*/) {
        this.shortName = shortName;
        /*this.fullName = fullName;
        this.description = description;*/
    }
}
