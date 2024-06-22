package com.ironhack.MusicLibrary.repository;

import com.ironhack.MusicLibrary.dtos.PlayListDTO;
import com.ironhack.MusicLibrary.model.PlayList;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface PlayListControllerInterface {
    PlayList createPlayList(@Valid PlayListDTO playListDTO);
    PlayList updatePlayList(Long playListId, @Valid PlayListDTO playListDTO);
    PlayList deletePlayList(Long playListId);
}
