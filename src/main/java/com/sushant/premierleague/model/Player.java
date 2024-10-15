package com.sushant.premierleague.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="player_stats")
public class Player {
    private String date;
    @Column(name = "team_name")
    private String teamName;

    @Id
    @Column(name = "player_name")
    private String playerName;

    @Column(name = "total_passes")
    private int totalPasses;

    @Column(name = "accurate_passes")
    private int accuratePasses;

    @Column(name = "total_long_balls")
    private int totalLongBalls;

    @Column(name = "accurate_long_balls")
    private int accurateLongBalls;

    @Column(name = "total_cross")
    private int totalCross;

    @Column(name = "aerial_lost")
    private int aerialLost;

    @Column(name = "aerial_won")
    private int aerialWon;

    @Column(name = "duel_lost")
    private int duelLost;

    @Column(name = "duel_won")
    private int duelWon;

    @Column(name = "challenge")
    private int challenge;

    private int dispossessed;

    @Column(name = "total_contest")
    private int totalContest;

    @Column(name = "won_contest")
    private int wonContest;

    @Column(name = "blocked_scoring_attempt")
    private int blockedScoringAttempt;

    @Column(name = "total_clearance")
    private int totalClearance;

    @Column(name = "interception_won")
    private int interceptionWon;

    @Column(name = "was_fouled")
    private int wasFouled;

    private int fouls;

    private int touches;

    private Double rating;

    @Column(name = "possession_lost_control")
    private int possessionLostControl;

    @Column(name = "expected_goals")
    private Double expectedGoals;

    @Column(name = "original_rating")
    private Double originalRating;

    @Column(name = "alternative_rating")
    private Double alternativeRating;

}
