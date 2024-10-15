package com.sushant.premierleague.controllers;

import com.sushant.premierleague.model.Player;
import com.sushant.premierleague.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers(@RequestParam(required = false) String team,
                              @RequestParam(required = false) String playerName,
                              @RequestParam(required = false) String rating,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size
    ){
        if(team != null){
            return playerService.getPlayersFromTeam(team);
        }else if(playerName != null){
            return playerService.getPlayersByName(playerName);

        } else if (rating != null) {
            return playerService.getPlayersByRating(Double.parseDouble(rating));
        }else{
            return playerService.getPlayers(page,size).getContent();
        }
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updatePlayer(@RequestBody Player player){
        Player updatedPlayer = playerService.updatePlayer(player);
        if(updatedPlayer != null){
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("No Such Player Found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Player Deleted Successfully", HttpStatus.OK);
    }

}
