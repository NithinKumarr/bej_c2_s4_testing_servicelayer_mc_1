package org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.repositoryTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.domain.Artist;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.domain.Track;
import org.niit.BEJ_C2_S3_REST_API_MongoDB_MC_1.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TrackRepositoryTest
{
    @Autowired
      TrackRepository trackRepository;
      Artist artist;
      Track track;
    @BeforeEach
    public void setUp()
    {
        this.artist = new Artist(121,"Justin Bieber");
        this.track = new Track(121,"Sorry",8,this.artist);
    }
    @Test
    @DisplayName("Test Case for Saving Track")
    public void givenCustomerToSaveReturnSaveTrack()
    {
        trackRepository.save(track);
        Track t =  trackRepository.findById(track.getTrackId()).get();
        assertEquals(track,t);

    }
    @Test
    @DisplayName("Test Case for Deleting Track")
    public void deleteTrack()
    {
//       trackRepository.insert(track);
       Track t = trackRepository.findById(track.getTrackId()).get();
        trackRepository.delete(t);
        assertEquals(Optional.empty(),trackRepository.findById(t.getTrackId()));
    }
    @Test
    @DisplayName("Test Case for getting Track")
       public void displayTrackList()
    {    trackRepository.deleteAll();
        trackRepository.insert(track);
        this.artist = new Artist(1005,"Charlie Puth");
        this.track = new Track(100,"Attention",9,this.artist);
        trackRepository.insert(track);
        List<Track> trackList  = trackRepository.findAll();
        assertEquals(2,trackList.size());
    }


}
