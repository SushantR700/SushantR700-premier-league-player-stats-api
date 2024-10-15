package com.sushant.premierleague.service;

import com.sushant.premierleague.model.Player;
import com.sushant.premierleague.dao.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    // Method to get paginated players (returning Page<Player> for pagination metadata)
    public Page<Player> getPlayers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);  // Create a Pageable object
        return playerRepository.findAll(pageable);       // Return Page<Player> for pagination
    }

    // Method to get only a List<Player> (extracting content from the Page)
    public List<Player> getPlayersAsList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);  // Create a Pageable object
        Page<Player> pagedResult = playerRepository.findAll(pageable);
        return pagedResult.getContent();  // Return the content (list of players)
    }
    public List<Player> getPlayersFromTeam(String teamName){
//        return playerRepository.findAll().stream().filter(new Predicate<Player>() {
//            @Override
//            public boolean test(Player player) {
//                return teamName.equals(player.getTeamName());
//            }
//        }).collect(Collectors.toList());
        //So the equivalent of the above code can be written as:
        return  playerRepository.findAll().stream().filter(player -> teamName.equalsIgnoreCase(player.getTeamName())).collect(Collectors.toList());

    }

    public List<Player> getPlayersByName(String searchText){
       return playerRepository.findAll().stream().filter(player -> player.getPlayerName().toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList());
    }

    public List<Player> getPlayersByRating(double ratingValue){
        return playerRepository.findAll().stream().filter(player -> player.getRating() == ratingValue).collect(Collectors.toList());
    }

    public Player addPlayer(Player player){
        playerRepository.save(player);
        return player;
    }

    @Transactional
    public Player updatePlayer(Player updatedPlayer){
        Optional<Player> existingPlayer = playerRepository.findByPlayerName(updatedPlayer.getPlayerName());
        if(existingPlayer.isPresent()){
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.setPlayerName(updatedPlayer.getPlayerName());
            playerToUpdate.setTeamName(updatedPlayer.getTeamName());
            playerToUpdate.setRating(updatedPlayer.getRating());
            playerRepository.save(playerToUpdate);
            return playerToUpdate;
        }else{
            return  null;
        }
    }

    @Transactional
    public void deletePlayer(String playerName){
        playerRepository.deleteByPlayerName(playerName);
    }
}
