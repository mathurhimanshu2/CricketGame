package com.tekion.cricketgame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "team")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ignore Hibernate-specific properties
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;
    @JsonProperty("team_name")
    private String teamName;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Player> players;

    public Team() {
    }

    public Team(String teamName, List<Player> players) {
        this.teamName = teamName;
        this.players = players;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}