package org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.serviceTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.domain.Artist;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.domain.Track;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception.TrackAlreadyExists;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.exception.TrackNotFoundException;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.repository.TrackRepository;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.service.TrackServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrackServiceTest
{      @Mock
      TrackRepository trackRepository;
       @InjectMocks
    TrackServiceImpl trackService;
       private Track track;
       private Artist artist;
 private  List<Track>trackList = trackRepository.findAll();
    @BeforeEach
    public void setUp(){
        this.artist = new Artist(860,"Dsp");
        this.track = new Track(990,"Sorry",8,this.artist);
    }
    @AfterEach
    public void clear(){
        this.artist=null;
        this.track=null;
    }
    @Test
    @DisplayName("Test Case for Saving Track")
    public void  trackToSaveShouldReturnTrackSuccess()throws TrackAlreadyExists
    {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        when(trackRepository.save(track)).thenReturn(track);
        assertEquals(track,trackService.addTrack(track));

    }
    @Test
    @DisplayName("Test case for Failing Track object")
    public void TrackToSaveShouldReturnShouldTrackFailure()
    {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        assertThrows(TrackAlreadyExists.class,()->trackService.addTrack(track));
    }
    @Test
    @DisplayName("Test case for deleted Track object")
    public void  deleteTrackSuccess() throws TrackNotFoundException
    {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        String res = trackService.deleteTrack(track.getTrackId());
        assertEquals("Track deleted  Successfully",res);
    }
    @Test
    @DisplayName("Test case for  deleted Track object Failure")
    public void deleteTrackFailure()
    {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        assertThrows(TrackNotFoundException.class,()->trackService.deleteTrack(track.getTrackId()));
    }
    @Test
    @DisplayName("Test case for   get All Tracks  object")
    public void  getAllTrackSuccess()
    {

        when(trackRepository.findAll()).thenReturn(trackList);
        assertEquals(trackList.size(),trackService.getAllTracks().size());
    }
    @Test
    @DisplayName("Test case for   get All Tracks  object Failure")
    public void getAllTracksFailure()
    {
        trackList = new ArrayList<>();
        when(trackRepository.findAll()).thenReturn(trackList);
        assertThrows(TrackNotFoundException.class,()->trackService.getAllTracks());
    }

}
