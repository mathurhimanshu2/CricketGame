package com.tekion.cricketgame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "cricket-match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "overs")
    private int overs;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team2_id")
    private Team team2;

    public Match(int overs, Team team1, Team team2) {
        this.overs = overs;
        this.team1 = team1;
        this.team2 = team2;
    }

}